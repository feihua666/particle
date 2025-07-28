package com.particle.cms.adapter.dynamic.directive;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.cms.client.dto.command.directive.CmsDirectivePageQueryCommand;
import com.particle.cms.client.api.ICmsDynamicApplicationService;
import com.particle.cms.client.dto.data.CmsSiteVO;
import com.particle.cms.client.dto.data.dynamic.CmsSiteTemplateModelVO;
import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 自定义指令基础类
 * 如自定义一个指令 user_list
 * 使用方式如下：
 * <@user_list param="男" param1="hello" param2=123 param3=true param4=["apple", "banana"]; item, index>
 *     <p>Index: ${index}, Channel: ${item}</p>
 * </@user_list>
 *     param1 是字符串，会被解析为 TemplateScalarModel。
 *     param2 是数字，会被解析为 TemplateNumberModel。
 *     param3 是布尔值，会被解析为 TemplateBooleanModel。
 *     param4 是序列，会被解析为 TemplateSequenceModel。
 *     注意：{@link AbstractDirective#getParamStr(String, Map)} 将数据转为了字符串
 * Created by yangwei
 * Created at 2025-07-08 10:34:38
 */
public abstract class AbstractDirective implements
        TemplateDirectiveModel {

    protected static final String param_site_id = "siteId";
    protected static final String param_channel_id = "channelId";
    protected static final String param_content_id = "contentId";
    protected static final String param_channel_parent_id = "parentId";


    // 迭代类型
    protected static final String param_iterator_type = "iteratorType";
    protected static final String param_iterator_type_value_default = "default";
    protected static final String param_iterator_type_value_var = "var";

    @Autowired
    protected ICmsDynamicApplicationService iCmsDynamicApplicationService;


    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        doExecute(env,params,loopVars,body);
    }

    /**
     * 执行指令
     * @param env
     * @param params 类型：Map<String, TemplateModel>
     * @param loopVars 用于绑定循环变量，变量顺序为：0,1,2,3,4,5... 依次绑定，需要在使用指令时指定，分号后面的变量名称
     * @param body
     * @throws TemplateException
     * @throws IOException
     */
    public abstract void doExecute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException;

    protected Long getSiteId(Map params){
        return getParamLong(param_site_id,params);
    }
    protected Long getChannelId(Map params){
        return getParamLong(param_channel_id,params);
    }
    protected Long getContentId(Map params){
        String contentIdStr = getParamStr(param_content_id,params);
        return StrUtil.isBlank(contentIdStr) ? null : Long.valueOf(contentIdStr);
    }
    protected Long getParentId(Map params){
        return getParamLong(param_channel_parent_id,params);
    }
    protected Long getParentId1(Map params){
        return getParamLong(param_channel_parent_id + "1",params);
    }
    protected Long getParentId2(Map params){
        return getParamLong(param_channel_parent_id + "2",params);
    }
    protected Long getParentId3(Map params){
        return getParamLong(param_channel_parent_id + "3",params);
    }
    protected Long getParentId4(Map params){
        return getParamLong(param_channel_parent_id + "4",params);
    }
    protected Long getParentId5(Map params){
        return getParamLong(param_channel_parent_id + "5",params);
    }
    protected Long getParentId6(Map params){
        return getParamLong(param_channel_parent_id + "6",params);
    }
    protected Long getParentId7(Map params){
        return getParamLong(param_channel_parent_id + "7",params);
    }
    protected Long getParentId8(Map params){
        return getParamLong(param_channel_parent_id + "8",params);
    }
    protected Long getParentId9(Map params){
        return getParamLong(param_channel_parent_id + "9",params);
    }
    protected Long getParentId10(Map params){
        return getParamLong(param_channel_parent_id + "10",params);
    }

    /**
     * 获取迭代类型，默认为default，var为变量模式，此时迭代变量为var
     * @param params
     * @return
     */
    protected String getIteratorType(Map params){
        String type =  getParamStr(param_iterator_type,params);
        if (!param_iterator_type_value_var.equals(type)){
            return param_iterator_type_value_default;
        }

        return type;
    }

    /**
     * 获取指令中的参数，以字符形式返回
     * @param paramName
     * @param params
     * @return
     */
    protected String getParamStr(String paramName, Map params){
        Object obj = params.get(paramName);
        if (obj != null) {
            return obj.toString();
        }
        return null;
    }

    /**
     * 获取指令中的参数，以Long形式返回
     * @param paramName
     * @param params
     * @return
     */
    protected Long getParamLong(String paramName, Map params) {
        String paramStr = getParamStr(paramName, params);
        return StrUtil.isBlank(paramStr) ? null : Long.valueOf(paramStr);
    }

    /**
     * 绑定循环变量
     * @param index
     * @param loopVar
     * @param loopVars
     */
    void bindLoopVars(int index,TemplateModel loopVar,TemplateModel[] loopVars){
        if (loopVars != null && index >= 0 && loopVars.length > index ) {
            loopVars[index] = loopVar;
        }
    }

    /**
     * 封装对象为模板对象
     * @param obj
     * @return
     * @throws TemplateModelException
     */
    protected TemplateModel wrapTemplateModel(Object obj) throws TemplateModelException {
        return DefaultObjectWrapperBuilderFactory.getDefaultObjectWrapper().wrap(obj);
    }

    /**
     * 获取分页查询参数
     * @param params
     * @return
     */
    protected CmsDirectivePageQueryCommand getPageQueryCommand(Map params){
        String pageNo = getParamStr("pageNo", params);
        String pageSize = getParamStr("pageSize", params);
        String isPage = getParamStr("isPage", params);
        String orderBy = getParamStr("orderBy", params);
        String isOrderBy = getParamStr("isOrderBy", params);

        boolean isPageBool = BooleanUtil.toBoolean(isPage);
        boolean isOrderByBool = BooleanUtil.toBoolean(isOrderBy);

        CmsDirectivePageQueryCommand cmsDirectivePageQueryCommand = new CmsDirectivePageQueryCommand();
        cmsDirectivePageQueryCommand.setIsPage(isPageBool);
        if (StrUtil.isNotEmpty(pageNo)) {
            cmsDirectivePageQueryCommand.setPageNo(NumberUtil.parseLong(pageNo));
        }
        if (StrUtil.isNotEmpty(pageSize)) {
            cmsDirectivePageQueryCommand.setPageSize(NumberUtil.parseLong(pageSize));
        }

        cmsDirectivePageQueryCommand.setIsOrderBy(isOrderByBool);
        cmsDirectivePageQueryCommand.setOrderBy(orderBy);
        return cmsDirectivePageQueryCommand;
    }


    /**
     * 渲染body
     * @param env
     * @param params
     * @param loopVars
     * @param body
     * @param dataList 数据列表
     * @param varName 变量模式时的变量名
     * @param dataListItemMappingFunction 数据列表项映射函数
     * @throws TemplateException
     * @throws IOException
     */
    protected void bodyRender(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body,
                              List dataList, String varName, Function dataListItemMappingFunction) throws TemplateException, IOException {
        if (CollectionUtil.isNotEmpty(dataList)) {
            String iteratorType = getIteratorType(params);
            // 迭代模式
            if(param_iterator_type_value_default.equals(iteratorType)){
                Object dataListItem = null;
                for (int i = 0; i < dataList.size(); i++) {
                    dataListItem = dataList.get(i);
                    TemplateModel item =  wrapTemplateModel(dataListItemMappingFunction.apply(dataListItem));
                    TemplateModel index = new SimpleNumber(i);
                    bindLoopVars(0,item,loopVars);
                    bindLoopVars(1,index,loopVars);
                    body.render(env.getOut());
                }
            }
            // 变量模式,主要用于将列表赋值给 include 子片段使用
            else if(param_iterator_type_value_var.equals(iteratorType)){
                List<Object> cmsSiteTemplateModelVOs = new ArrayList<>(dataList.size());
                Object dataListItem = null;
                Object cmsSiteTemplateModelVO = null;
                for (int i = 0; i < dataList.size(); i++) {
                    dataListItem = dataList.get(i);
                    cmsSiteTemplateModelVO = dataListItemMappingFunction.apply(dataListItem);
                    cmsSiteTemplateModelVOs.add(cmsSiteTemplateModelVO);
                }
                TemplateModel items =  wrapTemplateModel(cmsSiteTemplateModelVOs);
                env.setVariable(varName,items);
                body.render(env.getOut());
                //清除变量
                env.setVariable(varName,null);
            }else{
                body.render(env.getOut());
            }

        }else{
            body.render(env.getOut());
        }
    }
}
