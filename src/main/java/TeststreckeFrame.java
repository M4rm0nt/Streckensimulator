import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TeststreckeFrame extends JFrame {
    private Teststrecke teststrecke;
    private TeststreckeVisualisierung visualisierung;
    private JComboBox<String> fahrzeugAuswahl;
    private JButton startButton;
    private JButton stopButton;
    private JButton weicheZurStreckeOeffnenButton;
    private JButton weicheZurStreckeSchliessenButton;
    private JButton weicheZurGarageOeffnenButton;
    private JButton weicheZurGarageSchliessenButton;
    private Timer werkzeugwagenTimer;
    private Timer transrapidTimer;

    public TeststreckeFrame(Teststrecke teststrecke) {
        this.teststrecke = teststrecke;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Teststrecke Visualisierung");
        setLayout(new BorderLayout());

        visualisierung = new TeststreckeVisualisierung(teststrecke);
        add(visualisierung, BorderLayout.CENTER);

        JPanel mainControlPanel = new JPanel();
        mainControlPanel.setLayout(new BoxLayout(mainControlPanel, BoxLayout.Y_AXIS));

        // Reihe 1
        JPanel row1 = new JPanel();
        fahrzeugAuswahl = new JComboBox<>(new String[]{"Werkzeugwagen", "Transrapid"});
        row1.add(fahrzeugAuswahl);

        startButton = new JButton("Start");
        startButton.addActionListener(this::startFahrzeug);
        row1.add(startButton);

        stopButton = new JButton("Stopp");
        stopButton.addActionListener(this::stopFahrzeug);
        row1.add(stopButton);

        mainControlPanel.add(row1);

        // Reihe 2
        JPanel row2 = new JPanel();
        weicheZurStreckeOeffnenButton = new JButton("Weiche zur Strecke öffnen");
        weicheZurStreckeOeffnenButton.addActionListener(e -> {
            teststrecke.oeffneWeicheZurStrecke();
            visualisierung.repaint();
        });
        row2.add(weicheZurStreckeOeffnenButton);

        weicheZurStreckeSchliessenButton = new JButton("Weiche zur Strecke schließen");
        weicheZurStreckeSchliessenButton.addActionListener(e -> {
            teststrecke.schliesseWeicheZurStrecke();
            visualisierung.repaint();
        });
        row2.add(weicheZurStreckeSchliessenButton);
        mainControlPanel.add(row2);

        // Reihe 3
        JPanel row3 = new JPanel();
        weicheZurGarageOeffnenButton = new JButton("Weiche zur Garage öffnen");
        weicheZurGarageOeffnenButton.addActionListener(e -> {
            teststrecke.oeffneWeicheZurGarage();
            visualisierung.repaint();
        });
        row3.add(weicheZurGarageOeffnenButton);

        weicheZurGarageSchliessenButton = new JButton("Weiche zur Garage schließen");
        weicheZurGarageSchliessenButton.addActionListener(e -> {
            teststrecke.schliesseWeicheZurGarage();
            visualisierung.repaint();
        });
        row3.add(weicheZurGarageSchliessenButton);
        mainControlPanel.add(row3);

        add(mainControlPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        werkzeugwagenTimer = new Timer(100, this::aktualisiereWerkzeugwagenPosition);
        transrapidTimer = new Timer(100, this::aktualisiereTransrapidPosition);
    }

    private void startFahrzeug(ActionEvent e) {
        String ausgewaehltesFahrzeug = (String) fahrzeugAuswahl.getSelectedItem();
        if ("Werkzeugwagen".equals(ausgewaehltesFahrzeug)) {
            teststrecke.starteWerkzeugwagen();
            werkzeugwagenTimer.start();
        } else if ("Transrapid".equals(ausgewaehltesFahrzeug)) {
            teststrecke.starteTransrapid();
            transrapidTimer.start();
        }
    }

    private void stopFahrzeug(ActionEvent e) {
        String ausgewaehltesFahrzeug = (String) fahrzeugAuswahl.getSelectedItem();
        if ("Werkzeugwagen".equals(ausgewaehltesFahrzeug)) {
            teststrecke.stoppeWerkzeugwagen();
            werkzeugwagenTimer.stop();
        } else if ("Transrapid".equals(ausgewaehltesFahrzeug)) {
            teststrecke.stoppeTransrapid();
            transrapidTimer.stop();
        }
    }

    private void aktualisiereWerkzeugwagenPosition(ActionEvent e) {
        teststrecke.bewegeWerkzeugwagen();
        visualisierung.repaint();
    }

    private void aktualisiereTransrapidPosition(ActionEvent e) {
        teststrecke.bewegeTransrapid();
        visualisierung.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Teststrecke teststrecke = new Teststrecke();
            new TeststreckeFrame(teststrecke);
        });
    }
}