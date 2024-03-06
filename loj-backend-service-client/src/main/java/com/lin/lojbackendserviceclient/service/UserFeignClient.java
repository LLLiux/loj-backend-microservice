package com.lin.lojbackendserviceclient.service;

import com.lin.lojbackendcommon.common.ErrorCode;
import com.lin.lojbackendcommon.constant.UserConstant;
import com.lin.lojbackendcommon.exception.BusinessException;
import com.lin.lojbackendmodel.model.entity.User;
import com.lin.lojbackendmodel.model.enums.UserRoleEnum;
import org.springframework.cloud.openfeign.FeignClient;

import javax.servlet.http.HttpServletRequest;

@FeignClient(name = "loj-backend-user-service",path = "/api/user/inner")
public interface UserFeignClient {

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    default User getLoginUser(HttpServletRequest request){
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getId() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }

    /**
     * 是否为管理员
     *
     * @param user
     * @return
     */
    default boolean isAdmin(User user){
        return user != null && UserRoleEnum.ADMIN.getValue().equals(user.getUserRole());
    }
}
