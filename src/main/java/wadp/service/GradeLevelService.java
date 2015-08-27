
package wadp.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wadp.domain.GradeLevel;
import wadp.repository.GradeLevelRepository;

/**
 * Service that handles grade levels.
 */
@Service
public class GradeLevelService {
    
    @Autowired
    private GradeLevelRepository levelRepository;

    /**
     * Adds new grade level object to the database.
     * @param level the new grade level
     */
    @Transactional
    public void addGradeLevel(GradeLevel level){
        levelRepository.save(level);
    }

    public GradeLevel findGradeLevelById(Long id) {
        return levelRepository.findOne(id);
    }
    
}
