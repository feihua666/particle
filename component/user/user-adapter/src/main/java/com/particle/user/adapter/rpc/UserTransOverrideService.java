package com.particle.user.adapter.rpc;

import com.particle.global.dto.basic.DTO;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 用户翻译覆盖，主要是使用租户的人员姓名
 * </p>
 *
 * @author yangwei
 * @since 2023-06-26 15:48
 */
public interface UserTransOverrideService {


	/**
	 * 获取额外覆盖数据
	 * @param userIds
	 * @return
	 */
	List<UserTransOverrideDTO> getOverrideData(List<Long> userIds);


	@Data
	public static class UserTransOverrideDTO extends DTO {
		private Long id;
		private String name;

		public static UserTransOverrideDTO create(Long id, String name) {
			UserTransOverrideDTO userTransOverrideDTO = new UserTransOverrideDTO();
			userTransOverrideDTO.setId(id);
			userTransOverrideDTO.setName(name);
			return userTransOverrideDTO;
		}
	}
}
