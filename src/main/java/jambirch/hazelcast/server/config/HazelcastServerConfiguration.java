package jambirch.hazelcast.server.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.NetworkConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastServerConfiguration {

    @Bean
    public Config config() {
        Config hazelcastConfig = new Config();
        NetworkConfig netConf = hazelcastConfig.getNetworkConfig();
        JoinConfig joinCfg = netConf.getJoin();
        joinCfg.getMulticastConfig().setEnabled(false);
        joinCfg.getTcpIpConfig().setEnabled(true).addMember("127.0.0.1");

        return hazelcastConfig;
    }
}
