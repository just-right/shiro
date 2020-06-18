package com.example.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录请求Dto
 * @className: LoginDto
 * @description
 * @author: luffy
 * @date: 2020/6/16 12:46
 * @version:V1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    private String userName;
    private String passWord;
}
