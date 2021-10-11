//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.render;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.zero.alpine.fork.listener.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import java.util.function.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import java.awt.*;
import net.minecraft.client.renderer.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.anti.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import net.minecraft.init.*;
import org.lwjgl.opengl.*;
import net.minecraft.item.*;
import net.minecraft.enchantment.*;
import net.minecraft.util.text.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.damage.*;
import net.minecraft.nbt.*;
import net.minecraft.client.network.*;
import java.util.*;
import me.shatteredhej.railhack.railhackmod.modules.chat.*;

public class WurstplusNameTags extends Module
{
    Setting show_armor;
    Setting show_health;
    Setting show_ping;
    Setting show_totems;
    Setting show_invis;
    Setting reverse;
    Setting simplify;
    Setting m_scale;
    Setting range;
    Setting r;
    Setting g;
    Setting b;
    Setting a;
    Setting rainbow_mode;
    Setting sat;
    Setting brightness;
    private static WurstplusNameTags INSTANCE;
    @EventHandler
    private final Listener<EventRender> on_render_name;
    
    public WurstplusNameTags() {
        super(Category.Render);
        this.show_armor = this.register("Armor", "NametagArmor", true);
        this.show_health = this.register("Health", "NametagHealth", true);
        this.show_ping = this.register("Ping", "NametagPing", true);
        this.show_totems = this.register("Totem Count", "NametagTotems", true);
        this.show_invis = this.register("Invis", "NametagInvis", true);
        this.reverse = this.register("Armor Reverse", "NametagReverse", true);
        this.simplify = this.register("Simplify", "NametagSimp", false);
        this.m_scale = this.register("Scale", "NametagScale", 4, 1, 15);
        this.range = this.register("Range", "NametagRange", 75, 1, 250);
        this.r = this.register("R", "NametagR", 255, 0, 255);
        this.g = this.register("G", "NametagG", 255, 0, 255);
        this.b = this.register("B", "NametagB", 255, 0, 255);
        this.a = this.register("A", "NametagA", 0.0, 0.0, 1.0);
        this.rainbow_mode = this.register("Rainbow", "NametagRainbow", false);
        this.sat = this.register("Saturation", "NametagSatiation", 0.8, 0.0, 1.0);
        this.brightness = this.register("Brightness", "NametagBrightness", 0.8, 0.0, 1.0);
        this.on_render_name = new Listener<EventRender>(event -> event.cancel(), (Predicate<EventRender>[])new Predicate[0]);
        this.name = "Nametags";
        this.tag = "Nametags";
        this.description = "MORE DETAILED NAMESSSSS";
        this.setInstance();
    }
    
    public static WurstplusNameTags getInstance() {
        if (WurstplusNameTags.INSTANCE == null) {
            WurstplusNameTags.INSTANCE = new WurstplusNameTags();
        }
        return WurstplusNameTags.INSTANCE;
    }
    
    private void setInstance() {
        WurstplusNameTags.INSTANCE = this;
    }
    
    public void render(final EventRender event) {
        for (final EntityPlayer player : WurstplusNameTags.mc.world.playerEntities) {
            if (player != null && !player.equals((Object)WurstplusNameTags.mc.player) && player.isEntityAlive() && (!player.isInvisible() || this.show_invis.getValue(true)) && WurstplusNameTags.mc.player.getDistance((Entity)player) < this.range.getValue(1)) {
                final double x = this.interpolate(player.lastTickPosX, player.posX, event.get_partial_ticks()) - WurstplusNameTags.mc.getRenderManager().renderPosX;
                final double y = this.interpolate(player.lastTickPosY, player.posY, event.get_partial_ticks()) - WurstplusNameTags.mc.getRenderManager().renderPosY;
                final double z = this.interpolate(player.lastTickPosZ, player.posZ, event.get_partial_ticks()) - WurstplusNameTags.mc.getRenderManager().renderPosZ;
                this.renderNameTag(player, x, y, z, event.get_partial_ticks());
            }
        }
    }
    
    public void onUpdate() {
        if (this.rainbow_mode.getValue(true)) {
            this.cycle_rainbow();
        }
    }
    
    public void cycle_rainbow() {
        final float[] tick_color = { System.currentTimeMillis() % 11520L / 11520.0f };
        final int color_rgb_o = Color.HSBtoRGB(tick_color[0], (float)this.sat.getValue(1), (float)this.brightness.getValue(1));
        this.r.getValue(color_rgb_o >> 16 & 0xFF);
        this.g.getValue(color_rgb_o >> 8 & 0xFF);
        this.b.getValue(color_rgb_o & 0xFF);
    }
    
