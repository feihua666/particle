package com.particle.global.mybatis.plus.crud;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.Update;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.plugins.IgnoreStrategy;
import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.LambdaMeta;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.particle.global.dto.basic.*;
import com.particle.global.dto.dataconstraint.DataConstraintContext;
import com.particle.global.light.share.mybatis.anno.*;
import com.particle.global.mybatis.plus.dto.BaseDO;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import com.particle.global.mybatis.plus.dto.RelDTO;
import com.particle.global.tool.str.StringTool;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;


/**
 * 所有service接口的父接口，以实现通用功能
 * @author yangwei
 * @since 2019/7/23 14:30
 */
public interface IBaseService<DO> extends IService<DO> {

    /*************************** 增 开始 *************************************/
    /**
     * 添加实体
     * @param dos
     * @return
     */
    DO add(DO dos);

    /**
     * 复制实体
     * @param id
     * @param doHandle 可以对实体数据做一些处理,返回null不复制
     * @return
     */
    DO copy(Long id, Function<DO,DO> doHandle);

    /**
     * 复制实体树
     * @param id
     * @param doHandle
     * @param isIncludeAllChildren
     * @return
     */
    DO copyAndCopyChildren(Long id, Function<DO,DO> doHandle,Boolean isIncludeAllChildren);

    /*************************** 增 结束 *************************************/
    /*************************** 删 开始 *************************************/

    /**
     * 删除
     * @param id
     * @return
     */
    boolean deleteById(Long id);
    /**
     * 删除, 主要拉架对数据权限的支持
     * @param idCommand
     * @return
     */
    boolean deleteById(IdCommand idCommand);

    /**
     * 根据属性字段删除
     * @param columnId
     * @param column
     * @return
     */
    boolean deleteByColumn(Object columnId,  SFunction<DO, ?> column);

    /**
     * 根据属性字段删除，增加对数据权限的支持
     * @param columnId
     * @param column
     * @param dataConstraintContext
     * @return
     */
    boolean deleteByColumn(Object columnId, SFunction<DO, ?> column, DataConstraintContext dataConstraintContext);

    /*************************** 删 结束 *************************************/
    /*************************** 改 开始 *************************************/

    DO update(DO dos);

    /**
     * 修改，增加对数据权限的支持
     * @param dos
     * @param updateCommand
     * @return
     */
    DO update(DO dos, UpdateCommand updateCommand);
    /**
     * 根据主键增加一个字段的值，
     * @param id
     * @param plusColumn
     * @param plusNum
     * @return
     */
    default boolean plusForColumnById(Long id, SFunction<DO, ?> plusColumn, Object plusNum){
        Assert.notNull(plusColumn,"plusColumn 不能为空");
        Assert.notNull(id,"id 不能为空");

        String columnName  = SFunctionHelperTool.columnToString(plusColumn);
        String sql = StrUtil.format(" {} = {} + {} ",columnName,columnName,plusNum);
        return update(Wrappers.<DO>update().setSql(sql).eq(com.particle.global.dto.basic.DO.COLUMN_ID,id));
    }
    /**
     * 根据主键增加一个字段的值，
     * @param columnValue
     * @param column
     * @param plusColumn
     * @param plusNum
     * @return
     */
    default boolean plusForColumnByColumn(Object columnValue,SFunction<DO, ?> column, SFunction<DO, ?> plusColumn, Object plusNum){
        Assert.notNull(column,"column 不能为空");
        Assert.notNull(columnValue,"columnValue 不能为空");
        Assert.notNull(plusColumn,"plusColumn 不能为空");

        String columnName  = SFunctionHelperTool.columnToString(column);
        String plusColumnName  = SFunctionHelperTool.columnToString(plusColumn);
        String sql = StrUtil.format(" {} = {} + {} ",plusColumnName,plusColumnName,plusNum);
        return update(Wrappers.<DO>update().setSql(sql).eq(columnName,columnValue));
    }
    /**
     * 根据一列更新一个字段的值，
     * @param columnValue
     * @param column
     * @param updateColumn
     * @param updateValue
     * @return
     */
    default boolean updateColumnByColumn(Object columnValue,SFunction<DO, ?> column, SFunction<DO, ?> updateColumn, Object updateValue){
        Assert.notNull(updateColumn,"column 不能为空");
        Assert.notNull(columnValue,"columnValue 不能为空");
        Assert.notNull(updateColumn,"updateColumn 不能为空");
        return update(Wrappers.<DO>lambdaUpdate().set(updateColumn,updateValue).eq(column,columnValue));
    }
    /**
     * 可以根据任务唯一字段进行指更新
     * 对{@link  IService#saveOrUpdateBatch(Collection)}  的一个补充，其只能根据主键id更新
     * @param entityList
     * @return
     */
    default boolean saveOrUpdateBatchByUniqueColumn(Collection<DO> entityList, SFunction<DO, ?> column) {
        return saveOrUpdateBatchByUniqueColumn(entityList,column,DEFAULT_BATCH_SIZE);
    }

    /**
     * 实时从数据库查询是否指定字段已存在
     * 可以根据任务唯一字段进行指更新，不适用主键，如果主键请使用mybatisPlus指定方法{@link IService#saveOrUpdateBatch(Collection, int)}
     * 对{@link IService#saveOrUpdateBatch(Collection, int)} 的一个补充，其只能根据主键id更新
     * @param entityList
     * @param batchSize
     * @return
     */
    boolean saveOrUpdateBatchByUniqueColumn(Collection<DO> entityList, SFunction<DO, ?> column, int batchSize);

    /**
     * 可以根据任务唯一字段进行指更新
     * 对{@link IService#updateBatchById(Collection)} 的一个补充，其只能根据主键id更新
     * @param entityList
     * @return
     */
    default boolean updateBatchByUniqueColumn(Collection<DO> entityList, SFunction<DO, ?> column) {
        return updateBatchByUniqueColumn(entityList,column,DEFAULT_BATCH_SIZE);
    }

