package com.particle.dream.app.ssq.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.dream.client.ssq.dto.command.representation.SsqCodeOpenedPageQueryCommand;
import com.particle.dream.client.ssq.dto.command.representation.SsqCodeOpenedQueryListCommand;
import com.particle.dream.client.ssq.dto.data.SsqCodeOpenedVO;
import com.particle.dream.domain.ssq.SsqCodeOpened;
import com.particle.dream.domain.ssq.SsqCodeOpenedId;
import com.particle.dream.infrastructure.ssq.dos.SsqCodeOpenedDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 双色球开奖 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:49:47
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class SsqCodeOpenedAppStructMapping  implements IBaseQueryCommandMapStruct<SsqCodeOpenedDO>{
	public static SsqCodeOpenedAppStructMapping instance = Mappers.getMapper( SsqCodeOpenedAppStructMapping.class );

	protected Long map(SsqCodeOpenedId ssqCodeOpenedId){
		if (ssqCodeOpenedId == null) {
			return null;
		}
		return ssqCodeOpenedId.getId();
	}
	/**
	 * 双色球开奖领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SsqCodeOpenedAppStructMapping#map(SsqCodeOpenedId)}
	 * @param ssqCodeOpened
	 * @return
	 */
	public abstract SsqCodeOpenedVO toSsqCodeOpenedVO(SsqCodeOpened ssqCodeOpened);


	/**
	 * 数据对象转视图对象
	 * @param ssqCodeOpenedDO
	 * @return
	 */
	public abstract SsqCodeOpenedVO ssqCodeOpenedDOToSsqCodeOpenedVO(SsqCodeOpenedDO ssqCodeOpenedDO);

	/**
	 * 批量转换
	 * @param ssqCodeOpenedDOs
	 * @return
	 */
	public abstract List<SsqCodeOpenedVO> ssqCodeOpenedDOsToSsqCodeOpenedVOs(List<SsqCodeOpenedDO> ssqCodeOpenedDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<SsqCodeOpenedVO> infrastructurePageToPageResponse(Page<SsqCodeOpenedDO> page) {
		return PageResponse.of(ssqCodeOpenedDOsToSsqCodeOpenedVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public SsqCodeOpenedDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof SsqCodeOpenedPageQueryCommand) {
			return pageQueryCommandToDO((SsqCodeOpenedPageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof SsqCodeOpenedQueryListCommand) {
			return queryListCommandToDO(((SsqCodeOpenedQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract SsqCodeOpenedDO pageQueryCommandToDO(SsqCodeOpenedPageQueryCommand ssqCodeOpenedPageQueryCommand);

	public abstract SsqCodeOpenedDO queryListCommandToDO(SsqCodeOpenedQueryListCommand ssqCodeOpenedQueryListCommand);
}
