//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.helper.values;

public class TurokFloat
{
    private TurokString name;
    private TurokString tag;
    private float value;
    private float max;
    private float min;
    
    public TurokFloat(final TurokString name, final TurokString tag, final float _float, final float min, final float max) {
        this.name = name;
        this.tag = tag;
        this.value = this.value;
        this.max = max;
        this.min = min;
    }
    
    public void set_value(final float _float) {
        this.value = _float;
    }
    
    public void set_slider_value(final float _float) {
        if (_float >= this.max) {
            this.value = this.max;
        }
        else if (_float <= this.min) {
            this.value = this.min;
        }
        else {
            this.value = _float;
        }
    }
    
    public TurokString get_name() {
        return this.name;
    }
    
    public TurokString get_tag() {
        return this.tag;
    }
    
    public float get_value() {
        return this.value;
    }
}
