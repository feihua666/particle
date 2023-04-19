package com.particle.func.app.funcapplicationfuncrel.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.func.client.funcapplicationfuncrel.dto.data.FuncApplicationFuncRelVO;
import com.particle.func.domain.funcapplicationfuncrel.FuncApplicationFuncRel;
import com.particle.func.domain.funcapplicationfuncrel.FuncApplicationFuncRelId;
import com.particle.func.infrastructure.funcapplicationfuncrel.dos.FuncApplicationFuncRelDO;
import com.particle.func.client.funcapplicationfuncrel.dto.command.representation.FuncApplicationFuncRelPageQueryCommand;
import com.particle.func.client.funcapplicationfuncrel.dto.command.representation.FuncApplicationFuncRelQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 功能应用功能关系 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:15:29
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class FuncApplicationFuncRelAppStructMapping  implements IBaseQueryCommandMapStruct<FuncApplicationFuncRelDO>{
	public static FuncApplicationFuncRelAppStructMapping instance = Mappers.getMapper( FuncApplicationFuncRelAppStructMapping.class );

	protected Long map(FuncApplicationFuncRelId funcApplicationFuncRelId){
		if (funcApplicationFuncRelId == null) {
			return null;
		}
		return funcApplicationFuncRelId.getId();
	}
	/**
	 * 功能应用功能关系领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FuncApplicationFuncRelAppStructMapping#map(FuncApplicationFuncRelId)}
	 * @param funcApplicationFuncRel
	 * @return
	 */
	public abstract FuncApplicationFuncRelVO toFuncApplicationFuncRelVO(FuncApplicationFuncRel funcApplicationFuncRel);


	/**
	 * 数据对象转视图对象
	 * @param funcApplicationFuncRelDO
	 * @return
	 */
	public abstract FuncApplicationFuncRelVO funcApplicationFuncRelDOToFuncApplicationFuncRelVO(FuncApplicationFuncRelDO funcApplicationFuncRelDO);

	/**
	 * 批量转换
	 * @param funcApplicationFuncRelDOs
	 * @return
	 */
	public abstract List<FuncApplicationFuncRelVO> funcApplicationFuncRelDOsToFuncApplicationFuncRelVOs(List<FuncApplicationFuncRelDO> funcApplicationFuncRelDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<FuncApplicationFuncRelVO> infrastructurePageToPageResponse(Page<FuncApplicationFuncRelDO> page) {
		return PageResponse.of(funcApplicationFuncRelDOsToFuncApplicationFuncRelVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public FuncApplicationFuncRelDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof FuncApplicationFuncRelPageQueryCommand) {
			return pageQueryCommandToDO((FuncApplicationFuncRelPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof FuncApplicationFuncRelQueryListCommand) {
			return queryListCommandToDO(((FuncApplicationFuncRelQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract FuncApplicationFuncRelDO pageQueryCommandToDO(FuncApplicationFuncRelPageQueryCommand funcApplicationFuncRelPageQueryCommand);

	public abstract FuncApplicationFuncRelDO queryListCommandToDO(FuncApplicationFuncRelQueryListCommand funcApplicationFuncRelQueryListCommand);
}
