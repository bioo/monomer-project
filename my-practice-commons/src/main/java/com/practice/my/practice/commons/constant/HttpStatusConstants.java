package com.practice.my.practice.commons.constant;

/**
 * 错误枚举类
 * @author Pengcheng Zhao
 * @date 2019/7/21 13:31
 * Description：
 */
public enum HttpStatusConstants {
    BAD_GATEWAY(502,"从上游服务器收到无效的响应");

    private int status;
    private String content;

    private HttpStatusConstants(int status, String content){
        this.status = status;
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
