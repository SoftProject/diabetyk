<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage title="Reset hasła">

    <jsp:attribute name="html_extra_header">
        <link href="<c:url value="/resources/css/login.css"/>" rel="stylesheet">
    </jsp:attribute>

    <jsp:body>

        <div class="container">

            <div class="page-header">
                <h1>Resetowanie hasła</h1>
            </div>

            <t:error/>

            <t:message/>

            <c:url value="/app/resetPassword/formAction" var="action"/>
            <form:form cssclass="form-hirizontal" action="${action}" method="post">
                <fieldset>

                    <div class="form-group">
                        <label class="control-label" for="email">Adres e-mail</label>

                        <div class="controls">
                            <input id="email" type="text" name="email" class="form-control"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="controls">
                            <input type="submit" class="btn btn-success" value="Resetuj"/>
                        </div>
                    </div>
                </fieldset>
            </form:form>

        </div>

    </jsp:body>

</t:genericpage>
