//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.chat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import java.text.*;
import net.minecraft.util.math.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.zero.alpine.fork.listener.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.network.play.server.*;
import java.util.function.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import java.util.*;
import me.shatteredhej.railhack.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import java.math.*;
import java.util.concurrent.*;

public class Announcer extends Module
{
    Setting min_distance;
    Setting max_distance;
    Setting delay;
    Setting queue_size;
    Setting units;
    Setting movement_string;
    Setting suffix;
    Setting smol;
    private static DecimalFormat df;
    private static final Queue<String> message_q;
    private static final Map<String, Integer> mined_blocks;
    private static final Map<String, Integer> placed_blocks;
    private static final Map<String, Integer> dropped_items;
    private static final Map<String, Integer> consumed_items;
    private boolean first_run;
    private static Vec3d thisTickPos;
    private static Vec3d lastTickPos;
    private static int delay_count;
    private static double distanceTraveled;
    private static float thisTickHealth;
    private static float lastTickHealth;
    private static float gainedHealth;
    private static float lostHealth;
    @EventHandler
    private Listener<EventPacket.ReceivePacket> receive_listener;
    @EventHandler
    private Listener<EventPacket.SendPacket> send_listener;
    
    public Announcer() {
        super(Category.Chat);
        this.min_distance = this.register("Min Distance", "AnnouncerMinDist", 12, 1, 100);
        this.max_distance = this.register("Max Distance", "AnnouncerMaxDist", 144, 12, 1200);
        this.delay = this.register("Delay Seconds", "AnnouncerDelay", 4, 0, 20);
        this.queue_size = this.register("Queue Size", "AnnouncerQueueSize", 5, 1, 20);
        this.units = this.register("Units", "AnnouncerUnits", "Meters", this.combobox(new String[] { "Meters", "Feet", "Yards", "Inches" }));
        this.movement_string = this.register("Movement", "AnnouncerMovement", "Aha x", this.combobox(new String[] { "Aha x", "Leyta", "FUCK" }));
        this.suffix = this.register("Suffix", "AnnouncerSuffix", true);
        this.smol = this.register("Small Text", "AnnouncerSmallText", false);
        this.receive_listener = new Listener<EventPacket.ReceivePacket>(event -> {
            if (Announcer.mc.world == null) {
                return;
            }
            else {
                if (event.getPacket() instanceof SPacketUseBed) {
                    this.queue_message("I am going to bed now, goodnight");
                }
                return;
            }
        }, (Predicate<EventPacket.ReceivePacket>[])new Predicate[0]);
        CPacketPlayerDigging packet;
        String name;
        String name2;
        ItemStack stack;
        String name3;
        String name4;
        this.send_listener = new Listener<EventPacket.SendPacket>(event -> {
            if (Announcer.mc.world == null) {
                return;
            }
            else {
                if (event.getPacket() instanceof CPacketPlayerDigging) {
                    packet = (CPacketPlayerDigging)event.getPacket();
                    if (Announcer.mc.player.getHeldItemMainhand().getItem() != Items.AIR && (packet.getAction().equals((Object)CPacketPlayerDigging.Action.DROP_ITEM) || packet.getAction().equals((Object)CPacketPlayerDigging.Action.DROP_ALL_ITEMS))) {
                        name = Announcer.mc.player.inventory.getCurrentItem().getDisplayName();
                        if (Announcer.dropped_items.containsKey(name)) {
                            Announcer.dropped_items.put(name, Announcer.dropped_items.get(name) + 1);
                        }
                        else {
                            Announcer.dropped_items.put(name, 1);
                        }
                    }
                    if (packet.getAction().equals((Object)CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK)) {
                        name2 = Announcer.mc.world.getBlockState(packet.getPosition()).getBlock().getLocalizedName();
                        if (Announcer.mined_blocks.containsKey(name2)) {
                            Announcer.mined_blocks.put(name2, Announcer.mined_blocks.get(name2) + 1);
                        }
                        else {
                            Announcer.mined_blocks.put(name2, 1);
                        }
                    }
                }
                else {
                    if (event.getPacket() instanceof CPacketUpdateSign) {
                        this.queue_message("I just updated a sign with some epic text");
                    }
                    if (event.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
                        stack = Announcer.mc.player.inventory.getCurrentItem();
                        if (!stack.isEmpty()) {
                            if (stack.getItem() instanceof ItemBlock) {
                                name3 = Announcer.mc.player.inventory.getCurrentItem().getDisplayName();
                                if (Announcer.placed_blocks.containsKey(name3)) {
                                    Announcer.placed_blocks.put(name3, Announcer.placed_blocks.get(name3) + 1);
                                }
                                else {
                                    Announcer.placed_blocks.put(name3, 1);
                                }
                            }
                            else if (stack.getItem() == Items.END_CRYSTAL) {
                                name4 = "Crystals";
                                if (Announcer.placed_blocks.containsKey(name4)) {
                                    Announcer.placed_blocks.put(name4, Announcer.placed_blocks.get(name4) + 1);
                                }
                                else {
                                    Announcer.placed_blocks.put(name4, 1);
                                }
                            }
                        }
                    }
                }
                return;
            }
        }, (Predicate<EventPacket.SendPacket>[])new Predicate[0]);
        this.name = "Announcer";
        this.tag = "Announcer";
        this.description = "how to get muted 101";
    }
    
