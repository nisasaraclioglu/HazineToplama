
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.util.Iterator;
import java.util.List;

public class sonEkran extends JPanel implements ActionListener {

    private int width;
    private int height;
    private int cellSize;
    private Image backgroundImage;
    private Image cloudImage;

    private static final int KARAKTER_BOYUTU = 1;
    private Karakter karakter;
    private Timer timer;

    boolean[][] engelMatrisi = oyunEkrani.getEngelMatrisi();

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
    private ArrayList<Lokasyon> altinSandikKonumlari = new ArrayList<>();
    private ArrayList<Lokasyon> gumusSandikKonumlari = new ArrayList<>();
    private ArrayList<Lokasyon> bakirSandikKonumlari = new ArrayList<>();
    private ArrayList<Lokasyon> zumrutSandikKonumlari = new ArrayList<>();
    private boolean[][] bulutMatris;
    private Sandik[][] sandikMatris;
    private boolean[][] yenimatris;
    private boolean[][] yenisandiklar;
    private final int hız = 1;

    public sonEkran(int width, int height, ArrayList<Agac> yazagaclar, ArrayList<Agac> kisagaclar, ArrayList<Duvar> yazduvarlar, ArrayList<Duvar> kisduvarlar, ArrayList<Kaya> kiskayalar, ArrayList<Kaya> yazkayalar, ArrayList<Dag> yazdaglar, ArrayList<Dag> kisdaglar, ArrayList<Ari> arilar, ArrayList<Kus> kuslar, ArrayList<altinsandik> altinsandiklar, ArrayList<gumussandik> gumussandiklar, ArrayList<bakirsandik> bakirsandiklar, ArrayList<zumrutsandik> zumrutsandiklar) {
        this.width = width;
        this.height = height;
        this.yazagaclar = yazagaclar;
        this.kisagaclar = kisagaclar;
        this.yazduvarlar = yazduvarlar;
        this.kisduvarlar = kisduvarlar;
        this.yazkayalar = yazkayalar;
        this.kiskayalar = kiskayalar;
        this.yazdaglar = yazdaglar;
        this.kisdaglar = kisdaglar;
        this.arilar = arilar;
        this.kuslar = kuslar;
        this.altinsandiklar = altinsandiklar;
        this.gumussandiklar = gumussandiklar;
        this.bakirsandiklar = bakirsandiklar;
        this.zumrutsandiklar = zumrutsandiklar;
        this.cellSize = Math.min(10, 10);

        for (altinsandik sandik : altinsandiklar) {
            altinSandikKonumlari.add(sandik.getLokasyon());
        }
        for (gumussandik sandik : gumussandiklar) {
            gumusSandikKonumlari.add(sandik.getLokasyon());
        }
        for (bakirsandik sandik : bakirsandiklar) {
            bakirSandikKonumlari.add(sandik.getLokasyon());
        }
        for (zumrutsandik sandik : zumrutsandiklar) {
            zumrutSandikKonumlari.add(sandik.getLokasyon());
        }
        this.yenimatris = new boolean[width][height];
        this.yenisandiklar = new boolean[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                yenimatris[x][y] = true;
            }
        }
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                yenimatris[x][y] = engelMatrisi[y][x];
            }
        }
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                yenisandiklar[x][y] = false;
            }
        }
        this.bulutMatris = new boolean[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                bulutMatris[x][y] = true;
            }
        }
        this.sandikMatris = new Sandik[width * cellSize][height * cellSize];
        for (altinsandik sandik : altinsandiklar) {
            Lokasyon lokasyon = sandik.getLokasyon();
            int x = lokasyon.getX();
            int y = lokasyon.getY();
            sandikMatris[x][y] = sandik;
        }
        for (gumussandik sandik : gumussandiklar) {
            Lokasyon lokasyon = sandik.getLokasyon();
            int x = lokasyon.getX();
            int y = lokasyon.getY();
            sandikMatris[x][y] = sandik;
        }
        for (bakirsandik sandik : bakirsandiklar) {
            Lokasyon lokasyon = sandik.getLokasyon();
            int x = lokasyon.getX();
            int y = lokasyon.getY();
            sandikMatris[x][y] = sandik;
        }
        for (zumrutsandik sandik : zumrutsandiklar) {
            Lokasyon lokasyon = sandik.getLokasyon();
            int x = lokasyon.getX();
            int y = lokasyon.getY();
            sandikMatris[x][y] = sandik;
        }

        int panelWidth = width * cellSize;
        int panelHeight = height * cellSize;
        setPreferredSize(new Dimension(panelWidth, panelHeight));

        this.backgroundImage = new ImageIcon("C:\\Users\\Nisa\\Documents\\NetBeansProjects\\OyunEkrani\\resimler\\cim.jpg").getImage();
        this.cloudImage = new ImageIcon("C:\\Users\\Nisa\\Documents\\NetBeansProjects\\OyunEkrani\\resimler\\bulut.png").getImage();

        Random rand = new Random();

        int randomX, randomY;
        do {
            randomX = rand.nextInt(width - KARAKTER_BOYUTU) * cellSize;
            randomY = rand.nextInt(height - KARAKTER_BOYUTU) * cellSize;
        } while (checkEngelMatris(randomX, randomY, KARAKTER_BOYUTU));

        karakter = new Karakter(new Lokasyon(randomX, randomY));
        setFocusable(true);

        timer = new Timer(100, this);

        timer.start();

        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");

        actionMap.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newX = karakter.getLokasyon().getX() - cellSize * KARAKTER_BOYUTU;
                int newY = karakter.getLokasyon().getY();
                if (!checkCollision(newX, newY)) {
                    karakter.moveLeft(cellSize);
                    updateBulutMatris();
                    repaint();
                }
            }
        });

        actionMap.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newX = karakter.getLokasyon().getX() + cellSize * KARAKTER_BOYUTU;
                int newY = karakter.getLokasyon().getY();
                if (!checkCollision(newX, newY)) {
                    karakter.moveRight(cellSize);
                    updateBulutMatris();
                    repaint();
                }
            }
        });

        actionMap.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newX = karakter.getLokasyon().getX();
                int newY = karakter.getLokasyon().getY() - cellSize;
                if (!checkCollision(newX, newY)) {
                    karakter.moveUp(cellSize);
                    updateBulutMatris();
                    repaint();
                }
            }
        });

        actionMap.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int newX = karakter.getLokasyon().getX();
                int newY = karakter.getLokasyon().getY() + cellSize * KARAKTER_BOYUTU;
                if (!checkCollision(newX, newY)) {
                    karakter.moveDown(cellSize);
                    updateBulutMatris();
                    repaint();
                }
            }
        });
    }

    class Node {

        int x, y;
        int g, h, f;
        Node parent;

        public Node(int x, int y, Node parent, int g, int h) {
            this.x = x;
            this.y = y;
            this.parent = parent;
            this.g = g;
            this.h = h;
            this.f = g + h;
        }
    }

    private void arilarıHareketEttir() {
        for (Ari ari : arilar) {

            int yeniX = ari.getLokasyon().getX() + hız * ari.getYonX();
            ari.setSayac(ari.getSayac() + 1);

            if (ari.getSayac() == 3 * cellSize) {
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

            if (kus.getSayac() == 5 * cellSize) {
                kus.setYonY(kus.getYonY() * -1);
                kus.setSayac(0);
            }

            kus.getLokasyon().setY(yeniY);
        }
    }

    private boolean checkEngelMatris(int x, int y, int boyut) {
        x = x / cellSize;
        y = y / cellSize;
        for (int i = x; i < x + boyut; i++) {
            for (int j = y; j < y + boyut; j++) {
                if (yenimatris[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Random rand = new Random();

        int currentX = karakter.getLokasyon().getX();
        int currentY = karakter.getLokasyon().getY();

        int direction = rand.nextInt(4);

        int newX = currentX;
        int newY = currentY;

        switch (direction) {
            case 0:
                newX -= cellSize;
                break;
            case 1:
                newX += cellSize;
                break;
            case 2:
                newY -= cellSize;
                break;
            case 3:
                newY += cellSize;
                break;
        }

        if (newX < 0 || newY < 0 || newX >= width * cellSize || newY >= height * cellSize || checkCollision(newX, newY)) {

            while (true) {
                direction = rand.nextInt(4);

                switch (direction) {
                    case 0:
                        newX = currentX - cellSize;
                        newY = currentY;
                        break;
                    case 1:
                        newX = currentX + cellSize;
                        newY = currentY;
                        break;
                    case 2:
                        newX = currentX;
                        newY = currentY - cellSize;
                        break;
                    case 3:
                        newX = currentX;
                        newY = currentY + cellSize;
                        break;
                }

                if (!checkCollision(newX, newY) && newX >= 0 && newY >= 0 && newX < width * cellSize && newY < height * cellSize) {
                    break;
                }
            }
        }

        karakter.getLokasyon().setX(newX);
        karakter.getLokasyon().setY(newY);
        Node start = new Node(karakter.getLokasyon().getX() / cellSize, karakter.getLokasyon().getY() / cellSize, null, 0, 0);

        List<Sandik> detectedSandiklar = detectNearbySandiklar(4);

        if (!detectedSandiklar.isEmpty()) {
            Sandik closestSandik = findClosestSandik(detectedSandiklar, start);

            Node goal = new Node(closestSandik.getLokasyon().getX() / cellSize, closestSandik.getLokasyon().getY() / cellSize, null, 0, 0);
            List<Node> path = findPath(start, goal);

            if (path != null && path.size() > 1) {
                Node nextStep = path.get(1);
                karakter.getLokasyon().setX(nextStep.x * cellSize);
                karakter.getLokasyon().setY(nextStep.y * cellSize);
            }
        }

        if (altinsandiklar.isEmpty() && gumussandiklar.isEmpty() && bakirsandiklar.isEmpty() && zumrutsandiklar.isEmpty()) {
            timer.stop();
            System.out.println("Tüm sandıklar toplandı.");

            if (!altinSandikKonumlari.isEmpty()) {
                for (Lokasyon lokasyon : altinSandikKonumlari) {
                    System.out.println("Altın sandik: " + lokasyon.getX() + ", " + lokasyon.getY());
                }
            }

            if (!gumusSandikKonumlari.isEmpty()) {
                for (Lokasyon lokasyon : gumusSandikKonumlari) {
                    System.out.println("Gümüş sandik: " + lokasyon.getX() + ", " + lokasyon.getY());
                }
            }

            if (!bakirSandikKonumlari.isEmpty()) {
                for (Lokasyon lokasyon : bakirSandikKonumlari) {
                    System.out.println("Bakır sandik: " + lokasyon.getX() + ", " + lokasyon.getY());
                }
            }

            if (!zumrutSandikKonumlari.isEmpty()) {
                for (Lokasyon lokasyon : zumrutSandikKonumlari) {
                    System.out.println("Zümrüt sandik: " + lokasyon.getX() + ", " + lokasyon.getY());
                }
            }
        }

        updateBulutMatris();
        repaint();
    }

    private List<Node> findPath(Node start, Node goal) {
        List<Node> openList = new ArrayList<>();
        List<Node> closedList = new ArrayList<>();

        openList.add(start);

        while (!openList.isEmpty()) {
            Node current = openList.remove(0);

            if (current.x == goal.x && current.y == goal.y) {
                return constructPath(current);
            }

            closedList.add(current);

            for (Node neighbor : getNeighbors(current)) {
                if (closedList.contains(neighbor)) {
                    continue;
                }
                if (!openList.contains(neighbor)) {
                    openList.add(neighbor);
                }
            }
        }

        return null;
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        int[] directions = {-1, 0, 1};
        for (int dx : directions) {
            for (int dy : directions) {
                if (dx == 0 && dy == 0) {
                    continue;
                }
                int newX = node.x + dx;
                int newY = node.y + dy;

                if (newX >= 0 && newX < width && newY >= 0 && newY < height && !engelMatrisi[newX][newY]) {
                    neighbors.add(new Node(newX, newY, node, node.g + 1, 0));
                }
            }
        }
        return neighbors;
    }

    private List<Node> constructPath(Node node) {
        List<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(0, node);
            node = node.parent;
        }
        return path;
    }

    private List<Sandik> detectNearbySandiklar(int detectionRange) {
        List<Sandik> detectedSandiklar = new ArrayList<>();
        int karakterX = karakter.getLokasyon().getX() / cellSize;
        int karakterY = karakter.getLokasyon().getY() / cellSize;

        List<Sandik> allSandiklar = new ArrayList<>();
        allSandiklar.addAll(altinsandiklar);
        allSandiklar.addAll(gumussandiklar);
        allSandiklar.addAll(bakirsandiklar);
        allSandiklar.addAll(zumrutsandiklar);

        for (Sandik sandik : allSandiklar) {
            int sandikX = sandik.getLokasyon().getX() / cellSize;
            int sandikY = sandik.getLokasyon().getY() / cellSize;
            int distanceX = Math.abs(sandikX - karakterX);
            int distanceY = Math.abs(sandikY - karakterY);

            if (distanceX <= detectionRange && distanceY <= detectionRange) {
                detectedSandiklar.add(sandik);
            }
        }

        return detectedSandiklar;
    }

    private Sandik findClosestSandik(List<Sandik> sandiklar, Node start) {
        Sandik closest = null;
        int minDistance = Integer.MAX_VALUE;

        for (Sandik sandik : sandiklar) {
            int sandikX = sandik.getLokasyon().getX() / cellSize;
            int sandikY = sandik.getLokasyon().getY() / cellSize;
            int distance = Math.abs(sandikX - start.x) + Math.abs(sandikY - start.y);

            if (distance < minDistance) {
                minDistance = distance;
                closest = sandik;
            }
        }

        return closest;
    }

    private void updateBulutMatris() {
        int karakterX = karakter.getLokasyon().getX() / cellSize;
        int karakterY = karakter.getLokasyon().getY() / cellSize;
        for (int x = Math.max(0, karakterX - 3); x < Math.min(width, karakterX + 4); x++) {
            for (int y = Math.max(0, karakterY - 3); y < Math.min(height, karakterY + 4); y++) {
                bulutMatris[x][y] = false;
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, width * cellSize, height * cellSize, null);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        for (int x = 0; x <= width * 10; x += cellSize) {
            g2d.drawLine(x, 0, x, height * 10);
        }
        for (int y = 0; y <= height * 10; y += cellSize) {
            g2d.drawLine(0, y, width * 10, y);
        }

        ImageIcon icon = new ImageIcon("C:\\Users\\Nisa\\Documents\\NetBeansProjects\\OyunEkrani\\resimler\\megumi.png");
        Image image = icon.getImage();

        g.drawImage(image, karakter.getLokasyon().getX(), karakter.getLokasyon().getY(), KARAKTER_BOYUTU * cellSize, KARAKTER_BOYUTU * cellSize, this);

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
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (bulutMatris[x][y]) {
                    g.drawImage(cloudImage, x * cellSize, y * cellSize, cellSize, cellSize, this);
                }
            }
        }
    }

    private boolean checkCollision(int x, int y) {
        if (x < 0 || y < 0 || x >= width * cellSize || y >= height * cellSize) {
            return true;
        }
        Rectangle karakterBounds = new Rectangle(x, y, KARAKTER_BOYUTU * cellSize, KARAKTER_BOYUTU * cellSize);

        for (Iterator<altinsandik> iterator = altinsandiklar.iterator(); iterator.hasNext();) {
            altinsandik sandik = iterator.next();
            Rectangle sandikBounds = new Rectangle(sandik.getLokasyon().getX(), sandik.getLokasyon().getY(), sandik.getBoyut() * cellSize, sandik.getBoyut() * cellSize);
            if (karakterBounds.intersects(sandikBounds)) {
                iterator.remove();
                System.out.println("Altın sandik bulundu " + sandik.getLokasyon().getX() + "," + sandik.getLokasyon().getY());
                return true;
            }
        }
        for (Iterator<gumussandik> iterator = gumussandiklar.iterator(); iterator.hasNext();) {
            gumussandik sandik = iterator.next();
            Rectangle sandikBounds = new Rectangle(sandik.getLokasyon().getX(), sandik.getLokasyon().getY(), sandik.getBoyut() * cellSize, sandik.getBoyut() * cellSize);
            if (karakterBounds.intersects(sandikBounds)) {
                iterator.remove();
                System.out.println("Gumus sandik bulundu " + sandik.getLokasyon().getX() + "," + sandik.getLokasyon().getY());
                return true;
            }
        }
        for (Iterator<bakirsandik> iterator = bakirsandiklar.iterator(); iterator.hasNext();) {
            bakirsandik sandik = iterator.next();
            Rectangle sandikBounds = new Rectangle(sandik.getLokasyon().getX(), sandik.getLokasyon().getY(), sandik.getBoyut() * cellSize, sandik.getBoyut() * cellSize);
            if (karakterBounds.intersects(sandikBounds)) {
                iterator.remove();
                System.out.println("Bakır sandik bulundu " + sandik.getLokasyon().getX() + "," + sandik.getLokasyon().getY());
                return true;
            }
        }
        for (Iterator<zumrutsandik> iterator = zumrutsandiklar.iterator(); iterator.hasNext();) {
            zumrutsandik sandik = iterator.next();
            Rectangle sandikBounds = new Rectangle(sandik.getLokasyon().getX(), sandik.getLokasyon().getY(), sandik.getBoyut() * cellSize, sandik.getBoyut() * cellSize);
            if (karakterBounds.intersects(sandikBounds)) {
                iterator.remove();
                System.out.println("Zümrüt sandik bulundu " + sandik.getLokasyon().getX() + "," + sandik.getLokasyon().getY());
                return true;
            }
        }

        int karakterCellX = x / cellSize;
        int karakterCellY = y / cellSize;

        if (engelMatrisi[karakterCellX][karakterCellY]) {
            return true;
        }
        return false;

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Oyun Ekranı");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(WIDTH, HEIGHT);
            frame.setResizable(false);

            sonEkran sonEkranPanel = new sonEkran(sonEkran.WIDTH, sonEkran.HEIGHT, new ArrayList<Agac>(),
                    new ArrayList<Agac>(), new ArrayList<Duvar>(), new ArrayList<Duvar>(), new ArrayList<Kaya>(),
                    new ArrayList<Kaya>(), new ArrayList<Dag>(), new ArrayList<Dag>(), new ArrayList<Ari>(),
                    new ArrayList<Kus>(), new ArrayList<altinsandik>(), new ArrayList<gumussandik>(), new ArrayList<bakirsandik>(), new ArrayList<zumrutsandik>());

            frame.add(sonEkranPanel);
            sonEkranPanel.setFocusable(true);

            frame.setVisible(true);
        });
    }

}
