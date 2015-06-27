      
$(document).ready(function() {
    
    
    // 3 different gradelevels, 9-10, 7-8 and 5-6
   
    addGoal("0");
    addGoal("1");
    addGoal("2");
  
  
  
   $("#addGoal0").click();
   $("#addGoal1").click();
   $("#addGoal2").click(); 
   
   $("#addSkill00").click();
   $("#addSkill10").click(); 
   $("#addSkill20").click();
    
  




  function addGoal(gradeLevel){
    var maxFields      = 10; 
    var wrapper         = $(".goalsWrap"+gradeLevel); 
    
    
    var goalCounter= 0; 
    $("#addGoal"+gradeLevel).click(function(e){ 
        e.preventDefault();
        if(goalCounter < maxFields){ 
           
            var displayCurrent=goalCounter+1;
            $(wrapper).append('<div id="goal'+gradeLevel+''+goalCounter+'" class="panel panel-primary">\n\
                                    <div class="panel-body">\n\
                                        <label for="text">Tavoitteen ' + displayCurrent + ' nimi</label>\n\
                                        <input type="text" required="required" maxlength="255" name="gradeLevels['+gradeLevel+'].goals[' + goalCounter + '].name" class="form-control" id="gradeLevels['+gradeLevel+'].goals[' + goalCounter + '].name"/>\n\
                                        <div id="goal'+gradeLevel+''+goalCounter+'skills">\n\
                                        </div>\n\
                                        <a href="#" id="addSkill'+gradeLevel+''+goalCounter+'">[Lisää taitoja]</a>\n\
                                        <a href="#" id="removeSkill'+gradeLevel+''+goalCounter+'">[Vähennä taitoja]</a></br><br>\n\
                                    </div>\n\
                                </div>'); 
            addSkill(gradeLevel, goalCounter);
            goalCounter++;
        }
    });
    $("#removeGoal"+gradeLevel).click(function(e){ 
    
     e.preventDefault();
       
          
     if (goalCounter > 1){
       var remIndex = goalCounter-1
       $("#goal"+gradeLevel+remIndex).remove(); 

          goalCounter--;
       
     } 

    })
  }

   function addSkill(gradeLevel, goalIndex){
    var maxFields      = 10; 
    var wrapper         = $("#goal"+gradeLevel+goalIndex+"skills"); 
    var add             = $("#addSkill"+gradeLevel+goalIndex); 
    var remove          = $("#removeSkill"+gradeLevel+goalIndex); 
   
    var skillCounter= 0; 
    $(add).click(function(e){ 
        e.preventDefault();
        if(skillCounter < maxFields){ 
            var displayCurrentSkill=skillCounter+1;
            var displayCurrentGoal=goalIndex+1
            $(wrapper).append('<div class="panel-body" id="skill'+gradeLevel+''+skillCounter+''+goalIndex+'"><label for="text">Tavoitteen ' + displayCurrentGoal + '. taito numero ' + displayCurrentSkill + '.</label> <input type="text" required="required" maxlength="255" name="gradeLevels['+gradeLevel+'].goals[' + goalIndex + '].skills[' + skillCounter + '].description" class="form-control" id="gradeLevels['+gradeLevel+'].goals[' + goalIndex + '].skills[' + skillCounter + '].description"/><label for="text">Tavoitteen ' + displayCurrentGoal + '. taitoon numero ' + displayCurrentSkill + '. liittyvät tehtävät</label><textArea required="required" maxlength="2000" name="gradeLevels['+gradeLevel+'].goals[' + goalIndex + '].skills[' + skillCounter + '].exercises[0].description" class="form-control" id="gradeLevels['+gradeLevel+'].goals[' + goalIndex + '].skills[' + skillCounter + '].exercises[0].description"></textArea></div>'); 
            skillCounter++; 
        }
    });
   
    $(remove).click(function(e){ 
    
        e.preventDefault();
       
          
      if (skillCounter >1){
       var remIndex = skillCounter-1
       $("#skill"+gradeLevel+remIndex+goalIndex).remove(); 

          skillCounter--;
       
     }  

     
    })   
    
  }
  
  
  



});