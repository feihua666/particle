package com.particle.user.infrastructure.service.impl;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlUtils;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.particle.global.dto.basic.QueryCommand;
import com.particle.global.mybatis.plus.crud.IBaseServiceImpl;
import com.particle.global.mybatis.plus.mapstruct.IBaseQueryCommandMapStruct;
import com.particle.user.infrastructure.dos.UserDO;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierPwdService;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierService;
import com.particle.user.infrastructure.mapper.UserMapper;
import com.particle.user.infrastructure.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
public class UserServiceImpl extends IBaseServiceImpl<UserMapper, UserDO> implements IUserService {
	private IBaseQueryCommandMapStruct<UserDO> queryCommandMapStruct;

	@Autowired
	private IUserIdentifierService iUserIdentifierService;
	@Autowired
	private IUserIdentifierPwdService iUserIdentifierPwdService;

	@Override
	protected UserDO queryCommandToDO(QueryCommand queryCommand) {
		return queryCommandMapStruct.queryCommandToDO(queryCommand);
	}
	@Autowired
	public void setQueryCommandMapStruct(IBaseQueryCommandMapStruct<UserDO> queryCommandMapStruct) {
		this.queryCommandMapStruct = queryCommandMapStruct;
	}

	/**
	 * 删除后，删除 identifier
	 * @param id
	 * @param DO
	 */
	@Override
	protected void postDeleteById(Long id, UserDO DO) {
		onUserDelete(id);
	}

	/**
	 * 删除后，删除 identifier
	 * @param columnId
	 * @param column
	 * @param DOS
	 */
	@Override
	protected void postDeleteByColumn(Object columnId, SFunction<UserDO, ?> column, List<UserDO> DOS) {
		for (UserDO aDo : DOS) {
			onUserDelete(aDo.getId());
		}
	}
    @Override
    public QueryWrapper<UserDO> getQueryWrapper(QueryCommand queryForm) {
        QueryWrapper<UserDO> queryWrapper = super.getQueryWrapper(queryForm);

        if (StrUtil.equalsAny(queryForm.getClass().getName(),
                "com.particle.user.client.dto.command.representation.UserPageQueryCommand",
                "com.particle.user.client.dto.command.representation.UserQueryListCommand")) {
            Object identifierIdObj = ReflectUtil.getFieldValue(queryForm, "identifierId");
            Object userIdentifierObj = ReflectUtil.getFieldValue(queryForm, "userIdentifier");

            addIfUserIdentifierIdNotNull(queryWrapper, userIdentifierObj);
            addIfUserIdentifierNotEmpty(queryWrapper, userIdentifierObj);
        }

        return queryWrapper;
    }

    /**
     * 添加userIdentifierId条件查询
     * @param queryWrapper
     * @param identifierIdObj
     */
    public void addIfUserIdentifierIdNotNull(QueryWrapper<UserDO> queryWrapper, Object identifierIdObj){
        Long identifierId = (Long)identifierIdObj;
        if (identifierIdObj != null) {
            if (identifierId != null) {
                UserIdentifierDO userIdentifierDO = iUserIdentifierService.getById(identifierId);
                if (userIdentifierDO == null) {
                    // 为空就是不存在，不存在，得加一个不存在的条件
                    queryWrapper.apply("false");
                }else {
                    queryWrapper.eq(UserDO.COLUMN_ID,userIdentifierDO.getUserId());
                }
            }
        }
    }

    private static String UserIdentifierDOTableNameCache = null;
    private static String UserDOTableNameCache = null;

    /**
     * 添加userIdentifier条件查询
     * @param queryWrapper
     * @param userIdentifierObj
     */
    public void addIfUserIdentifierNotEmpty(QueryWrapper<UserDO> queryWrapper, Object userIdentifierObj){
        String userIdentifier = (String)userIdentifierObj;
        if (StrUtil.isEmpty(userIdentifier)) {
            return;
        }
        // 是否使用 like 查询
        boolean isUserIdentifierUseLike = true;
        if (isUserIdentifierUseLike) {
            if (UserIdentifierDOTableNameCache == null) {
                TableName annotation = AnnotationUtil.getAnnotation(UserIdentifierDO.class, TableName.class);
                UserIdentifierDOTableNameCache = annotation.value();
            }
            if (UserDOTableNameCache == null) {
                TableName annotation = AnnotationUtil.getAnnotation(UserDO.class, TableName.class);
                UserDOTableNameCache = annotation.value();
            }

            // select id from component_user_identifier where user_id = component_user.id and identifier like '%222%'
            String sql = "select id from " + UserIdentifierDOTableNameCache + " where user_id = " + UserDOTableNameCache + ".id" + " and identifier like {0}";
            queryWrapper.exists(sql, SqlUtils.concatLike(userIdentifier, SqlLike.DEFAULT));
        }else {
            UserIdentifierDO userIdentifierDO = iUserIdentifierService.getByIdentifier(userIdentifier);
            if (userIdentifierDO == null) {
                // 为空就是不存在，不存在，得加一个不存在的条件
                queryWrapper.apply("false");
            }else {
                queryWrapper.eq(UserDO.COLUMN_ID,userIdentifierDO.getUserId());
            }
        }
    }
	/**
	 * 统一删除联动删除方法
	 * @param userId
	 */
	private void onUserDelete(Long userId) {
		iUserIdentifierService.deleteByUserId(userId);
		iUserIdentifierPwdService.deleteByUserId(userId);
	}

}
