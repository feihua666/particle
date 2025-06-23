package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyIprTrademarkDO;
import com.particle.data.infrastructure.company.dto.DataCompanyIprTrademarkListPageByCompanyIdParam;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业知识产权商标 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:14:45
 */
public interface IDataCompanyIprTrademarkService extends IBaseService<DataCompanyIprTrademarkDO> {

    /**
     * 根据申请号查询
     * @param applyNo
     * @return
     */
    default DataCompanyIprTrademarkDO getByApplyNo(String applyNo) {
        Assert.notNull(applyNo,"applyNo 不能为空");
        return getOne(Wrappers.<DataCompanyIprTrademarkDO>lambdaQuery().eq(DataCompanyIprTrademarkDO::getApplyNo, applyNo));
    }



    /**
     * 根据申请号查询多个
     * @param applyNos
     * @return
     */
    default List<DataCompanyIprTrademarkDO> getByApplyNos(List<String> applyNos) {
        Assert.notEmpty(applyNos,"applyNos 不能为空");
        return list(Wrappers.<DataCompanyIprTrademarkDO>lambdaQuery().in(DataCompanyIprTrademarkDO::getApplyNo, applyNos));
    }



    /**
     * 根据注册号查询
     * @param regNo
     * @return
     */
    default DataCompanyIprTrademarkDO getByRegNo(String regNo) {
        Assert.notNull(regNo,"regNo 不能为空");
        return getOne(Wrappers.<DataCompanyIprTrademarkDO>lambdaQuery().eq(DataCompanyIprTrademarkDO::getRegNo, regNo));
    }



    /**
     * 根据注册号查询多个
     * @param regNos
     * @return
     */
    default List<DataCompanyIprTrademarkDO> getByRegNos(List<String> regNos) {
        Assert.notEmpty(regNos,"regNos 不能为空");
        return list(Wrappers.<DataCompanyIprTrademarkDO>lambdaQuery().in(DataCompanyIprTrademarkDO::getRegNo, regNos));
    }

    /**
     * 分页查询商标信息
     * @param param
     * @param pageQueryForm
     * @return
     */
    public Page<DataCompanyIprTrademarkDO> listPage(DataCompanyIprTrademarkListPageByCompanyIdParam param, PageQueryCommand pageQueryForm);
    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param applyNo
     * @param applyNo
     * @param regNo
     * @return
     */
    default public Page<DataCompanyIprTrademarkDO> listPageByCompanyId(Long companyId, String applyNo,String regNo, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        return listPage(DataCompanyIprTrademarkListPageByCompanyIdParam.create(companyId,
                applyNo, regNo), pageQueryForm);
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyIprTrademarkDO>lambdaUpdate().eq(DataCompanyIprTrademarkDO::getId, id)
                .set(DataCompanyIprTrademarkDO::getLatestHandleAt, LocalDateTime.now()));
    }

}
