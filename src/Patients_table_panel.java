import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// klasa panelu w ktorym znajduje sie tabela z pacjentami
public class Patients_table_panel extends JPanel {
//    tworzenie listy ze słuchaczami
    public List<Patient_listener> patient_listeners = new ArrayList<>();
//    tworzenie klawiszy
    private JButton add_btn, delete_btn;
//    inicjalizacja klasy z tabela pacjentow
    public Patients_Table patients_table;
//    tworzeie grouplayoutu
    private GroupLayout groupLayout;
//    tworzenie klasy kotrolera
    private Controller controller;
//    konstruktor panelu z tabela
    public Patients_table_panel(Controller controler) {

        this.controller = controler;

        groupLayout = new GroupLayout(this);

        addListener(controller);

        setLayout(groupLayout);

        setBorder(new TitledBorder("Lista pacjentow"));

        patients_table = new Patients_Table(controller.defaultTableModel);

        layout_builder();

    }
    //    metoda tworzaca rozkład komorek na panelu
    private void layout_builder(){
        addBTN();
        deleteBTN();
        load_row();
        notify_load(patients_table.jTable.getSelectedRow());
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addComponent(patients_table)
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(add_btn)
                        .addComponent(delete_btn)));

        groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(patients_table)
                .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(add_btn)
                        .addComponent(delete_btn)));

        groupLayout.linkSize(SwingConstants.HORIZONTAL,add_btn,delete_btn);
        groupLayout.setAutoCreateGaps(true);
    }


    //    dodawanie nowego słuchacza
    public void addListener(Patient_listener patient_listener){
        patient_listeners.add(patient_listener);
    }
//    metoda tworzaca przycisk zmiany trybu na dodawanie
    private void addBTN(){
        add_btn = new JButton("dodaj");
        add_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notify_add();
            }
        });
    }

    //    metoda tworzaca przycisk usuwania
    private void deleteBTN() {
        delete_btn = new JButton("usun");
        delete_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notify_delete(patients_table.jTable.getSelectedRow());
            }
        });
    }
//    metoda wczytujaca dane zaznaczonego pacjenta
    private void load_row(){
        patients_table.jTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (patients_table.jTable.getSelectedRow() > -1) {
                        notify_load(patients_table.jTable.getSelectedRow());
                }
            }
        });
    }
//    inforamcja dla słuchacza
    private void notify_add(){
        for (Patient_listener patient_listener:patient_listeners){
            patient_listener.add_new_patient();
        }
    }

    //    inforamcja dla słuchacza
    private void notify_delete(int row){
        for (Patient_listener patient_listener:patient_listeners){
            patient_listener.delete_patient(row);
        }
    }

    //    inforamcja dla słuchacza
    private void notify_load(int row){
        for (Patient_listener patient_listener:patient_listeners){
            patient_listener.load_patient_data(row);
        }
    }
}
