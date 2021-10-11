//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.squareup.okhttp.internal.tls;

import javax.security.auth.x500.*;
import java.security.cert.*;
import java.util.*;
import java.security.*;

public final class RealTrustRootIndex implements TrustRootIndex
{
    private final Map<X500Principal, List<X509Certificate>> subjectToCaCerts;
    
    public RealTrustRootIndex(final X509Certificate... caCerts) {
        this.subjectToCaCerts = new LinkedHashMap<X500Principal, List<X509Certificate>>();
        for (final X509Certificate caCert : caCerts) {
            final X500Principal subject = caCert.getSubjectX500Principal();
            List<X509Certificate> subjectCaCerts = this.subjectToCaCerts.get(subject);
            if (subjectCaCerts == null) {
                subjectCaCerts = new ArrayList<X509Certificate>(1);
                this.subjectToCaCerts.put(subject, subjectCaCerts);
            }
            subjectCaCerts.add(caCert);
        }
    }
    
    @Override
    public X509Certificate findByIssuerAndSignature(final X509Certificate cert) {
        final X500Principal issuer = cert.getIssuerX500Principal();
        final List<X509Certificate> subjectCaCerts = this.subjectToCaCerts.get(issuer);
        if (subjectCaCerts == null) {
            return null;
        }
        for (final X509Certificate caCert : subjectCaCerts) {
            final PublicKey publicKey = caCert.getPublicKey();
            try {
                cert.verify(publicKey);
                return caCert;
            }
            catch (Exception ex) {
                continue;
            }
            break;
        }
        return null;
    }
}
