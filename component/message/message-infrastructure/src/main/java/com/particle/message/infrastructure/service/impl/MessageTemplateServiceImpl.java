package com.particle.message.infrastructure.service.impl;

import cn.hutool.core.util.StrUtil;
import com.particle.global.exception.Assert;
import com.particle.message.infrastructure.dos.MessageTemplateDO;
import com.particle.message.infrastructure.mapper.MessageTemplateMapper;
import com.particle.message.infrastructure.service.IMessageTemplateService;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.dto.basic.QueryCommand;
import org.springframework.stereotype.Component;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * <p>
 * 消息模板 服务实现类
 * </p>
 *
 * @author yw
 * @since 2023-05-18 15:34:49
 */
@Component
public class MessageTemplateServiceImpl extends IBaseServiceImpl<MessageTemplateMapper, MessageTemplateDO> implements IMessageTemplateService {
	private IBaseQueryCommandMapStruct<MessageTemplateDO> queryCommandMapStruct;

	@Override
	protected MessageTemplateDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<MessageTemplateDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}


	@Override
	protected void preAdd(MessageTemplateDO po) {
		// 如果为模板，不需要添加编码code，如果模板编辑code为空，不校验唯一
		if (StrUtil.isNotEmpty(po.getCode())) {
			// 编码已存在不能添加
			assertByColumn(po.getCode(),MessageTemplateDO::getCode,false);
		}

		if (po.getParentId() != null) {
			MessageTemplateDO byParentId = getById(po.getParentId());
			Assert.isTrue(byParentId.getIsGroup(),"模板不能添加子节点");
		}
	}

	@Override
	protected void preUpdate(MessageTemplateDO po) {


		if (StrUtil.isNotEmpty(po.getCode())) {
			MessageTemplateDO byId = getById(po.getId());
			// 如果编码有改动
			if (!StrUtil.equals(po.getCode(), byId.getCode())) {
				// 编码已存在不能修改
				assertByColumn(po.getCode(),MessageTemplateDO::getCode,false);
			}
		}
		if (po.getParentId() != null) {
			MessageTemplateDO byId = getById(po.getParentId());
			Assert.isTrue(byId.getIsGroup(),"模板不能添加子节点");
		}
	}
}
