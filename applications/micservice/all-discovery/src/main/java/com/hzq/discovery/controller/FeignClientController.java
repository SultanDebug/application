package com.hzq.discovery.controller;

import com.hzq.providerservice.ProviderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-03-17
 */
@RestController
public class FeignClientController {
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ProviderInterface providerInterface;

    @GetMapping("/services")
    public Set<String> getServices(){
        System.out.println(providerInterface.provider("client"));
        return new LinkedHashSet<>(discoveryClient.getServices());
    }

    @GetMapping("/services/{serviceName}")
    public List<ServiceInstance> getServices(@PathVariable("serviceName") String serviceName){
        return new LinkedList<>(discoveryClient.getInstances(serviceName));
    }
}
