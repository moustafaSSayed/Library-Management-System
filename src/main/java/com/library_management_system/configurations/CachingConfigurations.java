package com.library_management_system.configurations;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfigurations {
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("books", "patrons","borrowingRecords");
    }
}
