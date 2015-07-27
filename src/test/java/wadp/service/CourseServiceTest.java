
package wadp.service;

import java.util.ArrayList;
import java.util.List;
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
import wadp.Application;
import wadp.domain.Course;
import wadp.domain.Goal;
import wadp.domain.GradeLevel;
import wadp.domain.Skill;
import wadp.domain.User;
import wadp.repository.CourseRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@DirtiesContext (classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CourseServiceTest {
    
    @Autowired
    private CourseService courseService;
   
    @Autowired
    private CourseRepository courseRepository;
        
    @Autowired
    private UserService userService;


    @Autowired
    private GradeLevelService gradeService;

    @Autowired
    private GoalService goalService;

    @Autowired
    private SkillService skillService;

    @Autowired
    private ProgressService progressService;

    @Autowired
    private CommentService commentService;
    
    private Course course;
    private Course TestCourse;
    

    
    @Before
    public void setUp(){
        
        userService.createUser("testope@a.com", "ope", "Olli Opettaja", "teacher");
        userService.createUser("testoppilas@a.com", "oppilas", "Matti Meikalainen", "student");
        
        TestCourse = new Course();
        TestCourse.setName("Testikurssi");
        TestCourse.setDescription("Kurssilla perehdytään erinäisiin taivankappaleisiina alkaen omasta aurinkokunnastamme"
                + "ja edeten hiljalleen galaksin muihin osiin. Kurssin jälkeen tiedät mitä eroa on mustalla aukolla"
                + "ja valkoisella kääpiöllä sekä tunnistat tähtitaivaalta eri tähtikuviot.");

        GradeLevel level1 = new GradeLevel();
        level1.setGrade("5-6");
        Goal goal1 = new Goal();
        goal1.setName("Tähtikuviot");

        Skill skill1 = new Skill("Oppilas tunnistaa tähtikuvioita", "667, 12a, Käy illalla kotona ulkona ja piirrä kolme valitsemaasi tähtikuviota paperille, Kirjota 200 sanan tiivistelmä kirjan luvusta 6: Meidän galaksimme");

        

        Skill skill2 = new Skill("Oppilas ymmärtää tähtikuvioiden suhteelliset etäisyydet", "758, 827, 100, 220, 12, 16, 16");

        

        ArrayList<Skill> skills1 = new ArrayList<>();
        skills1.add(skill1);
        skills1.add(skill2);

        goal1.setSkills(skills1);
        

        Goal goal2 = new Goal();
        goal2.setName("Painovoima");

        Skill skill3 = new Skill("Oppilas osaa selittää painovoiman vaikutuksen hänen fyysiseen ympäristöönsä", "101b, 76, Katso Cosmos: A Spacetime Odyssey -sarjan seitsemästoista jakso ja kirjoita siitä 200 sanan referaatti, 63");

        

        Skill skill4 = new Skill("Oppilas ymmärtää painovoiman suhteen planeettojen massaan", "758, 827,100,220,12,16,19");

        

        ArrayList<Skill> skills2 = new ArrayList<>();

        skills2.add(skill4);
        skills2.add(skill3);

        goal2.setSkills(skills2);

        

        ArrayList<Goal> goals1 = new ArrayList<Goal>();
        goals1.add(goal1);
        goals1.add(goal2);

        level1.setGoals(goals1);

        

        GradeLevel level2 = new GradeLevel();
        level2.setGrade("7-8");

        Goal goal3 = new Goal();
        goal3.setName("Mustat aukot");

        Skill skill5 = new Skill("Oppilas osaa kuvata mustan aukon syntymisprosessin", "89, 10");

        

        ArrayList<Skill> skillz = new ArrayList<>();
        skillz.add(skill5);
        goal3.setSkills(skillz);

        

        ArrayList<Goal> goals2 = new ArrayList<Goal>();
        goals2.add(goal3);
        level2.setGoals(goals2);
        

        GradeLevel level3 = new GradeLevel();
        level3.setGrade("9-10");

        Goal goal4 = new Goal();
        goal4.setName("Astrofysiikka");

        Skill skill6 = new Skill("Oppilas taitaa astrofysiikan salat", "Laske auringon massa, 893, Lue Carl Saganin Kosmos ja kirjoita siitä neljän sivun referaatti. ");

        

        ArrayList<Skill> skills4 = new ArrayList<Skill>();
        skills4.add(skill6);
        goal4.setSkills(skills4);

        

        ArrayList<Goal> goals4 = new ArrayList<Goal>();
        goals4.add(goal4);

        level3.setGoals(goals4);

        

        ArrayList<GradeLevel> levels = new ArrayList<GradeLevel>();
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);

        TestCourse.setGradeLevels(levels);
        
        User teacher = userService.findUserByEmail("testope@a.com");
        TestCourse.setTeacher(teacher);
        
    }

    @Test
    public void testGetCourses() {
        course = new Course();
        course.setName("todari");
        course.setDescription("todari");
        courseRepository.save(course);
        assertEquals("todari", courseService.getCourses().get(0).getName());
        assertEquals(1, courseService.getCourses().size());
    }

    @Test
    public void testAddCourse() {
        course = new Course();
        course.setName("todari");
        course.setDescription("todari2");
        courseService.addCourse(course);
        
        assertEquals("todari", courseRepository.findAll().get(0).getName());
        assertEquals(1, courseRepository.findAll().size());
    }
    
    @Test
    public void getCourseById() {
        course = new Course();
        course.setName("kurssi1");
        course.setDescription("kurssi taas");
        courseService.addCourse(course);
        Long id = course.getId();
        Course samecourse = courseService.getCourseById(id);
        
        
        assertTrue(samecourse.getName().equals(course.getName()));
        
    }

    @Test
    public void updateCourseWorks(){
        course = new Course();
        List<GradeLevel> gradeLevels = new ArrayList<GradeLevel>();
        GradeLevel l1 = new GradeLevel();
        GradeLevel l2 = new GradeLevel();
        GradeLevel l3 = new GradeLevel();
 
        List<Goal> goals = new ArrayList<Goal>();
        Goal x = new Goal();
        x.setName("xx");
        goals.add(x);
        l1.setGoals(goals);
        
        List<Goal> goals2 = new ArrayList<Goal>();
        Goal x2 = new Goal();
        x2.setName("xx");
        goals2.add(x2);
        l2.setGoals(goals2);
        
        List<Goal> goals3 = new ArrayList<Goal>();
        Goal x3 = new Goal();
        x3.setName("xx");
        goals3.add(x3);
        l3.setGoals(goals3);
        
        gradeLevels.add(l1);
        gradeLevels.add(l2);
        gradeLevels.add(l3);
        
        course.setGradeLevels(gradeLevels);
        course.setName("test");
        course.setDescription("descr");
        long oldCourseId = courseRepository.save(course).getId();

        assertEquals("test", courseService.getCourseById(oldCourseId).getName());

        Course course2 = new Course();

        List<GradeLevel> gradeLevels2 = new ArrayList<GradeLevel>();
        GradeLevel l4 = new GradeLevel();
        GradeLevel l5 = new GradeLevel();
        GradeLevel l6 = new GradeLevel();
        
        List<Goal> goals4 = new ArrayList<Goal>();
        Goal x4 = new Goal();
        x4.setName("xx");
        goals4.add(x4);
        l4.setGoals(goals4);
        
        List<Goal> goals5 = new ArrayList<Goal>();
        Goal x5 = new Goal();
        x5.setName("xx");
        goals5.add(x5);
        l5.setGoals(goals5);
        
        List<Goal> goals6 = new ArrayList<Goal>();
        Goal x6 = new Goal();
        x6.setName("xx");
        goals6.add(x6);
        l6.setGoals(goals6);
        
        gradeLevels2.add(l4);
        gradeLevels2.add(l5);
        gradeLevels2.add(l6);
        course2.setGradeLevels(gradeLevels2);
        course2.setName("newname");
        course2.setDescription("newdescr");

        courseService.updateCourse(course2, oldCourseId);

        assertEquals("newname", courseService.getCourseById(oldCourseId).getName());
        assertEquals("newdescr", courseService.getCourseById(oldCourseId).getDescription());
    }
    
    @Test
    public void JoinCourseWorks(){
        
       courseService.addCourse(TestCourse);
        
         
       User user = userService.findUserByEmail("testoppilas@a.com");
        
       courseService.joinCourse(user, TestCourse);
    
       assertTrue(progressService.getCourseProgressTrackersByCourse(TestCourse).isEmpty()==false);
       assertTrue(progressService.getGradeProgressTrackersByCourse(TestCourse).isEmpty()==false);
       assertTrue(progressService.getGoalProgressTrackersByCourse(TestCourse).isEmpty()==false);
       assertTrue(commentService.getCommentsByCourse(TestCourse).isEmpty()==false);
        
        
    }
    
    @Test
    public void GetUsersCoursesWorks(){
        courseService.addCourse(TestCourse);
        User user = userService.findUserByEmail("testoppilas@a.com");
        assertTrue(courseService.getUsersCourses(user).isEmpty());
        courseService.joinCourse(user,TestCourse);
        List<Course> courses = courseService.getUsersCourses(user);
        assertTrue(courses.get(0).getName().equals("Testikurssi"));
        
    }
    
    @Test
    public void GetCourseStudentsWorks(){
        courseService.addCourse(TestCourse);
        assertTrue(courseService.getCourseStudents(TestCourse).isEmpty());
        User user = userService.findUserByEmail("testoppilas@a.com");
        courseService.joinCourse(user,TestCourse);
        assertTrue(courseService.getCourseStudents(TestCourse).get(0).getName().equals("Matti Meikalainen"));
    }
    
    
    @Test
    public void DeleteCourseWorks(){
        

        
        courseService.addCourse(TestCourse);
        
        
        User user = userService.findUserByEmail("testoppilas@a.com");
        
        courseService.joinCourse(user, TestCourse);

  
     
        
        Long id = TestCourse.getId();
         
        courseService.deleteCourse(id);
        
        ///test that not only course itself but also all related trackers and comments are truly deleted with the course
        
        assertTrue(courseService.getCourseById(id) == null);
        assertTrue(progressService.getCourseProgressTrackersByCourse(TestCourse).isEmpty());
        assertTrue(progressService.getGradeProgressTrackersByCourse(TestCourse).isEmpty());
        assertTrue(progressService.getGoalProgressTrackersByCourse(TestCourse).isEmpty());
        assertTrue(commentService.getCommentsByCourse(TestCourse).isEmpty());
        
  
        
   
    }
    
    @Test
    public void RemoveUserFromCourseWorks(){
        
    courseService.addCourse(TestCourse);
    User user = userService.findUserByEmail("testoppilas@a.com");
    Long CourseId = TestCourse.getId();
    Long UserId = user.getId();
        
    
    courseService.joinCourse(user, TestCourse);
    courseService.RemoveUserFromCourse(CourseId, UserId);
    
    assertTrue(progressService.getCourseProgressTrackersByUserAndCourse(user, TestCourse).isEmpty());
    assertTrue(progressService.getGradeProgressTrackersByUserAndCourse(user,TestCourse).isEmpty());
    assertTrue(progressService.getGoalProgressTrackersByUserAndCourse(user,TestCourse).isEmpty());
    assertTrue(commentService.getCommentsByUserAndCourse(user,TestCourse).isEmpty());
        
        
        
    }
    
}
