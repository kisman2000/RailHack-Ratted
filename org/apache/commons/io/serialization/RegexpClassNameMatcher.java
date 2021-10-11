//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.serialization;

import java.util.regex.*;

final class RegexpClassNameMatcher implements ClassNameMatcher
{
    private final Pattern pattern;
    
    public RegexpClassNameMatcher(final String regex) {
        this(Pattern.compile(regex));
    }
    
    public RegexpClassNameMatcher(final Pattern pattern) {
        if (pattern == null) {
            throw new IllegalArgumentException("Null pattern");
        }
        this.pattern = pattern;
    }
    
    public boolean matches(final String className) {
        return this.pattern.matcher(className).matches();
    }
}
