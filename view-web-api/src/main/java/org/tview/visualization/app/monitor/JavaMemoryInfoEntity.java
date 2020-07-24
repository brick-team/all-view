package org.tview.visualization.app.monitor;

import java.util.List;

public class JavaMemoryInfoEntity {
    private JavaUsageEntity nonHeapMemoryUsage;
    private JavaUsageEntity heapMemoryUsage;

    private List<JavaUsageKvInfo> nonHeap;
    private List<JavaUsageKvInfo> heap;

    public List<JavaUsageKvInfo> getNonHeap() {
        return nonHeap;
    }

    public void setNonHeap(List<JavaUsageKvInfo> nonHeap) {
        this.nonHeap = nonHeap;
    }

    public List<JavaUsageKvInfo> getHeap() {
        return heap;
    }

    public void setHeap(List<JavaUsageKvInfo> heap) {
        this.heap = heap;
    }

    public JavaUsageEntity getNonHeapMemoryUsage() {
        return nonHeapMemoryUsage;
    }

    public void setNonHeapMemoryUsage(JavaUsageEntity nonHeapMemoryUsage) {
        this.nonHeapMemoryUsage = nonHeapMemoryUsage;
    }

    public JavaUsageEntity getHeapMemoryUsage() {
        return heapMemoryUsage;
    }

    public void setHeapMemoryUsage(JavaUsageEntity heapMemoryUsage) {
        this.heapMemoryUsage = heapMemoryUsage;
    }
}
