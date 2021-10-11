//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util;

import java.net.*;
import java.io.*;
import java.util.*;

public class CapeUtil
{
    static final ArrayList<String> final_uuid_list;
    
    public static ArrayList<String> get_uuids() {
        try {
            final URL url = new URL("https://pastebin.com/zHxT337M");
            final BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            final ArrayList<String> uuid_list = new ArrayList<String>();
            String s;
            while ((s = reader.readLine()) != null) {
                uuid_list.add(s);
            }
            return uuid_list;
        }
        catch (Exception ignored) {
            return null;
        }
    }
    
    public static boolean is_uuid_valid(final UUID uuid) {
        for (final String u : Objects.requireNonNull(CapeUtil.final_uuid_list)) {
            if (u.equals(uuid.toString())) {
                return true;
            }
        }
        return false;
    }
    
    static {
        final_uuid_list = get_uuids();
    }
}
