
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.util.Random;
import java.awt.event.ActionEvent;

public class oyunEkrani extends JPanel {

    private int width;
    private int height;
    private int cellSize;
    private Image backgroundImage;

    private ArrayList<Agac> yazagaclar = new ArrayList<>();
    private ArrayList<Duvar> yazduvarlar = new ArrayList<>();
    private ArrayList<Kaya> yazkayalar = new ArrayList<>();
    private ArrayList<Dag> yazdaglar = new ArrayList<>();
    private ArrayList<Agac> kisagaclar = new ArrayList<>();
    private ArrayList<Duvar> kisduvarlar = new ArrayList<>();
    private ArrayList<Kaya> kiskayalar = new ArrayList<>();
    private ArrayList<Dag> kisdaglar = new ArrayList<>();
    private ArrayList<Ari> arilar = new ArrayList<>();
    private ArrayList<Kus> kuslar = new ArrayList<>();
    private ArrayList<altinsandik> altinsandiklar = new ArrayList<>();
    private ArrayList<gumussandik> gumussandiklar = new ArrayList<>();
    private ArrayList<bakirsandik> bakirsandiklar = new ArrayList<>();
    private ArrayList<zumrutsandik> zumrutsandiklar = new ArrayList<>();
    private int sandiksayisi;
    private static boolean[][] engelMatrisi;

    private final int hız = 1;

    public oyunEkrani(int width, int height) {

        this.width = width;
        this.height = height;
        this.sandiksayisi = 1;
        this.engelMatrisi = new boolean[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                engelMatrisi[i][j] = false;
            }
        }

        this.cellSize = Math.min(10, 10);
        this.backgroundImage = new ImageIcon("C:\\Users\\Nisa\\Documents\\NetBeansProjects\\OyunEkrani\\resimler\\cim.jpg").getImage();

        int panelWidth = width * cellSize;
        int panelHeight = height * cellSize;
        setPreferredSize(new Dimension(panelWidth, panelHeight));

        Random rand = new Random();

        for (int i = 0; i < 1; i++) {
            olusturyazdag(yazdaglar, 15);
        }
        for (int i = 0; i < 1; i++) {
            olusturkisdag(kisdaglar, 15);
        }
        for (int i = 0; i < 3; i++) {
            olusturyazduvar(yazduvarlar, 10);
        }
        for (int i = 0; i < 3; i++) {
            olusturkisduvar(kisduvarlar, 10);
        }
        for (int i = 0; i < 3; i++) {
            olusturari(arilar, 2, 0, 3, 0);
        }
        for (int i = 0; i < 3; i++) {
            olusturkus(kuslar, 2, 0, 5, 0);
        }
        for (int i = 0; i < 3; i++) {
            olusturyazagac(yazagaclar, 2);
        }
        for (int i = 0; i < 3; i++) {
            olusturyazagac(yazagaclar, 3);
        }
        for (int i = 0; i < 3; i++) {
            olusturyazagac(yazagaclar, 4);
        }
        for (int i = 0; i < 3; i++) {
            olusturyazagac(yazagaclar, 5);
        }
        for (int i = 0; i < 3; i++) {
            olusturyazkaya(yazkayalar, 2);
        }
        for (int i = 0; i < 3; i++) {
            olusturyazkaya(yazkayalar, 3);
        }
        for (int i = 0; i < 3; i++) {
            olusturkisagac(kisagaclar, 2);
        }
        for (int i = 0; i < 3; i++) {
            olusturkisagac(kisagaclar, 3);
        }
        for (int i = 0; i < 3; i++) {
            olusturkisagac(kisagaclar, 4);
        }
        for (int i = 0; i < 3; i++) {
            olusturkisagac(kisagaclar, 5);
        }
        for (int i = 0; i < 3; i++) {
            olusturkiskaya(kiskayalar, 2);
        }
        for (int i = 0; i < 3; i++) {
            olusturkiskaya(kiskayalar, 3);
        }
        for (int i = 0; i < sandiksayisi; i++) {
            olusturaltinsandik(altinsandiklar, 2);
        }
        for (int i = 0; i < sandiksayisi; i++) {
            olusturgumussandik(gumussandiklar, 2);
        }
        for (int i = 0; i < sandiksayisi; i++) {
            olusturbakirsandik(bakirsandiklar, 2);
        }
        for (int i = 0; i < sandiksayisi; i++) {
            olusturzumrutsandik(zumrutsandiklar, 2);
        }

