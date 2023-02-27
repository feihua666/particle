package com.particle.global.mybatis.plus.crud;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.ReflectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.particle.global.data.permission.DataPermissionService;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.dto.basic.TreeDO;
import com.particle.global.exception.ExceptionFactory;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.global.mybatis.plus.dto.BaseDO;
import com.particle.global.mybatis.plus.dto.BaseTreeDO;
import com.particle.global.mybatis.plus.wrapper.DataPermissionServiceWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.*;
import java.util.function.Function;

import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * 基础能用服务类，提供最常用的服务逻辑
 * @author yangwei
 * @since 2020/10/27 15:49
 */
@Slf4j()
public class IBaseServiceImpl<Mapper extends IBaseMapper<DO>, DO extends BaseDO> extends ServiceImpl<Mapper, DO> implements IBaseService<DO> {

    protected DataPermissionService dataPermissionService;

    protected List<IDeleteServiceListener<DO>> deleteServiceListeners;

    protected List<IAddServiceListener<DO>> addServiceListeners;

    protected List<IUpdateServiceListener<DO>> updateServiceListeners;

    @Autowired(required = false)
    private DataPermissionServiceWrapper dataPermissionServiceWrapper;

    /**
     * 添加前
     * @param po
     */
    protected void preAdd(DO po){}

    /**
     * 添加前
     * @param po
     * @param options 预留字段
     */
    protected void preAdd(DO po,Object options){
        // 树相关
        if (po instanceof BaseTreeDO) {
            initParentIdXByParent(po, ((BaseTreeDO) po).getParentId());
        }
        preAdd(po);
        if (!isEmpty(addServiceListeners)) {
            addServiceListeners.parallelStream().forEach(listener->listener.preAdd(po));
        }
    }

    /**
     * 添加后
     * @param po
     * @param po
     */
    protected void postAdd(DO po){}

    /**
     * 添加后
     * @param po
     * @param options
     */
    private void postAdd(DO po,Object options){
        postAdd(po);
        if (!isEmpty(addServiceListeners)) {
            addServiceListeners.parallelStream().forEach(listener->listener.postAdd(po));
        }
    }
    @Override
    public DO add(DO po) {
        preAdd(po,null);
        boolean r =  save(po);
        if(r){
            postAdd(po,null);
            return po;
        }else {
            return null;
        }
    }

    @Override
    public DO copy(Long id, Function<DO, DO> doHandle) {
        DO byId = getById(id);

        return copy(byId,doHandle,false);
    }

    @Override
    public DO copyAndCopyChildren(Long id, Function<DO, DO> doHandle, Boolean isIncludeAllChildren) {
        DO byId = getById(id);

        return copy(byId,doHandle,isIncludeAllChildren);
    }

    /**
     *
     * @param dos
     * @param doHandle
     * @return
     */
    protected DO copy(DO dos, Function<DO, DO> doHandle, Boolean isIncludeAllChildren){
        if (dos == null) {
            return null;
        }
        Long id = dos.getId();
        DO handledDO = doHandle.apply(dos);
        if (handledDO == null) {
            return null;
        }
        ServiceHelperTool.emptyBaseDOFields(handledDO);

        handledDO = add(handledDO);

        if(BooleanUtil.isTrue(isIncludeAllChildren) && dos instanceof BaseTreeDO){
            List<DO> children = getChildren(id);
            if (CollectionUtil.isNotEmpty(children)) {
                for (DO child : children) {
                    DO finalHandledDO = handledDO;
                    copy(child, (handleDO)->{
                        DO r = doHandle.apply(handleDO);
                        if (r instanceof BaseTreeDO) {
                            ((BaseTreeDO) r).setParentId(finalHandledDO.getId());
                        }
                        return r;
                    }, isIncludeAllChildren);
                }
            }
        }

        return handledDO;
    }

    @Override
    public DO queryById(Long id) {
        Assert.notNull(id,"id 不能为空");
        QueryWrapper<DO> queryWrapper = Wrappers.<DO>query().eq(BaseDO.COLUMN_ID, id);


        if (dataPermissionServiceWrapper !=null && getQueryDataPermissionService() != null) {
            getQueryDataPermissionService().dataConstraint(queryWrapper);
        }
        DO dbDO = getOne(queryWrapper,true);
        return dbDO;
    }

