//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.misc;

import me.shatteredhej.railhack.railhackmod.module.*;
import com.mojang.realmsclient.gui.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import org.lwjgl.input.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.anti.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import net.minecraft.entity.*;

public class MiddleClickFriends extends Module
{
    private boolean clicked;
    public static ChatFormatting red;
    public static ChatFormatting green;
    public static ChatFormatting bold;
    public static ChatFormatting reset;
    
    public MiddleClickFriends() {
        super(Category.Misc);
        this.clicked = false;
        this.name = "MiddleClick";
        this.tag = "MiddleClick";
        this.description = "you press button and the world becomes a better place :D";
    }
    
    public void onUpdate() {
        if (MiddleClickFriends.mc.currentScreen != null) {
            return;
        }
        if (!Mouse.isButtonDown(2)) {
            this.clicked = false;
            return;
        }
        if (!this.clicked) {
            this.clicked = true;
            final RayTraceResult result = MiddleClickFriends.mc.objectMouseOver;
            if (result == null || result.typeOfHit != RayTraceResult.Type.ENTITY) {
                return;
            }
            if (!(result.entityHit instanceof EntityPlayer)) {
                return;
            }
            final Entity player = result.entityHit;
            if (FriendUtil.isFriend(player.getName())) {
                final FriendUtil.Friend f = FriendUtil.friends.stream().filter(friend -> friend.getUsername().equalsIgnoreCase(player.getName())).findFirst().get();
                FriendUtil.friends.remove(f);
                MessageUtil.send_client_message("Player " + MiddleClickFriends.red + MiddleClickFriends.bold + player.getName() + MiddleClickFriends.reset + " has been removed as a friend");
            }
            else {
                final FriendUtil.Friend f = FriendUtil.get_friend_object(player.getName());
                FriendUtil.friends.add(f);
                MessageUtil.send_client_message("Player " + MiddleClickFriends.green + MiddleClickFriends.bold + player.getName() + MiddleClickFriends.reset + " is now friended");
            }
        }
    }
    
    static {
        MiddleClickFriends.red = ChatFormatting.RED;
        MiddleClickFriends.green = ChatFormatting.GREEN;
        MiddleClickFriends.bold = ChatFormatting.BOLD;
        MiddleClickFriends.reset = ChatFormatting.RESET;
    }
}
