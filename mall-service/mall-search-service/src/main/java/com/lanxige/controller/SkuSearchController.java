package com.lanxige.controller;

import com.lanxige.search.model.SkuEs;
import com.lanxige.service.SkuSearchService;
import com.lanxige.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/search")
public class SkuSearchController {
    @Autowired
    private SkuSearchService skuSearchService;

    @PostMapping(value = "/add")
    public RespResult add(@RequestBody SkuEs skuEs){
        skuSearchService.add(skuEs);
        return RespResult.ok();
    }

    @DeleteMapping("/del/{id}")
    public RespResult del(@PathVariable("id") String id){
        skuSearchService.del(id);
        return RespResult.ok();
    }

    @GetMapping
    // 实现搜索
    public RespResult<Map<String,Object>> search(@RequestParam(required = false) Map<String,Object> map){
        Map<String, Object> searchData = this.skuSearchService.search(map);
        return RespResult.ok(searchData);
    }
}
