package Vista;

import Controlador.Bodega;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Vista extends JFrame {
    private Bodega bodega;
    private JPanel mainPanel;
    private JTextField codField, nomField, stockField;
    private JButton agregarBtn, listarBtn, agregarStockBtn, venderBtn;
    private JTextArea listaArea;

    public Vista(Bodega bodega) {
        this.bodega = bodega;
        
        
        setTitle("Sistema de Almacén");
        setSize(600, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
       
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        
              
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Datos del Producto"));
        
        inputPanel.add(new JLabel("Código:"));
        codField = new JTextField(15);
        inputPanel.add(codField);
        
        inputPanel.add(new JLabel("Nombre:"));
        nomField = new JTextField(15);
        inputPanel.add(nomField);
        
        inputPanel.add(new JLabel("Stock:"));
        stockField = new JTextField(15);
        inputPanel.add(stockField);
        
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        agregarBtn = new JButton("Agregar Producto");
        listarBtn = new JButton("Listar Productos");
        agregarStockBtn = new JButton("Agregar Stock");
        venderBtn = new JButton("Vender Producto");
        
        buttonPanel.add(agregarBtn);
        buttonPanel.add(listarBtn);
        buttonPanel.add(agregarStockBtn);
        buttonPanel.add(venderBtn);
        
        
        listaArea = new JTextArea(10, 40); 
        listaArea.setEditable(false);
        listaArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(listaArea);
        scrollPane.setPreferredSize(new Dimension(550, 200)); 
        
       
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);
        
        // Agregar el panel principal a la ventana
        add(mainPanel);
        
        // Configurar eventos de los botones
        configurarEventos();
        
        // Inicializar el área de texto vacía
        listaArea.setText("Presione 'Listar Productos' para ver el inventario");
    }

    private void configurarEventos() {
        agregarBtn.addActionListener(e -> agregarProducto());
        listarBtn.addActionListener(e -> listarProductos());
        agregarStockBtn.addActionListener(e -> agregarStock());
        venderBtn.addActionListener(e -> restarStock());
    }
 
    private void agregarProducto() {
        try {
            String cod = codField.getText();
            String nom = nomField.getText();
            int stock = Integer.parseInt(stockField.getText());
            
            bodega.agregarProducto(cod, nom, stock);
            JOptionPane.showMessageDialog(this, "Producto agregado exitosamente");
            
            // Limpiar campos
            codField.setText("");
            nomField.setText("");
            stockField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido para el stock", 
                                       "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarProductos() {
        String inventario = bodega.listarProductos();
        listaArea.setText("");  // Limpia el área de texto
        listaArea.append("============ INVENTARIO ALMACÉN ============\n");
        listaArea.append(inventario);
        listaArea.append("=========================================\n");
    }
    
    private void agregarStock() {
        String cod = JOptionPane.showInputDialog(this, "Ingrese el código del producto:");
        if (cod != null && !cod.trim().isEmpty()) {
            try {
                String cantStr = JOptionPane.showInputDialog(this, "Ingrese la cantidad a agregar:");
                if (cantStr != null && !cantStr.trim().isEmpty()) {
                    int cant = Integer.parseInt(cantStr);
                    bodega.Agregar(cod, cant);
                    JOptionPane.showMessageDialog(this, "Stock agregado exitosamente");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese una cantidad válida", 
                                           "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), 
                                           "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
 
    private void restarStock() {
        String cod = JOptionPane.showInputDialog(this, "Ingrese el código del producto:");
        if (cod != null && !cod.trim().isEmpty()) {
            try {
                String cantStr = JOptionPane.showInputDialog(this, "Ingrese la cantidad a vender:");
                if (cantStr != null && !cantStr.trim().isEmpty()) {
                    int cant = Integer.parseInt(cantStr);
                    bodega.restar(cod, cant);
                    JOptionPane.showMessageDialog(this, "Venta realizada exitosamente");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese una cantidad válida", 
                                           "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), 
                                           "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}