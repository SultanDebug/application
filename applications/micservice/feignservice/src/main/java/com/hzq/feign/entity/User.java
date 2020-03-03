package com.hzq.feign.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-02-28
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -2354460834163683135L;
    private Integer id;

    private String user_name;

    private String sex;

    private Integer age;

    private String address;
}
