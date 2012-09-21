<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="org.isma.tools.cv.model.*" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%--<%@ taglib prefix="s" uri="/struts-tags" %>--%>
<%--<%@ taglib prefix="form" uri="/struts-tags" %>--%>
<%--<%@ taglib prefix="html" uri="/struts-tags" %>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
    Cv cv = (Cv) session.getAttribute("cv");
    Candidat candidat = cv.getCandidat();
    Adresse adresse = candidat.getAdresse();
    Contact contact = candidat.getContact();
    Profil profil = candidat.getProfil();
    List competences = profil.getCompetences();
    List formations = cv.getFormations();
    List pagesExperiences = cv.getPagesExperiences();
    List langues = cv.getLangues();
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>CV</title>
    <link href='http://fonts.googleapis.com/css?family=PT+Sans' rel='stylesheet' type='text/css'>
    <link type="text/css" rel="stylesheet" href="css/green.css"/>
</head>
<body>
<!-- Begin Wrapper -->
<div id="wrapper">
<div class="wrapper-top"></div>
<div class="wrapper-mid">

<!-- Begin Paper -->
<div id="paper">
<div class="paper-top"></div>
<div id="paper-mid">
<div class="entry">
    <!-- Begin Image -->
    <img class="portrait" src="images/image.jpg" alt="Ismaël MAMECHE" style="display:none"/>
    <!-- End Image -->
    <!-- Begin Personal Information -->
    <!-- SI PHOTO : supprimer les surcharges style adéquates -->
    <div style="text-align: right;">
        <p><strong>Age : </strong><%=candidat.getAge()%> ans</p>
        <p><strong>Nationalité : </strong><%=candidat.getNationalite()%></p>
    </div>
    <div  class="self" style="width: 500px;margin-left: 40px;float: right;margin-top: -30px;">
        <h1 class="name">
            <%= candidat.getPrenom()+"&nbsp;"+candidat.getNom() %>
            <span><%= profil.getTitre() %></span>
        </h1>
        <br/>
        <span>
            <%= profil.getDescription() %>
        </span>
        <ul>
            <li class="ad"><%= adresse.getAdresseComplete() %>
            </li>
            <li class="mail"><%= contact.getMail() %>
            </li>
            <li class="tel"><%= contact.getTel() %>
            </li>
        </ul>
    </div>
</div>

<!-- COMPETENCES -->
<div class="entry">
    <h2>COMPETENCES</h2>
    <div class="content">
    <%
        for (int i = 0; i < competences.size(); i++){
            Competence competence = (Competence)competences.get(i);
            List details = competence.getDetails();
            String detailContent = StringUtils.join(details, ", ");
    %>
        <p>
            <%=competence.getIntitule()%><br/>
            <em><%=detailContent%></em>
            <br/>
        </p>
    <%
        }//i
    %>
    </div>
</div>
<!-- COMPETENCES -->

<!-- ETUDES -->
<div class="entry">
    <h2>ETUDES</h2>
    <% for (int i = 0; i < formations.size(); i++) {
        Formation formation = (Formation)formations.get(i);
    %>
    <div class="content">
        <h3><%= formation.getAnnee()%>
        </h3>
        <% if (formation.getSpecialisation() != null) { %>
        <p><%= formation.getNom()%><br/>
            <em><%= formation.getSpecialisation()%>
            </em>
        </p>
        <% } else { %>
        <p><%= formation.getNom()%>
        </p>
        <% } %>
    </div>
    <% } %>
</div>
<!-- ETUDES -->

<!-- EXPERIENCES -->
<div class="entry">
    <h2>EXPERIENCES</h2>
    <!-- Experiences -->
    <% for (int i = 0; i < pagesExperiences.size(); i++) {
        PageExperiences pageExperiences = (PageExperiences) pagesExperiences.get(i);
        List experiences = pageExperiences.getExperiences();
        boolean firstPage = i == 0;
        if (!firstPage){
            out.println("<div class='page-break'>");
        }
        for (int e = 0; e < experiences.size(); e++) {
            Experience experience = (Experience) experiences.get(e);
            Client client = experience.getClient();
            Mission mission = experience.getMission();
            List projets = experience.getProjets();
            Intervention intervention = experience.getIntervention();
            Environnement environnement = experience.getEnvironnement();
            String labelClientGras = client.getNom() + " - " + client.getFonction();
            if (client.getEquipe() != null){
                labelClientGras += ". Equipe " + client.getEquipe();
            }
            //Pas super clean (on récupére les infos sur mission... pas sur environnement)
            String effectifs = mission.getEffectifs() + " personne";
            effectifs += "1".equals(effectifs) ?  "." : "s.";
            String labelEnvironnementGras = mission.getType() + " : " + effectifs;
    %>
    <div class="content">
        </br>
        <h3><%=mission.getPeriode()%></h3>
        <p><b><%=labelClientGras%></b>, <%=mission.getDuree()%><br/>
            <em><%=mission.getPoste()%></em>
            <%if (mission.getTexte() != null){%>
            <%=mission.getTexte()%>
            <%}%>
        </p>
        <%if (intervention != null){%>
        <h3>Intervention</h3>
        <p>
        <%=intervention.getTexte()%>
        </p>

        <%}//if intervention%>
        <% for (int j = 0; j < projets.size(); j++) {
            Projet projet = (Projet)projets.get(j);
        %>
        <h3>Projet <%=projet.getNom()%></h3>
        <p>
        <%=projet.getTexte()%>
        </p>
        <% }//j %>

        <%
            if (environnement != null){
                List details = environnement.getDetails();
        %>
        <h3>Environnement</h3>
        <p><%=labelEnvironnementGras%></p>
        <p>
        <%
            String detailContent = StringUtils.join(environnement.getDetails(), ", ");
        %>
        <p>
        <em><%=detailContent%></em>
        </p>

        <%  }//if environnement %>

    </div>
    <hr style="width: 500px;float: right;"/>
    <%  }//e
        if (!firstPage){
            out.println("</div>");
        }
    %>
    <% }//i %>
</div>
<!-- EXPERIENCES -->

<!-- LANGUES -->
<div class="entry">
    <h2>LANGUES</h2>
    <% for (int i = 0; i < langues.size(); i++) {
        Langue langue = (Langue)langues.get(i);
    %>
    <div class="content">
        <h3><%= langue.getNom()%>
        </h3>
        <p><%= langue.getNiveau()%>
        </p>
    </div>
    <% }//i %>
</div>
<!-- LANGUES -->
</div>

<div class="clear"></div>
<div class="paper-bottom"></div>
</div>
<!-- End Paper -->

</div>
<div class="wrapper-bottom"></div>
</div>

<!-- End Wrapper -->
</body>
</html>
