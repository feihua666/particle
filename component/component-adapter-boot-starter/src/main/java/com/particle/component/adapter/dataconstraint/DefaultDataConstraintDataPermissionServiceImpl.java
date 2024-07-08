package com.particle.component.adapter.dataconstraint;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.particle.component.light.share.trans.TransTableNameConstants;
import com.particle.global.data.permission.DataPermissionService;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.exception.Assert;
import com.particle.global.mybatis.plus.dto.BaseDO;
import com.particle.global.security.security.login.*;
import com.particle.global.tool.script.GroovyTool;
import com.particle.global.tool.str.StringTool;
import com.particle.global.tool.template.TemplateRenderDataWrap;
import com.particle.global.tool.template.TemplateTool;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.script.Bindings;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * <p>
 * 基于数据范围约束的数据权限实现
 * </p>
 *
 * @author yangwei
 * @since 2024/7/4 18:32
 */
public class DefaultDataConstraintDataPermissionServiceImpl implements DataPermissionService {

    private UserDeptService userDeptService;

    private static UserDeptService userDeptServiceStatic;

    /**
     * 内置支持的非自定义数据范围片段
     */
    private static final Map<String, BiConsumer<AbstractWrapper,LoginUser>> builtInConstraintContent = new HashMap<>();
    /**
     * 内置支持的非自定义数据范围片段前缀，后缀应该是一个列名（字段名）
     */
    private static final Map<String, TriConsumer<AbstractWrapper,LoginUser,String>> builtInConstraintContentPrefixWithSuffixColumn = new HashMap<>();


    /**
     * 数据范围约束限制条件
     * @param wrapper
     * @param dataConstraintContext
     * @return
     */
    @Override
    public AbstractWrapper dataConstraint(AbstractWrapper wrapper, DataConstraintContext dataConstraintContext) {
        LoginUser loginUser = LoginUserTool.getLoginUser();
        if (loginUser == null) {
            return wrapper;
        }
        // 超级管理员不限制
        if (loginUser.getIsSuperAdmin()) {
            return wrapper;
        }
        List<GrantedDataConstraint> grantedDataConstraints = loginUser.finalizeDataConstraints();
        // 默认如果没有授权，不处理，也就是没有数据限制
        if (CollectionUtil.isEmpty(grantedDataConstraints)) {
            return wrapper;
        }

        // 如果没有数据约束上下文直接返回
        if (dataConstraintContext == null || StrUtil.isEmpty(dataConstraintContext.getDataObject())) {
            return wrapper;
        }
        String requestDataObject = dataConstraintContext.getDataObject();
        String requestAction = dataConstraintContext.getAction();
        GrantedDataConstraint grantedDataConstraint = getGrantedDataConstraint(grantedDataConstraints, requestDataObject);
        // 没有匹配到，直接返回
        if (grantedDataConstraint == null) {
            // todo 在没有配置数据范围约束时，默认返回，也就是不限制数据，可以在数据对象加上字段（isAllWhenUnAssign）设置来控制在没有配置时是全部数据还是没有数据
            return wrapper;
        }
        GrantedDataConstraint.GrantedDataObject grantedDataObject = grantedDataConstraint.getGrantedDataObject();
        // 数据对象禁用，直接返回
        if (grantedDataObject.getIsDisabled()) {
            return wrapper;
        }
        GrantedDataConstraint.GrantedDataScope grantedDataScope = grantedDataConstraint.getGrantedDataScope();
        DataConstraintContext.Action requestActionEnum = DataConstraintContext.Action.valueOf(requestAction);
        switch (requestActionEnum) {
            case delete: {
                if (!grantedDataScope.getIsForDelete()) {
                    return wrapper;
                }
                break;
            }
            case update: {
                if (!grantedDataScope.getIsForUpdate()) {
                    return wrapper;
                }
                break;
            }
            case query: {
                if (!grantedDataScope.getIsForQuery()) {
                    return wrapper;
                }
                break;
            }
            case other: {
                if (!grantedDataScope.getIsForOther()) {
                    return wrapper;
                }
                break;
            }
        }
        // 自定义数据
        String constraintContent = grantedDataScope.getConstraintContent();
        String constraintContentDictValue = grantedDataScope.getConstraintContentTypeDictValue();
        if (grantedDataScope.getIsCustom()) {
            List<Long> customeDataIds = grantedDataScope.getCustomDataIds();
            // 如果没有分配自定义数据，直接返回
            if (CollectionUtil.isEmpty(customeDataIds)) {
                return wrapper;
            }
            String inColumnName = BaseDO.COLUMN_ID;
            // 给自定义列提供一次修改的机会
            if (StrUtil.isNotEmpty(constraintContent)) {
                inColumnName = constraintContent.trim();
            }
            // 自定义数据使用in查询
            wrapper.in(inColumnName, customeDataIds);
        } else {
            if (StrUtil.isEmpty(constraintContent)) {
                return wrapper;
            }
            // 支持一些自定义内置片段
            String constraintContentTrimed = constraintContent.trim();
            if (builtInConstraintContent.containsKey(constraintContentTrimed)) {
                builtInConstraintContent.get(constraintContentTrimed).accept(wrapper, loginUser);
                return wrapper;
            }
            // 支持一些自定义内置片段，前缀+字段名
            for (Map.Entry<String, TriConsumer<AbstractWrapper, LoginUser, String>> stringTriConsumerEntry : builtInConstraintContentPrefixWithSuffixColumn.entrySet()) {
                if (constraintContentTrimed.startsWith(stringTriConsumerEntry.getKey())) {
                    stringTriConsumerEntry.getValue().accept(wrapper, loginUser, constraintContentTrimed.substring(stringTriConsumerEntry.getKey().length()));
                    return wrapper;
                }
            }

            // 约束条件内容
            String sql = getSql(loginUser,wrapper, constraintContent,constraintContentDictValue);
            if (StrUtil.isEmpty(sql)) {
                return wrapper;
            } else {
                wrapper.apply(sql);
            }
        }

        return wrapper;
    }

