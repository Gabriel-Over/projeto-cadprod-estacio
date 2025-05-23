package com.mycompany.cadprod;

public class Venda {
    Cliente cliente = new Cliente("", "");
    Produto[] produto = new Produto[10];
    int qtdProdutos = 1;
    
    
    public double calcularTotal() {
        double total = 0;
        for(Produto p : produto) {
            if(p != null) {
                total+=p.getPreço();
            }
        }
        return total;
    }
}
