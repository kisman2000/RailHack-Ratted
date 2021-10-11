//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.serialization;

import org.apache.commons.io.*;

final class WildcardClassNameMatcher implements ClassNameMatcher
{
    private final String pattern;
    
    public WildcardClassNameMatcher(final String pattern) {
        this.pattern = pattern;
    }
    
    public boolean matches(final String className) {
        return FilenameUtils.wildcardMatch(className, this.pattern);
    }
}
