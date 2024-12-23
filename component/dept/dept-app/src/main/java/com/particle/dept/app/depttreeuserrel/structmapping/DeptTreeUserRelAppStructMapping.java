package com.particle.dept.app.depttreeuserrel.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.dept.client.depttreeuserrel.dto.command.representation.DeptTreeUserRelPageQueryCommand;
import com.particle.dept.client.depttreeuserrel.dto.command.representation.DeptTreeUserRelQueryListCommand;
import com.particle.dept.client.depttreeuserrel.dto.data.DeptTreeUserRelVO;
import com.particle.dept.domain.depttreeuserrel.DeptTreeUserRel;
import com.particle.dept.domain.depttreeuserrel.DeptTreeUserRelId;
import com.particle.dept.infrastructure.depttreeuserrel.dos.DeptTreeUserRelDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 部门树用户关系 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:26:06
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DeptTreeUserRelAppStructMapping  implements IBaseQueryCommandMapStruct<DeptTreeUserRelDO>{
	public static DeptTreeUserRelAppStructMapping instance = Mappers.getMapper( DeptTreeUserRelAppStructMapping.class );

	protected Long map(DeptTreeUserRelId deptTreeUserRelId){
		if (deptTreeUserRelId == null) {
			return null;
		}
		return deptTreeUserRelId.getId();
	}
	/**
	 * 部门树用户关系领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DeptTreeUserRelAppStructMapping#map(DeptTreeUserRelId)}
	 * @param deptTreeUserRel
	 * @return
	 */
	public abstract DeptTreeUserRelVO toDeptTreeUserRelVO(DeptTreeUserRel deptTreeUserRel);


	/**
	 * 数据对象转视图对象
	 * @param deptTreeUserRelDO
	 * @return
	 */
	public abstract DeptTreeUserRelVO deptTreeUserRelDOToDeptTreeUserRelVO(DeptTreeUserRelDO deptTreeUserRelDO);

	/**
	 * 批量转换
	 * @param deptTreeUserRelDOs
	 * @return
	 */
	public abstract List<DeptTreeUserRelVO> deptTreeUserRelDOsToDeptTreeUserRelVOs(List<DeptTreeUserRelDO> deptTreeUserRelDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DeptTreeUserRelVO> infrastructurePageToPageResponse(Page<DeptTreeUserRelDO> page) {
		return PageResponse.of(deptTreeUserRelDOsToDeptTreeUserRelVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DeptTreeUserRelDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DeptTreeUserRelPageQueryCommand) {
			return pageQueryCommandToDO((DeptTreeUserRelPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DeptTreeUserRelQueryListCommand) {
			return queryListCommandToDO(((DeptTreeUserRelQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DeptTreeUserRelDO pageQueryCommandToDO(DeptTreeUserRelPageQueryCommand deptTreeUserRelPageQueryCommand);

	public abstract DeptTreeUserRelDO queryListCommandToDO(DeptTreeUserRelQueryListCommand deptTreeUserRelQueryListCommand);
}
