package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyAbnormalDO;
import com.particle.data.infrastructure.company.dos.DataCompanyAbnormalDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;

import java.time.LocalDateTime;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 企业经营异常 服务类
 * </p>
 *
 * @author yw
 * @since 2025-05-29 10:47:31
 */
public interface IDataCompanyAbnormalService extends IBaseService<DataCompanyAbnormalDO> {

    /**
     * 根据企业表ID查询
     * @param companyId
     * @return
     */
    default List<DataCompanyAbnormalDO> getByCompanyId(Long companyId) {
        Assert.notNull(companyId,"companyId 不能为空");
        return list(Wrappers.<DataCompanyAbnormalDO>lambdaQuery().eq(DataCompanyAbnormalDO::getCompanyId, companyId));
    }



    /**
     * 根据企业表ID查询多个
     * @param companyIds
     * @return
     */
    default List<DataCompanyAbnormalDO> getByCompanyIds(List<Long> companyIds) {
        Assert.notEmpty(companyIds,"companyIds 不能为空");
        return list(Wrappers.<DataCompanyAbnormalDO>lambdaQuery().in(DataCompanyAbnormalDO::getCompanyId, companyIds));
    }


    /**
     * 根据 dataMd5 查询
     * @param punishContentMd5
     * @return
     */
    default public DataCompanyAbnormalDO getByCompanyIdAndDataMd5(Long companyId, String punishContentMd5) {
        Assert.notNull(companyId,"companyId 不能为空");
        Assert.notEmpty(punishContentMd5,"punishContentMd5 不能为空");
        return getOne(Wrappers.<DataCompanyAbnormalDO>lambdaQuery().eq(DataCompanyAbnormalDO::getCompanyId, companyId).eq(DataCompanyAbnormalDO::getDataMd5, punishContentMd5));
    }
    /**
     * 根据企业表ID查询列表
     * @param companyId
     * @return
     */
    default public List<DataCompanyAbnormalDO> listByCompanyId(Long companyId){
        Assert.notNull(companyId,"companyId 不能为空");
        return list(Wrappers.<DataCompanyAbnormalDO>lambdaQuery().eq(DataCompanyAbnormalDO::getCompanyId, companyId));
    }

    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyAbnormalDO> listPageByCompanyId(Long companyId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery,Wrappers.<DataCompanyAbnormalDO>lambdaQuery().eq(DataCompanyAbnormalDO::getCompanyId, companyId));
    }
    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyAbnormalDO>lambdaUpdate().eq(DataCompanyAbnormalDO::getId, id)
                .set(DataCompanyAbnormalDO::getLatestHandleAt, LocalDateTime.now()));
    }

}
