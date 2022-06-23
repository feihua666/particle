package com.particle.area.app.wrapper;

import com.particle.area.client.dto.data.AreaVO;
import com.particle.area.domain.Area;
import com.particle.area.domain.AreaId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * <p>
 * 区域各种转换
 * </p>
 *
 * @author yangwei
 * @since 2022-05-17 17:29
 */
@Mapper
public abstract class AreaWrapper {
	public static AreaWrapper instance = Mappers.getMapper( AreaWrapper.class );

	protected Long map(AreaId areaId){
		return areaId.getId();
	}
	/**
	 * 区域领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link AreaWrapper#map(AreaId)}
	 * @param area
	 * @return
	 */
	public abstract AreaVO toAreaVO(Area area);
}
