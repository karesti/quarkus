package io.quarkus.test.infinispan;

import java.util.Collections;
import java.util.Map;

import org.infinispan.server.test.core.ContainerInfinispanServerDriver;
import org.infinispan.server.test.core.InfinispanGenericContainer;
import org.infinispan.server.test.core.ServerRunMode;
import org.infinispan.server.test.core.TestServer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class InfinispanTestResource implements QuarkusTestResourceLifecycleManager {

    private static TestServer testServer;

    @Override
    public Map<String, String> start() {
        testServer = new TestServer(new QuarkusTestInfinispanConfigBuilder()
                .runMode(ServerRunMode.CONTAINER)
                .numServers(1)
                .build());
        testServer.initServerDriver();
        testServer.getDriver().start("infinispan-test-resource");
        ContainerInfinispanServerDriver container = (ContainerInfinispanServerDriver) testServer.getDriver();
        InfinispanGenericContainer container1 = container.getContainer(1);

        final String hosts = container1.getIpAddress() + ":" + container1.getMappedPort(11222);

        return Collections.singletonMap("quarkus.infinispan-client.server-list", hosts);
    }

    @Override
    public void stop() {
        testServer.getDriver().stop("infinispan-test-resource");
    }
}
