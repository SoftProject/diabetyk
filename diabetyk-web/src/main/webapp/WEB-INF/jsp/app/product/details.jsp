<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage title="Szczegóły">

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
                        <ul class="nav navbar-nav">
                            <li>
                                <a  href='<c:url value="/app/product/list" />' > <span class="glyphicon glyphicon-arrow-left"></span> Lista produktów</a>

                            </li>
                        </ul>
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

        <t:error/>

        <t:message/>

        <div class="container">

            <div class="panel panel-success"> 
                <div class="panel-heading"><h1>Szczegóły produktu</h1></div>

                <div class="panel-body">
                    <div class="container">

                        <div class="col-md-3">
                            <img src="<c:url value="/resources/img/flowers.png"/>" alt=""/>
                        </div>

                        <div class="col-md-8"  >
                            <table class="table table-striped table-hover ">

                                <tbody>
                                    <tr class="active">
                                        <td> <t:row value="${product.name}" description="Nazwa"/></td>
                                    </tr>
                                    <tr>
                                        <td><t:row value="${product.description}" description="Opis"/></td>
                                    </tr>
                                    <tr class="success">
                                        <td><t:row value="${product.fat}" description="Tłuszcz"/></td>
                                    </tr>
                                    <tr>
                                        <td><t:row value="${product.protein}" description="Białko"/></td>
                                    </tr>
                                    <tr class="warning">
                                        <td><t:row value="${product.carbohydrates}" description="Węglowodany"/></td>
                                    </tr>
                                    <tr>
                                        <td><t:row value="${product.weightForOneWw}" description="Waga na jeden wymiennik węglowodanowy"/></td>
                                    </tr>
                                    <tr class="danger">
                                        <td><t:row value="${product.homeMeasure}" description="Miara domowa"/></td>
                                    </tr>
                                    <tr>
                                        <td><t:row value="${product.wwInPortion}" description="Ilość wymienników węglowodanowych w porcji"/></td>
                                    </tr>
                                    <tr class="info">
                                        <td><t:row value="${product.allergen}" description="Czy jest alergenem?" isBoolean="true"/></td>
                                    </tr>
                                    <tr>
                                        <td><t:row value="${product.glutenFree}" description="Czy jest bezglutenowy?" isBoolean="true"/></td>
                                    </tr>
                                    <tr class="active">
                                        <td><t:row value="${product.categories}" description="Kategorie" isCollection="true"/></td>
                                    </tr>
                                    <tr>
                                        <td><t:row value="${product.saturatedFattyAcids}" description="Nasycone kwasy tłuszczowe"/></td>
                                    </tr>
                                    <tr class="warning">
                                        <td><t:row value="${product.oneSaturatedFattyAcids}" description="Jednonasycone kwasy tłuszczowe"/></td>
                                    </tr>
                                    <tr>
                                        <td><t:row value="${product.multiSaturatedFattyAcids}" description="Wielonasycone kwasy tłuszczowe"/></td>
                                    </tr>
                                    <tr class="danger">
                                        <td><t:row value="${product.cholesterol}" description="Cholesterol"/></td>
                                    </tr>
                                    <tr>
                                        <td><t:row value="${product.saccharose}" description="Sacharoza"/></td>
                                    </tr>
                                    <tr class="info">
                                        <td><t:row value="${product.cellulose}" description="Błonnik"/></td>
                                    </tr>
                                    <tr>
                                        <td><t:row value="${product.calcium}" description="Wapno"/></td>
                                    </tr>
                                    <tr class="active">
                                        <td><t:row value="${product.magnesium}" description="Magnez"/></td>
                                    </tr>
                                    <tr>
                                        <td><t:row value="${product.iron}" description="Żelazo"/></td>
                                    </tr>
                                    <tr class="success">
                                        <td><t:row value="${product.zinc}" description="Cynk"/> </td>
                                    </tr>
                                    <tr>
                                        <td><t:row value="${product.vitaminD}" description="Witamina D"/> </td>
                                    </tr>
                                    <tr class="warning">
                                        <td><t:row value="${product.vitaminB1}" description="Witamina B1"/></td>
                                    </tr>
                                    <tr>
                                        <td><t:row value="${product.vitaminB2}" description="Witamina B2"/></td>
                                    </tr>
                                    <tr class="danger">
                                        <td><t:row value="${product.vitaminPp}" description="Witamina PP"/></td>
                                    </tr>
                                    <tr>
                                        <td><t:row value="${product.vitaminB6}" description="Witamina B6"/> </td>
                                    </tr>
                                    <tr class="info">
                                        <td><t:row value="${product.vitaminB12}" description="Witamina B12"/></td>
                                    </tr>
                                    <tr>
                                        <td><t:row value="${product.vitaminC}" description="Witamina C"/></td>
                                    </tr>
                                    <tr class="active">
                                        <td><t:row value="${product.folicAcid}" description="Kwas foliowy"/> </td>
                                    </tr>
                                </tbody>
                            </table> 
                        </div>

                    </div>
                    <div class="container-fluid">
                        <center>
                            <!-- like -->
                            <c:set var="likeButton" value="daj +1"/>
                            <c:if test="${not empty like}">
                                <c:set var="likeButton" value="zabierz +1"/>
                            </c:if>
                            <button
                                class="btn btn-success btn-lg"
                                onclick="document.location.href = '<c:url value="/app/product/${product.id}/like" />';">
                                ${likeButton}
                            </button>

                            <!-- dislike -->
                            <c:set var="dislikeButton" value="daj -1"/>
                            <c:if test="${not empty dislike}">
                                <c:set var="dislikeButton" value="zabierz -1"/>
                            </c:if>
                            <button
                                class="btn btn-warning btn-lg"
                                onclick="document.location.href = '<c:url value="/app/product/${product.id}/dislike" />';">
                                ${dislikeButton}
                            </button>
                        </center>
                    </div>
                </div>
            </div>
        </div>

        <div class="form-group">
            <sec:authorize access="hasAnyRole('ROLE_MODERATOR','ROLE_ADMIN','ROLE_SYS_ADMIN')">
                <!-- moderowanie -->
                <c:set var="moderatedButton" value="Aktywuj"/>
                <c:if test="${product.moderated}">
                    <c:set var="moderatedButton" value="Dezaktywuj"/>
                </c:if>
                <button
                    class="btn btn-default"
                    onclick="document.location.href = '<c:url value="/app/product/${product.id}/moderate" />';">
                    ${moderatedButton}
                </button>
            </sec:authorize>
        </div>
        <p class="text-center ">Copyright © 2015, <a target="_blank" href="http://www.soft-project.pl ">soft-project.pl</a></p>
    </jsp:body>
</t:genericpage>
