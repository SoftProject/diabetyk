<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:set var="title" value="Dodaj nowy produkt"/>
<c:if test="${not empty product.id}">
    <c:set var="title" value="Edytuj produkt"/>
</c:if>

<t:genericpage title="${title}">

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
                                <a  href='<c:url value="/app/product/list" />' > <span class="glyphicon glyphicon-list"></span> Lista produktów</a>
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

            <div class="col-md-12 jumbotron">

                <t:message/>

                <c:set var="action" value="/app/product/addAction"/>
                <c:if test="${not empty product.id}">
                    <c:set var="action" value="/app/product/editAction"/>
                </c:if>

                <t:form-panel title="${title}" modelAttribute="product" action="${action}">
                    <div class="container"> 
                        <div class="container"> 
                            <div class="col-md-6"  >
                                <div class="col-md-1"  ></div>   
                                <div class="col-md-10"  >
                                    <t:input path="name" label="Nazwa" required="true"/>

                                    <t:input path="description" label="Opis" required="true"/>

                                    <t:input path="fat" label="Tłuszcz" required="true" type="number" step="0.01" min="0"
                                             cssClass="form-control valid"/>

                                    <t:input path="protein" label="Białko" required="true" type="number" step="0.01" min="0"
                                             cssClass="form-control valid"/>
                                </div>
                                <div class="col-md-1"  ></div>   
                            </div>

                            <div class="col-md-6"  >
                                <div class="col-md-1"  ></div>   
                                <div class="col-md-10"  >
                                    <t:input path="carbohydrates" label="Węglowodany" required="true" type="number" step="0.01" min="0"
                                             cssClass="form-control valid"/>

                                    <t:input path="weightForOneWw" label="Waga na jeden wymiennik węglowodanowy" required="true" type="number"
                                             step="0.01" min="0" cssClass="form-control valid"/>

                                    <t:input path="homeMeasure" label="Miara domowa" required="true"/>

                                    <t:input path="wwInPortion" label="Ilość wymienników węglowodanowych w porcji" required="true" type="number"
                                             step="1" min="0" cssClass="form-control valid"/> 
                                </div>
                                <div class="col-md-1"></div> 
                            </div>
                        </div>
                        <div class="container"> 


                            <div class="col-md-6">

                                <div class="col-md-1"  ></div>   
                                <div class="col-md-10"  >
                                    <div>
                                        <t:checkbox path="allergen" label="Czy jest alergenem?"/> 
                                    </div> 
                                    <div>
                                        <t:checkbox path="glutenFree" label="Czy jest bezglutenowy?"/> 
                                    </div>
                                </div>
                                <div class="col-md-1"></div> 
                            </div>
                            <div class="col-md-6">
                                <div class="col-md-1"></div>  
                                <div class="col-md-10" style="padding: 0 0 0 0;">
                                    <form:select  style="width:100%" path="categories" multiple="true" items="${categories}" itemLabel="name" itemValue="id"/>
                                    <form:errors path="categories"/>
                                </div>
                                <div class="col-md-1"></div>  
                            </div>
                        </div>



                        <div class="row ">
                            <div class="col-md-4 col-md-offset-1">
                                <label for="showOptions"><h3>Pokaż szczegóły</h3></label>
                                <input type="checkbox" id="showOptions"/>
                            </div>
                        </div>
                        <div style="height: 30px;"></div>
                        <div class="container"> 
                            <div id="options" class="hidden">


                                <div class="col-md-6"  >
                                    <div class="col-md-1"  ></div>   
                                    <div class="col-md-10"  >
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
                                    </div>
                                    <div class="col-md-1"  ></div>   
                                </div>

                                <div class="col-md-6"  >
                                    <div class="col-md-1"  ></div>   
                                    <div class="col-md-10"  >
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
                                    <div class="col-md-1"  ></div> 
                                </div>  
                            </div>
                        </div>
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
            </div> 

            <p class="text-center ">Copyright © 2015, <a target="_blank" href="http://www.soft-project.pl ">soft-project.pl</a></p>

        </div>     
    </jsp:body>

</t:genericpage>
