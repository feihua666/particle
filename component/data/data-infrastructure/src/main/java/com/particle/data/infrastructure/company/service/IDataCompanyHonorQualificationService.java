package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyHonorQualificationDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;

/**
 * <p>
 * 企业荣誉资质 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:14
 */
public interface IDataCompanyHonorQualificationService extends IBaseService<DataCompanyHonorQualificationDO> {


    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param certificateNo
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyHonorQualificationDO> listPageByCompanyId(Long companyId, String certificateNo, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyHonorQualificationDO>lambdaQuery()
                .eq(DataCompanyHonorQualificationDO::getCompanyId, companyId)
                .eq(certificateNo != null,DataCompanyHonorQualificationDO::getCertificateNo, certificateNo)
        );
    }

    /**
     * 根据 公司id 和 dataMd5 查询
     * @param companyId
     * @param dataMd5
     * @return
     */
    default public DataCompanyHonorQualificationDO getByCompanyIdAndDataMd5(Long companyId, String dataMd5) {
        Assert.notNull(companyId,"companyId 不能为空");
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyHonorQualificationDO>lambdaQuery()
                .eq(DataCompanyHonorQualificationDO::getCompanyId, companyId)
                .eq(DataCompanyHonorQualificationDO::getDataMd5, dataMd5)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyHonorQualificationDO>lambdaUpdate().eq(DataCompanyHonorQualificationDO::getId, id)
                .set(DataCompanyHonorQualificationDO::getLatestHandleAt, LocalDateTime.now()));
    }
}
