package org.tview.visualization.model.db.mysql;

import java.util.List;

import org.tview.visualization.model.db.CreateRowParams;

/**
 * 修改表结构的请求参数
 */
public class MysqlChangeTableStructureParam {

    /**
     * 新的表名字
     */
    private String newTableName;
    /**
     * 老的表名字
     */
    private String oldTableName;


    /**
     * 需要变化的字段结构
     */
    private List<CreateRowParams> changeRow;
    /**
     * 新增的字段结构
     */
    private List<CreateRowParams> addRow;


    /**
     * 删除的字段
     */
    private List<String> removeRow;

    /**
     * 需要修改的索引
     */
    private List<String> changeIndex;
    /**
     * 需要添加的索引
     */
    private List<String> addIndex;

    /**
     * 需要删除的索引
     */
    private List<String> removeIndex;

}
