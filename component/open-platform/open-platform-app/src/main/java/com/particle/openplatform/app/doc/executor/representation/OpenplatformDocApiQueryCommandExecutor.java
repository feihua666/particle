package com.particle.openplatform.app.doc.executor.representation;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.comparator.CompareUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.doc.structmapping.*;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiQueryAllDetailCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.*;
import com.particle.openplatform.domain.doc.enums.OpenPlatformDocParamFieldType;
import com.particle.openplatform.domain.doc.value.OpenplatformDocParamFieldDictItemDTO;
import com.particle.openplatform.domain.doc.value.OpenplatformDocParamFieldDictItemParam;
import com.particle.openplatform.domain.gateway.OpenplatformDictGateway;
import com.particle.openplatform.infrastructure.doc.dos.*;
import com.particle.openplatform.infrastructure.doc.service.*;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 开放接口文档接口 列表查询指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:01
 */
@Component
@Validated
public class OpenplatformDocApiQueryCommandExecutor extends AbstractBaseQueryExecutor {

    private IOpenplatformDocApiService iOpenplatformDocApiService;
    private IOpenplatformDocApiDocService iOpenplatformDocApiDocService;
    private IOpenplatformDocApiDocTemplateService iOpenplatformDocApiDocTemplateService;

    private IOpenplatformDocApiDocParamFieldService iOpenplatformDocApiDocParamFieldService;
    private IOpenplatformDocApiDocTemplateParamFieldService iOpenplatformDocApiDocTemplateParamFieldService;

    private OpenplatformDictGateway openplatformDictGateway;

    private IOpenplatformDocApiDocResponseCodeService iOpenplatformDocApiDocResponseCodeService;
    private IOpenplatformDocApiDocTemplateResponseCodeService iOpenplatformDocApiDocTemplateResponseCodeService;

    private IOpenplatformDocApiDocExampleCodeService iOpenplatformDocApiDocExampleCodeService;
    private IOpenplatformDocApiDocTemplateExampleCodeService iOpenplatformDocApiDocTemplateExampleCodeService;


    private IOpenplatformOpenapiService iOpenplatformOpenapiService;
    /**
     * 执行 开放接口文档接口 列表查询指令
     *
     * @param openplatformDocApiQueryListCommand
     * @return
     */
    public MultiResponse<OpenplatformDocApiVO> execute(@Valid OpenplatformDocApiQueryListCommand openplatformDocApiQueryListCommand) {
        List<OpenplatformDocApiDO> openplatformDocApiDO = iOpenplatformDocApiService.list(openplatformDocApiQueryListCommand);
        List<OpenplatformDocApiVO> openplatformDocApiVOs = OpenplatformDocApiAppStructMapping.instance.openplatformDocApiDOsToOpenplatformDocApiVOs(openplatformDocApiDO);
        return MultiResponse.of(openplatformDocApiVOs);
    }

    /**
     * 执行 开放接口文档接口 分页查询指令
     *
     * @param openplatformDocApiPageQueryCommand
     * @return
     */
    public PageResponse<OpenplatformDocApiVO> execute(@Valid OpenplatformDocApiPageQueryCommand openplatformDocApiPageQueryCommand) {
        Page<OpenplatformDocApiDO> page = iOpenplatformDocApiService.listPage(openplatformDocApiPageQueryCommand);
        return OpenplatformDocApiAppStructMapping.instance.infrastructurePageToPageResponse(page);
    }

    /**
     * 执行 开放接口文档接口 展示用详情查询指令
     *
     * @param detailCommand
     * @return
     */
    public SingleResponse<OpenplatformDocApiVO> executeDetail(IdCommand detailCommand) {
        OpenplatformDocApiDO byId = iOpenplatformDocApiService.getById(detailCommand.getId());
        OpenplatformDocApiVO openplatformDocApiVO = OpenplatformDocApiAppStructMapping.instance.openplatformDocApiDOToOpenplatformDocApiVO(byId);
        return SingleResponse.of(openplatformDocApiVO);
    }