    /**
     * 可以根据任意唯一字段进行指更新
     * 对{@link IService#updateBatchById(Collection,int)} 的一个补充，其只能根据主键id更新
     * @param entityList
     * @param batchSize
     * @return
     */
    boolean updateBatchByUniqueColumn(Collection<DO> entityList, SFunction<DO, ?> column, int batchSize);

    /*************************** 改 结束 *************************************/
    /*************************** 查 开始 *************************************/


    /**
     * 根据id查询
     * @param id
     * @return
     */
    default DO queryById(Long id){
        return queryById(IdCommand.create(id));
    }

    /**
     * 根据id查询,新增对数据范围的支持
     * @param idCommand
     * @return
     */
    DO queryById(IdCommand idCommand);

    /**
     * 不考虑租户的mybatis plus 插件限制
     * @param id
     * @return
     */
    default DO getByIdIgnoreTenantLimit(Long id) {
        try {
            // 设置忽略租户插件
            InterceptorIgnoreHelper.handle(IgnoreStrategy.builder().tenantLine(true).build());
            return getById(id);
        } finally {
            InterceptorIgnoreHelper.clearIgnoreStrategy();
        }
    }

    /**
     * 判断数据是否存在
     * @param id
     * @return
     */
    default boolean existById(Long id) {
        Assert.notNull(id,"id 不能为空");

        return count( Wrappers.<DO>query().eq(com.particle.global.dto.basic.DO.COLUMN_ID,id)) > 0;
    }


    /**
     * 判断数据是否存在
     * @param columnValue
     * @param column DO::getId
     * @return
     */
    default boolean existByColumn(Object columnValue, SFunction<DO, ?> column) {
        Assert.notNull(column,"column 不能为空");
        Assert.notNull(columnValue,"columnValue 不能为空");
        return existByColumnWithTenantId(columnValue,column,null);
    }

    /**
     * 判断数据是否存在，手动指定租户
     * @param columnValue
     * @param column
     * @param tenantId
     * @return
     */
    default boolean existByColumnWithTenantId(Object columnValue, SFunction<DO, ?> column,Long tenantId) {
        Assert.notNull(column,"column 不能为空");
        Assert.notNull(columnValue,"columnValue 不能为空");
        return count( Wrappers.<DO>query().eq(tenantId != null,BaseDO.COLUMN_TENANT_ID,tenantId).lambda().eq(column,columnValue)) > 0;
    }
    /**
     * 根据一个外键查询
     * @param columnId
     * @param column
     * @return
     */
    default List<DO> listByColumn(Long columnId, SFunction<DO, ?> column){
        Assert.notNull(column,"column 不能为空");
        Assert.notNull(columnId,"columnId 不能为空");
        return list( Wrappers.<DO>query().lambda().eq(column,columnId));
    }
    /**
     * 根据多个外键查询
     * @param columnIds
     * @param column
     * @return
     */
    default List<DO> listByColumns(List<Long> columnIds, SFunction<DO, ?> column){
        Assert.notNull(column,"column 不能为空");
        Assert.notEmpty(columnIds,"columnIds 不能为空");
        return list( Wrappers.<DO>query().lambda().in(column,columnIds));
    }
    /**
     * 根据一个外键查询
     * @param columnId
     * @param column
     * @return
     */
    default DO getOneByColumn(Object columnId, SFunction<DO, ?> column){
        Assert.notNull(column,"column 不能为空");
        Assert.notNull(columnId,"columnId 不能为空");
        return getOne( Wrappers.<DO>query().lambda().eq(column,columnId));
    }

    /**
     * 获取更新时间最新的一条数据
     * @return
     */
    default DO getLastestUpdate(){
        Page<DO> page = page(
                new Page<>(1, 1),
                Wrappers.<DO>query().orderByDesc(BaseDO.COLUMN_UPDATE_AT)
        );
        List<DO> records = page.getRecords();
        return records.stream().findFirst().orElse(null);
    }

    /**
     * 查询单个字段，并返回property
     * @param selectColumn
     * @param columnId
     * @param column
     * @return
     */
    default List<?> listSingleColumnFieldByColumn(SFunction<DO, ?> selectColumn, Long columnId, SFunction<DO, ?> column){
        List<DO> list = listSingleColumnByColumn(selectColumn,columnId,column);
        return list.stream().map(selectColumn).collect(Collectors.toList());
    }

    /**
     * 查询单个字段
     * @param selectColumn
     * @param columnId
     * @param column
     * @return 只有查询的字段有值
     */
    default List<DO> listSingleColumnByColumn(SFunction<DO, ?> selectColumn, Long columnId, SFunction<DO, ?> column){
        Assert.notNull(selectColumn,"selectColumn 不能为空");
        Assert.notNull(column,"column 不能为空");
        Assert.notNull(columnId,"columnId 不能为空");
        List<DO> list = list(Wrappers.<DO>query().lambda().select(selectColumn).eq(column, columnId));
        return list;
    }
    /**
     * baomidou不支持 or连接，这里手动转一下
     * issue https://github.com/baomidou/mybatis-plus/issues/3418
     * 该方法的初衷是拼接or使用，主要用户公共字段包含
     * @param queryWrapper
     * @return
     */
    default QueryWrapper<DO> convertEntityConditionToWrapper(QueryWrapper<DO> queryWrapper) {
        DO entity = queryWrapper.getEntity();
        if (entity == null) {
            return queryWrapper;
        }
        queryWrapper.setEntity(null);
        Field[] fields = ReflectUtil.getFields(entity.getClass());
        for (Field field : fields) {
            Object fieldValue = ReflectUtil.getFieldValue(entity, field);
            if (fieldValue != null && !Modifier.isStatic(field.getModifiers())) {
                queryWrapper.eq(StringTool.humpToLine(field.getName()), fieldValue);
            }
        }
        return queryWrapper;
    }

