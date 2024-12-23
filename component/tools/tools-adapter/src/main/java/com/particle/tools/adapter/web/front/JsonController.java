package com.particle.tools.adapter.web.front;

import cn.hutool.json.JSONUtil;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.security.security.ApplicationContextForSecurityHelper;
import com.particle.global.tool.json.JsonTool;
import com.particle.tools.client.dto.command.JsonParseCommand;
import com.particle.tools.client.dto.command.JsonParseMultipleLineCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangwei
 * Created at 2024-08-06 10:59:16
 */
@Tag(name = "json相关接口")
@RestController
@RequestMapping("/front/web/json")
public class JsonController extends AbstractBaseWebAdapter {

    /**
     * json解析，将json字符串转为对象
     * 本项目大量使用bigint作为id，这里需要将bigint转为long，返回给前端时会自动转为字符串
     * @param form
     * @return
     */
    @Operation(summary = "json解析")
    @PostMapping("/parse")
    @ResponseStatus(HttpStatus.OK)
    public SingleResponse<Object> parse( @RequestBody @Validated JsonParseCommand form) {
        Object o = parseJson(form.getJsonStr());
        return SingleResponse.of(o);
    }

    @Operation(summary = "json解析多条")
    @PostMapping("/parseMultipleLine")
    @ResponseStatus(HttpStatus.OK)
    public MultiResponse<String> parse(@RequestBody @Validated JsonParseMultipleLineCommand form) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = ApplicationContextForSecurityHelper.getBean(MappingJackson2HttpMessageConverter.class);
        List<String> result = new ArrayList<>(form.getJsonStrs().size());
        for (String jsonStr : form.getJsonStrs()) {
            Object o = parseJson(jsonStr);

            String toJsonStrForHttp = JsonTool.toJsonStrForHttp(o, jackson2HttpMessageConverter.getObjectMapper());
            result.add(toJsonStrForHttp);
        }

        return MultiResponse.of(result);
    }


    private Object parseJson(String jsonStr) {
        return JSONUtil.parse(jsonStr);
    }
}
