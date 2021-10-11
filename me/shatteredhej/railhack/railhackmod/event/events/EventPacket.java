//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.event.events;

import me.shatteredhej.railhack.railhackmod.event.*;
import net.minecraft.network.*;

public class EventPacket extends EventCancellable
{
    private final Packet packet;
    
    public EventPacket(final Packet packet) {
        this.packet = packet;
    }
    
    public Packet getPacket() {
        return this.packet;
    }
    
    public static class ReceivePacket extends EventPacket
    {
        public ReceivePacket(final Packet packet) {
            super(packet);
        }
    }
    
    public static class SendPacket extends EventPacket
    {
        public SendPacket(final Packet packet) {
            super(packet);
        }
    }
}
