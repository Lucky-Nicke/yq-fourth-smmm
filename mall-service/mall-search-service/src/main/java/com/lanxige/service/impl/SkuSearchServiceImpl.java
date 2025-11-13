package com.lanxige.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lanxige.mapper.SkuSearchMapper;
import com.lanxige.search.model.SkuEs;
import com.lanxige.service.SkuSearchService;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SkuSearchServiceImpl implements SkuSearchService {
    @Autowired
    private SkuSearchMapper skuSearchMapper;

    @Override
    public void add(SkuEs skuEs) {
        String skuAttribute = skuEs.getSkuAttribute();
        if (!StringUtils.isEmpty(skuAttribute)) {
            skuEs.setAttrMap(JSON.parseObject(skuAttribute, Map.class));
        }
        skuSearchMapper.save(skuEs);
    }

    @Override
    public void del(String id) {
        skuSearchMapper.deleteById(id);
    }

    @Override
    public Map<String, Object> search(Map<String, Object> searchMap) {
        //条件封装
        NativeSearchQueryBuilder queryBuilder = queryBuilder(searchMap);

        //执行搜索
        Page<SkuEs> result = skuSearchMapper.search(queryBuilder.build());

        //结果集
        List<SkuEs> list = result.getContent();
        //中记录数
        long totalElements = result.getTotalElements();

        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("list",list);
        resultMap.put("totalElements",totalElements);
        return resultMap;
    }

    public NativeSearchQueryBuilder queryBuilder(Map<String,Object> searchMap){
        //QueryBuilder构建
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();

        //条件判断
        if(searchMap!=null && searchMap.size()>0){
            //关键词
            Object keywords =searchMap.get("keywords");
            if(!StringUtils.isEmpty((CharSequence) keywords)){
                queryBuilder.withQuery(QueryBuilders.termQuery("name",keywords.toString()));
            }
        }
        return queryBuilder;
    }
}