    /**
     * 获取单个接口的详情
     *
     * @param detailCommand
     * @return
     */
    public SingleResponse<OpenplatformDocApiDetailVO> queryAllDetail(OpenplatformDocApiQueryAllDetailCommand detailCommand) {

        OpenplatformDocApiDO openplatformDocApiDO = null;
        Long id = detailCommand.getId();
        if (id == null) {
            if (detailCommand.getOpenplatformOpenapiId() != null) {
                openplatformDocApiDO = iOpenplatformDocApiService.getByOpenplatformOpenapiId(detailCommand.getOpenplatformOpenapiId());

            }
        }else {
            openplatformDocApiDO = iOpenplatformDocApiService.getById(id);
        }



        if (openplatformDocApiDO == null) {
            return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
        }
        // 接口信息
        OpenplatformDocApiVO openplatformDocApiVO = OpenplatformDocApiAppStructMapping.instance.openplatformDocApiDOToOpenplatformDocApiVO(openplatformDocApiDO);
        OpenplatformDocApiDetailVO openplatformDocApiDetailVO = OpenplatformDocApiDetailVO.create(openplatformDocApiVO);

        // 文档内容
        OpenplatformDocApiDocVO finalOpenplatformDocApiDocVO = getFinalOpenplatformDocApiDocVO(openplatformDocApiDetailVO.getId());
        openplatformDocApiDetailVO.setDocApiDocVO(finalOpenplatformDocApiDocVO);
        // 文档参数字段
        if (finalOpenplatformDocApiDocVO != null) {
            Map<String, List<OpenplatformDocApiDocParamFieldBasicVO>> finalOpenplatformDocApiDocParamFieldBasicVOsMap = getFinalOpenplatformDocApiDocParamFieldBasicVOsMap(finalOpenplatformDocApiDocVO.getId(), finalOpenplatformDocApiDocVO.getOpenplatformDocApiDocTemplateId());
            // 字典处理
            if (finalOpenplatformDocApiDocParamFieldBasicVOsMap != null) {
                for (Map.Entry<String, List<OpenplatformDocApiDocParamFieldBasicVO>> stringListEntry : finalOpenplatformDocApiDocParamFieldBasicVOsMap.entrySet()) {
                    List<OpenplatformDocApiDocParamFieldBasicVO> value = stringListEntry.getValue();
                    if (CollectionUtil.isNotEmpty(value)) {
                        List<OpenplatformDocParamFieldDictItemParam> collect = value.stream().filter(item -> item.getDictGroupDictId() != null).map(item -> OpenplatformDocParamFieldDictItemParam.create(item.getDictGroupDictId(), item.getDictItemTags())).collect(Collectors.toList());
                        if (CollectionUtil.isNotEmpty(collect)) {
							Map<Long, List<OpenplatformDocParamFieldDictItemDTO>> itemsByGroupIds = openplatformDictGateway.getItemsByGroupIds(collect);
							value.stream().filter(item -> item.getDictGroupDictId() != null).forEach(item -> {
								List<OpenplatformDocParamFieldDictItemDTO> itemsByGroupId = itemsByGroupIds.get(item.getDictGroupDictId());
								if (CollectionUtil.isNotEmpty(itemsByGroupId)) {
									item.setDictItemVOs(openplatformDocParamFieldDictItemDTOsToOpenplatformDocParamFieldDictItemVOs(itemsByGroupId));
								}
							});
						}
                    }
                }
            }

            openplatformDocApiDetailVO.setDocApiDocParamFieldVOsMap(finalOpenplatformDocApiDocParamFieldBasicVOsMap);
        }
        // 文档响应码
        if (finalOpenplatformDocApiDocVO != null) {
            List<OpenplatformDocApiDocResponseCodeVO> finalOpenplatformDocApiDocResponseCodeVOs = getFinalOpenplatformDocApiDocResponseCodeVOs(finalOpenplatformDocApiDocVO.getId(), finalOpenplatformDocApiDocVO.getOpenplatformDocApiDocTemplateId());
            openplatformDocApiDetailVO.setDocApiDocResponseCodeVOs(finalOpenplatformDocApiDocResponseCodeVOs);
        }
        // 文档示例代码
        if (finalOpenplatformDocApiDocVO != null) {
            List<OpenplatformDocApiDocExampleCodeVO> finalOpenplatformDocApiDocExampleCodeVOs = getFinalOpenplatformDocApiDocExampleCodeVOs(finalOpenplatformDocApiDocVO.getId(), finalOpenplatformDocApiDocVO.getOpenplatformDocApiDocTemplateId());
            openplatformDocApiDetailVO.setDocApiDocExampleCodeVOs(finalOpenplatformDocApiDocExampleCodeVOs);
        }

        return SingleResponse.of(openplatformDocApiDetailVO);

    }


