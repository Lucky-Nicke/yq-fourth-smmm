package com.lanxige.search.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.Map;

@Data
@Document(indexName = "shopsearch", type = "skues")
public class SkuEs {
    @Id
    private String id;
    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_smart")
    private String name;
    private Integer price;
    private Integer num;
    private String image;
    private String images;
    private Date createTime;
    private Date updateTime;
    private String spuId;
    private Integer categoryId;
    @Field(type= FieldType.Keyword) //不会被分词，例如类型，智能手机不会拆分
    private String categoryName;
    private Integer brandId;
    @Field(type= FieldType.Keyword) //不会被分词，例如类型，“华为”不会拆分
    private String brandName;
    private String skuAttribute;
    private Integer status;

    private Map<String, String> attrMap;
}