    /**
     * 不分页查询，
     * @param queryForm
     * @return
     */
    default List<DO> list(QueryCommand queryForm) {
        return list(getQueryWrapper(queryForm));
    }
    /**
     * 分页查询
     * @param pageQueryForm
     * @return
     */
    default Page<DO> listPage(PageQueryCommand pageQueryForm) {
        Page pageQuery = new Page((pageQueryForm).getPageNo(), (pageQueryForm).getPageSize());
        return page(pageQuery,getQueryWrapper(pageQueryForm));
    }
    /**
     * 根据表单组装查询条件
     * @param queryForm
     * @return
     */
    QueryWrapper<DO> getQueryWrapper(QueryCommand queryForm);
    
    /*************************** 查 结束 *************************************/
    /*************************** 其它 开始 *************************************/
    /**
     * 断言根据该列查询是否存在或不存在
     * 参见：{@link IBaseService#assertByColumn(java.lang.Object, com.baomidou.mybatisplus.core.toolkit.support.SFunction, boolean, java.lang.String)}
     * @param columnValue
     * @param column
     * @param exist
     */
    default void assertByColumn(Object columnValue, SFunction<DO, ?> column, boolean exist) {
        assertByColumn(columnValue, column, exist, null);
    }
    /**
     * 断言根据该列查询是否存在或不存在
     * 参见：{@link IBaseService#assertByColumn(java.lang.Object, com.baomidou.mybatisplus.core.toolkit.support.SFunction, boolean, java.lang.String)}
     * @param columnValue
     * @param column
     * @param exist
     */
    default void assertByColumn(Object columnValue, SFunction<DO, ?> column,Long tenantId, boolean exist) {
        assertByColumnWithTenantId(columnValue, column,tenantId, exist, null);
    }
    /**
     * 断言根据该列查询是否存在或不存在
     * 参见：{@link IBaseService#assertByColumn(java.lang.Object, com.baomidou.mybatisplus.core.toolkit.support.SFunction, boolean, java.lang.String)}
     * @param columnValue
     * @param column
     * @param exist
     */
    default void assertByColumnWithTenantId(Object columnValue, SFunction<DO, ?> column,Long tenantId, boolean exist) {
        assertByColumnWithTenantId(columnValue, column,tenantId, exist, null);
    }
    /**
     * 断言根据该列查询是否存在或不存在
     * @param columnValue
     * @param column
     * @param exist true=断言存在，false=断言不存在
     */
    default void assertByColumn(Object columnValue, SFunction<DO, ?> column, boolean exist,String message) {
        assertByColumnWithTenantId(columnValue, column, null, exist, message);
    }

    /**
     * 断言根据该列查询是否存在或不存在
     * @param columnValue
     * @param column
     * @param exist true=断言存在，false=断言不存在
     */
    default void assertByColumnWithTenantId(Object columnValue, SFunction<DO, ?> column,Long tenantId, boolean exist,String message) {
        Assert.notNull(column,"column 不能为空");
        Assert.notNull(columnValue,"columnValue 不能为空");
        boolean existByColumn = existByColumnWithTenantId(columnValue, column,tenantId);
        if(exist == existByColumn){
            return;
        }

        String messageTemp = message;
        if (messageTemp == null) {
            String realDbColumn = SFunctionHelperTool.columnToString(column);
            // 获取swagger注解
            LambdaMeta resolve = LambdaUtils.extract(column);
            String propertyName = StringTool.lineToHump(realDbColumn);
            String columnLabel = propertyName;
            Field field = ReflectUtil.getField(resolve.getInstantiatedClass(), propertyName);
            if (field != null) {
                Schema apiModelProperty = AnnotationUtil.getAnnotation(field, Schema.class);
                if (apiModelProperty != null && StrUtil.isNotEmpty(apiModelProperty.title())) {
                    // 取第一个逗号逗号分隔的字符作为提示标签
                    columnLabel = apiModelProperty.title().split(",")[0].split("，")[0];
                }
            }
            if (exist) {
                messageTemp = columnLabel + " " + columnValue + " " +  "不存在";
            }else {
                messageTemp = columnLabel + " " + columnValue + " " + "已存在";
            }
        }

        if (exist) {
            Assert.isTrue(existByColumn,messageTemp);
        }else {
            Assert.isTrue(!existByColumn,messageTemp);
        }
    }
    /**
     * 断言数据是否存在
     * @param queryWrapper
     * @param exist true=断言存在，false=断言不存在
     * @param msg 不满足条件时的提示消息
     */
    default void assertByWrapper(Wrapper<DO> queryWrapper, boolean exist, String msg){
        long count = count(queryWrapper);
        boolean existByWrapper = count > 0;
        if (exist) {
            Assert.isTrue(existByWrapper,msg);
        }else {
            Assert.isTrue(!existByWrapper,msg);
        }
    }
    /*************************** 其它 结束 *************************************/


    /****************************** 扩展辅助功能 开始*************************************************/


    /**
     * 更新注解处理
     * 仅限updateform添加注解使用 SetNullWhenNull 注解支持
     * @param updateWrapper
     * @param query
     */
    default void annotationSupportUpdateWrapper(Update updateWrapper, Object query){

        if (query == null) {
            return;
        }
        for (Field field : ReflectUtil.getFields(query.getClass())) {

            Ignore ignore = AnnotationUtil.getAnnotation(field, Ignore.class);
            if (ignore != null) {
                continue;
            }
            Object fieldValue = ReflectUtil.getFieldValue(query, field);
            String columnName = StringTool.humpToLine(field.getName());
            if (fieldValue == null) {
                SetNullWhenNull setNullWhenNull = AnnotationUtil.getAnnotation(field, SetNullWhenNull.class);
                if (setNullWhenNull != null) {
                    updateWrapper.set(columnName, null);
                }
            }else {
                if (fieldValue instanceof String && StrUtil.isEmpty((String)fieldValue)) {
                    SetNullWhenNull setNullWhenNull = AnnotationUtil.getAnnotation(field, SetNullWhenNull.class);
                    if (setNullWhenNull != null && setNullWhenNull.includeEmpty()) {
                        updateWrapper.set(columnName, null);
                    }
                }
            }

        }// end for
    }

