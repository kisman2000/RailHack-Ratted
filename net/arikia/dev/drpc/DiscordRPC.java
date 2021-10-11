//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package net.arikia.dev.drpc;

import org.apache.commons.lang.*;
import org.apache.commons.io.*;
import java.io.*;
import com.sun.jna.*;

public final class DiscordRPC
{
    private static final String DLL_VERSION = "3.3.0";
    
    public static void discordInitialize(final String applicationId, final DiscordEventHandlers handlers, final boolean autoRegister) {
        DLL.INSTANCE.Discord_Initialize(applicationId, handlers, autoRegister ? 1 : 0, null);
    }
    
    public static void discordRegister(final String applicationId, final String command) {
        DLL.INSTANCE.Discord_Register(applicationId, command);
    }
    
    public static void discordInitialize(final String applicationId, final DiscordEventHandlers handlers, final boolean autoRegister, final String steamId) {
        DLL.INSTANCE.Discord_Initialize(applicationId, handlers, autoRegister ? 1 : 0, steamId);
    }
    
    public static void discordRegisterSteam(final String applicationId, final String steamId) {
        DLL.INSTANCE.Discord_RegisterSteamGame(applicationId, steamId);
    }
    
    public static void discordUpdateEventHandlers(final DiscordEventHandlers handlers) {
        DLL.INSTANCE.Discord_UpdateHandlers(handlers);
    }
    
    public static void discordShutdown() {
        DLL.INSTANCE.Discord_Shutdown();
    }
    
    public static void discordRunCallbacks() {
        DLL.INSTANCE.Discord_RunCallbacks();
    }
    
    public static void discordUpdatePresence(final DiscordRichPresence presence) {
        DLL.INSTANCE.Discord_UpdatePresence(presence);
    }
    
    public static void discordClearPresence() {
        DLL.INSTANCE.Discord_ClearPresence();
    }
    
    public static void discordRespond(final String userId, final DiscordReply reply) {
        DLL.INSTANCE.Discord_Respond(userId, reply.reply);
    }
    
    private static void loadDLL() {
        String name = "";
        String finalPath = "";
        String tempPath = "";
        if (SystemUtils.IS_OS_MAC_OSX) {
            name = System.mapLibraryName("libdiscord-rpc");
            final File homeDir = new File(System.getProperty("user.home") + "/Library/Application Support/");
            finalPath = "/darwin/" + name;
            tempPath = homeDir + "/discord-rpc/" + name;
        }
        else if (SystemUtils.IS_OS_WINDOWS) {
            name = System.mapLibraryName("discord-rpc");
            final File homeDir = new File(System.getenv("TEMP"));
            final boolean is64bit = System.getProperty("sun.arch.data.model").equals("64");
            finalPath = (is64bit ? ("/win-x64/" + name) : ("win-x86/" + name));
            tempPath = homeDir + "/discord-rpc/" + name;
        }
        else {
            name = System.mapLibraryName("libdiscord-rpc");
            final File homeDir = new File(System.getProperty("user.home"), ".discord-rpc");
            finalPath = "/linux/" + name;
            tempPath = homeDir + "/" + name;
        }
        final File f = new File(tempPath);
        try (final InputStream in = DiscordRPC.class.getResourceAsStream(finalPath);
             final OutputStream out = FileUtils.openOutputStream(f)) {
            IOUtils.copy(in, out);
            FileUtils.forceDeleteOnExit(f);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.load(f.getAbsolutePath());
    }
    
    static {
        loadDLL();
    }
    
    public enum DiscordReply
    {
        NO(0), 
        YES(1), 
        IGNORE(2);
        
        public final int reply;
        
        private DiscordReply(final int reply) {
            this.reply = reply;
        }
    }
    
    private interface DLL extends Library
    {
        public static final DLL INSTANCE = (DLL)Native.loadLibrary("discord-rpc", (Class)DLL.class);
        
        void Discord_Initialize(final String p0, final DiscordEventHandlers p1, final int p2, final String p3);
        
        void Discord_Register(final String p0, final String p1);
        
        void Discord_RegisterSteamGame(final String p0, final String p1);
        
        void Discord_UpdateHandlers(final DiscordEventHandlers p0);
        
        void Discord_Shutdown();
        
        void Discord_RunCallbacks();
        
        void Discord_UpdatePresence(final DiscordRichPresence p0);
        
        void Discord_ClearPresence();
        
        void Discord_Respond(final String p0, final int p1);
    }
}
