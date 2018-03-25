package ru.sbt.matveev.node_one;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;

public class ServerNode {

    public static void main(String[] args) {
        Ignite serverNode = IgniteProvider.getServerNode();

        IgniteCache<Object, Object> cache = serverNode.getOrCreateCache(CacheUtils.CACHE_ONE);
        for (int i = 0; i < 10; i++) {
            System.out.println("Cache values:" + cache.get(i));
        }

    }
}
