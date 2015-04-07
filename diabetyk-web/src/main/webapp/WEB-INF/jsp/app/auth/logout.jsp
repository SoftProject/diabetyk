<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage title="Logowanie">

    <jsp:attribute name="html_extra_header"> 
        <link href="<c:url value="/resources/css/logout.css"/>" rel="stylesheet">
    </jsp:attribute>

    <jsp:body>
        <center> 

            <div class="flash" style="max-width: 700px;">

                <div class="page-header">
                    <h2>Dziękujemy za korzystanie z systemu</h2>
                </div>

                <div class="form-group">
                    Naciśnij "Potwierdź" celem wylogowania się.
                </div>

                <div style="height:45px;">
                    <c:url var="logoutValue" value="/app/auth/logout"/>
                    <form action="${logoutValue}" method="POST">
                        <div  style="float: left;">    
                            <button class="btn btn-default btn-lg" style ="border-radius:0; background: #F5F5F5" type="submit">Potwierdź</button>
                        </div> 
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <div style="float: right;">   
                            <button type="button" class="btn btn-default btn-lg" style ="border-radius:0; background: #F5F5F5" onclick="window.history.back();" >Anuluj </button>
                        </div>
                    </form>

                </div>
            </div>

        </center>
    </jsp:body>

</t:genericpage>
