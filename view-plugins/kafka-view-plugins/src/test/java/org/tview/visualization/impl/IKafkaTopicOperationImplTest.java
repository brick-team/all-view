package org.tview.visualization.impl;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.tview.visualization.factory.AbsKafkaConfig;
import org.tview.visualization.inter.kafka.IKafkaTopicOperation;
import org.tview.visualization.model.kafka.CreateTopicParam;
import org.tview.visualization.model.kafka.topic.TopicVO;

import java.util.Map;

class IKafkaTopicOperationImplTest extends AbsKafkaConfig {

  IKafkaTopicOperation kafkaTopicOperation = new IKafkaTopicOperationImpl();

  @Test
  void createTopic() {
    boolean test_topic =
        kafkaTopicOperation.createTopic(
            new CreateTopicParam("test_topic", 1, (short) 1), this.config());
    Assert.assertTrue(test_topic);
    Map<String, TopicVO> topics = kafkaTopicOperation.getTopics(this.config());
    Assert.assertFalse(topics.isEmpty());

    Assert.assertTrue(topics.size() == 1);
    boolean test_topic1 = kafkaTopicOperation.deleteTopic("test_topic", this.config());
    Assert.assertTrue(test_topic1);
  }

  @Test
  void getTopics() {
    Map<String, TopicVO> topics = kafkaTopicOperation.getTopics(this.config());
    System.out.println();
  }

  @Test
  void topicInfo() {}
}
