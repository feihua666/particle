package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.particle.data.infrastructure.company.dos.DataCompanyDO;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.util.List;

/**
 * <p>
 * 企业 服务类
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
public interface IDataCompanyService extends IBaseService<DataCompanyDO> {


    /**
     * 根据统一社会信用代码查询
     * @param uscc
     * @return
     */
    default DataCompanyDO getByUscc(String uscc) {
        Assert.notNull(uscc,"uscc 不能为空");
        return getOne(Wrappers.<DataCompanyDO>lambdaQuery().eq(DataCompanyDO::getUscc, uscc));
    }



    /**
     * 根据统一社会信用代码查询多个
     * @param usccs
     * @return
     */
    default List<DataCompanyDO> getByUsccs(List<String> usccs) {
        Assert.notEmpty(usccs,"usccs 不能为空");
        return list(Wrappers.<DataCompanyDO>lambdaQuery().in(DataCompanyDO::getUscc, usccs));
    }


    /**
     * 根据注册号查询
     * @param regNo
     * @return
     */
    default DataCompanyDO getByRegNo(String regNo) {
        Assert.notNull(regNo,"regNo 不能为空");
        return getOne(Wrappers.<DataCompanyDO>lambdaQuery().eq(DataCompanyDO::getRegNo, regNo));
    }



    /**
     * 根据注册号查询多个
     * @param regNos
     * @return
     */
    default List<DataCompanyDO> getByRegNos(List<String> regNos) {
        Assert.notEmpty(regNos,"regNos 不能为空");
        return list(Wrappers.<DataCompanyDO>lambdaQuery().in(DataCompanyDO::getRegNo, regNos));
    }


    /**
     * 根据组织机构代码查询
     * @param orgCode
     * @return
     */
    default DataCompanyDO getByOrgCode(String orgCode) {
        Assert.notNull(orgCode,"orgCode 不能为空");
        return getOne(Wrappers.<DataCompanyDO>lambdaQuery().eq(DataCompanyDO::getOrgCode, orgCode));
    }



    /**
     * 根据组织机构代码查询多个
     * @param orgCodes
     * @return
     */
    default List<DataCompanyDO> getByOrgCodes(List<String> orgCodes) {
        Assert.notEmpty(orgCodes,"orgCodes 不能为空");
        return list(Wrappers.<DataCompanyDO>lambdaQuery().in(DataCompanyDO::getOrgCode, orgCodes));
    }












}
