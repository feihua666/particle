package com.particle.lowcode.app.generator.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentGenRenderGenCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentTemplateRenderCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeSegmentTemplatePageQueryCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeSegmentTemplateQueryListCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentGenRenderGenVO;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentTemplateRenderVO;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentTemplateVO;
import com.particle.lowcode.domain.generator.LowcodeSegmentTemplate;
import com.particle.lowcode.domain.generator.LowcodeSegmentTemplateId;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeSegmentTemplateDO;
import com.particle.lowcode.infrastructure.generator.dto.LowcodeSegmentTemplateRenderParam;
import com.particle.lowcode.infrastructure.generator.dto.LowcodeSegmentTemplateRenderResult;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 低代码片段模板 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-01-06
 */

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE,imports = Optional.class)
public abstract class LowcodeSegmentTemplateAppStructMapping  implements IBaseQueryCommandMapStruct<LowcodeSegmentTemplateDO>{
	public static LowcodeSegmentTemplateAppStructMapping instance = Mappers.getMapper( LowcodeSegmentTemplateAppStructMapping.class );

	protected Long map(LowcodeSegmentTemplateId lowcodeSegmentTemplateId){
		if (lowcodeSegmentTemplateId == null) {
			return null;
		}
		return lowcodeSegmentTemplateId.getId();
	}
	/**
	 * 低代码片段模板领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link LowcodeSegmentTemplateAppStructMapping#map(LowcodeSegmentTemplateId)}
	 * @param lowcodeSegmentTemplate
	 * @return
	 */
	public abstract LowcodeSegmentTemplateVO toLowcodeSegmentTemplateVO(LowcodeSegmentTemplate lowcodeSegmentTemplate);


	/**
	 * 数据对象转视图对象
	 * @param lowcodeSegmentTemplateDO
	 * @return
	 */
	public abstract LowcodeSegmentTemplateVO lowcodeSegmentTemplateDOToLowcodeSegmentTemplateVO(LowcodeSegmentTemplateDO lowcodeSegmentTemplateDO);

	/**
	 * 批量转换
	 * @param lowcodeSegmentTemplateDOs
	 * @return
	 */
	public abstract List<LowcodeSegmentTemplateVO> lowcodeSegmentTemplateDOsToLowcodeSegmentTemplateVOs(List<LowcodeSegmentTemplateDO> lowcodeSegmentTemplateDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<LowcodeSegmentTemplateVO> infrastructurePageToPageResponse(Page<LowcodeSegmentTemplateDO> page) {
		return PageResponse.of(lowcodeSegmentTemplateDOsToLowcodeSegmentTemplateVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public LowcodeSegmentTemplateDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof LowcodeSegmentTemplatePageQueryCommand) {
			return pageQueryCommandToDO((LowcodeSegmentTemplatePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof LowcodeSegmentTemplateQueryListCommand) {
			return queryListCommandToDO(((LowcodeSegmentTemplateQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract LowcodeSegmentTemplateDO pageQueryCommandToDO(LowcodeSegmentTemplatePageQueryCommand lowcodeSegmentTemplatePageQueryCommand);

	public abstract LowcodeSegmentTemplateDO queryListCommandToDO(LowcodeSegmentTemplateQueryListCommand lowcodeSegmentTemplateQueryListCommand);

	public abstract LowcodeSegmentTemplateRenderParam lowcodeSegmentTemplateRenderCommandToLowcodeSegmentTemplateRenderParam(LowcodeSegmentTemplateRenderCommand lowcodeSegmentTemplateRenderCommand);

	/**
	 * 注意 {@link LowcodeSegmentTemplateRenderParam#rootSegmentTemplateId} 需要额外处理
	 * @param lowcodeSegmentGenRenderGenCommand
	 * @return
	 */
	public abstract LowcodeSegmentTemplateRenderParam lowcodeSegmentGenRenderGenCommandToLowcodeSegmentTemplateRenderParam(LowcodeSegmentGenRenderGenCommand lowcodeSegmentGenRenderGenCommand);

	@Mapping(target = "templateNameContentResultFile",expression = "java(Optional.ofNullable(lowcodeSegmentTemplateRenderResult.getTemplateNameContentResultFile()).map(i->i.getAbsolutePath()).orElse(null))")
	public abstract LowcodeSegmentTemplateRenderVO lowcodeSegmentTemplateRenderResultToLowcodeSegmentTemplateRenderVO(LowcodeSegmentTemplateRenderResult lowcodeSegmentTemplateRenderResult);

	@Mappings({
			@Mapping(target = "templateNameContentResultFile",expression = "java(Optional.ofNullable(lowcodeSegmentTemplateRenderResult.getTemplateNameContentResultFile()).map(i->i.getAbsolutePath()).orElse(null))"),
			@Mapping(target = "templateNameContentResultFileName",expression = "java(Optional.ofNullable(lowcodeSegmentTemplateRenderResult.getTemplateNameContentResultFile()).map(i->i.getName()).orElse(null))")
	})

	public abstract LowcodeSegmentGenRenderGenVO lowcodeSegmentTemplateRenderResultToLowcodeSegmentGenRenderGenVO(LowcodeSegmentTemplateRenderResult lowcodeSegmentTemplateRenderResult);

}
