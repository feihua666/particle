package com.particle.func.app.application.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.func.client.application.dto.command.representation.FuncApplicationPageQueryCommand;
import com.particle.func.client.application.dto.command.representation.FuncApplicationQueryListCommand;
import com.particle.func.client.application.dto.data.FuncApplicationVO;
import com.particle.func.domain.application.FuncApplication;
import com.particle.func.domain.application.FuncApplicationId;
import com.particle.func.infrastructure.application.dos.FuncApplicationDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 功能应用 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class FuncApplicationAppStructMapping  implements IBaseQueryCommandMapStruct<FuncApplicationDO>{
	public static FuncApplicationAppStructMapping instance = Mappers.getMapper( FuncApplicationAppStructMapping.class );

	protected Long map(FuncApplicationId funcApplicationId){
		if (funcApplicationId == null) {
			return null;
		}
		return funcApplicationId.getId();
	}
	/**
	 * 功能应用领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FuncApplicationAppStructMapping#map(FuncApplicationId)}
	 * @param funcApplication
	 * @return
	 */
	public abstract FuncApplicationVO toFuncApplicationVO(FuncApplication funcApplication);


	/**
	 * 数据对象转视图对象
	 * @param funcApplicationDO
	 * @return
	 */
	public abstract FuncApplicationVO funcApplicationDOToFuncApplicationVO(FuncApplicationDO funcApplicationDO);

	/**
	 * 批量转换
	 * @param funcApplicationDOs
	 * @return
	 */
	public abstract List<FuncApplicationVO> funcApplicationDOsToFuncApplicationVOs(List<FuncApplicationDO> funcApplicationDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<FuncApplicationVO> infrastructurePageToPageResponse(Page<FuncApplicationDO> page) {
		return PageResponse.of(funcApplicationDOsToFuncApplicationVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public FuncApplicationDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof FuncApplicationPageQueryCommand) {
			return pageQueryCommandToDO((FuncApplicationPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof FuncApplicationQueryListCommand) {
			return queryListCommandToDO(((FuncApplicationQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract FuncApplicationDO pageQueryCommandToDO(FuncApplicationPageQueryCommand funcApplicationPageQueryCommand);

	public abstract FuncApplicationDO queryListCommandToDO(FuncApplicationQueryListCommand funcApplicationQueryListCommand);
}
