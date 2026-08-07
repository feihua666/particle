package com.particle.global.trans.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 翻译结果
 * Created by yangwei
 * Created at 2020/11/27 10:48
 */
@Getter
@Setter
public class TransResult<R,K> implements Serializable {

    // 添加两个参数要构造函数
    public TransResult(R transValue, K key) {
        this.transValue = transValue;
        this.key = key;
    }

    /**
     * 翻译的结果值
     */
    private R transValue;
    /**
     * 翻译的关键字，一般为id
     */
    private K key;


    public static <R,K> TransResult<R,K> create(R transValue, K key){
        return new TransResult<>(transValue,key);
    }
}
