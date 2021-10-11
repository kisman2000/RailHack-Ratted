//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack;

import net.minecraftforge.fml.common.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.*;
import me.shatteredhej.railhack.railhackmod.helper.*;
import com.mojang.realmsclient.gui.*;
import net.minecraftforge.fml.common.event.*;
import me.shatteredhej.railhack.railhackmod.manager.*;
import org.lwjgl.opengl.*;
import me.shatteredhej.railhack.railhackmod.event.*;
import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.*;
import org.apache.logging.log4j.*;
import net.minecraft.client.*;
import me.shatteredhej.railhack.railhackmod.helper.task.*;

@Mod(modid = "railhack", version = "0.4.8")
public class RailHack
{
    @Mod.Instance
    private static RailHack MASTER;
    public static final String RailHackName = "[RailHack]";
    public static final String RailHackVersion = "0.4.8";
    public static final String RailHackSign = " ";
    public static final int RailHackGuiKey = 54;
    public static final int RailHackKeyDelete = 211;
    public static final int RailHackGuiCloseKey = 1;
    public static Logger railhackRegisterLog;
    private static SettingManager settingManager;
    private static ConfigManager configManager;
    private static ModuleManager moduleManager;
    private static HudManager hudManager;
    public static Gui clickGui;
    public static Hud clickHud;
    public static Turok turok;
    public static ChatFormatting g;
    public static ChatFormatting r;
    
    @Mod.EventHandler
    public void RailHackStarting(final FMLInitializationEvent event) {
        this.initLog("[Root_Client]");
        HandleEvent.INSTANCE = new HandleEvent();
        sendMinecraftLog("initialising managers");
        RailHack.settingManager = new SettingManager();
        RailHack.configManager = new ConfigManager();
        RailHack.moduleManager = new ModuleManager();
        RailHack.hudManager = new HudManager();
        final EventManager event_manager = new EventManager();
        final CommandManager command_manager = new CommandManager();
        sendMinecraftLog("done");
        sendMinecraftLog("initialising guis");
        Display.setTitle("Root_Client");
        RailHack.clickGui = new Gui();
        RailHack.clickHud = new Hud();
        sendMinecraftLog("done");
        RailHack.turok = new Turok("Turok");
        sendMinecraftLog("done");
        sendMinecraftLog("initialising commands and events");
        RegisterEvent.register_command_manager(command_manager);
        RegisterEvent.register_module_manager(event_manager);
        sendMinecraftLog("done");
        sendMinecraftLog("loading settings");
        RailHack.configManager.load_settings();
        sendMinecraftLog("done");
        Start.start();
        if (RailHack.moduleManager.getModuleWithTag("GUI").isActive()) {
            RailHack.moduleManager.getModuleWithTag("GUI").setActive(false);
        }
        if (RailHack.moduleManager.getModuleWithTag("HUD").isActive()) {
            RailHack.moduleManager.getModuleWithTag("HUD").setActive(false);
        }
        sendMinecraftLog("client started");
        sendMinecraftLog("AutoCrystal enabled");
    }
    
    public void initLog(final String name) {
        RailHack.railhackRegisterLog = LogManager.getLogger(name);
        sendMinecraftLog("starting wurstplustwo");
    }
    
    public static void sendMinecraftLog(final String log) {
        RailHack.railhackRegisterLog.info(log);
    }
    
    public static String get_name() {
        return "[Root_Client]";
    }
    
    public static String get_version() {
        return "0.4.8";
    }
    
    public static String get_actual_user() {
        return Minecraft.getMinecraft().getSession().getUsername();
    }
    
    public static ConfigManager get_config_manager() {
        return RailHack.configManager;
    }
    
    public static ModuleManager get_hack_manager() {
        return RailHack.moduleManager;
    }
    
    public static SettingManager getSettingManager() {
        return RailHack.settingManager;
    }
    
    public static HudManager get_hud_manager() {
        return RailHack.hudManager;
    }
    
    public static ModuleManager getModuleManager() {
        return RailHack.moduleManager;
    }
    
    public static HandleEvent get_event_handler() {
        return HandleEvent.INSTANCE;
    }
    
    public static String smoth(final String base) {
        return Font.smoth(base);
    }
    
    static {
        RailHack.g = ChatFormatting.DARK_GRAY;
        RailHack.r = ChatFormatting.RESET;
    }
}
