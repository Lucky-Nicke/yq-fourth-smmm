package com.lanxige.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanxige.goods.model.SkuAttribute;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface SkuAttributeMapper extends BaseMapper<SkuAttribute> {
    @Select("SELECT * FROM sku_attribute WHERE id IN(SELECT attr_id FROM category_attr WHERE category_id=#{id})")
    List<SkuAttribute> queryByCategoryId(Integer categoryId);
    @Select("SELECT id FROM sku_attribute WHERE name=#{name}")
    Integer queryIdByName(String name);
}




