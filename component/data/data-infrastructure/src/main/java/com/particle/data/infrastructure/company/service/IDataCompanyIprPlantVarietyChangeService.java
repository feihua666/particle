package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyIprPlantVarietyChangeDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业知识产权植物新品种变更信息 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:52
 */
public interface IDataCompanyIprPlantVarietyChangeService extends IBaseService<DataCompanyIprPlantVarietyChangeDO> {


    /**
     * 根据企业知识产权植物新品种变更表id查询多个
     * @param companyIprPlantVarietyIds
     * @return
     */
    default List<DataCompanyIprPlantVarietyChangeDO> listByCompanyIprPlantVarietyIds(List<Long> companyIprPlantVarietyIds) {
        Assert.notEmpty(companyIprPlantVarietyIds,"companyIprPlantVarietyIds 不能为空");
        return list(Wrappers.<DataCompanyIprPlantVarietyChangeDO>lambdaQuery().in(DataCompanyIprPlantVarietyChangeDO::getCompanyIprPlantVarietyId, companyIprPlantVarietyIds));
    }
    /**
     * 根据植物新品种变更ID分页查询列表
     * @param companyIprPlantVarietyId
     * @param pageQueryForm
     * @return year有值时，只会有一条
     */
    default public Page<DataCompanyIprPlantVarietyChangeDO> listPageByCompanyIprPlantVarietyId(Long companyIprPlantVarietyId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyIprPlantVarietyId,"companyIprPlantVarietyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyIprPlantVarietyChangeDO>lambdaQuery()
                .eq(DataCompanyIprPlantVarietyChangeDO::getCompanyIprPlantVarietyId, companyIprPlantVarietyId)
        );
    }
    /**
     * 根据 植物新品种变更id 和 dataMd5 查询
     * @param companyIprPlantVarietyId
     * @param dataMd5
     * @return
     */
    default public DataCompanyIprPlantVarietyChangeDO getByCompanyIprPlantVarietyIdAndDataMd5(Long companyIprPlantVarietyId, String dataMd5) {
        Assert.notNull(companyIprPlantVarietyId,"companyIprPlantVarietyId 不能为空");
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyIprPlantVarietyChangeDO>lambdaQuery()
                .eq(DataCompanyIprPlantVarietyChangeDO::getCompanyIprPlantVarietyId, companyIprPlantVarietyId)
                .eq(DataCompanyIprPlantVarietyChangeDO::getDataMd5, dataMd5)
        );
    }
    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyIprPlantVarietyChangeDO>lambdaUpdate().eq(DataCompanyIprPlantVarietyChangeDO::getId, id)
                .set(DataCompanyIprPlantVarietyChangeDO::getLatestHandleAt, LocalDateTime.now()));
    }



}
