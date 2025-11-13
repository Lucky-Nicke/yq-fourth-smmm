package com.lanxige.controller;

import com.lanxige.search.feign.SkuSearchFeign;
import com.lanxige.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping(value = "/web/search")
public class SearchController {
    // 注入Feign接口（假设Feign接口类名为SearchFeign）
    @Autowired
    private SkuSearchFeign searchFeign;

    @GetMapping
    public String search(@RequestParam(required = false) Map<String, Object> searchMap, Model model) {
        // 1. 调用Feign接口获取搜索结果（调用8084服务的搜索接口）
        RespResult<Map<String, Object>> respResult = searchFeign.search(searchMap);
        model.addAttribute("result", respResult);
        return "search";
    }
}