//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.filefilter;

import java.io.*;
import java.util.*;

public class AndFileFilter extends AbstractFileFilter implements ConditionalFileFilter, Serializable
{
    private static final long serialVersionUID = 7215974688563965257L;
    private final List<IOFileFilter> fileFilters;
    
    public AndFileFilter() {
        this.fileFilters = new ArrayList<IOFileFilter>();
    }
    
    public AndFileFilter(final List<IOFileFilter> fileFilters) {
        if (fileFilters == null) {
            this.fileFilters = new ArrayList<IOFileFilter>();
        }
        else {
            this.fileFilters = new ArrayList<IOFileFilter>(fileFilters);
        }
    }
    
    public AndFileFilter(final IOFileFilter filter1, final IOFileFilter filter2) {
        if (filter1 == null || filter2 == null) {
            throw new IllegalArgumentException("The filters must not be null");
        }
        this.fileFilters = new ArrayList<IOFileFilter>(2);
        this.addFileFilter(filter1);
        this.addFileFilter(filter2);
    }
    
    public void addFileFilter(final IOFileFilter ioFileFilter) {
        this.fileFilters.add(ioFileFilter);
    }
    
    public List<IOFileFilter> getFileFilters() {
        return Collections.unmodifiableList((List<? extends IOFileFilter>)this.fileFilters);
    }
    
    public boolean removeFileFilter(final IOFileFilter ioFileFilter) {
        return this.fileFilters.remove(ioFileFilter);
    }
    
    public void setFileFilters(final List<IOFileFilter> fileFilters) {
        this.fileFilters.clear();
        this.fileFilters.addAll(fileFilters);
    }
    
    public boolean accept(final File file) {
        if (this.fileFilters.isEmpty()) {
            return false;
        }
        for (final IOFileFilter fileFilter : this.fileFilters) {
            if (!fileFilter.accept(file)) {
                return false;
            }
        }
        return true;
    }
    
    public boolean accept(final File file, final String name) {
        if (this.fileFilters.isEmpty()) {
            return false;
        }
        for (final IOFileFilter fileFilter : this.fileFilters) {
            if (!fileFilter.accept(file, name)) {
                return false;
            }
        }
        return true;
    }
    
    public String toString() {
        final StringBuilder buffer = new StringBuilder();
        buffer.append(super.toString());
        buffer.append("(");
        if (this.fileFilters != null) {
            for (int i = 0; i < this.fileFilters.size(); ++i) {
                if (i > 0) {
                    buffer.append(",");
                }
                final Object filter = this.fileFilters.get(i);
                buffer.append((filter == null) ? "null" : filter.toString());
            }
        }
        buffer.append(")");
        return buffer.toString();
    }
}
