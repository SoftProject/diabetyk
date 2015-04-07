<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage title="Logowanie">

    <jsp:attribute name="html_extra_header">
        <link href="<c:url value="/resources/css/login.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/registation.css"/>" rel="stylesheet">
    </jsp:attribute>

    <jsp:body>

        <div class="container">
            
                <div class="col-md-3" ></div>
                <div class="col-md-6"> 
                    
                        <form class="form-signin" role="form" action="<c:url value='/j_spring_security_check' />" method="post">
                            <c:if test="${param.error != null}">
                                <div id="loginErrorMsg" class="alert alert-danger">
                                    Błędny login lub hasło
                                </div>
                            </c:if>

                            <t:message/>
                            <div> 
                                <center><img src="<c:url value="/resources/img/diabetyk.png"/>" alt=""/></center>

                                <h2 class="form-signin-heading" style="text-align: center">Zaloguj się</h2>

                            </div> 

                            <div class="form-group">
                                <div class="controls">
                                    <label for="inputEmail" class="sr-only">Użytkownik</label>
                                    <input type="text" name="username" id="inputEmail" class="form-control" placeholder="Użytkownik"
                                           required autofocus>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="controls">
                                    <label for="inputPassword" class="sr-only">Hasło</label>
                                    <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Hasło"
                                           required>
                                </div>
                            </div>

                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="remember"> Zapamiętaj mnie
                                </label>
                            </div>

                            <div class="checkbox">
                                <a href="<c:url value="/app/resetPassword/form"/>">Zapomniałem hasło</a>
                            </div>

                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                            <button class="btn btn-lg btn-primary btn-block" type="submit">Zaloguj</button>
                        </form>
                        <div class="form-links">
                            <a href="/diabetyk-web/" style="color: black">Wróć do głównej</a>
                        </div>
                </div>
            </div>
     
    </jsp:body>

</t:genericpage>
