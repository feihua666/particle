package com.particle.crm.infrastructure.tag.mapper;

import com.particle.crm.infrastructure.tag.dos.CrmCustomerTagRelDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 客户标签关系 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:22
 */
@Mapper
public interface CrmCustomerTagRelMapper extends IBaseMapper<CrmCustomerTagRelDO> {

}
