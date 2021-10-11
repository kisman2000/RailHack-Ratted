//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.helper.values;

public class TurokString
{
    private String name;
    private String tag;
    private String value;
    
    public TurokString(final String name, final String tag, final String string) {
        this.name = name;
        this.tag = tag;
        this.value = string;
    }
    
    public void setValue(final String string) {
        this.value = string;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getTag() {
        return this.tag;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public static String toString(final String value) {
        return value;
    }
    
    public static String toString(final boolean value) {
        return Boolean.toString(value);
    }
    
    public static String toString(final double value) {
        return Double.toString(value);
    }
    
    public static String toString(final float value) {
        return Float.toString(value);
    }
    
    public static String toString(final int value) {
        return Integer.toString(value);
    }
}
