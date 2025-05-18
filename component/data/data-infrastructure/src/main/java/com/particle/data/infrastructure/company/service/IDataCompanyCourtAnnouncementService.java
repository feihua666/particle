package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyCourtAnnouncementDO;
import com.particle.data.infrastructure.company.dto.DataCompanyCaseFilingListPageByCompanyIdParam;
import com.particle.data.infrastructure.company.dto.DataCompanyCourtAnnouncementListPageByCompanyIdParam;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;

/**
 * <p>
 * 企业法院公告 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:05
 */
public interface IDataCompanyCourtAnnouncementService extends IBaseService<DataCompanyCourtAnnouncementDO> {


    /**
     * 分页查询立案信息
     * @param param
     * @param pageQueryForm
     * @return
     */
    public Page<DataCompanyCourtAnnouncementDO> listPage(DataCompanyCourtAnnouncementListPageByCompanyIdParam param, PageQueryCommand pageQueryForm);
    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param caseNo
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyCourtAnnouncementDO> listPageByCompanyId(Long companyId,String announcementNo, String caseNo, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        return listPage(DataCompanyCourtAnnouncementListPageByCompanyIdParam.create(companyId,announcementNo, caseNo), pageQueryForm);
    }

    /**
     * 根据 dataMd5 查询
     * @param dataMd5
     * @return
     */
    default public DataCompanyCourtAnnouncementDO getByDataMd5(String dataMd5) {
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyCourtAnnouncementDO>lambdaQuery()
                .eq(DataCompanyCourtAnnouncementDO::getDataMd5, dataMd5)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyCourtAnnouncementDO>lambdaUpdate().eq(DataCompanyCourtAnnouncementDO::getId, id)
                .set(DataCompanyCourtAnnouncementDO::getLatestHandleAt, LocalDateTime.now()));
    }

}
