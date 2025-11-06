package com.lanxige.goods.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lanxige.goods.mapper.BrandMapper;
import com.lanxige.goods.service.BrandService;
import com.lanxige.model.Brand;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand> implements BrandService {
    @Override
    public List<Brand> queryList(Brand brand) {
        QueryWrapper<Brand> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(brand.getName())){
            queryWrapper.like("name",brand.getName());
        }

        if (!StringUtils.isEmpty(brand.getName())){
            queryWrapper.eq("initial",brand.getInitial());
        }

        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public Page<Brand> queryPageList(Long page, Long size, Brand brand) {
        Page<Brand> brandPage = baseMapper.selectPage(new Page<>(page, size),
                new QueryWrapper<Brand>().like("name", brand.getName()));
        return brandPage;
    }


}
