
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class baslatEkrani extends JFrame {

    public baslatEkrani() {
        setTitle("Ana Ekran");
        setSize(1920, 1080);

        ImageIcon arkaplan = new ImageIcon("C:\\Users\\Nisa\\Documents\\NetBeansProjects\\OyunEkrani\\resimler\\arkaplan.png");
        JLabel arkaplanLabel = new JLabel(arkaplan);
        setContentPane(arkaplanLabel);

        setLayout(null);

        JButton oynaButonu = new JButton("Oyna");
        oynaButonu.setBounds((getWidth() - 570) / 2, (getHeight() - 200) / 2, 200, 50);
        oynaButonu.setBackground(Color.decode("#442e74"));
        oynaButonu.setForeground(Color.decode("#FFFFFF"));
        oynaButonu.setFont(new Font("Arial", Font.PLAIN, 25));
        oynaButonu.setFocusPainted(false);
        add(oynaButonu);

        oynaButonu.addActionListener((ActionEvent e) -> {
            String widthStr = JOptionPane.showInputDialog(this, "Panel genişliğini giriniz:");
            String heightStr = JOptionPane.showInputDialog(this, "Panel yüksekliğini giriniz:");

            int width = Integer.parseInt(widthStr);
            int height = Integer.parseInt(heightStr);

            oyunEkrani oyunPanel = new oyunEkrani(width, height);

            gosterPanel(oyunPanel);
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void gosterPanel(JPanel panel) {
        getContentPane().removeAll();
        setContentPane(panel);
        revalidate();
        repaint();
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new baslatEkrani();
        });
    }
}
