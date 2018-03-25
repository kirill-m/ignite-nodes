package ru.sbt.matveev.node_one;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;

public class ClientNode {
    public static void main(String[] args) {
        IgniteProvider igniteProvider = new IgniteProvider();
        Ignite ignite = igniteProvider.getIgnite();
        IgniteCache<Object, Object> cache = ignite.getOrCreateCache(CacheUtils.CACHE_ONE);

        for (int i = 0; i < 10; i++) {
            cache.put(i, "value" + i);
        }
    }
}
