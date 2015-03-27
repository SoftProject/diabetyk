<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage title="Szczegóły">

    <jsp:body>

        <t:error/>

        <t:message/>

        <div class="container">

            <div class="form-group">
                <button class="btn btn-default" id="list"
                        onclick="document.location.href='<c:url value="/app/product/list" />';">
                    Lista produktów
                </button>
                <button class="btn btn-default" id="add"
                        onclick="document.location.href='<c:url value="/app/auth/logout" />';">
                    Wyloguj
                </button>
            </div>

            <div class="page-header">
                <h1>Szczegóły produktu</h1>
            </div>

            <t:row value="${product.name}" description="Nazwa"/>

            <t:row value="${product.description}" description="Opis"/>

            <t:row value="${product.fat}" description="Tłuszcz"/>

            <t:row value="${product.protein}" description="Białko"/>

            <t:row value="${product.carbohydrates}" description="Węglowodany"/>

            <t:row value="${product.weightForOneWw}" description="Waga na jeden wymiennik węglowodanowy"/>

            <t:row value="${product.homeMeasure}" description="Miara domowa"/>

            <t:row value="${product.wwInPortion}" description="Ilość wymienników węglowodanowych w porcji"/>

            <t:row value="${product.allergen}" description="Czy jest alergenem?" isBoolean="true"/>

            <t:row value="${product.glutenFree}" description="Czy jest bezglutenowy?" isBoolean="true"/>

            <t:row value="${product.categories}" description="Kategorie" isCollection="true"/>

            <t:row value="${product.saturatedFattyAcids}" description="Nasycone kwasy tłuszczowe"/>

            <t:row value="${product.oneSaturatedFattyAcids}" description="Jednonasycone kwasy tłuszczowe"/>

            <t:row value="${product.multiSaturatedFattyAcids}" description="Wielonasycone kwasy tłuszczowe"/>

            <t:row value="${product.cholesterol}" description="Cholesterol"/>

            <t:row value="${product.saccharose}" description="Sacharoza"/>

            <t:row value="${product.cellulose}" description="Błonnik"/>

            <t:row value="${product.calcium}" description="Wapno"/>

            <t:row value="${product.magnesium}" description="Magnez"/>

            <t:row value="${product.iron}" description="Żelazo"/>

            <t:row value="${product.zinc}" description="Cynk"/>

            <t:row value="${product.vitaminD}" description="Witamina D"/>

            <t:row value="${product.vitaminB1}" description="Witamina B1"/>

            <t:row value="${product.vitaminB2}" description="Witamina B2"/>

            <t:row value="${product.vitaminPp}" description="Witamina PP"/>

            <t:row value="${product.vitaminB6}" description="Witamina B6"/>

            <t:row value="${product.vitaminB12}" description="Witamina B12"/>

            <t:row value="${product.vitaminC}" description="Witamina C"/>

            <t:row value="${product.folicAcid}" description="Kwas foliowy"/>

        </div>

        <div class="form-group">

            <!-- like -->
            <c:set var="likeButton" value="daj +1"/>
            <c:if test="${not empty like}">
                <c:set var="likeButton" value="zabierz +1"/>
            </c:if>
            <button
                    class="btn btn-default"
                    onclick="document.location.href='<c:url value="/app/product/${product.id}/like" />';">
                    ${likeButton}
            </button>

            <!-- dislike -->
            <c:set var="dislikeButton" value="daj -1"/>
            <c:if test="${not empty dislike}">
                <c:set var="dislikeButton" value="zabierz -1"/>
            </c:if>
            <button
                    class="btn btn-default"
                    onclick="document.location.href='<c:url value="/app/product/${product.id}/dislike" />';">
                    ${dislikeButton}
            </button>

            <sec:authorize access="hasAnyRole('ROLE_MODERATOR','ROLE_ADMIN','ROLE_SYS_ADMIN')">

                <!-- moderowanie -->
                <c:set var="moderatedButton" value="Aktywuj"/>
                <c:if test="${product.moderated}">
                    <c:set var="moderatedButton" value="Dezaktywuj"/>
                </c:if>
                <button
                        class="btn btn-default"
                        onclick="document.location.href='<c:url value="/app/product/${product.id}/moderate" />';">
                        ${moderatedButton}
                </button>

            </sec:authorize>
        </div>

    </jsp:body>

</t:genericpage>
