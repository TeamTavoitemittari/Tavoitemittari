package wadp.domain.form;


import org.hibernate.validator.constraints.NotBlank;


import javax.validation.constraints.NotNull;

/**
 * Form object for new user creation. Necessary fields are passed to UserService that will create User object that will
 * be saved to database
 */



public class CourseForm {

    
    @NotNull(message="Nimeä ei voi jättää tyhjäksi!")
    @NotBlank(message="Nimeä ei voi jättää tyhjäksi! !")
    private String name;

    private String description;

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription(){
        return description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
}
