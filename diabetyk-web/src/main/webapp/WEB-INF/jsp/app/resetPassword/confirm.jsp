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

            <c:url value="/app/resetPassword/confirmAction" var="action"/>
            <form:form cssclass="form-hirizontal" action="${action}" method="post"
                       modelAttribute="resetPasswordForm" id="resetPasswordForm">
                <fieldset>

                    <div class="form-group">
                        <label class="control-label" for="password">Hasło</label>

                        <div class="controls">
                            <form:password path="password" id="password" placeholder="Hasło"
                                           cssClass="form-control"/>

                            <p class="error"><form:errors path="password"/></p>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label" for="password2">Hasło (potwierdzenie)</label>

                        <div class="controls">
                            <form:password path="password2" id="password2" placeholder="Hasło (potwierdzenie)"
                                           cssClass="form-control"/>

                            <p class="error"><form:errors path="password2"/></p>
                        </div>
                    </div>

                    <form:hidden path="token"/>

                    <div class="form-group">
                        <div class="controls">
                            <input type="submit" class="btn btn-success" value="Zapisz"/>
                        </div>
                    </div>
                </fieldset>
            </form:form>

        </div>

    </jsp:body>

</t:genericpage>
