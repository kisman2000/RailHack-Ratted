//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.render;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.client.entity.*;
import net.minecraft.item.*;
import net.minecraft.client.renderer.*;

public class HitAnimations extends Module
{
    Setting sword;
    
    public HitAnimations() {
        super(Category.Render);
        this.sword = this.register("Only Sword", "SwingOnlySword", false);
        this.name = "HitAnimations";
        this.tag = "HitAnimations";
    }
    
    public void onUpdate() {
        boolean boo = false;
        Label_0080: {
            if (this.sword.getValue(true)) {
                final EntityPlayerSP Sp = HitAnimations.mc.player;
                final ItemStack Stack = Sp.getHeldItemMainhand();
                if (Stack.getItem() instanceof ItemSword) {
                    final EntityRenderer Entity = HitAnimations.mc.entityRenderer;
                    final ItemRenderer Item = Entity.itemRenderer;
                    if (Item.prevEquippedProgressMainHand >= 0.9) {
                        boo = true;
                        break Label_0080;
                    }
                }
                final ItemStack var70 = Sp.getHeldItemMainhand();
                boo = false;
            }
            else {
                boo = true;
            }
        }
        if (boo) {
            EntityRenderer Entity = HitAnimations.mc.entityRenderer;
            ItemRenderer Item = Entity.itemRenderer;
            Item.equippedProgressMainHand = 1.0f;
            Entity = HitAnimations.mc.entityRenderer;
            Item = Entity.itemRenderer;
            final EntityPlayerSP var71 = HitAnimations.mc.player;
            Item.itemStackMainHand = var71.getHeldItemMainhand();
        }
    }
}
