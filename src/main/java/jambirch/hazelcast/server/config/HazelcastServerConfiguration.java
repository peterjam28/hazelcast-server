package jambirch.hazelcast.server.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class HazelcastServerConfiguration {
    @Bean
    @Profile("udp")
    public Config udpConfig() {
        return new Config();
    }

    @Bean
    @Profile("tcp")
    public Config tcpConfig(@Value("${tcp.member:127.0.0.1}") String tcpMember) {
        Config hazelcastConfig = new Config();
        JoinConfig joinCfg = hazelcastConfig.getNetworkConfig().getJoin();
        joinCfg.getMulticastConfig().setEnabled(false);
        joinCfg.getTcpIpConfig().setEnabled(true).addMember(tcpMember);

        return hazelcastConfig;
    }
}
