package com.particle.dict.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.dict.client.dto.command.DictPageQueryCommand;
import com.particle.dict.client.dto.command.DictQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.dict.domain.Dict;
import com.particle.dict.domain.DictId;
import com.particle.dict.infrastructure.dos.DictDO;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 字典 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DictAppStructMapping  implements IBaseQueryCommandMapStruct<DictDO> {
	public static DictAppStructMapping instance = Mappers.getMapper( DictAppStructMapping.class );

	protected Long map(DictId dictId){
		if (dictId == null) {
			return null;
		}
		return dictId.getId();
	}
	/**
	 * 字典领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DictAppStructMapping#map(DictId)}
	 * @param dict
	 * @return
	 */
	public abstract DictVO toDictVO(Dict dict);


	/**
	 * 数据对象转视图对象
	 * @param dictDO
	 * @return
	 */
	public abstract DictVO dictDOToDictVO(DictDO dictDO);

	/**
	 * 批量转换
	 * @param dictDOs
	 * @return
	 */
	public abstract List<DictVO> dictDOsToDictVOs(List<DictDO> dictDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DictVO> infrastructurePageToPageResponse(Page<DictDO> page) {
		return PageResponse.of(dictDOsToDictVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DictDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DictPageQueryCommand) {
			return pageQueryCommandToDO((DictPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DictQueryListCommand) {
			return QueryListCommandToDO(((DictQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DictDO pageQueryCommandToDO(DictPageQueryCommand DictPageQueryCommand);

	public abstract DictDO QueryListCommandToDO(DictQueryListCommand DictQueryCommand);
}
