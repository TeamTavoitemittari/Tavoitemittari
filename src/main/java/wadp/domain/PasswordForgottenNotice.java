package wadp.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Represents a loss of remembrance for someones password.
 * Passwords are retrieved manually by admins.
 */
@Entity
public class PasswordForgottenNotice extends AbstractPersistable<Long> {

    // The user who has forgotten their password
    @OneToOne
    private User user;

    public PasswordForgottenNotice(){
    }

    public PasswordForgottenNotice(User user){
        this.user = user;
    }

    public User getUser(){
        return this.user;
    }

    public void setUser(User user){
        this.user=user;
    }
}
