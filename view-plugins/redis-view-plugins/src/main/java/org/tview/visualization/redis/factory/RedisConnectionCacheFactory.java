package org.tview.visualization.redis.factory;

import org.springframework.data.redis.core.RedisTemplate;
import org.tview.visualization.model.redis.RedisConnectionConfig;

public interface RedisConnectionCacheFactory {

  RedisTemplate factory(RedisConnectionConfig config);
}
