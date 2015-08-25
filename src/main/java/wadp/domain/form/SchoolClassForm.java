package wadp.domain.form;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SchoolClassForm {

    @NotNull(message = "Luokkaa ei voi jättää tyhjäksi!")
    @NotBlank(message="Nimeä ei voi jättää tyhjäksi!")
    @Length(min = 2, max = 4, message = "Luokkatunnuksen pitää olla vähintään 2 ja enintään 4 merkkiä pitkä!")
    private String schoolClass;

    public String getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(String schoolClass) {
        this.schoolClass = schoolClass;
    }
}
