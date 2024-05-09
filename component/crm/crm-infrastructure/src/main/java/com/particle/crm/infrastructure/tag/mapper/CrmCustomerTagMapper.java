package com.particle.crm.infrastructure.tag.mapper;

import com.particle.crm.infrastructure.tag.dos.CrmCustomerTagDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 客户标签 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-04-24 16:32:09
 */
@Mapper
public interface CrmCustomerTagMapper extends IBaseMapper<CrmCustomerTagDO> {

}
