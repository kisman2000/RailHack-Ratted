//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util;

import net.minecraft.client.*;
import net.minecraft.client.multiplayer.*;
import net.arikia.dev.drpc.*;

public class DiscordUtil
{
    public static Minecraft mc;
    public static String details;
    public static String state;
    public static int players;
    public static int maxPlayers;
    public static int players2;
    public static int maxPlayers2;
    public static ServerData svr;
    public static String[] popInfo;
    
    public static void update() {
        final String applicationId = "705741263470723093";
        final String steamId = "";
        final DiscordRichPresence presence = new DiscordRichPresence();
        final DiscordEventHandlers handlers = new DiscordEventHandlers();
        DiscordRPC.discordInitialize(applicationId, handlers, true, steamId);
        DiscordRPC.discordUpdatePresence(presence);
        presence.startTimestamp = System.currentTimeMillis() / 2000L;
        presence.details = "RailHack";
        presence.state = "RailHack";
        presence.largeImageKey = "large";
        final DiscordRichPresence discordRichPresence;
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    DiscordUtil.details = "";
                    DiscordUtil.state = "";
                    DiscordUtil.players = 0;
                    DiscordUtil.maxPlayers = 0;
                    if (DiscordUtil.mc.isIntegratedServerRunning()) {
                        DiscordUtil.details = "RailHack";
                    }
                    else if (DiscordUtil.mc.getCurrentServerData() != null) {
                        DiscordUtil.svr = DiscordUtil.mc.getCurrentServerData();
                        if (!DiscordUtil.svr.serverIP.equals("")) {
                            DiscordUtil.details = "RailHack";
                            DiscordUtil.state = DiscordUtil.svr.serverIP;
                            if (DiscordUtil.svr.populationInfo != null) {
                                DiscordUtil.popInfo = DiscordUtil.svr.populationInfo.split("/");
                                if (DiscordUtil.popInfo.length > 2) {
                                    DiscordUtil.players2 = Integer.parseInt(DiscordUtil.popInfo[0]);
                                    DiscordUtil.maxPlayers2 = Integer.parseInt(DiscordUtil.popInfo[1]);
                                }
                            }
                        }
                    }
                    else {
                        DiscordUtil.details = "RailHack";
                        DiscordUtil.state = "RailHack";
                    }
                    if (!DiscordUtil.details.equals(discordRichPresence.details) || !DiscordUtil.state.equals(discordRichPresence.state)) {
                        discordRichPresence.startTimestamp = System.currentTimeMillis() / 1000L;
                    }
                    discordRichPresence.details = DiscordUtil.details;
                    discordRichPresence.state = DiscordUtil.state;
                    DiscordRPC.discordUpdatePresence(discordRichPresence);
                }
                catch (Exception e2) {
                    e2.printStackTrace();
                }
                try {
                    Thread.sleep(5000L);
                }
                catch (InterruptedException e3) {
                    e3.printStackTrace();
                }
            }
        }, "RPC-Callback-Handler").start();
    }
    
    static {
        DiscordUtil.mc = Minecraft.getMinecraft();
    }
}
