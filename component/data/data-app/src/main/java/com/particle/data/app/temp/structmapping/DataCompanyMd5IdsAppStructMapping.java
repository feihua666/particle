package com.particle.data.app.temp.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.temp.dto.command.representation.DataCompanyMd5IdsPageQueryCommand;
import com.particle.data.client.temp.dto.command.representation.DataCompanyMd5IdsQueryListCommand;
import com.particle.data.client.temp.dto.data.DataCompanyMd5IdsVO;
import com.particle.data.domain.temp.DataCompanyMd5Ids;
import com.particle.data.domain.temp.DataCompanyMd5IdsId;
import com.particle.data.infrastructure.temp.dos.DataCompanyMd5IdsDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业md5ids app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:24:11
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyMd5IdsAppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyMd5IdsDO>{
	public static DataCompanyMd5IdsAppStructMapping instance = Mappers.getMapper( DataCompanyMd5IdsAppStructMapping.class );

	protected Long map(DataCompanyMd5IdsId dataCompanyMd5IdsId){
		if (dataCompanyMd5IdsId == null) {
			return null;
		}
		return dataCompanyMd5IdsId.getId();
	}
	/**
	 * 企业md5ids领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyMd5IdsAppStructMapping#map(DataCompanyMd5IdsId)}
	 * @param dataCompanyMd5Ids
	 * @return
	 */
	public abstract DataCompanyMd5IdsVO toDataCompanyMd5IdsVO(DataCompanyMd5Ids dataCompanyMd5Ids);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyMd5IdsDO
	 * @return
	 */
	public abstract DataCompanyMd5IdsVO dataCompanyMd5IdsDOToDataCompanyMd5IdsVO(DataCompanyMd5IdsDO dataCompanyMd5IdsDO);

	/**
	 * 批量转换
	 * @param dataCompanyMd5IdsDOs
	 * @return
	 */
	public abstract List<DataCompanyMd5IdsVO> dataCompanyMd5IdsDOsToDataCompanyMd5IdsVOs(List<DataCompanyMd5IdsDO> dataCompanyMd5IdsDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyMd5IdsVO> infrastructurePageToPageResponse(Page<DataCompanyMd5IdsDO> page) {
		return PageResponse.of(dataCompanyMd5IdsDOsToDataCompanyMd5IdsVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyMd5IdsDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyMd5IdsPageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyMd5IdsPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyMd5IdsQueryListCommand) {
			return queryListCommandToDO(((DataCompanyMd5IdsQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyMd5IdsDO pageQueryCommandToDO(DataCompanyMd5IdsPageQueryCommand dataCompanyMd5IdsPageQueryCommand);

	public abstract DataCompanyMd5IdsDO queryListCommandToDO(DataCompanyMd5IdsQueryListCommand dataCompanyMd5IdsQueryListCommand);
}
