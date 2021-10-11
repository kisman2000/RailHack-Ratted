//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.misc;

import me.shatteredhej.railhack.railhackmod.module.*;
import net.minecraft.entity.passive.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.entity.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import com.google.gson.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class MobOwner extends Module
{
    private List<AbstractHorse> mobs;
    private Map<String, String> uuidToName;
    
    public MobOwner() {
        super(Category.Misc);
        this.name = "MobOwner";
        this.tag = "MobOwner";
        this.description = "who owns the mob";
        this.mobs = new ArrayList<AbstractHorse>();
        this.uuidToName = new HashMap<String, String>();
    }
    
    public void onUpdate() {
        if (MobOwner.mc.world == null) {
            return;
        }
        for (final Entity e : MobOwner.mc.world.loadedEntityList) {
            if (!(e instanceof AbstractHorse)) {
                continue;
            }
            final AbstractHorse horse = (AbstractHorse)e;
            if (this.mobs.contains(horse)) {
                continue;
            }
            this.mobs.add(horse);
            final UUID uuid = horse.getOwnerUniqueId();
            if (uuid == null) {
                horse.setCustomNameTag("Not tamed");
            }
            else {
                final String uuidString = uuid.toString().replace("-", "");
                String name = "";
                if (this.uuidToName.get(name) != null) {
                    name = this.uuidToName.get(name);
                }
                else {
                    try {
                        final String s = requestName(uuidString);
                        final JsonElement element = new JsonParser().parse(s);
                        final JsonArray array = element.getAsJsonArray();
                        if (array.size() == 0) {
                            MessageUtil.send_client_message("Couldn't find player name. (1)");
                            continue;
                        }
                        name = array.get(array.size() - 1).getAsJsonObject().get("name").getAsString();
                        this.uuidToName.put(uuidString, name);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        MessageUtil.send_client_message("Couldn't find player name. (2)");
                        continue;
                    }
                }
                horse.setCustomNameTag("Owner: " + name);
            }
        }
    }
    
    private static String requestName(final String uuid) {
        try {
            final String query = "https://api.mojang.com/user/profiles/" + uuid + "/names";
            final URL url = new URL(query);
            final HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            final InputStream in = new BufferedInputStream(conn.getInputStream());
            final String res = convertStreamToString(in);
            in.close();
            conn.disconnect();
            return res;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private static String convertStreamToString(final InputStream is) {
        final Scanner s = new Scanner(is).useDelimiter("\\A");
        final String r = s.hasNext() ? s.next() : "/";
        return r;
    }
}
