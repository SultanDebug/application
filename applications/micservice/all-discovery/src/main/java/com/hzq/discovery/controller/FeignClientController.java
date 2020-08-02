package com.hzq.discovery.controller;

import com.hzq.providerservice.ProviderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 各注册发现间件测试
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

    /**
     * 服务列表
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 13:40
    */
    @GetMapping("/services")
    public Set<String> getServices(){
//        System.out.println(providerInterface.provider("client"));
        return Stream.of(providerInterface.provider("client")).collect(Collectors.toSet());
//        return new LinkedHashSet<>(discoveryClient.getServices());
    }

    /**
     * 服务实例列表
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2020/8/2 13:40
    */
    @GetMapping("/services/{serviceName}")
    public List<ServiceInstance> getServices(@PathVariable("serviceName") String serviceName){
        return new LinkedList<>(discoveryClient.getInstances(serviceName));
    }
}
