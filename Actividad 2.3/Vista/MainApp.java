package Vista;

import Controlador.Bodega;

public class MainApp {
    public static void main(String[] args) {
        Bodega controlador = new Bodega();
        Vista vista = new Vista(controlador);
        vista.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        vista.setVisible(true); // Hace visible la ventana
    }
}
