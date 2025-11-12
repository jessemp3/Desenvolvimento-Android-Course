package com.jesse.listasecoleoces.testes;

public class Varargs {
    // varargs , telefones passa a ser uma lista
    public void salvarTefs(String...telefones){
        for(String t : telefones){
            System.out.println("telefones " + t);
        }
    }

    public static void main(String[] args) {
        Varargs v = new Varargs();
        v.salvarTefs();
    }
}
