package com.phj.mapper;

import com.phj.pojo.ClientType;

import java.util.List;

/**
 * <p>
 * 客户类型 Mapper 接口
 * </p>
 *
 * @author Mr.Pan
 * @since 2021-08-19
 */

public interface ClientTypeMapper extends EasyBaseMapper<ClientType> {
    public List<ClientType> baseSelect( ClientType param);

    public int baseUpdateByObjId(ClientType param);
}
