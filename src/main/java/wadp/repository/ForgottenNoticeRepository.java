
package wadp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wadp.domain.PasswordForgottenNotice;
import wadp.domain.User;

public interface ForgottenNoticeRepository extends JpaRepository<PasswordForgottenNotice, Long> {
    public PasswordForgottenNotice findByUser(User user);
}