package org.tview.visualization.app.monitor;

import static org.tview.visualization.app.monitor.MonitorUtils.RUNTIMNAME;

import java.io.IOException;
import java.lang.management.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.management.*;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

public class TestMonitor {

    public static final String THREAD_BEAN_NAME = "java.lang:type=Threading";
    public static final String OSBEANNAME = "java.lang:type=OperatingSystem";
    public static final String MEMORYNAME = "java.lang:type=Memory";
    public static final String HEAP_ITEM = "PS Survivor Space,PS Eden Space,PS Old Gen";

    private static MBeanServerConnection connection() {
        return ManagementFactory.getPlatformMBeanServer();
    }



    private static Map<String, Object> memoryPoolMXBeanInfo(MemoryPoolMXBean memoryPoolMXBean) {
        Map<String, Object> res = new HashMap<>();
        MemoryType type = memoryPoolMXBean.getType();
        String name = type.name();
        MemoryUsage usage = memoryPoolMXBean.getUsage();
        res.put("used", usage.getUsed());
        res.put("max", usage.getMax());
        res.put("committed", usage.getCommitted());
        res.put("init", usage.getInit());
        return res;
    }


}
