package com.particle.crm.infrastructure.relation.mapper;

import com.particle.crm.infrastructure.relation.dos.CrmCustomerRelationDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 客户与客户关系 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:30:39
 */
@Mapper
public interface CrmCustomerRelationMapper extends IBaseMapper<CrmCustomerRelationDO> {

}
