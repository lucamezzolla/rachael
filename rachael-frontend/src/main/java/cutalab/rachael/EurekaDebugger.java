package cutalab.rachael;


import jakarta.annotation.PostConstruct;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.cloud.client.ServiceInstance;

@Component
public class EurekaDebugger {

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostConstruct
    public void testEureka() {
        List<String> servicesToCheck = List.of("rachael-api-user", "rachael-api-wallet");
        for (String serviceId : servicesToCheck) {
            List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
            if (instances.isEmpty()) {
                System.out.println("❌ Nessuna istanza trovata per: " + serviceId);
            } else {
                instances.forEach(i -> System.out.println("✅ Istanza trovata per " + serviceId + ": " + i.getUri()));
            }
        }
    }

}

