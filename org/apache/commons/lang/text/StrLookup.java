//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.lang.text;

import java.util.*;

public abstract class StrLookup
{
    private static final StrLookup NONE_LOOKUP;
    private static final StrLookup SYSTEM_PROPERTIES_LOOKUP;
    
    public static StrLookup noneLookup() {
        return StrLookup.NONE_LOOKUP;
    }
    
    public static StrLookup systemPropertiesLookup() {
        return StrLookup.SYSTEM_PROPERTIES_LOOKUP;
    }
    
    public static StrLookup mapLookup(final Map map) {
        return new MapStrLookup(map);
    }
    
    protected StrLookup() {
    }
    
    public abstract String lookup(final String p0);
    
    static {
        NONE_LOOKUP = new MapStrLookup(null);
        StrLookup lookup = null;
        try {
            lookup = new MapStrLookup(System.getProperties());
        }
        catch (SecurityException ex) {
            lookup = StrLookup.NONE_LOOKUP;
        }
        SYSTEM_PROPERTIES_LOOKUP = lookup;
    }
    
    static class MapStrLookup extends StrLookup
    {
        private final Map map;
        
        MapStrLookup(final Map map) {
            this.map = map;
        }
        
        public String lookup(final String key) {
            if (this.map == null) {
                return null;
            }
            final Object obj = this.map.get(key);
            if (obj == null) {
                return null;
            }
            return obj.toString();
        }
    }
}
