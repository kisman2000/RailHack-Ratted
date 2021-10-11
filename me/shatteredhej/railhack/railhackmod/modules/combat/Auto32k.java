//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.zero.alpine.fork.listener.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.network.play.client.*;
import java.util.function.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import net.minecraft.util.math.*;
import java.util.*;
import me.shatteredhej.railhack.railhackmod.util.block.*;
import net.minecraft.client.gui.*;
import net.minecraft.entity.player.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import net.minecraft.enchantment.*;
import net.minecraft.item.*;
import net.minecraft.inventory.*;
import net.minecraft.client.gui.inventory.*;

public class Auto32k extends Module
{
    private BlockPos pos;
    private int hopper_slot;
    private int redstone_slot;
    private int shulker_slot;
    private int ticks_past;
    private int[] rot;
    private boolean setup;
    private boolean place_redstone;
    private boolean dispenser_done;
    Setting place_mode;
    Setting swing;
    Setting delay;
    Setting rotate;
    Setting debug;
    Setting noClose;
    @EventHandler
    public Listener<EventPacket.SendPacket> listener;
    
    public Auto32k() {
        super(Category.Combat);
        this.place_mode = this.register("Place Mode", "AutotkPlaceMode", "Auto", this.combobox(new String[] { "Auto", "Looking", "Hopper" }));
        this.swing = this.register("Swing", "AutotkSwing", "Mainhand", this.combobox(new String[] { "Mainhand", "Offhand", "Both", "None" }));
        this.delay = this.register("Delay", "AutotkDelay", 4, 0, 10);
        this.rotate = this.register("Rotate", "Autotkrotate", false);
        this.debug = this.register("Debug", "AutotkDebug", false);
        this.noClose = this.register("No Close", "NoCloseAutoTTK", false);
        this.listener = new Listener<EventPacket.SendPacket>(event -> {
            if (this.noClose.getValue(true) && event.getPacket() instanceof CPacketCloseWindow) {
                event.cancel();
            }
            return;
        }, (Predicate<EventPacket.SendPacket>[])new Predicate[0]);
        this.name = "Auto32k";
        this.tag = "Auto32k";
        this.description = "fastest in the west";
    }
    
