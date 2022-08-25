package br.com.bruno2code.contrateai.security;

import br.com.bruno2code.contrateai.api.LogApi;
import java.io.IOException;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -7858869558953243875L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) {
        try {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        } catch (IOException e) {
            new LogApi().sendLog("JwtAuthenticationEntryPoint", "commence", e.getMessage());
        }

    }

}
