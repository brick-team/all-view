package org.tview.visualization;

import com.sun.management.OperatingSystemMXBean;
import org.tview.visualization.model.jvm.*;

import javax.management.*;
import java.io.IOException;
import java.lang.management.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MonitorUtils {
  public static final String RUN_TIME_NAME = "java.lang:type=Runtime";
  public static final String THREAD_BEAN_NAME = "java.lang:type=Threading";
  public static final String OPERATING_SYSTEM = "java.lang:type=OperatingSystem";
  public static final String TYPE_MEMORY = "java.lang:type=Memory";
  public static final String HEAP_ITEM = "PS Survivor Space,PS Eden Space,PS Old Gen";
  private static final String CLASS_LOADING = "java.lang:type=ClassLoading";

  public static void main(String[] args) throws IOException, MalformedObjectNameException {
    JavaMemoryInfoEntity javaMemoryInfoEntity = memoryInfo();
  }

  public static JavaMemoryInfoEntity memoryInfo() throws IOException, MalformedObjectNameException {
    MemoryMXBean memoryMXBean =
        ManagementFactory.newPlatformMXBeanProxy(connection(), TYPE_MEMORY, MemoryMXBean.class);
    MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();
    MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
    JavaMemoryInfoEntity javaMemoryInfoEntity = new JavaMemoryInfoEntity();

    JavaUsageEntity nonHeapMemoryUsageEt = getJavaUsageEntity(nonHeapMemoryUsage);
    javaMemoryInfoEntity.setNonHeapMemoryUsage(nonHeapMemoryUsageEt);

    JavaUsageEntity heapMemoryUsageEt = getJavaUsageEntity(heapMemoryUsage);
    javaMemoryInfoEntity.setHeapMemoryUsage(heapMemoryUsageEt);

    ObjectName obj2 = new ObjectName("java.lang:type=MemoryPool,*");
    MBeanServerConnection conn = ManagementFactory.getPlatformMBeanServer();
    Set<ObjectInstance> mBeanset2 = conn.queryMBeans(obj2, null);
    List<JavaUsageKvInfo> nonHeap = new ArrayList<>();
    List<JavaUsageKvInfo> heap = new ArrayList<>();
    for (ObjectInstance objx : mBeanset2) {
      String name = objx.getObjectName().getCanonicalName();
      String keyName = objx.getObjectName().getKeyProperty("name");
      MemoryPoolMXBean memoryPoolMXBean =
          ManagementFactory.newPlatformMXBeanProxy(connection(), name, MemoryPoolMXBean.class);
      JavaUsageKvInfo javaUsageKvInfo = new JavaUsageKvInfo();
      javaUsageKvInfo.setName(name);
      javaUsageKvInfo.setKeyProperty(keyName);
      javaUsageKvInfo.setJavaUsageEntity(getJavaUsageEntity(memoryPoolMXBean.getUsage()));

      if (HEAP_ITEM.contains(keyName)) {
        heap.add(javaUsageKvInfo);
      } else {
        nonHeap.add(javaUsageKvInfo);
      }
    }

    javaMemoryInfoEntity.setHeap(heap);
    javaMemoryInfoEntity.setNonHeap(nonHeap);
    return javaMemoryInfoEntity;
  }

  private static JavaUsageEntity getJavaUsageEntity(MemoryUsage heapMemoryUsage) {
    JavaUsageEntity heapMemoryUsageEt = new JavaUsageEntity();
    heapMemoryUsageEt.setInit(heapMemoryUsage.getInit());
    heapMemoryUsageEt.setUsed(heapMemoryUsage.getUsed());
    heapMemoryUsageEt.setCommitted(heapMemoryUsage.getCommitted());
    heapMemoryUsageEt.setMax(heapMemoryUsage.getMax());
    return heapMemoryUsageEt;
  }

  /**
   * GC 信息
   *
   * @return
   * @throws IOException
   * @throws MalformedObjectNameException
   */
  public static List<JavaGcInfoEntity> gcInfoEntities()
      throws IOException, MalformedObjectNameException {
    MBeanServer platformMBeanServer = ManagementFactory.getPlatformMBeanServer();
    ObjectName obj = new ObjectName("java.lang:type=GarbageCollector,*");
    Set<ObjectInstance> mBeanset = platformMBeanServer.queryMBeans(obj, null);
    Class<GarbageCollectorMXBean> cls = GarbageCollectorMXBean.class;
    List<JavaGcInfoEntity> res = new ArrayList<>();

    for (ObjectInstance objx : mBeanset) {
      String name = objx.getObjectName().getCanonicalName();
      String keyName = objx.getObjectName().getKeyProperty("name");
      GarbageCollectorMXBean gc =
          ManagementFactory.newPlatformMXBeanProxy(platformMBeanServer, name, cls);

      JavaGcInfoEntity javaGcInfoEntity = new JavaGcInfoEntity();
      javaGcInfoEntity.setName(keyName + "-time");
      javaGcInfoEntity.setVal(gc.getCollectionTime() / 1000.0);
      res.add(javaGcInfoEntity);

      JavaGcInfoEntity javaGcInfoEntity2 = new JavaGcInfoEntity();
      javaGcInfoEntity2.setName(keyName + "-count");
      javaGcInfoEntity2.setVal(gc.getCollectionCount());
      res.add(javaGcInfoEntity2);
    }
    return res;
  }

  /**
   * cpu 信息
   *
   * @return
   * @throws IOException
   */
  public static JavaCpuEntity findCpuInfo() throws IOException {
    OperatingSystemMXBean os =
        ManagementFactory.newPlatformMXBeanProxy(
            connection(), OPERATING_SYSTEM, OperatingSystemMXBean.class);
    JavaCpuEntity javaCpuEntity = new JavaCpuEntity();
    javaCpuEntity.setTotalSwapSpaceSize(os.getTotalSwapSpaceSize());
    javaCpuEntity.setProcessCpuTime(os.getProcessCpuTime());
    javaCpuEntity.setFreePhysicalMemorySize(os.getFreePhysicalMemorySize());
    javaCpuEntity.setTotalPhysicalMemorySize(os.getTotalPhysicalMemorySize());
    javaCpuEntity.setSystemCpuLoad(os.getSystemCpuLoad());
    javaCpuEntity.setProcessCpuLoad(os.getProcessCpuLoad());
    javaCpuEntity.setCommittedVirtualMemorySize(os.getCommittedVirtualMemorySize());
    return javaCpuEntity;
  }

  /**
   * 死锁检查
   *
   * @return
   * @throws IOException
   */
  public static JavaDeadlockCheck deadlockCheck() throws IOException {
    ThreadMXBean threadMXBean =
        ManagementFactory.newPlatformMXBeanProxy(
            connection(), THREAD_BEAN_NAME, ThreadMXBean.class);
    long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
    JavaDeadlockCheck javaDeadlockCheck = new JavaDeadlockCheck();

    if (deadlockedThreads != null) {
      ThreadInfo[] threadInfo = threadMXBean.getThreadInfo(deadlockedThreads, Integer.MAX_VALUE);
      StringBuffer sb = new StringBuffer(64);
      for (ThreadInfo info : threadInfo) {
        sb.append("\n").append(info);
      }
      javaDeadlockCheck.setHasdeadlock(true);
      javaDeadlockCheck.setInfo(sb.toString());
    }
    return javaDeadlockCheck;
  }

  /**
   * 线程信息
   *
   * @return
   * @throws IOException
   */
  public static JavaThreadEntity javaThreadEntity() throws IOException {
    ThreadMXBean threadMXBean =
        ManagementFactory.newPlatformMXBeanProxy(
            connection(), THREAD_BEAN_NAME, ThreadMXBean.class);
    ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);

    JavaThreadEntity javaThreadEntity = new JavaThreadEntity();
    List<JavaThreadInfo> detail = new ArrayList<>();
    HashMap<Thread.State, Integer> state = new HashMap<>();

    for (ThreadInfo info : threadInfos) {

      long threadId = info.getThreadId();
      long cpu = threadMXBean.getThreadCpuTime(threadId);
      Thread.State tState = info.getThreadState();

      JavaThreadInfo javaThreadInfo = new JavaThreadInfo();
      javaThreadInfo.setId(threadId);
      javaThreadInfo.setState(tState);
      javaThreadInfo.setName(info.getThreadName());
      javaThreadInfo.setCpu(TimeUnit.NANOSECONDS.toMillis(cpu));
      detail.add(javaThreadInfo);

      Integer vl = state.get(tState);
      if (vl == null) {
        state.put(tState, 0);
      } else {
        state.put(tState, vl + 1);
      }
    }
    javaThreadEntity.setDeamon(threadMXBean.getDaemonThreadCount());
    javaThreadEntity.setTime(System.currentTimeMillis());
    javaThreadEntity.setTotal(threadMXBean.getThreadCount());
    javaThreadEntity.setDetail(detail);
    javaThreadEntity.setState(state);

    return javaThreadEntity;
  }

  /**
   * jvm 信息
   *
   * @return
   * @throws IOException
   */
  public static JavaMonitorEntity jvmInfo() throws IOException {
    RuntimeMXBean runtimeMXBean =
        ManagementFactory.newPlatformMXBeanProxy(connection(), RUN_TIME_NAME, RuntimeMXBean.class);
    ClassLoadingMXBean classLoadingMXBean =
        ManagementFactory.newPlatformMXBeanProxy(
            connection(), CLASS_LOADING, ClassLoadingMXBean.class);
    // 系统参数
    Map<String, String> systemProperties = runtimeMXBean.getSystemProperties();
    // 入参
    List<String> inputArguments = runtimeMXBean.getInputArguments();
    // 启动时间戳
    long startTime = runtimeMXBean.getStartTime();
    //  java 虚拟机开始执行到目前已经加载的类的总数。
    long totalLoadedClassCount = classLoadingMXBean.getTotalLoadedClassCount();
    // java 虚拟机开始执行到目前已经卸载的类的总数。
    long unloadedClassCount = classLoadingMXBean.getUnloadedClassCount();
    // 返回当前加载到 java 虚拟机中的类的数量。
    int loadedClassCount = classLoadingMXBean.getLoadedClassCount();

    JavaMonitorEntity javaMonitorEntity = new JavaMonitorEntity();
    javaMonitorEntity.setSystemProperties(systemProperties);
    javaMonitorEntity.setInputArguments(inputArguments);
    javaMonitorEntity.setStartTime(startTime);
    javaMonitorEntity.setTotalLoadedClassCount(totalLoadedClassCount);
    javaMonitorEntity.setUnloadedClassCount(unloadedClassCount);
    javaMonitorEntity.setLoadedClassCount(loadedClassCount);

    return javaMonitorEntity;
  }

  /**
   * 当前连接
   *
   * @return
   */
  private static MBeanServerConnection connection() {
    return ManagementFactory.getPlatformMBeanServer();
  }
}
