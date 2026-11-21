package sistembioskop;

public class Kursi {
    private String nomor;
    private char baris;
    private int kolom;
    private boolean tersedia;

    public Kursi(String nomor, char baris, int kolom) {
        this.nomor = nomor;
        this.baris = baris;
        this.kolom = kolom;
        this.tersedia = true;
    }
    
    // Getter
    public String getNomor() { return nomor; }
    public char getBaris() { return baris; }
    public int getKolom() { return kolom; }
    public boolean isTersedia() { return tersedia; }

    // Setter
    public void setTersedia(boolean tersedia) { this.tersedia = tersedia; }
}
