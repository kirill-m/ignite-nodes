package ru.sbt.matveev.node_two;

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
    private Log log = LogFactory.getLog(IgniteProvider.class);
    private final Ignite ignite;
    private boolean started = false;

    public IgniteProvider() {
        try {
            Ignition.ignite("testGrid-client");
            started = true;
        } catch (IgniteIllegalStateException e) {
            log.debug("Using the Ignite instance that has been already started.");
        }
        if (started)
            ignite = Ignition.ignite("testGrid-client");
        else {
            ignite = Ignition.start("test-client-config.xml");
            ((TcpDiscoverySpi) ignite.configuration().getDiscoverySpi())
                    .getIpFinder()
                    .registerAddresses(Collections.singletonList(new InetSocketAddress("localhost", DFLT_PORT)));
        }
    }

    public Ignite getIgnite() {
        return ignite;
    }
}
