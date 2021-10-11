//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.chat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;

public class AutoSuicide extends Module
{
    Setting sendChatMessage;
    
    public AutoSuicide() {
        super(Category.Chat);
        this.sendChatMessage = this.register("ChatMessage", "ChatMessage", false);
        this.name = "AutoSuicide";
        this.tag = "AutoSuicide";
        this.description = "Automatic suicidinqq";
    }
    
    public void onEnable() {
        if (this.sendChatMessage.getValue(true)) {
            AutoSuicide.mc.player.sendChatMessage("I just toggled AutoSuicide in Root Client!");
        }
        AutoSuicide.mc.player.sendChatMessage("/kill");
        this.setDisable();
    }
}
