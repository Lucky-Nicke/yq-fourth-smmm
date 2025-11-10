package com.lanxige.goods.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanxige.goods.service.SkuAttributeService;
import com.lanxige.model.SkuAttribute;
import com.lanxige.util.RespResult;
import com.lanxige.vo.SkuAttributeSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skuAttribute")
@CrossOrigin
public class SkuAttributeController {
    @Autowired
    private SkuAttributeService skuAttributeService;

    @GetMapping("/category/{id}")
    public RespResult<SkuAttribute> getSkuAttributeByCategoryId(@PathVariable Integer id) {
        List<SkuAttribute> skuAttributes = skuAttributeService.queryByCategoryId(id);
        return RespResult.ok(skuAttributes);
    }

    @PostMapping("/list/{page}/{size}")
    public RespResult<SkuAttribute> getSkuAttributeByCategoryId(@PathVariable Integer page, @PathVariable Integer size, @RequestBody(required = false) SkuAttributeSearch skuAttributeSearch) {
        Page<SkuAttribute> skuAttributePage = skuAttributeService.queryPageList(page, size, skuAttributeSearch);
        return RespResult.ok(skuAttributePage);
    }

    @DeleteMapping("/delete/{id}")
    public RespResult deleteSkuAttribute(@PathVariable Integer id) {
        boolean b = skuAttributeService.removeById(id);
        if (b) {
            return RespResult.ok();
        } else {
            return RespResult.error("删除失败");
        }
    }

    @PostMapping("/add")
    public RespResult addSkuAttribute(@RequestBody SkuAttribute skuAttribute) {
        try {
            boolean save = skuAttributeService.save(skuAttribute);
            if (save) {
                Integer added = skuAttributeService.addSkuIdToCategory(skuAttribute);
                return added > 0 ? RespResult.ok() : RespResult.error("添加失败");
            }
        } catch (Exception e) {
            return RespResult.error("添加失败");
        }
        return RespResult.error("添加失败");
    }
}
