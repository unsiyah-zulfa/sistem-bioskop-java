package sistembioskop;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.ArrayList;

public class MainWindow extends JFrame {

    private Bioskop bioskop;

    // Tab
    private JTabbedPane tabbedPane;

    // Tab Film
    private JTable tabelFilm;
    private DefaultTableModel modelFilm;

    // Tab Jadwal
    private JTable tabelJadwal;
    private DefaultTableModel modelJadwal;

    // Tab Pesan Tiket
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JTable listFilmPesan;
    private DefaultTableModel modelFilmPesan;
    private JTable listJadwalPesan;
    private DefaultTableModel modelJadwalPesan;
    private JPanel gridKursiPanel;
    private JLabel labelFilmDipilih;
    private JLabel labelJadwalDipilih;
    private JLabel labelKursiDipilih;
    private Film filmDipilih;
    private Jadwal jadwalDipilih;
    private Kursi kursiDipilih;

    //Pemesanan
    private JTable tabelPemesanan;
    private DefaultTableModel modelPemesanan;
    
    //pelanggan
    private JTable tabelPelanggan;
    private DefaultTableModel modelPelanggan;

    public MainWindow() {
        bioskop = new Bioskop();
        setupData();
        setupUI();
    }

    private void setupData() {
        Film f1 = new Film(1, "Agak Laen: Menyala Pantiku", "Comedy", 181);
        Film f2 = new Film(2, "Jumanji", "Action", 152);
        Film f3 = new Film(3, "World War Z", "Action", 169);
        Film f4 = new Film(4, "A Quiet Place", "Horor", 111);
        Film f5 = new Film(5, "The Walking Dead", "Horor", 155);
        Film f6 = new Film(6, "Frozen", "Animation", 123);
        Film f7 = new Film(7, "Sekawan Limo: Gunung Klawih", "Comedy", 131);
        Film f8 = new Film(8, "Demon Slayer: Kimetsu no Yaiba", "Action", 140);
        Film f9 = new Film(9, "Train To Busan", "Horor", 160);
        Film f10 = new Film(10, "Kimi No Na Wa", "Romance", 169);
        bioskop.tambahFilm(f1);
        bioskop.tambahFilm(f2);
        bioskop.tambahFilm(f3);
        bioskop.tambahFilm(f4);
        bioskop.tambahFilm(f5);
        bioskop.tambahFilm(f6);
        bioskop.tambahFilm(f7);
        bioskop.tambahFilm(f8);
        bioskop.tambahFilm(f9);
        bioskop.tambahFilm(f10);
        bioskop.tambahJadwal(new Jadwal(1, f1, "10:00", "Studio 1", 15));
        bioskop.tambahJadwal(new Jadwal(2, f1, "14:00", "Studio 2", 15));
        bioskop.tambahJadwal(new Jadwal(3, f2, "11:00", "Studio 1", 15));
        bioskop.tambahJadwal(new Jadwal(4, f3, "13:00", "Studio 3", 15));
        bioskop.tambahJadwal(new Jadwal(5, f1, "10:00", "Studio 3", 15));
        bioskop.tambahJadwal(new Jadwal(6, f1, "14:00", "Studio 2", 15));
        bioskop.tambahJadwal(new Jadwal(7, f2, "11:00", "Studio 1", 15));
        bioskop.tambahJadwal(new Jadwal(8, f3, "13:00", "Studio 3", 15));
        bioskop.tambahJadwal(new Jadwal(9, f2, "11:00", "Studio 1", 15));
        bioskop.tambahJadwal(new Jadwal(10, f3, "13:00", "Studio 2", 15));
        
        
        FilmAction fa = new FilmAction(11, "John Wick", 169, "R");
        fa.tampilkanInfo(); // → muncul di console
    }

    private void setupUI() {
        setTitle("Sistem Bioskop");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        add(tabbedPane);
        tabbedPane.addChangeListener(e -> {
            if (tabbedPane.getSelectedIndex() == 2) {
                tampilkanHalamanFilmPesan();
            }
        });
        setupTabFilm();
        setupTabJadwal();
        setupTabPesan();
        setupTabPemesanan();
        setupTabPelanggan();
    }

    //film