    /**
     * 获取当前的数据范围约束
     * @param grantedDataConstraints
     * @param dataObjectCode
     * @return
     */
    private GrantedDataConstraint getGrantedDataConstraint(List<GrantedDataConstraint> grantedDataConstraints, String dataObjectCode) {
        for (GrantedDataConstraint grantedDataConstraint : grantedDataConstraints) {
            if (grantedDataConstraint.getGrantedDataObject().getCode().equals(dataObjectCode)) {
                return grantedDataConstraint;
            }
        }
        return null;
    }

    /**
     * 获取sql片段的数据范围约束
     * @param loginUser
     * @param constraintContent
     * @return
     */
    @SneakyThrows
    private String getSql(LoginUser loginUser,AbstractWrapper wrapper,String constraintContent,String constraintContentDictValue) {
        GrantedDataConstraint.ConstraintContentType constraintContentType = GrantedDataConstraint.ConstraintContentType.valueOf(constraintContentDictValue);
        Map data = new HashMap<>();
        data.put("loginUser", loginUser);
        TemplateRenderDataWrap mapTemplateRenderDataWrap = TemplateRenderDataWrap.create(data);
        Map renderMap = mapTemplateRenderDataWrap.toRenderMap();
        switch (constraintContentType) {
            case sql_raw: {
                return constraintContent;
            }
            case sql_enjoy_template: {

                String render = TemplateTool.render(constraintContent, renderMap);
                return render;
            }
            case groovy_script: {
                data.put("wrapper", wrapper);
                Bindings bindings = GroovyTool.createBindings(renderMap);
                Object o = GroovyTool.compileAndEval(constraintContent, bindings, true);
                if (o instanceof String) {
                    return (String) o;
                }
            }
        }
        return null;
    }


