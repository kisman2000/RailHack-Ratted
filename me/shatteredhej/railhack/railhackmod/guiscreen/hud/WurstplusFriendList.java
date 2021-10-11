//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.guiscreen.hud;

import me.shatteredhej.railhack.railhackmod.guiscreen.render.pinnables.*;
import com.mojang.realmsclient.gui.*;
import me.shatteredhej.railhack.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import net.minecraft.entity.*;
import java.util.*;

public class WurstplusFriendList extends WurstplusPinnable
{
    int passes;
    public static ChatFormatting bold;
    
    public WurstplusFriendList() {
        super("Friends", "Friends", 1.0f, 0, 0);
    }
    
    @Override
    public void render() {
        final int nl_r = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorR").getValue(1);
        final int nl_g = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorG").getValue(1);
        final int nl_b = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorB").getValue(1);
        final int nl_a = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorA").getValue(1);
        final String line1 = WurstplusFriendList.bold + "the_fellas: ";
        this.passes = 0;
        this.create_line(line1, this.docking(1, line1), 2, nl_r, nl_g, nl_b, nl_a);
        if (!OnlineFriends.getFriends().isEmpty()) {
            for (final Entity e : OnlineFriends.getFriends()) {
                ++this.passes;
                this.create_line(e.getName(), this.docking(1, e.getName()), this.get(line1, "height") * this.passes, nl_r, nl_g, nl_b, nl_a);
            }
        }
        this.set_width(this.get(line1, "width") + 2);
        this.set_height(this.get(line1, "height") + 2);
    }
    
    static {
        WurstplusFriendList.bold = ChatFormatting.BOLD;
    }
}
