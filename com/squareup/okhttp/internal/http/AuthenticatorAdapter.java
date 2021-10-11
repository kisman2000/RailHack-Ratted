//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.squareup.okhttp.internal.http;

import java.util.*;
import com.squareup.okhttp.*;
import java.io.*;
import java.net.*;

public final class AuthenticatorAdapter implements Authenticator
{
    public static final Authenticator INSTANCE;
    
    public Request authenticate(final Proxy proxy, final Response response) throws IOException {
        final List<Challenge> challenges = response.challenges();
        final Request request = response.request();
        final HttpUrl url = request.httpUrl();
        for (int i = 0, size = challenges.size(); i < size; ++i) {
            final Challenge challenge = challenges.get(i);
            if ("Basic".equalsIgnoreCase(challenge.getScheme())) {
                final PasswordAuthentication auth = java.net.Authenticator.requestPasswordAuthentication(url.host(), this.getConnectToInetAddress(proxy, url), url.port(), url.scheme(), challenge.getRealm(), challenge.getScheme(), url.url(), java.net.Authenticator.RequestorType.SERVER);
                if (auth != null) {
                    final String credential = Credentials.basic(auth.getUserName(), new String(auth.getPassword()));
                    return request.newBuilder().header("Authorization", credential).build();
                }
            }
        }
        return null;
    }
    
    public Request authenticateProxy(final Proxy proxy, final Response response) throws IOException {
        final List<Challenge> challenges = response.challenges();
        final Request request = response.request();
        final HttpUrl url = request.httpUrl();
        for (int i = 0, size = challenges.size(); i < size; ++i) {
            final Challenge challenge = challenges.get(i);
            if ("Basic".equalsIgnoreCase(challenge.getScheme())) {
                final InetSocketAddress proxyAddress = (InetSocketAddress)proxy.address();
                final PasswordAuthentication auth = java.net.Authenticator.requestPasswordAuthentication(proxyAddress.getHostName(), this.getConnectToInetAddress(proxy, url), proxyAddress.getPort(), url.scheme(), challenge.getRealm(), challenge.getScheme(), url.url(), java.net.Authenticator.RequestorType.PROXY);
                if (auth != null) {
                    final String credential = Credentials.basic(auth.getUserName(), new String(auth.getPassword()));
                    return request.newBuilder().header("Proxy-Authorization", credential).build();
                }
            }
        }
        return null;
    }
    
    private InetAddress getConnectToInetAddress(final Proxy proxy, final HttpUrl url) throws IOException {
        return (proxy != null && proxy.type() != Proxy.Type.DIRECT) ? ((InetSocketAddress)proxy.address()).getAddress() : InetAddress.getByName(url.host());
    }
    
    static {
        INSTANCE = (Authenticator)new AuthenticatorAdapter();
    }
}