    @Override
    public QueryWrapper<DO> getQueryWrapper(QueryCommand queryForm)  {
        DO po = queryCommandToDO(queryForm);
        if (queryForm != null && po == null) {
            log.error("DO is null,you should override method queryCommandToDO to return a DO object. for {}",getClass().getName());
        }
        QueryWrapper<DO> queryWrapper = Wrappers.query(po);

        annotationSupportQueryWrapper(queryWrapper, queryForm);

        if(po instanceof BaseTreeDO && ((BaseTreeDO) po).getParentId() != null){
            boolean isIncludeParent = ReflectUtil.hasField(queryForm.getClass(), "isIncludeParent");
            if (isIncludeParent) {
                Object isIncludeParentValue = ReflectUtil.getFieldValue(queryForm, "isIncludeParent");
                if (BooleanUtil.isTrue(((Boolean) isIncludeParentValue))) {
                    queryWrapper.or(i -> i.eq(TreeDO.COLUMN_ID , ((BaseTreeDO) po).getParentId()));
                }
            }
            boolean isIncludeAllChildren = ReflectUtil.hasField(queryForm.getClass(), "isIncludeAllChildren");
            if (isIncludeAllChildren) {
                Object isIncludeAllChildrenValue = ReflectUtil.getFieldValue(queryForm, "isIncludeAllChildren");
                if (BooleanUtil.isTrue(((Boolean) isIncludeAllChildrenValue))) {
                    DO parent = getById(((BaseTreeDO) po).getParentId());
                    if (parent != null) {
                        queryWrapper.or(i -> i.eq(TreeDO.COLUMN_PARENT_ID + ((BaseTreeDO)parent).getLevel(), ((BaseTreeDO) po).getParentId()));
                    }
                }
            }
        }

        // 数据范围约束
        if (dataPermissionServiceWrapper !=null && getQueryDataPermissionService() != null) {
            getQueryDataPermissionService().dataConstraint(queryWrapper);
        }

        queryWrapper.orderByAsc(BaseDO.COLUMN_ID);
        return queryWrapper;
    }

    /**
     * 将查询指令转为DO
     * @return
     */
    protected DO queryCommandToDO(QueryCommand queryCommand){
        return null;
    }
    /**
     * 根据id删除前
     * @param id
     */
    protected void preDeleteById(Long id, DO DO){}

    /**
     * 删除前调用
     * @param id
     * @param DO
     * @param options
     */
    private void preDeleteById(Long id, DO DO, Object options){
        preDeleteById(id, DO);
        if (!isEmpty(deleteServiceListeners)) {
            deleteServiceListeners.parallelStream().forEach(listener->listener.preDeleteById(id, DO));
        }
    }

    /**
     * 根据id删除后
     * @param id
     */
    protected void postDeleteById(Long id, DO DO){}

    /**
     * 删除后调用
     * @param id
     * @param DO
     * @param options
     */
    private void postDeleteById(Long id, DO DO, Object options){
        postDeleteById(id, DO);
        if (!isEmpty(deleteServiceListeners)) {
            deleteServiceListeners.parallelStream().forEach(listener->listener.postDeleteById(id, DO));
        }
    }

    @Override
    public boolean deleteById(Long id) {
        Assert.notNull(id,"id 不能为空");

        DO byId = getById(id);
        // 如果为树，删除时不能删除还有子级的数据
        if (byId instanceof BaseTreeDO) {
            long childrenCount = getChildrenCount(id);
            Assert.isTrue(childrenCount == 0,"当前节点下还有子节点，不允许删除父节点");
        }
        preDeleteById(id,byId,null);
        QueryWrapper<DO> queryWrapper = Wrappers.<DO>query().eq(BaseDO.COLUMN_ID, id);

        if (dataPermissionServiceWrapper !=null && getDeleteDataPermissionService() != null) {
            getDeleteDataPermissionService().dataConstraint(queryWrapper);
        }
        boolean r = remove(queryWrapper);
        if(r){
            postDeleteById(id,byId,null);
        }
        return r;
    }
    /**
     * 根据id删除前
     * @param columnId
     */
    protected void preDeleteByColumn(Object columnId , SFunction<DO, ?> column, List<DO> DOS){}

