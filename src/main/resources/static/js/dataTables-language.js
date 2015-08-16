$(document).ready(function () {
    $('#example').DataTable({
        "language": {
            "lengthMenu": "Näytä _MENU_ hakukohdetta per sivu",
            "zeroRecords": "Mitään ei löytynyt",
            "info": "Sivu _PAGE_ / _PAGES_",
            "infoEmpty": "Haku ei vastaa taulun sisältöä",
            "infoFiltered": "(haku rajattu _MAX_ hakukohteesta)",
            "search": "Haku",
            "paginate": {
                "first": "Ensimmäinen",
                "last": "Viimeinen",
                "next": "Seuraava",
                "previous": "Edellinen"
            }
        }
    });
});