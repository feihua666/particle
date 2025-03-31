package com.particle.agi.domain.gateway;

import com.particle.agi.domain.enums.AgiRawDocumentType;
import com.particle.agi.domain.values.AgiDocument;
import com.particle.common.domain.gateway.IGateway;

import java.util.List;

/**
 * <p>
 * 文档网关,主要是提供一些工具方便使用
 * </p>
 *
 * @author yangwei
 * @since 2025/1/10 09:18
 */
public interface AgiDocumentGateway extends IGateway {


    /**
     * 解析并拆分，将一个url对应的文档拆分成多个片段文本，以做向量化使用
     * @param url 文档地址，以http开头的url
     * @param agiRawDocumentType 文档类型
     * @return
     */
    public List<AgiDocument> parseAndSplit(String url, AgiRawDocumentType agiRawDocumentType);
}