    /**
     * 根据列删除前调用
     * @param columnId
     * @param column
     * @param DOS
     * @param options
     */
    private void preDeleteByColumn(Object columnId, SFunction<DO, ?> column , List<DO> DOS, Object options){
        preDeleteByColumn(columnId,column, DOS);
        if (!isEmpty(deleteServiceListeners)) {
            deleteServiceListeners.parallelStream().forEach(listener->listener.preDeleteByColumn(columnId,column, DOS));
        }
    }

    /**
     * 根据id删除后
     * @param columnId
     */
    protected void postDeleteByColumn(Object columnId , SFunction<DO, ?> column , List<DO> DOS){}

    /**
     * 根据列删除后调用
     * @param columnId
     * @param column
     * @param DOS
     * @param options
     */
    private void postDeleteByColumn(Object columnId , SFunction<DO, ?> column , List<DO> DOS, Object options){
        postDeleteByColumn(columnId ,column , DOS);
        if (!isEmpty(deleteServiceListeners)) {
            deleteServiceListeners.parallelStream().forEach(listener->listener.postDeleteByColumn(columnId,column, DOS));
        }
    }
    @Override
    public boolean deleteByColumn(Object columnId, SFunction<DO, ?> column) {
        Assert.notNull(column,"column 不能为空");
        Assert.notNull(columnId,"columnId 不能为空");
        LambdaQueryWrapper<DO> queryWrapper = Wrappers.<DO>lambdaQuery().eq(column, columnId);
        if (dataPermissionServiceWrapper !=null && getDeleteDataPermissionService() != null) {
            getDeleteDataPermissionService().dataConstraint(queryWrapper);
        }
        List<DO> list = list(queryWrapper);
        preDeleteByColumn(columnId,column,list,null);

        boolean r = remove(queryWrapper);
        if(r){
            postDeleteByColumn(columnId,column,list,null);
        }
        return r;
    }
    /**
     * 更新前
     * @param po
     */
    protected void preUpdate(DO po){}
    /**
     * 更新前
     * @param options
     */
    protected void preUpdate(DO po,Object options){
        // 树相关
        if (po instanceof BaseTreeDO) {
            // 判断父级是否修改
            DO poDb = getById(po.getId());
            if (poDb == null) {
                throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.DATA_NOT_FOUND);
            }
            //父级不相等，则有修改父级
            if(!Objects.equals(((BaseTreeDO) poDb).getParentId(), ((BaseTreeDO) po).getParentId())){
                // 判断该节点下是否有子节点，如果有，不允许修改
                long childrenCount = getChildrenCount(((BaseTreeDO) po).getId());

                Assert.isTrue(childrenCount == 0,"当前节点下还有子节点，不允许修改父节点");
            }
            if (((BaseTreeDO) po).getParentId() != null) {
                Assert.isTrue(!Objects.equals(((BaseTreeDO) po).getId(),((BaseTreeDO) po).getParentId()),"不能将父级设置为自己");
                initParentIdXByParent(po, ((BaseTreeDO) po).getParentId());
            }
        }
        preUpdate(po);
        if (!isEmpty(updateServiceListeners)) {
            updateServiceListeners.parallelStream().forEach(listener->listener.preUpdate(po));
        }
    }

    /**
     * 更新后
     * @param po
     */
    protected void postUpdate(DO po){}

    /**
     * 更新后
     * @param po
     * @param options
     */
    private void postUpdate(DO po,Object options){
        postUpdate(po);
        if (!isEmpty(updateServiceListeners)) {
            updateServiceListeners.parallelStream().forEach(listener->listener.postUpdate(po));
        }
    }
    @Override
    public DO update(DO po) {
        Assert.isTrue(ReflectUtil.hasField(po.getClass(), BaseDO.PROPERTY_ID),"updateForm 必须包含主键 " + BaseDO.PROPERTY_ID);
        preUpdate(po,null);
        UpdateWrapper<DO> updateWrapper = Wrappers.update();
        annotationSupportUpdateWrapper(updateWrapper,po instanceof BaseDO ? po.getUpdateControl(): null);
        updateWrapper.eq(BaseDO.COLUMN_ID, ReflectUtil.getFieldValue(po, BaseDO.PROPERTY_ID));
        // 数据范围约束
        if (dataPermissionServiceWrapper !=null && getUpdateDataPermissionService() != null) {
            getUpdateDataPermissionService().dataConstraint(updateWrapper);
        }
        Integer versionOrigin = po.getVersion();
        boolean r = update(po,updateWrapper);
        if (r) {
            postUpdate(po,null);
            return po;
        }else {
            DO dbDo = getById(po.getId());
            if (!Objects.equals(versionOrigin, dbDo.getVersion())) {
                throw ExceptionFactory.bizException(ErrorCodeGlobalEnum.UPDATE_DATA_VERSION_ERROR);
            }
        }
        return po;
    }

    @Override
    public boolean saveOrUpdateBatchByUniqueColumn(Collection<DO> entityList, SFunction<DO, ?> column, int batchSize) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entityClass);
        com.baomidou.mybatisplus.core.toolkit.Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
        String keyProperty = tableInfo.getKeyProperty();
        String columnProperty = SFunctionHelperTool.columnPropertyString(column);
        String columnReal = SFunctionHelperTool.columnToString(column);
        com.baomidou.mybatisplus.core.toolkit.Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
        return SqlHelper.saveOrUpdateBatch(this.entityClass, this.mapperClass, super.log, entityList, batchSize, (sqlSession, entity) -> {

            Object columnVal = ReflectionKit.getFieldValue(entity, columnProperty);

           /* 因为不是主键，这里不用了，直接调用数据库查询，因为使用这个导致数据添加不上
            boolean b = StringUtils.checkValNull(columnVal);
            if (!b) {
                return b;
            }*/
            Map<String, Object> columnMap = new HashMap<>();
            columnMap.put(columnReal,columnVal);
            return CollectionUtils.isEmpty(sqlSession.selectList(getSqlStatement(SqlMethod.SELECT_BY_MAP), columnMap));
        }, (sqlSession, entity) -> {
            Map<String, Object> map = CollectionUtils.newHashMapWithExpectedSize(2);
            map.put(Constants.ENTITY, entity);
            Object columnVal = ReflectionKit.getFieldValue(entity, columnProperty);

            UpdateWrapper<Object> updateWrapper = Wrappers.update().eq(columnReal, columnVal);
            map.put(Constants.WRAPPER, updateWrapper);
            sqlSession.update(getSqlStatement(SqlMethod.UPDATE), map);
        });
    }

    @Override
    public boolean updateBatchByUniqueColumn(Collection<DO> entityList, SFunction<DO, ?> column, int batchSize) {

        String columnProperty = SFunctionHelperTool.columnPropertyString(column);
        String columnReal = SFunctionHelperTool.columnToString(column);
        return executeBatch(entityList, batchSize, (sqlSession, entity) -> {
            Map<String, Object> map = CollectionUtils.newHashMapWithExpectedSize(2);
            map.put(Constants.ENTITY, entity);
            Object columnVal = ReflectionKit.getFieldValue(entity, columnProperty);

            UpdateWrapper<Object> updateWrapper = Wrappers.update().eq(columnReal, columnVal);
            map.put(Constants.WRAPPER, updateWrapper);
            sqlSession.update(getSqlStatement(SqlMethod.UPDATE), map);
        });
    }

    @Autowired(required = false)
    public void setDataPermissionService(DataPermissionService dataPermissionService) {
        this.dataPermissionService = dataPermissionService;
    }
    @Autowired(required = false)
    public void setDeleteServiceListeners(List<IDeleteServiceListener<DO>> deleteServiceListeners) {
        this.deleteServiceListeners = deleteServiceListeners;
    }

    /**
     * 获取查询数据范围约束服务
     * @return
     */
    protected DataPermissionService getQueryDataPermissionService(){
        return dataPermissionService;
    }

    /**
     * 获取删除数据范围约束服务
     * @return
     */
    protected DataPermissionService getDeleteDataPermissionService(){
        return dataPermissionService;
    }

    /**
     * 获取更新数据范围结束服务
     * @return
     */
    protected DataPermissionService getUpdateDataPermissionService(){
        return dataPermissionService;
    }
    @Autowired(required = false)
    public void setAddServiceListeners(List<IAddServiceListener<DO>> addServiceListeners) {
        this.addServiceListeners = addServiceListeners;
    }
    @Autowired(required = false)
    public void setUpdateServiceListeners(List<IUpdateServiceListener<DO>> updateServiceListeners) {
        this.updateServiceListeners = updateServiceListeners;
    }
}
