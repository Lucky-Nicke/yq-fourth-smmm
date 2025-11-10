package com.lanxige.goods.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lanxige.goods.mapper.CategoryAttrMapper;
import com.lanxige.goods.mapper.SkuAttributeMapper;
import com.lanxige.goods.service.SkuAttributeService;
import com.lanxige.model.Category;
import com.lanxige.model.CategoryAttr;
import com.lanxige.model.SkuAttribute;
import com.lanxige.vo.SkuAttributeSearch;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkuAttributeServiceImpl extends ServiceImpl<SkuAttributeMapper, SkuAttribute> implements SkuAttributeService {
    @Autowired
    private CategoryAttrMapper categoryAttrMapper;

    @Override
    public List<SkuAttribute> queryByCategoryId(Integer categoryId) {
        return baseMapper.queryByCategoryId(categoryId);
    }

    @Override
    public Page<SkuAttribute> queryPageList(Integer page, Integer size, SkuAttributeSearch skuAttributeSearch) {
        Page<SkuAttribute> SkuPage = new Page<>(page, size);
        QueryWrapper<SkuAttribute> skuWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(skuAttributeSearch.getName())){
            skuWrapper.like("name", skuAttributeSearch.getName());
            Page<SkuAttribute> skuAttributePage = new Page<>(skuAttributeSearch.getPage(), skuAttributeSearch.getSize());
            Page<SkuAttribute> skuAttributePage1 = baseMapper.selectPage(skuAttributePage, skuWrapper);
            return skuAttributePage1;
        }

        Page<SkuAttribute> skuAttributePage = baseMapper.selectPage(SkuPage, null);
        return skuAttributePage;
    }

    @Override
    public Integer addSkuIdToCategory(SkuAttribute skuAttribute) {
        Integer id = baseMapper.queryIdByName(skuAttribute.getName());
        if (id != 0){
            List<Category> categories = skuAttribute.getCategories();
            if (CollectionUtils.isEmpty(categories)) {
                return 0;
            }

            ArrayList<CategoryAttr> categoryAttrs = new ArrayList<>();
            for (Category category : categories) {
                CategoryAttr categoryAttr = new CategoryAttr();
                categoryAttr.setCategoryId(category.getId());
                categoryAttr.setAttrId(id);
                categoryAttrs.add(categoryAttr);
            }

            categoryAttrMapper.saveBatch(categoryAttrs);
            return categoryAttrs.size();
        }else{
            return 0;
        }
    }
}




