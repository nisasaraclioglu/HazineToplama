class Sandik {

    private Lokasyon lokasyon;
    private int boyut;

    public Sandik(Lokasyon lokasyon, int boyut) {
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
class altinsandik extends Sandik{
    public altinsandik(Lokasyon lokasyon, int boyut) {
        super(lokasyon, boyut);
    }
}

class gumussandik extends Sandik{
    public gumussandik(Lokasyon lokasyon, int boyut) {
        super(lokasyon, boyut);
    }
}

class bakirsandik extends Sandik{
    public bakirsandik(Lokasyon lokasyon, int boyut) {
        super(lokasyon, boyut);
    }
}

class zumrutsandik extends Sandik{
    public zumrutsandik(Lokasyon lokasyon, int boyut) {
        super(lokasyon, boyut);
    }
}