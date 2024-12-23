package com.particle.dept.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.dept.client.dto.command.representation.DeptTreePageQueryCommand;
import com.particle.dept.client.dto.command.representation.DeptTreeQueryListCommand;
import com.particle.dept.client.dto.data.DeptTreeVO;
import com.particle.dept.domain.DeptTree;
import com.particle.dept.domain.DeptTreeId;
import com.particle.dept.infrastructure.dos.DeptTreeDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 部门树 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-04-12 11:41:43
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DeptTreeAppStructMapping  implements IBaseQueryCommandMapStruct<DeptTreeDO>{
	public static DeptTreeAppStructMapping instance = Mappers.getMapper( DeptTreeAppStructMapping.class );

	protected Long map(DeptTreeId deptTreeId){
		if (deptTreeId == null) {
			return null;
		}
		return deptTreeId.getId();
	}
	/**
	 * 部门树领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DeptTreeAppStructMapping#map(DeptTreeId)}
	 * @param deptTree
	 * @return
	 */
	public abstract DeptTreeVO toDeptTreeVO(DeptTree deptTree);


	/**
	 * 数据对象转视图对象
	 * @param deptTreeDO
	 * @return
	 */
	public abstract DeptTreeVO deptTreeDOToDeptTreeVO(DeptTreeDO deptTreeDO);

	/**
	 * 批量转换
	 * @param deptTreeDOs
	 * @return
	 */
	public abstract List<DeptTreeVO> deptTreeDOsToDeptTreeVOs(List<DeptTreeDO> deptTreeDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DeptTreeVO> infrastructurePageToPageResponse(Page<DeptTreeDO> page) {
		return PageResponse.of(deptTreeDOsToDeptTreeVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DeptTreeDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DeptTreePageQueryCommand) {
			return pageQueryCommandToDO((DeptTreePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DeptTreeQueryListCommand) {
			return queryListCommandToDO(((DeptTreeQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DeptTreeDO pageQueryCommandToDO(DeptTreePageQueryCommand deptTreePageQueryCommand);

	public abstract DeptTreeDO queryListCommandToDO(DeptTreeQueryListCommand deptTreeQueryListCommand);
}
