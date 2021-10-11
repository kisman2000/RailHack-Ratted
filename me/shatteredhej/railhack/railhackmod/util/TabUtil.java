//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util;

import net.minecraft.client.network.*;
import net.minecraft.scoreboard.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.anti.*;

public class TabUtil
{
    public static String get_player_name(final NetworkPlayerInfo info) {
        final String name = (info.getDisplayName() != null) ? info.getDisplayName().getFormattedText() : ScorePlayerTeam.formatPlayerName((Team)info.getPlayerTeam(), info.getGameProfile().getName());
        if (FriendUtil.isFriend(name)) {
            return section_sign() + "6" + name;
        }
        if (EnemyUtil.isEnemy(name)) {
            return section_sign() + "c" + name;
        }
        return name;
    }
    
    public static String section_sign() {
        return "§";
    }
}
