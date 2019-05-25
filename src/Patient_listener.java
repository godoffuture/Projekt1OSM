
// interfejs obserwatora zmian danyhc pacjenta
public interface Patient_listener {
    //      dodawanie nowego pacjenta do listy
    void add_new_patient();
    //      wczytywanie danych pacjenta do panelów
    void load_patient_data(int row);
    //    usuwanie zaznaczonego pacjenta
    void delete_patient(int row);
    //    zapisywanie nowego pacjenta
    void save_patient_data();
    //    edycja diagnosy zaznacoznego pacjenta
    void edit_patient_diagnose();
}
