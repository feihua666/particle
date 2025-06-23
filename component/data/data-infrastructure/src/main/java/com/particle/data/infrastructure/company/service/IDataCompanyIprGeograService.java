package com.particle.data.infrastructure.company.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.infrastructure.company.dos.DataCompanyIprGeograDO;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.crud.IBaseService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 企业知识产权地理标识 服务类
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:16:33
 */
public interface IDataCompanyIprGeograService extends IBaseService<DataCompanyIprGeograDO> {

    /**
     * 根据公告号查询
     * @param publicNo
     * @return
     */
    default DataCompanyIprGeograDO getByPublicNo(String publicNo) {
        Assert.notNull(publicNo,"publicNo 不能为空");
        return getOne(Wrappers.<DataCompanyIprGeograDO>lambdaQuery().eq(DataCompanyIprGeograDO::getPublicNo, publicNo));
    }



    /**
     * 根据公告号查询多个
     * @param publicNos
     * @return
     */
    default List<DataCompanyIprGeograDO> getByPublicNos(List<String> publicNos) {
        Assert.notEmpty(publicNos,"publicNos 不能为空");
        return list(Wrappers.<DataCompanyIprGeograDO>lambdaQuery().in(DataCompanyIprGeograDO::getPublicNo, publicNos));
    }


    /**
     * 根据企业表ID分页查询列表
     * @param companyId
     * @param publicNo
     * @param pageQueryForm
     * @return
     */
    default public Page<DataCompanyIprGeograDO> listPageByApplicantNameCompanyId(Long companyId, String publicNo, PageQueryCommand pageQueryForm){
        Assert.notNull(companyId,"companyId 不能为空");
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery, Wrappers.<DataCompanyIprGeograDO>lambdaQuery()
                .eq(DataCompanyIprGeograDO::getApplicantNameCompanyId, companyId)
                .eq(publicNo != null,DataCompanyIprGeograDO::getPublicNo, publicNo)
        );
    }

    /**
     * 根据 公司id 和 publicNo 查询
     * @param executedPersonCompanyId
     * @param publicNo
     * @return
     */
    default public DataCompanyIprGeograDO getByApplicantNameCompanyIdAndPublicNo(Long executedPersonCompanyId,String publicNo) {
        Assert.notNull(executedPersonCompanyId,"executedPersonCompanyId 不能为空");
        Assert.notEmpty(publicNo,"publicNo 不能为空");
        return getOne(Wrappers.<DataCompanyIprGeograDO>lambdaQuery()
                .eq(DataCompanyIprGeograDO::getApplicantNameCompanyId, executedPersonCompanyId)
                .eq(DataCompanyIprGeograDO::getPublicNo, publicNo)
        );
    }

    /**
     * 更新最后处理时间
     * @param id
     * @return
     */
    default boolean updateLatestHandleAt(Long id) {
        Assert.notNull(id,"id 不能为空");
        return update(Wrappers.<DataCompanyIprGeograDO>lambdaUpdate().eq(DataCompanyIprGeograDO::getId, id)
                .set(DataCompanyIprGeograDO::getLatestHandleAt, LocalDateTime.now()));
    }


}
