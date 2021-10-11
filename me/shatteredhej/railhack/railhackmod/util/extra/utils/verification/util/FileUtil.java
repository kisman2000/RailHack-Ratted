//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.util;

import java.io.*;
import java.nio.file.*;
import java.util.function.*;
import java.util.stream.*;
import java.util.*;

public final class FileUtil
{
    public static List<File> getFiles(final String dir) {
        try (final Stream<Path> paths = Files.walk(Paths.get(dir, new String[0]), new FileVisitOption[0])) {
            return (List<File>)paths.filter(x$0 -> Files.isRegularFile(x$0, new LinkOption[0])).map((Function<? super Path, ?>)Path::toFile).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList());
        }
        catch (Exception ex) {
            return new ArrayList<File>();
        }
    }
    
    public static Optional<File> getFile(final String name) {
        return Optional.of(new File(name));
    }
    
    public static String randomString() {
        final String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvxyz";
        final StringBuilder sb = new StringBuilder(20);
        for (int i = 0; i < 20; ++i) {
            final int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
