package com.lanxige.goods.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lanxige.goods.mapper.AdItemsMapper;
import com.lanxige.goods.mapper.SkuMapper;
import com.lanxige.goods.service.SkuService;
import com.lanxige.model.AdItems;
import com.lanxige.model.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@CacheConfig(cacheNames = "ad-item-skus")
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {
    @Autowired
    private AdItemsMapper adItemsMapper;

    @Cacheable(key = "#id") //查询到的缓存并存入，并且会检测是否有数据，没有数据就查询数据库
//    @CachePut(value = "ad-item-skus", key = "#id")  //不会查询缓存但是会把数据存入缓存
    @Override
    public List<Sku> typeSkuItems(Integer id) {
        QueryWrapper<AdItems> adItemsQueryWrapper = new QueryWrapper<>();
        adItemsQueryWrapper.eq("type", id);
        List<AdItems> adItems = adItemsMapper.selectList(adItemsQueryWrapper);

        if (!adItems.isEmpty()) {
            List<String> list = adItems.stream().map(adItem -> adItem.getSkuId()).collect(Collectors.toList());
            List<Sku> skus = baseMapper.selectBatchIds(list);
            System.out.println("查询了数据库！！！");
            return skus;
        } else {
            return null;
        }
    }

    @CacheEvict(key = "#id")
    @Override
    public void deleteAdItems(Integer id) {
        System.out.println("用 @CacheEvict 注解 执行了删除redis缓存操作....");
    }

    // 缓存修改
    @CachePut(key = "#id")
    @Override
    public List<Sku> updateAditemsByTypeId(Integer id) {
        QueryWrapper<AdItems> adItemsQueryWrapper = new QueryWrapper<>();
        adItemsQueryWrapper.eq("type", id);
        List<AdItems> adItems = adItemsMapper.selectList(adItemsQueryWrapper);

        if (!adItems.isEmpty()) {
            List<String> list = adItems.stream().map(adItem -> adItem.getSkuId()).collect(Collectors.toList());
            List<Sku> skus = baseMapper.selectBatchIds(list);
            System.out.println("查询了数据库！！！");
            return skus;
        } else {
            return null;
        }
    }
}




