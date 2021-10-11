//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.chat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import me.shatteredhej.railhack.railhackmod.util.*;

public class AutoQueueMain extends Module
{
    Setting message;
    Setting delay;
    private int wait;
    
    public AutoQueueMain() {
        super(Category.Chat);
        this.message = this.register("Message", "Message", false);
        this.delay = this.register("Delay", "Delay", 120, 10, 600);
        this.name = "AutoQueueMain";
        this.tag = "AutoQueueMain";
        this.description = "types queue main in chat for 2b";
    }
    
    public void onEnable() {
        this.wait = 12000;
    }
    
    public void onUpdate() {
        if (AutoQueueMain.mc.getCurrentServerData() == null || (AutoQueueMain.mc.getCurrentServerData() != null && !AutoQueueMain.mc.getCurrentServerData().serverIP.equals("2b2t.org"))) {
            this.wait = 0;
            MessageUtil.send_client_message("Not connected to 2b2t, disabling.");
            this.setDisable();
        }
        else {
            if (this.wait <= 0) {
                AutoQueueMain.mc.player.sendChatMessage("/queue main");
                if (this.message.getValue(true)) {
                    MessageUtil.send_client_message("Typed /queue main.");
                }
                this.wait = this.delay.getValue(1);
            }
            --this.wait;
        }
    }
}
