class Karakter {
    private Lokasyon lokasyon;

    public Karakter(Lokasyon lokasyon) {
        this.lokasyon = lokasyon;
    }

    public Lokasyon getLokasyon() {
        return lokasyon;
    }
    
    public void setLokasyonX(int x) {
        this.lokasyon.setX(x);
    }
    
    public void setLokasyonY(int y) {
        this.lokasyon.setY(y);
    }

    public void moveLeft(int step) {
        lokasyon.setX(lokasyon.getX() - step);
    }

    public void moveRight(int step) {
        lokasyon.setX(lokasyon.getX() + step);
    }

    public void moveUp(int step) {
        lokasyon.setY(lokasyon.getY() - step);
    }

    public void moveDown(int step) {
        lokasyon.setY(lokasyon.getY() + step);
    }}