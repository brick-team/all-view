package org.tview.visualization.inter.redis;

import java.util.List;
import java.util.Properties;
import org.tview.visualization.model.redis.RedisConnectionConfig;
import org.tview.visualization.model.redis.RedisInfo;
import org.tview.visualization.model.redis.info.RedisCliInfoClients;
import org.tview.visualization.model.redis.info.RedisCliInfoCluster;
import org.tview.visualization.model.redis.info.RedisCliInfoCpu;
import org.tview.visualization.model.redis.info.RedisCliInfoKeyspace;
import org.tview.visualization.model.redis.info.RedisCliInfoMemory;
import org.tview.visualization.model.redis.info.RedisCliInfoPersistence;
import org.tview.visualization.model.redis.info.RedisCliInfoReplication;
import org.tview.visualization.model.redis.info.RedisCliInfoServer;
import org.tview.visualization.model.redis.info.RedisCliInfoStats;
import org.tview.visualization.model.res.redis.RedisTreeInfo;

/**
 * redis 服务相关信息
 */
public interface IRedisServerInfo {

  /**
   * 版本
   *
   * @return
   */
  String version(RedisConnectionConfig config);

  /**
   * 已使用内存
   *
   * @return
   */
  long useMemory(RedisConnectionConfig config);

  /**
   * 客户端数量
   *
   * @return
   */
  int client(RedisConnectionConfig config);

  /**
   * 已执行的命令
   *
   * @return
   */
  long execSize(RedisConnectionConfig config);

  /**
   * 运行时间
   *
   * @return
   */
  long runTime(RedisConnectionConfig config);

  /**
   * 服务器信息
   *
   * @return
   */
  Properties conf(RedisConnectionConfig config);

  RedisCliInfoServer server(RedisConnectionConfig config);

  RedisCliInfoClients clients(RedisConnectionConfig config);

  RedisCliInfoMemory memory(RedisConnectionConfig config);

  RedisCliInfoPersistence persistence(RedisConnectionConfig config);

  RedisCliInfoStats stats(RedisConnectionConfig config);

  RedisCliInfoReplication replication(RedisConnectionConfig config);

  RedisCliInfoCpu cpu(RedisConnectionConfig config);

  RedisCliInfoCluster cluster(RedisConnectionConfig config);

  List<RedisCliInfoKeyspace> keyspace(RedisConnectionConfig config);

  RedisInfo info(RedisConnectionConfig config);

  int databases(RedisConnectionConfig config);

  RedisTreeInfo dataBaseList(RedisConnectionConfig config, String name);
}
