package org.isma.tools.cv.model;

import java.util.List;

public class Experience {
    private final Client client;
    private final Mission mission;
    private final List<Projet> projets;
    private final Intervention intervention;
    private final Environnement environnement;

    public Experience(Client client, Mission mission, List<Projet> projets, Intervention intervention, Environnement environnement) {
        this.client = client;
        this.mission = mission;
        this.projets = projets;
        this.intervention = intervention;
        this.environnement = environnement;
    }

    public Client getClient() {
        return client;
    }

    public Mission getMission() {
        return mission;
    }

    public List<Projet> getProjets() {
        return projets;
    }

    public Intervention getIntervention() {
        return intervention;
    }

    public Environnement getEnvironnement() {
        return environnement;
    }
}
