package com.particle.func.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.func.client.dto.command.FuncPageQueryCommand;
import com.particle.func.client.dto.command.FuncQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.func.client.dto.data.FuncVO;
import com.particle.func.domain.Func;
import com.particle.func.domain.FuncId;
import com.particle.func.infrastructure.dos.FuncDO;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 菜单功能 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class FuncAppStructMapping implements IBaseQueryCommandMapStruct<FuncDO> {
	public static FuncAppStructMapping instance = Mappers.getMapper( FuncAppStructMapping.class );

	protected Long map(FuncId funcId){
		if (funcId == null) {
			return null;
		}
		return funcId.getId();
	}
	/**
	 * 菜单功能领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FuncAppStructMapping#map(FuncId)}
	 * @param func
	 * @return
	 */
	public abstract FuncVO toFuncVO(Func func);


	/**
	 * 数据对象转视图对象
	 * @param funcDO
	 * @return
	 */
	public abstract FuncVO funcDOToFuncVO(FuncDO funcDO);

	/**
	 * 批量转换
	 * @param funcDOs
	 * @return
	 */
	public abstract List<FuncVO> funcDOsToFuncVOs(List<FuncDO> funcDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<FuncVO> infrastructurePageToPageResponse(Page<FuncDO> page) {
		return PageResponse.of(funcDOsToFuncVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}

	@Override
	public FuncDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof FuncPageQueryCommand) {
			return pageQueryCommandToDO((FuncPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof FuncQueryListCommand) {
			return QueryListCommandToDO(((FuncQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract FuncDO pageQueryCommandToDO(FuncPageQueryCommand FuncPageQueryCommand);

	public abstract FuncDO QueryListCommandToDO(FuncQueryListCommand FuncQueryCommand);
}
