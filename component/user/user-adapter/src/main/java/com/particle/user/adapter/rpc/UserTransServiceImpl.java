package com.particle.user.adapter.rpc;

import cn.hutool.core.util.StrUtil;
import com.particle.global.light.share.trans.TransConstants;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import com.particle.user.client.dto.data.UserTransVO;
import com.particle.user.infrastructure.dos.UserDO;
import com.particle.user.infrastructure.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
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

    @Autowired
    private IUserService userService;

    @Override
    public boolean support(String type) {
        return StrUtil.containsAny(type, TransConstants.TRANS_USER_BY_ID);
    }

    @Override
    public TransResult<UserTransVO, Long> trans(String type, Long key) {
        if (StrUtil.containsAny(type, TransConstants.TRANS_USER_BY_ID)) {
            UserDO byId = userService.getById(key);
            return new TransResult(userMapUserForTrans(byId),key);
        }
        return null;
    }

    @Override
    public boolean supportBatch(String type) {
        return support(type);
    }

    @Override
    public List<TransResult<UserTransVO, Long>> transBatch(String type, Set<Long> keys) {
        if (StrUtil.containsAny(type,TransConstants.TRANS_USER_BY_ID)) {
            return userService.listByIds(keys).stream().map(item->new TransResult<UserTransVO, Long>(userMapUserForTrans(item),item.getId())).collect(Collectors.toList());
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
        userForTrans.setAvatar(user.getAvatar());
        return userForTrans;
    }
}
