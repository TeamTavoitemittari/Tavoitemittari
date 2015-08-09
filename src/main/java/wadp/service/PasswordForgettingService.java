package wadp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wadp.domain.PasswordForgottenNotice;
import wadp.domain.User;
import wadp.repository.ForgottenNoticeRepository;

import java.util.List;

@Service
public class PasswordForgettingService {

    @Autowired
    private ForgottenNoticeRepository forgottenNoticeRepository;

    @Transactional
    public void reportPasswordForgotten(User user){
        PasswordForgottenNotice forgottenNotice = new PasswordForgottenNotice(user);
        forgottenNoticeRepository.save(forgottenNotice);
    }

    public List<PasswordForgottenNotice> getForgottenPasswords(){
        return forgottenNoticeRepository.findAll();
    }

    public boolean userHasForgottenPassword(User user){
        return forgottenNoticeRepository.findByUser(user)!=null;
    }

    public void deleteForgottenPasswordForUser(User user){
        forgottenNoticeRepository.delete(forgottenNoticeRepository.findByUser(user));
    }

}