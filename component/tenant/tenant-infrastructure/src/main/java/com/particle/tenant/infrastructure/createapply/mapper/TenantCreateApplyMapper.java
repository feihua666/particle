package com.particle.tenant.infrastructure.createapply.mapper;

import com.particle.tenant.infrastructure.createapply.dos.TenantCreateApplyDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 租户创建申请 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@Mapper
public interface TenantCreateApplyMapper extends IBaseMapper<TenantCreateApplyDO> {

}
