package org.tview.visualization.cache;

/**
 * 缓存接口
 *
 * @param <T> 实体对象
 */
public interface CacheInterface<T> {

  /**
   * 缓存容量
   *
   * @return 缓存容量
   */
  int size();

  /**
   * 设置缓存
   *
   * @param key key
   * @param value value
   */
  void put(String key, T value);

  /**
   * 获取缓存
   *
   * @param key key
   * @return value
   */
  T get(String key);
}
