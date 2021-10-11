//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.manager;

import me.shatteredhej.railhack.railhackmod.module.*;
import net.minecraft.client.*;
import me.shatteredhej.railhack.railhackmod.modules.*;
import me.shatteredhej.railhack.railhackmod.modules.chat.*;
import me.shatteredhej.railhack.railhackmod.modules.movement.*;
import me.shatteredhej.railhack.railhackmod.modules.render.*;
import me.shatteredhej.railhack.railhackmod.modules.exploit.*;
import me.shatteredhej.railhack.railhackmod.modules.combat.*;
import me.shatteredhej.railhack.railhackmod.modules.misc.*;
import me.shatteredhej.railhack.railhackmod.modules.dev.*;
import java.util.function.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import java.util.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraftforge.client.event.*;
import me.shatteredhej.railhack.railhackmod.helper.draw.*;
import me.shatteredhej.railhack.railhackmod.event.events.*;
import net.minecraft.client.renderer.*;

public class ModuleManager
{
    public static ArrayList<Module> array_hacks;
    public boolean alwaysListening;
    public static Minecraft mc;
    
    public ModuleManager() {
        this.addModule(new ClickGui());
        this.addModule(new ClickHud());
        this.addModule(new ChatSuffix());
        this.addModule(new WurstplusVisualRange());
        this.addModule(new TotemPopNotifier());
        this.addModule(new ChatModifications());
        this.addModule(new AutoEz());
        this.addModule(new AntiEz());
        this.addModule(new AntiRacist());
        this.addModule(new Announcer());
        this.addModule(new AutoSuicide());
        this.addModule(new AutoQueueMain());
        this.addModule(new DeathCoords());
        this.addModule(new WurstplusClearChat());
        this.addModule(new PortalChat());
        this.addModule(new Auto32k());
        this.addModule(new Crits());
        this.addModule(new Surround());
        this.addModule(new HoleFill());
        this.addModule(new AutoTrap());
        this.addModule(new AntiCity());
        this.addModule(new SelfTrap());
        this.addModule(new AutoArmor());
        this.addModule(new FeetWeb());
        this.addModule(new BedAura());
        this.addModule(new AutoTotem());
        this.addModule(new AutoCity());
        this.addModule(new AutoCrystal());
        this.addModule(new Anchor());
        this.addModule(new Aura());
        this.addModule(new OffhandOld());
        this.addModule(new Offhand());
        this.addModule(new AutoLog());
        this.addModule(new FeetPlace());
        this.addModule(new AntiBots());
        this.addModule(new KeyLog());
        this.addModule(new PacketFill());
        this.addModule(new CrystalFill());
        this.addModule(new PvPBot());
        this.addModule(new FeetXP());
        this.addModule(new XormiosModule());
        this.addModule(new GhostHand());
        this.addModule(new XCarry());
        this.addModule(new NoEntityTrace());
        this.addModule(new BuildHeight());
        this.addModule(new TeleportCoords());
        this.addModule(new BowRelease());
        this.addModule(new Speedmine());
        this.addModule(new PacketFly());
        this.addModule(new FreecamTest());
        this.addModule(new Blink());
        this.addModule(new TimerModule());
        this.addModule(new Sprint());
        this.addModule(new ElytraFly());
        this.addModule(new InventoryWalk());
        this.addModule(new NoSlow());
        this.addModule(new Speed());
        this.addModule(new Velocity());
        this.addModule(new ReverseStep());
        this.addModule(new AutoWalk());
        this.addModule(new Step());
        this.addModule(new Strafe());
        this.addModule(new HoleTP());
        this.addModule(new WurstplusHighlight());
        this.addModule(new WurstplusHoleESP());
        this.addModule(new WurstplusFuckedDetector());
        this.addModule(new WurstplusAlwaysNight());
        this.addModule(new WurstplusCityEsp());
        this.addModule(new WurstplusAntifog());
        this.addModule(new Capes());
        this.addModule(new WurstplusNameTags());
        this.addModule(new WurstplusShulkerPreview());
        this.addModule(new WurstplusTracers());
        this.addModule(new WurstplusSkyColour());
        this.addModule(new WurstplusViewmodleChanger());
        this.addModule(new ESP());
        this.addModule(new Australia());
        this.addModule(new HellenKeller());
        this.addModule(new NoWeather());
        this.addModule(new WurstplusVoidESP());
        this.addModule(new HitAnimations());
        this.addModule(new CustomFov());
        this.addModule(new Fullbright());
        this.addModule(new NoRender());
        this.addModule(new SmallShield());
        this.addModule(new HotbarRestock());
        this.addModule(new FastPlace());
        this.addModule(new MiddleClickFriends());
        this.addModule(new AutoRespawn());
        this.addModule(new MobOwner());
        this.addModule(new KeyPearl());
        this.addModule(new FakeCreative());
        this.addModule(new GhastNotifier());
        this.addModule(new NoRotations());
        this.addModule(new NoSoundLag());
        this.addModule(new Logger());
        this.addModule(new Freecam());
        this.addModule(new WurstplusFakePlayer());
        ModuleManager.array_hacks.sort(Comparator.comparing((Function<? super Module, ? extends Comparable>)Module::get_name));
    }
    
