//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.helper.values;

public class TurokInt
{
    private TurokString name;
    private TurokString tag;
    private int value;
    private int max;
    private int min;
    
    public TurokInt(final TurokString name, final TurokString tag, final int _int, final int min, final int max) {
        this.name = name;
        this.tag = tag;
        this.value = _int;
        this.max = max;
        this.min = min;
    }
    
    public void set_value(final int _int) {
        this.value = _int;
    }
    
    public void set_slider_value(final int _int) {
        if (_int >= this.max) {
            this.value = this.max;
        }
        else if (_int <= this.min) {
            this.value = this.min;
        }
        else {
            this.value = _int;
        }
    }
    
    public TurokString get_name() {
        return this.name;
    }
    
    public TurokString get_tag() {
        return this.tag;
    }
    
    public int get_value() {
        return this.value;
    }
}