        JButton yeniHaritaButton = new JButton("Yeni Harita");
        yeniHaritaButton.setBounds(10, 10, width, height);
        yeniHaritaButton.addActionListener((ActionEvent e) -> {
            yeniHaritaOlustur(width, height);
        });
        add(yeniHaritaButton);

        JButton baslatButonu = new JButton("Başlat");
        baslatButonu.setBounds(20, 20, width, height);
        add(baslatButonu);
        baslatButonu.addActionListener((ActionEvent e) -> {
            sonEkran sonPanel = new sonEkran(width, height, yazagaclar, kisagaclar, yazduvarlar, kisduvarlar, kiskayalar, yazkayalar, yazdaglar, kisdaglar, arilar, kuslar, altinsandiklar, gumussandiklar, bakirsandiklar, zumrutsandiklar);
            gosterPanel(sonPanel);
        });
    }

    private boolean checkEngelMatris(int x, int y, int boyut) {
        for (int i = x; i < x + boyut; i++) {
            for (int j = y; j < y + boyut; j++) {
                if (engelMatrisi[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkEngelMatrisduvar(int x, int y, int boyut) {
        for (int i = x; i < x + boyut; i++) {
            for (int j = y; j < y + 1; j++) {
                if (engelMatrisi[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkEngelMatriskus(int x, int y, int boyut) {
        for (int i = x; i < x + boyut; i++) {
            for (int j = y; j < y + boyut + 5; j++) {
                if (engelMatrisi[i][j]) {
                    return true;
                }
            }
            for (int j = y; j > y - 5; j--) {
                if (engelMatrisi[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkEngelMatrisari(int x, int y, int boyut) {
        for (int j = y; j < y + boyut; j++) {
            for (int i = x; i < x + boyut + 1; i++) {
                if (engelMatrisi[i][j]) {
                    return true;
                }
            }
            for (int i = x; i > x - 3; i--) {
                if (engelMatrisi[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    private void arilarıHareketEttir() {
        for (Ari ari : arilar) {

            int yeniX = ari.getLokasyon().getX() + hız * ari.getYonX();
            ari.setSayac(ari.getSayac() + 1);

            if (ari.getSayac() == 1 * cellSize) {
                ari.setYonX(ari.getYonX() * -1);
                ari.setSayac(0);
            }

            ari.getLokasyon().setX(yeniX);
        }
    }

    private void kuslarıHareketEttir() {
        for (Kus kus : kuslar) {

            int yeniY = kus.getLokasyon().getY() + hız * kus.getYonY();
            kus.setSayac(kus.getSayac() + 1);

            if (kus.getSayac() == 2 * cellSize) {
                kus.setYonY(kus.getYonY() * -1);
                kus.setSayac(0);
            }

            kus.getLokasyon().setY(yeniY);
        }
    }

    private void olusturari(ArrayList<Ari> arilar, int boyut, int sayac, int yonX, int yonY) {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(width - boyut - 2) + 2;
            y = rand.nextInt(height - boyut);
        } while (checkEngelMatrisari(x, y, 2));

        Ari Ari = new Ari(new Lokasyon(x * cellSize, y * cellSize), boyut, sayac, yonX, yonY);
        arilar.add(Ari);

        for (int i = x; i < x + 1 + boyut; i++) {
            for (int j = y; j < y + boyut; j++) {
                engelMatrisi[i][j] = true;
            }
        }
        for (int i = x; i > x - 1; i--) {
            for (int j = y; j < y + boyut; j++) {
                engelMatrisi[i][j] = true;
            }
        }
    }

    private void olusturkus(ArrayList<Kus> kuslar, int boyut, int sayac, int yonX, int yonY) {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(width - boyut);
            y = rand.nextInt(height - boyut - 4) + 4;
        } while (checkEngelMatriskus(x, y, 2));

        Kus kus = new Kus(new Lokasyon(x * cellSize, y * cellSize), boyut, sayac, yonX, yonY);
        kuslar.add(kus);

        for (int i = x; i < x + boyut; i++) {
            for (int j = y; j < y + boyut + 2; j++) {
                engelMatrisi[i][j] = true;
            }
        }
        for (int i = x; i < x + boyut; i++) {
            for (int j = y; j > y - 2; j--) {
                engelMatrisi[i][j] = true;
            }
        }
    }

    private void olusturyazagac(ArrayList<Agac> yazagaclar, int boyut) {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(width / 2 - boyut) + (width / 2);
            y = rand.nextInt(height - boyut);
        } while (checkEngelMatris(x, y, boyut));

        Agac agac = new Agac(new Lokasyon(x * cellSize, y * cellSize), boyut);
        yazagaclar.add(agac);

        for (int i = x; i < x + boyut; i++) {
            for (int j = y; j < y + boyut; j++) {
                engelMatrisi[i][j] = true;
            }
        }
    }

    private void olusturyazduvar(ArrayList<Duvar> yazduvarlar, int boyut) {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(width / 2 - boyut) + (width / 2);
            y = rand.nextInt(height - 1);
        } while (checkEngelMatrisduvar(x, y, boyut));

        Duvar duvar = new Duvar(new Lokasyon(x * cellSize, y * cellSize), boyut);
        yazduvarlar.add(duvar);

        for (int i = x; i < x + boyut; i++) {
            for (int j = y; j < y + 1; j++) {
                engelMatrisi[i][j] = true;
            }
        }
    }

    private void olusturyazkaya(ArrayList<Kaya> yazkayalar, int boyut) {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(width / 2 - boyut) + (width / 2);
            y = rand.nextInt(height - boyut);
        } while (checkEngelMatris(x, y, boyut));

        Kaya kaya = new Kaya(new Lokasyon(x * cellSize, y * cellSize), boyut);
        yazkayalar.add(kaya);

        for (int i = x; i < x + boyut; i++) {
            for (int j = y; j < y + boyut; j++) {
                engelMatrisi[i][j] = true;
            }
        }
    }

    private void olusturyazdag(ArrayList<Dag> yazdaglar, int boyut) {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(width / 2 - boyut) + (width / 2);
            y = rand.nextInt(height - boyut);
        } while (checkEngelMatris(x, y, boyut));

        Dag dag = new Dag(new Lokasyon(x * cellSize, y * cellSize), boyut);
        yazdaglar.add(dag);

        for (int i = x; i < x + boyut; i++) {
            for (int j = y; j < y + boyut; j++) {
                engelMatrisi[i][j] = true;
            }
        }
    }

    private void olusturkisagac(ArrayList<Agac> kisagaclar, int boyut) {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(width / 2 - boyut);
            y = rand.nextInt(height - boyut);
        } while (checkEngelMatris(x, y, boyut));

        Agac agac = new Agac(new Lokasyon(x * cellSize, y * cellSize), boyut);
        kisagaclar.add(agac);

        for (int i = x; i < x + boyut; i++) {
            for (int j = y; j < y + boyut; j++) {
                engelMatrisi[i][j] = true;
            }
        }
    }

    private void olusturkisduvar(ArrayList<Duvar> kisduvarlar, int boyut) {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(width / 2 - boyut);
            y = rand.nextInt(height - 1);
        } while (checkEngelMatrisduvar(x, y, boyut));

        Duvar duvar = new Duvar(new Lokasyon(x * cellSize, y * cellSize), boyut);
        kisduvarlar.add(duvar);

        for (int i = x; i < x + boyut; i++) {
            for (int j = y; j < y + 1; j++) {
                engelMatrisi[i][j] = true;
            }
        }
    }

    private void olusturkiskaya(ArrayList<Kaya> kiskayalar, int boyut) {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(width / 2 - boyut);
            y = rand.nextInt(height - boyut);
        } while (checkEngelMatris(x, y, boyut));

        Kaya kaya = new Kaya(new Lokasyon(x * cellSize, y * cellSize), boyut);
        kiskayalar.add(kaya);

        for (int i = x; i < x + boyut; i++) {
            for (int j = y; j < y + boyut; j++) {
                engelMatrisi[i][j] = true;
            }
        }
    }

    private void olusturkisdag(ArrayList<Dag> kisdaglar, int boyut) {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(width / 2 - boyut);
            y = rand.nextInt(height - boyut);
        } while (checkEngelMatris(x, y, boyut));

        Dag dag = new Dag(new Lokasyon(x * cellSize, y * cellSize), boyut);
        kisdaglar.add(dag);

        for (int i = x; i < x + boyut; i++) {
            for (int j = y; j < y + boyut; j++) {
                engelMatrisi[i][j] = true;
            }
        }
    }

    private void olusturaltinsandik(ArrayList<altinsandik> altinsandiklar, int boyut) {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(width - boyut);
            y = rand.nextInt(height - boyut);
        } while (checkEngelMatris(x, y, boyut));

        altinsandik altinsandik = new altinsandik(new Lokasyon(x * cellSize, y * cellSize), boyut);
        altinsandiklar.add(altinsandik);
    }

    private void olusturgumussandik(ArrayList<gumussandik> gumussandiklar, int boyut) {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(width - boyut);
            y = rand.nextInt(height - boyut);
        } while (checkEngelMatris(x, y, boyut));

        gumussandik gumussandik = new gumussandik(new Lokasyon(x * cellSize, y * cellSize), boyut);
        gumussandiklar.add(gumussandik);
    }

    private void olusturbakirsandik(ArrayList<bakirsandik> bakirsandiklar, int boyut) {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(width - boyut);
            y = rand.nextInt(height - boyut);
        } while (checkEngelMatris(x, y, boyut));

        bakirsandik bakirsandik = new bakirsandik(new Lokasyon(x * cellSize, y * cellSize), boyut);
        bakirsandiklar.add(bakirsandik);
    }

    private void olusturzumrutsandik(ArrayList<zumrutsandik> zumrutsandiklar, int boyut) {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(width - boyut);
            y = rand.nextInt(height - boyut);
        } while (checkEngelMatris(x, y, boyut));

        zumrutsandik zumrutsandik = new zumrutsandik(new Lokasyon(x * cellSize, y * cellSize), boyut);
        zumrutsandiklar.add(zumrutsandik);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, width * 10, height * 10, null);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        for (int x = 0; x <= width * 10; x += cellSize) {
            g2d.drawLine(x, 0, x, height * 10);
        }
        for (int y = 0; y <= height * 10; y += cellSize) {
            g2d.drawLine(0, y, width * 10, y);
        }

        arilarıHareketEttir();
        for (Ari ari : arilar) {
            ImageIcon ariIcon = new ImageIcon("C:\\Users\\Nisa\\Documents\\NetBeansProjects\\OyunEkrani\\resimler\\ari.png");
            Image ariImage = ariIcon.getImage();
            g.drawImage(ariImage, ari.getLokasyon().getX(), ari.getLokasyon().getY(), ari.getBoyut() * cellSize, ari.getBoyut() * cellSize, this);
        }
        kuslarıHareketEttir();
        for (Kus kus : kuslar) {
            ImageIcon kusIcon = new ImageIcon("C:\\Users\\Nisa\\Documents\\NetBeansProjects\\OyunEkrani\\resimler\\kus.png");
            Image kusImage = kusIcon.getImage();
            g.drawImage(kusImage, kus.getLokasyon().getX(), kus.getLokasyon().getY(), kus.getBoyut() * cellSize, kus.getBoyut() * cellSize, this);
        }

        for (altinsandik altinsandik : altinsandiklar) {
            ImageIcon AltinsandikIcon = new ImageIcon("C:\\Users\\Nisa\\Documents\\NetBeansProjects\\OyunEkrani\\resimler\\altinsandik.png");
            Image altinsandikImage = AltinsandikIcon.getImage();
            g.drawImage(altinsandikImage, altinsandik.getLokasyon().getX(), altinsandik.getLokasyon().getY(), altinsandik.getBoyut() * cellSize, altinsandik.getBoyut() * cellSize, this);
        }
        for (gumussandik gumussandik : gumussandiklar) {
            ImageIcon gumussandikIcon = new ImageIcon("C:\\Users\\Nisa\\Documents\\NetBeansProjects\\OyunEkrani\\resimler\\gumussandik.png");
            Image gumussandikImage = gumussandikIcon.getImage();
            g.drawImage(gumussandikImage, gumussandik.getLokasyon().getX(), gumussandik.getLokasyon().getY(), gumussandik.getBoyut() * cellSize, gumussandik.getBoyut() * cellSize, this);
        }
        for (bakirsandik bakirsandik : bakirsandiklar) {
            ImageIcon bakirsandikIcon = new ImageIcon("C:\\Users\\Nisa\\Documents\\NetBeansProjects\\OyunEkrani\\resimler\\bakirsandik.png");
            Image bakirsandikImage = bakirsandikIcon.getImage();
            g.drawImage(bakirsandikImage, bakirsandik.getLokasyon().getX(), bakirsandik.getLokasyon().getY(), bakirsandik.getBoyut() * cellSize, bakirsandik.getBoyut() * cellSize, this);
        }
        for (zumrutsandik zumrutsandik : zumrutsandiklar) {
            ImageIcon zumrutsandikIcon = new ImageIcon("C:\\Users\\Nisa\\Documents\\NetBeansProjects\\OyunEkrani\\resimler\\zumrutsandik.png");
            Image zumrutsandikImage = zumrutsandikIcon.getImage();
            g.drawImage(zumrutsandikImage, zumrutsandik.getLokasyon().getX(), zumrutsandik.getLokasyon().getY(), zumrutsandik.getBoyut() * cellSize, zumrutsandik.getBoyut() * cellSize, this);
        }
        for (Agac agac : yazagaclar) {
            ImageIcon agacIcon = new ImageIcon("C:\\Users\\Nisa\\Documents\\NetBeansProjects\\OyunEkrani\\resimler\\yazagac.png");
            Image agacImage = agacIcon.getImage();
            g.drawImage(agacImage, agac.getLokasyon().getX(), agac.getLokasyon().getY(), agac.getBoyut() * cellSize, agac.getBoyut() * cellSize, this);
        }
        for (Kaya kaya : yazkayalar) {
            ImageIcon kayaIcon = new ImageIcon("C:\\Users\\Nisa\\Documents\\NetBeansProjects\\OyunEkrani\\resimler\\yazkaya.png");
            Image kayaImage = kayaIcon.getImage();
            g.drawImage(kayaImage, kaya.getLokasyon().getX(), kaya.getLokasyon().getY(), kaya.getBoyut() * cellSize, kaya.getBoyut() * cellSize, this);
        }
        for (Duvar duvar : yazduvarlar) {
            ImageIcon duvarIcon = new ImageIcon("C:\\Users\\Nisa\\Documents\\NetBeansProjects\\OyunEkrani\\resimler\\yazduvar.jpg");
            Image duvarImage = duvarIcon.getImage();
            g.drawImage(duvarImage, duvar.getLokasyon().getX(), duvar.getLokasyon().getY(), duvar.getBoyut() * cellSize, cellSize, this);
        }
        for (Dag dag : yazdaglar) {
            ImageIcon dagIcon = new ImageIcon("C:\\Users\\Nisa\\Documents\\NetBeansProjects\\OyunEkrani\\resimler\\yazdag.png");
            Image dagImage = dagIcon.getImage();
            g.drawImage(dagImage, dag.getLokasyon().getX(), dag.getLokasyon().getY(), dag.getBoyut() * cellSize, dag.getBoyut() * cellSize, this);
        }
        for (Agac agac : kisagaclar) {
            ImageIcon agacIcon = new ImageIcon("C:\\Users\\Nisa\\Documents\\NetBeansProjects\\OyunEkrani\\resimler\\kisagac.png");
            Image agacImage = agacIcon.getImage();
            g.drawImage(agacImage, agac.getLokasyon().getX(), agac.getLokasyon().getY(), agac.getBoyut() * cellSize, agac.getBoyut() * cellSize, this);
        }
        for (Kaya kaya : kiskayalar) {
            ImageIcon kayaIcon = new ImageIcon("C:\\Users\\Nisa\\Documents\\NetBeansProjects\\OyunEkrani\\resimler\\kiskaya.png");
            Image kayaImage = kayaIcon.getImage();
            g.drawImage(kayaImage, kaya.getLokasyon().getX(), kaya.getLokasyon().getY(), kaya.getBoyut() * cellSize, kaya.getBoyut() * cellSize, this);
        }
        for (Duvar duvar : kisduvarlar) {
            ImageIcon duvarIcon = new ImageIcon("C:\\Users\\Nisa\\Documents\\NetBeansProjects\\OyunEkrani\\resimler\\kisduvar.jpg");
            Image duvarImage = duvarIcon.getImage();
            g.drawImage(duvarImage, duvar.getLokasyon().getX(), duvar.getLokasyon().getY(), duvar.getBoyut() * cellSize, cellSize, this);
        }
        for (Dag dag : kisdaglar) {
            ImageIcon dagIcon = new ImageIcon("C:\\Users\\Nisa\\Documents\\NetBeansProjects\\OyunEkrani\\resimler\\kisdag.png");
            Image dagImage = dagIcon.getImage();
            g.drawImage(dagImage, dag.getLokasyon().getX(), dag.getLokasyon().getY(), dag.getBoyut() * cellSize, dag.getBoyut() * cellSize, this);
        }
        repaint();

    }

    public void yeniHaritaOlustur(int width, int height) {
        kisagaclar.clear();
        yazagaclar.clear();
        yazduvarlar.clear();
        kisduvarlar.clear();
        yazkayalar.clear();
        kiskayalar.clear();
        yazdaglar.clear();
        kisdaglar.clear();
        arilar.clear();
        kuslar.clear();
        altinsandiklar.clear();
        gumussandiklar.clear();
        bakirsandiklar.clear();
        zumrutsandiklar.clear();

        this.width = width;
        this.height = height;

        oyunEkrani yeniOyunEkranı = new oyunEkrani(width, height);
        JFrame pencere = (JFrame) SwingUtilities.getWindowAncestor(this);
        pencere.getContentPane().removeAll();
        pencere.add(yeniOyunEkranı);
        pencere.revalidate();
    }

    public static boolean[][] getEngelMatrisi() {
        return engelMatrisi;
    }

    private void gosterPanel(JPanel panel) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.revalidate();
        frame.repaint();
        frame.pack();
        frame.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        JFrame pencere = new JFrame("Oyun Ekranı");
        pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pencere.setSize(oyunEkrani.WIDTH, oyunEkrani.HEIGHT);
        pencere.setResizable(false);
        oyunEkrani oyunEkranı = new oyunEkrani(oyunEkrani.WIDTH, oyunEkrani.HEIGHT);
        pencere.add(oyunEkranı);
        pencere.setVisible(true);

    }
}
