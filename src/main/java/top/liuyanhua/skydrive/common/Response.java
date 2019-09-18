package top.liuyanhua.skydrive.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created with IDEA
 *
 * @author: liuyanhua
 * @date: 2019-06-08 22:22
 * @description //TODO $
 */
@ApiModel(description = "返回的响应")
public class Response<T> {
    @ApiModelProperty(value = "响应码")
    private Integer code;
    @ApiModelProperty(value = "响应信息")
    private String message;
    @ApiModelProperty(value = "响应数据",dataType = "object")
    private T data;

    public Response setCode(Integer code){
        this.code = code;
        return this;
    }

    public Response setMessage(String message){
        this.message = message;
        return  this;
    }
    public Response setData(T data){
        this.data = data;
        return  this;
    }
    public Response success(){
        return  this.setCode(200).setMessage("OK");
    }

    public Response success(T data){
        return  this.success().setData(data);
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }
}
