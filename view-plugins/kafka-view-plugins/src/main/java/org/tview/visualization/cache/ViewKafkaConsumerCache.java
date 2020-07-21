package org.tview.visualization.cache;

import java.util.Properties;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.cache.LRUCache;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.tview.visualization.model.kafka.KafkaConnectionConfig;

public class ViewKafkaConsumerCache
    extends LRUCache<KafkaConnectionConfig, KafkaConsumer<byte[], byte[]>> {

  public ViewKafkaConsumerCache(int maxSize) {
    super(maxSize);
  }

  @Override
  public KafkaConsumer<byte[], byte[]> get(KafkaConnectionConfig key) {
    return super.get(key);
  }

  @Override
  public void put(KafkaConnectionConfig key, KafkaConsumer<byte[], byte[]> value) {
    if (value == null) {
      KafkaConsumer<byte[], byte[]> nv = new KafkaConsumer<byte[], byte[]>(pro(key));
      super.put(key, nv);
    } else {
      super.put(key, value);
    }
  }

  @Override
  public boolean remove(KafkaConnectionConfig key) {
    return super.remove(key);
  }

  @Override
  public long size() {
    return super.size();
  }

  private Properties pro(KafkaConnectionConfig connectionConfig) {
    Properties properties = new Properties();
    properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);
    properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ByteArrayDeserializer.class);
    properties.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 100);
    properties.setProperty(
        CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, connectionConfig.getBrokerConnect());
    return properties;
  }
}
