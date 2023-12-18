public class Teststrecke {
    private int werkzeugwagenPosition;
    private int transrapidPosition;
    private boolean werkzeugwagenAufStrecke;
    private boolean transrapidAufStrecke;
    private boolean weicheZurStreckeOffen;
    private boolean weicheZurGarageOffen;
    private boolean werkzeugwagenAngehalten;
    private boolean transrapidAngehalten;

    public Teststrecke() {
        werkzeugwagenPosition = -1; // -1 bedeutet, dass der Werkzeugwagen in der Garage ist
        transrapidPosition = -1; // -1 bedeutet, dass der Transrapid in der Garage ist
        werkzeugwagenAufStrecke = false;
        transrapidAufStrecke = false;
        weicheZurStreckeOffen = false;
        weicheZurGarageOffen = false;
        werkzeugwagenAngehalten = false;
        transrapidAngehalten = false;
    }

    public boolean istWerkzeugwagenInGarage() {
        return werkzeugwagenPosition == -1;
    }

    public boolean istTransrapidInGarage() {
        return transrapidPosition == -1;
    }

    public void starteWerkzeugwagen() {
        if (!transrapidAufStrecke && istWeicheZurStreckeOffen() && !werkzeugwagenAufStrecke) {
            werkzeugwagenAufStrecke = true;
            werkzeugwagenPosition = 170;
            schliesseWeicheZurStrecke();
        } else {
            werkzeugwagenAufStrecke = true;
        }
        werkzeugwagenAngehalten = false;
    }

    public void starteTransrapid() {
        if (!werkzeugwagenAufStrecke && istWeicheZurStreckeOffen() && !transrapidAufStrecke) {
            transrapidAufStrecke = true;
            transrapidPosition = 170;
            schliesseWeicheZurStrecke();
        } else {
            transrapidAufStrecke = true;
        }
        transrapidAngehalten = false;
    }

    public void stoppeWerkzeugwagen() {
        werkzeugwagenAngehalten = true;
    }

    public void stoppeTransrapid() {
        transrapidAngehalten = true;
    }

    public void bewegeWerkzeugwagen() {
        if (werkzeugwagenAufStrecke && !werkzeugwagenAngehalten) {
            werkzeugwagenPosition = (werkzeugwagenPosition + 5) % 360;
            if (weicheZurGarageOffen && werkzeugwagenPosition == 170) {
                werkzeugwagenAufStrecke = false;
                schliesseWeicheZurGarage();
            }
        }
    }

    public void bewegeTransrapid() {
        if (transrapidAufStrecke && !transrapidAngehalten) {
            transrapidPosition = (transrapidPosition + 5) % 360;
            if (weicheZurGarageOffen && transrapidPosition == 170) {
                transrapidAufStrecke = false;
                schliesseWeicheZurGarage();
            }
        }
    }

    public int getWerkzeugwagenPosition() {
        return werkzeugwagenPosition;
    }

    public int getTransrapidPosition() {
        return transrapidPosition;
    }

    public boolean istWeicheZurStreckeOffen() {
        return weicheZurStreckeOffen;
    }

    public boolean istWeicheZurGarageOffen() {
        return weicheZurGarageOffen;
    }

    public void oeffneWeicheZurStrecke() {
        if (!transrapidAufStrecke && !werkzeugwagenAufStrecke && !weicheZurStreckeOffen) {
            weicheZurStreckeOffen = true;
        }
    }

    public void schliesseWeicheZurStrecke() {
        weicheZurStreckeOffen = false;
    }

    public void oeffneWeicheZurGarage() {
        weicheZurGarageOffen = true;
    }

    public void schliesseWeicheZurGarage() {
        weicheZurGarageOffen = false;
    }
}
