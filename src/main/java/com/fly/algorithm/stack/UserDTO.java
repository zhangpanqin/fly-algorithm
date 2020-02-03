package com.fly.algorithm.stack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 张攀钦
 * @date 2020-02-03-15:29
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -4074898755358149527L;
    private String name;
}
