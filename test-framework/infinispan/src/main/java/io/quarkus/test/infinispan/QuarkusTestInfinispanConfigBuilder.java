package io.quarkus.test.infinispan;

import static org.infinispan.server.test.core.AbstractInfinispanServerDriver.DEFAULT_CLUSTERED_INFINISPAN_CONFIG_FILE_NAME;

import org.infinispan.server.test.core.AbstractServerConfigBuilder;
import org.infinispan.server.test.core.InfinispanServerTestConfiguration;
import org.infinispan.server.test.core.ServerRunMode;

public class QuarkusTestInfinispanConfigBuilder extends
        AbstractServerConfigBuilder<QuarkusTestInfinispanConfigBuilder> {

    public QuarkusTestInfinispanConfigBuilder() {
        super(DEFAULT_CLUSTERED_INFINISPAN_CONFIG_FILE_NAME, true);
        runMode(ServerRunMode.CONTAINER);
    }

    public InfinispanServerTestConfiguration build() {
        return createServerTestConfiguration();
    }

}
