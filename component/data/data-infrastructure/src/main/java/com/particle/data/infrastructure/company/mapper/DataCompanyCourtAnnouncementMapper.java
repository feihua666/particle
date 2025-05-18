package com.particle.data.infrastructure.company.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyCaseFilingDO;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementDO;
import com.particle.data.infrastructure.company.dto.DataCompanyCaseFilingListPageByCompanyIdParam;
import com.particle.data.infrastructure.company.dto.DataCompanyCourtAnnouncementListPageByCompanyIdParam;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 企业法院公告 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:05
 */
@Mapper
public interface DataCompanyCourtAnnouncementMapper extends IBaseMapper<DataCompanyCourtAnnouncementDO> {

    /**
     * 分页查询法院公告
     * @param page
     * @param param
     * @return
     */
    public Page<DataCompanyCourtAnnouncementDO> listPage(Page page, @Param("param") DataCompanyCourtAnnouncementListPageByCompanyIdParam param);

}
