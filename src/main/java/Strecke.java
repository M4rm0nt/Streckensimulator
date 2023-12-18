public class Strecke {
    private Fahrzeug werkzeugwagen;
    private Fahrzeug transrapid;
    private boolean weicheZurStreckeOffen;
    private boolean weicheZurGarageOffen;

    public Strecke() {
        werkzeugwagen = new Fahrzeug(this, "Werkzeugwagen");
        transrapid = new Fahrzeug(this, "Transrapid");
        weicheZurStreckeOffen = false;
        weicheZurGarageOffen = false;
    }

    public void starteFahrzeug(Fahrzeug fahrzeug) {
        fahrzeug.starte();
    }

    public void stoppeFahrzeug(Fahrzeug fahrzeug) {
        fahrzeug.stoppe();
    }

    public Fahrzeug getWerkzeugwagen() {
        return werkzeugwagen;
    }

    public Fahrzeug getTransrapid() {
        return transrapid;
    }

    public boolean istWeicheZurStreckeOffen() {
        return weicheZurStreckeOffen;
    }

    public boolean istWeicheZurGarageOffen() {
        return weicheZurGarageOffen;
    }

    public void oeffneWeicheZurStrecke() {
        if (!werkzeugwagen.istAufStrecke() && !transrapid.istAufStrecke() && !weicheZurStreckeOffen) {
            weicheZurStreckeOffen = true;
        }
    }

    public void oeffneWeicheZurGarage() {
        weicheZurGarageOffen = true;
    }

    public void schliesseWeicheZurStrecke() {
        weicheZurStreckeOffen = false;
    }

    public void schliesseWeicheZurGarage() {
        weicheZurGarageOffen = false;
    }

    public boolean andereFahrzeugeAufStrecke(Fahrzeug fragendesFahrzeug) {
        return (werkzeugwagen.istAufStrecke() && werkzeugwagen != fragendesFahrzeug) ||
                (transrapid.istAufStrecke() && transrapid != fragendesFahrzeug);
    }
}
