package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyRestrictHighConsumePartyDO;
import com.particle.data.infrastructure.company.dos.DataCompanyRestrictHighConsumePartyDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;

import java.time.LocalDateTime;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 企业限制高消费当事人 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:32
 */
public interface IDataCompanyRestrictHighConsumePartyService extends IBaseService<DataCompanyRestrictHighConsumePartyDO> {

    /**
     * 根据限制高消费表id查询
     * @param companyRestrictHighConsumeId
     * @return
     */
    default List<DataCompanyRestrictHighConsumePartyDO> getByCompanyRestrictHighConsumeId(Long companyRestrictHighConsumeId) {
        Assert.notNull(companyRestrictHighConsumeId,"companyRestrictHighConsumeId 不能为空");
        return list(Wrappers.<DataCompanyRestrictHighConsumePartyDO>lambdaQuery().eq(DataCompanyRestrictHighConsumePartyDO::getCompanyRestrictHighConsumeId, companyRestrictHighConsumeId));
    }



    /**
     * 根据限制高消费表id查询多个
     * @param companyRestrictHighConsumeIds
     * @return
     */
    default List<DataCompanyRestrictHighConsumePartyDO> getByCompanyRestrictHighConsumeIds(List<Long> companyRestrictHighConsumeIds) {
        Assert.notEmpty(companyRestrictHighConsumeIds,"companyRestrictHighConsumeIds 不能为空");
        return list(Wrappers.<DataCompanyRestrictHighConsumePartyDO>lambdaQuery().in(DataCompanyRestrictHighConsumePartyDO::getCompanyRestrictHighConsumeId, companyRestrictHighConsumeIds));
    }


    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyRestrictHighConsumePartyDO> listPageByCompanyId(Long companyId, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyRestrictHighConsumePartyDO>lambdaQuery()
                .eq(DataCompanyRestrictHighConsumePartyDO::getPartyCompanyId, companyId)
        );
    }

    /**
     * 根据 限制高消费id 和 dataMd5 查询
     * @param companyRestrictHighConsumeId
     * @param dataMd5
     * @return
     */
    default public DataCompanyRestrictHighConsumePartyDO getByCompanyRestrictHighConsumeIdAndDataMd5(Long companyRestrictHighConsumeId,String dataMd5) {
        Assert.notNull(companyRestrictHighConsumeId,"companyRestrictHighConsumeId 不能为空");
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyRestrictHighConsumePartyDO>lambdaQuery()
                .eq(DataCompanyRestrictHighConsumePartyDO::getCompanyRestrictHighConsumeId, companyRestrictHighConsumeId)
                .eq(DataCompanyRestrictHighConsumePartyDO::getDataMd5, dataMd5)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyRestrictHighConsumePartyDO>lambdaUpdate().eq(DataCompanyRestrictHighConsumePartyDO::getId, id)
                .set(DataCompanyRestrictHighConsumePartyDO::getLatestHandleAt, LocalDateTime.now()));
    }



















}
