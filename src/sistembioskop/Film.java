package sistembioskop;

public class Film {
    private int id;
    private String judul;
    private String genre;
    private int durasi;

    
    public Film(int id, String judul, String genre, int durasi) {
        this.id = id;
        this.judul = judul;
        this.genre = genre;
        this.durasi = durasi;

    }
   
    public void tampilkanInfo(){
        System.out.println("ID Film: "+ id);
        System.out.println("Judul Film: "+ judul);
        System.out.println("Genre Film: "+ genre);
        System.out.println("Durasi Film: "+ durasi);
        
    }
    // Getter buat untuk mengambil nilai atribut dari luar kelas
    public int getId() {
        return id; }
    public String getJudul() {
        return judul; }
    public String getGenre() {
        return genre; }
    public int getDurasi() {
        return durasi; }

    
    // Setter buat untuk mengubah nilai atribut dari luar kelas
    public void setJudul(String judul) {
        this.judul = judul; }
    public void setGenre(String genre) {
        this.genre = genre; }
    public void setDurasi(int durasi) {
        this.durasi = durasi; }

    
//    public static void main(String[] args) {
//        Film f = new Film(67, "aselole", "komedi", 123);
//        
//        //getter
//        String Judul = f.getJudul();
//        System.out.println("Judul awal: " + Judul);
//        
//        //setter
//        f.setJudul("Spongebob");
//        System.out.println("Judul baru: " + Judul);
//                
//    }
}
