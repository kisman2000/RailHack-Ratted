//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.serialization;

import java.util.*;

final class FullClassNameMatcher implements ClassNameMatcher
{
    private final Set<String> classesSet;
    
    public FullClassNameMatcher(final String... classes) {
        this.classesSet = Collections.unmodifiableSet((Set<? extends String>)new HashSet<String>(Arrays.asList(classes)));
    }
    
    public boolean matches(final String className) {
        return this.classesSet.contains(className);
    }
}
