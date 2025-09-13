package com.particle.user.adapter.identifier.rpc;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import com.particle.user.app.identifier.structmapping.UserIdentifierAppStructMapping;
import com.particle.user.app.structmapping.UserExtraInfoAppStructMapping;
import com.particle.user.client.identifier.dto.data.UserIdentifierVO;
import com.particle.user.infrastructure.identifier.dos.UserIdentifierDO;
import com.particle.user.infrastructure.identifier.service.IUserIdentifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 翻译角色实现
 * 主要是根据用户id翻译多个
 * @author  yangwei
 * Created at 2023-05-26 13:40:03
 */
@Primary
@Component
public class UserIdentifierMultipleTransServiceImpl implements ITransService<List<UserIdentifierVO>,Long> {

    @Autowired
    private IUserIdentifierService iUserIdentifierService;

    @Override
    public boolean support(String type) {
        return StrUtil.containsAny(type, TransConstants.TRANS_USER_IDENTIFIER_BY_USER_ID);
    }

    @Override
    public TransResult<List<UserIdentifierVO>, Long> trans(String type, Long key) {
        if (StrUtil.equals(type,TransConstants.TRANS_USER_IDENTIFIER_BY_USER_ID)) {
            List<UserIdentifierDO> userIdentifierDOS = iUserIdentifierService.getByUserId(key);
            if (CollectionUtil.isEmpty(userIdentifierDOS)) {
                return null;
            }
            return new TransResult(newUserIdentifierVOs(userIdentifierDOS),key);
        }
        return null;
    }

    @Override
    public boolean supportBatch(String type) {
        return support(type);
    }

    @Override
    public List<TransResult<List<UserIdentifierVO>, Long>> transBatch(String type, Set<Long> keys) {
        if (StrUtil.containsAny(type,TransConstants.TRANS_USER_IDENTIFIER_BY_USER_ID)) {
            Map<Long, List<UserIdentifierDO>> byUserIds = iUserIdentifierService.getMapByUserIds(new ArrayList<>(keys));
            if (CollectionUtil.isNotEmpty(byUserIds)) {
                Set<Long> userIds = byUserIds.keySet();
                List<TransResult<List<UserIdentifierVO>, Long>> result = new ArrayList<>(userIds.size());
                for (Long userId : userIds) {
                    result.add(new TransResult(newUserIdentifierVOs(byUserIds.get(userId)),userId));
                }
                return result;
            }
        }
        return null;
    }


    private List<UserIdentifierVO> newUserIdentifierVOs(List<UserIdentifierDO> userIdentifierDOs){
        return UserIdentifierAppStructMapping.instance.userIdentifierDOsToUserIdentifierVOs(userIdentifierDOs);
    }
}
