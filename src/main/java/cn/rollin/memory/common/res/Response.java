package cn.rollin.memory.common.res;

import cn.rollin.memory.common.enums.ResStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 响应对象
 *
 * @author rollin
 * @since 2024-12-28 00:02:42
 */
@Data
public class Response<T> {
    /**
     * 响应码
     */
    @NotNull
    private String code;

    /**
     * 响应描述
     */
    @NotNull
    private String message;

    /**
     * 时间戳
     */
    @NotNull
    private Long timestamp;

    /**
     * 响应实体
     */
    private T body;

    private Response(ResStatusEnum statusEnum) {
        this.timestamp = System.currentTimeMillis();
        this.code = statusEnum.getCode();
        this.message = statusEnum.getMessage();
    }

    /**
     * 构建成功Response对象
     *
     * @return response 对象
     */
    public static Response<Object> buildSuccess() {
        return new Response<>(ResStatusEnum.SUCCESS);
    }

    /**
     * 构造成功Response
     *
     * @param t   body 内容
     * @param <T> body 泛型
     * @return response 对象
     */
    public static <T> Response<T> buildSuccess(T t) {
        Response<T> response = new Response<>(ResStatusEnum.SUCCESS);
        response.setBody(t);
        return response;
    }

    /**
     * 构建错误Response
     *
     * @return response 对象
     */
    public static Response<Object> buildError() {
        return new Response<>(ResStatusEnum.FAILURE);
    }

    /**
     * 构建错误Response
     *
     * @param statusEnum 错误枚举
     * @return response 对象
     */
    public static Response<Object> buildError(ResStatusEnum statusEnum) {
        return new Response<>(statusEnum);
    }
}
