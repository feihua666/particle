package com.particle.user.adapter.rpc;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import com.particle.user.client.dto.data.UserTransVO;
import com.particle.user.domain.enums.UserAccountType;
import com.particle.user.domain.gateway.UserDictGateway;
import com.particle.user.infrastructure.dos.UserDO;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierService;
import com.particle.user.infrastructure.mapper.UserMapper;
import com.particle.user.infrastructure.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>
 * 后台管理用户翻译实现
 * </p>
 *
 * @author yw
 * @since 2020-12-08
 */
@Component
public class UserTransServiceImpl implements ITransService<UserTransVO,Long> {

    /**
     * 这里使用 mapper 否则有循环依赖问题
     */
    @Autowired
    private UserMapper userService;
    @Autowired
    private IUserIdentifierService userIdentifierService;
    @Autowired
    private UserDictGateway userDictGateway;

    @Autowired(required = false)
    private UserTransOverrideService userTransOverrideService;

    @Override
    public boolean support(String type) {
        return StrUtil.containsAny(type, TransConstants.TRANS_USER_BY_ID,TransConstants.TRANS_USER_INFO_BY_ID);
    }

    @Override
    public TransResult<UserTransVO, Long> trans(String type, Long key) {
        if (StrUtil.containsAny(type, TransConstants.TRANS_USER_BY_ID)) {
            UserDO byId = userService.selectById(key);
            if (byId == null) {
                return null;
            }
            return new TransResult(userMapUserForTrans(byId),key);
        }else if (StrUtil.containsAny(type, TransConstants.TRANS_USER_INFO_BY_ID)) {
            UserDO byId = userService.selectById(key);
            if (byId == null) {
                return null;
            }
            UserTransVO userTransVO = userMapUserForTrans(byId);

            List<UserIdentifierDO> userIdentifierDOS = userIdentifierService.getByUserId(byId.getId());
            if (CollectionUtil.isNotEmpty(userIdentifierDOS)) {
                Long emailDictId = userDictGateway.getDictIdByGroupCodeAndItemValue(UserAccountType.Group.user_account_type.groupCode(), UserAccountType.front_email.itemValue());
                Long mobileDictId = userDictGateway.getDictIdByGroupCodeAndItemValue(UserAccountType.Group.user_account_type.groupCode(), UserAccountType.front_mobile.itemValue());
                fillExtInfo(userTransVO, userIdentifierDOS, emailDictId, mobileDictId);
            }

            return new TransResult(userTransVO,key);
        }
        return null;
    }

    @Override
    public boolean supportBatch(String type) {
        return support(type);
    }

    @Override
    public List<TransResult<UserTransVO, Long>> transBatch(String type, Set<Long> keys) {
        List<UserDO> userDOS = userService.selectBatchIds(keys);
        if (CollectionUtil.isEmpty(userDOS)) {
            return null;
        }
        if (StrUtil.containsAny(type,TransConstants.TRANS_USER_BY_ID)) {
            return userDOS.stream().map(item->new TransResult<UserTransVO, Long>(userMapUserForTrans(item),item.getId())).collect(Collectors.toList());
        }else if (StrUtil.containsAny(type,TransConstants.TRANS_USER_INFO_BY_ID)) {
            Long emailDictId = userDictGateway.getDictIdByGroupCodeAndItemValue(UserAccountType.Group.user_account_type.groupCode(), UserAccountType.front_email.itemValue());
            Long mobileDictId = userDictGateway.getDictIdByGroupCodeAndItemValue(UserAccountType.Group.user_account_type.groupCode(), UserAccountType.front_mobile.itemValue());
            List<Long> userIds = userDOS.stream().map(UserDO::getId).collect(Collectors.toList());
            Map<Long, List<UserIdentifierDO>> mapByUserIds = userIdentifierService.getMapByUserIds(userIds);
            return userDOS.stream().map(item->{
                        UserTransVO userTransVO = userMapUserForTrans(item);
                        List<UserIdentifierDO> userIdentifierDOS = mapByUserIds.get(item.getId());
                        fillExtInfo(userTransVO, userIdentifierDOS, emailDictId, mobileDictId);
                        return new TransResult<UserTransVO, Long>(userTransVO,item.getId());
                    }
                    ).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * 转换实体
     * @param user
     * @return
     */
    private UserTransVO userMapUserForTrans(UserDO user){
        UserTransVO userForTrans = new UserTransVO();
        userForTrans.setId(user.getId());

        userForTrans.setName(user.getName());
        userForTrans.setNickname(user.getNickname());
        userForTrans.changeAbNameIfNecessary();

        userForTrans.setAvatar(user.getAvatar());
        return userForTrans;
    }

    private void fillExtInfo(UserTransVO userTransVO, List<UserIdentifierDO> userIdentifierDOS, Long emailDictId, Long mobileDictId) {
        if (CollectionUtil.isNotEmpty(userIdentifierDOS)) {
            for (UserIdentifierDO userIdentifierDO : userIdentifierDOS) {
                if (Objects.equals(userIdentifierDO.getIdentityTypeDictId(),emailDictId)) {
                    userTransVO.setEmail(userIdentifierDO.getIdentifier());
                }else if (Objects.equals(userIdentifierDO.getIdentityTypeDictId(),mobileDictId)) {
                    userTransVO.setMobile(userIdentifierDO.getIdentifier());
                }
            }
        }
    }

    /**
     * 覆盖数据
     * @param result
     */
    private void overrideData(List<UserTransVO> result) {
        if (userTransOverrideService == null) {
            return;
        }
        if(CollectionUtil.isEmpty(result)){
            return;
        }
        List<Long> collect = result.stream().map(UserTransVO::getId).collect(Collectors.toList());
        List<UserTransOverrideService.UserTransOverrideDTO> overrideData = userTransOverrideService.getOverrideData(collect);

        if (CollectionUtil.isEmpty(overrideData)) {
            return;
        }
        Map<Long, UserTransOverrideService.UserTransOverrideDTO> userIdMap = overrideData.stream().collect(Collectors.toMap(UserTransOverrideService.UserTransOverrideDTO::getId, Function.identity()));

        for (UserTransVO userTransVO : result) {
            UserTransOverrideService.UserTransOverrideDTO userTransOverrideDTO = userIdMap.get(userTransVO.getId());
            if (userTransOverrideDTO != null) {
                if (StrUtil.isNotEmpty(userTransOverrideDTO.getName())) {
                    userTransVO.changeName(userTransOverrideDTO.getName());
                }
            }
        }
    }
}
