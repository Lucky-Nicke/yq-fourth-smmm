package com.lanxige.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lanxige.goods.model.Sku;

import java.util.List;

public interface SkuService extends IService<Sku> {
    List<Sku> typeSkuItems(Integer id);
    void deleteAdItems(Integer id);
    List<Sku> updateAditemsByTypeId(Integer type);
}
