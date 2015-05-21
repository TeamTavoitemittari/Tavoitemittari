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
public class Student extends AbstractPersistable<Long> {
    
    /// no username since students will be identifyed by email address(?)
    
    
      //  @Length(min = 5, max = 15)
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;
    
    @NotBlank
    private String name;
  
    @NotBlank
    @Length(min = 5, max = 20)
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
}
