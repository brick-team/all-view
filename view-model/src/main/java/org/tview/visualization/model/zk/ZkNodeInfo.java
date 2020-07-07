package org.tview.visualization.model.zk;

/**
 * zookeeper 节点信息
 */
public class ZkNodeInfo {

    /**
     * zk stat
     */
    private ZkStat stat;
    /**
     * 数据
     */
    private String data;
    /**
     * * 节点类型:
     *
     * <ol>
     *   <li>持久化节点
     *   <li>临时节点
     * </ol>
     */
    private String type;
}