    /**
     * 获取最终的文档示例代码
     * 主要思路，不区分语言，如果自己定义使用自己定义的，否则使用模板定义的
     *
     * @param openplatformDocApiDocId
     * @param openplatformDocApiDocTemplateId
     * @return
     */
    private List<OpenplatformDocApiDocExampleCodeVO> getFinalOpenplatformDocApiDocExampleCodeVOs(Long openplatformDocApiDocId, Long openplatformDocApiDocTemplateId) {
        List<OpenplatformDocApiDocExampleCodeVO> result = new ArrayList<>();
        List<OpenplatformDocApiDocExampleCodeVO> openplatformDocApiDocExampleCodeVOs = getOpenplatformDocApiDocExampleCodeVOs(openplatformDocApiDocId);
        // 定义一个变量，是否自己定义了，如果自己定义了，则只使用全局的模板
        boolean selfDefined = false;
        if (CollectionUtil.isNotEmpty(openplatformDocApiDocExampleCodeVOs)) {
            result.addAll(openplatformDocApiDocExampleCodeVOs);
            selfDefined = true;
        }
        if (!selfDefined && openplatformDocApiDocTemplateId != null) {
            List<OpenplatformDocApiDocTemplateExampleCodeVO> openplatformDocApiDocTemplateExampleCodeVOs = getOpenplatformDocApiDocTemplateExampleCodeVOs(openplatformDocApiDocTemplateId);
            if (CollectionUtil.isNotEmpty(openplatformDocApiDocTemplateExampleCodeVOs)) {
                for (OpenplatformDocApiDocTemplateExampleCodeVO openplatformDocApiDocTemplateExampleCodeVO : openplatformDocApiDocTemplateExampleCodeVOs) {
                    result.add(OpenplatformDocApiDocExampleCodeVO.create(openplatformDocApiDocTemplateExampleCodeVO));
                }
            }
        }
        // 排序
        result = CollectionUtil.sort(result, (v1, v2) -> {
            return CompareUtil.compare(v1.getSeq(), v2.getSeq(), false);
        });
        return result;
    }

    /**
     * 根据文档id获取文档示例代码
     *
     * @param openplatformDocApiDocId
     * @return
     */
    private List<OpenplatformDocApiDocExampleCodeVO> getOpenplatformDocApiDocExampleCodeVOs(Long openplatformDocApiDocId) {
        List<OpenplatformDocApiDocExampleCodeDO> openplatformDocApiDocExampleCodeDOList = iOpenplatformDocApiDocExampleCodeService.getByOpenplatformDocApiDocId(openplatformDocApiDocId);
        return OpenplatformDocApiDocExampleCodeAppStructMapping.instance.openplatformDocApiDocExampleCodeDOsToOpenplatformDocApiDocExampleCodeVOs(openplatformDocApiDocExampleCodeDOList);
    }

    /**
     * 根据文档模板id获取文档示例代码
     *
     * @param openplatformDocApiDocTemplateId
     * @return
     */
    private List<OpenplatformDocApiDocTemplateExampleCodeVO> getOpenplatformDocApiDocTemplateExampleCodeVOs(Long openplatformDocApiDocTemplateId) {
        List<OpenplatformDocApiDocTemplateExampleCodeDO> openplatformDocApiDocTemplateExampleCodeDOList = iOpenplatformDocApiDocTemplateExampleCodeService.getByOpenplatformDocApiDocTemplateId(openplatformDocApiDocTemplateId);
        return OpenplatformDocApiDocTemplateExampleCodeAppStructMapping.instance.openplatformDocApiDocTemplateExampleCodeDOsToOpenplatformDocApiDocTemplateExampleCodeVOs(openplatformDocApiDocTemplateExampleCodeDOList);
    }

