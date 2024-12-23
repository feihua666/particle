package com.particle.data.app.company.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.data.client.company.dto.command.representation.DataCompanyMd5PageQueryCommand;
import com.particle.data.client.company.dto.command.representation.DataCompanyMd5QueryListCommand;
import com.particle.data.client.company.dto.data.DataCompanyMd5VO;
import com.particle.data.domain.company.DataCompanyMd5;
import com.particle.data.domain.company.DataCompanyMd5Id;
import com.particle.data.infrastructure.company.dos.DataCompanyMd5DO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 企业md5 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:59
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class DataCompanyMd5AppStructMapping  implements IBaseQueryCommandMapStruct<DataCompanyMd5DO>{
	public static DataCompanyMd5AppStructMapping instance = Mappers.getMapper( DataCompanyMd5AppStructMapping.class );

	protected Long map(DataCompanyMd5Id dataCompanyMd5Id){
		if (dataCompanyMd5Id == null) {
			return null;
		}
		return dataCompanyMd5Id.getId();
	}
	/**
	 * 企业md5领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link DataCompanyMd5AppStructMapping#map(DataCompanyMd5Id)}
	 * @param dataCompanyMd5
	 * @return
	 */
	public abstract DataCompanyMd5VO toDataCompanyMd5VO(DataCompanyMd5 dataCompanyMd5);


	/**
	 * 数据对象转视图对象
	 * @param dataCompanyMd5DO
	 * @return
	 */
	public abstract DataCompanyMd5VO dataCompanyMd5DOToDataCompanyMd5VO(DataCompanyMd5DO dataCompanyMd5DO);

	/**
	 * 批量转换
	 * @param dataCompanyMd5DOs
	 * @return
	 */
	public abstract List<DataCompanyMd5VO> dataCompanyMd5DOsToDataCompanyMd5VOs(List<DataCompanyMd5DO> dataCompanyMd5DOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<DataCompanyMd5VO> infrastructurePageToPageResponse(Page<DataCompanyMd5DO> page) {
		return PageResponse.of(dataCompanyMd5DOsToDataCompanyMd5VOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public DataCompanyMd5DO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof DataCompanyMd5PageQueryCommand) {
			return pageQueryCommandToDO((DataCompanyMd5PageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof DataCompanyMd5QueryListCommand) {
			return queryListCommandToDO(((DataCompanyMd5QueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract DataCompanyMd5DO pageQueryCommandToDO(DataCompanyMd5PageQueryCommand dataCompanyMd5PageQueryCommand);

	public abstract DataCompanyMd5DO queryListCommandToDO(DataCompanyMd5QueryListCommand dataCompanyMd5QueryListCommand);
}
