public class Fahrzeug {
    private Strecke strecke;
    private boolean aufStrecke;
    private final String bezeichnung;
    private int position;
    private boolean angehalten;

    public Fahrzeug(Strecke strecke, String bezeichnung) {
        this.strecke = strecke;
        this.aufStrecke = false;
        this.bezeichnung = bezeichnung;
        this.position = -1; //
        this.angehalten = false;
    }

    public void starte() {
        if (angehalten) {
            angehalten = false;
        } else if (strecke.istWeicheZurStreckeOffen() && !aufStrecke && !strecke.andereFahrzeugeAufStrecke(this)) {
            aufStrecke = true;
            position = 175;
            strecke.schliesseWeicheZurStrecke();
        }
    }

    public void stoppe() {
        angehalten = true;
    }

    public void bewege() {
        if (aufStrecke && !angehalten) {
            position = (position + 5) % 360;
            if (strecke.istWeicheZurGarageOffen() && position == 170) {
                aufStrecke = false;
                position = -1;
                strecke.schliesseWeicheZurGarage();
            }
        }
    }

    public int getPosition() {
        return position;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public boolean istAufStrecke() {
        return aufStrecke;
    }

    public boolean istInGarage() {
        return position == -1;
    }
}
