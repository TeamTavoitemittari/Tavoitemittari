
package wadp.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Table(name = "User_table")
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
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
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
    
    public boolean passwordEquals(String plaintextPassword){
        return BCrypt.checkpw(plaintextPassword, password);
    }
    
}
