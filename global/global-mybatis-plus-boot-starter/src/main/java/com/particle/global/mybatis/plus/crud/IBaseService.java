package com.particle.global.mybatis.plus.crud;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.Update;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.LambdaMeta;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.particle.global.dto.basic.PageQueryCommand;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.basic.TreeDO;
import com.particle.global.mybatis.plus.anno.*;
import com.particle.global.mybatis.plus.dto.BaseDO;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import com.particle.global.mybatis.plus.dto.RelDTO;
import com.particle.global.tool.str.StringTool;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
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

    /*************************** 增 结束 *************************************/
    /*************************** 删 开始 *************************************/

    /**
     * 删除
     * @param id
     * @return
     */
    boolean deleteById(Long id);

    /**
     * 根据属性字段删除
     * @param columnId
     * @param column
     * @return
     */
    boolean deleteByColumn(Object columnId,  SFunction<DO, ?> column);

    /*************************** 删 结束 *************************************/
    /*************************** 改 开始 *************************************/

    DO update(DO dos);
    /**
     * 根据主键增加一个字段的值，
     * @param id
     * @param column
     * @param num
     * @return
     */
    default boolean plusForColumnById(Long id, SFunction<DO, ?> column, Object num){
        Assert.notNull(column,"column 不能为空");
        Assert.notNull(id,"columnId 不能为空");

        String columnName  = SFunctionHelperTool.columnToString(column);
        String sql = StrUtil.format(" {} = {} + {} ",columnName,columnName,num);
        return update(Wrappers.<DO>update().setSql(sql).eq(com.particle.global.dto.basic.DO.COLUMN_ID,id));
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
    DO queryById(Long id);

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
     * @param columnId
     * @param column DO::getId
     * @return
     */
    default boolean existByColumn(Long columnId, SFunction<DO, ?> column) {
        Assert.notNull(column,"column 不能为空");
        Assert.notNull(columnId,"columnId 不能为空");
        return count( Wrappers.<DO>query().lambda().eq(column,columnId)) > 0;
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
        Page pageQuery = new Page((pageQueryForm).getCurrent(), (pageQueryForm).getSize());
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
     * @param columnId
     * @param column
     * @param exist true=断言存在，false=断言不存在
     */
    default void assertByColumn(Long columnId, SFunction<DO, ?> column, boolean exist) {
        Assert.notNull(column,"column 不能为空");
        Assert.notNull(columnId,"columnId 不能为空");
        boolean existByColumn = existByColumn(columnId, column);
        if(exist == existByColumn){
            return;
        }
        String realDbColumn = SFunctionHelperTool.columnToString(column);
        // 获取swagger注解
        LambdaMeta resolve = LambdaUtils.extract(column);
        String propertyName = StringTool.lineToHump(realDbColumn);
        String columnLabel = propertyName;
        Field field = ReflectUtil.getField(resolve.getInstantiatedClass(), propertyName);
        if (field != null) {
            ApiModelProperty apiModelProperty = AnnotationUtil.getAnnotation(field, ApiModelProperty.class);
            if (apiModelProperty != null && StrUtil.isNotEmpty(apiModelProperty.value())) {
                // 取第一个逗号逗号分隔的字符作为提示标签
                columnLabel = apiModelProperty.value().split(",")[0].split("，")[0];
            }
        }
        if (exist) {
            Assert.isTrue(existByColumn,columnLabel + " " + columnId + " " +  "不存在");
        }else {
            Assert.isTrue(!existByColumn,columnLabel + " " + columnId + " " +  "已存在");
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
        if (QueryCommand.class.isAssignableFrom(query.getClass())) {


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

                (((AbstractWrapper) queryWrapper)).in(StringTool.humpToLine(field.getName()), ((Collection) finalFieldValue));
                setObjectValueNull(queryWrapper.getEntity(), field.getName());
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
                ((AbstractWrapper) queryWrapper).like( StringTool.humpToLine(field.getName()), fieldValue);
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
        if (obj != null) {
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
     * 根据风节点，设置子节点parentIdx的值包括parentId本身
     * @param child
     * @param parentId
     * @return
     */
    default DO initParentIdXByParent(DO child, Long parentId){
        if (parentId == null) {
            return child;
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
    default boolean removeAssignRelLazy(Long mainId, List<Long> checkedOtherIds, List<Long> uncheckeOtherIds, SFunction<DO, ?> main, SFunction<DO, ?> other) {
        return removeAssignRel(mainId, checkedOtherIds, null, true, main, other);
    }
    /**
     * 关系删除
     *
     * @param mainId           主id，如：如果是角色分配功能，则为角色id
     */
    default boolean removeAssignRel(Long mainId,  SFunction<DO, ?> main) {
        return removeAssignRel(mainId, null, null, false, main, null);
    }
    /**
     * 关系处理，删除取消分配的数据
     * @param mainId 主id，如：如果是角色分配功能，则为角色id
     * @param checkedIds 已选择的被分配的id
     * @param uncheckeIds 未选择的被分配的id
     * @param isLazyLoad 标识页面的可选择数据是否为懒加载，如果不是懒加载会清空主id已绑定的所有数据
     * @return  返回真正需要checkedIds数据
     */
    default List<Long> removeAssignRelWithReturn(Long mainId, List<Long> checkedIds, List<Long> uncheckeIds, Boolean isLazyLoad, SFunction<DO,?> main, SFunction<DO,?> other){

        Assert.notNull(mainId, "mainId 不能为空");
        Assert.notNull(main, "main 不能为空");
        List<Long> result = new ArrayList<>();
        boolean isLazy = (isLazyLoad != null && isLazyLoad);

        if (isLazy) {
            if (!isEmpty(uncheckeIds)) {
                Assert.notNull(other, "other不能为空");
                remove(Wrappers.<DO>lambdaQuery().eq(main,mainId).in(other,uncheckeIds));
            }
        }else {
            // 数据不是懒加载，且没有选中数据，则全部删除关系
            if (isEmpty(checkedIds)) {
                remove(Wrappers.<DO>lambdaQuery().eq(main,mainId));
            }else {
                Assert.notNull(other, "other不能为空");
                remove(Wrappers.<DO>lambdaQuery().eq(main,mainId).notIn(other,checkedIds));
            }
        }
        // 上面已经把不需要的已经删除完成
        // 查询数据库里还剩下的已选中的
        if (!isEmpty(checkedIds)) {
            Assert.notNull(other, "other不能为空");
            List<DO> listDb = list(Wrappers.<DO>lambdaQuery().eq(main, mainId).in(other, checkedIds));
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
    default boolean removeAssignRel(Long mainId, List<Long> checkedIds, List<Long> uncheckeIds, Boolean isLazyLoad, SFunction<DO,?> main, SFunction<DO,?> other){

        List<Long> strings = removeAssignRelWithReturn(mainId, checkedIds, uncheckeIds, isLazyLoad, main, other);
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
    default boolean removeAndAssignRel(Long mainId, List<Long> checkedIds, List<Long> uncheckeIds, Boolean isLazyLoad, SFunction<DO,?> main, SFunction<DO,?> other, Function<RelDTO, ? extends DO> mapper){
        List<Long> realCheckedIds = removeAssignRelWithReturn(mainId, checkedIds, uncheckeIds, isLazyLoad, main, other);
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
            List<DO> insert = new ArrayList<>(checkedIds.size());
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
        removeAssignRel(mainId, main);
        return assignRel(mainId, checkedIds, mapper);
    }

    /******************* 关系表相关 结束 ****************************************/

}
