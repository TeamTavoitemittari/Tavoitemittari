
package wadp.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wadp.domain.Goal;
import wadp.repository.GoalRepository;

@Service
public class GoalService {
    
    @Autowired
    private GoalRepository goalRepository;
    
    private List<Goal> findGoals(){
        return goalRepository.findAll();
    }
    
    @Transactional
    public void addGoal(Goal goal){
        goalRepository.save(goal);
    }
    
}
