<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage title="Logowanie">

    <jsp:body>

        <div class="container">

            <div class="page-header">
                <h1>Zostałeś prawidłowo zalogowany</h1>
            </div>

            <div class="form-group">
                <button class="btn btn-default" id="list"
                        onclick="document.location.href='<c:url value="/app/product/list" />';">
                    Lista produktów
                </button>
                <button class="btn btn-default" id="add"
                        onclick="document.location.href='<c:url value="/app/product/add" />';">
                    Dodaj produkt
                </button>
                <button class="btn btn-default" id="add"
                        onclick="document.location.href='<c:url value="/app/auth/logout" />';">
                    Wyloguj
                </button>
            </div>

        </div>

    </jsp:body>

</t:genericpage>
