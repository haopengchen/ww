package com.aaa.chp.common;


import java.io.Serializable;

/**
 * Created by zts on 2020/3/20.
 */
public class CommonDTO<T> implements Serializable {


    private String ret_Code;


    private String ret_Msg;


    private T data;


    public CommonDTO(String ret_Code, String ret_Msg) {
        this.ret_Code = ret_Code;
        this.ret_Msg = ret_Msg;
    }

    public CommonDTO(String ret_Code, String ret_Msg, T t) {
        this.ret_Code = ret_Code;
        this.ret_Msg = ret_Msg;
        this.data = t;
    }

    public CommonDTO() {
    }

    public String getRet_Code() {
        return this.ret_Code;
    }

    public String getRet_Msg() {
        return this.ret_Msg;
    }

    public T getData() {
        return this.data;
    }

    public void setRet_Code(String ret_Code) {
        this.ret_Code = ret_Code;
    }

    public void setRet_Msg(String ret_Msg) {
        this.ret_Msg = ret_Msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String toString() {
        return "CommonDTO(ret_Code=" + this.getRet_Code() + ", ret_Msg=" + this.getRet_Msg() + ", data=" + this.getData() + ")";
    }

    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof CommonDTO)) {
            return false;
        } else {
            CommonDTO<?> other = (CommonDTO)o;
            if(!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$ret_Code = this.getRet_Code();
                    Object other$ret_Code = other.getRet_Code();
                    if(this$ret_Code == null) {
                        if(other$ret_Code == null) {
                            break label47;
                        }
                    } else if(this$ret_Code.equals(other$ret_Code)) {
                        break label47;
                    }

                    return false;
                }

                Object this$ret_Msg = this.getRet_Msg();
                Object other$ret_Msg = other.getRet_Msg();
                if(this$ret_Msg == null) {
                    if(other$ret_Msg != null) {
                        return false;
                    }
                } else if(!this$ret_Msg.equals(other$ret_Msg)) {
                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if(this$data == null) {
                    if(other$data != null) {
                        return false;
                    }
                } else if(!this$data.equals(other$data)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Override
    public int hashCode() {
        int result = ret_Code.hashCode();
        result = 31 * result + ret_Msg.hashCode();
        result = 31 * result + data.hashCode();
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof CommonDTO;
    }



}
