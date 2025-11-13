package com.lanxige.goods.feign;

import com.lanxige.goods.model.Sku;
import com.lanxige.util.RespResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("mall-goods")
@Component
@RequestMapping("/sku")
public interface SkuFeign {
    @GetMapping("/aditems/type/{id}")
    List<Sku> typeitems(@PathVariable Integer id);

    //清理缓存
    @DeleteMapping(value = "/deleteAditems/type")
    RespResult deleteTypeItems(@RequestParam(value = "id") Integer id);

    //修改缓存
    @PutMapping(value = "/updateAitems/type")
    RespResult updateTypeItems(@RequestParam(value = "id") Integer id);
}
