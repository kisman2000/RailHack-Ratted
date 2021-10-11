//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.chat;

import me.shatteredhej.railhack.railhackmod.module.*;
import com.mojang.realmsclient.gui.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.zero.alpine.fork.listener.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.network.play.server.*;
import net.minecraft.world.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.anti.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import java.util.function.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import java.util.*;

public class TotemPopNotifier extends Module
{
    public static final HashMap<String, Integer> totem_pop_counter;
    public static ChatFormatting red;
    public static ChatFormatting green;
    public static ChatFormatting gold;
    public static ChatFormatting grey;
    public static ChatFormatting bold;
    public static ChatFormatting reset;
    public static ChatFormatting dark_green;
    public static ChatFormatting aqua;
    @EventHandler
    private final Listener<EventPacket.ReceivePacket> packet_event;
    
    public TotemPopNotifier() {
        super(Category.Chat);
        SPacketEntityStatus packet;
        Entity entity;
        int count;
        this.packet_event = new Listener<EventPacket.ReceivePacket>(event -> {
            if (event.getPacket() instanceof SPacketEntityStatus) {
                packet = (SPacketEntityStatus)event.getPacket();
                if (packet.getOpCode() == 35) {
                    entity = packet.getEntity((World)TotemPopNotifier.mc.world);
                    count = 1;
                    if (TotemPopNotifier.totem_pop_counter.containsKey(entity.getName())) {
                        count = TotemPopNotifier.totem_pop_counter.get(entity.getName());
                        TotemPopNotifier.totem_pop_counter.put(entity.getName(), ++count);
                    }
                    else {
                        TotemPopNotifier.totem_pop_counter.put(entity.getName(), count);
                    }
                    if (entity != TotemPopNotifier.mc.player) {
                        if (FriendUtil.isFriend(entity.getName())) {
                            MessageUtil.send_client_message(TotemPopNotifier.aqua + " [TotemPopNotifier]: " + TotemPopNotifier.reset + entity.getName() + TotemPopNotifier.reset + " has popped " + count + TotemPopNotifier.reset + " totems");
                        }
                        else {
                            MessageUtil.send_client_message(TotemPopNotifier.aqua + " [TotemPopNotifier]: " + TotemPopNotifier.reset + entity.getName() + TotemPopNotifier.reset + " has popped " + count + TotemPopNotifier.reset + " totems");
                        }
                    }
                }
            }
            return;
        }, (Predicate<EventPacket.ReceivePacket>[])new Predicate[0]);
        this.name = "TotemPopNotifier";
        this.tag = "TotemPopNotifier";
    }
    
    public void onUpdate() {
        for (final EntityPlayer player : TotemPopNotifier.mc.world.playerEntities) {
            if (!TotemPopNotifier.totem_pop_counter.containsKey(player.getName())) {
                continue;
            }
            if (!player.isDead && player.getHealth() > 0.0f) {
                continue;
            }
            final int count = TotemPopNotifier.totem_pop_counter.get(player.getName());
            TotemPopNotifier.totem_pop_counter.remove(player.getName());
            if (player == TotemPopNotifier.mc.player) {
                continue;
            }
            if (FriendUtil.isFriend(player.getName())) {
                MessageUtil.send_client_message(TotemPopNotifier.aqua + " [TotemPopNotifier]: " + TotemPopNotifier.reset + player.getName() + TotemPopNotifier.reset + " has died after popping " + count + TotemPopNotifier.reset + " totems");
            }
            else {
                MessageUtil.send_client_message(TotemPopNotifier.aqua + " [TotemPopNotifier]: " + TotemPopNotifier.reset + player.getName() + TotemPopNotifier.reset + " has died after popping " + count + TotemPopNotifier.reset + " totems");
            }
        }
    }
    
    static {
        totem_pop_counter = new HashMap<String, Integer>();
        TotemPopNotifier.red = ChatFormatting.RED;
        TotemPopNotifier.green = ChatFormatting.GREEN;
        TotemPopNotifier.gold = ChatFormatting.GOLD;
        TotemPopNotifier.grey = ChatFormatting.GRAY;
        TotemPopNotifier.bold = ChatFormatting.BOLD;
        TotemPopNotifier.reset = ChatFormatting.RESET;
        TotemPopNotifier.dark_green = ChatFormatting.DARK_GREEN;
        TotemPopNotifier.aqua = ChatFormatting.AQUA;
    }
}
