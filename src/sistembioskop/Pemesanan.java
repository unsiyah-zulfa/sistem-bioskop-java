package sistembioskop;

public class Pemesanan {
    private int id;
    private Jadwal jadwal;
    private Kursi kursi;
    private String status;

    public Pemesanan(int id, Jadwal jadwal, Kursi kursi) {
        this.id = id;
        this.jadwal = jadwal;
        this.kursi = kursi;
        this.status = "Aktif";
    }

    // Getter
    public int getId() { return id; }
    public Jadwal getJadwal() { return jadwal; }
    public Kursi getKursi() { return kursi; }
    public String getStatus() { return status; }

    // Setter
    public void setStatus(String status) { this.status = status; }
}