    public void onUpdate() {
        if (Announcer.mc.player == null || Announcer.mc.world == null) {
            this.setDisable();
            return;
        }
        try {
            this.get_tick_data();
        }
        catch (Exception ignored) {
            this.setDisable();
            return;
        }
        this.send_cycle();
    }
    
    private void get_tick_data() {
        Announcer.lastTickPos = Announcer.thisTickPos;
        Announcer.thisTickPos = Announcer.mc.player.getPositionVector();
        Announcer.distanceTraveled += Announcer.thisTickPos.distanceTo(Announcer.lastTickPos);
        Announcer.lastTickHealth = Announcer.thisTickHealth;
        Announcer.thisTickHealth = Announcer.mc.player.getHealth() + Announcer.mc.player.getAbsorptionAmount();
        final float healthDiff = Announcer.thisTickHealth - Announcer.lastTickHealth;
        if (healthDiff < 0.0f) {
            Announcer.lostHealth += healthDiff * -1.0f;
        }
        else {
            Announcer.gainedHealth += healthDiff;
        }
    }
    
    public void send_cycle() {
        ++Announcer.delay_count;
        if (Announcer.delay_count > this.delay.getValue(1) * 20) {
            Announcer.delay_count = 0;
            this.composeGameTickData();
            this.composeEventData();
            final Iterator<String> iterator = Announcer.message_q.iterator();
            if (iterator.hasNext()) {
                final String message = iterator.next();
                this.send_message(message);
                Announcer.message_q.remove(message);
            }
        }
    }
    
    private void send_message(String s) {
        if (this.suffix.getValue(true)) {
            final String i = " \u2763 ";
            s = s + i + RailHack.smoth("sponsored by wurstplus two");
        }
        if (this.smol.getValue(true)) {
            s = RailHack.smoth(s.toLowerCase());
        }
        Announcer.mc.player.connection.sendPacket((Packet)new CPacketChatMessage(s.replaceAll("§", "")));
    }
    
    public void queue_message(final String m) {
        if (Announcer.message_q.size() > this.queue_size.getValue(1)) {
            return;
        }
        Announcer.message_q.add(m);
    }
    
    protected void onEnable() {
        this.first_run = true;
        (Announcer.df = new DecimalFormat("#.#")).setRoundingMode(RoundingMode.CEILING);
        final Vec3d pos = Announcer.thisTickPos = (Announcer.lastTickPos = Announcer.mc.player.getPositionVector());
        Announcer.distanceTraveled = 0.0;
        final float health = Announcer.thisTickHealth = (Announcer.lastTickHealth = Announcer.mc.player.getHealth() + Announcer.mc.player.getAbsorptionAmount());
        Announcer.lostHealth = 0.0f;
        Announcer.gainedHealth = 0.0f;
        Announcer.delay_count = 0;
    }
    
    public static double round(final double unrounded, final int precision) {
        final BigDecimal bd = new BigDecimal(unrounded);
        final BigDecimal rounded = bd.setScale(precision, 4);
        return rounded.doubleValue();
    }
    
