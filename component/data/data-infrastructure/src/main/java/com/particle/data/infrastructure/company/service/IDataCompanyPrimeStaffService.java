package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyPrimeStaffDO;
import com.particle.data.infrastructure.company.dos.DataCompanyPrimeStaffDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业主要人员 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:44
 */
public interface IDataCompanyPrimeStaffService extends IBaseService<DataCompanyPrimeStaffDO> {


    /**
     * 根据 staffName 查询
     * @param punishContentMd5
     * @return
     */
    default public DataCompanyPrimeStaffDO getByCompanyIdAndStaffName(Long companyId, String punishContentMd5) {
        Assert.notNull(companyId,"companyId 不能为空");
        Assert.notEmpty(punishContentMd5,"punishContentMd5 不能为空");
        return getOne(Wrappers.<DataCompanyPrimeStaffDO>lambdaQuery().eq(DataCompanyPrimeStaffDO::getCompanyId, companyId).eq(DataCompanyPrimeStaffDO::getStaffName, punishContentMd5));
    }
    /**
     * 根据企业表ID查询列表
     * @param companyId
     * @return
     */
    default public List<DataCompanyPrimeStaffDO> listByCompanyId(Long companyId){
        Assert.notNull(companyId,"companyId 不能为空");
        return list(Wrappers.<DataCompanyPrimeStaffDO>lambdaQuery().eq(DataCompanyPrimeStaffDO::getCompanyId, companyId));
    }

    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyPrimeStaffDO> listPageByCompanyId(Long companyId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery,Wrappers.<DataCompanyPrimeStaffDO>lambdaQuery().eq(DataCompanyPrimeStaffDO::getCompanyId, companyId));
    }
    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyPrimeStaffDO>lambdaUpdate().eq(DataCompanyPrimeStaffDO::getId, id)
                .set(DataCompanyPrimeStaffDO::getLatestHandleAt, LocalDateTime.now()));
    }


}
