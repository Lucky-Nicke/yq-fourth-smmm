package com.lanxige.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lanxige.goods.model.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {
    List<Category> queryAllByParentId(Integer parentId);
}
