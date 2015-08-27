package wadp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wadp.domain.PasswordForgottenNotice;
import wadp.domain.User;
import wadp.repository.ForgottenNoticeRepository;

import java.util.List;

/**
 * Service that handles notices of forgotten passwords.
 */
@Service
public class PasswordForgettingService {

    @Autowired
    private ForgottenNoticeRepository forgottenNoticeRepository;

    /**
     * Crates a notice of a forgotten password for a specific user.
     * @param user the user who has forgotten their password.
     */
    @Transactional
    public void reportPasswordForgotten(User user){
        PasswordForgottenNotice forgottenNotice = new PasswordForgottenNotice(user);
        forgottenNoticeRepository.save(forgottenNotice);
    }

    /**
     * Returns all forgotten passwords by any user.
     * @return a list of notices.
     */
    public List<PasswordForgottenNotice> getForgottenPasswords(){
        return forgottenNoticeRepository.findAll();
    }

    /**
     * Checks if the user has forgotten their password.
     * @param user
     * @return if they have.
     */
    public boolean userHasForgottenPassword(User user){
        return forgottenNoticeRepository.findByUser(user)!=null;
    }

    /**
     * Deletes a notice of a forgotten password when the password has
     * been returned by the admin.
     * @param user which had reported their forgotten pasword.
     */
    public void deleteForgottenPasswordForUser(User user){
        forgottenNoticeRepository.delete(forgottenNoticeRepository.findByUser(user));
    }

}