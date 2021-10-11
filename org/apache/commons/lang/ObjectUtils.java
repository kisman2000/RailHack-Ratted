//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.lang;

import org.apache.commons.lang.reflect.*;
import org.apache.commons.lang.exception.*;
import java.lang.reflect.*;
import java.io.*;

public class ObjectUtils
{
    public static final Null NULL;
    
    public static Object defaultIfNull(final Object object, final Object defaultValue) {
        return (object != null) ? object : defaultValue;
    }
    
    public static boolean equals(final Object object1, final Object object2) {
        return object1 == object2 || (object1 != null && object2 != null && object1.equals(object2));
    }
    
    public static boolean notEqual(final Object object1, final Object object2) {
        return !equals(object1, object2);
    }
    
    public static int hashCode(final Object obj) {
        return (obj == null) ? 0 : obj.hashCode();
    }
    
    public static String identityToString(final Object object) {
        if (object == null) {
            return null;
        }
        final StringBuffer buffer = new StringBuffer();
        identityToString(buffer, object);
        return buffer.toString();
    }
    
    public static void identityToString(final StringBuffer buffer, final Object object) {
        if (object == null) {
            throw new NullPointerException("Cannot get the toString of a null identity");
        }
        buffer.append(object.getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(object)));
    }
    
    public static StringBuffer appendIdentityToString(StringBuffer buffer, final Object object) {
        if (object == null) {
            return null;
        }
        if (buffer == null) {
            buffer = new StringBuffer();
        }
        return buffer.append(object.getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(object)));
    }
    
    public static String toString(final Object obj) {
        return (obj == null) ? "" : obj.toString();
    }
    
    public static String toString(final Object obj, final String nullStr) {
        return (obj == null) ? nullStr : obj.toString();
    }
    
    public static Object min(final Comparable c1, final Comparable c2) {
        return (compare(c1, c2, true) <= 0) ? c1 : c2;
    }
    
    public static Object max(final Comparable c1, final Comparable c2) {
        return (compare(c1, c2, false) >= 0) ? c1 : c2;
    }
    
    public static int compare(final Comparable c1, final Comparable c2) {
        return compare(c1, c2, false);
    }
    
    public static int compare(final Comparable c1, final Comparable c2, final boolean nullGreater) {
        if (c1 == c2) {
            return 0;
        }
        if (c1 == null) {
            return nullGreater ? 1 : -1;
        }
        if (c2 == null) {
            return nullGreater ? -1 : 1;
        }
        return c1.compareTo(c2);
    }
    
    public static Object clone(final Object o) {
        if (o instanceof Cloneable) {
            Object result;
            if (o.getClass().isArray()) {
                final Class componentType = o.getClass().getComponentType();
                if (!componentType.isPrimitive()) {
                    result = ((Object[])o).clone();
                }
                else {
                    int length = Array.getLength(o);
                    result = Array.newInstance(componentType, length);
                    while (length-- > 0) {
                        Array.set(result, length, Array.get(o, length));
                    }
                }
            }
            else {
                try {
                    result = MethodUtils.invokeMethod(o, "clone", null);
                }
                catch (NoSuchMethodException e) {
                    throw new CloneFailedException("Cloneable type " + o.getClass().getName() + " has no clone method", (Throwable)e);
                }
                catch (IllegalAccessException e2) {
                    throw new CloneFailedException("Cannot clone Cloneable type " + o.getClass().getName(), (Throwable)e2);
                }
                catch (InvocationTargetException e3) {
                    throw new CloneFailedException("Exception cloning Cloneable type " + o.getClass().getName(), e3.getTargetException());
                }
            }
            return result;
        }
        return null;
    }
    
    public static Object cloneIfPossible(final Object o) {
        final Object clone = clone(o);
        return (clone == null) ? o : clone;
    }
    
    static {
        NULL = new Null();
    }
    
    public static class Null implements Serializable
    {
        private static final long serialVersionUID = 7092611880189329093L;
        
        Null() {
        }
        
        private Object readResolve() {
            return ObjectUtils.NULL;
        }
    }
}
