package com.particle.func.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.func.client.dto.data.FuncGroupVO;
import com.particle.func.domain.FuncGroup;
import com.particle.func.domain.FuncGroupId;
import com.particle.func.infrastructure.dos.FuncGroupDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 功能组 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Mapper
public abstract class FuncGroupAppStructMapping {
	public static FuncGroupAppStructMapping instance = Mappers.getMapper( FuncGroupAppStructMapping.class );

	protected Long map(FuncGroupId funcGroupId){
		if (funcGroupId == null) {
			return null;
		}
		return funcGroupId.getId();
	}
	/**
	 * 功能组领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link FuncGroupAppStructMapping#map(FuncGroupId)}
	 * @param funcGroup
	 * @return
	 */
	public abstract FuncGroupVO toFuncGroupVO(FuncGroup funcGroup);


	/**
	 * 数据对象转视图对象
	 * @param funcGroupDO
	 * @return
	 */
	public abstract FuncGroupVO funcGroupDOToFuncGroupVO(FuncGroupDO funcGroupDO);

	/**
	 * 批量转换
	 * @param funcGroupDOs
	 * @return
	 */
	public abstract List<FuncGroupVO> funcGroupDOsToFuncGroupVOs(List<FuncGroupDO> funcGroupDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<FuncGroupVO> infrastructurePageToPageResponse(Page<FuncGroupDO> page) {
		return PageResponse.of(funcGroupDOsToFuncGroupVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
