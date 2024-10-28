package com.inventario.model;
//En la linea 1 se define el paquete en el que se encuentra esta clase

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.ArrayList;
//Importación de las clases para la interfaz


//Se declara la clase y se extiende, esta clase se va a utilizar para datos de una tabla
public class ProductTableModel extends AbstractTableModel {

    //Definición de atributos
    private final List<Product> productList;
    private final String[] columnNames = {"ID", "Nombre", "Precio", "Cantidad"};


    //Construtor de la clase
    public ProductTableModel() {
        this.productList = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return productList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int index) {
        return columnNames[index];
    }

    //Métodos que devuelven el número de filas, número de columnas y el nombre de la columna

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Product product = productList.get(rowIndex);
        switch (columnIndex) {
            case 0: return product.getId();
            case 1: return product.getName();
            case 2: return product.getPrice();
            case 3: return product.getQuantity();
            default: return null;
        }
    }
    //Devuelve el valor de una celda en especifico

    public void addProduct(Product product) {
        productList.add(product);
        fireTableRowsInserted(productList.size() - 1, productList.size() - 1);
    }
    //Método para agregar un producto a la lista
}