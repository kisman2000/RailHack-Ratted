//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.command.commands;

import me.shatteredhej.railhack.railhackmod.command.*;
import com.mojang.realmsclient.gui.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.anti.*;
import java.util.*;

public class Enemy extends Command
{
    public static ChatFormatting red;
    public static ChatFormatting green;
    public static ChatFormatting bold;
    public static ChatFormatting reset;
    
    public Enemy() {
        super("enemy", "To add enemy");
    }
    
    public boolean getMessage(final String[] message) {
        if (message.length == 1) {
            MessageUtil.send_client_message("Add - add enemy");
            MessageUtil.send_client_message("Del - delete enemy");
            MessageUtil.send_client_message("List - list enemies");
            return true;
        }
        if (message.length != 2) {
            if (message.length >= 3) {
                if (message[1].equalsIgnoreCase("add")) {
                    if (EnemyUtil.isEnemy(message[2])) {
                        MessageUtil.send_client_message("Player " + Enemy.green + Enemy.bold + message[2] + Enemy.reset + " is already your Enemy D:");
                        return true;
                    }
                    final EnemyUtil.Enemy f = EnemyUtil.get_enemy_object(message[2]);
                    if (f == null) {
                        MessageUtil.send_client_error_message("Cannot find " + Enemy.red + Enemy.bold + "UUID" + Enemy.reset + " for that player :(");
                        return true;
                    }
                    EnemyUtil.enemies.add(f);
                    MessageUtil.send_client_message("Player " + Enemy.green + Enemy.bold + message[2] + Enemy.reset + " is now your Enemy D:");
                    return true;
                }
                else if (message[1].equalsIgnoreCase("del") || message[1].equalsIgnoreCase("remove") || message[1].equalsIgnoreCase("delete")) {
                    if (!EnemyUtil.isEnemy(message[2])) {
                        MessageUtil.send_client_message("Player " + Enemy.red + Enemy.bold + message[2] + Enemy.reset + " is already not your Enemy :/");
                        return true;
                    }
                    final EnemyUtil.Enemy f = EnemyUtil.enemies.stream().filter(Enemy -> Enemy.getUsername().equalsIgnoreCase(message[2])).findFirst().get();
                    EnemyUtil.enemies.remove(f);
                    MessageUtil.send_client_message("Player " + Enemy.red + Enemy.bold + message[2] + Enemy.reset + " is now not your Enemy :)");
                    return true;
                }
            }
            return true;
        }
        if (message[1].equalsIgnoreCase("list")) {
            if (EnemyUtil.enemies.isEmpty()) {
                MessageUtil.send_client_message("You appear to have " + Enemy.red + Enemy.bold + "no" + Enemy.reset + " enemies :)");
            }
            else {
                for (final EnemyUtil.Enemy Enemy2 : EnemyUtil.enemies) {
                    MessageUtil.send_client_message("" + Enemy.green + Enemy.bold + Enemy2.getUsername());
                }
            }
            return true;
        }
        if (EnemyUtil.isEnemy(message[1])) {
            MessageUtil.send_client_message("Player " + Enemy.green + Enemy.bold + message[1] + Enemy.reset + " is your Enemy D:");
            return true;
        }
        MessageUtil.send_client_error_message("Player " + Enemy.red + Enemy.bold + message[1] + Enemy.reset + " is not your Enemy :)");
        return true;
    }
    
    static {
        Enemy.red = ChatFormatting.GREEN;
        Enemy.green = ChatFormatting.RED;
        Enemy.bold = ChatFormatting.BOLD;
        Enemy.reset = ChatFormatting.RESET;
    }
}
