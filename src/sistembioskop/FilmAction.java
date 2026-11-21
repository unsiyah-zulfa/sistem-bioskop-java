/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistembioskop;

/**
 *
 * @author UNSIYAH
 */
public class FilmAction extends Film{
    private String rating;
    
    public FilmAction(int id, String judul, int durasi, String rating){
      super(id, judul, "Action", durasi)  ;
      this.rating = rating;
    }
    @Override
    public void tampilkanInfo(){
            System.out.println("Film Action" + getJudul() + " | Rating " + rating);
    }
}
