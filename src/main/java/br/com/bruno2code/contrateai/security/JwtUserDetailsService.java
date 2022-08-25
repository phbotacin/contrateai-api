package br.com.bruno2code.contrateai.security;

import br.com.bruno2code.contrateai.api.LogApi;
import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {

        try {
            User userInfo = new User(username, username, new ArrayList<>());

            if (userInfo != null) {
                return userInfo;
            } else {
                return null;
            }
        } catch (Exception e) {
            new LogApi().sendLog("JwtUserDetailsService", "loadUserByUsername", e.getMessage());
            return null;
        }

    }
}
