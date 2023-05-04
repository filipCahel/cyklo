import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GUI extends JFrame {
    private JPanel panel;
    private JTextArea text;
    private JTextField field;
    private JTextArea textArea1;
    private JButton smazaat;
    private int i = 1;
    private List<Main> arraj = new ArrayList<>();
    private final JFileChooser jFileChooser = new JFileChooser(",");
    private static final String SPLITTER = ",";


    public static void main(String[] args) {
        new GUI();
    }








    public List<Main> getFileData() {
        refresh();
        int result = jFileChooser.showOpenDialog(this);
        if (result == JFileChooser.CANCEL_OPTION) {
            System.out.println("špatná možnost!");
            return null;
        }
        return scan(jFileChooser.getSelectedFile());
    }





    private void vymazad() {

        try {
            int lineNumbers = text.getLineCount();
            int lineNumber = Integer.parseInt(field.getText());


            if (lineNumbers <= lineNumber) {
                JOptionPane.showMessageDialog(null, "řádek " + field.getText() + " nebyl nalezen");
            }

            arraj.remove(field.getText());
            deleteLine(text, lineNumber);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "napiste cislo vyletu");
        }
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(jFileChooser.getSelectedFile())))) {

            arraj.forEach(cykloVylet -> {
                writer.println(cykloVylet.toString());
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "řádek nebyl nalezen " + field.getText());
        }
    }
    private List<Main> scan(File file) {
        List<Main> list = new ArrayList<>();
        try (Scanner scanner = new Scanner((new BufferedReader(new FileReader(file))))) {
            while (scanner.hasNextLine()) {

                String[] data = scanner.nextLine().split(SPLITTER);
                int cisla = Integer.parseInt(data[1]);
                LocalDate ld = LocalDate.now();
                list.add(new Main(data[0], cisla, ld));
                text.append((i++ + ") " + data[0] + " (" + cisla + " km) \n"));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Nastala chyba");
        }
        return list;
    }



    private void deleteLine(JTextArea text, int lineNumber) {

        String texts = text.getText();
        String[] lines = texts.split("\n");

        if (lineNumber >= 1 && lineNumber <= lines.length) {
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < lines.length; i++) {
                if (i != lineNumber - 1) {
                    builder.append(lines[i]).append("\n");
                }
            }
            text.setText(builder.toString());
        }
    }



    public GUI() {
        JMenuBar jMenuBar = new JMenuBar();
        setJMenuBar(jMenuBar);
        JMenu jMenu = new JMenu("Soubor");
        JMenuItem nacti = new JMenuItem("Načti");
        nacti.addActionListener(e -> getFileData());
        JMenuItem refresh = new JMenuItem("Refresh");
        refresh.addActionListener(e -> refresh());
        smazaat.addActionListener(e -> vymazad());
        jMenu.add(refresh);
        jMenu.add(nacti);
        jMenuBar.add(jMenu);


        setVisible(true);
        setContentPane(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
    }

    private void refresh() {
        field.setText("");
        text.setText("");

    }
}