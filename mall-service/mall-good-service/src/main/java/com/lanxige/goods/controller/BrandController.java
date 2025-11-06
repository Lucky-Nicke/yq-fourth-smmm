package com.lanxige.goods.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lanxige.goods.service.BrandService;
import com.lanxige.model.Brand;
import com.lanxige.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    BrandService brandService;

    //添加品牌
    @PostMapping("/addBrand")
    public RespResult addBrand(@RequestBody Brand brand){
        boolean save = brandService.save(brand);
        return RespResult.ok();
    }

    // 根据id 去得到一个品牌信息
    @GetMapping("/getBrandById/{id}")
    public RespResult getBrandById(@PathVariable("id") Integer id){
        Brand brand = brandService.getById(id);
        return RespResult.ok(brand);
    }

    // 修改实现
    @PutMapping("/updateBrand")
    public RespResult updateBrand(@RequestBody Brand brand){
        brandService.updateById(brand);
        return RespResult.ok();
    }

    // 删除实现
    @DeleteMapping("/deleteBrand/{id}")
    public RespResult deleteBrand(@PathVariable("id") Integer id){
        brandService.removeById(id);
        return RespResult.ok();
    }

    // 条件查询
    @GetMapping("/list")
    public RespResult list(@RequestBody(required = false) Brand brand){
        List<Brand> brands = brandService.queryList(brand);
        return RespResult.ok(brands);
    }

    // 分页查询
    @GetMapping("/queryPageList/{page}/{size}")
    public RespResult queryPageList(@PathVariable("page") Long page,
                                    @PathVariable("size") Long size,
                                    @RequestBody Brand brand){
        Page<Brand> brandPage = brandService.queryPageList(page, size, brand);
        return RespResult.ok(brandPage);
    }
}
