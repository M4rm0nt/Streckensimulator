public class Teststrecke {
    private Fahrzeug werkzeugwagen;
    private Fahrzeug transrapid;
    private boolean weicheZurStreckeOffen;
    private boolean weicheZurGarageOffen;

    public Teststrecke() {
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

    public void bewegeFahrzeug(Fahrzeug fahrzeug) {
        fahrzeug.bewege();
    }

    public Fahrzeug getWerkzeugwagen() {
        return werkzeugwagen;
    }

    public Fahrzeug getTransrapid() {
        return transrapid;
    }

    public boolean istFahrzeugInGarage(Fahrzeug fahrzeug) {
        return fahrzeug.istInGarage();
    }

    public boolean istWeicheZurStreckeOffen() {
        return weicheZurStreckeOffen;
    }

    public void oeffneWeicheZurStrecke() {
        if (!werkzeugwagen.istAufStrecke() && !transrapid.istAufStrecke() && !weicheZurStreckeOffen) {
            weicheZurStreckeOffen = true;
        }
    }

    public void schliesseWeicheZurStrecke() {
        weicheZurStreckeOffen = false;
    }

    public boolean istWeicheZurGarageOffen() {
        return weicheZurGarageOffen;
    }

    public void oeffneWeicheZurGarage() {
        weicheZurGarageOffen = true;
    }

    public void schliesseWeicheZurGarage() {
        weicheZurGarageOffen = false;
    }

    public boolean andereFahrzeugeAufStrecke(Fahrzeug fragendesFahrzeug) {
        return (werkzeugwagen.istAufStrecke() && werkzeugwagen != fragendesFahrzeug) ||
                (transrapid.istAufStrecke() && transrapid != fragendesFahrzeug);
    }
}
