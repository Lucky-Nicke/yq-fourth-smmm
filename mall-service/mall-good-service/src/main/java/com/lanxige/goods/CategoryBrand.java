package com.lanxige.goods;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 
 * @TableName category_brand
 */
@TableName(value ="category_brand")
@Data
public class CategoryBrand {
    /**
     * 品牌ID
     */
    @TableId
    private Integer brand_id;

    /**
     * 分类ID
     */
    @TableId
    private Integer category_id;
}