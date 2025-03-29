package cn.rollin.memory.common.utils;

import cn.rollin.memory.pojo.dto.LoginUser;

public class UserContext {
    private static final ThreadLocal<LoginUser> userHolder = new ThreadLocal<>();

    public static void setUser(LoginUser user) {
        userHolder.set(user);
    }

    public static LoginUser getUser() {
        return userHolder.get();
    }

    public static void removeUser() {
        userHolder.remove();
    }
}
