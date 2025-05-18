package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyOpenCourtAnnouncementDO;
import com.particle.data.infrastructure.company.dto.DataCompanyOpenCourtAnnouncementListPageByCompanyIdParam;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;

/**
 * <p>
 * 企业开庭公告 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:44:31
 */
public interface IDataCompanyOpenCourtAnnouncementService extends IBaseService<DataCompanyOpenCourtAnnouncementDO> {



    /**
     * 分页查询立案信息
     * @param param
     * @param pageQueryForm
     * @return
     */
    public Page<DataCompanyOpenCourtAnnouncementDO> listPage(DataCompanyOpenCourtAnnouncementListPageByCompanyIdParam param, PageQueryCommand pageQueryForm);
    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param caseNo
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyOpenCourtAnnouncementDO> listPageByCompanyId(Long companyId, String caseNo, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        return listPage(DataCompanyOpenCourtAnnouncementListPageByCompanyIdParam.create(companyId, caseNo), pageQueryForm);
    }

    /**
     * 根据 dataMd5 查询
     * @param dataMd5
     * @return
     */
    default public DataCompanyOpenCourtAnnouncementDO getByDataMd5(String dataMd5) {
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyOpenCourtAnnouncementDO>lambdaQuery()
                .eq(DataCompanyOpenCourtAnnouncementDO::getDataMd5, dataMd5)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyOpenCourtAnnouncementDO>lambdaUpdate().eq(DataCompanyOpenCourtAnnouncementDO::getId, id)
                .set(DataCompanyOpenCourtAnnouncementDO::getLatestHandleAt, LocalDateTime.now()));
    }


}
