import javax.swing.*;
import java.awt.*;

public class TeststreckeVisualisierung extends JPanel {
    private Teststrecke teststrecke;

    public TeststreckeVisualisierung(Teststrecke teststrecke) {
        this.teststrecke = teststrecke;
        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Berechnen Sie die Verschiebungswert (20% der Panelbreite)
        int verschiebung = (int) (getWidth() * 0.05);

        // Zeichnen der Teststrecke
        int radius = 100;
        int mittelpunktX = getWidth() / 2 + verschiebung; // Verschieben um 20%
        int mittelpunktY = getHeight() / 2;
        g.drawOval(mittelpunktX - radius, mittelpunktY - radius, 2 * radius, 2 * radius);

        // Zeichnen der Garage
        int garageX = mittelpunktX - radius - 85; // Verschieben um 20%
        int garageY = mittelpunktY - 65;
        int garageWidth = 85;
        int garageHeight = 125;
        g.drawRect(garageX, garageY, garageWidth, garageHeight);

        // Zeichnen der Weichen
        int weicheX = 85 + verschiebung;; // x-Koordinate der Garage
        int weicheY = 200; // y-Koordinate der Garage
        int weicheEndeX = 155 + verschiebung;; // x-Koordinate der Strecke (Näherungswert)
        int weicheEndeYOben = 100; // y-Koordinate der Strecke, steigend
        int weicheEndeYUnten = 300; // y-Koordinate der Strecke, fallend

        // Weiche von der Garage zur Teststrecke
        g.setColor(teststrecke.istWeicheZurStreckeOffen() ? Color.GREEN : Color.RED);
        g.drawLine(weicheX, weicheY, weicheEndeX, weicheEndeYOben);

        // Weiche von der Teststrecke zur Garage
        g.setColor(teststrecke.istWeicheZurGarageOffen() ? Color.GREEN : Color.RED);
        g.drawLine(weicheX, weicheY, weicheEndeX, weicheEndeYUnten);

        // Setzen Sie die Farbe zurück
        g.setColor(Color.BLACK);

        // Zeichnen der Fahrzeuge
        drawFahrzeug(g, teststrecke.getWerkzeugwagen(), verschiebung);
        drawFahrzeug(g, teststrecke.getTransrapid(), verschiebung);
    }

    private void drawFahrzeug(Graphics g, Fahrzeug fahrzeug, int verschiebung) {
        int x, y;
        Color farbe;

        // Definieren Sie die Farbe basierend auf dem Fahrzeug
        if (fahrzeug.getBezeichnung().equals("Werkzeugwagen")) {
            farbe = Color.BLUE;
            y = 160; // Y-Position für Werkzeugwagen
        } else {
            farbe = Color.RED;
            y = 240; // Y-Position für Transrapid
        }

        if (fahrzeug.istInGarage()) {
            x = 50 + verschiebung; // X-Position näher an der Garage
        } else {
            double angle = Math.toRadians(fahrzeug.getPosition());
            x = (int) (200 + 100 * Math.cos(angle)) - 5 + verschiebung; // Verschiebung anwenden
            y = (int) (200 + 100 * Math.sin(angle)) - 5;
        }

        g.setColor(farbe); // Setzen Sie die Farbe für das Fahrzeug
        g.fillOval(x, y, 10, 10); // Zeichnen Sie das Fahrzeug an der berechneten Position
        g.drawString(fahrzeug.getBezeichnung(), x - 20, y - 10); // Zeichnen Sie den Text neben dem Fahrzeug
    }




}



