package com.dcoj;

import com.dcoj.service.CacheService;
import com.dcoj.service.IdGenerateCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author Leon
 * 初始化器,在应用加载完成后加载缓存
 */
@Component
public class ENVApplicationRunner implements ApplicationRunner {

    @Autowired
    private CacheService cacheService;

    @Autowired
    private IdGenerateCacheService idGenerateCacheService;

    /**
     * Callback used to run the bean.
     *
     * @param args incoming application arguments
     * @throws Exception on error
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("==========应用启动成功，正在加载缓存==========");
//        cacheService.reloadCache("permissionCache");
        idGenerateCacheService.initIdGenerateCache();
        System.out.println("=================缓存加载成功=================");
    }
}
