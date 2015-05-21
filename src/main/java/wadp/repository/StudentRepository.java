/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wadp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wadp.domain.Student;

/**
 *
 * @author OP
 */

public interface StudentRepository extends JpaRepository <Student, Long> {
    
  /// no  username so users identified by email address
    
    Student findByEmail(String username);
    
}
