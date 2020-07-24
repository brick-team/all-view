package org.tview.visualization.app.monitor;

import java.util.List;
import java.util.Map;

public class JavaMonitorEntity {
    /**
     * 系统参数
     */
    Map<String, String> systemProperties;
    /**
     * 入参
     */
    List<String> inputArguments;
    /**
     * 启动时间戳
     */
    long startTime;
    /**
     * java 虚拟机开始执行到目前已经加载的类的总数。
     */
    long totalLoadedClassCount;
    /**
     * java 虚拟机开始执行到目前已经卸载的类的总数。
     */
    long unloadedClassCount;
    /**
     * 返回当前加载到 java 虚拟机中的类的数量。
     */
    int loadedClassCount;

    public Map<String, String> getSystemProperties() {
        return systemProperties;
    }

    public void setSystemProperties(Map<String, String> systemProperties) {
        this.systemProperties = systemProperties;
    }

    public List<String> getInputArguments() {
        return inputArguments;
    }

    public void setInputArguments(List<String> inputArguments) {
        this.inputArguments = inputArguments;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getTotalLoadedClassCount() {
        return totalLoadedClassCount;
    }

    public void setTotalLoadedClassCount(long totalLoadedClassCount) {
        this.totalLoadedClassCount = totalLoadedClassCount;
    }

    public long getUnloadedClassCount() {
        return unloadedClassCount;
    }

    public void setUnloadedClassCount(long unloadedClassCount) {
        this.unloadedClassCount = unloadedClassCount;
    }

    public int getLoadedClassCount() {
        return loadedClassCount;
    }

    public void setLoadedClassCount(int loadedClassCount) {
        this.loadedClassCount = loadedClassCount;
    }
}
