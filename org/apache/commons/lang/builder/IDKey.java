//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.lang.builder;

final class IDKey
{
    private final Object value;
    private final int id;
    
    public IDKey(final Object _value) {
        this.id = System.identityHashCode(_value);
        this.value = _value;
    }
    
    public int hashCode() {
        return this.id;
    }
    
    public boolean equals(final Object other) {
        if (!(other instanceof IDKey)) {
            return false;
        }
        final IDKey idKey = (IDKey)other;
        return this.id == idKey.id && this.value == idKey.value;
    }
}
