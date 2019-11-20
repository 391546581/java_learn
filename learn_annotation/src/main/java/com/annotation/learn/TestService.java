package com.annotation.learn;

import org.springframework.stereotype.Service;

/**
 * @author BlueWang
 * @ClassName: TestService
 * @Description:
 * @date 2019/11/20 10:23
 */

@Service
@Mark
public class TestService {
    private String test;
    @Mark
    public void setTest(){
    }
}
