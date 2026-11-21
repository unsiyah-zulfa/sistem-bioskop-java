package sistembioskop;
import javax.swing.SwingUtilities;

public class SistemBioskop {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
    }
}