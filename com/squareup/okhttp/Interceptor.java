//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.squareup.okhttp;

import java.io.*;

public interface Interceptor
{
    Response intercept(final Chain p0) throws IOException;
    
    public interface Chain
    {
        Request request();
        
        Response proceed(final Request p0) throws IOException;
        
        Connection connection();
    }
}
