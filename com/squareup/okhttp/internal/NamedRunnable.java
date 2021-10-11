//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.squareup.okhttp.internal;

public abstract class NamedRunnable implements Runnable
{
    protected final String name;
    
    public NamedRunnable(final String format, final Object... args) {
        this.name = String.format(format, args);
    }
    
    @Override
    public final void run() {
        final String oldName = Thread.currentThread().getName();
        Thread.currentThread().setName(this.name);
        try {
            this.execute();
        }
        finally {
            Thread.currentThread().setName(oldName);
        }
    }
    
    protected abstract void execute();
}
