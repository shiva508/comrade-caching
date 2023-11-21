package com.comrade.config;

import com.comrade.entity.CacheStoreEntity;
import com.comrade.repository.CacheStoreRepository;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.internal.ascii.rest.RestValue;
import com.hazelcast.map.MapLoaderLifecycleSupport;
import com.hazelcast.map.MapStore;
import com.hazelcast.spring.context.SpringAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

@SpringAware
@Component
@Slf4j
public class ProcessingStore implements MapStore<String,Object>, MapLoaderLifecycleSupport {

    @Autowired
    private CacheStoreRepository cacheStoreRepository;

//    public ProcessingStore(CacheStoreRepository cacheStoreRepository) {
//        this.cacheStoreRepository = cacheStoreRepository;
//    }

    @Override
    public void init(@Autowired HazelcastInstance hazelcastInstance, Properties properties, String s) {
        hazelcastInstance.getConfig().getManagedContext().initialize(this);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void store(String key, Object object) {
        String value = convertRestValueToString(object);
        log.info("store key {},value {}",key,value);
        CacheStoreEntity cacheStoreEntity = new CacheStoreEntity(key,value);
        cacheStoreRepository.save(cacheStoreEntity);
    }

    @Override
    public void storeAll(Map<String, Object> map) {
        log.info("storeAll {}",map);
       var entities = map.entrySet()
                         .stream()
                         .map(cse -> new CacheStoreEntity(cse.getKey(),this.convertRestValueToString(cse.getValue())))
                         .toList();
        cacheStoreRepository.saveAll(entities);
    }

    @Override
    public void delete(String key) {
        log.info("delete {}",key);
        cacheStoreRepository.deleteById(key);
    }

    @Override
    public void deleteAll(Collection<String> collection) {
        log.info("deleteAll {}",collection);
        collection.forEach(cacheStoreRepository::deleteById);
    }

    @Override
    public Object load(String key) {
        log.info("load {}",key);
        return cacheStoreRepository.findById(key).orElse(new CacheStoreEntity(key,null)).getValue();
    }

    @Override
    public Map<String, Object> loadAll(Collection<String> collection) {
        log.info("loadAll {}",collection);
        return cacheStoreRepository.findAllById(collection).stream().collect(Collectors.toMap(CacheStoreEntity::getKey,CacheStoreEntity::getValue));
    }

    @Override
    public Iterable<String> loadAllKeys() {
        log.info("loadAllKeys");
        return cacheStoreRepository.findAll().stream().map(CacheStoreEntity::getKey).collect(Collectors.toList());
    }

    private String convertRestValueToString(Object o) {
        RestValue restValue = (RestValue) o;
        return new String(restValue.getValue());
    }

}
