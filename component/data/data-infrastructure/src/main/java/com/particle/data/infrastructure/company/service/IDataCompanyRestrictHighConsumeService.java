package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyRestrictHighConsumeDO;
import com.particle.data.infrastructure.company.dto.DataCompanyJudgmentDocumentListPageByCompanyIdParam;
import com.particle.data.infrastructure.company.dto.DataCompanyRestrictHighConsumeListPageByCompanyIdParam;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;

/**
 * <p>
 * 企业限制高消费 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:19
 */
public interface IDataCompanyRestrictHighConsumeService extends IBaseService<DataCompanyRestrictHighConsumeDO> {


    /**
     * 分页查询限制高消费
     * @param param
     * @param pageQueryForm
     * @return
     */
    public Page<DataCompanyRestrictHighConsumeDO> listPage(DataCompanyRestrictHighConsumeListPageByCompanyIdParam param, PageQueryCommand pageQueryForm);
    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param partyName
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyRestrictHighConsumeDO> listPageByCompanyId(Long companyId,String partyName, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        return listPage(DataCompanyRestrictHighConsumeListPageByCompanyIdParam.create(companyId, partyName), pageQueryForm);
    }

    /**
     * 根据 dataMd5 查询
     * @param dataMd5
     * @return
     */
    default public DataCompanyRestrictHighConsumeDO getByDataMd5(String dataMd5) {
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyRestrictHighConsumeDO>lambdaQuery()
                .eq(DataCompanyRestrictHighConsumeDO::getDataMd5, dataMd5)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyRestrictHighConsumeDO>lambdaUpdate().eq(DataCompanyRestrictHighConsumeDO::getId, id)
                .set(DataCompanyRestrictHighConsumeDO::getLatestHandleAt, LocalDateTime.now()));
    }


}
