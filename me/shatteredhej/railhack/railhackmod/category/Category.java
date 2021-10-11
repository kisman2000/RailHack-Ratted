//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package me.shatteredhej.railhack.railhackmod.category;

public enum Category
{
    Chat("Chat", "WurstplusChat", false), 
    Combat("Combat", "WurstplusCombat", false), 
    Movement("Movement", "WurstplusMovement", false), 
    Render("Render", "WurstplusRender", false), 
    Exploit("Exploit", "WurstplusExploit", false), 
    Misc("Misc", "WurstplusMisc", false), 
    Gui("Client", "WurstplusGui", false), 
    Beta("Beta", "WurstplusBeta", true), 
    Hidden("Hidden", "WurstplusHidden", true);
    
    String name;
    String tag;
    boolean hidden;
    
    private Category(final String name, final String tag, final boolean hidden) {
        this.name = name;
        this.tag = tag;
        this.hidden = hidden;
    }
    
    public boolean is_hidden() {
        return this.hidden;
    }
    
    public String get_name() {
        return this.name;
    }
    
    public String get_tag() {
        return this.tag;
    }
}
