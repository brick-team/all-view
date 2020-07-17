package org.tview.visualization.inter;

import org.tview.visualization.model.label.AbsConfig;

/**
 * 配置登记
 *
 * @param <T> 配置信息对象
 * @see AbsConfig
 */
public interface ConfigLoginService<T extends AbsConfig> {

  /**
   * 获取配置对象
   *
   * @param name 自定义名称
   * @return 配置
   */
  T get(String name);

  /**
   * 是否链接成功
   *
   * @param t 配置对象
   * @return true:连接成功,false:连接失败
   */
  boolean connection(T t);

  /**
   * 登记配置
   *
   * @param name 自定义名称
   * @param t 配置
   */
  void login(String name, T t);
}
