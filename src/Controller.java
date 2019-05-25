import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/* klasa konrolujaca dane wejściowe i reagujaca na wszelskie ruchy użytkownika */
public class Controller implements Patient_listener {

//    inicjalizacja obiektu panelu danych pacjenta
    private Patient_data_panel data_panel;

//    inicjalizacja obiektu panelu diagnozy pacjenta
    private Patient_diagnose_panel diagnose_panel;

//    inicjalizacja obiektu panelu tabeli pacjenta
    private Patients_table_panel table_panel;

//    inicjalizacja listy pacjentów
    private ArrayList<Patient> patients;

//    inicjalizacja listy słuchaczy tabeli pacjentów
    public List<Table_listener> table_listeners = new ArrayList<>();

//    inicjalizacja modelu tabeli
    public DefaultTableModel defaultTableModel;

//    tabela nazw kolumn tabeli
    private final static String[] column_names = {"imie i nazwisko","płeć","PESEL","ubezpieczenie","badanie"};

//    konstruktor kontrolera
    public Controller() {

        defaultTableModel= new DefaultTableModel(column_names,0){
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    case 3:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };

        data_panel = new Patient_data_panel(this);

        diagnose_panel = new Patient_diagnose_panel(this);

        table_panel = new Patients_table_panel(this);

        patients = new ArrayList<>();

        addListener(table_panel.patients_table);

    }

//      tworzenie nowego sluchacza
    public void addListener(Table_listener table_listener){
        table_listeners.add(table_listener);
    }

//       metoda przypisująca panele
    public void setPanels(Patient_data_panel data_panel,Patient_diagnose_panel diagnose_panel,Patients_table_panel table_panel) {
        this.data_panel = data_panel;
        this.table_panel = table_panel;
        this.diagnose_panel = diagnose_panel;
    }

//      dodawanie nowego pacjenta do listy
    @Override
    public void add_new_patient() {
        data_panel.open_textfield();
        diagnose_panel.editable_textfield();
        diagnose_panel.enable_buttons();

    }
//      wczytywanie danych pacjenta do panelów
    @Override
    public void load_patient_data(int row) {
        if(row>-1){
            data_panel.clear_textfield();
            data_panel.name_tf.setText(patients.get(row).getName());
            data_panel.second_name_tf.setText(patients.get(row).getSecond_name());
            data_panel.pesel_tf.setText(patients.get(row).getPESEL());
            data_panel.editable_textfield();

        if(patients.get(row).getSex()=="M") data_panel.male_gender_btn.setSelected(true);
        else data_panel.female_gender_btn.setSelected(true);

            data_panel.insuranceBox.setSelectedItem(patients.get(row).getInsurance());

            diagnose_panel.editable_textfield();
        try {
            if(patients.get(row).getDate()!=null)
            diagnose_panel.jDateChooser.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(patients.get(row).getDate()));
        }catch (ParseException e) { }

            diagnose_panel.opt1_tf.setText(Float.toString(patients.get(row).getOp1()));
            diagnose_panel.opt2_cb.setSelected(patients.get(row).getOp2());
            diagnose_panel.opt3_tf.setText(Float.toString(patients.get(row).getOp3()));

        }
    }

//    usuwanie zaznaczonego pacjenta
    @Override
    public void delete_patient(int row) {
        try{
            patients.remove(row);
            data_panel.enable_textfield();
            diagnose_panel.enable_textfield();
            notify_listeners(patients);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "wybierz odpowiedni wiersz aby go usunąć","Uwaga",JOptionPane.ERROR_MESSAGE);
        }
        catch (IndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Dodaj pacientów aby móc ich usunąć","Uwaga",JOptionPane.ERROR_MESSAGE);
        }

    }

