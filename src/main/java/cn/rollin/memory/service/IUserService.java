package cn.rollin.memory.service;

import cn.rollin.memory.common.res.Response;
import cn.rollin.memory.pojo.dto.LoginReq;
import cn.rollin.memory.pojo.dto.LoginRes;
import cn.rollin.memory.pojo.dto.RegisterReq;

/**
 * 用户服务
 *
 * @author rollin
 * @since 2024-12-27 23:53:06
 */
public interface IUserService {

    /**
     * 用户注册
     *
     * @param req
     * @return
     */
    Response<Object> register(RegisterReq req);

    /**
     * 用户登录
     *
     * @param loginReq 请求体
     * @return 登录结果
     */
    Response<LoginRes> login(LoginReq loginReq);
}
