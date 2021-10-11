//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.util;

import java.util.*;

public final class Message
{
    private final String name;
    private final List<Field> fields;
    
    private Message(final String name, final List<Field> fields) {
        this.name = name;
        this.fields = fields;
    }
    
    public String getName() {
        return this.name;
    }
    
    public List<Field> getFields() {
        return this.fields;
    }
    
    public static class Builder
    {
        private final String name;
        private final List<Field> fields;
        
        public Builder(final String name) {
            this.fields = new ArrayList<Field>();
            this.name = name;
        }
        
        public Builder addField(final String name, final String value, final boolean inline) {
            this.fields.add(new Field(name, value, inline));
            return this;
        }
        
        public Message build() {
            return new Message(this.name, this.fields, null);
        }
    }
    
    public static class Field
    {
        private final String name;
        private final String value;
        private final boolean inline;
        
        public Field(final String name, final String value, final boolean inline) {
            this.name = name;
            this.value = value;
            this.inline = inline;
        }
        
        public String getName() {
            return this.name;
        }
        
        public String getValue() {
            return this.value;
        }
        
        public boolean isInline() {
            return this.inline;
        }
    }
}
