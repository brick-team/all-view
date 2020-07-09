package org.tview.visualization.inter.db;

import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.db.TableDataEntity;
import org.tview.visualization.model.req.PageVO;

import java.sql.SQLException;
import java.util.Map;

/** 表的操作 */
public interface TableOperation {

    /**
     * 查询表的所有
     *
     * @param config 链接配置
     * @param table  表名字
     * @param pageVO 分页参数
     *
     * @return
     */
    TableDataEntity findAll(DBConnectionConfig config, String table, PageVO pageVO)
            throws SQLException;

    /**
     * 表的信息
     *
     * <ol>
     *   <li>表结构
     *   <li>表索引
     * </ol>
     *
     * @return
     */
    Object tableInfo(DBConnectionConfig config, String table);

    /**
     * 删除一条数据
     */
    void deleteOnceData(DBConnectionConfig config, String table, int id) throws SQLException;

    /**
     * 创建一条数据
     */
    void createOnceData(DBConnectionConfig config, String table, Map<String, Object> data)
            throws SQLException;

    /** 创建数据表 */
    void createTable(DBConnectionConfig config, String table);

    /** 删除表 */
  void deleteTable(DBConnectionConfig config, String table);
}
