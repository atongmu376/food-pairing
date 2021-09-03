package com.phj.mapper;

import com.phj.pojo.Supplier;

import java.util.List;

/**
 * <p>
 * 供应商 Mapper 接口
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */

public interface SupplierMapper extends EasyBaseMapper<Supplier> {
    public List<Supplier> baseSelect( Supplier param);

    public int baseUpdateByObjId(Supplier param);
}
