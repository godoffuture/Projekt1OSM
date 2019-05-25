
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// klasa tworzaca okno aplikcaji
public class WindowMain extends JFrame{
// inicjacja panelów okna aplikcji
    private JPanel left_panel,pacient_data_panel, pacient_diagnose_panel, pacients_table_panel;
// inicjacja menu
    private JMenuBar jMenuBar;
//    inicjacja menu
    private JMenu jMenu;
// inicjacja punktow menu
    private JMenuItem jMenuItem;
// inicjacja kontrollera
    private Controller controller;
// konstruktor okna aplikcji
    public WindowMain() throws HeadlessException {

        setTitle("Rejestracja wynikow badań");
        create_window();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
// metoda inicjalizująca obiekty okna
    private void create_window() {
        jMenuBar = new JMenuBar();

        jMenu = new JMenu("Aplikacja");


        jMenuItem = new JMenuItem("Zamknij ALT+F4");

        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jMenu.add(jMenuItem);

        jMenuBar.add(jMenu);

        controller = new Controller();

        pacients_table_panel = new Patients_table_panel(controller);

        pacient_data_panel = new Patient_data_panel(controller);

        pacient_diagnose_panel = new Patient_diagnose_panel(controller);

        controller.setPanels((Patient_data_panel) pacient_data_panel, (Patient_diagnose_panel) pacient_diagnose_panel, (Patients_table_panel) pacients_table_panel);

        setLayout(new GridLayout(1,2));

        left_panel = new JPanel(new GridLayout(2,1));
        left_panel.add(pacient_data_panel);
        left_panel.add(pacient_diagnose_panel);


        add(left_panel);
        add(pacients_table_panel);
        setJMenuBar(jMenuBar);
    }

}
