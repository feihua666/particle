package com.particle.report.infrastructure.template.mapper;

import com.particle.report.infrastructure.template.dos.ReportSegmentTemplateDO;
import com.particle.global.mybatis.plus.crud.IBaseMapper;
import org.apache.ibatis.annotations.Mapper;
/**
 * <p>
 * 报告片段模板 Mapper 接口
 * </p>
 *
 * @author yw
 * @since 2023-09-05 17:49:31
 */
@Mapper
public interface ReportSegmentTemplateMapper extends IBaseMapper<ReportSegmentTemplateDO> {

}
