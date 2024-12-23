package com.particle.dream.app.ssq.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.dream.client.ssq.dto.command.representation.SsqCodePageQueryCommand;
import com.particle.dream.client.ssq.dto.command.representation.SsqCodeQueryListCommand;
import com.particle.dream.client.ssq.dto.data.SsqCodeVO;
import com.particle.dream.domain.ssq.SsqCode;
import com.particle.dream.domain.ssq.SsqCodeId;
import com.particle.dream.infrastructure.ssq.dos.SsqCodeDO;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;
/**
 * <p>
 * 双色球号码 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2024-05-16 09:47:01
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class SsqCodeAppStructMapping  implements IBaseQueryCommandMapStruct<SsqCodeDO>{
	public static SsqCodeAppStructMapping instance = Mappers.getMapper( SsqCodeAppStructMapping.class );

	protected Long map(SsqCodeId ssqCodeId){
		if (ssqCodeId == null) {
			return null;
		}
		return ssqCodeId.getId();
	}
	/**
	 * 双色球号码领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link SsqCodeAppStructMapping#map(SsqCodeId)}
	 * @param ssqCode
	 * @return
	 */
	public abstract SsqCodeVO toSsqCodeVO(SsqCode ssqCode);


	/**
	 * 数据对象转视图对象
	 * @param ssqCodeDO
	 * @return
	 */
	public abstract SsqCodeVO ssqCodeDOToSsqCodeVO(SsqCodeDO ssqCodeDO);

	/**
	 * 批量转换
	 * @param ssqCodeDOs
	 * @return
	 */
	public abstract List<SsqCodeVO> ssqCodeDOsToSsqCodeVOs(List<SsqCodeDO> ssqCodeDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<SsqCodeVO> infrastructurePageToPageResponse(Page<SsqCodeDO> page) {
		return PageResponse.of(ssqCodeDOsToSsqCodeVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}


	@Override
	public SsqCodeDO queryCommandToDO(QueryCommand queryCommand) {
		if (queryCommand instanceof SsqCodePageQueryCommand) {
			return pageQueryCommandToDO((SsqCodePageQueryCommand) queryCommand);
		}
		if (queryCommand instanceof SsqCodeQueryListCommand) {
			return queryListCommandToDO(((SsqCodeQueryListCommand) queryCommand));
		}
		return null;
	}

	public abstract SsqCodeDO pageQueryCommandToDO(SsqCodePageQueryCommand ssqCodePageQueryCommand);

	public abstract SsqCodeDO queryListCommandToDO(SsqCodeQueryListCommand ssqCodeQueryListCommand);
}
