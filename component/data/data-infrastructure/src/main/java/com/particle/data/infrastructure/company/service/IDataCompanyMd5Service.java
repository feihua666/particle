package com.particle.data.infrastructure.company.service;

import com.particle.data.infrastructure.company.dos.DataCompanyMd5DO;
import com.particle.global.mybatis.plus.crud.IBaseService;
import com.particle.global.exception.Assert;
import java.util.List;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 * 企业md5 服务类
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:59
 */
public interface IDataCompanyMd5Service extends IBaseService<DataCompanyMd5DO> {


    /**
     * 根据名称md5查询
     * @param nameMd5
     * @return
     */
    default List<DataCompanyMd5DO> getByNameMd5(String nameMd5) {
        Assert.notNull(nameMd5,"nameMd5 不能为空");
        return list(Wrappers.<DataCompanyMd5DO>lambdaQuery().eq(DataCompanyMd5DO::getNameMd5, nameMd5));
    }

    /**
     * 根据英文名称md5查询
     * @param enNameMd5
     * @return
     */
    default List<DataCompanyMd5DO> getByEnNameMd5(String enNameMd5) {
        Assert.notNull(enNameMd5,"enNameMd5 不能为空");
        return list(Wrappers.<DataCompanyMd5DO>lambdaQuery().eq(DataCompanyMd5DO::getEnNameMd5, enNameMd5));
    }
    /**
     * 根据companyId查询
     * @param companyId
     * @return
     */
    default DataCompanyMd5DO getByCompanyId(Long companyId) {
        Assert.notNull(companyId,"companyId 不能为空");
        return getOne(Wrappers.<DataCompanyMd5DO>lambdaQuery().eq(DataCompanyMd5DO::getCompanyId, companyId));
    }
    /**
     * 根据统一社会信用代码md5查询
     * @param usccMd5
     * @return
     */
    default DataCompanyMd5DO getByUsccMd5(String usccMd5) {
        Assert.notNull(usccMd5,"usccMd5 不能为空");
        return getOne(Wrappers.<DataCompanyMd5DO>lambdaQuery().eq(DataCompanyMd5DO::getUsccMd5, usccMd5));
    }



    /**
     * 根据统一社会信用代码md5查询多个
     * @param usccMd5s
     * @return
     */
    default List<DataCompanyMd5DO> getByUsccMd5s(List<String> usccMd5s) {
        Assert.notEmpty(usccMd5s,"usccMd5s 不能为空");
        return list(Wrappers.<DataCompanyMd5DO>lambdaQuery().in(DataCompanyMd5DO::getUsccMd5, usccMd5s));
    }
            

    /**
     * 根据注册号md5查询
     * @param regNoMd5
     * @return
     */
    default DataCompanyMd5DO getByRegNoMd5(String regNoMd5) {
        Assert.notNull(regNoMd5,"regNoMd5 不能为空");
        return getOne(Wrappers.<DataCompanyMd5DO>lambdaQuery().eq(DataCompanyMd5DO::getRegNoMd5, regNoMd5));
    }



    /**
     * 根据注册号md5查询多个
     * @param regNoMd5s
     * @return
     */
    default List<DataCompanyMd5DO> getByRegNoMd5s(List<String> regNoMd5s) {
        Assert.notEmpty(regNoMd5s,"regNoMd5s 不能为空");
        return list(Wrappers.<DataCompanyMd5DO>lambdaQuery().in(DataCompanyMd5DO::getRegNoMd5, regNoMd5s));
    }
            

    /**
     * 根据组织机构代码md5查询
     * @param orgCodeMd5
     * @return
     */
    default DataCompanyMd5DO getByOrgCodeMd5(String orgCodeMd5) {
        Assert.notNull(orgCodeMd5,"orgCodeMd5 不能为空");
        return getOne(Wrappers.<DataCompanyMd5DO>lambdaQuery().eq(DataCompanyMd5DO::getOrgCodeMd5, orgCodeMd5));
    }



    /**
     * 根据组织机构代码md5查询多个
     * @param orgCodeMd5s
     * @return
     */
    default List<DataCompanyMd5DO> getByOrgCodeMd5s(List<String> orgCodeMd5s) {
        Assert.notEmpty(orgCodeMd5s,"orgCodeMd5s 不能为空");
        return list(Wrappers.<DataCompanyMd5DO>lambdaQuery().in(DataCompanyMd5DO::getOrgCodeMd5, orgCodeMd5s));
    }
            









}
