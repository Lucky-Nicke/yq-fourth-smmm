package com.lanxige.search.feign;

import com.lanxige.search.model.SkuEs;
import com.lanxige.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@FeignClient(name = "mall-search-service")
@RequestMapping("/search")
public interface SkuSearchFeign {
    @PostMapping("/add")
    RespResult add(SkuEs skuEs);

    @DeleteMapping("/del/{id}")
    RespResult del(@PathVariable("id") String id);

    @GetMapping
    RespResult<Map<String,Object>> search(@RequestParam Map<String,Object> searchMap);
}
