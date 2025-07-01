package org.example.locationservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

// Marks this class as a source of bean definitions for the application context
@Configuration
public class RedisConfig {

    // Defines and exposes a RedisConnectionFactory bean
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        // Create a new JedisConnectionFactory instance
        JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
        // Set the Redis server hostname (default: localhost)
        jedisConFactory.setHostName("localhost");
        // Set the Redis server port (default: 6379)
        jedisConFactory.setPort(6379);
        // Return the configured factory for connection management
        return jedisConFactory;
    }

    // Defines and exposes a RedisTemplate bean for String key/value operations
    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        // Instantiate a RedisTemplate with String key/value types
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        // Set the connection factory to the one defined above
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        // Use String serializer for keys to ensure human-readable keys
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // Use String serializer for values for consistent data format
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        // Return the fully-configured RedisTemplate bean
        return redisTemplate;
    }
}
