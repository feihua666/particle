package com.particle.dict.app.api.impl.representation;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.particle.common.app.AbstractBaseApplicationServiceImpl;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.dict.app.executor.representation.DictQueryCommandExecutor;
import com.particle.dict.app.structmapping.DictAppStructMapping;
import com.particle.dict.client.api.representation.IDictRepresentationApplicationService;
import com.particle.dict.client.dto.command.representation.DictItemsQueryListCommand;
import com.particle.dict.client.dto.command.representation.DictPageQueryCommand;
import com.particle.dict.client.dto.command.representation.DictQueryListCommand;
import com.particle.dict.client.dto.data.DictGroupItemsVO;
import com.particle.dict.client.dto.data.DictVO;
import com.particle.dict.infrastructure.dos.DictDO;
import com.particle.dict.infrastructure.service.IDictService;
import com.particle.global.catchlog.CatchAndLog;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 字典 门面服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Transactional
@Service
@CatchAndLog
public class DictRepresentationApplicationServiceImpl extends AbstractBaseApplicationServiceImpl implements IDictRepresentationApplicationService {

	private DictQueryCommandExecutor dictQueryCommandExecutor;

	private IDictService iDictService;

	@Override
	public SingleResponse<DictVO> queryDetail(IdCommand dictQueryDetailCommand) {
		return dictQueryCommandExecutor.executeDetail(dictQueryDetailCommand);
	}

	@Override
	public SingleResponse<DictVO> queryDetailForUpdate(IdCommand dictQueryDetailForUpdateCommand) {
		return dictQueryCommandExecutor.executeDetailForUpdate(dictQueryDetailForUpdateCommand);
	}

	@Override
	public PageResponse<DictVO> pageQuery(DictPageQueryCommand dictPageQueryCommand) {
		return dictQueryCommandExecutor.execute(dictPageQueryCommand);
	}

	@Override
	public MultiResponse<DictVO> getItemsByGroupCode(@Valid DictItemsQueryListCommand dictItemsQueryListCommand) {
		return getChildItemsByGroupCode(dictItemsQueryListCommand,false);
	}


	/**
	 * 获取子一级
	 * @param dictItemsQueryListCommand
	 * @param isGroup
	 * @return
	 */
	private MultiResponse<DictVO> getChildItemsByGroupCode(DictItemsQueryListCommand dictItemsQueryListCommand,boolean isGroup) {
		DictDO groupDict = iDictService.getByCode(dictItemsQueryListCommand.getGroupCode());
		if (groupDict == null) {
			return MultiResponse.of(Collections.EMPTY_LIST);
		}

		LambdaQueryWrapper<DictDO> queryWrapper = Wrappers.<DictDO>lambdaQuery()
				.eq(DictDO::getParentId, groupDict.getId())
				.eq(!StrUtil.isEmpty(dictItemsQueryListCommand.getGroupFlag()), DictDO::getGroupFlag, dictItemsQueryListCommand.getGroupFlag())
				.eq(DictDO::getIsDisabled, false)
				.eq(DictDO::getIsGroup, isGroup)
				.and(wp -> {
					wp.eq(DictDO::getIsPublic, true).or().eq(!StrUtil.isEmpty(dictItemsQueryListCommand.getPrivateFlag()), DictDO::getPrivateFlag, dictItemsQueryListCommand.getPrivateFlag());
				}).orderByAsc(DictDO::getSeq).orderByAsc(DictDO::getId);
		List<DictDO> result = iDictService.list(
				queryWrapper
		);
		if (CollectionUtil.isEmpty(result)) {
			return MultiResponse.of(Collections.EMPTY_LIST);
		}

		if (!StrUtil.isEmpty(dictItemsQueryListCommand.getTags())) {
			List<String> paramTags = Lists.newArrayList(dictItemsQueryListCommand.getTags().split(","));
			// 过滤标签
			result = result.stream().filter(dict ->
					{
						// 字典标签为空，直接过滤掉，不显示
						if (StrUtil.isEmpty(dict.getTags())) {
							return false;
						}
						return Lists.newArrayList(dict.getTags().split(",")).stream().anyMatch(tag -> paramTags.contains(tag));
					}
			).collect(Collectors.toList());
		}

		List<DictVO> dictVOs = DictAppStructMapping.instance.dictDOsToDictVOs(result);
		return MultiResponse.of(dictVOs);
	}

	@Override
	public MultiResponse<DictGroupItemsVO> getGroupItemsByGroupCode(@Valid DictItemsQueryListCommand dictItemsQueryListCommand) {

		MultiResponse<DictVO> groupsByGroupCode = getGroupsByGroupCode(dictItemsQueryListCommand);
		if (groupsByGroupCode.isEmpty()) {
			return MultiResponse.of(Collections.EMPTY_LIST);
		}

		List<DictGroupItemsVO> result = new ArrayList<>();
		DictGroupItemsVO dictGroupItemDto = null;
		for (DictVO group : groupsByGroupCode.getData()) {
			dictGroupItemDto = DictAppStructMapping.instance.voToDictGroupItemsVO(group);
			dictItemsQueryListCommand.setGroupCode(dictGroupItemDto.getCode());
			MultiResponse<DictVO> itemsByGroupCode = getItemsByGroupCode(dictItemsQueryListCommand);
			if (itemsByGroupCode.isNotEmpty()) {
				// 如果字典项不为空则添加
				dictGroupItemDto.setItems(itemsByGroupCode.getData());
				result.add(dictGroupItemDto);
			}
		}

		return MultiResponse.of(result);
	}

	@Override
	public MultiResponse<DictVO> getGroupsByGroupCode(@Valid DictItemsQueryListCommand dictItemsQueryListCommand) {
		return getChildItemsByGroupCode(dictItemsQueryListCommand,true);
	}

	@Override
	public MultiResponse<DictVO> queryList(DictQueryListCommand dictQueryListCommand) {
		return dictQueryCommandExecutor.execute(dictQueryListCommand);
	}

	@Autowired
	public void setDictQueryCommandExecutor(DictQueryCommandExecutor dictQueryCommandExecutor) {
		this.dictQueryCommandExecutor = dictQueryCommandExecutor;
	}

	@Autowired
	public void setiDictService(IDictService iDictService) {
		this.iDictService = iDictService;
	}
}
