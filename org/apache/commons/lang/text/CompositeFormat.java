//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.lang.text;

import java.text.*;

public class CompositeFormat extends Format
{
    private static final long serialVersionUID = -4329119827877627683L;
    private final Format parser;
    private final Format formatter;
    
    public CompositeFormat(final Format parser, final Format formatter) {
        this.parser = parser;
        this.formatter = formatter;
    }
    
    public StringBuffer format(final Object obj, final StringBuffer toAppendTo, final FieldPosition pos) {
        return this.formatter.format(obj, toAppendTo, pos);
    }
    
    public Object parseObject(final String source, final ParsePosition pos) {
        return this.parser.parseObject(source, pos);
    }
    
    public Format getParser() {
        return this.parser;
    }
    
    public Format getFormatter() {
        return this.formatter;
    }
    
    public String reformat(final String input) throws ParseException {
        return this.format(this.parseObject(input));
    }
}