    /**
     * 查询注解处理
     * @param queryWrapper
     * @param query 查询实体载体
     */
    default void annotationSupportQueryWrapper(Wrapper<DO> queryWrapper, Object query){

        if (query == null) {
            return;
        }

        // 补充一个条件，否则在拼接or的时候会拼接不对，而是and
        ((AbstractWrapper) queryWrapper).apply(true, "true");
        boolean userCustomOrderBy = false;
        // 标识是否为查询场景
        boolean isQuery = false;
        if (QueryCommand.class.isAssignableFrom(query.getClass())) {

            isQuery = true;

            String orderBy = ((QueryCommand) query).getOrderBy();
            if (StrUtil.isNotEmpty(orderBy)) {
                userCustomOrderBy = true;
                String[] orderBys = orderBy.split(",");
                for (String orderByItem : orderBys) {
                    String[] orderByItems = orderByItem.split("-");
                    String columnName = StringTool.humpToLine(orderByItems[0]);
                    ((AbstractWrapper) queryWrapper).orderBy(true, orderByItems.length > 1 ? "1".equals(orderByItems[1]) : true, columnName);
                }
            }
        }

        Map<String, OrderBy> orderByMap = new HashMap<>();
        if (!userCustomOrderBy) {
            // 类上注解支持
            OrderBy orderByClass = AnnotationUtil.getAnnotation(query.getClass(), OrderBy.class);
            if (orderByClass != null) {
                String columnName = StringTool.humpToLine(orderByClass.value());
                if (StrUtil.isNotEmpty(columnName)) {
                    orderByMap.put(columnName,orderByClass);
                }else {
                    throw new RuntimeException(query.getClass().getName() + "指定了OrderBy但未指定列名");
                }
            }
        }


        for (Field field : ReflectUtil.getFields(query.getClass())) {
            Object fieldValue = ReflectUtil.getFieldValue(query, field);
            Ignore ignore = AnnotationUtil.getAnnotation(field, Ignore.class);
            if (ignore != null) {
                continue;
            }

            // orderby处理
            if (!userCustomOrderBy) {
                OrderBy orderBy = AnnotationUtil.getAnnotation(field, OrderBy.class);
                if (orderBy != null) {
                    String columnName = StringTool.humpToLine(orderBy.value());
                    if (StrUtil.isEmpty(columnName)) {
                        columnName = StringTool.humpToLine(field.getName());
                    }
                    orderByMap.put(columnName,orderBy);
                }
            }


            In in = AnnotationUtil.getAnnotation(field, In.class);
            if (in != null && fieldValue != null) {
                Object finalFieldValue = fieldValue;
                Collection finalFieldValueCollection = (Collection) finalFieldValue;
                if (!finalFieldValueCollection.isEmpty()) {
                    (((AbstractWrapper) queryWrapper)).in(StringTool.humpToLine(Optional.ofNullable(StrUtil.emptyToNull(in.value())).orElse(field.getName())), finalFieldValueCollection);
                    setObjectValueNull(queryWrapper.getEntity(), field.getName());

                }
                continue;
            }

            if (fieldValue == null) {
                continue;
            }
            // Ne 处理
            Ne ne = AnnotationUtil.getAnnotation(field, Ne.class);
            if(ne != null){
                String neValue = ne.value();
                if (StrUtil.isNotEmpty(neValue)) {
                    ((AbstractWrapper) queryWrapper).ne( StringTool.humpToLine(neValue), fieldValue);
                }else {
                    ((AbstractWrapper) queryWrapper).ne( StringTool.humpToLine(field.getName()), fieldValue);
                    setObjectValueNull(queryWrapper.getEntity(), field.getName());
                }
                continue;
            }
            // like 处理
            Like like = AnnotationUtil.getAnnotation(field, Like.class);
            if(like != null){
                // 在查询时，一般前端请求都可能传了空字符串，这里也是拼接，导致查询有问题，这里根据全局配置来决定
                if (fieldValue instanceof String && StrUtil.isEmpty((String)fieldValue) && isQuery) {
                    Class<?> entityClass = ((AbstractWrapper<?, ?, ?>) queryWrapper).getEntityClass();
                    TableFieldInfo tableFieldInfo = Optional.ofNullable(entityClass)
                            .map(clazz -> TableInfoHelper.getTableInfo(entityClass))
                            .map(tableInfo -> tableInfo.getFieldList())
                            .map(fieldList -> fieldList.stream().filter(fieldInfo -> fieldInfo.getProperty().equals(field.getName())).findFirst().orElse(null))
                            .orElse(null);
                    if (tableFieldInfo != null && tableFieldInfo.getWhereStrategy() == FieldStrategy.NOT_EMPTY) {
                        continue;
                    }
                }

                if (like.left() && like.right()) {
                    ((AbstractWrapper) queryWrapper).like( StringTool.humpToLine(field.getName()), fieldValue);
                }else if (like.left()){
                    ((AbstractWrapper) queryWrapper).likeLeft( StringTool.humpToLine(field.getName()), fieldValue);
                }else if (like.right()){
                    ((AbstractWrapper) queryWrapper).likeRight( StringTool.humpToLine(field.getName()), fieldValue);
                }else {
                    ((AbstractWrapper) queryWrapper).likeLeft( StringTool.humpToLine(field.getName()), fieldValue);
                }
                setObjectValueNull(queryWrapper.getEntity(), field.getName());
                continue;
            }
            // 大于 处理
            Gt gt = AnnotationUtil.getAnnotation(field, Gt.class);
            if (gt != null) {
                String gtValue = gt.value();
                if (StrUtil.isEmpty(gtValue)) {
                    throw new RuntimeException("你必须指定比较字段的名称");
                }
                if (gt.eq()) {
                    ((AbstractWrapper) queryWrapper).ge( StringTool.humpToLine(gtValue), fieldValue);

                }else {
                    ((AbstractWrapper) queryWrapper).gt( StringTool.humpToLine(gtValue), fieldValue);
                }

                continue;
            }
            // 小于 处理
            Lt lt = AnnotationUtil.getAnnotation(field, Lt.class);
            if (lt != null) {
                String ltValue = lt.value();
                if (StrUtil.isEmpty(ltValue)) {
                    throw new RuntimeException("你必须指定比较字段的名称");
                }
                if (lt.eq()) {
                    ((AbstractWrapper) queryWrapper).le( StringTool.humpToLine(ltValue), fieldValue);

                }else {
                    ((AbstractWrapper) queryWrapper).lt( StringTool.humpToLine(ltValue), fieldValue);
                }
                continue;
            }

            if (fieldValue != null && fieldValue instanceof Boolean && ((Boolean) fieldValue)) {
                // QueryNull 处理
                QueryNull queryNull = AnnotationUtil.getAnnotation(field, QueryNull.class);
                if(queryNull != null){
                    String queryNullValue = queryNull.value();
                    if (queryNull.nulls()) {
                        ((AbstractWrapper) queryWrapper).isNull(StringTool.humpToLine(queryNullValue));
                    }else if (queryNull.empty()) {
                        ((AbstractWrapper) queryWrapper).eq(StringTool.humpToLine(queryNullValue),"");
                    }else if (queryNull.nullsOrEmpty()) {
                        ((AbstractWrapper) queryWrapper).nested(wq -> {
                            ((AbstractWrapper) wq).isNull(StringTool.humpToLine(queryNullValue));
                            ((AbstractWrapper) ((AbstractWrapper) queryWrapper).or()).eq(StringTool.humpToLine(queryNullValue), "");
                        });
                    }
                    setObjectValueNull(queryWrapper.getEntity(), queryNullValue);

                    continue;
                }
            }


            Or or = AnnotationUtil.getAnnotation(field, Or.class);
            if (or != null) {
                Object finalFieldValue = fieldValue;
                ((AbstractWrapper) ((AbstractWrapper) queryWrapper).or()).eq(StringTool.humpToLine(field.getName()), finalFieldValue);
                setObjectValueNull(queryWrapper.getEntity(), field.getName());
                continue;
            }

        }// end for

        if (!orderByMap.isEmpty()) {
            orderByMap.entrySet().stream().sorted(Comparator.comparingInt(e -> e.getValue().order()))
                    .forEach((entry)->{
                        ((AbstractWrapper) queryWrapper).orderBy(true, entry.getValue().asc(), entry.getKey());
                    });
        }
    }


