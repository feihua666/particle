package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyPrimeStaffPositionDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业主要人员职位 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-22 15:07:33
 */
public interface IDataCompanyPrimeStaffPositionService extends IBaseService<DataCompanyPrimeStaffPositionDO> {

    /**
     * 根据主要人员表id查询多个
     * @param companyPrimeStaffId
     * @return
     */
    default List<DataCompanyPrimeStaffPositionDO> listByCompanyPrimeStaffId(Long companyPrimeStaffId) {
        Assert.notNull(companyPrimeStaffId,"companyPrimeStaffId 不能为空");
        return list(Wrappers.<DataCompanyPrimeStaffPositionDO>lambdaQuery().eq(DataCompanyPrimeStaffPositionDO::getCompanyPrimeStaffId, companyPrimeStaffId));
    }
    /**
     * 根据主要人员表id查询多个
     * @param companyPrimeStaffIds
     * @return
     */
    default List<DataCompanyPrimeStaffPositionDO> getByCompanyPrimeStaffIds(List<Long> companyPrimeStaffIds) {
        Assert.notEmpty(companyPrimeStaffIds,"companyPrimeStaffIds 不能为空");
        return list(Wrappers.<DataCompanyPrimeStaffPositionDO>lambdaQuery().in(DataCompanyPrimeStaffPositionDO::getCompanyPrimeStaffId, companyPrimeStaffIds));
    }



    /**
     * 根据主要人员ID分页查询列表
     * @param companyPrimeStaffId
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyPrimeStaffPositionDO> listPageByCompanyPrimeStaffId(Long companyPrimeStaffId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyPrimeStaffId,"companyPrimeStaffId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyPrimeStaffPositionDO>lambdaQuery()
                .eq(DataCompanyPrimeStaffPositionDO::getCompanyPrimeStaffId, companyPrimeStaffId)
        );
    }

    /**
     * 根据 主要人员id 和 positionName 查询
     * @param companyPrimeStaffId
     * @param positionName
     * @return
     */
    default public DataCompanyPrimeStaffPositionDO getByCompanyPrimeStaffIdAndPositionName(Long companyPrimeStaffId,String positionName) {
        Assert.notNull(companyPrimeStaffId,"companyPrimeStaffId 不能为空");
        Assert.notEmpty(positionName,"positionName 不能为空");
        return getOne(Wrappers.<DataCompanyPrimeStaffPositionDO>lambdaQuery()
                .eq(DataCompanyPrimeStaffPositionDO::getCompanyPrimeStaffId, companyPrimeStaffId)
                .eq(DataCompanyPrimeStaffPositionDO::getPositionName, positionName)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyPrimeStaffPositionDO>lambdaUpdate().eq(DataCompanyPrimeStaffPositionDO::getId, id)
                .set(DataCompanyPrimeStaffPositionDO::getLatestHandleAt, LocalDateTime.now()));
    }


}
