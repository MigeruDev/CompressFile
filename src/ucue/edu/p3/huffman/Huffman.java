/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ucue.edu.p3.huffman;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 *
 * @author PaolaRemache
 */
public class Huffman {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Lector lector = new Lector();
        List<Nodo> lista = lector.frecuenciaSimbolo("TextoO.txt");
        for(Nodo clave: lista){
            System.out.println("clave: " + clave.getClave() + " frecuencia " + clave.getFrecuencia());
        } 
        Arbol arbol = new Arbol(lista);
        Map<String, byte[]> mapaBin = arbol.generarMapaBinario(arbol.getRaiz(), "");
        for(String clave: mapaBin.keySet()){
            System.out.println("clave: " + clave + " frecuencia ");
            for(byte bit: mapaBin.get(clave)){
                System.out.println(bit);
            }
        }
        lector.GenerarArchivoBinario(mapaBin, "TextoO.txt");
        System.out.println(lector.Descomprimir(arbol.getRaiz()));        
    }
    
}
