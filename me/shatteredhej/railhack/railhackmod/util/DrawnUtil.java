//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util;

import java.util.*;

public class DrawnUtil
{
    public static List<String> hidden_tags;
    
    public static void add_remove_item(String s) {
        s = s.toLowerCase();
        if (DrawnUtil.hidden_tags.contains(s)) {
            MessageUtil.send_client_message("Added " + s);
            DrawnUtil.hidden_tags.remove(s);
        }
        else {
            MessageUtil.send_client_message("Removed " + s);
            DrawnUtil.hidden_tags.add(s);
        }
    }
    
    static {
        DrawnUtil.hidden_tags = new ArrayList<String>();
    }
}
