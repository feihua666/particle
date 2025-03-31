package com.particle.agi.infrastructure.gateway.impl;

import cn.hutool.core.util.URLUtil;
import com.particle.agi.domain.enums.AgiRawDocumentType;
import com.particle.agi.domain.gateway.AgiDocumentGateway;
import com.particle.agi.domain.values.AgiDocument;
import com.particle.agi.infrastructure.tool.AgiDocumentTool;
import com.particle.global.ai.enums.AIDocumentType;
import com.particle.global.ai.tool.AiDocumentTool;
import com.particle.global.projectinfo.RunningInfo;
import com.particle.global.tool.str.NetPathTool;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 文档网关，实现类
 * </p>
 *
 * @author yangwei
 * @since 2025/1/10 09:23
 */
@Component
public class AgiDocumentGatewayImpl implements AgiDocumentGateway {


    private RunningInfo runningInfo;
    /**
     * 本地地址，如：http://localhost:8080
     */
    private static String local = null;

    @Override
    public List<AgiDocument> parseAndSplit(String url, AgiRawDocumentType agiRawDocumentType) {
        if (local == null) {
            local = runningInfo.toMap().get("Local") + "/oss/download";
        }
        // 兼容一下本地oss的url，有可能上传的文件没有拼接endpoint地址
        if (!url.startsWith("http")) {
            url = NetPathTool.concat(local, url);
        }
        url = URLUtil.encodeFragment(url);
        AIDocumentType aiDocumentType = AIDocumentType.valueOf(agiRawDocumentType.name());
        List<Document> documents = AiDocumentTool.loadAsDocuments(new DefaultResourceLoader().getResource(url), aiDocumentType);
        // 由于拆分后的文档太大，需要重新分割，以适应大模型和嵌入模型的长度，需要重新计算一下
        documents = AiDocumentTool.splitDocuments(documents);
        return AgiDocumentTool.toAgiDocumentList(documents);
    }


    @Autowired
    public void setRunningInfo(RunningInfo runningInfo) {
        this.runningInfo = runningInfo;
    }
}
