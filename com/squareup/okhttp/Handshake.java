//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.squareup.okhttp;

import javax.net.ssl.*;
import com.squareup.okhttp.internal.*;
import java.util.*;
import java.security.*;
import java.security.cert.*;

public final class Handshake
{
    private final String cipherSuite;
    private final List<Certificate> peerCertificates;
    private final List<Certificate> localCertificates;
    
    private Handshake(final String cipherSuite, final List<Certificate> peerCertificates, final List<Certificate> localCertificates) {
        this.cipherSuite = cipherSuite;
        this.peerCertificates = peerCertificates;
        this.localCertificates = localCertificates;
    }
    
    public static Handshake get(final SSLSession session) {
        final String cipherSuite = session.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        Certificate[] peerCertificates;
        try {
            peerCertificates = session.getPeerCertificates();
        }
        catch (SSLPeerUnverifiedException ignored) {
            peerCertificates = null;
        }
        final List<Certificate> peerCertificatesList = (peerCertificates != null) ? Util.immutableList(peerCertificates) : Collections.emptyList();
        final Certificate[] localCertificates = session.getLocalCertificates();
        final List<Certificate> localCertificatesList = (localCertificates != null) ? Util.immutableList(localCertificates) : Collections.emptyList();
        return new Handshake(cipherSuite, peerCertificatesList, localCertificatesList);
    }
    
    public static Handshake get(final String cipherSuite, final List<Certificate> peerCertificates, final List<Certificate> localCertificates) {
        if (cipherSuite == null) {
            throw new IllegalArgumentException("cipherSuite == null");
        }
        return new Handshake(cipherSuite, Util.immutableList(peerCertificates), Util.immutableList(localCertificates));
    }
    
    public String cipherSuite() {
        return this.cipherSuite;
    }
    
    public List<Certificate> peerCertificates() {
        return this.peerCertificates;
    }
    
    public Principal peerPrincipal() {
        return this.peerCertificates.isEmpty() ? null : this.peerCertificates.get(0).getSubjectX500Principal();
    }
    
    public List<Certificate> localCertificates() {
        return this.localCertificates;
    }
    
    public Principal localPrincipal() {
        return this.localCertificates.isEmpty() ? null : this.localCertificates.get(0).getSubjectX500Principal();
    }
    
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof Handshake)) {
            return false;
        }
        final Handshake that = (Handshake)other;
        return this.cipherSuite.equals(that.cipherSuite) && this.peerCertificates.equals(that.peerCertificates) && this.localCertificates.equals(that.localCertificates);
    }
    
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + this.cipherSuite.hashCode();
        result = 31 * result + this.peerCertificates.hashCode();
        result = 31 * result + this.localCertificates.hashCode();
        return result;
    }
}
