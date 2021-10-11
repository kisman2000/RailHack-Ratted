//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util;

import net.minecraft.entity.*;
import net.minecraft.client.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.anti.*;
import java.util.stream.*;
import net.minecraft.entity.player.*;
import java.util.*;

public class OnlineFriends
{
    public static List<Entity> entities;
    
    public static List<Entity> getFriends() {
        OnlineFriends.entities.clear();
        OnlineFriends.entities.addAll((Collection<? extends Entity>)Minecraft.getMinecraft().world.playerEntities.stream().filter(entityPlayer -> FriendUtil.isFriend(entityPlayer.getName())).collect(Collectors.toList()));
        return OnlineFriends.entities;
    }
    
    static {
        OnlineFriends.entities = new ArrayList<Entity>();
    }
}
