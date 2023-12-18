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

        // Zeichnen der Teststrecke
        int radius = 100;
        int mittelpunktX = getWidth() / 2;
        int mittelpunktY = getHeight() / 2;
        g.drawOval(mittelpunktX - radius, mittelpunktY - radius, 2 * radius, 2 * radius);

        // Zeichnen der Garage
        int garageX = mittelpunktX - radius - 40;
        int garageY = mittelpunktY - 15;
        int garageWidth = 30;
        int garageHeight = 30;
        g.drawRect(garageX, garageY, garageWidth, garageHeight);

        // Zeichnen der Weichen
        int weicheX = 85; // x-Koordinate der Garage
        int weicheY = 200; // y-Koordinate der Garage
        int weicheEndeX = 155; // x-Koordinate der Strecke (Näherungswert)
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
        if (!teststrecke.istWerkzeugwagenInGarage()) {
            drawWerkzeugwagen(g, teststrecke.getWerkzeugwagenPosition());
        }
        if (!teststrecke.istTransrapidInGarage()) {
            drawTransrapid(g, teststrecke.getTransrapidPosition());
        }
    }

    private void drawWerkzeugwagen(Graphics g, int position) {
        double angle = Math.toRadians(position);
        int x = (int) (200 + 100 * Math.cos(angle)) - 5;
        int y = (int) (200 + 100 * Math.sin(angle)) - 5;
        g.setColor(Color.RED);
        g.fillOval(x, y, 10, 10);
        g.drawString("Werkzeugwagen", x - 20, y - 10);
    }

    private void drawTransrapid(Graphics g, int position) {
        double angle = Math.toRadians(position);
        int x = (int) (200 + 100 * Math.cos(angle)) - 5;
        int y = (int) (200 + 100 * Math.sin(angle)) - 5;
        g.setColor(Color.BLUE);
        g.fillOval(x, y, 10, 10);
        g.drawString("Transrapid", x - 20, y + 20);
    }
}
