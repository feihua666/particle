package com.particle.generator.adapter.web.admin;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 代码生成管理前端适配器
 * 主要用于后台管理等
 * </p>
 *
 * @author yangwei
 * @since 2022-06-30 17:33
 */
@Api(tags = "代码生成后台管理相关接口")
@RestController
@RequestMapping("/admin/generator")
public class GeneratorAdminController extends AbstractBaseWebAdapter {
}
