//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util.crystal.anti;

import net.minecraft.client.*;
import net.minecraft.client.network.*;
import com.mojang.util.*;
import com.google.gson.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class FriendUtil
{
    public static ArrayList<Friend> friends;
    
    public static boolean isFriend(final String name) {
        return FriendUtil.friends.stream().anyMatch(friend -> friend.username.equalsIgnoreCase(name));
    }
    
    public static Friend get_friend_object(final String name) {
        final ArrayList<NetworkPlayerInfo> infoMap = new ArrayList<NetworkPlayerInfo>(Minecraft.getMinecraft().getConnection().getPlayerInfoMap());
        final NetworkPlayerInfo profile = infoMap.stream().filter(networkPlayerInfo -> networkPlayerInfo.getGameProfile().getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        if (profile == null) {
            final String s = request_ids("[\"" + name + "\"]");
            if (s != null) {
                if (!s.isEmpty()) {
                    final JsonElement element = new JsonParser().parse(s);
                    if (element.getAsJsonArray().size() != 0) {
                        try {
                            final String id = element.getAsJsonArray().get(0).getAsJsonObject().get("id").getAsString();
                            final String username = element.getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString();
                            return new Friend(username, UUIDTypeAdapter.fromString(id));
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return null;
        }
        return new Friend(profile.getGameProfile().getName(), profile.getGameProfile().getId());
    }
    
    private static String request_ids(final String data) {
        try {
            final String query = "https://api.mojang.com/profiles/minecraft";
            final URL url = new URL(query);
            final HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            final OutputStream os = conn.getOutputStream();
            os.write(data.getBytes("UTF-8"));
            os.close();
            final InputStream in = new BufferedInputStream(conn.getInputStream());
            final String res = convertStreamToString(in);
            in.close();
            conn.disconnect();
            return res;
        }
        catch (Exception e) {
            return null;
        }
    }
    
    private static String convertStreamToString(final InputStream is) {
        final Scanner s = new Scanner(is).useDelimiter("\\A");
        final String r = s.hasNext() ? s.next() : "/";
        return r;
    }
    
    static {
        FriendUtil.friends = new ArrayList<Friend>();
    }
    
    public static class Friend
    {
        String username;
        UUID uuid;
        
        public Friend(final String username, final UUID uuid) {
            this.username = username;
            this.uuid = uuid;
        }
        
        public String getUsername() {
            return this.username;
        }
        
        public UUID getUUID() {
            return this.uuid;
        }
    }
}
