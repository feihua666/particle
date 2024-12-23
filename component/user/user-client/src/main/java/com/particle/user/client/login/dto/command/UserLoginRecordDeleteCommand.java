package com.particle.user.client.login.dto.command;

import com.particle.common.client.dto.command.IdCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
/**
 * <p>
 * 用户登录记录 通用删除指令对象
 * </p>
 *
 * @author yw
 * @since 2022-11-26
 */
@Data
@Schema
public class UserLoginRecordDeleteCommand extends IdCommand {

}
