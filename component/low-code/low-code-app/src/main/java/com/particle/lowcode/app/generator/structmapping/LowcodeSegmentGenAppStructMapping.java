package com.particle.lowcode.app.generator.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeSegmentGenPageQueryCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeSegmentGenQueryListCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentGenVO;
import com.particle.lowcode.domain.generator.LowcodeSegmentGen;
import com.particle.lowcode.domain.generator.LowcodeSegmentGenId;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeSegmentGenDO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 低代码生成 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-02-10
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class LowcodeSegmentGenAppStructMapping  implements IBaseQueryCommandMapStruct<LowcodeSegmentGenDO>{
	public static LowcodeSegmentGenAppStructMapping instance = Mappers.getMapper( LowcodeSegmentGenAppStructMapping.class );

	protected Long map(LowcodeSegmentGenId lowcodeSegmentGenId){
		if (lowcodeSegmentGenId == null) {
			return null;
		}
		return lowcodeSegmentGenId.getId();
	}
	/**
	 * 低代码生成领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link LowcodeSegmentGenAppStructMapping#map(LowcodeSegmentGenId)}
	 * @param lowcodeSegmentGen
	 * @return
	 */
	public abstract LowcodeSegmentGenVO toLowcodeSegmentGenVO(LowcodeSegmentGen lowcodeSegmentGen);


	/**
	 * 数据对象转视图对象
	 * @param lowcodeSegmentGenDO
	 * @return
	 */
	public abstract LowcodeSegmentGenVO lowcodeSegmentGenDOToLowcodeSegmentGenVO(LowcodeSegmentGenDO lowcodeSegmentGenDO);

	/**
	 * 批量转换
	 * @param lowcodeSegmentGenDOs
	 * @return
	 */
	public abstract List<LowcodeSegmentGenVO> lowcodeSegmentGenDOsToLowcodeSegmentGenVOs(List<LowcodeSegmentGenDO> lowcodeSegmentGenDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<LowcodeSegmentGenVO> infrastructurePageToPageResponse(Page<LowcodeSegmentGenDO> page) {
		return PageResponse.of(lowcodeSegmentGenDOsToLowcodeSegmentGenVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public LowcodeSegmentGenDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof LowcodeSegmentGenPageQueryCommand) {
			return pageQueryCommandToDO((LowcodeSegmentGenPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof LowcodeSegmentGenQueryListCommand) {
			return queryListCommandToDO(((LowcodeSegmentGenQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract LowcodeSegmentGenDO pageQueryCommandToDO(LowcodeSegmentGenPageQueryCommand lowcodeSegmentGenPageQueryCommand);

	public abstract LowcodeSegmentGenDO queryListCommandToDO(LowcodeSegmentGenQueryListCommand lowcodeSegmentGenQueryListCommand);
}
