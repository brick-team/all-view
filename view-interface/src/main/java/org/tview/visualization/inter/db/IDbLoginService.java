package org.tview.visualization.inter.db;

import org.tview.visualization.model.db.DBConnectionConfig;

/**
 * 数据库登录
 */
public interface IDbLoginService {

  /**
   * 连接测试
   *
   * @param config 数据库链接配置
   * @return true:连接成功,false:连接失败
   */
  boolean connection(DBConnectionConfig config);

  /**
   * 登录
   *
   * @param name
   * @param config
   */
  void login(String name, DBConnectionConfig config);

  /**
   * 获取链接配置
   *
   * @param name
   * @return
   */
  DBConnectionConfig get(String name);
}