    /**
     * 获取最终的文档响应码
     * 主要思路：包含自己定义的，包含模板全局定义的，如果自己没有定义，则使用模板的全部
     *
     * @param openplatformDocApiDocId
     * @param openplatformDocApiDocTemplateId
     * @return
     */
    private List<OpenplatformDocApiDocResponseCodeVO> getFinalOpenplatformDocApiDocResponseCodeVOs(Long openplatformDocApiDocId, Long openplatformDocApiDocTemplateId) {
        List<OpenplatformDocApiDocResponseCodeVO> result = new ArrayList<>();
        List<OpenplatformDocApiDocResponseCodeVO> openplatformDocApiDocResponseCodeVOs = getOpenplatformDocApiDocResponseCodeVOs(openplatformDocApiDocId);
        // 定义一个变量，是否自己定义了，如果自己定义了，则只使用全局的模板
        boolean selfDefined = false;
        if (CollectionUtil.isNotEmpty(openplatformDocApiDocResponseCodeVOs)) {
            result.addAll(openplatformDocApiDocResponseCodeVOs);
            selfDefined = true;
        }
        if (openplatformDocApiDocTemplateId != null) {
            List<OpenplatformDocApiDocTemplateResponseCodeVO> openplatformDocApiDocTemplateResponseCodeVOs = getOpenplatformDocApiDocTemplateResponseCodeVOs(openplatformDocApiDocTemplateId);
            if (CollectionUtil.isNotEmpty(openplatformDocApiDocTemplateResponseCodeVOs)) {
                for (OpenplatformDocApiDocTemplateResponseCodeVO openplatformDocApiDocTemplateResponseCodeVO : openplatformDocApiDocTemplateResponseCodeVOs) {
                    // 根据是否自己定义判断取或不取，如果自己定义了，只取全局的模板
                    if (selfDefined && !openplatformDocApiDocTemplateResponseCodeVO.getIsGlobal()) {
                        continue;
                    }
                    result.add(OpenplatformDocApiDocResponseCodeVO.create(openplatformDocApiDocTemplateResponseCodeVO));
                }
            }
        }
        // 排序
        result = CollectionUtil.sort(result, (v1, v2) -> {
            return CompareUtil.compare(v1.getSeq(), v2.getSeq(), false);
        });
        return result;
    }

    /**
     * 根据文档id获取文档响应码
     *
     * @param openplatformDocApiDocId
     * @return
     */
    private List<OpenplatformDocApiDocResponseCodeVO> getOpenplatformDocApiDocResponseCodeVOs(Long openplatformDocApiDocId) {
        List<OpenplatformDocApiDocResponseCodeDO> openplatformDocApiDocResponseCodeDOList = iOpenplatformDocApiDocResponseCodeService.getByOpenplatformDocApiDocId(openplatformDocApiDocId);
        return OpenplatformDocApiDocResponseCodeAppStructMapping.instance.openplatformDocApiDocResponseCodeDOsToOpenplatformDocApiDocResponseCodeVOs(openplatformDocApiDocResponseCodeDOList);
    }

    /**
     * 根据文档模板id获取文档响应码
     *
     * @param openplatformDocApiDocTemplateId
     * @return
     */
    private List<OpenplatformDocApiDocTemplateResponseCodeVO> getOpenplatformDocApiDocTemplateResponseCodeVOs(Long openplatformDocApiDocTemplateId) {
        List<OpenplatformDocApiDocTemplateResponseCodeDO> openplatformDocApiDocTemplateResponseCodeDOList = iOpenplatformDocApiDocTemplateResponseCodeService.getByOpenplatformDocApiDocTemplateId(openplatformDocApiDocTemplateId);
        return OpenplatformDocApiDocTemplateResponseCodeAppStructMapping.instance.openplatformDocApiDocTemplateResponseCodeDOsToOpenplatformDocApiDocTemplateResponseCodeVOs(openplatformDocApiDocTemplateResponseCodeDOList);
    }

