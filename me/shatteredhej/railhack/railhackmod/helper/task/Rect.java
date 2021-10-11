//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.helper.task;

public class Rect
{
    private String tag;
    private int x;
    private int y;
    private int width;
    private int height;
    
    public Rect(final String tag, final int width, final int height) {
        this.tag = tag;
        this.width = width;
        this.height = height;
    }
    
    public void transform(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    public void transform(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public boolean event_mouse(final int mx, final int my) {
        return mx >= this.get_x() && my >= this.get_y() && mx <= this.get_screen_width() && my <= this.get_screen_height();
    }
    
    public String get_tag() {
        return this.tag;
    }
    
    public int get_x() {
        return this.x;
    }
    
    public int get_y() {
        return this.y;
    }
    
    public int get_width() {
        return this.width;
    }
    
    public int get_height() {
        return this.height;
    }
    
    public int get_screen_width() {
        return this.x + this.width;
    }
    
    public int get_screen_height() {
        return this.y + this.height;
    }
}
