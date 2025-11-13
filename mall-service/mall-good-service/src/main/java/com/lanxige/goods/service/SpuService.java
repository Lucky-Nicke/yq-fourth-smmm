package com.lanxige.goods.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lanxige.goods.model.Product;
import com.lanxige.goods.model.Spu;
import com.lanxige.goods.vo.SpuAuditStatus;
import com.lanxige.goods.vo.SpuSearch;


public interface SpuService extends IService<Spu> {
    void saveProduct(Product product);
    Page<Spu> queryPageList(int page, int size, SpuSearch spuSearch);
    Boolean auditStatus(SpuAuditStatus spuAuditStatus);
}
