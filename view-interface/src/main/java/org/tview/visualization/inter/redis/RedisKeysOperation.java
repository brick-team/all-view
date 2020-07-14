package org.tview.visualization.inter.redis;

import java.util.List;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.model.redis.RedisKeyInfo;

public interface RedisKeysOperation {

  List<RedisKeyInfo> keys(RedisConnectionConfig config, String keyRegion);
}

