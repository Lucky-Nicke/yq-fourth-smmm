package com.lanxige.goods.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lanxige.model.Brand;

import java.util.List;

public interface BrandService extends IService<Brand> {
    List<Brand> queryList(Brand brand);
    Page<Brand> queryPageList(Long page, Long size, Brand brand);
}
