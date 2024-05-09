package com.particle.crm.infrastructure.customer.mapper;

import com.particle.crm.infrastructure.customer.dos.CrmCustomerContactDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 客户联系方式 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:27:56
 */
@Mapper
public interface CrmCustomerContactMapper extends IBaseMapper<CrmCustomerContactDO> {

}
