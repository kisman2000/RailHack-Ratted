//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.helper.values;

public class TurokBoolean
{
    private TurokString name;
    private TurokString tag;
    private TurokGeneric<Boolean> value;
    
    public TurokBoolean(final TurokString name, final TurokString tag, final boolean _bool) {
        this.name = name;
        this.tag = tag;
        this.value = new TurokGeneric<Boolean>(_bool);
    }
    
    public void set_value(final boolean _bool) {
        this.value.set_value(_bool);
    }
    
    public TurokString get_name() {
        return this.name;
    }
    
    public TurokString get_tag() {
        return this.tag;
    }
    
    public boolean get_value() {
        return this.value.get_value();
    }
}
