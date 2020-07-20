package org.tview.visualization.singlet;

import org.tview.visualization.cache.ViewKafkaAdminClientCache;
import org.tview.visualization.cache.ViewKafkaConsumerCache;
import org.tview.visualization.cache.ViewKafkaProducerCache;

public class KafkaSinglet {

  private static ViewKafkaConsumerCache viewKafkaConsumerCache = null;
  private static ViewKafkaAdminClientCache viewKafkaAdminClientCache = null;
  private static ViewKafkaProducerCache viewKafkaProducerCache = null;

  public static ViewKafkaProducerCache getViewKafkaProducerCache() {
    if (viewKafkaProducerCache == null) {
      synchronized (KafkaSinglet.class) {
        if (viewKafkaProducerCache == null) {
          viewKafkaProducerCache = new ViewKafkaProducerCache(10);
        }
      }
    }
    return viewKafkaProducerCache;
  }

  public static ViewKafkaAdminClientCache getViewKafkaAdminClientCache() {
    if (viewKafkaAdminClientCache == null) {
      synchronized (KafkaSinglet.class) {
        if (viewKafkaAdminClientCache == null) {
          viewKafkaAdminClientCache = new ViewKafkaAdminClientCache(10);
        }
      }
    }
    return viewKafkaAdminClientCache;
  }

  public static ViewKafkaConsumerCache getViewKafkaConsumerCache() {
    if (viewKafkaConsumerCache == null) {
      synchronized (KafkaSinglet.class) {
        if (viewKafkaConsumerCache == null) {
          viewKafkaConsumerCache = new ViewKafkaConsumerCache(10);
        }
      }
    }
    return viewKafkaConsumerCache;
  }
}
