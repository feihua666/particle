package com.particle.func.app.api.wrapper;


import com.particle.func.client.dto.data.FuncVO;
import com.particle.func.domain.Func;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 菜单实体类转换
 * @program: particle
 * @description:
 * @author: 许宝华
 * @create: 2022-07-04 14:58
 */
@Mapper
public abstract class FuncWrapper {
	public static FuncWrapper instance = Mappers.getMapper( FuncWrapper.class );


	/**
	 * 区域领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，}
	 * @param func
	 * @return
	 */
	public abstract FuncVO toAreaVO(Func func);
}
