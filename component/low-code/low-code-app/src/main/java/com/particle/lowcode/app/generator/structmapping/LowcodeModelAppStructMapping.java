package com.particle.lowcode.app.generator.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelVO;
import com.particle.lowcode.domain.generator.LowcodeModel;
import com.particle.lowcode.domain.generator.LowcodeModelId;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeModelDO;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeModelPageQueryCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeModelQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 低代码模型 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class LowcodeModelAppStructMapping  implements IBaseQueryCommandMapStruct<LowcodeModelDO>{
	public static LowcodeModelAppStructMapping instance = Mappers.getMapper( LowcodeModelAppStructMapping.class );

	protected Long map(LowcodeModelId lowcodeModelId){
		if (lowcodeModelId == null) {
			return null;
		}
		return lowcodeModelId.getId();
	}
	/**
	 * 低代码模型领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link LowcodeModelAppStructMapping#map(LowcodeModelId)}
	 * @param lowcodeModel
	 * @return
	 */
	public abstract LowcodeModelVO toLowcodeModelVO(LowcodeModel lowcodeModel);


	/**
	 * 数据对象转视图对象
	 * @param lowcodeModelDO
	 * @return
	 */
	public abstract LowcodeModelVO lowcodeModelDOToLowcodeModelVO(LowcodeModelDO lowcodeModelDO);

	/**
	 * 批量转换
	 * @param lowcodeModelDOs
	 * @return
	 */
	public abstract List<LowcodeModelVO> lowcodeModelDOsToLowcodeModelVOs(List<LowcodeModelDO> lowcodeModelDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<LowcodeModelVO> infrastructurePageToPageResponse(Page<LowcodeModelDO> page) {
		return PageResponse.of(lowcodeModelDOsToLowcodeModelVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public LowcodeModelDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof LowcodeModelPageQueryCommand) {
			return pageQueryCommandToDO((LowcodeModelPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof LowcodeModelQueryListCommand) {
			return queryListCommandToDO(((LowcodeModelQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract LowcodeModelDO pageQueryCommandToDO(LowcodeModelPageQueryCommand lowcodeModelPageQueryCommand);

	public abstract LowcodeModelDO queryListCommandToDO(LowcodeModelQueryListCommand lowcodeModelQueryListCommand);
}