    private void renderNameTag(final EntityPlayer player, final double x, final double y, final double z, final float delta) {
        double tempY = y;
        tempY += (player.isSneaking() ? 0.5 : 0.7);
        final Entity camera = WurstplusNameTags.mc.getRenderViewEntity();
        assert camera != null;
        final double originalPositionX = camera.posX;
        final double originalPositionY = camera.posY;
        final double originalPositionZ = camera.posZ;
        camera.posX = this.interpolate(camera.prevPosX, camera.posX, delta);
        camera.posY = this.interpolate(camera.prevPosY, camera.posY, delta);
        camera.posZ = this.interpolate(camera.prevPosZ, camera.posZ, delta);
        final String displayTag = this.getDisplayTag(player);
        final double distance = camera.getDistance(x + WurstplusNameTags.mc.getRenderManager().viewerPosX, y + WurstplusNameTags.mc.getRenderManager().viewerPosY, z + WurstplusNameTags.mc.getRenderManager().viewerPosZ);
        final int width = WurstplusNameTags.mc.fontRenderer.getStringWidth(displayTag) / 2;
        double scale = (0.0018 + this.m_scale.getValue(1) * (distance * 0.3)) / 1000.0;
        if (distance <= 8.0) {
            scale = 0.0245;
        }
        GlStateManager.pushMatrix();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, -1500000.0f);
        GlStateManager.disableLighting();
        GlStateManager.translate((float)x, (float)tempY + 1.4f, (float)z);
        GlStateManager.rotate(-WurstplusNameTags.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(WurstplusNameTags.mc.getRenderManager().playerViewX, (WurstplusNameTags.mc.gameSettings.thirdPersonView == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(-scale, -scale, scale);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.enableBlend();
        final boolean is_friend = FriendUtil.isFriend(player.getName());
        final boolean is_enemy = EnemyUtil.isEnemy(player.getName());
        int red = this.r.getValue(1);
        int green = this.g.getValue(1);
        int blue = this.b.getValue(1);
        if (is_friend) {
            red = 157;
            green = 99;
            blue = 255;
        }
        if (is_enemy) {
            red = 255;
            green = 40;
            blue = 7;
        }
        WurstplusRenderUtil.drawRect(-width - 2 - 1.0f, -(WurstplusNameTags.mc.fontRenderer.FONT_HEIGHT + 1) - 1.0f, width + 3.0f, 2.5f, (float)red, (float)green, (float)blue, (float)this.a.getValue(1));
        WurstplusRenderUtil.drawRect((float)(-width - 2), (float)(-(WurstplusNameTags.mc.fontRenderer.FONT_HEIGHT + 1)), width + 2.0f, 1.5f, 1426063360);
        GlStateManager.disableBlend();
        final ItemStack renderMainHand = player.getHeldItemMainhand().copy();
        if (renderMainHand.hasEffect() && (renderMainHand.getItem() instanceof ItemTool || renderMainHand.getItem() instanceof ItemArmor)) {
            renderMainHand.stackSize = 1;
        }
        if (!renderMainHand.isEmpty && renderMainHand.getItem() != Items.AIR) {
            final String stackName = renderMainHand.getDisplayName();
            final int stackNameWidth = WurstplusNameTags.mc.fontRenderer.getStringWidth(stackName) / 2;
            GL11.glPushMatrix();
            GL11.glScalef(0.75f, 0.75f, 0.0f);
            WurstplusNameTags.mc.fontRenderer.drawStringWithShadow(stackName, (float)(-stackNameWidth), -(this.getBiggestArmorTag(player) + 18.0f), -1);
            GL11.glScalef(1.5f, 1.5f, 1.0f);
            GL11.glPopMatrix();
        }
        if (this.show_armor.getValue(true)) {
            GlStateManager.pushMatrix();
            int xOffset = -8;
            for (final ItemStack stack : player.inventory.armorInventory) {
                if (stack != null) {
                    xOffset -= 8;
                }
            }
            xOffset -= 8;
            final ItemStack renderOffhand = player.getHeldItemOffhand().copy();
            if (renderOffhand.hasEffect() && (renderOffhand.getItem() instanceof ItemTool || renderOffhand.getItem() instanceof ItemArmor)) {
                renderOffhand.stackSize = 1;
            }
            this.renderItemStack(renderOffhand, xOffset);
            xOffset += 16;
            if (this.reverse.getValue(true)) {
                for (final ItemStack stack2 : player.inventory.armorInventory) {
                    if (stack2 != null) {
                        final ItemStack armourStack = stack2.copy();
                        if (armourStack.hasEffect() && (armourStack.getItem() instanceof ItemTool || armourStack.getItem() instanceof ItemArmor)) {
                            armourStack.stackSize = 1;
                        }
                        this.renderItemStack(armourStack, xOffset);
                        xOffset += 16;
                    }
                }
            }
            else {
                for (int i = player.inventory.armorInventory.size(); i > 0; --i) {
                    final ItemStack stack2 = (ItemStack)player.inventory.armorInventory.get(i - 1);
                    final ItemStack armourStack = stack2.copy();
                    if (armourStack.hasEffect() && (armourStack.getItem() instanceof ItemTool || armourStack.getItem() instanceof ItemArmor)) {
                        armourStack.stackSize = 1;
                    }
                    this.renderItemStack(armourStack, xOffset);
                    xOffset += 16;
                }
            }
            this.renderItemStack(renderMainHand, xOffset);
            GlStateManager.popMatrix();
        }
        WurstplusNameTags.mc.fontRenderer.drawStringWithShadow(displayTag, (float)(-width), (float)(-(WurstplusNameTags.mc.fontRenderer.FONT_HEIGHT - 1)), this.getDisplayColour(player));
        camera.posX = originalPositionX;
        camera.posY = originalPositionY;
        camera.posZ = originalPositionZ;
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.disablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, 1500000.0f);
        GlStateManager.popMatrix();
    }
    
    private void renderItemStack(final ItemStack stack, final int x) {
        GlStateManager.pushMatrix();
        GlStateManager.depthMask(true);
        GlStateManager.clear(256);
        RenderHelper.enableStandardItemLighting();
        WurstplusNameTags.mc.getRenderItem().zLevel = -150.0f;
        GlStateManager.disableAlpha();
        GlStateManager.enableDepth();
        GlStateManager.disableCull();
        WurstplusNameTags.mc.getRenderItem().renderItemAndEffectIntoGUI(stack, x, -29);
        WurstplusNameTags.mc.getRenderItem().renderItemOverlays(WurstplusNameTags.mc.fontRenderer, stack, x, -29);
        WurstplusNameTags.mc.getRenderItem().zLevel = 0.0f;
        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableCull();
        GlStateManager.enableAlpha();
        GlStateManager.scale(0.5f, 0.5f, 0.5f);
        GlStateManager.disableDepth();
        this.renderEnchantmentText(stack, x);
        GlStateManager.enableDepth();
        GlStateManager.scale(2.0f, 2.0f, 2.0f);
        GlStateManager.popMatrix();
    }
    
    private void renderEnchantmentText(final ItemStack stack, final int x) {
        int enchantmentY = -37;
        final NBTTagList enchants = stack.getEnchantmentTagList();
        if (enchants.tagCount() > 2 && this.simplify.getValue(true)) {
            WurstplusNameTags.mc.fontRenderer.drawStringWithShadow("god", (float)(x * 2), (float)enchantmentY, -3977919);
            enchantmentY -= 8;
        }
        else {
            for (int index = 0; index < enchants.tagCount(); ++index) {
                final short id = enchants.getCompoundTagAt(index).getShort("id");
                final short level = enchants.getCompoundTagAt(index).getShort("lvl");
                final Enchantment enc = Enchantment.getEnchantmentByID((int)id);
                if (enc != null) {
                    String encName = enc.isCurse() ? (TextFormatting.RED + enc.getTranslatedName((int)level).substring(11).substring(0, 1).toLowerCase()) : enc.getTranslatedName((int)level).substring(0, 1).toLowerCase();
                    encName += level;
                    WurstplusNameTags.mc.fontRenderer.drawStringWithShadow(encName, (float)(x * 2), (float)enchantmentY, -1);
                    enchantmentY -= 8;
                }
            }
        }
        if (DamageUtil.hasDurability(stack)) {
            final int percent = DamageUtil.getRoundedDamage(stack);
            String color;
            if (percent >= 60) {
                color = this.section_sign() + "a";
            }
            else if (percent >= 25) {
                color = this.section_sign() + "e";
            }
            else {
                color = this.section_sign() + "c";
            }
            WurstplusNameTags.mc.fontRenderer.drawStringWithShadow(color + percent + "%", (float)(x * 2), (enchantmentY < -62) ? ((float)enchantmentY) : -62.0f, -1);
        }
    }
    
    private float getBiggestArmorTag(final EntityPlayer player) {
        float enchantmentY = 0.0f;
        boolean arm = false;
        for (final ItemStack stack : player.inventory.armorInventory) {
            float encY = 0.0f;
            if (stack != null) {
                final NBTTagList enchants = stack.getEnchantmentTagList();
                for (int index = 0; index < enchants.tagCount(); ++index) {
                    final short id = enchants.getCompoundTagAt(index).getShort("id");
                    final Enchantment enc = Enchantment.getEnchantmentByID((int)id);
                    if (enc != null) {
                        encY += 8.0f;
                        arm = true;
                    }
                }
            }
            if (encY > enchantmentY) {
                enchantmentY = encY;
            }
        }
        final ItemStack renderMainHand = player.getHeldItemMainhand().copy();
        if (renderMainHand.hasEffect()) {
            float encY2 = 0.0f;
            final NBTTagList enchants2 = renderMainHand.getEnchantmentTagList();
            for (int index2 = 0; index2 < enchants2.tagCount(); ++index2) {
                final short id2 = enchants2.getCompoundTagAt(index2).getShort("id");
                final Enchantment enc2 = Enchantment.getEnchantmentByID((int)id2);
                if (enc2 != null) {
                    encY2 += 8.0f;
                    arm = true;
                }
            }
            if (encY2 > enchantmentY) {
                enchantmentY = encY2;
            }
        }
        final ItemStack renderOffHand = player.getHeldItemOffhand().copy();
        if (renderOffHand.hasEffect()) {
            float encY = 0.0f;
            final NBTTagList enchants = renderOffHand.getEnchantmentTagList();
            for (int index = 0; index < enchants.tagCount(); ++index) {
                final short id = enchants.getCompoundTagAt(index).getShort("id");
                final Enchantment enc = Enchantment.getEnchantmentByID((int)id);
                if (enc != null) {
                    encY += 8.0f;
                    arm = true;
                }
            }
            if (encY > enchantmentY) {
                enchantmentY = encY;
            }
        }
        return (arm ? 0 : 20) + enchantmentY;
    }
    
    private String getDisplayTag(final EntityPlayer player) {
        String name = player.getDisplayNameString();
        if (!this.show_health.getValue(true)) {
            return name;
        }
        final float health = player.getHealth() + player.getAbsorptionAmount();
        if (health <= 0.0f) {
            return name + " - DEAD";
        }
        String color;
        if (health > 25.0f) {
            color = this.section_sign() + "5";
        }
        else if (health > 20.0f) {
            color = this.section_sign() + "a";
        }
        else if (health > 15.0f) {
            color = this.section_sign() + "2";
        }
        else if (health > 10.0f) {
            color = this.section_sign() + "6";
        }
        else if (health > 5.0f) {
            color = this.section_sign() + "c";
        }
        else {
            color = this.section_sign() + "4";
        }
        String pingStr = "";
        if (this.show_ping.getValue(true)) {
            try {
                final int responseTime = Objects.requireNonNull(WurstplusNameTags.mc.getConnection()).getPlayerInfo(player.getUniqueID()).getResponseTime();
                if (responseTime > 150) {
                    pingStr = pingStr + this.section_sign() + "4";
                }
                else if (responseTime > 100) {
                    pingStr = pingStr + this.section_sign() + "6";
                }
                else {
                    pingStr = pingStr + this.section_sign() + "2";
                }
                pingStr = pingStr + responseTime + "ms ";
            }
            catch (Exception ex) {}
        }
        String popStr = " ";
        if (this.show_totems.getValue(true)) {
            try {
                popStr += ((TotemPopNotifier.totem_pop_counter.get(player.getName()) == null) ? (this.section_sign() + "70") : (this.section_sign() + "c -" + TotemPopNotifier.totem_pop_counter.get(player.getName())));
            }
            catch (Exception ex2) {}
        }
        if (Math.floor(health) == health) {
            name = name + color + " " + ((health > 0.0f) ? Integer.valueOf((int)Math.floor(health)) : "dead");
        }
        else {
            name = name + color + " " + ((health > 0.0f) ? Integer.valueOf((int)health) : "dead");
        }
        return pingStr + this.section_sign() + "r" + name + this.section_sign() + "r" + popStr;
    }
    
    private int getDisplayColour(final EntityPlayer player) {
        final int colour = -5592406;
        if (FriendUtil.isFriend(player.getName())) {
            return -11157267;
        }
        if (EnemyUtil.isEnemy(player.getName())) {
            return -49632;
        }
        return colour;
    }
    
    private double interpolate(final double previous, final double current, final float delta) {
        return previous + (current - previous) * delta;
    }
    
    public String section_sign() {
        return "§";
    }
    
    static {
        WurstplusNameTags.INSTANCE = new WurstplusNameTags();
    }
}
