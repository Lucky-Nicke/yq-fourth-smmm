package com.lanxige.listener;

import com.alibaba.fastjson.JSON;
import com.lanxige.goods.model.Sku;
import com.lanxige.search.feign.SkuSearchFeign;
import com.lanxige.search.model.SkuEs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

@Component
@CanalTable(value = "sku")
public class SkuHandler implements EntryHandler<Sku> {
    @Autowired
    private SkuSearchFeign skuSearchFeign;

    @Override
    public void insert(Sku sku) {
        if (sku.getStatus() == 1){
            String jsonString = JSON.toJSONString(sku);
            skuSearchFeign.add(JSON.parseObject(jsonString, SkuEs.class));
        }
    }

    @Override
    public void update(Sku before, Sku after) {
        if(after.getStatus() == 2){
            skuSearchFeign.del(after.getId());
        }else{
            skuSearchFeign.add(JSON.parseObject(JSON.toJSONString(after), SkuEs.class));
        }
    }

    @Override
    public void delete(Sku sku) {
        skuSearchFeign.del(sku.getId());
    }
}
