package com.particle.dept.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.dept.client.dto.command.representation.DeptTreeNamePageQueryCommand;
import com.particle.dept.client.dto.command.representation.DeptTreeNameQueryListCommand;
import com.particle.dept.client.dto.data.DeptTreeNameVO;
import com.particle.dept.domain.DeptTreeName;
import com.particle.dept.domain.DeptTreeNameId;
import com.particle.dept.infrastructure.dos.DeptTreeNameDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 部门树名称 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:42:10
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DeptTreeNameAppStructMapping  implements IBaseQueryCommandMapStruct<DeptTreeNameDO>{
	public static DeptTreeNameAppStructMapping instance = Mappers.getMapper( DeptTreeNameAppStructMapping.class );

	protected Long map(DeptTreeNameId deptTreeNameId){
		if (deptTreeNameId == null) {
			return null;
		}
		return deptTreeNameId.getId();
	}
	/**
	 * 部门树名称领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DeptTreeNameAppStructMapping#map(DeptTreeNameId)}
	 * @param deptTreeName
	 * @return
	 */
	public abstract DeptTreeNameVO toDeptTreeNameVO(DeptTreeName deptTreeName);


	/**
	 * 数据对象转视图对象
	 * @param deptTreeNameDO
	 * @return
	 */
	public abstract DeptTreeNameVO deptTreeNameDOToDeptTreeNameVO(DeptTreeNameDO deptTreeNameDO);

	/**
	 * 批量转换
	 * @param deptTreeNameDOs
	 * @return
	 */
	public abstract List<DeptTreeNameVO> deptTreeNameDOsToDeptTreeNameVOs(List<DeptTreeNameDO> deptTreeNameDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DeptTreeNameVO> infrastructurePageToPageResponse(Page<DeptTreeNameDO> page) {
		return PageResponse.of(deptTreeNameDOsToDeptTreeNameVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DeptTreeNameDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DeptTreeNamePageQueryCommand) {
			return pageQueryCommandToDO((DeptTreeNamePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DeptTreeNameQueryListCommand) {
			return queryListCommandToDO(((DeptTreeNameQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DeptTreeNameDO pageQueryCommandToDO(DeptTreeNamePageQueryCommand deptTreeNamePageQueryCommand);

	public abstract DeptTreeNameDO queryListCommandToDO(DeptTreeNameQueryListCommand deptTreeNameQueryListCommand);
}