    /**
     * 设置对象值为空
     * @param obj
     * @param fieldName
     */
    default void setObjectValueNull(Object obj,String fieldName){
        if (obj != null && ReflectUtil.hasField(obj.getClass(),fieldName)) {
            try {
                ReflectUtil.setFieldValue(obj,fieldName,null);
            }catch (Exception e){

                LoggerFactory.getLogger(IBaseService.class).warn(e.getMessage() + "你利用了查询参数注解，在将原始值设置为空时出错了，但这也许不影响运行的正确性",e);
            }
        }
    }
    /****************************** 扩展辅助功能 结束*************************************************/


    /************************** 以下为树操作 开始*******************************************/

    /**
     * 查询第一级
     * @return
     */
    default List<DO> getRoot(){
        return list(Wrappers.<DO>query().eq(TreeDO.COLUMN_LEVEL, TreeDO.INIT_LEVEL).isNull(TreeDO.COLUMN_PARENT_ID));
    }
    /**
     * 查询第一级
     * @return
     */
    default List<DO> getRoot(DO query){
        return list(Wrappers.<DO>query(query).eq(TreeDO.COLUMN_LEVEL, TreeDO.INIT_LEVEL).isNull(TreeDO.COLUMN_PARENT_ID));
    }

    /**
     * 查询第一级并且等于该id
     * @return
     */
    default DO getRoot(Long id){
        Assert.notNull(id, "id 不能为空");
        return getOne(Wrappers.<DO>query().eq(TreeDO.COLUMN_LEVEL, TreeDO.INIT_LEVEL).isNull(TreeDO.COLUMN_PARENT_ID).eq(TreeDO.COLUMN_ID,id));

    }
    /**
     * 查询子一级节点
     * @param parentId
     * @return
     */
    default List<DO> getChildren(Long parentId){
        Assert.notNull(parentId,"parentId 不能为空");
        return list(Wrappers.<DO>query().eq(TreeDO.COLUMN_PARENT_ID,parentId));
    }
    /**
     * 查询子一级节点
     * @param parentId
     * @return
     */
    default List<DO> getChildren(Long parentId,Boolean isAsc, String column){
        Assert.notNull(parentId,"parentId 不能为空");
        return list(Wrappers.<DO>query().eq(TreeDO.COLUMN_PARENT_ID,parentId).orderBy(column != null, isAsc, column));
    }
    /**
     * 查询子一级节点
     * @param parentId
     * @param query
     * @return
     */
    default List<DO> getChildren(Long parentId, DO query){
        Assert.notNull(parentId,"parentId 不能为空");
        return list(Wrappers.<DO>query(query).eq(TreeDO.COLUMN_PARENT_ID,parentId));
    }

    /**
     * 查询子一级节点,是否有子节点
     * @param parentId
     * @return
     */
    default boolean hasChildren(Long parentId){
        long count = getChildrenCount(parentId);
        return count > 0;
    }

