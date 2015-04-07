<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage title="Rejestracja">

    <jsp:attribute name="html_extra_header">
        <link href="<c:url value="/resources/css/login.css"/>" rel="stylesheet">
        <link href="<c:url value="/resources/css/registation.css"/>" rel="stylesheet">
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    </jsp:attribute>

    <jsp:body>

        <div class="container">
            <div class="row" id="pwd-container">
                <div class="col-md-2" ></div>
                <div class="col-md-4">
                    <section class="login-form">

                        <c:url value="/app/registration/formAction" var="action"/>
                        <form:form cssclass="form-hirizontal" action="${action}" method="post"
                                   modelAttribute="registrationForm" id="registrationForm"  >
                            <fieldset>

                                <div>  
                                    <h1 style="text-align: center">Zarejestruj się</h1>
                                </div>
                                <div class="form-group">


                                    <div class="controls">
                                        <form:input path="email" id="email" placeholder="Adres e-mial"
                                                    cssClass="form-control input-lg" type="email"/>

                                        <p class="error"><form:errors path="email"/></p>
                                    </div>
                                </div>

                                <div class="form-group">


                                    <div class="controls">
                                        <form:password path="password" id="password" placeholder="Hasło"
                                                       cssClass="form-control input-lg"/>

                                        <p class="error"><form:errors path="password"/></p>
                                    </div>
                                </div>

                                <div class="form-group">


                                    <div class="controls">
                                        <form:password path="password2" id="password2" placeholder="Hasło (potwierdzenie)"
                                                       cssClass="form-control input-lg"/>

                                        <p class="error"><form:errors path="password2"/></p>
                                    </div>
                                </div>
                                <div class="pwstrength_viewport_progress"></div>
                                <div class="form-group">
                                    <div class="controls">
                                        <input type="submit" class="btn btn-lg btn-primary btn-block" value="Rejestruj"/>
                                    </div>
                                </div>
                            </fieldset>
                        </form:form>
                        <div class="form-links">
                            <a href="/diabetyk-web/" style="color: black">Wróć do głównej</a>
                        </div>

                    </section> 
                </div>
                <div class="col-md-4">
                    <section class="login-form">

                        <div class="col-xs-10" style="margin-left: 20px;">
                            <h2 class="lead">Załóż <span class="text-success">darmowe</span> 
                                konto, a będziesz miał(a) możliwość:</h2>
                            <ul class="list-unstyled" style="line-height: 3">
                                <li><span class="fa fa-check text-success"></span> Ułożyć dietę do swoich potrzeb</li>
                                <li><span class="fa fa-check text-success"></span> Dodawać swoje produkty</li>
                                <li><span class="fa fa-check text-success"></span> Przeglądać produkty innych</li>
                                <li><span class="fa fa-check text-success"></span> Oceniać</li>
                                <li><span class="fa fa-check text-success"></span> Komentować</li>
                            </ul>
                        </div>
                    </section> 
                </div> 
            </div>
        </div>

    </jsp:body>

</t:genericpage>
