package com.atjh.linux.entity.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class FoodQuery implements Serializable {
    private Integer id;

    private String name;
}