    /**
     * 获取所有的子孙节点
     * @param parentId
     * @return
     */
    default List<DO> getAllChildren(Long parentId){
        if (parentId == null) {
            return null;
        }
        DO parent = getById(parentId);
        if (parent == null) {
            return null;
        }
        Map<String,Object> p = new HashMap<>(1);
        p.put(TreeDO.COLUMN_PARENT_ID + ((BaseTreeDO)parent).getLevel(),parentId);
        return listByMap(p);
    }

    /**
     * 获取子一级节点的数目
     * @param parentId
     * @return
     */
    default long getChildrenCount(Long parentId){

        Assert.hasText("parentId","parentId 不能为空");
        Map<String,Object> p = new HashMap<>(1);
        p.put(TreeDO.COLUMN_PARENT_ID,parentId);
        return count(Wrappers.<DO>query().eq(TreeDO.COLUMN_PARENT_ID,parentId));
    }
    /**
     * 查询父级
     * @param id
     * @return
     */
    default DO getParent(Long id){
        if (id == null) {
            return null;
        }
        DO DO = getById(id);
        if (DO != null) {
            return getById(((BaseTreeDO) DO).getParentId());
        }
        return null;
    }

    /**
     * 获取所有父级
     * @param id
     * @return
     */
    default List<DO> getAllParents(Long id){
        if (id == null) {
            return null;
        }
        BaseTreeDO dbDO = (BaseTreeDO) getParent(id);
        if (dbDO == null) {
            return null;
        }
        List<Long> parentIds = new ArrayList<>(BaseTreeDO.MAX_LEVEL - 1);
        if (dbDO.getParentId1() != null) {
            parentIds.add(dbDO.getParentId1());
        }
        if (dbDO.getParentId2() != null) {
            parentIds.add(dbDO.getParentId2());
        }
        if (dbDO.getParentId3() != null) {
            parentIds.add(dbDO.getParentId3());
        }
        if (dbDO.getParentId4() != null) {
            parentIds.add(dbDO.getParentId4());
        }
        if (dbDO.getParentId5() != null) {
            parentIds.add(dbDO.getParentId5());
        }
        if (dbDO.getParentId6() != null) {
            parentIds.add(dbDO.getParentId6());
        }
        if (dbDO.getParentId7() != null) {
            parentIds.add(dbDO.getParentId7());
        }
        if (dbDO.getParentId8() != null) {
            parentIds.add(dbDO.getParentId8());
        }
        if (dbDO.getParentId9() != null) {
            parentIds.add(dbDO.getParentId9());
        }
        if (dbDO.getParentId10() != null) {
            parentIds.add(dbDO.getParentId10());
        }
        return list(Wrappers.<DO>query().in(BaseDO.COLUMN_ID,parentIds).orderByAsc(TreeDO.COLUMN_LEVEL));
    }

    /**
     * 根据ID查询所有父级包括自己
     * @param id
     * @return
     */
    default List<DO> getAllParentsAndSelf(Long id){
        if (id == null) {
            return null;
        }
        BaseTreeDO dbDO = (BaseTreeDO) getById(id);
        if (dbDO == null) {
            return null;
        }
        List<Long> parentIds = new ArrayList<>(BaseTreeDO.MAX_LEVEL - 1);
        if (dbDO.getParentId1() != null) {
            parentIds.add(dbDO.getParentId1());
        }
        if (dbDO.getParentId2() != null) {
            parentIds.add(dbDO.getParentId2());
        }
        if (dbDO.getParentId3() != null) {
            parentIds.add(dbDO.getParentId3());
        }
        if (dbDO.getParentId4() != null) {
            parentIds.add(dbDO.getParentId4());
        }
        if (dbDO.getParentId5() != null) {
            parentIds.add(dbDO.getParentId5());
        }
        if (dbDO.getParentId6() != null) {
            parentIds.add(dbDO.getParentId6());
        }
        if (dbDO.getParentId7() != null) {
            parentIds.add(dbDO.getParentId7());
        }
        if (dbDO.getParentId8() != null) {
            parentIds.add(dbDO.getParentId8());
        }
        if (dbDO.getParentId9() != null) {
            parentIds.add(dbDO.getParentId9());
        }
        if (dbDO.getParentId10() != null) {
            parentIds.add(dbDO.getParentId10());
        }
        parentIds.add(id);
        return list(Wrappers.<DO>query().in(BaseDO.COLUMN_ID,parentIds).gt(TreeDO.COLUMN_LEVEL,1).orderByAsc(TreeDO.COLUMN_LEVEL));
    }

    /**
     * 检查树结构是否完整，该检查一旦检查到异常数据就停止，而不会汇总出所有数据的异常，因为父级有异常子级就不保证正确了，所以先处理了再检查
     * @param parent 对parent本身没有做检查，启用必须传null对全量做检查，如果不传null请确保parent本身的正确
     */
    default void checkTreeStruct(BaseTreeDO parent) {

        int level = parent == null? BaseTreeDO.INIT_LEVEL:parent.getLevel() + 1;
        Map<String, Long> parentIdx = new HashMap<>(10);
        for (int i = 1; i < BaseTreeDO.MAX_LEVEL; i++) {
            String fieldName = TreeDO.PROPERTY_PARENT_ID + i;
            Object fieldValueObj = ReflectUtil.getFieldValue(parent, fieldName);
            Long fieldValue = fieldValueObj == null ? null : ((Long) fieldValueObj);
            parentIdx.put(fieldName, parent == null ? null : fieldValue);
        }
        if (parent != null) {
            String fieldName = TreeDO.PROPERTY_PARENT_ID + parent.getLevel();
            parentIdx.put(fieldName, parent.getId());
        }
        List<BaseTreeDO> children = null;
        if (parent == null) {
            children = (List<BaseTreeDO>) getRoot();
        }else {
            children = (List<BaseTreeDO>) getChildren(parent.getId());
        }
        if (!isEmpty(children)) {
            for (BaseTreeDO po : children) {
                if(po.getLevel() != level){
                    throw new IllegalArgumentException("id=" + po.getId() + " level应该为"+ level +"实际为" + po.getLevel());
                }
                for (int i = 1; i < BaseTreeDO.MAX_LEVEL; i++) {
                    String fieldName = TreeDO.PROPERTY_PARENT_ID + i;
                    Object fieldValueObj = ReflectUtil.getFieldValue(po, fieldName);
                    Long fieldValue = fieldValueObj == null ? null : ((Long) fieldValueObj);
                    if( !Objects.equals(parentIdx.get(fieldName),fieldValue)){
                        throw new IllegalArgumentException("id=" + po.getId() + " "+ (fieldName) +"应该为"+ parentIdx.get(fieldName) +"实际为" + fieldValue);
                    }
                }
                checkTreeStruct(po);
            }
        }

    }
    /**
     * 根据id和父id查询
     * @param id
     * @param parentId
     * @return
     */
    default DO getByIdAndParentId(Long id, Long parentId){
        if (id == null || parentId == null) {
            throw new IllegalArgumentException("id 和 parentId" + "都不能为空");
        }
        return getOne(Wrappers.<DO>query().eq(TreeDO.COLUMN_PARENT_ID, parentId).eq(TreeDO.COLUMN_ID, id));
    }