    /**
     * 内置支持的非自定义数据范围片段
     */
    static {
        // 本人创建
        builtInConstraintContent.put("selfCreatedBy", (wrapper,loginUser) -> {
            builtInConstraintContentPrefixWithSuffixColumn.get("selfPrefix").accept(wrapper,loginUser,BaseDO.COLUMN_CREATE_BY);
        });
        // 本部门下的人创建
        builtInConstraintContent.put("selfDeptCreateBy", (wrapper,loginUser) -> {
            builtInConstraintContentPrefixWithSuffixColumn.get("selfDeptPrefix").accept(wrapper,loginUser,BaseDO.COLUMN_CREATE_BY);
        });
        // 本部门及以下部门下的人创建
        builtInConstraintContent.put("selfDeptAndSubDeptCreateBy", (wrapper,loginUser) -> {
            builtInConstraintContentPrefixWithSuffixColumn.get("selfDeptAndSubDeptPrefix").accept(wrapper,loginUser,BaseDO.COLUMN_CREATE_BY);
        });
        // 已分配的部门数据范围下的人创建
        builtInConstraintContent.put("selfAssignedDeptCreateBy", (wrapper,loginUser) -> {
            builtInConstraintContentPrefixWithSuffixColumn.get("selfAssignedDeptPrefix").accept(wrapper,loginUser,BaseDO.COLUMN_CREATE_BY);
        });
    }
    /**
     * 内置支持的非自定义数据范围片段,主要是支持前缀固定，后缀自定义指定字段，方便不是按 create_by 的表
     */
    static {
        // 本人
        builtInConstraintContentPrefixWithSuffixColumn.put("selfPrefix", (wrapper,loginUser,suffixColumnName) -> {
            wrapper.eq(StringTool.humpToLine(suffixColumnName), loginUser.getId());
        });
        // 本部门下的人
        builtInConstraintContentPrefixWithSuffixColumn.put("selfDeptPrefix", (wrapper,loginUser,suffixColumnName) -> {
            DeptInfo deptInfo = loginUser.getDeptInfo();
            String sqlTemplate = "select user_id from {} u where u.dept_id = {}";
            wrapper.inSql(deptInfo != null,
                    StringTool.humpToLine(suffixColumnName),
                    StrUtil.format(sqlTemplate,TransTableNameConstants.component_dept_user_rel,deptInfo.getId().toString()));
        });
        // 本部门及以下部门下的人
        builtInConstraintContentPrefixWithSuffixColumn.put("selfDeptAndSubDeptPrefix", (wrapper,loginUser,suffixColumnName) -> {
            DeptInfo deptInfo = loginUser.getDeptInfo();
            if (deptInfo == null) {
                return;
            }
            String sqlTemplate = "select u.user_id from {} d join {} u on u.dept_id = d.id where d.id = {} or d.parent_id{} = {}";
            String sqlFormat = StrUtil.format(sqlTemplate,TransTableNameConstants.component_dept,TransTableNameConstants.component_dept_user_rel,deptInfo.getId().toString(),deptInfo.getLevel().toString(),deptInfo.getId().toString());
            wrapper.inSql(deptInfo != null,StringTool.humpToLine(suffixColumnName),sqlFormat);
        });

        // 已分配的部门数据范围下的人
        builtInConstraintContentPrefixWithSuffixColumn.put("selfAssignedDeptPrefix", (wrapper,loginUser,suffixColumnName) -> {
            Assert.notNull(userDeptServiceStatic,"不支持的数据范围约束 selfAssignedDeptPrefix，请检查配置信息");

            List<DeptInfo> deptInfos = userDeptServiceStatic.retrieveDeptInfoByDataConstraint();
            if (CollectionUtil.isEmpty(deptInfos)) {
                //没有数据范围内的部门数据，则数据权限为空， 本来想设置为false，但不太好排查问题，所以设置一个表达式
                wrapper.apply("'DeptInfoByDataConstraint' = 'empty'");
                return;
            }
            String sqlTemplate = "select user_id from {} u where u.dept_id in ({})";
            // 逗号分隔的部门id
            String commaSeparatedDeptIds = deptInfos.stream().map(deptInfo -> deptInfo.getId().toString()).distinct().collect(Collectors.joining(","));
            wrapper.inSql(StringTool.humpToLine(suffixColumnName),
                    StrUtil.format(sqlTemplate,TransTableNameConstants.component_dept_user_rel,commaSeparatedDeptIds));
        });
    }

    /**
     * 自定义一个接收三个参数的消费类
     * @param <T>
     * @param <U>
     * @param <V>
     */
    @FunctionalInterface
    private interface TriConsumer<T, U, V> {

        void accept(T t, U u, V v);

    }

    @Autowired(required = false)
    public void setUserDeptService(UserDeptService userDeptService) {
        this.userDeptService = userDeptService;
        this.userDeptServiceStatic = userDeptService;
    }
}
