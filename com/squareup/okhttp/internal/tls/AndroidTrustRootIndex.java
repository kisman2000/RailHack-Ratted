//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.squareup.okhttp.internal.tls;

import javax.net.ssl.*;
import java.security.cert.*;
import java.lang.reflect.*;

public final class AndroidTrustRootIndex implements TrustRootIndex
{
    private final X509TrustManager trustManager;
    private final Method findByIssuerAndSignatureMethod;
    
    public AndroidTrustRootIndex(final X509TrustManager trustManager, final Method findByIssuerAndSignatureMethod) {
        this.findByIssuerAndSignatureMethod = findByIssuerAndSignatureMethod;
        this.trustManager = trustManager;
    }
    
    @Override
    public X509Certificate findByIssuerAndSignature(final X509Certificate cert) {
        try {
            final TrustAnchor trustAnchor = (TrustAnchor)this.findByIssuerAndSignatureMethod.invoke(this.trustManager, cert);
            return (trustAnchor != null) ? trustAnchor.getTrustedCert() : null;
        }
        catch (IllegalAccessException e) {
            throw new AssertionError();
        }
        catch (InvocationTargetException e2) {
            return null;
        }
    }
    
    public static TrustRootIndex get(final X509TrustManager trustManager) {
        try {
            final Method method = trustManager.getClass().getDeclaredMethod("findTrustAnchorByIssuerAndSignature", X509Certificate.class);
            method.setAccessible(true);
            return new AndroidTrustRootIndex(trustManager, method);
        }
        catch (NoSuchMethodException e) {
            return null;
        }
    }
}
