package org.tview.visualization.cache;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.common.cache.LRUCache;
import org.tview.visualization.model.kafka.KafkaConnectionConfig;

import java.util.Properties;

public class ViewKafkaAdminClientCache extends LRUCache<KafkaConnectionConfig, AdminClient> {

  public ViewKafkaAdminClientCache(int maxSize) {
    super(maxSize);
  }

  @Override
  public void put(KafkaConnectionConfig key, AdminClient value) {
    if (value == null) {
      AdminClient adminClient = AdminClient.create(pro(key));
      super.put(key, adminClient);
    } else {

      super.put(key, value);
    }
  }

  private Properties pro(KafkaConnectionConfig kafkaConnectionConfig) {
    Properties properties = new Properties();
    properties.setProperty(
        CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, kafkaConnectionConfig.getBrokerConnect());
    return properties;
  }
}
