package sistembioskop;

import java.util.ArrayList;

public class Bioskop {
    private ArrayList<Film> daftarFilm;
    private ArrayList<Jadwal> daftarJadwal;
    private ArrayList<Pemesanan> daftarPemesanan;
    private ArrayList<Pelanggan> daftarPelanggan;
    private int nextPemesananId;

    public Bioskop() {
        daftarFilm = new ArrayList<>();
        daftarJadwal = new ArrayList<>();
        daftarPemesanan = new ArrayList<>();
        daftarPelanggan = new ArrayList<>();
        nextPemesananId = 1;
    }

    // Film
    public void tambahFilm(Film f) { daftarFilm.add(f); }
    public void hapusFilm(int index) { daftarFilm.remove(index); }
    public ArrayList<Film> getDaftarFilm() { return daftarFilm; }

    // Jadwal
    public void tambahJadwal(Jadwal j) { daftarJadwal.add(j); }
    public void hapusJadwal(int index) { daftarJadwal.remove(index); }
    public ArrayList<Jadwal> getDaftarJadwal() { return daftarJadwal; }

    // Pemesanan
    public ArrayList<Pemesanan> getDaftarPemesanan() { return daftarPemesanan; }
    
    //pelanggan
    public void tambahPelanggan(Pelanggan p) { daftarPelanggan.add(p); }
    public void hapusPelanggan(int index) { daftarPelanggan.remove(index); }
    public ArrayList<Pelanggan> getDaftarPelanggan() { return daftarPelanggan; }

    // jadwal berdasarkan film
    public ArrayList<Jadwal> getJadwalByFilm(Film film) {
        ArrayList<Jadwal> hasil = new ArrayList<>();
        for (Jadwal j : daftarJadwal) {
            if (j.getFilm() == film) hasil.add(j);
        }
        return hasil;
    }
    // untuk pesan kursi
    public Pemesanan pesanKursi(Jadwal jadwal, Kursi kursi) {
        if (!kursi.isTersedia()) return null;
        kursi.setTersedia(false);
        Pemesanan p = new Pemesanan(nextPemesananId++, jadwal, kursi);
        daftarPemesanan.add(p);
        return p;
    }

    // untuk batal pemesanan
    public void batalPemesanan(int id) {
        for (Pemesanan p : daftarPemesanan) {
            if (p.getId() == id) {
                p.getKursi().setTersedia(true);
                p.setStatus("Dibatalkan");
                return;
            }
        }
    }
}