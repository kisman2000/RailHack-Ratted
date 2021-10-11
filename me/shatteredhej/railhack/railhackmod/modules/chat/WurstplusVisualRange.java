//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.chat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.entity.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.anti.*;
import com.mojang.realmsclient.gui.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import net.minecraft.entity.player.*;
import java.util.*;

public class WurstplusVisualRange extends Module
{
    private List<String> people;
    
    public WurstplusVisualRange() {
        super(Category.Chat);
        this.name = "VisualRange";
        this.tag = "VisualRange";
        this.description = "bc using ur eyes is overrated";
    }
    
    public void onEnable() {
        this.people = new ArrayList<String>();
    }
    
    public void onUpdate() {
        if (WurstplusVisualRange.mc.world == null | WurstplusVisualRange.mc.player == null) {
            return;
        }
        final List<String> peoplenew = new ArrayList<String>();
        final List<EntityPlayer> playerEntities = (List<EntityPlayer>)WurstplusVisualRange.mc.world.playerEntities;
        for (final Entity e : playerEntities) {
            if (e.getName().equals(WurstplusVisualRange.mc.player.getName())) {
                continue;
            }
            peoplenew.add(e.getName());
        }
        if (peoplenew.size() > 0) {
            for (final String name : peoplenew) {
                if (!this.people.contains(name)) {
                    if (FriendUtil.isFriend(name)) {
                        MessageUtil.send_client_message(ChatFormatting.GREEN + name + ChatFormatting.RESET + " has entered visual range.");
                    }
                    else {
                        MessageUtil.send_client_message(ChatFormatting.RED + name + ChatFormatting.RESET + " has left visual range.");
                    }
                    this.people.add(name);
                }
            }
        }
    }
}
