package com.lanxige.goods.controller;

import com.lanxige.goods.service.Impl.CategoryServiceImpl;
import com.lanxige.model.Category;
import com.lanxige.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryServiceImpl categoryService;

    //根据父id查询分类
    @GetMapping("/parent/{parentId}")
    public RespResult<List<Category>> queryCategoryByParentId(@PathVariable Integer parentId){
        List<Category> categories = categoryService.queryAllByParentId(parentId);
        return RespResult.ok(categories);
    }
}
