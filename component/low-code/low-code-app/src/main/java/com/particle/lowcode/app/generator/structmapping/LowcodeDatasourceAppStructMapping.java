package com.particle.lowcode.app.generator.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.lowcode.client.generator.dto.data.LowcodeDatasourceVO;
import com.particle.lowcode.domain.generator.LowcodeDatasource;
import com.particle.lowcode.domain.generator.LowcodeDatasourceId;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeDatasourceDO;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeDatasourcePageQueryCommand;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeDatasourceQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 低代码数据源 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class LowcodeDatasourceAppStructMapping  implements IBaseQueryCommandMapStruct<LowcodeDatasourceDO>{
	public static LowcodeDatasourceAppStructMapping instance = Mappers.getMapper( LowcodeDatasourceAppStructMapping.class );

	protected Long map(LowcodeDatasourceId lowcodeDatasourceId){
		if (lowcodeDatasourceId == null) {
			return null;
		}
		return lowcodeDatasourceId.getId();
	}
	/**
	 * 低代码数据源领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link LowcodeDatasourceAppStructMapping#map(LowcodeDatasourceId)}
	 * @param lowcodeDatasource
	 * @return
	 */
	public abstract LowcodeDatasourceVO toLowcodeDatasourceVO(LowcodeDatasource lowcodeDatasource);


	/**
	 * 数据对象转视图对象
	 * @param lowcodeDatasourceDO
	 * @return
	 */
	public abstract LowcodeDatasourceVO lowcodeDatasourceDOToLowcodeDatasourceVO(LowcodeDatasourceDO lowcodeDatasourceDO);

	/**
	 * 批量转换
	 * @param lowcodeDatasourceDOs
	 * @return
	 */
	public abstract List<LowcodeDatasourceVO> lowcodeDatasourceDOsToLowcodeDatasourceVOs(List<LowcodeDatasourceDO> lowcodeDatasourceDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<LowcodeDatasourceVO> infrastructurePageToPageResponse(Page<LowcodeDatasourceDO> page) {
		return PageResponse.of(lowcodeDatasourceDOsToLowcodeDatasourceVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public LowcodeDatasourceDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof LowcodeDatasourcePageQueryCommand) {
			return pageQueryCommandToDO((LowcodeDatasourcePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof LowcodeDatasourceQueryListCommand) {
			return queryListCommandToDO(((LowcodeDatasourceQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract LowcodeDatasourceDO pageQueryCommandToDO(LowcodeDatasourcePageQueryCommand lowcodeDatasourcePageQueryCommand);

	public abstract LowcodeDatasourceDO queryListCommandToDO(LowcodeDatasourceQueryListCommand lowcodeDatasourceQueryListCommand);
}
