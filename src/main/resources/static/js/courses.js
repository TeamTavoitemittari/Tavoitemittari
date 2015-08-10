$(document).ready(function () {
    jQuery.expr[":"].Contains = jQuery.expr.createPseudo(function(arg) {
        return function( elem ) {
            return jQuery(elem).text().toUpperCase().indexOf(arg.toUpperCase()) >= 0;
        };
    });

    $("#testbutton").click(function() {
        var search = $("#testinput").val();
        $("#allcourses .row:not(:Contains(" +search+ "))").hide();
    })


});