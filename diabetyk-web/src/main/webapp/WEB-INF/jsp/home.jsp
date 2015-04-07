<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage title="Strona główna">

    <jsp:body>

        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="navbar navbar-inverse">
                        <div class="container-fluid">
                            <div class="navbar-header">
                                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                                    <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
                                </button> 
                            </div>
                            <div class="collapse navbar-collapse" > 
                                <ul class="nav navbar-nav navbar-right">
                                    <li class="active">
                                        <a href="/diabetyk-web/" ><span class="glyphicon glyphicon-th-large"></span> Gł&oacute;wna</a>
                                    </li>
                                    <li>
                                        <a href="/diabetyk-web/app/auth/login"> <span class="glyphicon glyphicon-user"></span> Zaloguj się</a>
                                    </li>
                                    <li>
                                        <a href="/diabetyk-web/app/registration/form"><span class="glyphicon glyphicon-registration-mark"></span> Załóż konto</a>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="jumbotron">
                                    <h1>ODŻYWIANIE</h1>
                                    <p>
                                        Jedzenie jest jedną z największych przyjemności życiowych. Zapewnia nam energię i dostarcza składniki niezbędne do prawidłowego funkcjonowania organizmu. Diabetycy mają takie same potrzeby żywieniowe jak osoby zdrowe. Mając cukrzycę, nie musisz rezygnować z kulinarnych rozkoszy podniebienia – wystarczy tylko nauczyć się prawidłowo komponować posiłki.
                                        Pomożemy ci dowiedzieć się, co i ile jeść, aby pozytywnie wpływać na swoje zdrowie, przestrzegając zasad zdrowej diety, nie tracąc przy tym walorów smakowych potraw i przyjemności z jedzenia.</p> 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row featurette">

            <!-- Three columns of text below the carousel -->
            <div class="row" style="  margin-bottom: 20px; text-align: center;">
                <div class="col-lg-4">
                    <img class="img-circle" src="http://trc-iraq.com/wp-content/uploads/2014/10/food.png" alt="Generic placeholder image" width="140" height="140">
                    <h2>Dodawaj produkty</h2>
                    <p>tekst opinia zachęta. tekst opinia zachęta.tekst opinia zachęta. tekst opinia zachęta.tekst opinia zachęta. tekst opinia zachęta.</p>
                    <p><a class="btn btn-default" href="#one" role="button">Dowiedz się więcej &raquo;</a></p>
                </div><!-- /.col-lg-4 -->
                <div class="col-lg-4">
                    <img   src="http://www.clipartbest.com/cliparts/RcG/ypd/RcGypdAei.jpeg"   alt="Generic placeholder image" width="140" height="140">
                    <h2>Oceniaj</h2>
                    <p>tekst opinia zachęta. tekst opinia zachęta.tekst opinia zachęta. tekst opinia zachęta.tekst opinia zachęta. tekst opinia zachęta.</p>
                    <p><a class="btn btn-default" href="#two" role="button">Dowiedz się więcej &raquo;</a></p>
                </div><!-- /.col-lg-4 -->
                <div class="col-lg-4">
                    <img class="img-circle" src="http://careers.domesticandgeneral.com/wp-content/uploads/2014/11/icon-health.png" alt="Generic placeholder image" width="140" height="140">
                    <h2>Żyj zdrowiej</h2>
                    <p>tekst opinia zachęta. tekst opinia zachęta.tekst opinia zachęta. tekst opinia zachęta.tekst opinia zachęta. tekst opinia zachęta.</p>
                    <p><a class="btn btn-default" href="#three" role="button">Dowiedz się więcej &raquo;</a></p>
                </div><!-- /.col-lg-4 -->
            </div>
        </div>
        <!-- START THE FEATURETTES -->

        <hr class="featurette-divider">

        <div id="one" class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading">First featurette heading. <span class="text-muted">It'll blow your mind.</span></h2>
                <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
            </div>
            <div class="col-md-5">
                <img class="featurette-image img-responsive center-block"  src="http://www.weightloss.audio/wp-content/uploads/2014/12/weight%20loss%20logo%20ideas-iaJc.jpg" alt="Generic placeholder image">
            </div>
        </div>

        <hr class="featurette-divider">

        <div id="two" class="row featurette">
            <div class="col-md-7 col-md-push-5">
                <h2 class="featurette-heading">Oh yeah, it's that good. <span class="text-muted">See for yourself.</span></h2>
                <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
            </div>
            <div class="col-md-5 col-md-pull-7">
                <img class="featurette-image img-responsive center-block"  src="http://myhealth.co.ke/wp-content/uploads/myhealth-logo-categories.png" alt="Generic placeholder image">
            </div>
        </div>

        <hr class="featurette-divider">

        <div id="three" class="row featurette">
            <div class="col-md-7">
                <h2 class="featurette-heading">And lastly, this one. <span class="text-muted">Checkmate.</span></h2>
                <p class="lead">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>
            </div>
            <div class="col-md-5">
                <img class="featurette-image img-responsive center-block"  src="http://cdn.marketplaceimages.windowsphone.com/v8/images/29f44dee-e584-4cc9-baab-241834147166?imageType=ws_icon_large" alt="Generic placeholder image">
            </div>
        </div>

        <hr class="featurette-divider">

        <!-- /END THE FEATURETTES -->




        <div class="container">
            <div class="row">
                <div style="height:30px;">

                </div>
                <div class="col-md-12"  >

                    <p class="text-center ">Copyright © 2015, <a target="_blank" href="http://www.soft-project.pl ">soft-project.pl</a></p>
                </div>
            </div>
        </div>

    </jsp:body>

</t:genericpage>
