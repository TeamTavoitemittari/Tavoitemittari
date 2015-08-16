$(document).ready(function () {
    jQuery.expr[":"].Contains = jQuery.expr.createPseudo(function(arg) {
        return function( elem ) {
            return jQuery(elem).text().toUpperCase().indexOf(arg.toUpperCase()) >= 0;
        };
    });

    searchFunctionality("#ownsearch", "#owncourses");
    searchFunctionality("#allsearch", "#allcourses");

    function searchFunctionality(inputField, searchArea) {
        $(inputField).keyup(function(event) {
            var search = $(inputField).val();
            $(searchArea + " .row").show();
            $(searchArea + " .row:not(:Contains(" +search+ "))").hide();
            event.preventDefault();
        });
    }




});