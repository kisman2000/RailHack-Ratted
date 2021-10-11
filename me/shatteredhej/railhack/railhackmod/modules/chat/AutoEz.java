//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.chat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import java.util.concurrent.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.zero.alpine.fork.listener.*;
import net.minecraftforge.event.entity.living.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.world.*;
import net.minecraft.entity.player.*;
import java.util.function.*;
import net.minecraft.entity.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.chat.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import java.util.*;

public class AutoEz extends Module
{
    int delay_count;
    Setting custom;
    Setting gLeak;
    private static final ConcurrentHashMap targeted_players;
    @EventHandler
    private Listener<EventPacket.SendPacket> send_listener;
    @EventHandler
    private Listener<LivingDeathEvent> living_death_listener;
    
    public AutoEz() {
        super(Category.Chat);
        this.delay_count = 0;
        this.custom = this.register("Custom", "EzCustom", false);
        this.gLeak = this.register("Github Leak", "AutoEzLeak", false);
        CPacketUseEntity cPacketUseEntity;
        Entity target_entity;
        this.send_listener = new Listener<EventPacket.SendPacket>(event -> {
            if (AutoEz.mc.player == null) {
                return;
            }
            else {
                if (event.getPacket() instanceof CPacketUseEntity) {
                    cPacketUseEntity = (CPacketUseEntity)event.getPacket();
                    if (cPacketUseEntity.getAction().equals((Object)CPacketUseEntity.Action.ATTACK)) {
                        target_entity = cPacketUseEntity.getEntityFromWorld((World)AutoEz.mc.world);
                        if (target_entity instanceof EntityPlayer) {
                            add_target(target_entity.getName());
                        }
                    }
                }
                return;
            }
        }, (Predicate<EventPacket.SendPacket>[])new Predicate[0]);
        EntityLivingBase e;
        EntityPlayer player;
        this.living_death_listener = new Listener<LivingDeathEvent>(event -> {
            if (AutoEz.mc.player == null) {
                return;
            }
            else {
                e = event.getEntityLiving();
                if (e == null) {
                    return;
                }
                else {
                    if (e instanceof EntityPlayer) {
                        player = (EntityPlayer)e;
                        if (player.getHealth() <= 0.0f && AutoEz.targeted_players.containsKey(player.getName())) {
                            this.announce(player.getName());
                        }
                    }
                    return;
                }
            }
        }, (Predicate<LivingDeathEvent>[])new Predicate[0]);
        this.name = "AutoEz";
        this.tag = "AutoEz";
    }
    
    public void onUpdate() {
        for (final Entity entity : AutoEz.mc.world.getLoadedEntityList()) {
            if (entity instanceof EntityPlayer) {
                final EntityPlayer player = (EntityPlayer)entity;
                if (player.getHealth() > 0.0f || !AutoEz.targeted_players.containsKey(player.getName())) {
                    continue;
                }
                this.announce(player.getName());
            }
        }
        AutoEz.targeted_players.forEach((name, timeout) -> {
            if (timeout <= 0) {
                AutoEz.targeted_players.remove(name);
            }
            else {
                AutoEz.targeted_players.put(name, timeout - 1);
            }
            return;
        });
        ++this.delay_count;
    }
    
    public void announce(final String name) {
        if (this.delay_count < 150) {
            return;
        }
        this.delay_count = 0;
        AutoEz.targeted_players.remove(name);
        String message = "";
        if (this.custom.getValue(true)) {
            message += EzMessageUtil.get_message().replace("[", "").replace("]", "");
        }
        else {
            message += "say offhand fail or desync if gay";
        }
        if (this.gLeak.getValue(true)) {
            message += " - EzZZzzZZ";
        }
        AutoEz.mc.player.connection.sendPacket((Packet)new CPacketChatMessage(message));
    }
    
    public static void add_target(final String name) {
        if (!Objects.equals(name, AutoEz.mc.player.getName())) {
            AutoEz.targeted_players.put(name, 20);
        }
    }
    
    static {
        targeted_players = new ConcurrentHashMap();
    }
}
