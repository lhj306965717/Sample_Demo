package com.lhj.junit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by LiaoHongjie on 2017/7/8.
 */
public class CalculatorTest {

    private Calculator mCalculator;

    @Before
    public void setUp() throws Exception {
        mCalculator = new Calculator();
    }

    @Test
    public void sum() throws Exception {

        // 参数分析：
        // 第一个参数是指 期望值
        // 第二个参数是 实际给的值
        // 第三个参数是 期望值与实际值的差异，如果在差异值范围内就正确，否则错误
        Assert.assertEquals(5d, mCalculator.sum(3d, 5d), 2);
    }

    @Test
    public void substract() throws Exception {

    }

    @Test
    public void divide() throws Exception {

    }

    @Test
    public void multiply() throws Exception {

    }

}