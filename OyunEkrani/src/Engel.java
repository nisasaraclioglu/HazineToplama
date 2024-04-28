
class Engel {

    private Lokasyon lokasyon;
    private int boyut;

    public Engel(Lokasyon lokasyon, int boyut) {
        this.lokasyon = lokasyon;
        this.boyut = boyut;
    }

    public Lokasyon getLokasyon() {
        return lokasyon;
    }

    public int getBoyut() {
        return boyut;
    }

}

class HareketsizEngel extends Engel {

    public HareketsizEngel(Lokasyon lokasyon, int boyut) {
        super(lokasyon, boyut);
    }

}

class HareketliEngel extends Engel {

    private int sayac;
    private int yonX;
    private int yonY;

    public HareketliEngel(Lokasyon lokasyon, int boyut, int sayac, int yonX, int yonY) {
        super(lokasyon, boyut);
        this.sayac = sayac;
        this.yonX = 1;
        this.yonY = 1;

    }

    public int getYonX() {
        return yonX;
    }

    public void setYonX(int yonX) {
        this.yonX = yonX;
    }

    public int getYonY() {
        return yonY;
    }

    public void setYonY(int yonY) {
        this.yonY = yonY;
    }
    
    public int getSayac() {
        return sayac;
    }
    
    public void setSayac(int sayac) {
        this.sayac = sayac;
    }

}

class Agac extends HareketsizEngel {

    public Agac(Lokasyon lokasyon, int boyut) {
        super(lokasyon, boyut);
    }

}

class Kaya extends HareketsizEngel {

    public Kaya(Lokasyon lokasyon, int boyut) {
        super(lokasyon, boyut);
    }

}

class Duvar extends HareketsizEngel {

    public Duvar(Lokasyon lokasyon, int boyut) {
        super(lokasyon, boyut);
    }

}

class Dag extends HareketsizEngel {

    public Dag(Lokasyon lokasyon, int boyut) {
        super(lokasyon, boyut);
    }

}

class Ari extends HareketliEngel {

    public Ari(Lokasyon lokasyon, int boyut, int sayac, int yonX, int yonY) {
        super(lokasyon, boyut, sayac, yonX, yonY);

    }

}

class Kus extends HareketliEngel {

    public Kus(Lokasyon lokasyon, int boyut, int sayac, int yonX, int yonY) {
        super(lokasyon, boyut, sayac, yonX, yonY);
    }
}
