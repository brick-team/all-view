package org.tview.visualization.mysql.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.CollectionUtils;
import org.tview.visualization.inter.db.TableOperation;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.db.TableDataEntity;
import org.tview.visualization.model.req.PageVO;
import org.tview.visualization.mysql.factory.JdbcFactory;
import org.tview.visualization.mysql.factory.JdbcTemplateFactory;
import org.tview.visualization.page.PageUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MysqlTableOperation implements TableOperation {
    JdbcFactory factory = new JdbcTemplateFactory();

    /**
     * 查询表的所有
     *
     * @param config
     * @param table
     * @param pageVO
     *
     * @return
     */
    @Override
    public TableDataEntity findAll(DBConnectionConfig config, String table, PageVO pageVO)
            throws SQLException {
        JdbcTemplate jdbcTemplate = factory.create(config);
        PageVO calc = PageUtils.calc(pageVO);
        TableDataEntity tableDataEntity = new TableDataEntity();

        List<Map<String, Object>> maps =
                jdbcTemplate.queryForList(
                        "select * from " + table + " limit " + calc.getNum() + " , " + calc.getSize());

        List<String> fields = new ArrayList<>();

        // 添加字段
        if (!CollectionUtils.isEmpty(maps)) {
            Map<String, Object> firstData = maps.get(0);
            firstData.forEach(
                    (k, v) -> {
                        fields.add(k);
                    });
        }
        List<List<Object>> rows = new ArrayList<>();

        if (!CollectionUtils.isEmpty(maps)) {
            List<Object> row = new ArrayList<>();
            for (Map<String, Object> map : maps) {
                map.forEach(
                        (k, v) -> {
                            row.add(v);
                        });
            }
            rows.add(row);
        }
        tableDataEntity.setData(maps);
        tableDataEntity.setFiledName(fields);
        tableDataEntity.setShowData(rows);

        return tableDataEntity;
    }

    /**
     * 表的信息
     *
     * <ol>
     *   <li>表结构
     *   <li>表索引
     * </ol>
     *
     * @param config
     * @param table
     *
     * @return
     */
    @Override
    public Object tableInfo(DBConnectionConfig config, String table) {
        return null;
    }

    /**
     * 删除一条数据
     *
     * @param config
     * @param table
     * @param id
     */
    @Override
    public void deleteOnceData(DBConnectionConfig config, String table, int id) throws SQLException {
        JdbcTemplate jdbcTemplate = factory.create(config);
        jdbcTemplate.execute("delete from " + table + " where id = " + id);
    }

    /**
     * 创建一条数据
     *
     * @param config
     * @param data
     */
    @Override
    public void createOnceData(DBConnectionConfig config, String table, Map<String, Object> data)
            throws SQLException {

        JdbcTemplate jdbcTemplate = factory.create(config);

        String sql = "insert into %s (%s) values(%s)";
        StringBuffer filed = new StringBuffer(64);
        StringBuffer value = new StringBuffer(64);
        List<Object> arg = new ArrayList<>();

        data.forEach(
                (k, v) -> {
                    filed.append("`" + k + "`" + ",");
                    value.append("?" + ",");
                    arg.add(v);
                });
        filed.deleteCharAt(filed.length() - 1);
        value.deleteCharAt(value.length() - 1);
        String format = String.format(sql, table, filed, value);
        jdbcTemplate.update(format, arg.stream().toArray());

        System.out.println(format);
    }

    /**
     * 创建数据表
     *
     * @param config
     * @param table
     */
    @Override
    public void createTable(DBConnectionConfig config, String table) {
    }

    /**
     * 删除表
     *
     * @param config
     * @param table
     */
    @Override
    public void deleteTable(DBConnectionConfig config, String table) {
    }
}
