package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyDiscreditedJudgmentDebtorDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;

/**
 * <p>
 * 企业失信被执行人 服务类
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:38:58
 */
public interface IDataCompanyDiscreditedJudgmentDebtorService extends IBaseService<DataCompanyDiscreditedJudgmentDebtorDO> {


    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param caseNo
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyDiscreditedJudgmentDebtorDO> listPageByCompanyId(Long companyId, String caseNo, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyDiscreditedJudgmentDebtorDO>lambdaQuery()
                .eq(DataCompanyDiscreditedJudgmentDebtorDO::getDishonestExecutedPersonCompanyId, companyId)
                .eq(caseNo != null,DataCompanyDiscreditedJudgmentDebtorDO::getCaseNo, caseNo)
        );
    }

    /**
     * 根据 公司id 和 dataMd5 查询
     * @param dishonestExecutedPersonCompanyId
     * @param dataMd5
     * @return
     */
    default public DataCompanyDiscreditedJudgmentDebtorDO getByDishonestExecutedPersonCompanyIdAndDataMd5(Long dishonestExecutedPersonCompanyId,String dataMd5) {
        Assert.notNull(dishonestExecutedPersonCompanyId,"dishonestExecutedPersonCompanyId 不能为空");
        Assert.notEmpty(dataMd5,"dataMd5 不能为空");
        return getOne(Wrappers.<DataCompanyDiscreditedJudgmentDebtorDO>lambdaQuery()
                .eq(DataCompanyDiscreditedJudgmentDebtorDO::getDishonestExecutedPersonCompanyId, dishonestExecutedPersonCompanyId)
                .eq(DataCompanyDiscreditedJudgmentDebtorDO::getDataMd5, dataMd5)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyDiscreditedJudgmentDebtorDO>lambdaUpdate().eq(DataCompanyDiscreditedJudgmentDebtorDO::getId, id)
                .set(DataCompanyDiscreditedJudgmentDebtorDO::getLatestHandleAt, LocalDateTime.now()));
    }

}
