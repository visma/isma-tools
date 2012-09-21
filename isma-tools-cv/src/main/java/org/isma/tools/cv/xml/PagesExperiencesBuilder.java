package org.isma.tools.cv.xml;

import org.isma.tools.cv.model.*;
import org.jdom.Element;

import java.util.ArrayList;
import java.util.List;

public class PagesExperiencesBuilder extends Builder<List<PageExperiences>> {
    private ClientBuilder clientBuilder = new ClientBuilder();
    private MissionBuilder missionBuilder = new MissionBuilder();
    private ProjetsBuilder projetsBuilder = new ProjetsBuilder();
    private InterventionBuilder interventionBuilder = new InterventionBuilder();
    private EnvironnementBuilder environnementBuilder = new EnvironnementBuilder();

    public List<PageExperiences> build(Element element) {
        List<PageExperiences> pages = new ArrayList<PageExperiences>();
        for (Object obj : element.getChildren("pageExperience")) {
            pages.add(buildPage((Element) obj));
        }
        return pages;
    }

    private PageExperiences buildPage(Element element) {
        List<Experience> experiences = new ArrayList<Experience>();
        for (Object obj : element.getChildren("experience")) {
            experiences.add(buildExperience((Element) obj));
        }
        return new PageExperiences(experiences);

    }

    private Experience buildExperience(Element element) {
        Client client = clientBuilder.build(element.getChild("client"));
        Mission mission = missionBuilder.build(element.getChild("mission"));
        List<Projet> projets = projetsBuilder.build(element.getChild("projets"));
        Intervention intervention = interventionBuilder.build(element.getChild("intervention"));
        Environnement environnement = environnementBuilder.build(element.getChild("environnement"));
        return new Experience(client, mission, projets, intervention, environnement);
    }
}
