import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.GroupLayout.Alignment.BASELINE;

// klasa tworzaca wygląd panelu badan

public class Patient_diagnose_panel extends JPanel {
//    inicjalizacja klawiszy
    private JButton save_btn, cancel_btn;
//   tworzenie komórek z nazwami
    private JLabel data_lbl, opt1_lbl, opt2_lbl, opt3_lbl;
//    tworzenie komórek do wpisywania danych
    public JTextField opt1_tf, opt3_tf;
//    tworzenie okna wybory daty
    public JDateChooser jDateChooser;
//    tworzenie chcek boxu
    public JCheckBox  opt2_cb;
// inicjalizacja group layoutu
    private GroupLayout groupLayout;
//    inicjalizacja lisy słuchaczy
    public List<Patient_listener> patient_listeners = new ArrayList<>();
//    konstruktor panelu badania
    public Patient_diagnose_panel(Controller controller) {
        groupLayout = new GroupLayout(this);

        setLayout(groupLayout);
        setBorder(new TitledBorder("Badanie"));

        addListener(controller);

        initialize_btn_lbl();

        layout_builder();
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

    }
    //    dodawanie nowego słuchacza
    public void addListener(Patient_listener patient_listener){
        patient_listeners.add(patient_listener);
    }
    //    klasa inicjalizująca
    private void initialize_btn_lbl() {
        data_lbl = new JLabel("Data");
        opt1_lbl = new JLabel("Stężenie glukozy we krwi [mg/dL]");
        opt2_lbl = new JLabel("Obecność glikowanej hemoglobiny GHB");
        opt3_lbl = new JLabel("Poziom cukru w moczu[mg/dL]");

        jDateChooser = new JDateChooser();

        opt1_tf = new JTextField();

        opt2_cb = new JCheckBox("obecne");
        opt2_cb.isSelected();

        opt3_tf = new JTextField();
        saveBTN();
        cancelBTN();
    }
    //    metoda definiujaca przycisk zapisu danych
    private void saveBTN(){
        save_btn = new JButton("zapisz");

         save_btn.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                    notify_listeners();
             }
         });
    }
    //    metoda definiujaca przycisk anulowania danych
    private void cancelBTN(){
        cancel_btn = new JButton("anuluj");

        cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear_textfield();
            }
        });
    }
    //    metoda umożliwijąca ustawienie edycji badania
    public void editable_textfield(){
        clear_textfield();

        jDateChooser.setEnabled(true);
        jDateChooser.setBackground(Color.white);

        opt1_tf.setEditable(true);
        opt1_tf.setBackground(Color.white);

        opt2_cb.setEnabled(true);

        opt3_tf.setEditable(true);
        opt3_tf.setBackground(Color.white);

        save_btn.setEnabled(true);
        cancel_btn.setEnabled(true);
    }
    //    metoda wyłączajaca mozliwosć wpisywania badania
    public void enable_textfield(){

        clear_textfield();
        jDateChooser.setEnabled(false);
        jDateChooser.setBackground(null);

        opt1_tf.setEditable(false);
        opt1_tf.setBackground(null);

        opt2_cb.setEnabled(false);

        opt3_tf.setEditable(false);
        opt3_tf.setBackground(null);

        save_btn.setEnabled(false);
        cancel_btn.setEnabled(false);
    }
    //    metoda czyszcząca komórki panelu badan
    public void clear_textfield() {
        jDateChooser.setCalendar(null);
        jDateChooser.setDate(null);
        opt1_tf.setText("");
        opt3_tf.setText("");
    }

//    metoda dezaktywująca przyciski
    public void enable_buttons(){

        save_btn.setEnabled(false);
        cancel_btn.setEnabled(false);

    }
//    metoda pobierajaca dane
    public float getOpt1_value(){
        try {
            return Float.parseFloat(opt1_tf.getText());
        }catch (NumberFormatException e){
            return 0;
        }
    }
    //    metoda pobierajaca dane
    public float getOpt3_value(){
        try {
           return Float.parseFloat(opt3_tf.getText());
        }catch (NumberFormatException e){
            return 0;
        }
    }
    //    metoda pobierajaca date
    public String getData(){
        try{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                return simpleDateFormat.format(jDateChooser.getDate());
        }
        catch(NullPointerException e){

            }
        return null;
    }
    //    metoda tworzaca rozkład komorek na panelu
    private void layout_builder() {
        enable_buttons();

        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(data_lbl)
                        .addComponent(opt1_lbl)
                        .addComponent(opt2_lbl)
                        .addComponent(opt3_lbl)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(save_btn)
                                .addComponent(cancel_btn)))
                .addGap(50)
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(jDateChooser)
                        .addComponent(opt1_tf)
                        .addComponent(opt2_cb)
                        .addComponent(opt3_tf))
        );

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(BASELINE)
                        .addComponent(data_lbl)
                        .addComponent(jDateChooser))
                .addGroup(groupLayout.createParallelGroup(BASELINE)
                        .addComponent(opt1_lbl)
                        .addComponent(opt1_tf))
                .addGroup(groupLayout.createParallelGroup(BASELINE)
                        .addComponent(opt2_lbl)
                        .addComponent(opt2_cb))
                .addGroup(groupLayout.createParallelGroup(BASELINE)
                        .addComponent(opt3_lbl)
                        .addComponent(opt3_tf))
                .addGroup(groupLayout.createParallelGroup(BASELINE)
                        .addComponent(save_btn)
                        .addComponent(cancel_btn))

        );
        groupLayout.linkSize(SwingConstants.HORIZONTAL,save_btn,cancel_btn);
        groupLayout.linkSize(SwingConstants.VERTICAL,opt1_tf,jDateChooser);

    }

    //    inforamcja dla słuchacza
    private void notify_listeners(){
        for (Patient_listener patient_listener:patient_listeners){
            patient_listener.edit_patient_diagnose();
        }
    }

}
