<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag description="Panel formularza" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="title" required="true" %>
<%@ attribute name="modelAttribute" required="true" %>
<%@ attribute name="action" required="true" %>

<c:url var="formAction" value="${action}"/>

<div class="portlet box blue">
    <div class="portlet-title">

        <div class="page-header">
            <h1>${title}</h1>
        </div>

    </div>
    <div class="portlet-body form" style="display: block;">

        <form:form modelAttribute="${modelAttribute}" action="${formAction}" method="POST" class="form-horizontal">
            <div class="form-body">
                <jsp:doBody/>
            </div>

            <div class="form-actions fluid ">
                <div class="row">

                    <div  class=" col-md-offset-3 col-md-3 " style="margin-bottom: 10px;"> 

                        <button style="display: block; width: 100%" type="submit" class="btn btn-success btn-lg" id="submit">Zapisz</button>
                    </div>
                    <div class="col-md-3 ">    
                        <button style="display: block; width: 100%" type="button" class="btn btn-warning btn-lg" onclick="window.history.back();" id="cancel">Anuluj
                        </button>
                    </div>
                </div>
            </div>
        </div>

    </form:form>
</div>
</div>
