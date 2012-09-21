package org.isma.tools.cv.model;

public class Adresse {
    private final String rue;
    private final String cp;
    private final String ville;

    public Adresse(String rue, String cp, String ville) {
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
    }

    public String getRue() {
        return rue;
    }

    public String getCp() {
        return cp;
    }

    public String getVille() {
        return ville;
    }

    public String getAdresseComplete() {
        return String.format("%s, %s %s", rue, cp, ville);
    }
}
