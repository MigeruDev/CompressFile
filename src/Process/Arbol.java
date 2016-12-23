/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Process;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author The Worst One
 */
public class Arbol {
    private Nodo raiz;
    private Map<String, String> codigoPrefijo;
    
    public Arbol(List<Nodo> lista){
        while(lista.size() > 1){
            lista.add(new Nodo(lista.get(0), lista.get(1)));
            lista.remove(0);
            lista.remove(0);
            Collections.sort(lista, new CaracterComparator());
        }
        this.raiz = lista.get(0);
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public Map<String, String> getCodigoPrefijo() {
        return codigoPrefijo;
    }
    
    
    
    public Map<String, String> generarMapa(Nodo raiz, String bin){
        Map<String, String> nuevoMapa = new HashMap<String, String>();
        if(raiz.getRamaDer() == null && raiz.getRamaDer() == null)
        {
            nuevoMapa.put(raiz.getClave(), bin);
            return nuevoMapa;
        }
        String aux = bin;
        nuevoMapa.putAll(this.generarMapa(raiz.getRamaIzq(), aux + '0'));
        nuevoMapa.putAll(this.generarMapa(raiz.getRamaDer(), aux + '1'));
        return nuevoMapa;
    }
    
    public Map<String, byte[]> generarMapaBinario(Nodo raiz , String bin)
    {
        Map< String, byte[]> mapaBin = new HashMap<>();
        codigoPrefijo = generarMapa(raiz, bin);
        
        for(String clave: codigoPrefijo.keySet()){
            String valor = codigoPrefijo.get(clave);
            int tam = valor.length();
            byte[] bit = new byte[tam];
            for(int i=0;i<= tam-1;i++){
                    Character letra = valor.charAt(i);
                    if (letra == '0'){
                        bit[i] = 0;
                    }
                    else{
                        bit[i] = 1;
                    }
            }
                mapaBin.put(clave, bit);  
            }
        return mapaBin;
        }
    }
