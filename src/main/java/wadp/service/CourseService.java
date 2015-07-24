
package wadp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wadp.domain.Comment;
import wadp.domain.Course;
import wadp.domain.CourseProgressTracker;
import wadp.domain.GoalProgressTracker;
import wadp.domain.GradeLevel;
import wadp.domain.GradeProgressTracker;
import wadp.domain.Skill;
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
    
    public List<Course> getCourses(){
        return courseRepository.findAll();
    }

    @Transactional
    public void addCourse(Course course) {
        courseRepository.save(course);
    }
    
    public Course getCourseById(Long id){
        return courseRepository.findOne(id);
    }

    public void sortCourseGrades(Course course) {
        Collections.sort(course.getGradeLevels());
    }
    
    public void sortCourseGoals(Course course) {
        for (GradeLevel level : course.getGradeLevels()){
        Collections.sort(level.getGoals());
        }
    }
    
    @Transactional
    public void updateCourse(Course course, Long courseId){
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
    
        public List<Course> getUsersCourses(User user){
        List<CourseProgressTracker> trackers = progressRepository.findByUser(user);
        List<Course> courses = new ArrayList<Course>();
        
        for (CourseProgressTracker tracker : trackers){
            courses.add(tracker.getCourse());
            
        }
        
        return courses;
        
      }
        
        public List<User> getCourseStudents(Course course){
           List<CourseProgressTracker> trackers = progressRepository.findByCourse(course);
        List<User> users = new ArrayList<User>();
        
        for (CourseProgressTracker tracker : trackers){
            users.add(tracker.getUser());
            
        }
        
        return users;
        }
       
      ///needs work  
      
      public void joinCourse (User user, Course course){
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


        courseRepository.save(course);
        progressService.saveCourseTracker(tracker);    
          
      }  
      
        public List<Course> getCoursesByTeacher(User teacher){
        return courseRepository.findByTeacher(teacher);
    }
        
        
    @Transactional
    public void deleteCourse(Long courseId){
        
        Course course = getCourseById(courseId);
        if (progressService.getProgressByCourse(course).size() > 0) {
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

}
