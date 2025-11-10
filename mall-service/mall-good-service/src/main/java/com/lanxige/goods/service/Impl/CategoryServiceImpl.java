package com.lanxige.goods.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lanxige.goods.mapper.CategoryMapper;
import com.lanxige.goods.service.CategoryService;
import com.lanxige.model.Category;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Override
    public List<Category> queryAllByParentId(Integer parentId) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Category::getParentId, parentId);
        List<Category> list = this.baseMapper.selectList(queryWrapper);
        if (list == null) {
            return Collections.emptyList();
        }else{
            return list;
        }
    }
}