	/**
	 * 参数字典映射为对应的VO
	 * @param openplatformDocParamFieldDictItemDTOs
	 * @return
	 */
	private List<OpenplatformDocParamFieldDictItemVO> openplatformDocParamFieldDictItemDTOsToOpenplatformDocParamFieldDictItemVOs(List<OpenplatformDocParamFieldDictItemDTO> openplatformDocParamFieldDictItemDTOs) {
		if (CollectionUtil.isEmpty(openplatformDocParamFieldDictItemDTOs)) {
			return null;
		}
		return openplatformDocParamFieldDictItemDTOs.stream()
				.map(item -> OpenplatformDocParamFieldDictItemVO.create(item.getName(), item.getValue()))
				.collect(Collectors.toList());
	}

    /**
     * 获取最终的参数字段
     * 主要思路：按类别，如果某一类自己定义了（有一条也算，只要定义过就算），不再使用模板的字段信息，如果自己没有定义，则使用模板的字段信息
     *
     * @param openplatformDocApiDocId
     * @param openplatformDocApiDocTemplateId
     * @return
     */
    public Map<String, List<OpenplatformDocApiDocParamFieldBasicVO>> getFinalOpenplatformDocApiDocParamFieldBasicVOsMap(Long openplatformDocApiDocId, Long openplatformDocApiDocTemplateId) {

        // 获取自己定义的
        List<OpenplatformDocApiDocParamFieldBasicVO> openplatformDocApiDocParamFieldVOs = getOpenplatformDocApiDocParamFieldBasicVOs(openplatformDocApiDocId);
        // 将自己定义的按分类合并
        Map<Long, List<OpenplatformDocApiDocParamFieldBasicVO>> longListMap = openplatformDocApiDocParamFieldVOs.stream().collect(Collectors.groupingBy(OpenplatformDocApiDocParamFieldBasicVO::getCategoryDictId));

        // 定义模板定义的，也按分类合并
        Map<Long, List<OpenplatformDocApiDocTemplateParamFieldVO>> longListMapTemplate = null;
        if (openplatformDocApiDocTemplateId != null) {
            List<OpenplatformDocApiDocTemplateParamFieldVO> openplatformDocApiDocTemplateParamFieldVOs = getOpenplatformDocApiDocTemplateParamFieldVOs(openplatformDocApiDocTemplateId);
            longListMapTemplate = openplatformDocApiDocTemplateParamFieldVOs.stream().collect(Collectors.groupingBy(OpenplatformDocApiDocTemplateParamFieldVO::getCategoryDictId));
        }
        // 定义最终的结果
        Map<String, List<OpenplatformDocApiDocParamFieldBasicVO>> result = new HashMap<>();
        // 获取总的分类字典
        Map<Long, String> itemsByGroupCode = openplatformDictGateway.getItemsByGroupCode(OpenPlatformDocParamFieldType.Group.open_platform_doc_param_field_type.groupCode());
        // 根据字典，按每一类，遍历，如果自己定义了就不再使用模板，如果自己某一类没有定义，则尝试使用模板
        for (Map.Entry<Long, String> longStringEntry : itemsByGroupCode.entrySet()) {
            List<OpenplatformDocApiDocParamFieldBasicVO> newBasicVOS = new ArrayList<>();

            // 自己配置的
            List<OpenplatformDocApiDocParamFieldBasicVO> openplatformDocApiDocParamFieldBasicVOS = longListMap.get(longStringEntry.getKey());
            // 模板配置的
            List<OpenplatformDocApiDocTemplateParamFieldVO> openplatformDocApiDocTemplateParamFieldVOS = longListMapTemplate == null ? null : longListMapTemplate.get(longStringEntry.getKey());
            // 如果自己配置了就不要模板的了
            if (CollectionUtil.isNotEmpty(openplatformDocApiDocParamFieldBasicVOS)) {
                newBasicVOS.addAll(openplatformDocApiDocParamFieldBasicVOS);
            } else if (CollectionUtil.isNotEmpty(openplatformDocApiDocTemplateParamFieldVOS)) {
                for (OpenplatformDocApiDocTemplateParamFieldVO openplatformDocApiDocTemplateParamFieldVO : openplatformDocApiDocTemplateParamFieldVOS) {
                    newBasicVOS.add(OpenplatformDocApiDocParamFieldBasicVO.createByOpenplatformDocApiDocTemplateParamFieldVO(openplatformDocApiDocTemplateParamFieldVO));
                }

            }

            // 排序
            newBasicVOS = CollectionUtil.sort(newBasicVOS, (v1, v2) -> {
                return CompareUtil.compare(v1.getSeq(), v2.getSeq(), false);
            });
            result.put(longStringEntry.getValue(), newBasicVOS);
        }

        return result;
    }

