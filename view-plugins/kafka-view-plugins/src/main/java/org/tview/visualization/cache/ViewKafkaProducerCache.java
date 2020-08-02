package org.tview.visualization.cache;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.cache.LRUCache;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.tview.visualization.model.kafka.KafkaConnectionConfig;

import java.util.Properties;

public class ViewKafkaProducerCache
    extends LRUCache<KafkaConnectionConfig, KafkaProducer<byte[], byte[]>> {

  public ViewKafkaProducerCache(int maxSize) {
    super(maxSize);
  }

  @Override
  public void put(KafkaConnectionConfig key, KafkaProducer<byte[], byte[]> value) {
    if (value == null) {
      KafkaProducer<byte[], byte[]> producer = new KafkaProducer<>(pro(key));
      super.put(key, producer);
    } else {

      super.put(key, value);
    }
  }

  private Properties pro(KafkaConnectionConfig connectionConfig) {
    Properties properties = new Properties();
    properties.setProperty(
        CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, connectionConfig.getBrokerConnect());
    properties.put(ProducerConfig.ACKS_CONFIG, "-1");
    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);
    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);
    return properties;
  }
}
