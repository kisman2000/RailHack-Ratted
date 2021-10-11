//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.misc;

import me.shatteredhej.railhack.railhackmod.module.*;
import net.minecraft.entity.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.entity.monster.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import net.minecraft.init.*;
import java.util.*;

public class GhastNotifier extends Module
{
    private Set<Entity> ghasts;
    Setting chat;
    Setting sound;
    
    public GhastNotifier() {
        super(Category.Misc);
        this.ghasts = new HashSet<Entity>();
        this.chat = this.register("Chat", "GhastNotifierChat", false);
        this.sound = this.register("Sound", "GhastNotifierSound", false);
        this.name = "GhastNotifier";
        this.tag = "GhastNotifier";
    }
    
    public void onEnable() {
        this.ghasts.clear();
    }
    
    public void onUpdate() {
        for (final Entity entity : GhastNotifier.mc.world.getLoadedEntityList()) {
            if (entity instanceof EntityGhast && this.chat.getValue(true)) {
                MessageUtil.send_client_message("Ghast at: x" + entity.getPosition().getX() + " y" + entity.getPosition().getY() + " z" + entity.getPosition().getZ());
            }
            this.ghasts.add(entity);
            if (this.sound.getValue(true)) {
                GhastNotifier.mc.player.playSound(SoundEvents.BLOCK_ANVIL_DESTROY, 2.0f, 1.0f);
            }
        }
    }
}
