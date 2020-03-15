package com.hzq.discovery;

import com.hzq.common.annotation.EnableApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 *
 */
@EnableAutoConfiguration
@EnableDiscoveryClient

@RestController
public class DiscoveryAllApp
{

    @Autowired
    private DiscoveryClient discoveryClient;

    public static void main( String[] args )
    {
        SpringApplication.run(DiscoveryAllApp.class,args);
    }

    @GetMapping("/services")
    public Set<String> getServices(){
        return new LinkedHashSet<>(discoveryClient.getServices());
    }

    @GetMapping("/services/{serviceName}")
    public List<ServiceInstance> getServices(@PathVariable("serviceName") String serviceName){
        return new LinkedList<>(discoveryClient.getInstances(serviceName));
    }

}
