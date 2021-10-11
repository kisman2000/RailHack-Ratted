//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.movement;

import me.shatteredhej.railhack.railhackmod.module.*;
import net.minecraftforge.client.event.*;
import me.zero.alpine.fork.listener.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import java.util.function.*;
import net.minecraft.util.*;

public class NoSlow extends Module
{
    @EventHandler
    private Listener<InputUpdateEvent> eventListener;
    
    public NoSlow() {
        super(Category.Movement);
        final MovementInput movementInput;
        final MovementInput movementInput2;
        this.eventListener = new Listener<InputUpdateEvent>(event -> {
            if (NoSlow.mc.player.isHandActive() && !NoSlow.mc.player.isRiding()) {
                event.getMovementInput();
                movementInput.moveStrafe *= 5.0f;
                event.getMovementInput();
                movementInput2.moveForward *= 5.0f;
            }
            return;
        }, (Predicate<InputUpdateEvent>[])new Predicate[0]);
        this.name = "NoSlow";
        this.tag = "NoSlow";
        this.description = "dont make you slow while doing anything, duh.";
    }
}
