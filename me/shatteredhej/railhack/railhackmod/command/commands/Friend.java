//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.command.commands;

import me.shatteredhej.railhack.railhackmod.command.*;
import com.mojang.realmsclient.gui.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.anti.*;
import java.util.*;

public class Friend extends Command
{
    public static ChatFormatting red;
    public static ChatFormatting green;
    public static ChatFormatting bold;
    public static ChatFormatting reset;
    
    public Friend() {
        super("friend", "To add friends");
    }
    
    public boolean getMessage(final String[] message) {
        if (message.length == 1) {
            MessageUtil.send_client_message("Add - add friend");
            MessageUtil.send_client_message("Del - delete friend");
            MessageUtil.send_client_message("List - list friends");
            return true;
        }
        if (message.length != 2) {
            if (message.length >= 3) {
                if (message[1].equalsIgnoreCase("add")) {
                    if (FriendUtil.isFriend(message[2])) {
                        MessageUtil.send_client_message("Player " + Friend.green + Friend.bold + message[2] + Friend.reset + " is already your friend");
                        return true;
                    }
                    final FriendUtil.Friend f = FriendUtil.get_friend_object(message[2]);
                    if (f == null) {
                        MessageUtil.send_client_error_message("Cannot find " + Friend.red + Friend.bold + "UUID" + Friend.reset + " for that player");
                        return true;
                    }
                    FriendUtil.friends.add(f);
                    MessageUtil.send_client_message("Player " + Friend.green + Friend.bold + message[2] + Friend.reset + " is now your friend");
                    return true;
                }
                else if (message[1].equalsIgnoreCase("del") || message[1].equalsIgnoreCase("remove") || message[1].equalsIgnoreCase("delete")) {
                    if (!FriendUtil.isFriend(message[2])) {
                        MessageUtil.send_client_message("Player " + Friend.red + Friend.bold + message[2] + Friend.reset + " is already unfriended");
                        return true;
                    }
                    final FriendUtil.Friend f = FriendUtil.friends.stream().filter(friend -> friend.getUsername().equalsIgnoreCase(message[2])).findFirst().get();
                    FriendUtil.friends.remove(f);
                    MessageUtil.send_client_message("Player " + Friend.red + Friend.bold + message[2] + Friend.reset + " is now unfriended");
                    return true;
                }
            }
            return true;
        }
        if (message[1].equalsIgnoreCase("list")) {
            if (FriendUtil.friends.isEmpty()) {
                MessageUtil.send_client_message("You appear to have " + Friend.red + Friend.bold + "no" + Friend.reset + " friends");
            }
            else {
                for (final FriendUtil.Friend friend2 : FriendUtil.friends) {
                    MessageUtil.send_client_message("" + Friend.green + Friend.bold + friend2.getUsername());
                }
            }
            return true;
        }
        if (FriendUtil.isFriend(message[1])) {
            MessageUtil.send_client_message("Player " + Friend.green + Friend.bold + message[1] + Friend.reset + " is your friend :D");
            return true;
        }
        MessageUtil.send_client_error_message("Player " + Friend.green + Friend.bold + message[1] + Friend.reset + " is now friended");
        return true;
    }
    
    static {
        Friend.red = ChatFormatting.RED;
        Friend.green = ChatFormatting.GREEN;
        Friend.bold = ChatFormatting.BOLD;
        Friend.reset = ChatFormatting.RESET;
    }
}
