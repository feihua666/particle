package com.particle.global.wxjava.cp.endpoint;

import com.particle.global.wxjava.cp.config.WxCpConfiguration;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.WxCpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * <p>
 * 企业微信js配置
 * </p>
 *
 * @author yangwei
 * @since 2023-06-28 14:40:51
 */
@Slf4j
@RestController
@RequestMapping("/wx/cp/js/{corpId}/{agentId}")
public class WxJsController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/getJsConf")
    public Map getJsConf(
            @PathVariable String corpId,
            @PathVariable Integer agentId,
            String uri) throws WxErrorException {

        final WxCpService wxCpService = WxCpConfiguration.getCpService(corpId, agentId);
        if (wxCpService == null) {
            throw new IllegalArgumentException(String.format("未找到对应agentId=[%d]的配置，请核实！", agentId));
        }

        WxJsapiSignature wxJsapiSignature = wxCpService.createJsapiSignature(uri);
        String signature = wxJsapiSignature.getSignature();
        String nonceStr = wxJsapiSignature.getNonceStr();
        long timestamp = wxJsapiSignature.getTimestamp();

        Map res = new HashMap<String, String>();
        res.put("appId", corpId); // 必填，企业微信的corpID
        res.put("timestamp", timestamp); // 必填，生成签名的时间戳
        res.put("nonceStr", nonceStr); // 必填，生成签名的随机串
        res.put("signature", signature); // 必填，签名，见 附录-JS-SDK使用权限签名算法
        return res;
    }


}
