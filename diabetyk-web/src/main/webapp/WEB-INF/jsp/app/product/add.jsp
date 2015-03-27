<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:set var="title" value="Dodawanie produktu"/>
<c:if test="${not empty product.id}">
    <c:set var="title" value="Edycja produktu"/>
</c:if>

<t:genericpage title="${title}">

    <jsp:body>

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

        <t:message/>

        <c:set var="action" value="/app/product/addAction"/>
        <c:if test="${not empty product.id}">
            <c:set var="action" value="/app/product/editAction"/>
        </c:if>

        <t:form-panel title="${title}" modelAttribute="product" action="${action}">

            <t:input path="name" label="Nazwa" required="true"/>

            <t:input path="description" label="Opis" required="true"/>

            <t:input path="fat" label="Tłuszcz" required="true" type="number" step="0.01" min="0"
                     cssClass="form-control valid"/>

            <t:input path="protein" label="Białko" required="true" type="number" step="0.01" min="0"
                     cssClass="form-control valid"/>

            <t:input path="carbohydrates" label="Węglowodany" required="true" type="number" step="0.01" min="0"
                     cssClass="form-control valid"/>

            <t:input path="weightForOneWw" label="Waga na jeden wymiennik węglowodanowy" required="true" type="number"
                     step="0.01" min="0" cssClass="form-control valid"/>

            <t:input path="homeMeasure" label="Miara domowa" required="true"/>

            <t:input path="wwInPortion" label="Ilość wymienników węglowodanowych w porcji" required="true" type="number"
                     step="1" min="0" cssClass="form-control valid"/>

            <t:checkbox path="allergen" label="Czy jest alergenem?"/>

            <t:checkbox path="glutenFree" label="Czy jest bezglutenowy?"/>

            <form:select path="categories" multiple="true" items="${categories}" itemLabel="name" itemValue="id"/>
            <form:errors path="categories"/>

            <div class="form-group">
                <label for="showOptions">Pokaż szczegóły</label>
                <input type="checkbox" id="showOptions"/>
            </div>

            <div id="options" class="hidden">

                <t:input path="saturatedFattyAcids" label="Nasycone kwasy tłuszczowe" type="number"
                         step="1" min="0" cssClass="form-control valid"/>

                <t:input path="oneSaturatedFattyAcids"
                         label="Jednonasycone kwasy tłuszczowe"
                         type="number"
                         step="0.01" min="0"
                         cssClass="form-control valid"/>

                <t:input path="multiSaturatedFattyAcids"
                         label="Wielonasycone kwasy tłuszczowe"
                         type="number"
                         step="0.01" min="0"
                         cssClass="form-control valid"/>

                <t:input path="cholesterol"
                         label="Cholesterol"
                         type="number"
                         step="0.01" min="0"
                         cssClass="form-control valid"/>

                <t:input path="saccharose"
                         label="Sacharoza"
                         type="number"
                         step="0.01" min="0"
                         cssClass="form-control valid"/>

                <t:input path="cellulose"
                         label="Błonnik"
                         type="number"
                         step="0.01" min="0"
                         cssClass="form-control valid"/>

                <t:input path="calcium"
                         label="Wapno"
                         type="number"
                         step="0.01" min="0"
                         cssClass="form-control valid"/>

                <t:input path="magnesium"
                         label="Magnez"
                         type="number"
                         step="0.01" min="0"
                         cssClass="form-control valid"/>

                <t:input path="iron"
                         label="Żelazo"
                         type="number"
                         step="0.01" min="0"
                         cssClass="form-control valid"/>

                <t:input path="zinc"
                         label="Cynk"
                         type="number"
                         step="0.01" min="0"
                         cssClass="form-control valid"/>

                <t:input path="vitaminD"
                         label="Witamina D"
                         type="number"
                         step="0.01" min="0"
                         cssClass="form-control valid"/>

                <t:input path="vitaminB1"
                         label="Witamina B1"
                         type="number"
                         step="0.01" min="0"
                         cssClass="form-control valid"/>

                <t:input path="vitaminB2"
                         label="Witamina B2"
                         type="number"
                         step="0.01" min="0"
                         cssClass="form-control valid"/>

                <t:input path="vitaminPp"
                         label="Witamina PP"
                         type="number"
                         step="0.01" min="0"
                         cssClass="form-control valid"/>

                <t:input path="vitaminB6"
                         label="Witamina B6"
                         type="number"
                         step="0.01" min="0"
                         cssClass="form-control valid"/>

                <t:input path="vitaminB12"
                         label="Witamina B12"
                         type="number"
                         step="0.01" min="0"
                         cssClass="form-control valid"/>

                <t:input path="vitaminC"
                         label="Witamina C"
                         type="number"
                         step="0.01" min="0"
                         cssClass="form-control valid"/>

                <t:input path="folicAcid"
                         label="Kwas foliowy"
                         type="number"
                         step="0.01" min="0"
                         cssClass="form-control valid"/>

            </div>

            <form:hidden path="author.id"/>

            <c:if test="${not empty product.id}">
                <form:hidden path="id"/>
            </c:if>

        </t:form-panel>

        <script>
            $("#showOptions").click(function () {
                if ($(this).is(":checked")) {
                    $("#options").removeClass("hidden");
                } else {
                    $("#options").addClass("hidden");
                }
            });
        </script>

    </jsp:body>

</t:genericpage>
