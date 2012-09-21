<%@ page import="org.apache.commons.lang3.StringUtils" %>
<%@ page import="org.isma.tools.cv.oldmodel.*" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="form" uri="/struts-tags" %>
<%@ taglib prefix="html" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
    Cv cv = (Cv) session.getAttribute("cv");
    Candidat candidat = cv.getCandidat();
    Adresse adresse = candidat.getAdresse();
    Contact contact = candidat.getContact();
    List formations = cv.getFormations();
    List experiences = cv.getExperiences();
    List domainesCompetences = cv.getDomainesCompentences();
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    <title>Digital Curriculum Vitae</title>
    <link href="stylesheets/styles.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div id="container">
    <div id="frame-top">
        <img src="images/frame_top.png" alt="Curriculum Vitae"/>
    </div>

    <div id="frame-middle">

        <div id="page">
            <h1><%=candidat.getPrenom() + "&nbsp;" + candidat.getNom()%>
            </h1>

            <p class="job-title"><%=candidat.getProfil()%>
            </p>

            <p class="intro"></p>

            <div class="divider"></div>

            <h2>Compétences</h2>

            <ul class="details">
                <%
                    for (int i = 0; i < domainesCompetences.size(); i++) {
                        DomaineCompetence domaineCompetence = (DomaineCompetence) domainesCompetences.get(i);
                        List competences = domaineCompetence.getCompetences();
                %>
                <li>
                    <h3><%=domaineCompetence.getNom()%><span class="color2"><!-- Experience Compétence, Niveau Compétence--></span>
                    </h3>

                    <p>
                        <%=StringUtils.join(competences, ", ")%>
                    </p>
                </li>
                <%
                    }//i
                %>
            </ul>

            <h2>Expériences</h2>
            <%
                for (int i = 0; i < experiences.size(); i++) {
                    Experience experience = (Experience) experiences.get(i);
                    DescriptionExperience descriptionExperience = experience.getDescriptionExperience();
            %>
            <ul class="details">
                <li>
                    <h3><%=descriptionExperience.getClient()%> - <span class="color2">
                        <%=descriptionExperience.getPoste()%>, <%=descriptionExperience.getDateDebut()%> - <%=descriptionExperience.getDateFin()%>
                    </span>
                    </h3>

                    <p>Blabla.</p>
                </li>
            </ul>
            <%}%>

            <h2>Education</h2>
            <%
                for (int i = 0; i < formations.size(); i++) {
                    Formation formation = (Formation) formations.get(i);
                    String spe = formation.getSpecialisation();
                    String formationTitle = formation.getAnnee() + " - " + formation.getNom();
            %>
            <ul class="details">
                <li>
                    <h3><%=formationTitle%>
                    </h3>
                    <%if (spe != null) {%>
                    <p><%=spe%>
                    </p>
                    <%}//if spe%>
                </li>
            </ul>
            <%}%>

        </div>
        <!--PAGE ENDS-->
    </div>
    <!--FRAME MIDDLE ENDS-->
    <div id="frame-btm">
        <img src="images/frame_btm.png" alt="Curriculum Vitae"/>
    </div>
    <!--FRAME BOTTOM ENDS-->
</div>
<!--CONTAINER ENDS-->

</body>
</html>
