//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util;

import java.util.*;

public class TimeUtil
{
    public static int get_hour() {
        return Calendar.getInstance().get(11);
    }
    
    public static int get_day() {
        return Calendar.getInstance().get(5);
    }
    
    public static int get_month() {
        return Calendar.getInstance().get(2);
    }
    
    public static int get_minuite() {
        return Calendar.getInstance().get(12);
    }
    
    public static int get_second() {
        return Calendar.getInstance().get(13);
    }
}
