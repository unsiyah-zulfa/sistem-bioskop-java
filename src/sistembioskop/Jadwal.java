package sistembioskop;

import java.util.ArrayList;

public class Jadwal {
    private int id;
    private Film film;
    private String jamTayang;
    private String studio;
    private int kapasitas;
    private ArrayList<Kursi> daftarKursi;

    public Jadwal(int id, Film film, String jamTayang, String studio, int kapasitas) {
        this.id = id;
        this.film = film;
        this.jamTayang = jamTayang;
        this.studio = studio;
        this.kapasitas = kapasitas;
        this.daftarKursi = new ArrayList<>();

        // Buat kursi otomatis sesuai kapasitas
    for (int b = 0; b < 3; b++) {
        for (int kolom = 1; kolom <= 5; kolom++) {
            char hurufBaris = (char) ('A' + b);
            String nomor = hurufBaris + String.valueOf(kolom);
            daftarKursi.add(new Kursi(nomor, hurufBaris, kolom));
            }
        }
    }

    // Getter
    public int getId() { return id; }
    public Film getFilm() {return film; }
    public String getJamTayang() { return jamTayang; }
    public String getStudio() { return studio; }
    public int getKapasitas() { return kapasitas; }
    public ArrayList<Kursi> getDaftarKursi() { return daftarKursi; }

    // Setter
    public void setFilm(Film film) { this.film = film; }
    public void setJamTayang(String jamTayang) { this.jamTayang = jamTayang; }
    public void setStudio(String studio) { this.studio = studio; }
}