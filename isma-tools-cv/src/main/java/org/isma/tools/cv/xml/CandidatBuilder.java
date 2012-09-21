package org.isma.tools.cv.xml;

import org.isma.tools.cv.model.Adresse;
import org.isma.tools.cv.model.Candidat;
import org.isma.tools.cv.model.Contact;
import org.isma.tools.cv.model.Profil;
import org.jdom.Element;

public class CandidatBuilder extends Builder<Candidat> {
    private final ContactBuilder contactBuilder = new ContactBuilder();
    private final AdresseBuilder adresseBuilder = new AdresseBuilder();
    private final ProfilBuilder profilBuilder = new ProfilBuilder();

    private enum Attributes {
        PRENOM("prenom"),
        NOM("nom"),
        AGE("age"),
        STATUT_MARITAL("statutMarital"),
        NATIONALITE("nationalite");
        private String name;

        Attributes(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    private enum Tags {
        CONTACT,
        ADRESSE,
        PROFIL;

        public String getName() {
            return name().toLowerCase();
        }
    }

    public Candidat build(Element element) {
        String prenom = getRequiredAttributeValue(element, Attributes.PRENOM.getName());
        String nom = getRequiredAttributeValue(element, Attributes.NOM.getName());
        int age = Integer.parseInt(getRequiredAttributeValue(element, Attributes.AGE.getName()));
        String statut = getRequiredAttributeValue(element, Attributes.STATUT_MARITAL.getName());
        String nationalite = getRequiredAttributeValue(element, Attributes.NATIONALITE.getName());

        Contact contact = contactBuilder.build(element.getChild(Tags.CONTACT.getName()));
        Adresse adresse = adresseBuilder.build(element.getChild(Tags.ADRESSE.getName()));
        Profil profil = profilBuilder.build(element.getChild(Tags.PROFIL.getName()));

        return new Candidat(prenom, nom, age, statut, nationalite, contact, adresse, profil);
    }
}
