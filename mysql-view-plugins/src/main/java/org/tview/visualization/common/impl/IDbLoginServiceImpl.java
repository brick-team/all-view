package org.tview.visualization.common.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.tview.visualization.common.cache.DblLoginCache;
import org.tview.visualization.inter.db.IDbLoginService;
import org.tview.visualization.model.db.DBConnectionConfig;
import org.tview.visualization.mysql.factory.jdbc.JdbcTemplateCreate;
import org.tview.visualization.mysql.factory.jdbc.MysqlJdbcTemplateCreate;

public class IDbLoginServiceImpl implements IDbLoginService {

  DblLoginCache cache = new DblLoginCache(10);
  //
  JdbcTemplateCreate mysql = new MysqlJdbcTemplateCreate();

  /**
   * 连接测试
   *
   * @param config 数据库链接配置
   * @return true:连接成功,false:连接失败
   */
  @Override
  public boolean connection(DBConnectionConfig config) {
    try {
      JdbcTemplate jdbcTemplate = mysql.create(config);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 登录
   *
   * @param name
   * @param config
   */
  @Override
  public void login(String name, DBConnectionConfig config) {
    if (connection(config)) {
      cache.put(name, config);
    }
  }

  /**
   * 获取链接配置
   *
   * @param name
   * @return
   */
  @Override
  public DBConnectionConfig get(String name) {
    return cache.get(name);
  }
}
