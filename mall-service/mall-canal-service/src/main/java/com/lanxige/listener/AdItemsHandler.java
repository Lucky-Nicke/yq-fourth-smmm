package com.lanxige.listener;

import com.lanxige.goods.feign.SkuFeign;
import com.lanxige.goods.model.AdItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@CanalTable(value = "ad_items")
@Component
public class AdItemsHandler implements EntryHandler<AdItems> {
    @Autowired
    private SkuFeign skuFeign;

    @Autowired
    private RestTemplate restTemplate;

    private static final String NGINX_PURGE_URL = "http://192.168.239.130/purge/sku/aditems/type?id=";

    @Override
    //数据库的数据发生 增加 操作会触发该方法
    public void insert(AdItems adItems) {
        System.out.println("public void insert(AdItems adItems) 执行了:"+adItems);
        // 重新加载缓存
        this.skuFeign.updateTypeItems(adItems.getType());
        String purgeUrl = NGINX_PURGE_URL + adItems.getType();
        restTemplate.getForObject(purgeUrl, String.class);
    }

    @Override
    //数据库的数据发生 修改 操作，会触发该方法
    public void update(AdItems before, AdItems after) {
        System.out.println("public void update"+before);
        System.out.println("public void update"+after);
        if (before.getType().intValue()!=after.getType().intValue()){
            // 重新加载变更前分类的Id对应的推广产品
            this.skuFeign.updateTypeItems(before.getType());
            String oldPurgeUrl = NGINX_PURGE_URL + before.getType();
            System.out.println(oldPurgeUrl);
            restTemplate.getForObject(oldPurgeUrl, String.class);
        }
        // 重新加载缓存
        this.skuFeign.updateTypeItems(after.getType());
        String newPurgeUrl = NGINX_PURGE_URL + after.getType();
        restTemplate.getForObject(newPurgeUrl, String.class);
    }

    @Override
    //数据库的数据发生 删除 操作，会触发该方法
    public void delete(AdItems adItems) {
        System.out.println("public void delete(AdItems adItems) 执行了: "+adItems);
        this.skuFeign.deleteTypeItems(adItems.getType());
        String purgeUrl = NGINX_PURGE_URL + adItems.getType();
        restTemplate.getForObject(purgeUrl, String.class);
    }
}
