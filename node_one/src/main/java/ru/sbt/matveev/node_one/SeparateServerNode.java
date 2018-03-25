package ru.sbt.matveev.node_one;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;

public class SeparateServerNode {
    public static void main(String[] args) {
        Ignite serverNode = IgniteProvider.getServerNode(CacheUtils.SEPARATE_SERVER_CONFIG);

        IgniteCache<Object, Object> cache = serverNode.getOrCreateCache(CacheUtils.CACHE_ONE + 1);
        String key = "KEY";
        cache.put(key, "VALUE SAVED ON SERVER NODE");

        System.out.println("Read from server: " + cache.get(key));
    }
}
