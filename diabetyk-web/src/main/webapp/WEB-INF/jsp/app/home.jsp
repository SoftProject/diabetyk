<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage title="Logowanie">
    <jsp:attribute name="html_extra_header">
        <link href="<c:url value="/resources/css/home.css"/>" rel="stylesheet">  
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <div class="container">

                <div class="col-md-12">
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
                                        <a  href='<c:url value="/app/auth/logout" />' > <span class="glyphicon glyphicon-off"></span> Wyloguj się</a>
                                    </li> 
                                </ul>
                            </div>
                        </div>
                    </div> 

                    <!--           carusel-->

                    <div class="row">
                        <div class="col-md-12">
                            <!-- The carousel -->
                            <div id="transition-timer-carousel" class="carousel slide transition-timer-carousel" data-ride="carousel">
                                <!-- Indicators -->
                                <ol class="carousel-indicators">
                                    <li data-target="#transition-timer-carousel" data-slide-to="0" class="active"></li>
                                    <li data-target="#transition-timer-carousel" data-slide-to="1"></li>
                                    <li data-target="#transition-timer-carousel" data-slide-to="2"></li>
                                </ol>

                                <!-- Wrapper for slides -->
                                <div class="carousel-inner">
                                    <div class="item active">
                                        <center> <img style="height: 400px;" src="http://diabetycy-krakow.pl/img/i.jpg" /> <center></center>
                                            <div class="carousel-caption">
                                                <h1 class="carousel-caption-header">Slide 1</h1>
                                                <p class="carousel-caption-text hidden-sm hidden-xs" style="color:#444444;">
                                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse dignissim aliquet rutrum. Praesent vitae ante in nisi condimentum egestas. Aliquam.
                                                </p>
                                            </div>
                                    </div>

                                    <div class="item">
                                        <center> <img style="height: 400px;" src="http://dietmap.pl/cache/images/resizeCrop_740x415/files/artykuly/2014/czy_diabetyk_powinien_sie_odchudzac/czy_diabetyk_powinien_sie_odchudzac_3.jpg"/></center>
                                        <div class="carousel-caption">
                                            <h1 class="carousel-caption-header">Slide 2</h1>
                                            <p class="carousel-caption-text hidden-sm hidden-xs" style="color:#444444;">
                                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse dignissim aliquet rutrum. Praesent vitae ante in nisi condimentum egestas. Aliquam.
                                            </p>
                                        </div>
                                    </div>

                                    <div class="item">
                                        <center> <img style="height: 400px;" src="http://s3.redefine.pl/dcs/o2/redefine/images/82/829ce0a5419a2bc5efc523f48c908e6c.jpg"  />  </center>
                                        <div class="carousel-caption">
                                            <h1 class="carousel-caption-header">Slide 3</h1>
                                            <p class="carousel-caption-text hidden-sm hidden-xs" style="color:#444444;">
                                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse dignissim aliquet rutrum. Praesent vitae ante in nisi condimentum egestas. Aliquam.
                                            </p>
                                        </div>
                                    </div>
                                </div>

                                <!-- Controls -->
                                <a class="left carousel-control" href="#transition-timer-carousel" data-slide="prev">
                                    <span class="glyphicon glyphicon-chevron-left"></span>
                                </a>
                                <a class="right carousel-control" href="#transition-timer-carousel" data-slide="next">
                                    <span class="glyphicon glyphicon-chevron-right"></span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <!--    carusel end-->

            </div>
            <div class="container">                           
                <hr class="featurette-divider">



                <div class="container-fluid destacados">
                    <div class="col-md-6" >
                        <div>
                            <img src="http://icons.iconarchive.com/icons/kyo-tux/delikate/512/Add-icon.png" width="140" height="140"  >
                            <h2>Dodawaj produkty</h2>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                tempor incididunt ut labore et dolore magna aliqua.</p>
                            <a class="btn btn-success " href='<c:url value="/app/product/add" />' role="button">Dodaj produkt &raquo;</a>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div>
                            <img src="http://rynko.pl/wp-content/uploads/2013/02/Actions-view-calendar-list-icon.png" width="140" height="140"  >
                            <h2>Lista produktów</h2>
                            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                tempor incididunt ut labore et dolore magna aliqua.</p>
                            <a class="btn btn-success " href='<c:url value="/app/product/list" />' role="button">Zobacz produkty &raquo;</a>
                        </div>
                    </div>

                </div>

                <hr class="featurette-divider">

                <div class="row">

                    <div style="height:30px;"></div>

                    <center><p>Copyright © 2015, <a target="_blank" href="http://www.soft-project.pl ">soft-project.pl</a></p></center>

                </div>
            </div>
        </div>

    </jsp:body>

</t:genericpage>