    protected void onEnable() {
        this.ticks_past = 0;
        this.setup = false;
        this.dispenser_done = false;
        this.place_redstone = false;
        this.hopper_slot = -1;
        int dispenser_slot = -1;
        this.redstone_slot = -1;
        this.shulker_slot = -1;
        int block_slot = -1;
        for (int i = 0; i < 9; ++i) {
            final Item item = Auto32k.mc.player.inventory.getStackInSlot(i).getItem();
            if (item == Item.getItemFromBlock((Block)Blocks.HOPPER)) {
                this.hopper_slot = i;
            }
            else if (item == Item.getItemFromBlock(Blocks.DISPENSER)) {
                dispenser_slot = i;
            }
            else if (item == Item.getItemFromBlock(Blocks.REDSTONE_BLOCK)) {
                this.redstone_slot = i;
            }
            else if (item instanceof ItemShulkerBox) {
                this.shulker_slot = i;
            }
            else if (item instanceof ItemBlock) {
                block_slot = i;
            }
        }
        if ((this.hopper_slot == -1 || dispenser_slot == -1 || this.redstone_slot == -1 || this.shulker_slot == -1 || block_slot == -1) && !this.place_mode.in("Hopper")) {
            MessageUtil.send_client_message("missing item");
            this.setDisable();
            return;
        }
        if (this.hopper_slot == -1 || this.shulker_slot == -1) {
            MessageUtil.send_client_message("missing item");
            this.setDisable();
            return;
        }
        if (this.place_mode.in("Looking")) {
            final RayTraceResult r = Auto32k.mc.player.rayTrace(5.0, Auto32k.mc.getRenderPartialTicks());
            this.pos = Objects.requireNonNull(r).getBlockPos().up();
            final double pos_x = this.pos.getX() - Auto32k.mc.player.posX;
            final double pos_z = this.pos.getZ() - Auto32k.mc.player.posZ;
            this.rot = ((Math.abs(pos_x) > Math.abs(pos_z)) ? ((pos_x > 0.0) ? new int[] { -1, 0 } : new int[] { 1, 0 }) : ((pos_z > 0.0) ? new int[] { 0, -1 } : new int[] { 0, 1 }));
            if (BlockUtil.canPlaceBlock(this.pos) && BlockUtil.isBlockEmpty(this.pos) && BlockUtil.isBlockEmpty(this.pos.add(this.rot[0], 0, this.rot[1])) && BlockUtil.isBlockEmpty(this.pos.add(0, 1, 0)) && BlockUtil.isBlockEmpty(this.pos.add(0, 2, 0)) && BlockUtil.isBlockEmpty(this.pos.add(this.rot[0], 1, this.rot[1]))) {
                BlockUtil.placeBlock(this.pos, block_slot, this.rotate.getValue(true), false, this.swing);
                BlockUtil.rotatePacket(this.pos.add(-this.rot[0], 1, -this.rot[1]).getX() + 0.5, this.pos.getY() + 1, this.pos.add(-this.rot[0], 1, -this.rot[1]).getZ() + 0.5);
                BlockUtil.placeBlock(this.pos.up(), dispenser_slot, false, false, this.swing);
                BlockUtil.openBlock(this.pos.up());
                this.setup = true;
            }
            else {
                MessageUtil.send_client_message("unable to place");
                this.setDisable();
            }
        }
        else if (this.place_mode.in("Auto")) {
            for (int x = -2; x <= 2; ++x) {
                for (int y = -1; y <= 1; ++y) {
                    for (int z = -2; z <= 2; ++z) {
                        this.rot = ((Math.abs(x) > Math.abs(z)) ? ((x > 0) ? new int[] { -1, 0 } : new int[] { 1, 0 }) : ((z > 0) ? new int[] { 0, -1 } : new int[] { 0, 1 }));
                        this.pos = Auto32k.mc.player.getPosition().add(x, y, z);
                        if (Auto32k.mc.player.getPositionEyes(Auto32k.mc.getRenderPartialTicks()).distanceTo(Auto32k.mc.player.getPositionVector().add((double)(x - this.rot[0] / 2.0f), y + 0.5, (double)(z + this.rot[1] / 2))) <= 4.5 && Auto32k.mc.player.getPositionEyes(Auto32k.mc.getRenderPartialTicks()).distanceTo(Auto32k.mc.player.getPositionVector().add(x + 0.5, y + 2.5, z + 0.5)) <= 4.5 && BlockUtil.canPlaceBlock(this.pos) && BlockUtil.isBlockEmpty(this.pos) && BlockUtil.isBlockEmpty(this.pos.add(this.rot[0], 0, this.rot[1])) && BlockUtil.isBlockEmpty(this.pos.add(0, 1, 0)) && BlockUtil.isBlockEmpty(this.pos.add(0, 2, 0)) && BlockUtil.isBlockEmpty(this.pos.add(this.rot[0], 1, this.rot[1]))) {
                            BlockUtil.placeBlock(this.pos, block_slot, this.rotate.getValue(true), false, this.swing);
                            BlockUtil.rotatePacket(this.pos.add(-this.rot[0], 1, -this.rot[1]).getX() + 0.5, this.pos.getY() + 1, this.pos.add(-this.rot[0], 1, -this.rot[1]).getZ() + 0.5);
                            BlockUtil.placeBlock(this.pos.up(), dispenser_slot, false, false, this.swing);
                            BlockUtil.openBlock(this.pos.up());
                            this.setup = true;
                            return;
                        }
                    }
                }
            }
            MessageUtil.send_client_message("unable to place");
            this.setDisable();
        }
        else {
            for (int z2 = -2; z2 <= 2; ++z2) {
                for (int y = -1; y <= 2; ++y) {
                    for (int x2 = -2; x2 <= 2; ++x2) {
                        if ((z2 != 0 || y != 0 || x2 != 0) && (z2 != 0 || y != 1 || x2 != 0) && BlockUtil.isBlockEmpty(Auto32k.mc.player.getPosition().add(z2, y, x2)) && Auto32k.mc.player.getPositionEyes(Auto32k.mc.getRenderPartialTicks()).distanceTo(Auto32k.mc.player.getPositionVector().add(z2 + 0.5, y + 0.5, x2 + 0.5)) < 4.5 && BlockUtil.isBlockEmpty(Auto32k.mc.player.getPosition().add(z2, y + 1, x2)) && Auto32k.mc.player.getPositionEyes(Auto32k.mc.getRenderPartialTicks()).distanceTo(Auto32k.mc.player.getPositionVector().add(z2 + 0.5, y + 1.5, x2 + 0.5)) < 4.5) {
                            BlockUtil.placeBlock(Auto32k.mc.player.getPosition().add(z2, y, x2), this.hopper_slot, this.rotate.getValue(true), false, this.swing);
                            BlockUtil.placeBlock(Auto32k.mc.player.getPosition().add(z2, y + 1, x2), this.shulker_slot, this.rotate.getValue(true), false, this.swing);
                            BlockUtil.openBlock(Auto32k.mc.player.getPosition().add(z2, y, x2));
                            this.pos = Auto32k.mc.player.getPosition().add(z2, y, x2);
                            this.dispenser_done = true;
                            this.place_redstone = true;
                            this.setup = true;
                            return;
                        }
                    }
                }
            }
        }
    }
    
