package com.lanxige.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanxige.goods.model.CategoryAttr;
import org.apache.ibatis.annotations.Insert;

import java.util.ArrayList;

public interface CategoryAttrMapper extends BaseMapper<CategoryAttr> {
    @Insert({
            "<script>",
            "INSERT INTO category_attr (attr_id, category_id) VALUES ",
            "<foreach collection='list' item='item' separator=','>",
            "(#{item.attrId}, #{item.categoryId})",
            "</foreach>",
            "</script>"
    })
    void saveBatch(ArrayList<CategoryAttr> categoryAttrs);
}




