package com.particle.tools.adapter.web.front;

import cn.hutool.core.date.DateUtil;
import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.global.dto.response.MultiResponse;
import com.particle.tools.client.dto.command.CronQueryCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangwei
 * Created at 2021/2/5 17:46
 */
@Tag(name = "Cron相关接口")
@RestController
@RequestMapping("front/web/cron")
public class CronController extends AbstractBaseWebAdapter {

    @Operation(summary = "获取cron的执行时间")
    @GetMapping("/cronRunTimes")
    @ResponseStatus(HttpStatus.OK)
    public MultiResponse<String> cronRunTimes(@Validated CronQueryCommand cronQueryCommand) {
        LocalDateTime next = cronQueryCommand.getStartAt();
        if (next == null) {
            next =  LocalDateTime.now();
        }
        List<String> result = new ArrayList<>(cronQueryCommand.getTimes());

        CronExpression parse = CronExpression.parse(cronQueryCommand.getCronExpression());
        for (int i = 0; i < cronQueryCommand.getTimes(); i++) {
            next = parse.next(next);
            result.add(DateUtil.format(next, "yyyy-MM-dd HH:mm:ss"));
        }
        return MultiResponse.of(result);
    }

}
