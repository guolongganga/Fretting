package cn.com.buyforyou.fund.params;

import java.io.Serializable;

/**
 * ${DESCRIPTION}
 *
 * @Author
 * @create 2017-12-04
 **/
public class CommonReqData<T> implements Serializable {

    private static final long serialVersionUID = -3778015552740745986L;

    /**
     * 只有有权限请求时，才要求校验token。
     */
    private String token;

    /**
     * 只有有权限请求时，才校验用户ID.
     */
    private String userId;

    /**
     * 传输的数据
     */
    private T data;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
