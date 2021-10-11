//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.command;

import me.shatteredhej.railhack.railhackmod.helper.values.*;
import net.minecraft.util.text.*;
import me.shatteredhej.railhack.railhackmod.command.commands.*;
import java.util.*;
import java.util.function.*;

public class Commands
{
    public static ArrayList<Command> commandList;
    static HashMap<String, Command> listCommand;
    public static final TurokString prefix;
    public final Style style;
    
    public Commands(final Style style_) {
        this.style = style_;
        addCommand((Command)new Bind());
        addCommand((Command)new Prefix());
        addCommand((Command)new Settings());
        addCommand((Command)new Toggle());
        addCommand((Command)new Alert());
        addCommand((Command)new Help());
        addCommand((Command)new Friend());
        addCommand((Command)new Drawn());
        addCommand((Command)new EzMessage());
        addCommand((Command)new Enemy());
        addCommand((Command)new Config());
        Commands.commandList.sort(Comparator.comparing((Function<? super Command, ? extends Comparable>)Command::getName));
    }
    
    public static void addCommand(final Command command) {
        Commands.commandList.add(command);
        Commands.listCommand.put(command.getName().toLowerCase(), command);
    }
    
    public String[] getMessage(final String message) {
        String[] arguments = new String[0];
        if (this.hasPrefix(message)) {
            arguments = message.replaceFirst(Commands.prefix.getValue(), "").split(" ");
        }
        return arguments;
    }
    
    public boolean hasPrefix(final String message) {
        return message.startsWith(Commands.prefix.getValue());
    }
    
    public void setPrefix(final String newPrefix) {
        Commands.prefix.setValue(newPrefix);
    }
    
    public String getPrefix() {
        return Commands.prefix.getValue();
    }
    
    public static ArrayList<Command> getPureCommandList() {
        return Commands.commandList;
    }
    
    public static Command getCommandWithName(final String name) {
        return Commands.listCommand.get(name.toLowerCase());
    }
    
    static {
        Commands.commandList = new ArrayList<Command>();
        Commands.listCommand = new HashMap<String, Command>();
        prefix = new TurokString("Prefix", "Prefix", ".");
    }
}
