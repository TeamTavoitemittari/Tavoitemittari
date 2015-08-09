/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wadp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import wadp.repository.UserRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import wadp.domain.Course;
import wadp.domain.Grade;

import wadp.domain.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseService courseService;
    
    @Autowired
    private GradeService gradeService;
    
    @Autowired 
    private CommentService commentService;

    public List<User> list() {
        List<User> list = userRepository.findByUserRole("student");
        list.addAll(userRepository.findByUserRole("teacher"));
        return list;
    }

    public List<User> getAdmins() {
        return userRepository.findByUserRole("admin");
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

        if (email == null || email.isEmpty()) {
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
            System.out.println("Error in user authentication");
            throw new AuthenticationException("Unable to authenticate user with email" + email) {
            };
        }

        return user;
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authentication.getName());
    }

    @Transactional
    public User ChangePassword(String newPassword) {
        if (newPassword == null || newPassword.isEmpty()) {
            throw new IllegalArgumentException("newPassword must not be null or empty");
        }

        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            throw new AuthenticatedUserIsNullException();
        }
        User user = getAuthenticatedUser();
        user.setPassword(newPassword);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), auth.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authRequest);
        auth.setAuthenticated(false);
        return user;

    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> findUserByRole(String role) {
        return userRepository.findByUserRole(role);
    }

    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    public void clearUsers() {
        userRepository.deleteAll();
    }

    @Transactional
    public void deleteUser(Long id) {
            User user = userRepository.findOne(id);
            System.out.println(user.getName());
            System.out.println(user.getName());
            System.out.println(user.getName());
            System.out.println(user.getName());
            System.out.println(user.getName());
            if (user.getUserRole().equals("student")) {
                List<Course> courses = courseService.getUsersCourses(user);
                
                List<Grade> grades = gradeService.getStudentGrades(user);
                gradeService.deleteGrades(grades);
                for (Course course : courses) {
                    courseService.RemoveUserFromCourse(course.getId(), id);
                }
              
                userRepository.delete(user);
                
                
            }
            if (user.getUserRole().equals("teacher")) {
                List<Course> courses = courseService.getCoursesByTeacher(user);
                for (Course course : courses) {
                    courseService.deleteCourse(course.getId());
                }
                userRepository.delete(id);

            }   
    }

}
