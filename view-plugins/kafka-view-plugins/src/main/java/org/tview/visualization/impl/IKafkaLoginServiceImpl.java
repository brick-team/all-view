package org.tview.visualization.impl;

import org.tview.visualization.inter.ConfigLoginService;
import org.tview.visualization.model.kafka.KafkaConnectionConfig;

public class IKafkaLoginServiceImpl implements ConfigLoginService<KafkaConnectionConfig> {

  /**
   * 获取配置对象
   *
   * @param name 自定义名称
   * @return 配置
   */
  @Override
  public KafkaConnectionConfig get(String name) {
    return null;
  }

  /**
   * 是否链接成功
   *
   * @param kafkaConnectionConfig 配置对象
   * @return true:连接成功,false:连接失败
   */
  @Override
  public boolean connection(KafkaConnectionConfig kafkaConnectionConfig) {
    return false;
  }

  /**
   * 登记配置
   *
   * @param name                  自定义名称
   * @param kafkaConnectionConfig 配置
   */
  @Override
  public void login(String name, KafkaConnectionConfig kafkaConnectionConfig) {

  }
}
