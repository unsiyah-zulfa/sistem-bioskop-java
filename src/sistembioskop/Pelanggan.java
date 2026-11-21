
package sistembioskop;

public class Pelanggan {
    private int id;
    private String nama;
    private String nomorHp;
    private String email;

    
    public Pelanggan(int id, String nama, String nomorHp, String email) {
        this.id = id;
        this.nama = nama;
        this.nomorHp = nomorHp ;
        this.email = email;

    }

    // Getter buat untuk mengambil nilai atribut dari luar kelas
    public int getId() {
        return id; }
    public String getNama() {
        return nama; }
    public String getNomorHp() {
        return nomorHp; }
    public String getEmail() {
        return email; }

    
    // Setter buat untuk mengubah nilai atribut dari luar kelas
    public void setNama(String nama) {
        this.nama= nama; }
    public void setNomorHp(String nomorHp) {
        this.nomorHp = nomorHp; }
    public void setEmail(String email) {
        this.email = email; }
}
