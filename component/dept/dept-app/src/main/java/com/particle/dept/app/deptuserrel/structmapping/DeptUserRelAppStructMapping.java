package com.particle.dept.app.deptuserrel.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.dept.client.deptuserrel.dto.command.representation.DeptUserRelPageQueryCommand;
import com.particle.dept.client.deptuserrel.dto.command.representation.DeptUserRelQueryListCommand;
import com.particle.dept.client.deptuserrel.dto.data.DeptUserRelVO;
import com.particle.dept.domain.deptuserrel.DeptUserRel;
import com.particle.dept.domain.deptuserrel.DeptUserRelId;
import com.particle.dept.infrastructure.deptuserrel.dos.DeptUserRelDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 部门用户关系 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-05-17 10:28:42
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DeptUserRelAppStructMapping  implements IBaseQueryCommandMapStruct<DeptUserRelDO>{
	public static DeptUserRelAppStructMapping instance = Mappers.getMapper( DeptUserRelAppStructMapping.class );

	protected Long map(DeptUserRelId deptUserRelId){
		if (deptUserRelId == null) {
			return null;
		}
		return deptUserRelId.getId();
	}
	/**
	 * 部门用户关系领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DeptUserRelAppStructMapping#map(DeptUserRelId)}
	 * @param deptUserRel
	 * @return
	 */
	public abstract DeptUserRelVO toDeptUserRelVO(DeptUserRel deptUserRel);


	/**
	 * 数据对象转视图对象
	 * @param deptUserRelDO
	 * @return
	 */
	public abstract DeptUserRelVO deptUserRelDOToDeptUserRelVO(DeptUserRelDO deptUserRelDO);

	/**
	 * 批量转换
	 * @param deptUserRelDOs
	 * @return
	 */
	public abstract List<DeptUserRelVO> deptUserRelDOsToDeptUserRelVOs(List<DeptUserRelDO> deptUserRelDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DeptUserRelVO> infrastructurePageToPageResponse(Page<DeptUserRelDO> page) {
		return PageResponse.of(deptUserRelDOsToDeptUserRelVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DeptUserRelDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DeptUserRelPageQueryCommand) {
			return pageQueryCommandToDO((DeptUserRelPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DeptUserRelQueryListCommand) {
			return queryListCommandToDO(((DeptUserRelQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DeptUserRelDO pageQueryCommandToDO(DeptUserRelPageQueryCommand deptUserRelPageQueryCommand);

	public abstract DeptUserRelDO queryListCommandToDO(DeptUserRelQueryListCommand deptUserRelQueryListCommand);
}
