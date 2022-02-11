/*
 * package com.softserve.betterlearningroom.configuration;
 * 
 * import com.google.common.cache.Cache; import
 * com.google.common.cache.CacheBuilder;
 * 
 * import org.springframework.cache.annotation.CachingConfigurerSupport; import
 * org.springframework.context.annotation.Bean; import
 * org.springframework.stereotype.Component;
 * 
 * import java.util.concurrent.TimeUnit;
 * 
 * 
 * @Component public class SimpleCacheCustomizer extends
 * CachingConfigurerSupport {
 * 
 * @Bean public Cache cacheOne() { return new Cache("confirmation_tokens",
 * CacheBuilder.newBuilder() .expireAfterWrite(60, TimeUnit.MINUTES) .build());
 * } }
 */
