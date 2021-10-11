//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.event.events;

import me.shatteredhej.railhack.railhackmod.event.*;
import net.minecraft.client.gui.*;

public class EventGUIScreen extends EventCancellable
{
    private final GuiScreen guiscreen;
    
    public EventGUIScreen(final GuiScreen screen) {
        this.guiscreen = screen;
    }
    
    public GuiScreen get_guiscreen() {
        return this.guiscreen;
    }
}