    /**
     * 根据父节点，设置子节点parentIdx的值包括parentId本身
     * @param child
     * @param parentId
     * @return
     */
    default DO initParentIdXByParent(DO child, Long parentId){
        if (parentId == null) {
            return TreeServiceHelperTool.initParentIdXByParent(child, null);
        }
        DO parent = (DO) getById(parentId);
        return TreeServiceHelperTool.initParentIdXByParent(child, parent);
    }

    /**
     * 根据父节点，设置子节点parentIdx的值包括parentId本身
     * @param child
     * @param parent
     * @return
     */


    /**
     * 添加子节点
     * @param entity
     * @param parentId
     * @return
     */
    default boolean insertChild(DO entity, Long parentId){
        entity = initParentIdXByParent(entity, parentId);

        return save(entity);
    }

    /**
     * 将id节点移动到parentId下，包括所有子节点
     * @param id
     * @param parentId
     * @return  返回涉及到的数据条数
     */
    default int moveNode(Long id, Long parentId){

        int result = 0;
        Assert.notNull(id,"移动的节点id不能为空");
        DO entity = getById(id);
        Assert.notNull(entity,"移动的节点不存在");
        if (Objects.equals(id,parentId)) {
            throw new IllegalArgumentException("不能将节点本身做为父节点");
        }
        entity = initParentIdXByParent(entity, parentId);
        // 更新开始 主要是把parentIdX可以更新为null
        UpdateWrapper<DO> update = Wrappers.update();
        update.set(TreeDO.COLUMN_PARENT_ID, ((BaseTreeDO) entity).getParentId());
        update.eq(BaseDO.COLUMN_ID, ((BaseTreeDO) entity).getId());
        String propertyParentIdx = null;
        String columnParentIdx = null;
        for (int i = 1; i < BaseTreeDO.MAX_LEVEL; i++) {
            columnParentIdx = TreeDO.COLUMN_PARENT_ID + i;
            propertyParentIdx = TreeDO.PROPERTY_PARENT_ID + i;
            update.set(columnParentIdx, ReflectUtil.getFieldValue(entity,propertyParentIdx));
        }
        boolean entityUpdateResult = update(entity,update);
        // 更新结束
        if(entityUpdateResult){
            result++;
        }
        List<DO> children = getChildren(id);
        if (!isEmpty(children)) {
            for (DO child : children) {
                result += moveNode(((BaseTreeDO) child).getId(), id);
            }
        }
        return result;
    }
    /************************** 以下为树操作 结束*******************************************/

    /******************* 关系表相关 开始 ****************************************/


    /**
     * 关系删除，仅限懒加载时使用
     *
     * @param mainId           主id，如：如果是角色分配功能，则为角色id
     * @param checkedOtherIds  已选择的被分配的id
     * @param uncheckeOtherIds 未选择的被分配的id
     */
    default boolean removeAssignRelLazy(Long mainId, List<Long> checkedOtherIds, List<Long> uncheckeOtherIds, SFunction<DO, ?> main, SFunction<DO, ?> other, Consumer<LambdaQueryWrapper<DO>> addtionalCondition) {
        return removeAssignRel(mainId, checkedOtherIds, null, true, main, other,addtionalCondition);
    }
    /**
     * 关系删除
     *
     * @param mainId           主id，如：如果是角色分配功能，则为角色id
     */
    default boolean removeAssignRel(Long mainId,  SFunction<DO, ?> main, Consumer<LambdaQueryWrapper<DO>> addtionalCondition) {
        return removeAssignRel(mainId, null, null, false, main, null,addtionalCondition);
    }
    /**
     * 关系处理，删除取消分配的数据
     * @param mainId 主id，如：如果是角色分配功能，则为角色id
     * @param checkedIds 已选择的被分配的id
     * @param uncheckeIds 未选择的被分配的id
     * @param isLazyLoad 标识页面的可选择数据是否为懒加载，如果不是懒加载会清空主id已绑定的所有数据
     * @return  返回真正需要checkedIds数据
     */
    default List<Long> removeAssignRelWithReturn(Long mainId, List<Long> checkedIds, List<Long> uncheckeIds, Boolean isLazyLoad, SFunction<DO,?> main, SFunction<DO,?> other, Consumer<LambdaQueryWrapper<DO>> addtionalCondition){

        Assert.notNull(mainId, "mainId 不能为空");
        Assert.notNull(main, "main 不能为空");
        List<Long> result = new ArrayList<>();
        boolean isLazy = (isLazyLoad != null && isLazyLoad);

        if (isLazy) {
            if (!isEmpty(uncheckeIds)) {
                Assert.notNull(other, "other不能为空");
                LambdaQueryWrapper<DO> lambdaQueryWrapper = Wrappers.<DO>lambdaQuery().eq(main, mainId).in(other, uncheckeIds);
                if (addtionalCondition != null) {
                    addtionalCondition.accept(lambdaQueryWrapper);
                }
                remove(lambdaQueryWrapper);
            }
        }else {
            // 数据不是懒加载，且没有选中数据，则全部删除关系
            if (isEmpty(checkedIds)) {
                LambdaQueryWrapper<DO> lambdaQueryWrapper = Wrappers.<DO>lambdaQuery().eq(main, mainId);
                if (addtionalCondition != null) {
                    addtionalCondition.accept(lambdaQueryWrapper);
                }
                remove(lambdaQueryWrapper);
            }else {
                Assert.notNull(other, "other不能为空");
                LambdaQueryWrapper<DO> lambdaQueryWrapper = Wrappers.<DO>lambdaQuery().eq(main, mainId).notIn(other, checkedIds);
                if (addtionalCondition != null) {
                    addtionalCondition.accept(lambdaQueryWrapper);
                }
                remove(lambdaQueryWrapper);
            }
        }
        // 上面已经把不需要的已经删除完成
        // 查询数据库里还剩下的已选中的
        if (!isEmpty(checkedIds)) {
            Assert.notNull(other, "other不能为空");
            LambdaQueryWrapper<DO> lambdaQueryWrapper = Wrappers.<DO>lambdaQuery().eq(main, mainId).in(other, checkedIds);
            if (addtionalCondition != null) {
                addtionalCondition.accept(lambdaQueryWrapper);
            }
            List<DO> listDb = list(lambdaQueryWrapper);
            // 已经选中的，除去数据库里已经存在的，是真正要添加的
            List<Long> newRealCheckedIds = new ArrayList<>();
            List<?> checkedIdsInDb = listDb.stream().map(other).collect(Collectors.toList());
            for (Long checkedId : checkedIds) {
                if (!checkedIdsInDb.contains(checkedId)) {
                    newRealCheckedIds.add(checkedId);
                }
            }
            result = newRealCheckedIds;
        }

        return result;
    }


