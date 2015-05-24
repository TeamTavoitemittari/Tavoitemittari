/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wadp.service;

import wadp.domain.User;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import wadp.repository.UserRepository;
import java.util.List;
import javax.transaction.Transactional;

import wadp.domain.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> list() {
        List<User> list = userRepository.findAll();

        return list;
    }

    private boolean emailAlreadyRegistered(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }
@Transactional       
public User createUser(String email, String password, String name, String userRole) {
        
        if (email== null || email.isEmpty()) {
            throw new IllegalArgumentException("Email must not be null or empty");
        }
        email = email.toLowerCase();
        email = email.trim();

        if (emailAlreadyRegistered(email)) {
            throw new EmailAlreadyRegisteredException();
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);
        user.setUserRole(userRole);

        return userRepository.save(user);
    }

    public User authenticate(String email, String password) throws AuthenticationException {
        User user = userRepository.findByEmail(email);

        if (user == null || !user.passwordEquals(password)) {
            throw new AuthenticationException("Unable to authenticate user with email" + email) {
            };
        }

        return user;
    }

}
