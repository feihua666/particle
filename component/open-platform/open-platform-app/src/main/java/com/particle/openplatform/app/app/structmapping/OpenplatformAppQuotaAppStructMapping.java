package com.particle.openplatform.app.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppQuotaVO;
import com.particle.openplatform.domain.app.OpenplatformAppQuota;
import com.particle.openplatform.domain.app.OpenplatformAppQuotaId;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppQuotaDO;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppQuotaPageQueryCommand;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppQuotaQueryListCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 开放平台应用额度 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OpenplatformAppQuotaAppStructMapping  implements IBaseQueryCommandMapStruct<OpenplatformAppQuotaDO>{
	public static OpenplatformAppQuotaAppStructMapping instance = Mappers.getMapper( OpenplatformAppQuotaAppStructMapping.class );

	protected Long map(OpenplatformAppQuotaId openplatformAppQuotaId){
		if (openplatformAppQuotaId == null) {
			return null;
		}
		return openplatformAppQuotaId.getId();
	}
	/**
	 * 开放平台应用额度领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link OpenplatformAppQuotaAppStructMapping#map(OpenplatformAppQuotaId)}
	 * @param openplatformAppQuota
	 * @return
	 */
	public abstract OpenplatformAppQuotaVO toOpenplatformAppQuotaVO(OpenplatformAppQuota openplatformAppQuota);


	/**
	 * 数据对象转视图对象
	 * @param openplatformAppQuotaDO
	 * @return
	 */
	public abstract OpenplatformAppQuotaVO openplatformAppQuotaDOToOpenplatformAppQuotaVO(OpenplatformAppQuotaDO openplatformAppQuotaDO);

	/**
	 * 批量转换
	 * @param openplatformAppQuotaDOs
	 * @return
	 */
	public abstract List<OpenplatformAppQuotaVO> openplatformAppQuotaDOsToOpenplatformAppQuotaVOs(List<OpenplatformAppQuotaDO> openplatformAppQuotaDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<OpenplatformAppQuotaVO> infrastructurePageToPageResponse(Page<OpenplatformAppQuotaDO> page) {
		return PageResponse.of(openplatformAppQuotaDOsToOpenplatformAppQuotaVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public OpenplatformAppQuotaDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof OpenplatformAppQuotaPageQueryCommand) {
			return pageQueryCommandToDO((OpenplatformAppQuotaPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof OpenplatformAppQuotaQueryListCommand) {
			return queryListCommandToDO(((OpenplatformAppQuotaQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract OpenplatformAppQuotaDO pageQueryCommandToDO(OpenplatformAppQuotaPageQueryCommand openplatformAppQuotaPageQueryCommand);

	public abstract OpenplatformAppQuotaDO queryListCommandToDO(OpenplatformAppQuotaQueryListCommand openplatformAppQuotaQueryListCommand);
}
