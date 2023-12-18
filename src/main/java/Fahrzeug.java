public class Fahrzeug {
    private Teststrecke teststrecke;
    private int position;
    private boolean aufStrecke;
    private boolean angehalten;
    private final String bezeichnung;

    public Fahrzeug(Teststrecke teststrecke, String bezeichnung) {
        this.teststrecke = teststrecke;
        this.bezeichnung = bezeichnung;
        this.position = -1; // -1 bedeutet, dass das Fahrzeug in der Garage ist
        this.aufStrecke = false;
        this.angehalten = false;
    }

    public boolean istInGarage() {
        return position == -1;
    }

    public void starte() {
        if (angehalten) {
            angehalten = false;
        } else if (teststrecke.istWeicheZurStreckeOffen() && !aufStrecke && !teststrecke.andereFahrzeugeAufStrecke(this)) {
            aufStrecke = true;
            position = 175;
            teststrecke.schliesseWeicheZurStrecke();
        }
    }

    public void stoppe() {
        angehalten = true;
    }

    public void bewege() {
        if (aufStrecke && !angehalten) {
            position = (position + 5) % 360;
            if (teststrecke.istWeicheZurGarageOffen() && position == 170) {
                aufStrecke = false;
                position = -1;
                System.out.println(bezeichnung + " ist jetzt in der Garage."); // Debugging-Ausgabe
                teststrecke.schliesseWeicheZurGarage();
            }
        }
    }


    public int getPosition() {
        return position;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public boolean istAngehalten() {
        return angehalten;
    }

    public boolean istAufStrecke() {
        return aufStrecke;
    }
}
