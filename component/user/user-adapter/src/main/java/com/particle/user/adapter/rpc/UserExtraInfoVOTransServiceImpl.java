package com.particle.user.adapter.rpc;

import cn.hutool.core.util.StrUtil;
import com.particle.component.light.share.trans.TransConstants;
import com.particle.global.trans.api.ITransService;
import com.particle.global.trans.result.TransResult;
import com.particle.user.app.structmapping.UserExtraInfoAppStructMapping;
import com.particle.user.client.dto.data.UserExtraInfoVO;
import com.particle.user.infrastructure.dos.UserExtraInfoDO;
import com.particle.user.infrastructure.service.IUserExtraInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 翻译部门实现
 * @author  yangwei
 * Created at 2023-05-26 13:40:03
 */
@Primary
@Component
public class UserExtraInfoVOTransServiceImpl implements ITransService<UserExtraInfoVO,Long> {

    @Autowired
    private IUserExtraInfoService iUserExtraInfoService;

    @Override
    public boolean support(String type) {
        return StrUtil.containsAny(type, TransConstants.TRANS_USER_EXTRA_INFO_BY_ID);
    }

    @Override
    public TransResult<UserExtraInfoVO, Long> trans(String type, Long key) {
        UserExtraInfoDO userExtraInfoDO = null;
        if (StrUtil.containsAny(type,TransConstants.TRANS_USER_EXTRA_INFO_BY_ID)) {
            userExtraInfoDO = iUserExtraInfoService.getByUserId(key);
        }
        if (userExtraInfoDO != null) {
            return new TransResult(newUserExtraInfoVO(userExtraInfoDO),key);
        }
        return null;
    }

    @Override
    public boolean supportBatch(String type) {
        return support(type);
    }

    @Override
    public List<TransResult<UserExtraInfoVO, Long>> transBatch(String type, Set<Long> keys) {
        if (StrUtil.containsAny(type,TransConstants.TRANS_USER_EXTRA_INFO_BY_ID)) {
            List<UserExtraInfoDO> userExtraInfoDOS = iUserExtraInfoService.getByUserIds(keys);
            return userExtraInfoDOS.stream()
                    .map(item->new TransResult<UserExtraInfoVO, Long>(newUserExtraInfoVO(item),item.getUserId()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    private UserExtraInfoVO newUserExtraInfoVO(UserExtraInfoDO userExtraInfoDO){
        return UserExtraInfoAppStructMapping.instance.userExtraInfoDOToUserExtraInfoVO(userExtraInfoDO);
    }

}
