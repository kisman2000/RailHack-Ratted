//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.lang.mutable;

public class MutableInt extends Number implements Comparable, Mutable
{
    private static final long serialVersionUID = 512176391864L;
    private int value;
    
    public MutableInt() {
    }
    
    public MutableInt(final int value) {
        this.value = value;
    }
    
    public MutableInt(final Number value) {
        this.value = value.intValue();
    }
    
    public MutableInt(final String value) throws NumberFormatException {
        this.value = Integer.parseInt(value);
    }
    
    public Object getValue() {
        return new Integer(this.value);
    }
    
    public void setValue(final int value) {
        this.value = value;
    }
    
    public void setValue(final Object value) {
        this.setValue(((Number)value).intValue());
    }
    
    public void increment() {
        ++this.value;
    }
    
    public void decrement() {
        --this.value;
    }
    
    public void add(final int operand) {
        this.value += operand;
    }
    
    public void add(final Number operand) {
        this.value += operand.intValue();
    }
    
    public void subtract(final int operand) {
        this.value -= operand;
    }
    
    public void subtract(final Number operand) {
        this.value -= operand.intValue();
    }
    
    public int intValue() {
        return this.value;
    }
    
    public long longValue() {
        return this.value;
    }
    
    public float floatValue() {
        return (float)this.value;
    }
    
    public double doubleValue() {
        return this.value;
    }
    
    public Integer toInteger() {
        return new Integer(this.intValue());
    }
    
    public boolean equals(final Object obj) {
        return obj instanceof MutableInt && this.value == ((MutableInt)obj).intValue();
    }
    
    public int hashCode() {
        return this.value;
    }
    
    public int compareTo(final Object obj) {
        final MutableInt other = (MutableInt)obj;
        final int anotherVal = other.value;
        return (this.value < anotherVal) ? -1 : ((this.value == anotherVal) ? 0 : 1);
    }
    
    public String toString() {
        return String.valueOf(this.value);
    }
}
