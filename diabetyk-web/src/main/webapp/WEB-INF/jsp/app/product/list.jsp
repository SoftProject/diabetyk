<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage title="Lista produktów">

    <jsp:attribute name="html_extra_header">
        <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
        <script type="text/javascript" src="//cdn.datatables.net/1.10.0/js/jquery.dataTables.js"></script>
        <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.0/css/jquery.dataTables.css"/>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <div class="navbar navbar-inverse">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
                        </button> 
                    </div>
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav navbar-right"> 
                            <li>
                                <a href="/diabetyk-web/app/" ><span class="glyphicon glyphicon-th-large"></span> Gł&oacute;wna</a>
                            </li>
                            <li>
                                <a  href='<c:url value="/app/auth/logout" />' > <span class="glyphicon glyphicon-off"></span> Wyloguj się</a>
                            </li> 
                        </ul>
                    </div>
                </div>
            </div> 
        </div>
        <div class="container">    

            <div class="col-md-12 jumbotron" style="padding-top: 0"> 

                <div class="page-header">
                    <h2>Lista produktów</h2>
                </div>

                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h3 class="panel-title">Lista produktów</h3>
                    </div>
                    <div class="panel-body">
                        <table id="products" class="display" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Nazwa</th>
                                    <th>Zamoderowany</th>
                                    <th></th>
                                    <th></th>
                                </tr>
                            </thead>

                        </table>
                    </div>
                </div>

                <div class="container">

                    <center>
                        <button  type="button" class="btn btn-success btn-lg" onclick="document.location.href = '<c:url value="/app/product/add" />';">Dodaj produkt
                        </button>

                    </center>
                </div>

            </div> 



            <p class="text-center ">Copyright © 2015, <a target="_blank" href="http://www.soft-project.pl ">soft-project.pl</a></p>

        </div>   



        <script>
            $(document).ready(function () {
                var table = $('#products').DataTable({
                    "ajax": {
                        "url": "<c:url value="/app/product/list/json" />",
                        "dataSrc": ""
                    },
                    "oLanguage": {
                        "sEmptyTable": "Brak produktów",
                        "sInfo": "_START_ do _END_ z _TOTAL_ produktów",
                        "sInfoEmpty": "Brak rekordów",
                        "sSearch": "Szukaj:",
                        "sLengthMenu": "Pokaż _MENU_ rekordów",
                        "oPaginate": {
                            "sFirst": "Pierwsza strona",
                            "sLast": "Ostatnia strona",
                            "sNext": "Następna strona",
                            "sPrevious": "Poprzednia strona"
                        }
                    },
                    "columns": [
                        {"data": "id"},
                        {"data": "name"},
                        {"data": "isModerated"},
                        {"defaultContent": "<button class='details'>Szczegóły</button>", "orderable": false},
                        {"defaultContent": "<button class='edit'>Edytuj</button>", "orderable": false}
                    ],
                    "fnCreatedRow": function (nRow, aData, iDataIndex) {
                        if (aData.editable == "no") {
                            $('.edit', nRow).hide();
                        }
                    }
                });
                $('#products tbody').on('click', '.details', function () {
                    var data = table.row($(this).parents('tr')).data();
                    document.location.href = baseUrl + 'app/product/' + data.id + "/details";
                });
                $('#products tbody').on('click', '.edit', function () {
                    var data = table.row($(this).parents('tr')).data();
                    document.location.href = baseUrl + 'app/product/' + data.id + "/edit";
                });
            });
        </script>

    </jsp:body>

</t:genericpage>
