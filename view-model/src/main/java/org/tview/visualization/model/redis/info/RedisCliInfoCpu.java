package org.tview.visualization.model.redis.info;

public class RedisCliInfoCpu {
    private String usedCpuSys;
    private String usedCpuUser;
    private String usedCpuSysChildren;
    private String usedCpuUserChildren;

    public String getUsedCpuSys() {
        return usedCpuSys;
    }

    public void setUsedCpuSys(String usedCpuSys) {
        this.usedCpuSys = usedCpuSys;
    }

    public String getUsedCpuUser() {
        return usedCpuUser;
    }

    public void setUsedCpuUser(String usedCpuUser) {
        this.usedCpuUser = usedCpuUser;
    }

    public String getUsedCpuSysChildren() {
        return usedCpuSysChildren;
    }

    public void setUsedCpuSysChildren(String usedCpuSysChildren) {
        this.usedCpuSysChildren = usedCpuSysChildren;
    }

    public String getUsedCpuUserChildren() {
        return usedCpuUserChildren;
    }

    public void setUsedCpuUserChildren(String usedCpuUserChildren) {
        this.usedCpuUserChildren = usedCpuUserChildren;
    }
}
