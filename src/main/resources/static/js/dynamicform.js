      
$(document).ready(function() {
   
    addGoal1();
    addGoal2();
    addGoal3();
  
  
  
   $("#addGoal1").click();
   $("#addGoal2").click();
   $("#addGoal3").click(); 
  


// asteikko 9-10

  function addGoal1(){
    var maxFields      = 10; 
    var wrapper         = $(".goalsWrap1"); 
    
    
    var goalCounter= 0; 
    $("#addGoal1").click(function(e){ 
        e.preventDefault();
        if(goalCounter < maxFields){ 
           
            var displayCurrent=goalCounter+1;
            $(wrapper).append('<div id="goal1'+goalCounter+'" class="panel panel-primary">\n\
                                    <div class="panel-body">\n\
                                        <label for="text">Tavoitteen ' + displayCurrent + ' nimi</label>\n\
                                        <input type="text" name="gradeLevels[0].goals[' + goalCounter + '].name" class="form-control" id="gradeLevels[0].goals[' + goalCounter + '].name"/>\n\
                                        <div id="goal1'+goalCounter+'skills">\n\
                                        </div>\n\
                                        <a href="#" id="addSkill1'+goalCounter+'">[Lisää taitoja]</a>\n\
                                        <a href="#" id="removeSkill1'+goalCounter+'">[Vähennä taitoja]</a></br><br>\n\
                                    </div>\n\
                                </div>'); 
            addSkill1(goalCounter);
            goalCounter++;
        }
    });
    $("#removeGoal1").click(function(e){ 
    
     e.preventDefault();
       
          
     if (goalCounter > 1){
       var remIndex = goalCounter-1
       $("#goal1"+remIndex).remove(); 

          goalCounter--;
       
     } 

    })
  }

   function addSkill1(goalIndex){
    var maxFields      = 10; 
    var wrapper         = $("#goal1"+goalIndex+"skills"); 
    var add             = $("#addSkill1"+goalIndex); 
    var remove          = $("#removeSkill1"+goalIndex); 
   
    var skillCounter= 0; 
    $(add).click(function(e){ 
        e.preventDefault();
        if(skillCounter < maxFields){ 
            var displayCurrentSkill=skillCounter+1;
            var displayCurrentGoal=goalIndex+1
            $(wrapper).append('<div class="panel-body" id="skill1'+skillCounter+''+goalIndex+'"><label for="text">Tavoitteen ' + displayCurrentGoal + '. taito numero ' + displayCurrentSkill + '.</label> <input type="text" name="gradeLevels[0].goals[' + goalIndex + '].skills[' + skillCounter + '].description" class="form-control" id="gradeLevels[0].goals[' + goalIndex + '].skills[' + skillCounter + '].description"/><label for="text">Tavoitteen ' + displayCurrentGoal + '. taitoon numero ' + displayCurrentSkill + '. liittyvät tehtävät</label><textArea name="gradeLevels[0].goals[' + goalIndex + '].skills[' + skillCounter + '].exercises[0].description" class="form-control" id="gradeLevels[0].goals[' + goalIndex + '].skills[' + skillCounter + '].exercises[0].description"></textArea></div>'); 
            skillCounter++; 
        }
    });
   
    $(remove).click(function(e){ 
    
        e.preventDefault();
       
          
      if (skillCounter >0){
       var remIndex = skillCounter-1
       $("#skill1"+remIndex+goalIndex).remove(); 

          skillCounter--;
       
     }  

     
    })   
    
  }
  
  
  // asteikko 7-8

  function addGoal2(){
    var maxFields      = 10; 
    var wrapper         = $(".goalsWrap2"); 
    
    
    var goalCounter= 0; 
    $("#addGoal2").click(function(e){ 
        e.preventDefault();
        if(goalCounter < maxFields){ 
           
            var displayCurrent=goalCounter+1;
            $(wrapper).append('<div  id="goal1'+goalCounter+'"class="panel panel-primary">\n\
                                    <div class="panel-body">\n\
                                        <label for="text">Tavoitteen ' + displayCurrent + ' nimi</label>\n\
                                        <input type="text" name="gradeLevels[1].goals[' + goalCounter + '].name" class="form-control" id="gradeLevels[1].goals[' + goalCounter + '].name"/>\n\
                                        <div id="goal2'+goalCounter+'skills">\n\
                                        </div>\n\
                                        <a href="#" id="addSkill2'+goalCounter+'">[Lisää taitoja]</a>\n\
                                        <a href="#" id="removeSkill2'+goalCounter+'">[Vähennä taitoja]</a></br><br>\n\
                                    </div>\n\
                                </div>'); 
            addSkill2(goalCounter);
            goalCounter++;
        }
    });
    $("#removeGoal2").click(function(e){ 
    
     e.preventDefault();
       
          
     if (goalCounter > 1){
       var remIndex = goalCounter-1
       $("#goal2"+remIndex).remove(); 

          goalCounter--;
       
     } 

    })
  }

   function addSkill2(goalIndex){
    var maxFields      = 10; 
    var wrapper         = $("#goal2"+goalIndex+"skills"); 
    var add             = $("#addSkill2"+goalIndex); 
    var remove          = $("#removeSkill2"+goalIndex); 
   
    var skillCounter= 0; 
    $(add).click(function(e){ 
        e.preventDefault();
        if(skillCounter < maxFields){ 
            var displayCurrentSkill=skillCounter+1;
            var displayCurrentGoal=goalIndex+1
            $(wrapper).append('<div class="panel-body" id="skill2'+skillCounter+''+goalIndex+'"><label for="text">Tavoitteen ' + displayCurrentGoal + '. taito numero ' + displayCurrentSkill + '.</label> <input type="text" name="gradeLevels[1].goals[' + goalIndex + '].skills[' + skillCounter + '].description" class="form-control" id="gradeLevels[1].goals[' + goalIndex + '].skills[' + skillCounter + '].description"/><label for="text">Tavoitteen ' + displayCurrentGoal + '. taitoon numero ' + displayCurrentSkill + '. liittyvät tehtävät</label><textArea name="gradeLevels[1].goals[' + goalIndex + '].skills[' + skillCounter + '].exercises[0].description" class="form-control" id="gradeLevels[1].goals[' + goalIndex + '].skills[' + skillCounter + '].exercises[0].description"></textArea></div>'); 
            skillCounter++; 
        }
    });
   
    $(remove).click(function(e){ 
    
        e.preventDefault();
       
          
      if (skillCounter > 0){
       var remIndex = skillCounter-1
       $("#skill2"+remIndex+goalIndex).remove(); 

          skillCounter--;
       
     }  

     
    })   
    
  }

  // asteikko 5-6

  function addGoal3(){
    var maxFields      = 10; 
    var wrapper         = $(".goalsWrap3"); 
    
    
    var goalCounter= 0; 
    $("#addGoal3").click(function(e){ 
        e.preventDefault();
        if(goalCounter < maxFields){ 
           
            var displayCurrent=goalCounter+1;
            $(wrapper).append('<div  id="goal1'+goalCounter+'" class="panel panel-primary">\n\
                                    <div class="panel-body">\n\
                                        <label for="text">Tavoitteen ' + displayCurrent + ' nimi</label>\n\
                                        <input type="text" name="gradeLevels[2].goals[' + goalCounter + '].name" class="form-control" id="gradeLevels[2].goals[' + goalCounter + '].name"/>\n\
                                        <div id="goal3'+goalCounter+'skills">\n\
                                        </div>\n\
                                        <a href="#" id="addSkill3'+goalCounter+'">[Lisää taitoja]</a>\n\
                                        <a href="#" id="removeSkill3'+goalCounter+'">[Vähennä taitoja]</a></br><br>\n\
                                    </div>\n\
                                </div>'); 
            addSkill3(goalCounter);
            goalCounter++;
        }
    });
    $("#removeGoal3").click(function(e){ 
    
     e.preventDefault();
       
          
     if (goalCounter > 1){
       var remIndex = goalCounter-1
       $("#goal3"+remIndex).remove(); 
       

          goalCounter--;
       
     } 

    })
  }

   function addSkill3(goalIndex){
    var maxFields      = 10; 
    var wrapper         = $("#goal3"+goalIndex+"skills"); 
    var add             = $("#addSkill3"+goalIndex); 
    var remove          = $("#removeSkill3"+goalIndex); 
   
    var skillCounter= 0; 
    $(add).click(function(e){ 
        e.preventDefault();
        if(skillCounter < maxFields){ 
            var displayCurrentSkill=skillCounter+1;
            var displayCurrentGoal=goalIndex+1
            $(wrapper).append('<div class="panel-body" id="skill3'+skillCounter+''+goalIndex+'"><label for="text">Tavoitteen ' + displayCurrentGoal + '. taito numero ' + displayCurrentSkill + '.</label> <input type="text" name="gradeLevels[2].goals[' + goalIndex + '].skills[' + skillCounter + '].description" class="form-control" id="gradeLevels[2].goals[' + goalIndex + '].skills[' + skillCounter + '].description"/><label for="text">Tavoitteen ' + displayCurrentGoal + '. taitoon numero ' + displayCurrentSkill + '. liittyvät tehtävät</label><textArea name="gradeLevels[2].goals[' + goalIndex + '].skills[' + skillCounter + '].exercises[0].description" class="form-control" id="gradeLevels[2].goals[' + goalIndex + '].skills[' + skillCounter + '].exercises[0].description"></textArea></div>'); 
            skillCounter++; 
        }
    });
   
    $(remove).click(function(e){ 
    
        e.preventDefault();
       
          
      if (skillCounter > 0){
       var remIndex = skillCounter-1
       $("#skill3"+remIndex+goalIndex).remove(); 
       

          skillCounter--;
       
     }  

     
    })   
    
  }



});