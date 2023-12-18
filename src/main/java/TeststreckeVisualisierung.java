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

        int verschiebung = (int) (getWidth() * 0.05);

        int radius = 100;
        int mittelpunktX = getWidth() / 2 + verschiebung;
        int mittelpunktY = getHeight() / 2;
        g.drawOval(mittelpunktX - radius, mittelpunktY - radius, 2 * radius, 2 * radius);

        int garageX = mittelpunktX - radius - 85;
        int garageY = mittelpunktY - 65;
        int garageWidth = 85;
        int garageHeight = 125;
        g.drawRect(garageX, garageY, garageWidth, garageHeight);

        int weicheX = 85 + verschiebung;;
        int weicheY = 200;
        int weicheEndeX = 155 + verschiebung;
        int weicheEndeYOben = 100;
        int weicheEndeYUnten = 300;

        g.setColor(teststrecke.istWeicheZurStreckeOffen() ? Color.GREEN : Color.RED);
        g.drawLine(weicheX, weicheY, weicheEndeX, weicheEndeYOben);

        g.setColor(teststrecke.istWeicheZurGarageOffen() ? Color.GREEN : Color.RED);
        g.drawLine(weicheX, weicheY, weicheEndeX, weicheEndeYUnten);

        g.setColor(Color.BLACK);

        drawFahrzeug(g, teststrecke.getWerkzeugwagen(), verschiebung);
        drawFahrzeug(g, teststrecke.getTransrapid(), verschiebung);
    }

    private void drawFahrzeug(Graphics g, Fahrzeug fahrzeug, int verschiebung) {
        int x, y;
        Color farbe;

        if (fahrzeug.getBezeichnung().equals("Werkzeugwagen")) {
            farbe = Color.BLUE;
            y = 160;
        } else {
            farbe = Color.RED;
            y = 240;
        }

        if (fahrzeug.istInGarage()) {
            x = 50 + verschiebung;
        } else {
            double angle = Math.toRadians(fahrzeug.getPosition());
            x = (int) (200 + 100 * Math.cos(angle)) - 5 + verschiebung;
            y = (int) (200 + 100 * Math.sin(angle)) - 5;
        }

        g.setColor(farbe);
        g.fillOval(x, y, 10, 10);
        g.drawString(fahrzeug.getBezeichnung(), x - 20, y - 10);
    }

}



