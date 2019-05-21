package com.yangzhongli.sp.config;

import com.google.gson.Gson;
import com.yangzhongli.sp.annotation.LoginRequired;
import com.yangzhongli.sp.constants.BaseConstants;
import com.yangzhongli.sp.constants.JsonResult;
import com.yangzhongli.sp.constants.StatusConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 拦截器
 */
@Slf4j
public class MyInterceptor implements HandlerInterceptor {

    /**
      * 
      * 在业务处理器处理请求之前被调用 如果返回false 
      * 从当前的拦截器往回执行所有拦截器的afterCompletion(),
      * 再退出拦截器链, 如果返回true 执行下一个拦截器,
      * 直到所有的拦截器都执行完毕 再执行被拦截的Controller
      * 然后进入拦截器链,
      * 从最后一个拦截器往回执行所有的postHandle()
      * 接着再从最后一个拦截器往回执行所有的afterCompletion()
      * 
      * @param  request
      * 
      * @param  response
     */
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        log.info("token==============" + request.getSession().getId());

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 判断接口是否需要登录（是否使用@LoginRequired）
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        // 有 @LoginRequired 注解，需要认证
        if (methodAnnotation != null) {
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute(BaseConstants.LOGIN_USER);
            if (userId == null) {
                JsonResult jsonResult = new JsonResult(StatusConstants.USER_NOT_FOND);
                String jsonObjectStr = new Gson().toJson(jsonResult);
                returnJson(request, response, jsonObjectStr);
                return false;
            }

        }

        //处理跨域问题代码
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        return true;
    }

    private void returnJson(HttpServletRequest request, HttpServletResponse response, String json) throws Exception {
        try {
            response.reset();
            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-store");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            pw.write(json);
            pw.flush();
        } catch (Exception e) {
            log.error("返回用户未登录失败", e);
        }
    }

    //请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
    // 在业务处理器处理请求执行完成后,生成视图之前执行的动作
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("postHandle被调用\n");
    }

    //在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
    /**
      * 在DispatcherServlet完全处理完请求后被调用
      * 当有拦截器抛出异常时,
      * 会从当前拦截器往回执行所有的拦截器的afterCompletion()
      * 
      * @param request
      * 
      * @param response
      * 
      * @param handler
      * 
    */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("afterCompletion被调用\n");
    }
}
