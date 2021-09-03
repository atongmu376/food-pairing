package com.phj.mapper;

import com.phj.pojo.PurchaseItem;

import java.util.List;

/**
 * <p>
 * 采购单明细 Mapper 接口
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */

public interface PurchaseItemMapper extends EasyBaseMapper<PurchaseItem> {
    public List<PurchaseItem> baseSelect( PurchaseItem param);

    public int baseUpdateByObjId(PurchaseItem param);

    public int bachInsert(List<PurchaseItem> purchaseItems);

    //订单id 查明细
    public List<PurchaseItem> selectByOrderId (Long id);

    public int deleteByPurchaseId(Long id);
}
