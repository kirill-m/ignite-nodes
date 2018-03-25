package ru.sbt.matveev.node_one;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteIllegalStateException;
import org.apache.ignite.Ignition;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.util.Collections;

import static org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi.DFLT_PORT;

@Configuration
public class IgniteProvider {
    private static final Log LOG = LogFactory.getLog(IgniteProvider.class);
    private final Ignite ignite;

    public IgniteProvider() {
        boolean started = false;
        try {
            Ignition.ignite("testGrid-client");
            started = true;
        } catch (IgniteIllegalStateException e) {
            LOG.debug("Using the Ignite instance that has been already started.");
        }
        if (started)
            ignite = Ignition.ignite("testGrid-client");
        else {
            ignite = Ignition.start(CacheUtils.CLIENT_CONFIG);
            ((TcpDiscoverySpi) ignite.configuration().getDiscoverySpi())
                    .getIpFinder()
                    .registerAddresses(Collections.singletonList(new InetSocketAddress("localhost", DFLT_PORT)));
        }
    }

    static Ignite getServerNode() {
        return Ignition.start(CacheUtils.SERVER_CONFIG);
    }

    static Ignite getServerNode(String configFile) {
        return Ignition.start(configFile);
    }

    Ignite getIgnite() {
        return ignite;
    }
}
