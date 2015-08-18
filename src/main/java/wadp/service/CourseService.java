package wadp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wadp.domain.Comment;
import wadp.domain.Course;
import wadp.domain.CourseProgressTracker;
import wadp.domain.Goal;
import wadp.domain.GoalProgressTracker;
import wadp.domain.GradeLevel;
import wadp.domain.GradeProgressTracker;
import wadp.domain.Skill;
import wadp.domain.Status;
import wadp.domain.User;
import wadp.repository.CourseProgressRepository;
import wadp.repository.CourseRepository;
import wadp.repository.GradeLevelRepository;

@Service
public class CourseService {

    @Autowired
    private CourseProgressRepository progressRepository;

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private GradeLevelRepository gradeLevelRepository;

    @Autowired
    private ProgressService progressService;

    @Autowired
    private CommentService commentService;
    
    @Autowired 
    GradeLevelService gradeLevelService;
    
    @Autowired
    GoalService goalService;
    
    @Autowired
    SkillService skillService;

    @Autowired
    private UserService userService;

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }
     public List<Course> getCoursesInUse() {
        return courseRepository.findByInUse(true);
    }

    @Transactional
    public void addCourse(Course course) {
        courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        return courseRepository.findOne(id);
    }

    public void sortCourseGrades(Course course) {
        Collections.sort(course.getGradeLevels());
    }

    public void sortCourseGoals(Course course) {
        for (GradeLevel level : course.getGradeLevels()) {
            Collections.sort(level.getGoals());
        }
    }

    @Transactional
    public void updateCourse(Course course, Long courseId) {
        Course courseToBeUpdated = courseRepository.findOne(courseId);
        courseToBeUpdated.setName(course.getName());
        courseToBeUpdated.setDescription(course.getDescription());

        Long OldGradeLevel1Id = courseToBeUpdated.getGradeLevels().get(0).getId();
        Long OldGradeLevel2Id = courseToBeUpdated.getGradeLevels().get(1).getId();
        Long OldGradeLevel3Id = courseToBeUpdated.getGradeLevels().get(2).getId();

        courseToBeUpdated.setGradeLevels(course.getGradeLevels());

        gradeLevelRepository.delete(OldGradeLevel1Id);
        gradeLevelRepository.delete(OldGradeLevel2Id);
        gradeLevelRepository.delete(OldGradeLevel3Id);
    }
    
   public List<Course> getUsersCoursesWithTeacher(User user, User teacher) {
        List<CourseProgressTracker> trackers = progressRepository.findByUserAndCourse_Teacher(user, teacher);
        List<Course> courses = new ArrayList<Course>();

        for (CourseProgressTracker tracker : trackers) {
            courses.add(tracker.getCourse());

        }

        return courses;

    }
    public List<Course> getUsersUncompletedCourses(User user) {
        List<CourseProgressTracker> trackers = progressRepository.findByUser(user);
        List<Course> courses = new ArrayList<Course>();

        for (CourseProgressTracker tracker : trackers) {
            if(tracker.getCompleted()==false){
            courses.add(tracker.getCourse());
            }
        }

        return courses;

    }
    
      public List<Course> getUsersCourses(User user) {
        List<CourseProgressTracker> trackers = progressRepository.findByUser(user);
        List<Course> courses = new ArrayList<Course>();

        for (CourseProgressTracker tracker : trackers) {
            
            courses.add(tracker.getCourse());
            
        }

        return courses;

    }

    public List<User> getCourseStudents(Course course) {
        List<CourseProgressTracker> trackers = progressRepository.findByCourse(course);
        List<User> users = new ArrayList<>();

        for (CourseProgressTracker tracker : trackers) {
            users.add(tracker.getUser());

        }

        return users;
    }

      ///needs work  
    public void joinCourse(User user, Course course) {
        if (course.getInUse()==true){
         CourseProgressTracker tracker = new CourseProgressTracker(user, course);

         for (GradeProgressTracker gradeTracker : tracker.getGradeLevels().values()) {
            for (GoalProgressTracker goalTracker : gradeTracker.getGoals().values()) {
                progressService.saveGoalTracker(goalTracker);
                HashMap<Skill, Comment> comments = new HashMap<Skill, Comment>();
                for (Skill skill : goalTracker.getSkills().keySet()) {
                    Comment comment = commentService.addComment(skill, user, course);
                    comments.put(skill, comment);
                }
                goalTracker.setComments(comments);
            }
            progressService.saveGradeTracker(gradeTracker);
         }

         progressService.saveCourseTracker(tracker);
        }
    }

    public List<Course> getCoursesByTeacher(User teacher) {
        return courseRepository.findByTeacher(teacher);
    }
    
    public List<Course> getCoursesNotInUseByTeacher(User teacher) {
        Boolean inUse=false;
        return courseRepository.findByTeacherAndInUse(teacher, inUse);
    }
    
    public List<Course> getCoursesInUseByTeacher(User teacher) {
        Boolean inUse=true;
        return courseRepository.findByTeacherAndInUse(teacher, inUse);
    }
       
    @Transactional
    public void publishCourse(Long courseId) {

    Course course = getCourseById(courseId);
    course.setInUse(true);
    }    

    @Transactional
    public void deleteCourse(Long courseId) {

        Course course = getCourseById(courseId);
        if (progressService.getCourseProgressTrackersByCourse(course).size() > 0) {
            List<CourseProgressTracker> CourseProgressTrackers = progressService.getCourseProgressTrackersByCourse(course);
            List<GradeProgressTracker> GradeProgressTrackers = progressService.getGradeProgressTrackersByCourse(course);
            List<Comment> comments = commentService.getCommentsByCourse(course);
            List<GoalProgressTracker> GoalProgressTrackers = progressService.getGoalProgressTrackersByCourse(course);

            progressService.deleteCourseProgressTrackers(CourseProgressTrackers);
            progressService.deleteGradeProgressTrackers(GradeProgressTrackers);
            commentService.deleteComments(comments);
            progressService.deleteGoalProgressTrackers(GoalProgressTrackers);
        }
        courseRepository.delete(getCourseById(courseId));

    }
    @Transactional
    public void RemoveUserFromCourse(Long courseId, Long UserId) {
        Course course = getCourseById(courseId);
        User user = userService.findById(UserId);

        List<CourseProgressTracker> courseProgressTrackers = progressService.getCourseProgressTrackersByUserAndCourse(user, course);
        List<GradeProgressTracker> gradeProgressTrackers = progressService.getGradeProgressTrackersByUserAndCourse(user, course);
        List<Comment> comments = commentService.getCommentsByUserAndCourse(user, course);
        List<GoalProgressTracker> goalProgressTrackers = progressService.getGoalProgressTrackersByUserAndCourse(user, course);

        progressService.deleteCourseProgressTrackers(courseProgressTrackers);
        progressService.deleteGradeProgressTrackers(gradeProgressTrackers);
        progressService.deleteGoalProgressTrackers(goalProgressTrackers);
        commentService.deleteComments(comments);

    }
    
      public Map<Long, Integer> GetStudentCountsForTeachersEachCourseAsMap(User teacher, List<Course> teachersCourses) {
        Map<Long, Integer> count = new HashMap<Long, Integer>();
        for (Course course : teachersCourses) {
             count.put(course.getId(), progressService.getCourseProgressTrackersByCourse(course).size());
       }
       return count;
    }
      @Transactional
    public void createDummyCourse() {


        Course course = new Course();
        course.setInUse(true);
        course.setName("Tähtitiede 1");
        course.setDescription("Kurssilla perehdytään erinäisiin taivankappaleisiina alkaen omasta aurinkokunnastamme"
                + "ja edeten hiljalleen galaksin muihin osiin. Kurssin jälkeen tiedät mitä eroa on mustalla aukolla"
                + "ja valkoisella kääpiöllä sekä tunnistat tähtitaivaalta eri tähtikuviot.");

        GradeLevel level1 = new GradeLevel();
        level1.setGrade("5-6");
        Goal goal1 = new Goal();
        goal1.setName("Tähtikuviot");

        Skill skill1 = new Skill("Oppilas tunnistaa tähtikuvioita", "667, 12a, Käy illalla kotona ulkona ja piirrä kolme valitsemaasi tähtikuviota paperille, Kirjota 200 sanan tiivistelmä kirjan luvusta 6: Meidän galaksimme");

        skillService.addSkill(skill1);

        Skill skill2 = new Skill("Oppilas ymmärtää tähtikuvioiden suhteelliset etäisyydet", "758, 827, 100, 220, 12, 16, 16");

        skillService.addSkill(skill2);

        ArrayList<Skill> skills1 = new ArrayList<>();
        skills1.add(skill1);
        skills1.add(skill2);

        goal1.setSkills(skills1);
        goalService.addGoal(goal1);

        Goal goal2 = new Goal();
        goal2.setName("Painovoima");

        Skill skill3 = new Skill("Oppilas osaa selittää painovoiman vaikutuksen hänen fyysiseen ympäristöönsä", "101b, 76, Katso Cosmos: A Spacetime Odyssey -sarjan seitsemästoista jakso ja kirjoita siitä 200 sanan referaatti, 63");

        skillService.addSkill(skill3);

        Skill skill4 = new Skill("Oppilas ymmärtää painovoiman suhteen planeettojen massaan", "758, 827,100,220,12,16,19");

        skillService.addSkill(skill4);

        ArrayList<Skill> skills2 = new ArrayList<>();

        skills2.add(skill4);
        skills2.add(skill3);

        goal2.setSkills(skills2);

        goalService.addGoal(goal2);

        ArrayList<Goal> goals1 = new ArrayList<Goal>();
        goals1.add(goal1);
        goals1.add(goal2);

        level1.setGoals(goals1);

        gradeLevelService.addGradeLevel(level1);

        GradeLevel level2 = new GradeLevel();
        level2.setGrade("7-8");

        Goal goal3 = new Goal();
        goal3.setName("Mustat aukot");

        Skill skill5 = new Skill("Oppilas osaa kuvata mustan aukon syntymisprosessin", "89, 10");

        skillService.addSkill(skill5);

        ArrayList<Skill> skillz = new ArrayList<>();
        skillz.add(skill5);
        goal3.setSkills(skillz);

        goalService.addGoal(goal3);

        ArrayList<Goal> goals2 = new ArrayList<Goal>();
        goals2.add(goal3);
        level2.setGoals(goals2);
        gradeLevelService.addGradeLevel(level2);

        GradeLevel level3 = new GradeLevel();
        level3.setGrade("9-10");

        Goal goal4 = new Goal();
        goal4.setName("Astrofysiikka");

        Skill skill6 = new Skill("Oppilas taitaa astrofysiikan salat", "Laske auringon massa, 893, Lue Carl Saganin Kosmos ja kirjoita siitä neljän sivun referaatti. ");

        skillService.addSkill(skill6);

        ArrayList<Skill> skills4 = new ArrayList<Skill>();
        skills4.add(skill6);
        goal4.setSkills(skills4);

        goalService.addGoal(goal4);

        ArrayList<Goal> goals4 = new ArrayList<Goal>();
        goals4.add(goal4);

        level3.setGoals(goals4);

        gradeLevelService.addGradeLevel(level3);

        ArrayList<GradeLevel> levels = new ArrayList<GradeLevel>();
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);

        course.setGradeLevels(levels);

        User teacher = userService.findUserByEmail("ope@a.com");
        course.setTeacher(teacher);

        addCourse(course);

        User user = userService.findUserByEmail("oppilas@a.com");

        CourseProgressTracker tracker = new CourseProgressTracker(user, course);

        for (GradeProgressTracker gradeTracker : tracker.getGradeLevels().values()) {
            for (GoalProgressTracker goalTracker : gradeTracker.getGoals().values()) {
                progressService.saveGoalTracker(goalTracker);
                HashMap<Skill, Comment> comments = new HashMap<Skill, Comment>();
                for (Skill skill : goalTracker.getSkills().keySet()) {
                    Comment comment = commentService.addComment(skill, user, course);
                    comments.put(skill, comment);
                }
                goalTracker.setComments(comments);
            }
            progressService.saveGradeTracker(gradeTracker);
        }

        Comment comment1 = tracker.getGradeLevels().get(level3).getGoals().get(goal4).getComments().get(skill6);
        commentService.updateComment(comment1, "Tämä oli hyvin kiinnostava osa-alue"
                + " mutta materiaali oli aika vaikeaselkoista. Video auttoi paljon. /Teemu");

        Comment comment2 = tracker.getGradeLevels().get(level1).getGoals().get(goal1).getComments().get(skill1);
        commentService.updateComment(comment2, "Teit tänään kovasti töitä ja autoit myös muita oppilaita. "
                + "Jatka samaan malliin! /Ope");

        progressService.saveCourseTracker(tracker);

        progressService.updateSkillStatus(tracker, skill6, Status.STUDENT_CONFIRMED);
        progressService.updateSkillStatus(tracker, skill1, Status.TEACHER_CONFIRMED);
        progressService.updateSkillStatus(tracker, skill2, Status.TEACHER_CONFIRMED);

    }

    @Transactional
    public void createDummyCourseWithoutUsers(User teacher) {


        Course course = new Course();
        course.setInUse(true);
        course.setName("Tähtitiede 2");
        course.setDescription("Kurssilla perehdytään erinäisiin taivankappaleisiina alkaen omasta aurinkokunnastamme"
                + "ja edeten hiljalleen galaksin muihin osiin. Kurssin jälkeen tiedät mitä eroa on mustalla aukolla"
                + "ja valkoisella kääpiöllä sekä tunnistat tähtitaivaalta eri tähtikuviot.");

        GradeLevel level1 = new GradeLevel();
        level1.setGrade("5-6");
        Goal goal1 = new Goal();
        goal1.setName("Tähtikuviot");

        Skill skill1 = new Skill("Oppilas tunnistaa tähtikuvioita", "667, 12a, Käy illalla kotona ulkona ja piirrä kolme valitsemaasi tähtikuviota paperille, Kirjota 200 sanan tiivistelmä kirjan luvusta 6: Meidän galaksimme");

        skillService.addSkill(skill1);

        Skill skill2 = new Skill("Oppilas ymmärtää tähtikuvioiden suhteelliset etäisyydet", "758, 827, 100, 220, 12, 16, 16");

        skillService.addSkill(skill2);

        ArrayList<Skill> skills1 = new ArrayList<>();
        skills1.add(skill1);
        skills1.add(skill2);

        goal1.setSkills(skills1);
        goalService.addGoal(goal1);

        Goal goal2 = new Goal();
        goal2.setName("Painovoima");

        Skill skill3 = new Skill("Oppilas osaa selittää painovoiman vaikutuksen hänen fyysiseen ympäristöönsä", "101b, 76, Katso Cosmos: A Spacetime Odyssey -sarjan seitsemästoista jakso ja kirjoita siitä 200 sanan referaatti, 63");

        skillService.addSkill(skill3);

        Skill skill4 = new Skill("Oppilas ymmärtää painovoiman suhteen planeettojen massaan", "758, 827,100,220,12,16,19");

        skillService.addSkill(skill4);

        ArrayList<Skill> skills2 = new ArrayList<>();

        skills2.add(skill4);
        skills2.add(skill3);

        goal2.setSkills(skills2);

        goalService.addGoal(goal2);

        ArrayList<Goal> goals1 = new ArrayList<Goal>();
        goals1.add(goal1);
        goals1.add(goal2);

        level1.setGoals(goals1);

        gradeLevelService.addGradeLevel(level1);

        GradeLevel level2 = new GradeLevel();
        level2.setGrade("7-8");

        Goal goal3 = new Goal();
        goal3.setName("Mustat aukot");

        Skill skill5 = new Skill("Oppilas osaa kuvata mustan aukon syntymisprosessin", "89, 10");

        skillService.addSkill(skill5);

        ArrayList<Skill> skillz = new ArrayList<>();
        skillz.add(skill5);
        goal3.setSkills(skillz);

        goalService.addGoal(goal3);

        ArrayList<Goal> goals2 = new ArrayList<Goal>();
        goals2.add(goal3);
        level2.setGoals(goals2);
        gradeLevelService.addGradeLevel(level2);

        GradeLevel level3 = new GradeLevel();
        level3.setGrade("9-10");

        Goal goal4 = new Goal();
        goal4.setName("Astrofysiikka");

        Skill skill6 = new Skill("Oppilas taitaa astrofysiikan salat", "Laske auringon massa, 893, Lue Carl Saganin Kosmos ja kirjoita siitä neljän sivun referaatti. ");

        skillService.addSkill(skill6);

        ArrayList<Skill> skills4 = new ArrayList<Skill>();
        skills4.add(skill6);
        goal4.setSkills(skills4);

        goalService.addGoal(goal4);

        ArrayList<Goal> goals4 = new ArrayList<Goal>();
        goals4.add(goal4);

        level3.setGoals(goals4);

        gradeLevelService.addGradeLevel(level3);

        ArrayList<GradeLevel> levels = new ArrayList<GradeLevel>();
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);
        
        course.setGradeLevels(levels);
                course.setTeacher(teacher);
        addCourse(course);

    }
    


}
