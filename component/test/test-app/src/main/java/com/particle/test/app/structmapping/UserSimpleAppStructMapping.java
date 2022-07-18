package com.particle.test.app.structmapping;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.PageResponse;
import com.particle.test.client.dto.data.UserSimpleVO;
import com.particle.test.domain.UserSimple;
import com.particle.test.domain.UserSimpleId;
import com.particle.test.infrastructure.dos.UserSimpleDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * <p>
 * 简单用户 app应用层数据实体映射转换
 * </p>
 *
 * @author yw
 * @since 2022-07-15
 */
@Mapper
public abstract class UserSimpleAppStructMapping {
	public static UserSimpleAppStructMapping instance = Mappers.getMapper( UserSimpleAppStructMapping.class );

	protected Long map(UserSimpleId userSimpleId){
		if (userSimpleId == null) {
			return null;
		}
		return userSimpleId.getId();
	}
	/**
	 * 简单用户领域模型对象转视图对象
	 * MapStruct自动映射,其中枚举也会自动映射，id转换会自动使用{@link UserSimpleAppStructMapping#map(UserSimpleId)}
	 * @param userSimple
	 * @return
	 */
	public abstract UserSimpleVO toUserSimpleVO(UserSimple userSimple);


	/**
	 * 数据对象转视图对象
	 * @param userSimpleDO
	 * @return
	 */
	public abstract UserSimpleVO userSimpleDOToUserSimpleVO(UserSimpleDO userSimpleDO);

	/**
	 * 批量转换
	 * @param userSimpleDOs
	 * @return
	 */
	public abstract List<UserSimpleVO> userSimpleDOsToUserSimpleVOs(List<UserSimpleDO> userSimpleDOs);

	/**
	 * 分页转换
	 * @param page
	 * @return
	 */
	public PageResponse<UserSimpleVO> infrastructurePageToPageResponse(Page<UserSimpleDO> page) {
		return PageResponse.of(userSimpleDOsToUserSimpleVOs(page.getRecords()), (int) page.getTotal(), (int) page.getSize(), (int) page.getCurrent());
	}
}
