package cn.rollin.memory.common.exception;


import cn.rollin.memory.common.enums.ResStatusEnum;
import lombok.Getter;

/**
 * 业务异常
 *
 * @author rollin
 * @since 2022-10-01 23:00:34
 */
@Getter
public class BizException extends RuntimeException {

    private ResStatusEnum resStatusEnum;

    public BizException(ResStatusEnum resStatusEnum) {
        this.resStatusEnum = resStatusEnum;
    }

}