    public void onUpdate() {
        if (this.ticks_past > 50 && !(Auto32k.mc.currentScreen instanceof GuiHopper)) {
            MessageUtil.send_client_message("inactive too long, disabling");
            this.setDisable();
            return;
        }
        if (this.setup && this.ticks_past > this.delay.getValue(1)) {
            if (!this.dispenser_done) {
                try {
                    Auto32k.mc.playerController.windowClick(Auto32k.mc.player.openContainer.windowId, 36 + this.shulker_slot, 0, ClickType.QUICK_MOVE, (EntityPlayer)Auto32k.mc.player);
                    this.dispenser_done = true;
                    if (this.debug.getValue(true)) {
                        MessageUtil.send_client_message("sent item");
                    }
                }
                catch (Exception ex) {}
            }
            if (!this.place_redstone) {
                BlockUtil.placeBlock(this.pos.add(0, 2, 0), this.redstone_slot, this.rotate.getValue(true), false, this.swing);
                if (this.debug.getValue(true)) {
                    MessageUtil.send_client_message("placed redstone");
                }
                this.place_redstone = true;
                return;
            }
            if (!this.place_mode.in("Hopper") && Auto32k.mc.world.getBlockState(this.pos.add(this.rot[0], 1, this.rot[1])).getBlock() instanceof BlockShulkerBox && Auto32k.mc.world.getBlockState(this.pos.add(this.rot[0], 0, this.rot[1])).getBlock() != Blocks.HOPPER && this.place_redstone && this.dispenser_done && !(Auto32k.mc.currentScreen instanceof GuiInventory)) {
                BlockUtil.placeBlock(this.pos.add(this.rot[0], 0, this.rot[1]), this.hopper_slot, this.rotate.getValue(true), false, this.swing);
                BlockUtil.openBlock(this.pos.add(this.rot[0], 0, this.rot[1]));
                if (this.debug.getValue(true)) {
                    MessageUtil.send_client_message("in the hopper");
                }
            }
            if (Auto32k.mc.currentScreen instanceof GuiHopper) {
                final GuiHopper gui = (GuiHopper)Auto32k.mc.currentScreen;
                for (int slot = 32; slot <= 40; ++slot) {
                    if (EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, gui.inventorySlots.getSlot(slot).getStack()) > 5) {
                        Auto32k.mc.player.inventory.currentItem = slot - 32;
                        break;
                    }
                }
                if (!(gui.inventorySlots.inventorySlots.get(0).getStack().getItem() instanceof ItemAir)) {
                    boolean swapReady = true;
                    if (((GuiContainer)Auto32k.mc.currentScreen).inventorySlots.getSlot(0).getStack().isEmpty) {
                        swapReady = false;
                    }
                    if (!((GuiContainer)Auto32k.mc.currentScreen).inventorySlots.getSlot(this.shulker_slot + 32).getStack().isEmpty) {
                        swapReady = false;
                    }
                    if (swapReady) {
                        Auto32k.mc.playerController.windowClick(((GuiContainer)Auto32k.mc.currentScreen).inventorySlots.windowId, 0, this.shulker_slot, ClickType.SWAP, (EntityPlayer)Auto32k.mc.player);
                        this.whenDisabled();
                    }
                }
            }
        }
        ++this.ticks_past;
    }
}
