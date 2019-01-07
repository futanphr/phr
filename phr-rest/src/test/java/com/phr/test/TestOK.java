package com.phr.test;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author
 * @create 2019-01-06 上午1:06
 **/
public class TestOK {
    public static void main (String args[]){
       /* PhrOrderEntity phrOrderEntity=new PhrOrderEntity.Builder().setOrderId(1111L).setAmt(new BigDecimal(999)).build();
        BigDecimal bigDecimal=Optional.ofNullable(phrOrderEntity).map(PhrOrderEntity::getAmt).orElseGet(null);
        System.out.println(bigDecimal);
        System.out.println(JSON.toJSON(phrOrderEntity));

        Optional<List<String>> o=Optional.ofNullable(Arrays.asList("1","2","3"));
        List<String> list=o.orElseThrow(()->new RuntimeException("返回结果为空"));
        Optional<PhrOrderEntity> phrOrderEntity1=new ArrayList<PhrOrderEntity>().stream().max(Comparator.comparing(PhrOrderEntity::getId));
        System.out.println(JSON.toJSON(list));
*/
    }
}
