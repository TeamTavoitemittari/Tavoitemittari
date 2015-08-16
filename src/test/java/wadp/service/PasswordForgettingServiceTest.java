package wadp.service;


import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import wadp.Application;
import wadp.domain.User;
import wadp.repository.ForgottenNoticeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PasswordForgettingServiceTest {

    @Autowired
    private ForgottenNoticeRepository repository;
    @Autowired
    private PasswordForgettingService passwordService;

    @Autowired
    private UserService userService;

    private User user;

    @Before
    public void setUp(){
        this.user = userService.createUser("keijo@gmail.com","keijo123","Keijo Matinpoika","student");
    }


    @Test
    public void passwordForgottenReportingForSpecificUser(){
        assertEquals(null, repository.findByUser(user));
        passwordService.reportPasswordForgotten(user);
        assertEquals(user, repository.findByUser(user).getUser());

    }

    @Test
    public void hasPasswordForgottenStatus(){
        assertFalse(passwordService.userHasForgottenPassword(user));
        passwordService.reportPasswordForgotten(user);
        assertTrue(passwordService.userHasForgottenPassword(user));
    }

    @Test
    public void deletingForgottenPasswordNotice(){
        passwordService.reportPasswordForgotten(user);
        assertEquals(user, repository.findByUser(user).getUser());
        passwordService.deleteForgottenPasswordForUser(user);
        assertEquals(null, repository.findByUser(user));

    }

}
