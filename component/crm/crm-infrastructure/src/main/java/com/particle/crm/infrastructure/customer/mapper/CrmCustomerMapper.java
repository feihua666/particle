package com.particle.crm.infrastructure.customer.mapper;

import com.particle.crm.infrastructure.customer.dos.CrmCustomerDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 客户 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:21:36
 */
@Mapper
public interface CrmCustomerMapper extends IBaseMapper<CrmCustomerDO> {

}
