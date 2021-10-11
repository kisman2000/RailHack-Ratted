//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.dev;

import me.shatteredhej.railhack.railhackmod.module.*;
import net.minecraft.client.entity.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import java.util.*;
import com.mojang.authlib.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;

public class WurstplusFakePlayer extends Module
{
    private EntityOtherPlayerMP fake_player;
    
    public WurstplusFakePlayer() {
        super(Category.Beta);
        this.name = "FakePlayer";
        this.tag = "FakePlayer";
    }
    
    protected void onEnable() {
        (this.fake_player = new EntityOtherPlayerMP((World)WurstplusFakePlayer.mc.world, new GameProfile(UUID.fromString("a08f613b-1d9c-4be3-9b4b-00f7a0732e58"), "RootClient"))).copyLocationAndAnglesFrom((Entity)WurstplusFakePlayer.mc.player);
        this.fake_player.rotationYawHead = WurstplusFakePlayer.mc.player.rotationYawHead;
        WurstplusFakePlayer.mc.world.addEntityToWorld(-100, (Entity)this.fake_player);
    }
    
    protected void whenDisabled() {
        try {
            WurstplusFakePlayer.mc.world.removeEntity((Entity)this.fake_player);
        }
        catch (Exception ex) {}
    }
}
