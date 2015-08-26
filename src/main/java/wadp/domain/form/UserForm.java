package wadp.domain.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import wadp.validation.FieldMatch;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Email;

/**
 * Form object for new user creation. Necessary fields are passed to UserService that will create User object that will
 * be saved to database
 */


@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmpassword", message = "Varmista että kirjoitit salasanat oikein!"),
        @FieldMatch(first = "email", second = "confirmemail", message = "Varmista että kirjoitit sähköpostiosoitteesi oikein!")})

public class UserForm {

    @NotNull(message="Sähköpostia ei voi jättää tyhjäksi!")
    @NotBlank(message="Sähköpostia ei voi jättää tyhjäksi!")
    @Email(message="Sähköpostiosoitteen pitää olla validi!")
    private String email;
    
    @NotNull(message="Nimeä ei voi jättää tyhjäksi!")
    @NotBlank(message="Nimeä ei voi jättää tyhjäksi!")
    private String name;

    @NotNull(message="Salasanaa ei voi jättää tyhjäksi!")
    @Length(min=8, message="Salasanan pitää olla ainakin 8 kirjainta!")
    private String password;
    private String userRole;
    private String confirmpassword;
    private String confirmemail;

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmemail() {
        return confirmemail;
    }

    public void setConfirmemail(String confirmemail) {
        this.confirmemail = confirmemail;
    }
    
    
    
    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
