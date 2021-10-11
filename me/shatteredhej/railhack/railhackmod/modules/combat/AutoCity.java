//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.modules.combat;

import me.shatteredhej.railhack.railhackmod.module.*;
import me.shatteredhej.railhack.railhackmod.guiscreen.settings.*;
import me.shatteredhej.railhack.railhackmod.category.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import me.shatteredhej.railhack.railhackmod.util.crystal.*;
import me.shatteredhej.railhack.railhackmod.util.*;
import me.shatteredhej.railhack.railhackmod.util.block.*;
import net.minecraft.util.math.*;
import java.util.*;

public class AutoCity extends Module
{
    Setting end_crystal;
    Setting range;
    
    public AutoCity() {
        super(Category.Combat);
        this.end_crystal = this.register("End Crystal", "MineEndCrystal", false);
        this.range = this.register("Range", "MineRange", 4, 0, 6);
        this.name = "AutoCity";
        this.tag = "AutoCity";
        this.description = "jumpy is now never going to use the client again";
    }
    
    protected void onEnable() {
        BlockPos target_block = null;
        for (final EntityPlayer player : AutoCity.mc.world.playerEntities) {
            if (AutoCity.mc.player.getDistance((Entity)player) > this.range.getValue(1)) {
                continue;
            }
            final BlockPos p = EntityUtil.is_cityable(player, this.end_crystal.getValue(true));
            if (p == null) {
                continue;
            }
            target_block = p;
        }
        if (target_block == null) {
            MessageUtil.send_client_message("cannot find block");
            this.whenDisabled();
        }
        BreakUtil.set_current_block(target_block);
    }
    
    protected void whenDisabled() {
        BreakUtil.set_current_block(null);
    }
}
