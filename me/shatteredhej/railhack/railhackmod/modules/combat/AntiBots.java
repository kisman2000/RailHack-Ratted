//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.zero.alpine.fork.listener.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import net.minecraftforge.event.entity.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.entity.player.*;
import java.util.function.*;
import net.minecraft.network.play.server.*;
import java.util.*;
import net.minecraft.util.*;

public class AntiBots extends Module
{
    private Map<Integer, UUID> _playersMap;
    @EventHandler
    private Listener<EventPlayerUpdate> onPlayerUpdate;
    @EventHandler
    private Listener<EventNetworkPacketEvent> onPacketEvent;
    @EventHandler
    private Listener<EntityJoinWorldEvent> OnWorldEvent;
    
    public AntiBots() {
        super(Category.Combat);
        this._playersMap = new HashMap<Integer, UUID>();
        final Iterator<EntityPlayer> iterator;
        EntityPlayer player;
        this.onPlayerUpdate = new Listener<EventPlayerUpdate>(event -> {
            if (AntiBots.mc.getCurrentServerData() == null) {
                return;
            }
            else {
                new ArrayList<EntityPlayer>(AntiBots.mc.world.playerEntities).iterator();
                while (iterator.hasNext()) {
                    player = iterator.next();
                    if (this.isBot(player)) {
                        AntiBots.mc.world.playerEntities.remove(player);
                    }
                }
                return;
            }
        }, (Predicate<EventPlayerUpdate>[])new Predicate[0]);
        SPacketSpawnPlayer packet;
        SPacketDestroyEntities packet2;
        final int[] array;
        int length;
        int i = 0;
        int e;
        this.onPacketEvent = new Listener<EventNetworkPacketEvent>(event -> {
            if (AntiBots.mc.world == null || AntiBots.mc.player == null) {
                return;
            }
            else {
                if (event.getPacket() instanceof SPacketSpawnPlayer) {
                    packet = (SPacketSpawnPlayer)event.getPacket();
                    if (Math.sqrt((AntiBots.mc.player.posX - packet.getX() / 0.0) * (AntiBots.mc.player.posX - packet.getX() / 0.0) + (AntiBots.mc.player.posY - packet.getY() / 0.0) * (AntiBots.mc.player.posY - packet.getY() / 0.0) + (AntiBots.mc.player.posZ - packet.getZ() / 0.0) * (AntiBots.mc.player.posZ - packet.getZ() / 0.0)) <= 0.0 && packet.getX() / 0.0 != AntiBots.mc.player.posX && packet.getY() / 0.0 != AntiBots.mc.player.posY && packet.getZ() / 0.0 != AntiBots.mc.player.posZ) {
                        this._playersMap.put(packet.getEntityID(), packet.getUniqueId());
                    }
                }
                else if (event.getPacket() instanceof SPacketDestroyEntities) {
                    packet2 = (SPacketDestroyEntities)event.getPacket();
                    packet2.getEntityIDs();
                    for (length = array.length; i < length; ++i) {
                        e = array[i];
                        if (this._playersMap.containsKey(e)) {
                            this._playersMap.remove(e);
                        }
                    }
                }
                return;
            }
        }, (Predicate<EventNetworkPacketEvent>[])new Predicate[0]);
        this.OnWorldEvent = new Listener<EntityJoinWorldEvent>(p_Event -> {
            if (p_Event.getEntity() == AntiBots.mc.player) {
                this._playersMap.clear();
            }
            return;
        }, (Predicate<EntityJoinWorldEvent>[])new Predicate[0]);
        this.name = "AntiBots";
        this.tag = "AntiBots";
        this.description = "no bots";
    }
    
    public boolean isBot(final EntityPlayer entity) {
        return entity.getUniqueID().toString().startsWith(entity.getName()) || !StringUtils.stripControlCodes(entity.getGameProfile().getName()).equals(entity.getName()) || entity.getGameProfile().getId() != entity.getUniqueID() || this._playersMap.containsKey(entity.getEntityId());
    }
}
