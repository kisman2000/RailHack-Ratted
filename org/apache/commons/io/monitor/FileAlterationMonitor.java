//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package org.apache.commons.io.monitor;

import java.util.concurrent.*;
import java.util.*;

public final class FileAlterationMonitor implements Runnable
{
    private final long interval;
    private final List<FileAlterationObserver> observers;
    private Thread thread;
    private ThreadFactory threadFactory;
    private volatile boolean running;
    
    public FileAlterationMonitor() {
        this(10000L);
    }
    
    public FileAlterationMonitor(final long interval) {
        this.observers = new CopyOnWriteArrayList<FileAlterationObserver>();
        this.thread = null;
        this.running = false;
        this.interval = interval;
    }
    
    public FileAlterationMonitor(final long interval, final FileAlterationObserver... observers) {
        this(interval);
        if (observers != null) {
            for (final FileAlterationObserver observer : observers) {
                this.addObserver(observer);
            }
        }
    }
    
    public long getInterval() {
        return this.interval;
    }
    
    public synchronized void setThreadFactory(final ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
    }
    
    public void addObserver(final FileAlterationObserver observer) {
        if (observer != null) {
            this.observers.add(observer);
        }
    }
    
    public void removeObserver(final FileAlterationObserver observer) {
        if (observer != null) {
            while (this.observers.remove(observer)) {}
        }
    }
    
    public Iterable<FileAlterationObserver> getObservers() {
        return this.observers;
    }
    
    public synchronized void start() throws Exception {
        if (this.running) {
            throw new IllegalStateException("Monitor is already running");
        }
        for (final FileAlterationObserver observer : this.observers) {
            observer.initialize();
        }
        this.running = true;
        if (this.threadFactory != null) {
            this.thread = this.threadFactory.newThread(this);
        }
        else {
            this.thread = new Thread(this);
        }
        this.thread.start();
    }
    
    public synchronized void stop() throws Exception {
        this.stop(this.interval);
    }
    
    public synchronized void stop(final long stopInterval) throws Exception {
        if (!this.running) {
            throw new IllegalStateException("Monitor is not running");
        }
        this.running = false;
        try {
            this.thread.join(stopInterval);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        for (final FileAlterationObserver observer : this.observers) {
            observer.destroy();
        }
    }
    
    @Override
    public void run() {
        while (this.running) {
            for (final FileAlterationObserver observer : this.observers) {
                observer.checkAndNotify();
            }
            if (!this.running) {
                break;
            }
            try {
                Thread.sleep(this.interval);
            }
            catch (InterruptedException ex) {}
        }
    }
}
