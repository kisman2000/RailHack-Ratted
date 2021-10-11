//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.movement;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.util.*;

public class AutoWalk extends Module
{
    public AutoWalk() {
        super(Category.Movement);
        this.name = "AutoWalk";
        this.tag = "AutoWalk";
    }
    
    public void onUpdate() {
        final MovementInput movementInput = AutoWalk.mc.player.movementInput;
        ++movementInput.moveForward;
    }
}
