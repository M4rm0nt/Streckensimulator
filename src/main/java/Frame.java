import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Frame extends JFrame {
    private Visualisierung visualisierung;
    private Strecke strecke;
    private JComboBox<String> fahrzeugAuswahl;
    private JButton weicheZurStreckeOeffnenButton;
    private JButton weicheZurGarageOeffnenButton;
    private JButton weicheZurGarageSchliessenButton;
    private JButton startButton;
    private JButton stopButton;
    private Timer werkzeugwagenTimer;
    private Timer transrapidTimer;

    public Frame(Strecke strecke) {
        this.strecke = strecke;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Strecke Visualisierung");
        setLayout(new BorderLayout());

        visualisierung = new Visualisierung(strecke, 150, 2);
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
            strecke.oeffneWeicheZurStrecke();
            visualisierung.repaint();
        });
        row2.add(weicheZurStreckeOeffnenButton);

        mainControlPanel.add(row2);

        // Reihe 3
        JPanel row3 = new JPanel();
        weicheZurGarageOeffnenButton = new JButton("Weiche zur Garage öffnen");
        weicheZurGarageOeffnenButton.addActionListener(e -> {
            strecke.oeffneWeicheZurGarage();
            visualisierung.repaint();
        });
        row3.add(weicheZurGarageOeffnenButton);

        weicheZurGarageSchliessenButton = new JButton("Weiche zur Garage schließen");
        weicheZurGarageSchliessenButton.addActionListener(e -> {
            strecke.schliesseWeicheZurGarage();
            visualisierung.repaint();
        });
        row3.add(weicheZurGarageSchliessenButton);
        mainControlPanel.add(row3);

        add(mainControlPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setResizable(false); 
        setVisible(true);

        werkzeugwagenTimer = new Timer(100, e -> aktualisierePosition(e, () -> bewegeFahrzeug(strecke.getWerkzeugwagen())));
        transrapidTimer = new Timer(100, e -> aktualisierePosition(e, () -> bewegeFahrzeug(strecke.getTransrapid())));
    }

    private void startFahrzeug(ActionEvent e) {
        String ausgewaehltesFahrzeug = (String) fahrzeugAuswahl.getSelectedItem();
        if ("Werkzeugwagen".equals(ausgewaehltesFahrzeug)) {
            strecke.starteFahrzeug(strecke.getWerkzeugwagen());
            werkzeugwagenTimer.start();
        } else if ("Transrapid".equals(ausgewaehltesFahrzeug)) {
            strecke.starteFahrzeug(strecke.getTransrapid());
            transrapidTimer.start();
        }
    }

    private void stopFahrzeug(ActionEvent e) {
        String ausgewaehltesFahrzeug = (String) fahrzeugAuswahl.getSelectedItem();
        if ("Werkzeugwagen".equals(ausgewaehltesFahrzeug)) {
            strecke.stoppeFahrzeug(strecke.getWerkzeugwagen());
            werkzeugwagenTimer.stop();
        } else if ("Transrapid".equals(ausgewaehltesFahrzeug)) {
            strecke.stoppeFahrzeug(strecke.getTransrapid());
            transrapidTimer.stop();
        }
    }

    private void aktualisierePosition(ActionEvent e, Runnable bewegungsAktion) {
        bewegungsAktion.run();
        visualisierung.repaint();
    }

    private void bewegeFahrzeug(Fahrzeug fahrzeug) {
        fahrzeug.bewege();
        if (fahrzeug.istInGarage()) {
            // Stoppen des Timers, wenn das Fahrzeug die Garage erreicht
            if (fahrzeug == strecke.getWerkzeugwagen()) {
                werkzeugwagenTimer.stop();
            } else if (fahrzeug == strecke.getTransrapid()) {
                transrapidTimer.stop();
            }
        }
        visualisierung.repaint(); // Aktualisieren der Visualisierung
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Strecke strecke = new Strecke();
            new Frame(strecke);
        });
    }

}