    private void composeGameTickData() {
        if (this.first_run) {
            this.first_run = false;
            return;
        }
        if (Announcer.distanceTraveled >= 1.0) {
            if (Announcer.distanceTraveled < this.delay.getValue(1) * this.min_distance.getValue(1)) {
                return;
            }
            if (Announcer.distanceTraveled > this.delay.getValue(1) * this.max_distance.getValue(1)) {
                Announcer.distanceTraveled = 0.0;
                return;
            }
            final CharSequence sb = new StringBuilder();
            if (this.movement_string.in("Aha x")) {
                ((StringBuilder)sb).append("aha x, I just traveled ");
            }
            if (this.movement_string.in("FUCK")) {
                ((StringBuilder)sb).append("FUCK, I just fucking traveled ");
            }
            if (this.movement_string.in("Leyta")) {
                ((StringBuilder)sb).append("leyta bitch, I just traveled ");
            }
            if (this.units.in("Feet")) {
                ((StringBuilder)sb).append(round(Announcer.distanceTraveled * 3.2808, 2));
                if ((int)Announcer.distanceTraveled == 1.0) {
                    ((StringBuilder)sb).append(" Foot");
                }
                else {
                    ((StringBuilder)sb).append(" Feet");
                }
            }
            if (this.units.in("Yards")) {
                ((StringBuilder)sb).append(round(Announcer.distanceTraveled * 1.0936, 2));
                if ((int)Announcer.distanceTraveled == 1.0) {
                    ((StringBuilder)sb).append(" Yard");
                }
                else {
                    ((StringBuilder)sb).append(" Yards");
                }
            }
            if (this.units.in("Inches")) {
                ((StringBuilder)sb).append(round(Announcer.distanceTraveled * 39.37, 2));
                if ((int)Announcer.distanceTraveled == 1.0) {
                    ((StringBuilder)sb).append(" Inch");
                }
                else {
                    ((StringBuilder)sb).append(" Inches");
                }
            }
            if (this.units.in("Meters")) {
                ((StringBuilder)sb).append(round(Announcer.distanceTraveled, 2));
                if ((int)Announcer.distanceTraveled == 1.0) {
                    ((StringBuilder)sb).append(" Meter");
                }
                else {
                    ((StringBuilder)sb).append(" Meters");
                }
            }
            this.queue_message(sb.toString());
            Announcer.distanceTraveled = 0.0;
        }
        if (Announcer.lostHealth != 0.0f) {
            final CharSequence sb = "HECK! I just lost " + Announcer.df.format(Announcer.lostHealth) + " health D:";
            this.queue_message((String)sb);
            Announcer.lostHealth = 0.0f;
        }
        if (Announcer.gainedHealth != 0.0f) {
            final CharSequence sb = "#ezmode I now have " + Announcer.df.format(Announcer.gainedHealth) + " more health";
            this.queue_message((String)sb);
            Announcer.gainedHealth = 0.0f;
        }
    }
    
    private void composeEventData() {
        for (final Map.Entry<String, Integer> kv : Announcer.mined_blocks.entrySet()) {
            this.queue_message("We be mining " + kv.getValue() + " " + kv.getKey() + " out here");
            Announcer.mined_blocks.remove(kv.getKey());
        }
        for (final Map.Entry<String, Integer> kv : Announcer.placed_blocks.entrySet()) {
            this.queue_message("We be placing " + kv.getValue() + " " + kv.getKey() + " out here");
            Announcer.placed_blocks.remove(kv.getKey());
        }
        for (final Map.Entry<String, Integer> kv : Announcer.dropped_items.entrySet()) {
            this.queue_message("I just dropped " + kv.getValue() + " " + kv.getKey() + ", whoops!");
            Announcer.dropped_items.remove(kv.getKey());
        }
        for (final Map.Entry<String, Integer> kv : Announcer.consumed_items.entrySet()) {
            this.queue_message("NOM NOM, I just ate " + kv.getValue() + " " + kv.getKey() + ", yummy");
            Announcer.consumed_items.remove(kv.getKey());
        }
    }
    
    static {
        Announcer.df = new DecimalFormat();
        message_q = new ConcurrentLinkedQueue<String>();
        mined_blocks = new ConcurrentHashMap<String, Integer>();
        placed_blocks = new ConcurrentHashMap<String, Integer>();
        dropped_items = new ConcurrentHashMap<String, Integer>();
        consumed_items = new ConcurrentHashMap<String, Integer>();
    }
}
