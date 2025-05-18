package com.particle.data.infrastructure.company.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementDO;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementDO;
import com.particle.data.infrastructure.company.dto.DataCompanyOpenCourtAnnouncementListPageByCompanyIdParam;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 企业开庭公告 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:31
 */
@Mapper
public interface DataCompanyOpenCourtAnnouncementMapper extends IBaseMapper<DataCompanyOpenCourtAnnouncementDO> {
    /**
     * 分页查询开庭公告
     * @param page
     * @param param
     * @return
     */
    public Page<DataCompanyOpenCourtAnnouncementDO> listPage(Page page, @Param("param") DataCompanyOpenCourtAnnouncementListPageByCompanyIdParam param);

}
