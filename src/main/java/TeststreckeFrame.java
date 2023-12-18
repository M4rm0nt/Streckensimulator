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
        setResizable(false); // Fenstergröße fixieren
        setVisible(true);

        werkzeugwagenTimer = new Timer(100, e -> aktualisierePosition(e, () -> bewegeFahrzeug(teststrecke.getWerkzeugwagen())));
        transrapidTimer = new Timer(100, e -> aktualisierePosition(e, () -> bewegeFahrzeug(teststrecke.getTransrapid())));
    }

    private void startFahrzeug(ActionEvent e) {
        String ausgewaehltesFahrzeug = (String) fahrzeugAuswahl.getSelectedItem();
        if ("Werkzeugwagen".equals(ausgewaehltesFahrzeug)) {
            teststrecke.starteFahrzeug(teststrecke.getWerkzeugwagen());
            werkzeugwagenTimer.start();
        } else if ("Transrapid".equals(ausgewaehltesFahrzeug)) {
            teststrecke.starteFahrzeug(teststrecke.getTransrapid());
            transrapidTimer.start();
        }
    }

    private void stopFahrzeug(ActionEvent e) {
        String ausgewaehltesFahrzeug = (String) fahrzeugAuswahl.getSelectedItem();
        if ("Werkzeugwagen".equals(ausgewaehltesFahrzeug)) {
            teststrecke.stoppeFahrzeug(teststrecke.getWerkzeugwagen());
            werkzeugwagenTimer.stop();
        } else if ("Transrapid".equals(ausgewaehltesFahrzeug)) {
            teststrecke.stoppeFahrzeug(teststrecke.getTransrapid());
            transrapidTimer.stop();
        }
    }

    private void aktualisierePosition(ActionEvent e, Runnable bewegungsAktion) {
        bewegungsAktion.run();
        visualisierung.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Teststrecke teststrecke = new Teststrecke();
            new TeststreckeFrame(teststrecke);
        });
    }

    private void bewegeFahrzeug(Fahrzeug fahrzeug) {
        fahrzeug.bewege();
        if (fahrzeug.istInGarage()) {
            // Stoppen des Timers, wenn das Fahrzeug die Garage erreicht
            if (fahrzeug == teststrecke.getWerkzeugwagen()) {
                werkzeugwagenTimer.stop();
            } else if (fahrzeug == teststrecke.getTransrapid()) {
                transrapidTimer.stop();
            }
        }
        visualisierung.repaint(); // Aktualisieren der Visualisierung
    }

}