    /**
     * 获取文档参数字段
     *
     * @param openplatformDocApiDocId
     * @return
     */
    private List<OpenplatformDocApiDocParamFieldBasicVO> getOpenplatformDocApiDocParamFieldBasicVOs(Long openplatformDocApiDocId) {
        List<OpenplatformDocApiDocParamFieldDO> openplatformDocApiDocParamFieldDOList = iOpenplatformDocApiDocParamFieldService.getByOpenplatformDocApiDocId(openplatformDocApiDocId);
        return OpenplatformDocApiDocParamFieldAppStructMapping.instance.openplatformDocApiDocParamFieldDOsToOpenplatformDocApiDocParamFieldBasicVOs(openplatformDocApiDocParamFieldDOList);
    }

    /**
     * 获取文档模板参数字段
     *
     * @param openplatformDocApiDocTemplateId
     * @return
     */
    private List<OpenplatformDocApiDocTemplateParamFieldVO> getOpenplatformDocApiDocTemplateParamFieldVOs(Long openplatformDocApiDocTemplateId) {
        List<OpenplatformDocApiDocTemplateParamFieldDO> openplatformDocApiDocTemplateParamFieldDOList = iOpenplatformDocApiDocTemplateParamFieldService.getByOpenplatformDocApiDocTemplateId(openplatformDocApiDocTemplateId);
        return OpenplatformDocApiDocTemplateParamFieldAppStructMapping.instance.openplatformDocApiDocTemplateParamFieldDOsToOpenplatformDocApiDocTemplateParamFieldVOs(openplatformDocApiDocTemplateParamFieldDOList);
    }

    /**
     * 获取最终的接口内容，如果相应字典没有设置但模板有值，会被模板对应的值填充
     *
     * @param openplatformDocApiId
     * @return
     */
    public OpenplatformDocApiDocVO getFinalOpenplatformDocApiDocVO(Long openplatformDocApiId) {
        OpenplatformDocApiDocVO openplatformDocApiDocVO = getOpenplatformDocApiDocVO(openplatformDocApiId);
        if (openplatformDocApiDocVO == null) {
            return null;
        }
        OpenplatformDocApiDocTemplateVO openplatformDocApiDocTemplateVO = getOpenplatformDocApiDocTemplateVO(openplatformDocApiDocVO.getOpenplatformDocApiDocTemplateId());
        if (openplatformDocApiDocTemplateVO != null) {
            openplatformDocApiDocVO.fillByOpenplatformDocApiDocTemplateVO(openplatformDocApiDocTemplateVO);
        }

        return openplatformDocApiDocVO;
    }

    /**
     * 获取文档内容
     *
     * @param openplatformDocApiId
     * @return
     */
    private OpenplatformDocApiDocVO getOpenplatformDocApiDocVO(Long openplatformDocApiId) {
        OpenplatformDocApiDocDO openplatformDocApiDocDO = iOpenplatformDocApiDocService.getByOpenplatformDocApiId(openplatformDocApiId);
        OpenplatformDocApiDocVO openplatformDocApiDocVO = OpenplatformDocApiDocAppStructMapping.instance.openplatformDocApiDocDOToOpenplatformDocApiDocVO(openplatformDocApiDocDO);
        return openplatformDocApiDocVO;
    }

