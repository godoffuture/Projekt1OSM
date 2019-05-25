import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

// klasa tworzaca tabele pacjentów
public class Patients_Table extends JScrollPane implements Table_listener{

//    tworzenie modelu tabeli
    private DefaultTableModel tableModel;

//    tworzenie tabeli
    public JTable jTable;

//    konstruktor tabeli
    public Patients_Table(DefaultTableModel defaultTableModel) {
        this.tableModel = defaultTableModel;
        jTable= new JTable();
        jTable.setModel(tableModel);
        jTable.setOpaque(true);

        getViewport().add(jTable);
    }

//    metoda wczytujaca pacjentow
    @Override
    public void populate_table(ArrayList<Patient> patient_s) {
        if (tableModel.getRowCount() > 0) {
            for (int i = tableModel.getRowCount() - 1; i > -1; i--) {
                tableModel.removeRow(i);
            }
        }
        for(int i = 0; i< patient_s.size(); i++) {
            Object[] data = {patient_s.get(i).getName() + " " +
                    patient_s.get(i).getSecond_name(),
                    patient_s.get(i).getSex(),
                    patient_s.get(i).getPESEL(),
                    patient_s.get(i).getInsurance(),
                    new Boolean(patient_s.get(i).getDiagnose())};
            tableModel.addRow(data);
        }
    }


}


