/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;


import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author The Worst One
 */
public class Huffman {

    private List<Nodo> frecuencias;
    private Map<String, String> codigoPrefijo;
    private long originalLen;
    private long datLen;
    private long huffmanAsciiLen;
    
    public void compressHuffman(String ruta) throws FileNotFoundException, IOException {
        Lector lector = new Lector();
        List<Nodo> lista = lector.frecuenciaSimbolo(ruta+".txt");
        frecuencias = new LinkedList<>();
        frecuencias.addAll(lista);
        for(Nodo clave: lista){
            System.out.println("clave: " + clave.getClave() + " frecuencia " + clave.getFrecuencia());
        } 
        Arbol arbol = new Arbol(lista);
        Map<String, byte[]> mapaBin = arbol.generarMapaBinario(arbol.getRaiz(), "");
        codigoPrefijo = arbol.getCodigoPrefijo();
        
        for(String clave: mapaBin.keySet()){
            System.out.print("\nclave: " + clave + " Codigo prefijo: ");
            for(byte bit: mapaBin.get(clave)){
                System.out.print(bit);
            }
        }
        lector.GenerarArchivoBinario(mapaBin, ruta);
        lector.GenerarArchivo(codigoPrefijo, ruta);
        datLen = lector.getDatLen();
        huffmanAsciiLen = lector.getAsciiLen();
        File archivo = new File(ruta+".txt");
        FileReader reader = new FileReader(archivo);
        originalLen = Math.round(Math.ceil(archivo.length()/1024.0));
        reader.close();
        
        ObjectOutputStream salida=new ObjectOutputStream(new FileOutputStream(ruta+"RAIZ.obj"));
        salida.writeObject(arbol.getRaiz());
        salida.close();
        
        //System.out.println(lector.Descomprimir(arbol.getRaiz()));        
    }

    public List<Nodo> getFrecuencias() {
        return frecuencias;
    }

    public Map<String, String> getCodigoPrefijo() {
        return codigoPrefijo;
    }   

    public long getDatLen() {
        return datLen;
    }

    public long getHuffmanAsciiLen() {
        return huffmanAsciiLen;
    }

    
    
    

    public long getOriginalLen() {
        return originalLen;
    }
    
    public void descompress(Nodo raiz, String ruta) throws IOException
    {
        Lector lector= new Lector();
        //FileWriter newOriginal = new FileWriter(ruta+"META.txt");
        //PrintWriter pw = new PrintWriter(newOriginal);
        //pw.write(lector.Descomprimir(raiz, ruta));
        //pw.close();
        //newOriginal.close();
        System.out.println("\n"+lector.Descomprimir(raiz, ruta));
    }
    
    
}
