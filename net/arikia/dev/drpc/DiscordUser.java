//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package net.arikia.dev.drpc;

import com.sun.jna.*;
import java.util.*;

public class DiscordUser extends Structure
{
    public String userId;
    public String username;
    public int discriminator;
    public String avatar;
    
    public List<String> getFieldOrder() {
        return Arrays.asList("userId", "username", "discriminator", "avatar");
    }
}
