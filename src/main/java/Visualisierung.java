import javax.swing.*;
import java.awt.*;

public class Visualisierung extends JPanel {
    private Strecke strecke;

    public Visualisierung(Strecke strecke) {
        this.strecke = strecke;
        setPreferredSize(new Dimension(400, 400));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        int verschiebung = (int) (getWidth() * 0.05);

        int radius = 100;
        int mittelpunktX = getWidth() / 2 + verschiebung;
        int mittelpunktY = getHeight() / 2;
        graphics.drawOval(mittelpunktX - radius, mittelpunktY - radius, 2 * radius, 2 * radius);

        int garageX = mittelpunktX - radius - 85;
        int garageY = mittelpunktY - 65;
        int garageWidth = 85;
        int garageHeight = 125;
        graphics.drawRect(garageX, garageY, garageWidth, garageHeight);

        int weicheX = 85 + verschiebung;;
        int weicheY = 200;
        int weicheEndeX = 155 + verschiebung;
        int weicheEndeYOben = 100;
        int weicheEndeYUnten = 300;

        graphics.setColor(strecke.istWeicheZurStreckeOffen() ? Color.GREEN : Color.RED);
        graphics.drawLine(weicheX, weicheY, weicheEndeX, weicheEndeYOben);

        graphics.setColor(strecke.istWeicheZurGarageOffen() ? Color.GREEN : Color.RED);
        graphics.drawLine(weicheX, weicheY, weicheEndeX, weicheEndeYUnten);

        graphics.setColor(Color.BLACK);

        drawFahrzeug(graphics, strecke.getWerkzeugwagen(), verschiebung);
        drawFahrzeug(graphics, strecke.getTransrapid(), verschiebung);
    }

    private void drawFahrzeug(Graphics graphics, Fahrzeug fahrzeug, int verschiebung) {
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

        graphics.setColor(farbe);
        graphics.fillOval(x, y, 10, 10);
        graphics.drawString(fahrzeug.getBezeichnung(), x - 20, y - 10);
    }

}



