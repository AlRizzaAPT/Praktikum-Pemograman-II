import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Date;
import java.util.Calendar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;

public class PendaftaranNasabah extends JFrame {
    private boolean checkBoxWNA;
    private JPasswordField passwordField, confirmPasswordField;

    public PendaftaranNasabah() {
        this.checkBoxWNA = false;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem resetItem = new JMenuItem("Reset");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Listener untuk Reset
        resetItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });

        // Listener untuk Exit
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu.add(resetItem);
        menu.add(exitItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Label untuk input nama
        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(15, 20, 100, 25);

        // TextField untuk memasukkan nama
        JTextField textFieldNama = new JTextField();
        textFieldNama.setBounds(120, 20, 200, 25);

        // Label untuk input nomor HP
        JLabel labelHP = new JLabel("Nomor HP:");
        labelHP.setBounds(15, 60, 100, 25);

        // TextField untuk memasukkan nomor HP
        JTextField textFieldHP = new JTextField();
        textFieldHP.setBounds(120, 60, 200, 25);

        // Label untuk jenis kelamin
        JLabel labelJenisKelamin = new JLabel("Jenis Kelamin:");
        labelJenisKelamin.setBounds(15, 100, 100, 25);

        // RadioButtons untuk memilih jenis kelamin
        JRadioButton radioButtonLaki = new JRadioButton("Laki-Laki", true);
        radioButtonLaki.setBounds(120, 100, 100, 25);

        JRadioButton radioButtonPerempuan = new JRadioButton("Perempuan");
        radioButtonPerempuan.setBounds(220, 100, 100, 25);

        // Mengelompokkan RadioButtons agar hanya satu yang bisa dipilih
        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButtonLaki);
        bg.add(radioButtonPerempuan);

        // CheckBox untuk Warga Negara Asing
        JCheckBox checkBox = new JCheckBox("Warga Negara Asing");
        checkBox.setBounds(120, 140, 200, 25);

        // Label untuk jenis tabungan
        JLabel labelJenisTabungan = new JLabel("Jenis Tabungan:");
        labelJenisTabungan.setBounds(15, 180, 100, 25);

        // JList untuk memilih jenis tabungan
        String[] jenisTabungan = { "Tabungan Biasa", "Deposito", "Tabungan Bisnis", "Tabungan Mahasiswa" };
        JList<String> listTabungan = new JList<>(jenisTabungan);
        listTabungan.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScrollPane = new JScrollPane(listTabungan);
        listScrollPane.setBounds(120, 180, 200, 60);

        // JSpinner untuk frekuensi transaksi
        JLabel labelFrekuensi = new JLabel("Frekuensi Transaksi:");
        labelFrekuensi.setBounds(15, 250, 150, 25);
        JSpinner spinnerFrekuensi = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        spinnerFrekuensi.setBounds(170, 250, 150, 25);

        // Password field
        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setBounds(15, 290, 100, 25);
        passwordField = new JPasswordField();
        passwordField.setBounds(120, 290, 200, 25);

        JLabel labelConfirmPassword = new JLabel("Confirm Password:");
        labelConfirmPassword.setBounds(15, 330, 150, 25);
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(170, 330, 150, 25);

        // Spinner untuk input tanggal lahir
        JLabel labelTanggalLahir = new JLabel("Tanggal Lahir:");
        labelTanggalLahir.setBounds(15, 370, 100, 25);

        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner spinnerTanggalLahir = new JSpinner(dateModel);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spinnerTanggalLahir, "dd/MM/yyyy");
        spinnerTanggalLahir.setEditor(dateEditor);
        spinnerTanggalLahir.setBounds(120, 370, 200, 25);

        // Tombol Simpan
        JButton button = new JButton("Simpan");
        button.setBounds(120, 410, 100, 30);

        // TextArea untuk menampilkan output
        JTextArea txtOutput = new JTextArea("");
        txtOutput.setBounds(15, 450, 350, 200);

        // Listener untuk CheckBox WNA
        checkBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                checkBoxWNA = (e.getStateChange() == ItemEvent.SELECTED);
            }
        });

        // Listener untuk Tombol Simpan
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nama = textFieldNama.getText();
                String nomorHP = textFieldHP.getText();
                String jenisKelamin = radioButtonLaki.isSelected() ? "Laki-Laki" : "Perempuan";
                String tabungan = listTabungan.getSelectedValue();
                int frekuensi = (Integer) spinnerFrekuensi.getValue();
                String wna = checkBoxWNA ? "Ya" : "Tidak";
                Date tanggalLahir = (Date) spinnerTanggalLahir.getValue();
                
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                String passwordMatch = password.equals(confirmPassword) ? "Cocok" : "Tidak Cocok";

                // Menampilkan output di TextArea
                txtOutput.setText("Nama       : " + nama + "\n"
                        + "Nomor HP   : " + nomorHP + "\n"
                        + "Jenis Kelamin: " + jenisKelamin + "\n"
                        + "Tabungan   : " + tabungan + "\n"
                        + "Frekuensi  : " + frekuensi + " kali/bulan\n"
                        + "Tanggal Lahir: " + tanggalLahir + "\n"
                        + "WNA        : " + wna + "\n"
                        + "Password   : " + passwordMatch + "\n"
                        + "=====================================\n");
                resetForm();
            }
        });

        this.add(labelNama);
        this.add(textFieldNama);
        this.add(labelHP);
        this.add(textFieldHP);
        this.add(labelJenisKelamin);
        this.add(radioButtonLaki);
        this.add(radioButtonPerempuan);
        this.add(checkBox);
        this.add(labelJenisTabungan);
        this.add(listScrollPane);
        this.add(labelFrekuensi);
        this.add(spinnerFrekuensi);
        this.add(labelPassword);
        this.add(passwordField);
        this.add(labelConfirmPassword);
        this.add(confirmPasswordField);
        this.add(labelTanggalLahir);
        this.add(spinnerTanggalLahir);
        this.add(button);
        this.add(txtOutput);

        
        this.setSize(400, 700);
        this.setLayout(null);
    }

    
    private void resetForm() {
        passwordField.setText("");
        confirmPasswordField.setText("");
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                PendaftaranNasabah form = new PendaftaranNasabah();
                form.setVisible(true);
            }
        });
    }
}
