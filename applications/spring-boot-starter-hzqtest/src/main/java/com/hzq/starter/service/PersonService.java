package com.hzq.starter.service;

import com.alibaba.fastjson.JSONObject;
import com.hzq.starter.properties.PersonProperties;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Huangzq
 * @title: PersonService
 * @projectName applications
 * @date 2019/9/11 15:13
 */
@Slf4j
public class PersonService {

    private PersonProperties personProperties;

    public PersonService(){}

    public PersonService(PersonProperties personProperties){
        this.personProperties = personProperties;
    }

    public void sayHello(){
        log.info(JSONObject.toJSONString(personProperties));
    }

}
