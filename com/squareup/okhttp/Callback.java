//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.squareup.okhttp;

import java.io.*;

public interface Callback
{
    void onFailure(final Request p0, final IOException p1);
    
    void onResponse(final Response p0) throws IOException;
}