    private void setupTabFilm() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] kolom = {"ID", "Judul", "Genre", "Durasi (menit)"};
        modelFilm = new DefaultTableModel(kolom, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tabelFilm = new JTable(modelFilm);
        tabelFilm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(new JScrollPane(tabelFilm), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton btnTambah = new JButton("Tambah Film");
        JButton btnEdit = new JButton("Edit Film");
        JButton btnHapus = new JButton("Hapus Film");
        btnTambah.setBackground(new Color(76, 175, 80));
        btnTambah.setForeground(Color.WHITE);
        btnEdit.setBackground(new Color(33, 150, 243));
        btnEdit.setForeground(Color.WHITE);
        btnHapus.setBackground(new Color(244, 67, 54));
        btnHapus.setForeground(Color.WHITE);
        btnPanel.add(btnTambah);
        btnPanel.add(btnEdit);
        btnPanel.add(btnHapus);
        panel.add(btnPanel, BorderLayout.SOUTH);

        btnTambah.addActionListener(e -> tambahFilm());
        btnEdit.addActionListener(e -> editFilm());
        btnHapus.addActionListener(e -> hapusFilm());

        tabbedPane.addTab("Film", panel);
        refreshTabelFilm();
    }

    private void refreshTabelFilm() {
        modelFilm.setRowCount(0);
        for (Film f : bioskop.getDaftarFilm()) {
            modelFilm.addRow(new Object[]{f.getId(), f.getJudul(), f.getGenre(), f.getDurasi()});
        }
    }

    private void tambahFilm() {
        JTextField inputJudul = new JTextField();
        JTextField inputGenre = new JTextField();
        JTextField inputDurasi = new JTextField();

        Object[] fields = {"Judul:", inputJudul, "Genre:", inputGenre, "Durasi (menit):", inputDurasi};
        int result = JOptionPane.showConfirmDialog(this, fields, "Tambah Film", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            if (inputJudul.getText().isEmpty() || inputGenre.getText().isEmpty() || inputDurasi.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!");
                return;
            }
            int id = bioskop.getDaftarFilm().size() + 1;
            bioskop.tambahFilm(new Film(id, inputJudul.getText(), inputGenre.getText(), Integer.parseInt(inputDurasi.getText())));
            refreshTabelFilm();
        }
    }

    private void editFilm() {
        int row = tabelFilm.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Pilih film yang ingin diedit!"); return; }

        Film film = bioskop.getDaftarFilm().get(row);
        JTextField inputJudul = new JTextField(film.getJudul());
        JTextField inputGenre = new JTextField(film.getGenre());
        JTextField inputDurasi = new JTextField(String.valueOf(film.getDurasi()));

        Object[] fields = {"Judul:", inputJudul, "Genre:", inputGenre, "Durasi (menit):", inputDurasi};
        int result = JOptionPane.showConfirmDialog(this, fields, "Edit Film", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            film.setJudul(inputJudul.getText());
            film.setGenre(inputGenre.getText());
            film.setDurasi(Integer.parseInt(inputDurasi.getText()));
            refreshTabelFilm();
            refreshTabelJadwal();
        }
    }

    private void hapusFilm() {
        int row = tabelFilm.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Pilih film yang ingin dihapus!"); return; }

        Film film = bioskop.getDaftarFilm().get(row);
        int jawab = JOptionPane.showConfirmDialog(this, "Hapus film " + film.getJudul() + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (jawab == JOptionPane.YES_OPTION) {
            bioskop.hapusFilm(row);
            refreshTabelFilm();
            refreshTabelJadwal();
        }
    }

    //jadwal

    private void setupTabJadwal() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] kolom = {"ID", "Film", "Jam Tayang", "Studio", "Kapasitas"};
        modelJadwal = new DefaultTableModel(kolom, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tabelJadwal = new JTable(modelJadwal);
        tabelJadwal.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(new JScrollPane(tabelJadwal), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton btnTambah = new JButton("Tambah Jadwal");
        JButton btnEdit = new JButton("Edit Jadwal");
        JButton btnHapus = new JButton("Hapus Jadwal");
        btnTambah.setBackground(new Color(76, 175, 80));
        btnTambah.setForeground(Color.WHITE);
        btnEdit.setBackground(new Color(33, 150, 243));
        btnEdit.setForeground(Color.WHITE);
        btnHapus.setBackground(new Color(244, 67, 54));
        btnHapus.setForeground(Color.WHITE);
        btnPanel.add(btnTambah);
        btnPanel.add(btnEdit);
        btnPanel.add(btnHapus);
        panel.add(btnPanel, BorderLayout.SOUTH);

        btnTambah.addActionListener(e -> tambahJadwal());
        btnEdit.addActionListener(e -> editJadwal());
        btnHapus.addActionListener(e -> hapusJadwal());

        tabbedPane.addTab("Jadwal", panel);
        refreshTabelJadwal();
    }

    private void refreshTabelJadwal() {
        modelJadwal.setRowCount(0);
        for (Jadwal j : bioskop.getDaftarJadwal()) {
            modelJadwal.addRow(new Object[]{j.getId(), j.getFilm().getJudul(), j.getJamTayang(), j.getStudio(), j.getKapasitas()});
        }
    }

    private void tambahJadwal() {
        if (bioskop.getDaftarFilm().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tambah film terlebih dahulu!");
            return;
        }

        ArrayList<Film> daftarFilm = bioskop.getDaftarFilm();
        String[] namaFilm = new String[daftarFilm.size()];
        for (int i = 0; i < daftarFilm.size(); i++) namaFilm[i] = daftarFilm.get(i).getJudul();

        JComboBox<String> comboFilm = new JComboBox<>(namaFilm);
        JTextField inputJam = new JTextField();
        JTextField inputStudio = new JTextField();
        JTextField inputKapasitas = new JTextField();

        Object[] fields = {"Film:", comboFilm, "Jam Tayang:", inputJam, "Studio:", inputStudio, "Kapasitas:", inputKapasitas};
        int result = JOptionPane.showConfirmDialog(this, fields, "Tambah Jadwal", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            Film film = daftarFilm.get(comboFilm.getSelectedIndex());
            int id = bioskop.getDaftarJadwal().size() + 1;
            bioskop.tambahJadwal(new Jadwal(id, film, inputJam.getText(), inputStudio.getText(), Integer.parseInt(inputKapasitas.getText())));
            refreshTabelJadwal();
        }
    }

    private void editJadwal() {
        int row = tabelJadwal.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Pilih jadwal yang ingin diedit!"); return; }

        Jadwal jadwal = bioskop.getDaftarJadwal().get(row);
        ArrayList<Film> daftarFilm = bioskop.getDaftarFilm();
        String[] namaFilm = new String[daftarFilm.size()];
        int indexFilm = 0;
        for (int i = 0; i < daftarFilm.size(); i++) {
            namaFilm[i] = daftarFilm.get(i).getJudul();
            if (daftarFilm.get(i) == jadwal.getFilm()) indexFilm = i;
        }

        JComboBox<String> comboFilm = new JComboBox<>(namaFilm);
        comboFilm.setSelectedIndex(indexFilm);
        JTextField inputJam = new JTextField(jadwal.getJamTayang());
        JTextField inputStudio = new JTextField(jadwal.getStudio());

        Object[] fields = {"Film:", comboFilm, "Jam Tayang:", inputJam, "Studio:", inputStudio};
        int result = JOptionPane.showConfirmDialog(this, fields, "Edit Jadwal", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            jadwal.setFilm(daftarFilm.get(comboFilm.getSelectedIndex()));
            jadwal.setJamTayang(inputJam.getText());
            jadwal.setStudio(inputStudio.getText());
            refreshTabelJadwal();
        }
    }

    private void hapusJadwal() {
        int row = tabelJadwal.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Pilih jadwal yang ingin dihapus!"); return; }

        int jawab = JOptionPane.showConfirmDialog(this, "Hapus jadwal ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (jawab == JOptionPane.YES_OPTION) {
            bioskop.hapusJadwal(row);
            refreshTabelJadwal();
        }
    }

    //buat pesen tiket

    private void setupTabPesan() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Halaman 1: Pilih Film
        JPanel halamanFilm = new JPanel(new BorderLayout());
        halamanFilm.add(new JLabel("<html><h2>Pilih Film</h2></html>"), BorderLayout.NORTH);
        String[] kolomFilm = {"Judul", "Genre", "Durasi (menit)"};
        modelFilmPesan = new DefaultTableModel(kolomFilm, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        listFilmPesan = new JTable(modelFilmPesan);
        listFilmPesan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        halamanFilm.add(new JScrollPane(listFilmPesan), BorderLayout.CENTER);
        JButton btnPilihFilm = new JButton("Pilih Film →");
        btnPilihFilm.setBackground(new Color(76, 175, 80));
        btnPilihFilm.setForeground(Color.WHITE);
        btnPilihFilm.addActionListener(e -> pilihFilmPesan());
        halamanFilm.add(btnPilihFilm, BorderLayout.SOUTH);
        cardPanel.add(halamanFilm, "FILM");

        // Halaman 2: Pilih Jadwal
        JPanel halamanJadwal = new JPanel(new BorderLayout());
        labelFilmDipilih = new JLabel();
        halamanJadwal.add(labelFilmDipilih, BorderLayout.NORTH);
        String[] kolomJadwal = {"Jam Tayang", "Studio", "Kapasitas"};
        modelJadwalPesan = new DefaultTableModel(kolomJadwal, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        listJadwalPesan = new JTable(modelJadwalPesan);
        listJadwalPesan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        halamanJadwal.add(new JScrollPane(listJadwalPesan), BorderLayout.CENTER);
        JPanel btnJadwal = new JPanel();
        JButton btnKembali2 = new JButton("← Kembali");
        JButton btnPilihJadwal = new JButton("Pilih Jadwal →");
        btnPilihJadwal.setBackground(new Color(76, 175, 80));
        btnPilihJadwal.setForeground(Color.WHITE);
        btnKembali2.addActionListener(e -> cardLayout.show(cardPanel, "FILM"));
        btnPilihJadwal.addActionListener(e -> pilihJadwalPesan());
        btnJadwal.add(btnKembali2);
        btnJadwal.add(btnPilihJadwal);
        halamanJadwal.add(btnJadwal, BorderLayout.SOUTH);
        cardPanel.add(halamanJadwal, "JADWAL");

        // Halaman 3: Pilih Kursi
        JPanel halamanKursi = new JPanel(new BorderLayout());
        labelJadwalDipilih = new JLabel();
        JPanel topKursi = new JPanel(new GridLayout(2, 1));
        topKursi.add(labelJadwalDipilih);
        topKursi.add(new JLabel("  Hijau = Tersedia    Merah = Tidak Tersedia    Biru = Dipilih"));
        halamanKursi.add(topKursi, BorderLayout.NORTH);
        gridKursiPanel = new JPanel();
        halamanKursi.add(new JScrollPane(gridKursiPanel), BorderLayout.CENTER);
        JPanel bottomKursi = new JPanel(new BorderLayout());
        labelKursiDipilih = new JLabel("Kursi dipilih: -");
        JPanel btnKursi = new JPanel();
        JButton btnKembali3 = new JButton("← Kembali");
        JButton btnKonfirmasi = new JButton("Konfirmasi Pemesanan");
        btnKonfirmasi.setBackground(new Color(76, 175, 80));
        btnKonfirmasi.setForeground(Color.WHITE);
        btnKembali3.addActionListener(e -> cardLayout.show(cardPanel, "JADWAL"));
        btnKonfirmasi.addActionListener(e -> konfirmasiPemesanan());
        btnKursi.add(btnKembali3);
        btnKursi.add(btnKonfirmasi);
        bottomKursi.add(labelKursiDipilih, BorderLayout.NORTH);
        bottomKursi.add(btnKursi, BorderLayout.SOUTH);
        halamanKursi.add(bottomKursi, BorderLayout.SOUTH);
        cardPanel.add(halamanKursi, "KURSI");

        tabbedPane.addTab("Pesan Tiket", cardPanel);
        tampilkanHalamanFilmPesan();
    }

    private void tampilkanHalamanFilmPesan() {
        modelFilmPesan.setRowCount(0);
        for (Film f : bioskop.getDaftarFilm()) {
            modelFilmPesan.addRow(new Object[]{f.getJudul(), f.getGenre(), f.getDurasi()});
        }
        cardLayout.show(cardPanel, "FILM");
    }

    private void tampilkanHalamanJadwalPesan() {
        modelJadwalPesan.setRowCount(0);
        labelFilmDipilih.setText("<html><b>Film:</b> " + filmDipilih.getJudul() + "</html>");
        for (Jadwal j : bioskop.getJadwalByFilm(filmDipilih)) {
            modelJadwalPesan.addRow(new Object[]{j.getJamTayang(), j.getStudio(), j.getKapasitas()});
        }
        cardLayout.show(cardPanel, "JADWAL");
    }

    private void tampilkanHalamanKursiPesan() {
        kursiDipilih = null;
        labelJadwalDipilih.setText("<html><b>Film:</b> " + filmDipilih.getJudul() +
                " | <b>Jam:</b> " + jadwalDipilih.getJamTayang() +
                " | <b>Studio:</b> " + jadwalDipilih.getStudio() + "</html>");
        refreshGridKursi();
        cardLayout.show(cardPanel, "KURSI");
    }

    private void refreshGridKursi() {
        gridKursiPanel.removeAll();
        ArrayList<Kursi> daftar = jadwalDipilih.getDaftarKursi();
        int cols = 5;
        gridKursiPanel.setLayout(new GridLayout((daftar.size() + cols - 1) / cols, cols, 5, 5));

        for (Kursi k : daftar) {
            JButton btn = new JButton(k.getNomor());
            btn.setPreferredSize(new Dimension(60, 40));
            btn.setForeground(Color.WHITE);

            if (!k.isTersedia()) {
                btn.setBackground(new Color(244, 67, 54));
                btn.setEnabled(false);
            } else if (k == kursiDipilih) {
                btn.setBackground(new Color(33, 150, 243));
            } else {
                btn.setBackground(new Color(76, 175, 80));
            }

            btn.addActionListener(e -> {
                kursiDipilih = k;
                labelKursiDipilih.setText("Kursi dipilih: " + k.getNomor());
                refreshGridKursi();
            });

            gridKursiPanel.add(btn);
        }
        gridKursiPanel.revalidate();
        gridKursiPanel.repaint();
    }

    private void pilihFilmPesan() {
        int row = listFilmPesan.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Pilih film terlebih dahulu!"); return; }
        filmDipilih = bioskop.getDaftarFilm().get(row);
        tampilkanHalamanJadwalPesan();
    }

    private void pilihJadwalPesan() {
        int row = listJadwalPesan.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Pilih jadwal terlebih dahulu!"); return; }
        ArrayList<Jadwal> jadwalFilm = bioskop.getJadwalByFilm(filmDipilih);
        jadwalDipilih = jadwalFilm.get(row);
        tampilkanHalamanKursiPesan();
    }

    private void konfirmasiPemesanan() {
        if (kursiDipilih == null) { JOptionPane.showMessageDialog(this, "Pilih kursi terlebih dahulu!"); return; }
        Pemesanan p = bioskop.pesanKursi(jadwalDipilih, kursiDipilih);
        if (p != null) {
            JOptionPane.showMessageDialog(this, "Pemesanan Berhasil!\n" +
                    "ID: " + p.getId() + "\nFilm: " + filmDipilih.getJudul() +
                    "\nJam: " + jadwalDipilih.getJamTayang() +
                    "\nStudio: " + jadwalDipilih.getStudio() +
                    "\nKursi: " + kursiDipilih.getNomor());
            kursiDipilih = null;
            refreshGridKursi();
            refreshTabelPemesanan();
        }
    }

    // pemesanan

    private void setupTabPemesanan() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] kolom = {"ID", "Film", "Jadwal", "Kursi", "Status"};
        modelPemesanan = new DefaultTableModel(kolom, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tabelPemesanan = new JTable(modelPemesanan);
        tabelPemesanan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(new JScrollPane(tabelPemesanan), BorderLayout.CENTER);

        JButton btnBatal = new JButton("Batalkan Pemesanan");
        btnBatal.setBackground(new Color(244, 67, 54));
        btnBatal.setForeground(Color.WHITE);
        btnBatal.addActionListener(e -> batalPemesanan());
        panel.add(btnBatal, BorderLayout.SOUTH);

        tabbedPane.addTab("Pemesanan", panel);
    }

    private void refreshTabelPemesanan() {
        modelPemesanan.setRowCount(0);
        for (Pemesanan p : bioskop.getDaftarPemesanan()) {
            modelPemesanan.addRow(new Object[]{
                p.getId(),
                p.getJadwal().getFilm().getJudul(),
                p.getJadwal().getJamTayang() + " - " + p.getJadwal().getStudio(),
                p.getKursi().getNomor(),
                p.getStatus()
            });
        }
    }

    private void batalPemesanan() {
        int row = tabelPemesanan.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Pilih pemesanan yang ingin dibatalkan!"); return; }

        Pemesanan p = bioskop.getDaftarPemesanan().get(row);
        if (p.getStatus().equals("Dibatalkan")) { JOptionPane.showMessageDialog(this, "Pemesanan sudah dibatalkan!"); return; }

        int jawab = JOptionPane.showConfirmDialog(this, "Batalkan pemesanan ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (jawab == JOptionPane.YES_OPTION) {
            bioskop.batalPemesanan(p.getId());
            refreshTabelPemesanan();
        }
    }
    //pelanggan
    private void setupTabPelanggan() {
         JPanel panel = new JPanel(new BorderLayout());

        String[] kolom = {"ID", "Nama", "Nomor Hp", "Email"};
        modelPelanggan = new DefaultTableModel(kolom, 0) {
            public boolean isCellEditable(int row, int col) { return false; }
        };
        tabelPelanggan = new JTable(modelPelanggan);
        tabelPelanggan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panel.add(new JScrollPane(tabelPelanggan), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton btnTambah = new JButton("Tambah Pelanggan");
        JButton btnEdit = new JButton("Edit Pelanggan");
        JButton btnHapus = new JButton("Hapus Pelanggan");
        btnTambah.setBackground(new Color(76, 175, 80)); //hijau
        btnTambah.setForeground(Color.WHITE);
        btnEdit.setBackground(new Color(33, 150, 243));//kuning
        btnEdit.setForeground(Color.WHITE);
        btnHapus.setBackground(new Color(244, 67, 54));//merah
        btnHapus.setForeground(Color.WHITE);
        btnPanel.add(btnTambah);
        btnPanel.add(btnEdit);
        btnPanel.add(btnHapus);
        panel.add(btnPanel, BorderLayout.SOUTH);

        btnTambah.addActionListener(e -> tambahPelanggan());
        btnEdit.addActionListener(e -> editPelanggan());
        btnHapus.addActionListener(e -> hapusPelanggan());

        tabbedPane.addTab("Pelanggan", panel);
        refreshTabelPelanggan();
    }
    
    private void refreshTabelPelanggan() {
        modelPelanggan.setRowCount(0);
        for (Pelanggan pel : bioskop.getDaftarPelanggan()) {
            modelPelanggan.addRow(new Object[]{pel.getId(), pel.getNama(), pel.getNomorHp(), pel.getEmail()});
        }
    }

 

    private void tambahPelanggan() {
        JTextField inputNama = new JTextField();
        JTextField inputHp = new JTextField();
        JTextField inputEmail = new JTextField();

        Object[] fields = {"Nama:", inputNama, "Nomor HP:", inputHp, "Email:", inputEmail};
        int result = JOptionPane.showConfirmDialog(this, fields, "Tambah Pelanggan", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            int id = bioskop.getDaftarPelanggan().size() + 1;
            bioskop.tambahPelanggan(new Pelanggan(id, inputNama.getText(), inputHp.getText(), inputEmail.getText()));
            refreshTabelPelanggan();
        }
    }

    private void editPelanggan() {
        int row = tabelPelanggan.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Pilih pelanggan yang ingin diedit!"); return; }

        Pelanggan p = bioskop.getDaftarPelanggan().get(row);
        JTextField inputNama = new JTextField(p.getNama());
        JTextField inputHp = new JTextField(p.getNomorHp());
        JTextField inputEmail = new JTextField(p.getEmail());

        Object[] fields = {"Nama:", inputNama, "Nomor HP:", inputHp, "Email:", inputEmail};
        int result = JOptionPane.showConfirmDialog(this, fields, "Edit Pelanggan", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            p.setNama(inputNama.getText());
            p.setNomorHp(inputHp.getText());
            p.setEmail(inputEmail.getText());
            refreshTabelPelanggan();
        }
    }

    private void hapusPelanggan() {
        int row = tabelPelanggan.getSelectedRow();
        if (row < 0) { JOptionPane.showMessageDialog(this, "Pilih pelanggan yang ingin dihapus!"); return; }

        Pelanggan pel = bioskop.getDaftarPelanggan().get(row);
        int jawab = JOptionPane.showConfirmDialog(this, "Hapus pelanggan " + pel.getNama() + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (jawab == JOptionPane.YES_OPTION) {
            bioskop.hapusPelanggan(row);
            refreshTabelPelanggan();
        }
    }


}