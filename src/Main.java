import javax.swing.*;

// główna klasa
public class Main {

    public static void main(String[] args)throws Exception
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    createAndShowGUI();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
//    klasa tworzaca okno aplikacji
    public static void createAndShowGUI() throws Exception {
        new WindowMain();
    }


}

