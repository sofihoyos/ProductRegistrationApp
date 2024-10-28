package com.inventario.model;
//En la linea 1 se define el paquete en el que se encuentra esta clase


public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
//En la linea 5 se crea la clase product, en la linea del 6 al 9 se definen los atributos con sus tipos


    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
// En la linea 13 a la 17 se crea el constructor


    // Genera getters y setters para todos los campos
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}