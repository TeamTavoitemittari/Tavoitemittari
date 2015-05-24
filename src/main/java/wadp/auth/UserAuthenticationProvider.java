package wadp.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import wadp.domain.User;
import wadp.service.UserService;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        String email = a.getPrincipal().toString();
        String password = a.getCredentials().toString();

        User user = userService.authenticate(email, password);

        List<GrantedAuthority> grantedAuths = new ArrayList<>();

        grantedAuths.add(new SimpleGrantedAuthority(user.getUserRole()));

        return new UsernamePasswordAuthenticationToken(email, password, grantedAuths);
    }

    @Override
    public boolean supports(Class<?> type) {
        return true;
    }


}
