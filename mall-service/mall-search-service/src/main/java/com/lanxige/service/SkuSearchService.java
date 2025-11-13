package com.lanxige.service;

import com.lanxige.search.model.SkuEs;

import java.util.Map;

public interface SkuSearchService {
    void add(SkuEs skuEs);
    void del(String id);
    Map<String,Object> search(Map<String, Object> searchMap);
}
