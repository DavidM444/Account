package com.manager.revenuemanager.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * Configuracion de la cache de Redis con su respectivo ObjectMapper que soporte Serializacion para objetos java.time de java 8
 */
@Configuration
@EnableCaching
public class CacheConfig {

    //este método configura redis como cache, especificando un serializador para los valores, asi como un TTL(time to live) para
    // las keys
    @Bean
    public CacheManager cacheRedisManager(RedisConnectionFactory redisConnectionFactory){
        System.out.println("configurando cachemanager");

        /**
         * Aviso Importante: El error de serialización para objetos java/time de Java 8, en esta configuración para redis, se produce porque
         * en esta configuración de beans, el nuevo JavaTimeModule:(Este soporta serialización para objetos time) configurado para el
         * objectMapper no está siendo usado correctamente en la configuración de RedisCacheConfiguration, ya que por lo general usamos GenericJackson2JsonRedisSerializer(),
         * pero este serializador necesita usar el nuevo ObjectMapper configurado, antes de buildear su configuración, asi, queda el serializador
         *  new GenericJackson2JsonRedisSerializer(objectMapperWithNewJavaTimeModule) -> Usa el mapper configurado
         *
         */

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(1)).serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(
                        new GenericJackson2JsonRedisSerializer(objectMapperWithNewJavaTimeModule())));

        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration).transactionAware().build();
    }


    /**
     * @return ObjectMapper con modulo JavatimeModule
     */
    @Bean
    @Primary
    public ObjectMapper objectMapperWithNewJavaTimeModule() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;

        // Otra configuración del mapper menos personalizable

      /*  return Jackson2ObjectMapperBuilder.json()
                .modules(new JavaTimeModule())  // Asegura compatibilidad con fechas de Java 8
                .build();*/
    }



}
