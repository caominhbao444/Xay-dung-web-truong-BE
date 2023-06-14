package com.project.trainingteam.config.security;

import com.project.trainingteam.entities.user.User;
import com.project.trainingteam.repo.inf.user.UserRepo;
import com.project.trainingteam.service.impl.user.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AccessControlInterceptor implements HandlerInterceptor {

    @Autowired
    private UserRepo userRepo;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            // Xử lý sau khi yêu cầu được xử lý bởi Controller và trước khi gửi kết quả cho người dùng

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentName = authentication.getName();
            User user = userRepo.findUserByUserName(currentName);
            String userRole = user.getRoleName();

            // Kiểm tra quyền truy cập
            if (!hasAccessPermission(request, userRole)) {
                // Nếu quyền truy cập không được phép, từ chối truy cập và trả về phản hồi tương ứng
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getOutputStream().write("Access denied".getBytes());
                return false;
            }

            // Nếu quyền truy cập được phép, cho phép tiếp tục xử lý yêu cầu
            return true;

        } catch (Exception ex) {
            // Xử lý ngoại lệ và trả về phản hồi lỗi
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getOutputStream().write("An error occurred".getBytes());
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private boolean hasAccessPermission(HttpServletRequest request, String userRole) {
        // Lấy đường dẫn API và phương thức từ yêu cầu
        String apiPath = request.getRequestURI();

        if (apiPath.matches("/api/v1/.*")) {
            // Kiểm tra vai trò người dùng
            if (userRole.equals("ADMIN")) {
                return true;
            }
            return false;
        }
        if(apiPath.matches("/api/v2/.*")){
            if(userRole.equals("ADMIN") || userRole.equals("Nhân Sự Trường")){
                return true;
            }
            return false;
        }
        if(apiPath.matches("/api/v3/.*")){
            if(userRole.equals("ADMIN") || userRole.equals("Nhân Sự Trường") || userRole.equals("Sinh Viên")){
                return true;
            }
            return false;
        }
        return false;
    }
}

