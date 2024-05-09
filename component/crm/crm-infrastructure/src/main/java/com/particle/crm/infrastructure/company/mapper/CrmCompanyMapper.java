package com.particle.crm.infrastructure.company.mapper;

import com.particle.crm.infrastructure.company.dos.CrmCompanyDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 客户公司 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2024-04-11 13:44:00
 */
@Mapper
public interface CrmCompanyMapper extends IBaseMapper<CrmCompanyDO> {

}
