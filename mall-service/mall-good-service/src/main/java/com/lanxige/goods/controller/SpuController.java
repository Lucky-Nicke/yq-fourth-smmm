package com.lanxige.goods.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanxige.goods.model.Product;
import com.lanxige.goods.model.Spu;
import com.lanxige.goods.service.SpuService;
import com.lanxige.goods.vo.SpuAuditStatus;
import com.lanxige.goods.vo.SpuSearch;
import com.lanxige.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spu")
@CrossOrigin
public class SpuController {

    @Autowired
    private SpuService spuService;

    //保存
    @PostMapping(value = "/save")
    public RespResult save(@RequestBody Product product){
        spuService.saveProduct(product);
        return RespResult.ok();
    }

    @PostMapping("/queryPageList/{page}/{size}")
    public RespResult list(@PathVariable("page") Integer page, @PathVariable("size") Integer size, @RequestBody(required = false) SpuSearch spuSearch){
        Page<Spu> spuPage = spuService.queryPageList(page, size, spuSearch);
        return RespResult.ok(spuPage);
    }

    @PutMapping("/audit/{id}")
    public RespResult audit(@PathVariable("id") String id,@RequestBody SpuAuditStatus spuAuditStatus){
        Boolean audited = spuService.auditStatus(spuAuditStatus);
        if(audited){
            return RespResult.ok();
        }else {
            return RespResult.error("审核操作失败");
        }
    }

    @GetMapping("/product/{id}")
    public RespResult searchProduct(@PathVariable String id){
        Spu spu = spuService.getById(id);
        return RespResult.ok(spu);
    }
}