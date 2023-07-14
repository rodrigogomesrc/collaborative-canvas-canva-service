package br.ufrn.dimap.collaborativecanvas.reativecanvaservice.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String cacheHost;

    @Value("${spring.data.redis.port}")
    private  int cachePort;

    @Value("${spring.data.redis.password}")
    private String cachePassword;

    @Bean
    public RedissonReactiveClient getClient(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://"+ cacheHost + ":" + cachePort)
        .setPassword(cachePassword);
        return Redisson.create(config).reactive();
    }
}
