/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wadp.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author OP
 */
@Entity
public class User extends AbstractPersistable<Long> {
    
    /// no username since students will be identifyed by email address(?)
     
    @Column(unique = true)
    private String email;    
   ///  teacher or a student 
    private String userRole;    
    private String name;   
    private String password;

    @OneToMany
    private List<Course> courses;
    
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

     public List<Course> getCourses() {
        return this.courses;
                }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
    
}
