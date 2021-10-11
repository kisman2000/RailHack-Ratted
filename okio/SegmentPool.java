//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\Admin\Desktop\Minecraft-Deobfuscator3000-1.2.2\1.12 stable mappings"!

//Decompiled by Procyon!

package okio;

final class SegmentPool
{
    static final long MAX_SIZE = 65536L;
    static Segment next;
    static long byteCount;
    
    private SegmentPool() {
    }
    
    static Segment take() {
        synchronized (SegmentPool.class) {
            if (SegmentPool.next != null) {
                final Segment result = SegmentPool.next;
                SegmentPool.next = result.next;
                result.next = null;
                SegmentPool.byteCount -= 2048L;
                return result;
            }
        }
        return new Segment();
    }
    
    static void recycle(final Segment segment) {
        if (segment.next != null || segment.prev != null) {
            throw new IllegalArgumentException();
        }
        if (segment.shared) {
            return;
        }
        synchronized (SegmentPool.class) {
            if (SegmentPool.byteCount + 2048L > 65536L) {
                return;
            }
            SegmentPool.byteCount += 2048L;
            segment.next = SegmentPool.next;
            final int n = 0;
            segment.limit = n;
            segment.pos = n;
            SegmentPool.next = segment;
        }
    }
}
