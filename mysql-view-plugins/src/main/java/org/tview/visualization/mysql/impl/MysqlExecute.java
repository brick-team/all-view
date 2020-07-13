package org.tview.visualization.mysql.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.tview.visualization.inter.db.SqlExecute;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.model.res.ExecuteRes;
import org.tview.visualization.mysql.factory.jdbc.JdbcFactory;
import org.tview.visualization.mysql.factory.jdbc.JdbcTemplateFactory;

/** mysql 的sql执行器 */
public class MysqlExecute implements SqlExecute {
  JdbcFactory jdbcFactory;

  @PostConstruct
  public void initJdbcFactory() {
    jdbcFactory = new JdbcTemplateFactory();
  }

  /**
   * 执行sql
   *
   * @param connectionConfig 链接配置
   * @param sql sql语句
   * @return 执行sql后的结果
   */
  @Override
  public ExecuteRes execute(DBConnectionConfig connectionConfig, String sql) {
    ExecuteRes executeRes = new ExecuteRes();
    try {
      JdbcTemplate jdbcTemplate = jdbcFactory.create(connectionConfig);
      if (sql.startsWith("select")
          || sql.startsWith("SELECT")
          || sql.startsWith("show")
          || sql.startsWith("SHOW")) {
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        convertExecuteRes(maps, executeRes);

      } else {
        jdbcTemplate.execute(sql);
      }
      executeRes.setFlag(true);
    } catch (Exception e) {
      executeRes.setFlag(false);
    }
    return executeRes;
  }

  /**
   * 转换成执行结果对象
   *
   * @param maps jdbc template 执行结果
   * @param executeRes 返回结果
   */
  private void convertExecuteRes(List<Map<String, Object>> maps, ExecuteRes executeRes) {
    if (maps.size() > 0) {
      Map<String, Object> stringObjectMap = maps.get(0);
      List<String> head = new ArrayList<>();
      stringObjectMap.forEach(
          (k, v) -> {
            head.add(k);
          });

      List<List<Object>> dat = new ArrayList<>();
      // convert
      for (Map<String, Object> map : maps) {
        List<Object> list = new ArrayList<>();

        map.forEach(
            (k, v) -> {
              list.add(v);
            });

        dat.add(list);
      }
      executeRes.setFiledName(head);
      executeRes.setShowData(dat);
    }
  }
}
