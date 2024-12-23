package com.particle.report.infrastructure.reportapi.mapper;

import com.particle.global.mybatis.plus.crud.IBaseMapper;
import com.particle.report.infrastructure.reportapi.dos.ReportReportApiDO;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 报告接口 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-09-06 16:28:52
 */
@Mapper
public interface ReportReportApiMapper extends IBaseMapper<ReportReportApiDO> {

}
