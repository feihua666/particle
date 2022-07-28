package com.particle.func.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.func.client.dto.command.FuncGroupPageQueryCommand;
import com.particle.func.client.dto.command.FuncGroupQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.func.client.dto.data.FuncGroupVO;
import com.particle.func.domain.FuncGroup;
import com.particle.func.domain.FuncGroupId;
import com.particle.func.infrastructure.dos.FuncGroupDO;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 功能组 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class FuncGroupAppStructMapping implements IBaseQueryCommandMapStruct<FuncGroupDO> {
	public static FuncGroupAppStructMapping instance = Mappers.getMapper( FuncGroupAppStructMapping.class );

	protected Long map(FuncGroupId funcGroupId){
		if (funcGroupId == null) {
			return null;
		}
		return funcGroupId.getId();
	}
	/**
	 * 功能组领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FuncGroupAppStructMapping#map(FuncGroupId)}
	 * @param funcGroup
	 * @return
	 */
	public abstract FuncGroupVO toFuncGroupVO(FuncGroup funcGroup);


	/**
	 * 数据对象转视图对象
	 * @param funcGroupDO
	 * @return
	 */
	public abstract FuncGroupVO funcGroupDOToFuncGroupVO(FuncGroupDO funcGroupDO);

	/**
	 * 批量转换
	 * @param funcGroupDOs
	 * @return
	 */
	public abstract List<FuncGroupVO> funcGroupDOsToFuncGroupVOs(List<FuncGroupDO> funcGroupDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<FuncGroupVO> infrastructurePageToPageResponse(Page<FuncGroupDO> page) {
		return PageResponse.of(funcGroupDOsToFuncGroupVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
	@Override
	public FuncGroupDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof FuncGroupPageQueryCommand) {
			return pageQueryCommandToDO((FuncGroupPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof FuncGroupQueryListCommand) {
			return QueryListCommandToDO(((FuncGroupQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract FuncGroupDO pageQueryCommandToDO(FuncGroupPageQueryCommand FuncGroupPageQueryCommand);

	public abstract FuncGroupDO QueryListCommandToDO(FuncGroupQueryListCommand FuncGroupQueryCommand);
}
