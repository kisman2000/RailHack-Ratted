//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.squareup.okhttp;

import java.net.*;
import java.util.*;

public interface Dns
{
    public static final Dns SYSTEM = new Dns() {
        @Override
        public List<InetAddress> lookup(final String hostname) throws UnknownHostException {
            if (hostname == null) {
                throw new UnknownHostException("hostname == null");
            }
            return Arrays.asList(InetAddress.getAllByName(hostname));
        }
    };
    
    List<InetAddress> lookup(final String p0) throws UnknownHostException;
}
