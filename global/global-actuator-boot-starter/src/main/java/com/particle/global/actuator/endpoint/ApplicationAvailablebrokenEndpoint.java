package com.particle.global.actuator.endpoint;

import com.particle.global.dto.response.Response;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author yangwei
 * @since 2024/1/11 09:34
 */
@Component
@Endpoint(id = "customBroken")
public class ApplicationAvailablebrokenEndpoint {

    @WriteOperation
    public Response broken(){
        return AvailableTool.broken();
    }
}
