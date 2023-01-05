package com.particle.lowcode.app.generator.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.lowcode.client.generator.dto.data.LowcodeModelItemVO;
import com.particle.lowcode.domain.generator.LowcodeModelItem;
import com.particle.lowcode.domain.generator.LowcodeModelItemId;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeModelItemDO;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeModelItemPageQueryCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeModelItemQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 低代码模型项目 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class LowcodeModelItemAppStructMapping  implements IBaseQueryCommandMapStruct<LowcodeModelItemDO>{
	public static LowcodeModelItemAppStructMapping instance = Mappers.getMapper( LowcodeModelItemAppStructMapping.class );

	protected Long map(LowcodeModelItemId lowcodeModelItemId){
		if (lowcodeModelItemId == null) {
			return null;
		}
		return lowcodeModelItemId.getId();
	}
	/**
	 * 低代码模型项目领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link LowcodeModelItemAppStructMapping#map(LowcodeModelItemId)}
	 * @param lowcodeModelItem
	 * @return
	 */
	public abstract LowcodeModelItemVO toLowcodeModelItemVO(LowcodeModelItem lowcodeModelItem);


	/**
	 * 数据对象转视图对象
	 * @param lowcodeModelItemDO
	 * @return
	 */
	public abstract LowcodeModelItemVO lowcodeModelItemDOToLowcodeModelItemVO(LowcodeModelItemDO lowcodeModelItemDO);

	/**
	 * 批量转换
	 * @param lowcodeModelItemDOs
	 * @return
	 */
	public abstract List<LowcodeModelItemVO> lowcodeModelItemDOsToLowcodeModelItemVOs(List<LowcodeModelItemDO> lowcodeModelItemDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<LowcodeModelItemVO> infrastructurePageToPageResponse(Page<LowcodeModelItemDO> page) {
		return PageResponse.of(lowcodeModelItemDOsToLowcodeModelItemVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public LowcodeModelItemDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof LowcodeModelItemPageQueryCommand) {
			return pageQueryCommandToDO((LowcodeModelItemPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof LowcodeModelItemQueryListCommand) {
			return queryListCommandToDO(((LowcodeModelItemQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract LowcodeModelItemDO pageQueryCommandToDO(LowcodeModelItemPageQueryCommand lowcodeModelItemPageQueryCommand);

	public abstract LowcodeModelItemDO queryListCommandToDO(LowcodeModelItemQueryListCommand lowcodeModelItemQueryListCommand);
}
