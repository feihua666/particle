package com.particle.dept.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.dept.client.dto.command.representation.DeptPageQueryCommand;
import com.particle.dept.client.dto.command.representation.DeptQueryListCommand;
import com.particle.dept.client.dto.data.DeptVO;
import com.particle.dept.domain.Dept;
import com.particle.dept.domain.DeptId;
import com.particle.dept.infrastructure.dos.DeptDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 部门 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-12 14:19:42
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DeptAppStructMapping  implements IBaseQueryCommandMapStruct<DeptDO>{
	public static DeptAppStructMapping instance = Mappers.getMapper( DeptAppStructMapping.class );

	protected Long map(DeptId deptId){
		if (deptId == null) {
			return null;
		}
		return deptId.getId();
	}
	/**
	 * 部门领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DeptAppStructMapping#map(DeptId)}
	 * @param dept
	 * @return
	 */
	public abstract DeptVO toDeptVO(Dept dept);


	/**
	 * 数据对象转视图对象
	 * @param deptDO
	 * @return
	 */
	public abstract DeptVO deptDOToDeptVO(DeptDO deptDO);

	/**
	 * 批量转换
	 * @param deptDOs
	 * @return
	 */
	public abstract List<DeptVO> deptDOsToDeptVOs(List<DeptDO> deptDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DeptVO> infrastructurePageToPageResponse(Page<DeptDO> page) {
		return PageResponse.of(deptDOsToDeptVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DeptDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DeptPageQueryCommand) {
			return pageQueryCommandToDO((DeptPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DeptQueryListCommand) {
			return queryListCommandToDO(((DeptQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DeptDO pageQueryCommandToDO(DeptPageQueryCommand deptPageQueryCommand);

	public abstract DeptDO queryListCommandToDO(DeptQueryListCommand deptQueryListCommand);
}
