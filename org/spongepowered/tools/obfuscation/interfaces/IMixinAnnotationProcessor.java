//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.interfaces;

import javax.annotation.processing.*;
import org.spongepowered.asm.util.*;

public interface IMixinAnnotationProcessor extends Messager, IOptionProvider
{
    CompilerEnvironment getCompilerEnvironment();
    
    ProcessingEnvironment getProcessingEnvironment();
    
    IObfuscationManager getObfuscationManager();
    
    ITokenProvider getTokenProvider();
    
    ITypeHandleProvider getTypeProvider();
    
    IJavadocProvider getJavadocProvider();
    
    public enum CompilerEnvironment
    {
        JAVAC, 
        JDT;
    }
}