
package wadp.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wadp.domain.Exercise;
import wadp.repository.ExerciseRepository;

@Service
public class ExerciseService {
    
    @Autowired
    private ExerciseRepository exerciseRepository;
    
    public List<Exercise> getExercises(){
        return exerciseRepository.findAll();
    }
    
    @Transactional
    public void addExercise(Exercise exercise){
        exerciseRepository.save(exercise);
    }
    
}