        /**
		 * 关系处理，删除取消分配的数据
		 * @param mainId 主id，如：如果是角色分配功能，则为角色id
		 * @param checkedIds 已选择的被分配的id
		 * @param uncheckeIds 未选择的被分配的id
		 * @param isLazyLoad 标识页面的可选择数据是否为懒加载，如果不是懒加载会清空主id已绑定的所有数据
		 */
    default boolean removeAssignRel(Long mainId, List<Long> checkedIds, List<Long> uncheckeIds, Boolean isLazyLoad, SFunction<DO,?> main, SFunction<DO,?> other, Consumer<LambdaQueryWrapper<DO>> addtionalCondition){

        List<Long> strings = removeAssignRelWithReturn(mainId, checkedIds, uncheckeIds, isLazyLoad, main, other,addtionalCondition);
        // 这里直接返回 true 因为上面的返回值不代表没有成功
        return true;
    }

    /**
     * 分配支持懒加载
     * @param mainId
     * @param checkedIds
     * @param uncheckeIds
     * @param isLazyLoad
     * @param main
     * @param other
     * @param mapper
     */
    default boolean removeAndAssignRel(Long mainId, List<Long> checkedIds, List<Long> uncheckeIds, Boolean isLazyLoad,
                                       SFunction<DO,?> main, SFunction<DO,?> other,
                                       Function<RelDTO, ? extends DO> mapper){
        List<Long> realCheckedIds = removeAssignRelWithReturn(mainId, checkedIds, uncheckeIds, isLazyLoad, main, other,null);
        return assignRel(mainId, realCheckedIds, mapper);
    }
    /**
     * 分配支持懒加载,并支持额外条件
     * @param mainId
     * @param checkedIds
     * @param uncheckeIds
     * @param isLazyLoad
     * @param main
     * @param other
     * @param mapper
     */
    default boolean removeAndAssignRel(Long mainId, List<Long> checkedIds, List<Long> uncheckeIds, Boolean isLazyLoad,
                                       SFunction<DO,?> main, SFunction<DO,?> other,
                                       Function<RelDTO, ? extends DO> mapper, Consumer<LambdaQueryWrapper<DO>> addtionalCondition){
        List<Long> realCheckedIds = removeAssignRelWithReturn(mainId, checkedIds, uncheckeIds, isLazyLoad, main, other,addtionalCondition);
        return assignRel(mainId, realCheckedIds, mapper);
    }
    /**
     * 添加已选数据
     * @param mainId
     * @param checkedIds
     * @param mapper
     */
    default boolean assignRel(Long mainId, List<Long> checkedIds, Function<RelDTO, ? extends DO> mapper){
        if (!isEmpty(checkedIds)) {
            return saveBatch(checkedIds.stream().map(checkedId-> mapper.apply(new RelDTO(mainId,checkedId))).collect(Collectors.toList()));
        }
        return false;
    }

    /**
     * 删除并绑定数据
     * @param mainId
     * @param checkedIds
     * @param mapper
     */
    default boolean removeAndAssignRel(Long mainId, List<Long> checkedIds, SFunction<DO, ?> main, Function<RelDTO, ? extends DO> mapper){
        removeAssignRel(mainId, main,null);
        return assignRel(mainId, checkedIds, mapper);
    }
    /**
     * 删除并绑定数据，并支持额外条件
     * @param mainId
     * @param checkedIds
     * @param mapper
     */
    default boolean removeAndAssignRel(Long mainId, List<Long> checkedIds, SFunction<DO, ?> main, Function<RelDTO, ? extends DO> mapper, Consumer<LambdaQueryWrapper<DO>> addtionalCondition){
        removeAssignRel(mainId, main,addtionalCondition);
        return assignRel(mainId, checkedIds, mapper);
    }
    /******************* 关系表相关 结束 ****************************************/

}
