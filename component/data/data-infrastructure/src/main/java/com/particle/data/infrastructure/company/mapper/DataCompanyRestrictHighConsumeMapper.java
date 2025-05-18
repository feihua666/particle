package com.particle.data.infrastructure.company.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyRestrictHighConsumeDO;
import com.particle.data.infrastructure.company.dto.DataCompanyRestrictHighConsumeListPageByCompanyIdParam;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 企业限制高消费 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:19
 */
@Mapper
public interface DataCompanyRestrictHighConsumeMapper extends IBaseMapper<DataCompanyRestrictHighConsumeDO> {
    /**
     * 分页查询限制高消费
     * @param page
     * @param param
     * @return
     */
    public Page<DataCompanyRestrictHighConsumeDO> listPage(Page page, @Param("param") DataCompanyRestrictHighConsumeListPageByCompanyIdParam param);

}
