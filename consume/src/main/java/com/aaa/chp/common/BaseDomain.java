package com.aaa.chp.common;

import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
/**
 * Created by zts on 2020/3/23.
 */

public class BaseDomain implements Serializable {
    public BaseDomain() {
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
