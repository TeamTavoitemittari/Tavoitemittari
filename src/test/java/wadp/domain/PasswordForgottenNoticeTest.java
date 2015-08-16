package wadp.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PasswordForgottenNoticeTest {

    private PasswordForgottenNotice notice;
    private User user;


    @Before
    public void setUp(){
        this.user = new User();
        this.notice = new PasswordForgottenNotice(user);
    }

    @Test
    public void testUser(){
        assertEquals(user, notice.getUser());
        User user2 = new User();
        notice.setUser(user2);
        assertEquals(user2, notice.getUser());
    }


}
