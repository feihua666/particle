package com.particle.area.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.area.client.dto.data.AreaVO;
import com.particle.area.domain.Area;
import com.particle.area.domain.AreaId;
import com.particle.area.infrastructure.dos.AreaDO;
import com.particle.global.dto.response.PageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * 区域 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-07-14
 */
@Mapper
public abstract class AreaAppStructMapping {
	public static AreaAppStructMapping instance = Mappers.getMapper( AreaAppStructMapping.class );

	protected Long map(AreaId areaId){
		return areaId.getId();
	}
	/**
	 * 区域领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AreaAppStructMapping#map(AreaId)}
	 * @param area
	 * @return
	 */
	public abstract AreaVO toAreaVO(Area area);

	/**
	 * 数据对象转视图对象
	 * @param areaDO
	 * @return
	 */
	public abstract AreaVO areaDOToAreaVO(AreaDO areaDO);

	/**
	 * 批量转换
	 * @param areaDOs
	 * @return
	 */
	public abstract List<AreaVO> areaDOsToAreaVOs(List<AreaDO> areaDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<AreaVO> infrastructurePageToPageResponse(Page<AreaDO> page) {
		return PageResponse.of(areaDOsToAreaVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
