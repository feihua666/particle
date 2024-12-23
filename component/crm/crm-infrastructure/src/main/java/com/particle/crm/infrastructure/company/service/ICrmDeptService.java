package com.particle.crm.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.crm.infrastructure.company.dos.CrmDeptDO;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.util.List;

/**
 * <p>
 * 客户公司部门 服务类
 * </p>
 *
 * @author yw
 * @since 2024-04-24 10:16:52
 */
public interface ICrmDeptService extends IBaseService<CrmDeptDO> {

    /**
     * 根据部门编码查询
     * @param code
     * @return
     */
    default CrmDeptDO getByCode(String code) {
        Assert.notNull(code,"code 不能为空");
        return getOne(Wrappers.<CrmDeptDO>lambdaQuery().eq(CrmDeptDO::getCode, code));
    }



    /**
     * 根据部门编码查询多个
     * @param codes
     * @return
     */
    default List<CrmDeptDO> getByCodes(List<String> codes) {
        Assert.notEmpty(codes,"codes 不能为空");
        return list(Wrappers.<CrmDeptDO>lambdaQuery().in(CrmDeptDO::getCode, codes));
    }



    /**
     * 根据客户公司id查询
     * @param crmCompanyId
     * @return
     */
    default List<CrmDeptDO> getByCrmCompanyId(Long crmCompanyId) {
        Assert.notNull(crmCompanyId,"crmCompanyId 不能为空");
        return list(Wrappers.<CrmDeptDO>lambdaQuery().eq(CrmDeptDO::getCrmCompanyId, crmCompanyId));
    }



    /**
     * 根据客户公司id查询多个
     * @param crmCompanyIds
     * @return
     */
    default List<CrmDeptDO> getByCrmCompanyIds(List<Long> crmCompanyIds) {
        Assert.notEmpty(crmCompanyIds,"crmCompanyIds 不能为空");
        return list(Wrappers.<CrmDeptDO>lambdaQuery().in(CrmDeptDO::getCrmCompanyId, crmCompanyIds));
    }























}
