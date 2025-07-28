package cutalab.rachael;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.theme.Theme;

@SpringBootApplication
@EnableFeignClients(basePackages = "cutalab.rachael.backend.proxy")
@EnableDiscoveryClient
@Theme("default")
@Push
public class Application implements AppShellConfigurator {

    private static final long serialVersionUID = 4617212428508267446L;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
