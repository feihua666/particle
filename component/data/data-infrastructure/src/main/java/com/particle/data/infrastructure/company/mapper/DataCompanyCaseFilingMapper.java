package com.particle.data.infrastructure.company.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyCaseFilingDO;
import com.particle.data.infrastructure.company.dto.DataCompanyCaseFilingListPageByCompanyIdParam;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 企业立案信息 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:36
 */
@Mapper
public interface DataCompanyCaseFilingMapper extends IBaseMapper<DataCompanyCaseFilingDO> {

    /**
     * 分页查询立案信息
     * @param page
     * @param param
     * @return
     */
    public Page<DataCompanyCaseFilingDO> listPage(Page page, @Param("param") DataCompanyCaseFilingListPageByCompanyIdParam param);

}
