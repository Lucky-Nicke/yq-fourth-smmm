package com.lanxige.goods.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lanxige.model.Product;
import com.lanxige.model.Spu;
import com.lanxige.vo.SpuSearch;
import com.lanxige.vo.SpuAuditStatus;

public interface SpuService extends IService<Spu> {
    void saveProduct(Product product);
    Page<Spu> queryPageList(int page, int size, SpuSearch spuSearch);
    Boolean auditStatus(SpuAuditStatus spuAuditStatus);
}
