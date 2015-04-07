<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage title="Reset hasła">

    <jsp:attribute name="html_extra_header">
        <link href="<c:url value="/resources/css/logout.css"/>" rel="stylesheet">
    </jsp:attribute>

    <jsp:body>
        <center> 

            <div class="flash container" style="max-width: 700px; text-align: left;">

                <div class="page-header">
                    <h2>Zapomniałeś hasła?</h2>
                    <h4>Możesz je odzyskać, wpisz adres e-mail podany przy tworzeniu Twojego konta a otrzymasz wiadomość</h4>
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
                            <center>
                                <div class="controls">
                                    <input type="submit" class="btn btn-success btn-lg" style ="border-radius:0;" value="Resetuj"/>
                                </div>
                            </center>
                        </div>  
                    </fieldset>
                </form:form>

            </div>

        </center>
 
    </jsp:body>

</t:genericpage>
