package org.tview.visualization.model.zk;

/**
 * 修改 zookeeper node 的参数
 */
public class ZkNodeUpdateParam {
    private String path;
    private String data;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
