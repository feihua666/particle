package com.particle.global.actuator.endpoint;

import com.particle.global.dto.response.Response;
import com.particle.global.tool.spring.SpringContextHolder;
import org.springframework.boot.availability.AvailabilityChangeEvent;
import org.springframework.boot.availability.LivenessState;
import org.springframework.boot.availability.ReadinessState;

/**
 * <p>
 * 应用可用性工具
 * </p>
 *
 * @author yangwei
 * @since 2024/1/11 10:50
 */
public class AvailableTool {

    public static Response refusing_traffic(){
        AvailabilityChangeEvent.publish(SpringContextHolder.getApplicationContext(), ReadinessState.REFUSING_TRAFFIC);
        return Response.buildSuccess();
    }
    public static Response accepting_traffic(){
        AvailabilityChangeEvent.publish(SpringContextHolder.getApplicationContext(), ReadinessState.ACCEPTING_TRAFFIC);
        return Response.buildSuccess();
    }
    public static Response broken(){
        AvailabilityChangeEvent.publish(SpringContextHolder.getApplicationContext(), LivenessState.BROKEN);
        return Response.buildSuccess();
    }
    public static Response correct(){
        AvailabilityChangeEvent.publish(SpringContextHolder.getApplicationContext(), LivenessState.CORRECT);
        return Response.buildSuccess();
    }
}
