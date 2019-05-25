import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.GroupLayout.Alignment.BASELINE;

// klasa tworzaca wygląd panelu danych
public class Patient_data_panel extends JPanel{
//    inicjalizacja klawiszy
    private JButton save_btn, cancel_btn;
//    tworzenie komórek z nazwami
    private JLabel name_lbl, second_name_lbl, pesel_lbl, gender_lbl, insurance_lbl;

// inicjalizacja group layoutu
    private GroupLayout groupLayout;

//    tworzenie komórek do wpisywania danych
    public JTextField name_tf, second_name_tf, pesel_tf;

//    tworzenie przycisków radio
    public JRadioButton female_gender_btn, male_gender_btn;

//    tworzenie grupy przyciskow
    private  ButtonGroup gender_radio_btn_grop;

//    tabela nazw ubezpieczen
    private final static String insurance_opt[]={
            "NFZ",
            "prywatne",
            "brak"
    };

//    inicjalizacja lisy słuchaczy
    public List<Patient_listener> patient_listeners = new ArrayList<>();
//    inicjalizacja rozwijanej listy
    public JComboBox insuranceBox;

//    konstruktor panelu danych
    public Patient_data_panel(Controller controller){
        groupLayout = new GroupLayout(this);

        addListener(controller);

        setLayout(groupLayout);

        setBorder(new TitledBorder("Dane pacjenta"));

        initialize_view();

        layout_builder();
    }

//    klasa inicjalizująca
    private void initialize_view() {

        saveBTN();
        cancelBTN();

        name_lbl = new JLabel("Imie");
        second_name_lbl = new JLabel("Nazwisko");
        pesel_lbl = new JLabel("PESEL");
        gender_lbl = new JLabel("Płeć");
        insurance_lbl = new JLabel("Ubezpieczenie");
        name_tf = new JTextField();
        second_name_tf = new JTextField();
        pesel_tf = new JTextField();

        female_gender_btn = new JRadioButton("Kobita");
        male_gender_btn = new JRadioButton("Mężczyzna");
        male_gender_btn.setSelected(true);

        gender_radio_btn_grop = new ButtonGroup();
        gender_radio_btn_grop.add(female_gender_btn);
        gender_radio_btn_grop.add(male_gender_btn);

        insuranceBox = new JComboBox(insurance_opt);
    }

//    dodawanie nowego słuchacza
    public void addListener(Patient_listener patient_listener){
        patient_listeners.add(patient_listener);
    }

//    metoda pobierajaca plec
    public String getGender() {
        if(male_gender_btn.isSelected()) {
            return "M";
        } else if(female_gender_btn.isSelected()) {
            return "F";
        } else {
            return null;
        }
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

//    metoda pobierajaca pesel
    public String getPesel(){
        if(pesel_tf.getText().replaceAll("[^0-9?!\\.]","").length()==11)
            return pesel_tf.getText().replaceAll("[^0-9?!\\.]","");
        else { JOptionPane.showMessageDialog(null, "Podaj poprawny PESEL składający się z 9 cyftr bez liter", "Uwaga", JOptionPane.ERROR_MESSAGE);
            return null;
        }
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

//    metoda czyszcząca komórki panelu danych
    public void clear_textfield(){
        name_tf.setText("");
        second_name_tf.setText("");
        pesel_tf.setText("");
    }

//    metoda umożliwijąca ustawienie edycji danych
    public void editable_textfield(){

        name_tf.setEditable(true);
        name_tf.setBackground(Color.white);

        second_name_tf.setEditable(true);
        second_name_tf.setBackground(Color.white);

        pesel_tf.setEditable(false);
        pesel_tf.setBackground(null);

        male_gender_btn.setEnabled(false);
        female_gender_btn.setEnabled(false);

        insuranceBox.setEnabled(true);

        save_btn.setEnabled(true);
        cancel_btn.setEnabled(true);
    }

//    metoda wyłączajaca mozliwosć wpisywania danych
    public void enable_textfield(){
        clear_textfield();

        name_tf.setEditable(false);
        name_tf.setBackground(null);

        second_name_tf.setEditable(false);
        second_name_tf.setBackground(null);

        pesel_tf.setEditable(false);
        pesel_tf.setBackground(null);

        male_gender_btn.setEnabled(false);
        female_gender_btn.setEnabled(false);

        insuranceBox.setEnabled(false);
        insuranceBox.setBackground(Color.white);

        save_btn.setEnabled(false);
        cancel_btn.setEnabled(false);
    }

//    metoda pozwalająca na wpisywanie danych do komórek
    public void open_textfield(){
        clear_textfield();

        name_tf.setEditable(true);
        name_tf.setBackground(Color.white);

        second_name_tf.setEditable(true);
        second_name_tf.setBackground(Color.white);

        pesel_tf.setEditable(true);
        pesel_tf.setBackground(Color.white);

        male_gender_btn.setEnabled(true);
        female_gender_btn.setEnabled(true);

        insuranceBox.setEnabled(true);
        insuranceBox.setBackground(Color.white);

        save_btn.setEnabled(true);
        cancel_btn.setEnabled(true);
    }

//    metoda tworzaca rozkład komorek na panelu
    private void layout_builder(){
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(name_lbl)
                        .addComponent(second_name_lbl)
                        .addComponent(pesel_lbl)
                        .addComponent(gender_lbl)
                        .addComponent(insurance_lbl)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(save_btn)
                                .addComponent(cancel_btn)))
                .addGap(50)
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(name_tf)
                        .addComponent(second_name_tf)
                        .addComponent(pesel_tf)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(male_gender_btn)
                                .addComponent(female_gender_btn))
                        .addComponent(insuranceBox))
        );

        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(BASELINE)
                        .addComponent(name_lbl)
                        .addComponent(name_tf))
                .addGroup(groupLayout.createParallelGroup(BASELINE)
                        .addComponent(second_name_lbl)
                        .addComponent(second_name_tf))
                .addGroup(groupLayout.createParallelGroup(BASELINE)
                        .addComponent(pesel_lbl)
                        .addComponent(pesel_tf))
                .addGroup(groupLayout.createParallelGroup(BASELINE)
                        .addComponent(gender_lbl)
                        .addComponent(male_gender_btn)
                        .addComponent(female_gender_btn))
                .addGroup(groupLayout.createParallelGroup(BASELINE)
                        .addComponent(insurance_lbl)
                        .addComponent(insuranceBox))
                .addGroup(groupLayout.createParallelGroup(BASELINE)
                        .addComponent(save_btn)
                        .addComponent(cancel_btn))

        );
        groupLayout.linkSize(SwingConstants.HORIZONTAL, male_gender_btn, female_gender_btn);

        groupLayout.linkSize(SwingConstants.HORIZONTAL,save_btn,cancel_btn);

        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);
    }


    //    inforamcja dla słuchacza
    private void notify_listeners(){
        for (Patient_listener patient_listener:patient_listeners){
            patient_listener.save_patient_data();
        }
    }

}
