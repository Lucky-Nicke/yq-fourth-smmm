package com.lanxige.goods.service.Impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lanxige.goods.mapper.BrandMapper;
import com.lanxige.goods.mapper.CategoryMapper;
import com.lanxige.goods.mapper.SkuMapper;
import com.lanxige.goods.mapper.SpuMapper;
import com.lanxige.goods.service.SpuService;
import com.lanxige.model.*;
import com.lanxige.vo.SpuAuditStatus;
import com.lanxige.vo.SpuSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu> implements SpuService {
    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public void saveProduct(Product product) {
        //Spu
        Spu spu = product.getSpu();

        //如果ID为空，则增加
        if (StringUtils.isEmpty(spu.getId())) {
            //上架
            spu.setIsMarketable(1);
            //未删除
            spu.setIsDelete(0);
            //状态
            spu.setStatus(1);
            //添加
            spuMapper.insert(spu);
        } else {
            //ID 不为空，则修改
            spuMapper.updateById(spu);
            //删除之前的Sku记录
            skuMapper.delete(new QueryWrapper<Sku>().eq("spu_id", spu.getId()));
        }

        //查询三级分类
        Category category = categoryMapper.selectById(spu.getCategoryThreeId());
        //查询品牌
        Brand brand = brandMapper.selectById(spu.getBrandId());
        //当前时间
        Date now = new Date();

        //新增Sku集合
        for (Sku sku : product.getSkus()) {
            //设置名字
            String skuName = spu.getName();
            Map<String, String> attrMap = JSON.parseObject(sku.getSkuAttribute(), Map.class);
            for (Map.Entry<String, String> entry : attrMap.entrySet()) {
                skuName += "   " + entry.getValue();
            }
            sku.setName(skuName);
            //设置图片
            sku.setImages(spu.getImages());
            //设置状态
            sku.setStatus(1);
            //设置类目ID
            sku.setCategoryId(spu.getCategoryThreeId());
            //设置类目名称
            sku.setCategoryName(category.getName());
            //设置品牌ID
            sku.setBrandId(brand.getId());
            //设置品牌名称
            sku.setBrandName(brand.getName());
            //设置Spuid
            sku.setSpuId(spu.getId());
            //时间
            sku.setCreateTime(now);
            sku.setUpdateTime(now);
            //增加
            skuMapper.insert(sku);
        }
    }

    @Override
    public Page<Spu> queryPageList(int page, int size, SpuSearch spuSearch) {
        QueryWrapper<Spu> spuQueryWrapper = new QueryWrapper<>();
        if (spuSearch != null) {
            if (spuSearch.getCategory1Id() != null) {
                spuQueryWrapper.eq("category_one_id", spuSearch.getCategory1Id());
            }
            if (spuSearch.getCategory2Id() != null) {
                spuQueryWrapper.eq("category_two_id", spuSearch.getCategory2Id());
            }
            if (spuSearch.getCategory3Id() != null) {
                spuQueryWrapper.eq("category_three_id", spuSearch.getCategory3Id());
            }
            if (StringUtils.isNotEmpty(spuSearch.getName())) {
                spuQueryWrapper.like("name", spuSearch.getName());
            }
            if (spuSearch.getBrandId() != null) {
                spuQueryWrapper.eq("brand_id", spuSearch.getBrandId());
            }
            if (spuSearch.getStatus() != null) {
                switch (spuSearch.getStatus()) {
                    case 0:
                        spuQueryWrapper.eq("status", 0);
                        break;
                    case 1:
                        spuQueryWrapper.eq("status", 1);
                        break;
                    case 2:
                        spuQueryWrapper.eq("status", 2);
                        break;
                }
            }
            if (spuSearch.getIsMarketable() != null) {
                switch (spuSearch.getIsMarketable()) {
                    case 0:
                        spuQueryWrapper.eq("is_marketable", 0);
                        break;
                    case 1:
                        spuQueryWrapper.eq("is_marketable", 1);
                        break;
                }
            }
            Page<Spu> spuPage = baseMapper.selectPage(new Page<>(page, size), spuQueryWrapper);
            return spuPage;
        }
        Page<Spu> spuPage = baseMapper.selectPage(new Page<>(page, size), null);
        return spuPage;
    }

    @Override
    public Boolean auditStatus(SpuAuditStatus spuAuditStatus) {
        Spu spu = new Spu();
        spu.setId(spuAuditStatus.getId());
        spu.setStatus(spuAuditStatus.getStatus());
        int update = baseMapper.updateById(spu);
        return update > 0;
    }
}




