//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.guiscreen.hud;

import me.shatteredhej.railhack.railhackmod.guiscreen.render.pinnables.*;
import me.shatteredhej.railhack.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import java.util.function.*;
import java.util.stream.*;
import com.mojang.realmsclient.gui.*;
import java.util.*;
import javax.annotation.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.projectile.*;

public class WurstplusEntityList extends WurstplusPinnable
{
    public WurstplusEntityList() {
        super("Entity List", "EntityList", 1.0f, 0, 0);
    }
    
    @Override
    public void render() {
        int counter = 12;
        final int nl_r = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorR").getValue(1);
        final int nl_g = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorG").getValue(1);
        final int nl_b = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorB").getValue(1);
        final int nl_a = RailHack.getSettingManager().get_setting_with_tag("HUD", "HUDStringsColorA").getValue(1);
        final List<Entity> entity_list = new ArrayList<Entity>(this.mc.world.loadedEntityList);
        if (entity_list.size() <= 1) {
            return;
        }
        final Map<String, Integer> entity_counts = entity_list.stream().filter(Objects::nonNull).filter(e -> !(e instanceof EntityPlayer)).collect(Collectors.groupingBy((Function<? super Object, ? extends String>)WurstplusEntityList::get_entity_name, (Collector<? super Object, ?, Integer>)Collectors.reducing((D)0, ent -> {
            if (ent instanceof EntityItem) {
                return Integer.valueOf(ent.getItem().getCount());
            }
            else {
                return Integer.valueOf(1);
            }
        }, Integer::sum)));
        for (final Map.Entry<String, Integer> entity : entity_counts.entrySet()) {
            final String e2 = entity.getKey() + " " + ChatFormatting.DARK_GRAY + "x" + entity.getValue();
            this.create_line(e2, this.docking(1, e2), 1 * counter, nl_r, nl_g, nl_b, nl_a);
            counter += 12;
        }
        this.set_width(this.get("aaaaaaaaaaaaaaa", "width") + 2);
        this.set_height(this.get("aaaaaaaaaaaaaaa", "height") * 5);
    }
    
    private static String get_entity_name(@Nonnull final Entity entity) {
        if (entity instanceof EntityItem) {
            return ((EntityItem)entity).getItem().getItem().getItemStackDisplayName(((EntityItem)entity).getItem());
        }
        if (entity instanceof EntityWitherSkull) {
            return "Wither skull";
        }
        if (entity instanceof EntityEnderCrystal) {
            return "End crystal";
        }
        if (entity instanceof EntityEnderPearl) {
            return "Thrown ender pearl";
        }
        if (entity instanceof EntityMinecart) {
            return "Minecart";
        }
        if (entity instanceof EntityItemFrame) {
            return "Item frame";
        }
        if (entity instanceof EntityEgg) {
            return "Thrown egg";
        }
        if (entity instanceof EntitySnowball) {
            return "Thrown snowball";
        }
        return entity.getName();
    }
}
