//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.squareup.okhttp;

import java.net.*;
import java.io.*;

public interface Authenticator
{
    Request authenticate(final Proxy p0, final Response p1) throws IOException;
    
    Request authenticateProxy(final Proxy p0, final Response p1) throws IOException;
}
