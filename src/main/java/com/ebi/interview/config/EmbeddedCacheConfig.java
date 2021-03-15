package com.ebi.interview.config;

import com.ebi.interview.constants.ApplicationConstants;
import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** The type Embedded cache config. */
@Configuration
@EnableCaching
class EmbeddedCacheConfig {

    /**
     * Config config.
     *
     * @return the config
     */
    @Value("#{new Integer('${app.cache.ttl}')}")
    private Integer ttl;

    /**
     * Config config.
     *
     * @return the config
     */
    @Bean
    Config config() {
        Config config = new Config();

        MapConfig mapConfig = new MapConfig();
        mapConfig.setTimeToLiveSeconds(ttl);
        config.getMapConfigs().put(ApplicationConstants.CACHE_NAME_PERSONS, mapConfig);

        return config;
    }
}
