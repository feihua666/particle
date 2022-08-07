package com.particle.global.trans.result;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 翻译结果
 * Created by yangwei
 * Created at 2020/11/27 10:48
 */
@Getter
@Setter
@NoArgsConstructor // 缓存时需要
@AllArgsConstructor
public class TransResult<R,K> implements Serializable {
    /**
     * 翻译的结果值
     */
    private R transValue;
    /**
     * 翻译的关键字，一般为id
     */
    private K key;


}