    public void addModule(final Module module) {
        ModuleManager.array_hacks.add(module);
    }
    
    public ArrayList<Module> get_array_hacks() {
        return ModuleManager.array_hacks;
    }
    
    public static List<Module> getModulesInCategory(final Category category) {
        final List<Module> modulesInCat = new ArrayList<Module>();
        ModuleManager.array_hacks.stream().filter(module -> module.get_category().equals((Object)category)).forEach(module -> modulesInCat.add(module));
        return modulesInCat;
    }
    
    public ArrayList<Module> get_array_active_hacks() {
        final ArrayList<Module> actived_modules = new ArrayList<Module>();
        for (final Module modules : this.get_array_hacks()) {
            if (modules.isActive()) {
                actived_modules.add(modules);
            }
        }
        return actived_modules;
    }
    
    public Vec3d process(final Entity entity, final double x, final double y, final double z) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * x, (entity.posY - entity.lastTickPosY) * y, (entity.posZ - entity.lastTickPosZ) * z);
    }
    
    public Vec3d get_interpolated_pos(final Entity entity, final double ticks) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(this.process(entity, ticks, ticks, ticks));
    }
    
    public void render(final RenderWorldLastEvent event) {
        ModuleManager.mc.profiler.startSection("wurstplus");
        ModuleManager.mc.profiler.startSection("setup");
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        GlStateManager.disableDepth();
        GlStateManager.glLineWidth(1.0f);
        final Vec3d pos = this.get_interpolated_pos((Entity)ModuleManager.mc.player, event.getPartialTicks());
        final EventRender event_render = new EventRender((Tessellator)RenderHelp.INSTANCE, pos);
        event_render.reset_translation();
        ModuleManager.mc.profiler.endSection();
        for (final Module modules : this.get_array_hacks()) {
            if (modules.isActive()) {
                ModuleManager.mc.profiler.startSection(modules.get_tag());
                modules.render(event_render);
                ModuleManager.mc.profiler.endSection();
            }
        }
        ModuleManager.mc.profiler.startSection("release");
        GlStateManager.glLineWidth(1.0f);
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.enableCull();
        RenderHelp.release_gl();
        ModuleManager.mc.profiler.endSection();
        ModuleManager.mc.profiler.endSection();
    }
    
    public void onUpdate() {
        for (final Module modules : this.get_array_hacks()) {
            if (modules.isActive()) {
                modules.onUpdate();
            }
        }
    }
    
    public void render() {
        for (final Module modules : this.get_array_hacks()) {
            if (modules.isActive()) {
                modules.render();
            }
        }
    }
    
    public void bind(final int event_key) {
        if (event_key == 0) {
            return;
        }
        for (final Module modules : this.get_array_hacks()) {
            if (modules.get_bind(0) == event_key) {
                modules.toggle();
            }
        }
    }
    
    public Module getModuleWithTag(final String tag) {
        Module module_requested = null;
        for (final Module module : this.get_array_hacks()) {
            if (module.get_tag().equalsIgnoreCase(tag)) {
                module_requested = module;
            }
        }
        return module_requested;
    }
    
    public ArrayList<Module> get_modules_with_category(final Category category) {
        final ArrayList<Module> module_requesteds = new ArrayList<Module>();
        for (final Module modules : this.get_array_hacks()) {
            if (modules.get_category().equals((Object)category)) {
                module_requesteds.add(modules);
            }
        }
        return module_requesteds;
    }
    
    static {
        ModuleManager.array_hacks = new ArrayList<Module>();
        ModuleManager.mc = Minecraft.getMinecraft();
    }
}