    /**
     * 获取文档模板内容
     *
     * @param openplatformDocApiDocTemplateId
     * @return
     */
    private OpenplatformDocApiDocTemplateVO getOpenplatformDocApiDocTemplateVO(Long openplatformDocApiDocTemplateId) {
        OpenplatformDocApiDocTemplateDO openplatformDocApiDocTemplateDO = iOpenplatformDocApiDocTemplateService.getById(openplatformDocApiDocTemplateId);
        OpenplatformDocApiDocTemplateVO openplatformDocApiDocTemplateVO = OpenplatformDocApiDocTemplateAppStructMapping.instance.openplatformDocApiDocTemplateDOToOpenplatformDocApiDocTemplateVO(openplatformDocApiDocTemplateDO);
        return openplatformDocApiDocTemplateVO;
    }

    /**
     * 执行 开放接口文档接口 更新用详情查询指令
     *
     * @param detailForUpdateCommand
     * @return
     */
    public SingleResponse<OpenplatformDocApiVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
        OpenplatformDocApiDO byId = iOpenplatformDocApiService.getById(detailForUpdateCommand.getId());
        OpenplatformDocApiVO openplatformDocApiVO = OpenplatformDocApiAppStructMapping.instance.openplatformDocApiDOToOpenplatformDocApiVO(byId);
        return SingleResponse.of(openplatformDocApiVO);
    }

    @Autowired
    public void setIOpenplatformDocApiService(IOpenplatformDocApiService iOpenplatformDocApiService) {
        this.iOpenplatformDocApiService = iOpenplatformDocApiService;
    }

    @Autowired
    public void setIOpenplatformDocApiDocService(IOpenplatformDocApiDocService iOpenplatformDocApiDocService) {
        this.iOpenplatformDocApiDocService = iOpenplatformDocApiDocService;
    }

    @Autowired
    public void setIOpenplatformDocApiDocTemplateService(IOpenplatformDocApiDocTemplateService iOpenplatformDocApiDocTemplateService) {
        this.iOpenplatformDocApiDocTemplateService = iOpenplatformDocApiDocTemplateService;
    }

    @Autowired
    public void setiOpenplatformDocApiDocParamFieldService(IOpenplatformDocApiDocParamFieldService iOpenplatformDocApiDocParamFieldService) {
        this.iOpenplatformDocApiDocParamFieldService = iOpenplatformDocApiDocParamFieldService;
    }

    @Autowired
    public void setiOpenplatformDocApiDocTemplateParamFieldService(IOpenplatformDocApiDocTemplateParamFieldService iOpenplatformDocApiDocTemplateParamFieldService) {
        this.iOpenplatformDocApiDocTemplateParamFieldService = iOpenplatformDocApiDocTemplateParamFieldService;
    }

    @Autowired
    public void setOpenplatformDictGateway(OpenplatformDictGateway openplatformDictGateway) {
        this.openplatformDictGateway = openplatformDictGateway;
    }

    @Autowired
    public void setiOpenplatformDocApiDocResponseCodeService(IOpenplatformDocApiDocResponseCodeService iOpenplatformDocApiDocResponseCodeService) {
        this.iOpenplatformDocApiDocResponseCodeService = iOpenplatformDocApiDocResponseCodeService;
    }

    @Autowired
    public void setiOpenplatformDocApiDocTemplateResponseCodeService(IOpenplatformDocApiDocTemplateResponseCodeService iOpenplatformDocApiDocTemplateResponseCodeService) {
        this.iOpenplatformDocApiDocTemplateResponseCodeService = iOpenplatformDocApiDocTemplateResponseCodeService;
    }

    @Autowired
    public void setiOpenplatformDocApiDocExampleCodeService(IOpenplatformDocApiDocExampleCodeService iOpenplatformDocApiDocExampleCodeService) {
        this.iOpenplatformDocApiDocExampleCodeService = iOpenplatformDocApiDocExampleCodeService;
    }

    @Autowired
    public void setiOpenplatformDocApiDocTemplateExampleCodeService(IOpenplatformDocApiDocTemplateExampleCodeService iOpenplatformDocApiDocTemplateExampleCodeService) {
        this.iOpenplatformDocApiDocTemplateExampleCodeService = iOpenplatformDocApiDocTemplateExampleCodeService;
    }

    @Autowired
    public void setiOpenplatformOpenapiService(IOpenplatformOpenapiService iOpenplatformOpenapiService) {
        this.iOpenplatformOpenapiService = iOpenplatformOpenapiService;
    }
}
