package wadp.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import wadp.Application;
import wadp.domain.Course;
import wadp.repository.CourseRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class IndexControllerTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private IndexController indexController;

    private Model model;

    @Before
    public void setup() {
        model = new Model() {

            @Override
            public Model addAttribute(String string, Object o) {
                return null;
            }

            @Override
            public Model addAttribute(Object o) {
                return null;
            }

            @Override
            public Model addAllAttributes(Collection<?> clctn) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Model addAllAttributes(Map<String, ?> map) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Model mergeAttributes(Map<String, ?> map) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public boolean containsAttribute(String string) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Map<String, Object> asMap() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

    }

    @Test
    public void testShowIndex() {
        String index = indexController.showIndex();
        List<Course> courses = courseRepository.findAll();
        assertTrue(courses.size()>0);
        assertEquals("index", index);
        
    }

    @Test
    public void testShowPrototype() {
        assertEquals("courses", indexController.showPrototype());
    }

    @Test
    public void testShowWelcomePage() {
        assertEquals("welcome", indexController.showWelcomePage(model));
    }

    @Test
    public void testGetCourseDemo() {
        assertEquals("course", indexController.getCourseDemo(null));
    }

}

