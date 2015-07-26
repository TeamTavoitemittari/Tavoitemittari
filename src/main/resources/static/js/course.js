function enableEdit(id){

    var field = document.getElementById("comment"+id);
    field.readOnly = false;

    var editButton = document.getElementById("edit"+id);
    editButton.style.visibility = "hidden";

    var saveButton = document.getElementById("save"+id);
    saveButton.style.visibility = "visible";

    var teacherCancelButton = document.getElementById("cancelteacher"+id);
    if(teacherCancelButton != null) {
        teacherCancelButton.style.visibility = "visible";
    }

    var cancelButton = document.getElementById("cancelstudent"+id);
    if(cancelButton != null){
        cancelButton.style.visibility = "visible";
    }

}
