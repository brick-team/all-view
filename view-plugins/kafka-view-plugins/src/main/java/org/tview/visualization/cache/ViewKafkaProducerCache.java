package org.tview.visualization.cache;

import java.util.Properties;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.cache.LRUCache;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.tview.visualization.model.kafka.KafkaConnectionConfig;

public class ViewKafkaProducerCache extends LRUCache<KafkaConnectionConfig, KafkaProducer<byte[], byte[]>> {

  public ViewKafkaProducerCache(int maxSize) {
    super(maxSize);
  }

  @Override
  public KafkaProducer<byte[], byte[]> get(KafkaConnectionConfig key) {
    return super.get(key);
  }

  @Override
  public void put(KafkaConnectionConfig key, KafkaProducer<byte[], byte[]> value) {
    if (value == null) {
      KafkaProducer<byte[], byte[]> producer = new KafkaProducer<>(pro(key));
      super.put(key, producer);
    }
    super.put(key, value);
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
    properties.setProperty(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, connectionConfig.getBrokerConnect());
    properties.put(ProducerConfig.ACKS_CONFIG, "-1");
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);
    return properties;
  }
}
