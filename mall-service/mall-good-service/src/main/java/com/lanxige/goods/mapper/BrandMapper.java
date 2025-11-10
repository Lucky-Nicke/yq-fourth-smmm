package com.lanxige.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lanxige.model.Brand;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandMapper extends BaseMapper<Brand> {
    @Select("select brand_id from category_brand where category_id=#{id}")
    List<Integer> queryBrandIds(Integer id);
}
