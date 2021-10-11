//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.lang.enums;

import java.util.*;

public class EnumUtils
{
    public static Enum getEnum(final Class enumClass, final String name) {
        return Enum.getEnum(enumClass, name);
    }
    
    public static ValuedEnum getEnum(final Class enumClass, final int value) {
        return (ValuedEnum)ValuedEnum.getEnum(enumClass, value);
    }
    
    public static Map getEnumMap(final Class enumClass) {
        return Enum.getEnumMap(enumClass);
    }
    
    public static List getEnumList(final Class enumClass) {
        return Enum.getEnumList(enumClass);
    }
    
    public static Iterator iterator(final Class enumClass) {
        return Enum.getEnumList(enumClass).iterator();
    }
}
