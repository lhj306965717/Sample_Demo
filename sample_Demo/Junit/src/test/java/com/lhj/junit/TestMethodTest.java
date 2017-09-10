package com.lhj.junit;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by LiaoHongjie on 2017/7/8.
 */
public class TestMethodTest {
    @Test
    public void add() throws Exception {

       String  str = "liao hong jie";

        String[] strs = str.split(" ");

        List<String> asList = Arrays.asList(strs);

      //  Log.e("TAG", asList.toString());

        Assert.assertEquals("TA: ", "liao", asList.get(0)); //测试逻辑


        Assert.assertTrue(false); //直接判断是否为true
    }

}