//    zapisywanie nowego pacjenta
    @Override
    public void save_patient_data() {

        if(data_panel.pesel_tf.isEditable()==true) {
            if (data_panel.getPesel() != null) {
                for (Patient p : patients) {
                    if (data_panel.getPesel().equals(p.getPESEL())) {
                        JOptionPane.showMessageDialog(null, "Osoba o podanym peselu już istnieje", "Uwaga", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                if (data_panel.name_tf.getText().isEmpty() && data_panel.second_name_tf.getText().isEmpty())
                    JOptionPane.showMessageDialog(null, "Podaj imie i nazwisko aby dodać nowego pacjenta", "Uwaga", JOptionPane.ERROR_MESSAGE);

                else if (diagnose_panel.getData() == null && diagnose_panel.opt1_tf.getText().isEmpty() && diagnose_panel.opt3_tf.getText().isEmpty()) {

                    patients.add(new Patient(data_panel.name_tf.getText(), data_panel.second_name_tf.getText(), data_panel.getPesel(), data_panel.getGender(), data_panel.insuranceBox.getSelectedItem().toString()
                            , false, null, 0, false, 0));

                    notify_listeners(patients);
                    data_panel.enable_textfield();
                    diagnose_panel.enable_textfield();
                } else if (diagnose_panel.getData()==null) {
                    JOptionPane.showMessageDialog(null, "Podaj date aby zapisać badanie", "Uwaga", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (diagnose_panel.getOpt1_value() == 0 || diagnose_panel.getOpt3_value() == 0) {
                    JOptionPane.showMessageDialog(null, "podaj odpowiednie wartości w panelu badania", "Uwaga", JOptionPane.ERROR_MESSAGE);
                    return;

                } else {
                    if (diagnose_panel.getOpt1_value() < 70 || diagnose_panel.getOpt1_value() > 126)
                        JOptionPane.showMessageDialog(null, "Dane pomiaru stężenia glukozy we krwi wychodzą poza zakresy norm", "Uwaga", JOptionPane.ERROR_MESSAGE);
                    if (diagnose_panel.getOpt3_value() > 1 || diagnose_panel.getOpt3_value() < 0.1)
                        JOptionPane.showMessageDialog(null, "Dane pomiaru poziomu cukru w moczu wychodzą poza zakresy norm", "Uwaga", JOptionPane.ERROR_MESSAGE);

                    patients.add(new Patient(data_panel.name_tf.getText(), data_panel.second_name_tf.getText(), data_panel.getPesel(), data_panel.getGender(), data_panel.insuranceBox.getSelectedItem().toString()
                            , true, diagnose_panel.getData(), diagnose_panel.getOpt1_value(), diagnose_panel.opt2_cb.isSelected(), diagnose_panel.getOpt3_value()));

                    notify_listeners(patients);
                    data_panel.enable_textfield();
                    diagnose_panel.enable_textfield();
                }
            }
        } else{
            patients.get(table_panel.patients_table.jTable.getSelectedRow()).setName(data_panel.name_tf.getText());
            patients.get(table_panel.patients_table.jTable.getSelectedRow()).setSecond_name(data_panel.second_name_tf.getText());
            patients.get(table_panel.patients_table.jTable.getSelectedRow()).setInsurance(data_panel.insuranceBox.getSelectedItem().toString());
            data_panel.enable_textfield();
            diagnose_panel.enable_textfield();
            notify_listeners(patients);
        }
    }

//    edycja diagnosy zaznacoznego pacjenta
    @Override
    public void edit_patient_diagnose() {
        if (diagnose_panel.getData() == null) {
            JOptionPane.showMessageDialog(null, "Podaj date aby zapisać badanie", "Uwaga", JOptionPane.ERROR_MESSAGE);
        } else{
            patients.get(table_panel.patients_table.jTable.getSelectedRow()).setDate(diagnose_panel.getData());
            if (diagnose_panel.getOpt1_value() < 70 || diagnose_panel.getOpt1_value() > 126)
                JOptionPane.showMessageDialog(null, "Dane pomiaru stężenia glukozy we krwi wychodzą poza zakresy norm", "Uwaga", JOptionPane.ERROR_MESSAGE);
            patients.get(table_panel.patients_table.jTable.getSelectedRow()).setOp1(diagnose_panel.getOpt1_value());
            patients.get(table_panel.patients_table.jTable.getSelectedRow()).setOp2(diagnose_panel.opt2_cb.isSelected());
            if (diagnose_panel.getOpt3_value() > 1 || diagnose_panel.getOpt3_value() < 0.1)
                JOptionPane.showMessageDialog(null, "Dane pomiaru poziomu cukru w moczu wychodzą poza zakresy norm", "Uwaga", JOptionPane.ERROR_MESSAGE);
            patients.get(table_panel.patients_table.jTable.getSelectedRow()).setOp3(diagnose_panel.getOpt3_value());
            patients.get(table_panel.patients_table.jTable.getSelectedRow()).setDiagnose(true);
            data_panel.enable_textfield();
            diagnose_panel.enable_textfield();
            notify_listeners(patients);
        }
    }


    //    inforamcja dla słuchacza
    private void notify_listeners(ArrayList<Patient> patients){
        for (Table_listener table_listener:table_listeners){
            table_listener.populate_table(patients);
        }
    }

}
