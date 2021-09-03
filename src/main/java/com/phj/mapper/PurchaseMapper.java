package com.phj.mapper;

import com.phj.pojo.Purchase;

import java.util.List;

/**
 * <p>
 * 供应商 Mapper 接口
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */

public interface PurchaseMapper extends EasyBaseMapper<Purchase> {



    public List<Purchase> baseSelect( Purchase param);

    public int baseUpdateByObjId(Purchase param);

    //查询订单
    public Purchase baseSelectOne( Purchase param);
}
