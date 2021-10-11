//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.misc;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.world.*;

public class FakeCreative extends Module
{
    public FakeCreative() {
        super(Category.Misc);
        this.name = "FakeCreative";
        this.tag = "FakeCreative";
        this.description = "fake gamemode";
    }
    
    public void onEnable() {
        FakeCreative.mc.playerController.setGameType(GameType.CREATIVE);
    }
    
    public void whenDisabled() {
        FakeCreative.mc.playerController.setGameType(GameType.SURVIVAL);
    }
}
