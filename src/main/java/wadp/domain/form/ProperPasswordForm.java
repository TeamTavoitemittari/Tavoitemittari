package wadp.domain.form;

import org.hibernate.validator.constraints.Length;
import wadp.validation.FieldMatch;
import javax.validation.constraints.NotNull;

/**
 * Form object for new user creation. Necessary fields are passed to UserService that will create User object that will
 * be saved to database
 */


@FieldMatch(first = "password", second = "confirmpassword", message = "Salasanojen pitää olla samoja!")

public class ProperPasswordForm {

 

    @NotNull(message="Salasanaa ei voi jättää tyhjäksi!")
    @Length(min=8, message="Salasanan pitää olla ainakin 8 kirjainta!")
    private String password;
    private String confirmpassword;

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


    
    



  
    
    
}
