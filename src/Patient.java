// klasa opisujaca dane pacjenta
public class Patient {
// zmienna opisująca imie pacjeta
    private String name;

    // zmienna opisująca nazwisko pacjeta

    private String second_name;

    // zmienna opisująca PESEL pacjeta

    private String PESEL;

    // zmienna opisująca płeć pacjeta

    private String sex;

    // zmienna opisująca ubezpieczenie pacjeta

    private String insurance;

    // zmienna opisująca obecność badania pacjeta

    private boolean diagnose;

    // zmienna opisująca date wprowadzenia danych pacjeta

    private String date;

    // zmienna opisująca pierwsza zmienna badania pacjeta

    private float op1;

    // zmienna opisująca druga zmienna badania pacjeta

    private boolean op2;

    // zmienna opisująca trzecia zmienna badania pacjeta

    private float op3;

//    konstruktor nowego pacjenta
    public Patient(String name, String second_name, String PESEL, String sex, String insurance, boolean diagnose, String date, float op1, boolean op2, float op3) {
        this.name = name;
        this.second_name = second_name;
        this.PESEL = PESEL;
        this.sex = sex;
        this.insurance = insurance;
        this.diagnose = diagnose;
        this.date = date;
        this.op1 = op1;
        this.op2 = op2;
        this.op3 = op3;
    }

// klasa pobierająca imie
    public String getName() {
        return name;
    }

//    klasa ustawiająca imie
    public void setName(String name) {
        this.name = name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public boolean getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(boolean diagnose) {
        this.diagnose = diagnose;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getOp1() { return op1; }

    public void setOp1(float op1) {
        this.op1 = op1;
    }

    public boolean getOp2() {
        return op2;
    }

    public void setOp2(boolean op2) {
        this.op2 = op2;
    }

    public float getOp3() {
        return op3;
    }

    public void setOp3(float op3) {
        this.op3 = op3;
    }
}
