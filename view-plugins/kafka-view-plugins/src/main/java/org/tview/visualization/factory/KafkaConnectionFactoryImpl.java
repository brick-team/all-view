package org.tview.visualization.factory;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tview.visualization.cache.ViewKafkaAdminClientCache;
import org.tview.visualization.cache.ViewKafkaConsumerCache;
import org.tview.visualization.cache.ViewKafkaProducerCache;
import org.tview.visualization.model.kafka.KafkaConnectionConfig;
import org.tview.visualization.singlet.KafkaSinglet;

public class KafkaConnectionFactoryImpl implements KafkaConnectionFactory {

  protected Logger log = LoggerFactory.getLogger(KafkaConnectionFactoryImpl.class);

  @Override
  public AdminClient factoryAdminClient(KafkaConnectionConfig kafkaConnectionConfig) {
    ViewKafkaAdminClientCache viewKafkaAdminClientCache = KafkaSinglet.getViewKafkaAdminClientCache();
    AdminClient adminClient = viewKafkaAdminClientCache.get(kafkaConnectionConfig);
    if (adminClient == null) {
      log.info("该配置不在缓存,conf = [{}]", kafkaConnectionConfig);
      viewKafkaAdminClientCache.put(kafkaConnectionConfig, null);
    }
    return viewKafkaAdminClientCache.get(kafkaConnectionConfig);
  }

  @Override
  public KafkaConsumer<byte[], byte[]> factoryConsumer(KafkaConnectionConfig kafkaConnectionConfig) {
    ViewKafkaConsumerCache viewKafkaConsumerCache = KafkaSinglet.getViewKafkaConsumerCache();
    KafkaConsumer<byte[], byte[]> kafkaConsumer = viewKafkaConsumerCache.get(kafkaConnectionConfig);
    if (kafkaConsumer == null) {
      log.info("该配置不在缓存,conf = [{}]", kafkaConnectionConfig);
      viewKafkaConsumerCache.put(kafkaConnectionConfig, null);
    }
    return viewKafkaConsumerCache.get(kafkaConnectionConfig);
  }

  @Override
  public KafkaProducer<byte[], byte[]> factoryProducer(KafkaConnectionConfig kafkaConnectionConfig) {
    ViewKafkaProducerCache viewKafkaProducerCache = KafkaSinglet.getViewKafkaProducerCache();
    KafkaProducer<byte[], byte[]> kafkaProducer = viewKafkaProducerCache.get(kafkaConnectionConfig);
    if (kafkaProducer == null) {
      log.info("该配置不在缓存,conf = [{}]", kafkaConnectionConfig);
      viewKafkaProducerCache.put(kafkaConnectionConfig, null);
    }
    return viewKafkaProducerCache.get(kafkaConnectionConfig);
  }
}
