package com.inventario.gui;
//En la linea 1 se define el paquete en el que se encuentra esta clase

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.inventario.model.*;
import javax.swing.table.DefaultTableCellRenderer;
//Importación de las clases para la interfaz

public class ProductRegistrationFrame extends JFrame {
    //Declara la clase y la extiende, esto significa que esta clase es una ventana de la interfaz gráfica
    private JTextField txtName;
    private JTextField txtPrice;
    private JTextField txtQuantity;
    private JTable productTable;
    private ProductTableModel tableModel;
    private int productIdCounter = 1; // Para asignar IDs únicos

    public ProductRegistrationFrame() {
        //Constructor de la clase

        setTitle("Registro de Productos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        // Configuración del layout, también define la ventana, esto permite distribuir componentes

        // Panel para el formulario
        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        formPanel.add(new JLabel("Nombre:"));
        txtName = new JTextField();
        formPanel.add(txtName);

        formPanel.add(new JLabel("Precio:"));
        txtPrice = new JTextField();
        formPanel.add(txtPrice);

        formPanel.add(new JLabel("Cantidad:"));
        txtQuantity = new JTextField();
        formPanel.add(txtQuantity);

        JButton btnSave = new JButton("Guardar");
        btnSave.addActionListener(new SaveButtonListener());
        formPanel.add(btnSave);

        add(formPanel, BorderLayout.NORTH);
        //Añade el panel de fomulario a la parte superior

        // Tabla de productos y se crea una instancia
        tableModel = new ProductTableModel();
        productTable = new JTable(tableModel);
        add(new JScrollPane(productTable), BorderLayout.CENTER);
    }



    //Se define una clase interna que implementa una acción, donde cada vez que se haga click en el botón guardará los datos
    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveProduct();
        }
    }


    //Método que obtiene datos y almacena variables
    private void saveProduct() {
        String name = txtName.getText();
        double price;
        int quantity;

// Validación de  entrada
        if (name.isEmpty() || txtPrice.getText().isEmpty() || txtQuantity.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            price = Double.parseDouble(txtPrice.getText());
            quantity = Integer.parseInt(txtQuantity.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Precio y Cantidad deben ser numéricos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

// Crear producto y agregarlo a la tabla si las validaciones son exitosas
        Product product = new Product(productIdCounter++, name, price, quantity);
        tableModel.addProduct(product);

// Limpiar campos
        txtName.setText("");
        txtPrice.setText("");
        txtQuantity.setText("");
    }

    // Clase para el renderer de la tabla
    private class ProductTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

// Cambiar el color de fondo según el precio
            if (column == 1) { // Suponiendo que la columna 1 es el precio
                double price = (double) table.getValueAt(row, column);
                if (price > 100) {
                    cell.setBackground(Color.RED);
                } else {
                    cell.setBackground(Color.GREEN);
                }
            } else {
                cell.setBackground(Color.WHITE); // Color por defecto
            }

            return cell;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductRegistrationFrame frame = new ProductRegistrationFrame();
            frame.setVisible(true);
        });
    }


}