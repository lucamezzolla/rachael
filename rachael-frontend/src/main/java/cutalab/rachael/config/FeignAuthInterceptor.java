package cutalab.rachael.config;

import org.springframework.context.annotation.Configuration;

import cutalab.rachael.base.ui.util.SessionUtil;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class FeignAuthInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        var currentUser = SessionUtil.getCurrentUser();
        if (currentUser != null && currentUser.getToken() != null) {
            template.header("Authorization", "Bearer " + currentUser.getToken());
        }
    }
    
}