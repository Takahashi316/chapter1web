package org.smart4j.chapter1.model;

import lombok.Data;

/**
 * @author chen
 * @date 2019/5/2
 * @description
 */
@Data
public class Customer {

    private long id;

    private String name;

    private String contact;

    private String telephone;

    private String email;

    private String remark;


}
