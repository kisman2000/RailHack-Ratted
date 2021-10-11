//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class KeyPearl extends Module
{
    int oldSlot;
    int tick;
    
    public KeyPearl() {
        super(Category.Misc);
        this.oldSlot = -1;
        this.tick = 0;
        this.name = "KeyPearl";
        this.tag = "KeyPearl";
    }
    
    public void onEnable() {
        this.oldSlot = KeyPearl.mc.player.inventory.currentItem;
    }
    
    public void onUpdate() {
        KeyPearl.mc.player.inventory.currentItem = this.findPearlInHotbar();
        if (KeyPearl.mc.player.inventory.getStackInSlot(KeyPearl.mc.player.inventory.currentItem).getItem() == Items.ENDER_PEARL) {
            KeyPearl.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
        }
        else if (KeyPearl.mc.player.inventory.getStackInSlot(KeyPearl.mc.player.inventory.currentItem).getItem() != Items.ENDER_PEARL) {
            KeyPearl.mc.player.inventory.currentItem = this.findPearlInHotbar();
            KeyPearl.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
            KeyPearl.mc.player.inventory.currentItem = this.oldSlot;
            this.tick = 0;
            this.setDisable();
        }
    }
    
    private int findPearlInHotbar() {
        int slot = 0;
        for (int i = 0; i < 9; ++i) {
            if (KeyPearl.mc.player.inventory.getStackInSlot(i).getItem() == Items.ENDER_PEARL) {
                slot = i;
                break;
            }
        }
        return slot;
    }
}
