package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanySpotCheckDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业抽查检查 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:39
 */
public interface IDataCompanySpotCheckService extends IBaseService<DataCompanySpotCheckDO> {


    /**
     * 根据 dataMd5 查询
     * @param punishContentMd5
     * @return
     */
    default public DataCompanySpotCheckDO getByCompanyIdAndDataMd5(Long companyId, String punishContentMd5) {
        Assert.notNull(companyId,"companyId 不能为空");
        Assert.notEmpty(punishContentMd5,"punishContentMd5 不能为空");
        return getOne(Wrappers.<DataCompanySpotCheckDO>lambdaQuery().eq(DataCompanySpotCheckDO::getCompanyId, companyId).eq(DataCompanySpotCheckDO::getDataMd5, punishContentMd5));
    }
    /**
     * 根据企业表ID查询列表
     * @param companyId
     * @return
     */
    default public List<DataCompanySpotCheckDO> listByCompanyId(Long companyId){
        Assert.notNull(companyId,"companyId 不能为空");
        return list(Wrappers.<DataCompanySpotCheckDO>lambdaQuery().eq(DataCompanySpotCheckDO::getCompanyId, companyId));
    }

    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanySpotCheckDO> listPageByCompanyId(Long companyId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery,Wrappers.<DataCompanySpotCheckDO>lambdaQuery().eq(DataCompanySpotCheckDO::getCompanyId, companyId));
    }
    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanySpotCheckDO>lambdaUpdate().eq(DataCompanySpotCheckDO::getId, id)
                .set(DataCompanySpotCheckDO::getLatestHandleAt, LocalDateTime.now()));
    }
}
