package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPatentContentDO;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业知识产权专利内容 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:27
 */
public interface IDataCompanyIprPatentContentService extends IBaseService<DataCompanyIprPatentContentDO> {

    /**
     * 根据企业知识产权专利表id查询
     * @param companyIprPatentId
     * @return
     */
    default DataCompanyIprPatentContentDO getByCompanyIprPatentId(Long companyIprPatentId) {
        Assert.notNull(companyIprPatentId,"companyIprPatentId 不能为空");
        return getOne(Wrappers.<DataCompanyIprPatentContentDO>lambdaQuery().eq(DataCompanyIprPatentContentDO::getCompanyIprPatentId, companyIprPatentId));
    }



    /**
     * 根据企业知识产权专利表id查询多个
     * @param companyIprPatentIds
     * @return
     */
    default List<DataCompanyIprPatentContentDO> listByCompanyIprPatentIds(List<Long> companyIprPatentIds) {
        Assert.notEmpty(companyIprPatentIds,"companyIprPatentIds 不能为空");
        return list(Wrappers.<DataCompanyIprPatentContentDO>lambdaQuery().in(DataCompanyIprPatentContentDO::getCompanyIprPatentId, companyIprPatentIds));
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyIprPatentContentDO>lambdaUpdate().eq(DataCompanyIprPatentContentDO::getId, id)
                .set(DataCompanyIprPatentContentDO::getLatestHandleAt, LocalDateTime.now()));
    }









}
