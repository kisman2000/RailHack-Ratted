//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.lang.mutable;

import java.io.*;
import org.apache.commons.lang.*;

public class MutableBoolean implements Mutable, Serializable, Comparable
{
    private static final long serialVersionUID = -4830728138360036487L;
    private boolean value;
    
    public MutableBoolean() {
    }
    
    public MutableBoolean(final boolean value) {
        this.value = value;
    }
    
    public MutableBoolean(final Boolean value) {
        this.value = value;
    }
    
    public Object getValue() {
        return BooleanUtils.toBooleanObject(this.value);
    }
    
    public void setValue(final boolean value) {
        this.value = value;
    }
    
    public void setValue(final Object value) {
        this.setValue((boolean)value);
    }
    
    public boolean isTrue() {
        return this.value;
    }
    
    public boolean isFalse() {
        return !this.value;
    }
    
    public boolean booleanValue() {
        return this.value;
    }
    
    public Boolean toBoolean() {
        return BooleanUtils.toBooleanObject(this.value);
    }
    
    public boolean equals(final Object obj) {
        return obj instanceof MutableBoolean && this.value == ((MutableBoolean)obj).booleanValue();
    }
    
    public int hashCode() {
        return this.value ? Boolean.TRUE.hashCode() : Boolean.FALSE.hashCode();
    }
    
    public int compareTo(final Object obj) {
        final MutableBoolean other = (MutableBoolean)obj;
        final boolean anotherVal = other.value;
        return (this.value == anotherVal) ? 0 : (this.value ? 1 : -1);
    }
    
    public String toString() {
        return String.valueOf(this.value);
    }
}
