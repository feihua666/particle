package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyIprSoftwareCopyrightDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业知识产权软件著作 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:17:01
 */
public interface IDataCompanyIprSoftwareCopyrightService extends IBaseService<DataCompanyIprSoftwareCopyrightDO> {

    /**
     * 根据注册号查询
     * @param regNo
     * @return
     */
    default DataCompanyIprSoftwareCopyrightDO getByRegNo(String regNo) {
        Assert.notNull(regNo,"regNo 不能为空");
        return getOne(Wrappers.<DataCompanyIprSoftwareCopyrightDO>lambdaQuery().eq(DataCompanyIprSoftwareCopyrightDO::getRegNo, regNo));
    }



    /**
     * 根据注册号查询多个
     * @param regNos
     * @return
     */
    default List<DataCompanyIprSoftwareCopyrightDO> getByRegNos(List<String> regNos) {
        Assert.notEmpty(regNos,"regNos 不能为空");
        return list(Wrappers.<DataCompanyIprSoftwareCopyrightDO>lambdaQuery().in(DataCompanyIprSoftwareCopyrightDO::getRegNo, regNos));
    }


    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param regNo
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyIprSoftwareCopyrightDO> listPageByCopyrightOwnerCompanyId(Long companyId, String regNo, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyIprSoftwareCopyrightDO>lambdaQuery()
                .eq(DataCompanyIprSoftwareCopyrightDO::getCopyrightOwnerCompanyId, companyId)
                .eq(regNo != null,DataCompanyIprSoftwareCopyrightDO::getRegNo, regNo)
        );
    }

    /**
     * 根据 公司id 和 regNo 查询
     * @param executedPersonCompanyId
     * @param regNo
     * @return
     */
    default public DataCompanyIprSoftwareCopyrightDO getByCopyrightOwnerCompanyIdAndRegNo(Long executedPersonCompanyId,String regNo) {
        Assert.notNull(executedPersonCompanyId,"executedPersonCompanyId 不能为空");
        Assert.notEmpty(regNo,"regNo 不能为空");
        return getOne(Wrappers.<DataCompanyIprSoftwareCopyrightDO>lambdaQuery()
                .eq(DataCompanyIprSoftwareCopyrightDO::getCopyrightOwnerCompanyId, executedPersonCompanyId)
                .eq(DataCompanyIprSoftwareCopyrightDO::getRegNo, regNo)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyIprSoftwareCopyrightDO>lambdaUpdate().eq(DataCompanyIprSoftwareCopyrightDO::getId, id)
                .set(DataCompanyIprSoftwareCopyrightDO::getLatestHandleAt, LocalDateTime.now()));
    }

}
