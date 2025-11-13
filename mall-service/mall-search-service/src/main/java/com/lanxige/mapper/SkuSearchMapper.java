package com.lanxige.mapper;

import com.lanxige.search.model.SkuEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SkuSearchMapper extends ElasticsearchRepository<SkuEs, String> {

}
