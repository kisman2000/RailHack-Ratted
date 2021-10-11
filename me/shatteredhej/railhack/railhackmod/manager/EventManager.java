//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.manager;

import net.minecraft.client.*;
import net.minecraftforge.event.entity.living.*;
import me.shatteredhej.railhack.*;
import me.shatteredhej.railhack.railhackmod.event.*;
import net.minecraft.client.gui.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import net.minecraft.entity.passive.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import me.shatteredhej.railhack.railhackmod.helper.draw.*;
import net.minecraftforge.fml.common.gameevent.*;
import org.lwjgl.input.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.shatteredhej.railhack.railhackmod.command.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import java.util.*;
import net.minecraftforge.client.event.*;

public class EventManager
{
    private final Minecraft mc;
    
    public EventManager() {
        this.mc = Minecraft.getMinecraft();
    }
    
    @SubscribeEvent
    public void onUpdate(final LivingEvent.LivingUpdateEvent event) {
        if (event.isCanceled()) {
            return;
        }
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (this.mc.player == null) {
            return;
        }
        RailHack.get_hack_manager().onUpdate();
    }
    
    @SubscribeEvent
    public void onWorldRender(final RenderWorldLastEvent event) {
        if (event.isCanceled()) {
            return;
        }
        RailHack.get_hack_manager().render(event);
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Post event) {
        if (event.isCanceled()) {
            return;
        }
        Event.EVENT_BUS.post(new EventGameOverlay(event.getPartialTicks(), new ScaledResolution(this.mc)));
        RenderGameOverlayEvent.ElementType target = RenderGameOverlayEvent.ElementType.EXPERIENCE;
        if (!this.mc.player.isCreative() && this.mc.player.getRidingEntity() instanceof AbstractHorse) {
            target = RenderGameOverlayEvent.ElementType.HEALTHMOUNT;
        }
        if (event.getType() == target) {
            RailHack.get_hack_manager().render();
            if (!RailHack.get_hack_manager().getModuleWithTag("GUI").isActive()) {
                RailHack.get_hud_manager().render();
            }
            GL11.glPushMatrix();
            GL11.glEnable(3553);
            GL11.glEnable(3042);
            GlStateManager.enableBlend();
            GL11.glPopMatrix();
            RenderHelp.release_gl();
        }
    }
    
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyInput(final InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            RailHack.get_hack_manager().bind(Keyboard.getEventKey());
        }
    }
    
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onChat(final ClientChatEvent event) {
        final String message = event.getMessage();
        final String[] message_args = CommandManager.commandList.getMessage(event.getMessage());
        boolean true_command = false;
        if (message_args.length > 0) {
            event.setCanceled(true);
            this.mc.ingameGUI.getChatGUI().addToSentMessages(event.getMessage());
            for (final Command command : Commands.getPureCommandList()) {
                try {
                    if (!CommandManager.commandList.getMessage(event.getMessage())[0].equalsIgnoreCase(command.getName())) {
                        continue;
                    }
                    true_command = command.getMessage(CommandManager.commandList.getMessage(event.getMessage()));
                }
                catch (Exception ex) {}
            }
            if (!true_command && CommandManager.commandList.hasPrefix(event.getMessage())) {
                MessageUtil.send_client_message("Try using " + CommandManager.getPrefix() + "help list to see all commands");
                true_command = false;
            }
        }
    }
    
    @SubscribeEvent
    public void onInputUpdate(final InputUpdateEvent event) {
        Event.EVENT_BUS.post(event);
    }
}
