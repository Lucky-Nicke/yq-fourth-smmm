package com.lanxige.goods.controller;

import com.lanxige.goods.service.Impl.SkuServiceImpl;
import com.lanxige.model.Sku;
import com.lanxige.util.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sku")
@CrossOrigin
public class AdItemController {
    @Autowired
    private SkuServiceImpl skuService;

    @GetMapping("/aditems/type")
    public List<Sku> typeitems(@RequestParam(value = "id") Integer id){
        List<Sku> adSkuItems = skuService.typeSkuItems(id);
        return adSkuItems;
    }

    //清理缓存
    @DeleteMapping(value = "/deleteAditems/type")
    public RespResult deleteTypeItems(@RequestParam(value = "id") Integer id){
        skuService.deleteAdItems(id);
        return RespResult.ok();
    }

    //修改缓存
    @PutMapping(value = "/updateAitems/type")
    public RespResult updateTypeItems(@RequestParam(value = "id") Integer id){
        skuService.updateAditemsByTypeId(id);
        return RespResult.ok();
    }
}
