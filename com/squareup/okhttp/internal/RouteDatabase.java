//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package com.squareup.okhttp.internal;

import com.squareup.okhttp.*;
import java.util.*;

public final class RouteDatabase
{
    private final Set<Route> failedRoutes;
    
    public RouteDatabase() {
        this.failedRoutes = new LinkedHashSet<Route>();
    }
    
    public synchronized void failed(final Route failedRoute) {
        this.failedRoutes.add(failedRoute);
    }
    
    public synchronized void connected(final Route route) {
        this.failedRoutes.remove(route);
    }
    
    public synchronized boolean shouldPostpone(final Route route) {
        return this.failedRoutes.contains(route);
    }
    
    public synchronized int failedRoutesCount() {
        return this.failedRoutes.size();
    }
}
