package cn.xcloude.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 146
 * LRU 缓存
 */
public class LRUCache {
  private final LRUCache0<Integer, Integer> cache;

  public LRUCache(int capacity) {
    cache = new CustomLRUCache<>(capacity);
  }

  public int get(int key) {
    Integer value = cache.get(key);
    return value == null ? -1 : value;
  }

  public void put(int key, int value) {
    cache.put(key, value);
  }

  private interface LRUCache0<K, V> {
    V put(K key, V value);

    V get(K key);
  }

  private static final class CustomLRUCache<K, V> implements LRUCache0<K, V> {
    private final int capacity;
    private final Map<K, CacheValue<K, V>> cache;

    private CacheValue<K, V> head;
    private CacheValue<K, V> tail;

    public CustomLRUCache(int capacity) {
      this.capacity = capacity;
      this.cache = new HashMap<>(capacity);
    }

    @Override
    public V put(K key, V value) {
      CacheValue<K, V> cacheValue = cache.get(key);
      if (cacheValue == null) {
        cacheValue = new CacheValue<>(key, value);
        cache.put(key, cacheValue);
        insert(cacheValue);
        expire();
        return value;
      } else {
        V oldValue = cacheValue.value;
        cacheValue.value = value;
        access(cacheValue);
        return oldValue;
      }
    }

    private void insert(CacheValue<K, V> cacheValue) {
      if (tail == null) {
        head = cacheValue;
      } else {
        tail.after = cacheValue;
        cacheValue.before = tail;
      }

      tail = cacheValue;
    }

    private void access(CacheValue<K, V> cacheValue) {
      if (cacheValue == tail) {
        return;
      }

      if (cacheValue == head) {
        head = cacheValue.after;
        head.before = null;
      } else {
        cacheValue.before.after = cacheValue.after;
        cacheValue.after.before = cacheValue.before;
      }

      tail.after = cacheValue;
      cacheValue.before = tail;
      cacheValue.after = null;
      tail = cacheValue;
    }

    private void expire() {
      if (cache.size() <= capacity) {
        return;
      }

      cache.remove(head.key);
      if (head == tail) {
        head = null;
        tail = null;
      } else {
        head.after.before = null;
        head = head.after;
      }
    }

    @Override
    public V get(K key) {
      CacheValue<K, V> cacheValue = cache.get(key);
      if (cacheValue == null) {
        return null;
      }

      access(cacheValue);
      return cacheValue.value;
    }

    private static final class CacheValue<K, V> {
      private final K key;
      private V value;
      private CacheValue<K, V> before;
      private CacheValue<K, V> after;

      private CacheValue(K key, V value) {
        this.key = key;
        this.value = value;
      }
    }
  }

  private static final class LRUCacheViaLinkedHashMap<K, V>
      extends LinkedHashMap<K, V> implements LRUCache0<K, V> {
    private final int size;

    public LRUCacheViaLinkedHashMap(int size) {
      super(size, 0.75f, true);
      this.size = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
      return size() > size;
    }
  }
}
