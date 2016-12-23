
package Ascii;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Lector {

    public Map<String, Nodo> frecuencia;
    private long asciiLen;

    public Lector() {
        this.frecuencia = new HashMap<>();
    }

    public long getAsciiLen() {
        return asciiLen;
    }

    
    public List<Nodo> frecuenciaSimbolo(String ruta) {
        FileReader reader = null;
        try {
            File archivo = new File(ruta);
            reader = new FileReader(archivo);
            BufferedReader buffReader = new BufferedReader(reader);
            String s = buffReader.readLine();
            while (s != null) {
                int len = s.length();
                for (int i = 0; i <= len - 1; i++) {
                    Character letra = s.charAt(i);
                    if (frecuencia.containsKey(Character.toString(letra))) {
                        this.frecuencia.put(Character.toString(letra), frecuencia.get(Character.toString(letra)).incrementarFrecuencia());
                    } else {
                        this.frecuencia.put(Character.toString(letra), new Nodo(Character.toString(letra)));
                    }
                }
                s = buffReader.readLine();
                if (s != null) {

                    if (frecuencia.containsKey("\n")) {
                        this.frecuencia.put("\n", frecuencia.get("\n").incrementarFrecuencia());
                    } else {
                        this.frecuencia.put("\n", new Nodo("\n"));
                    }
                } else {
                    //this.frecuencia.put("EOF", new Nodo("EOF"));
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        List<Nodo> listaFrecuencia = new LinkedList<>();
        for (String clave : this.frecuencia.keySet()) {
            listaFrecuencia.add(this.frecuencia.get(clave));
        }
        Collections.sort(listaFrecuencia, new CaracterComparator());

        System.out.println("CAracteres con su Frecuencia");
        for (Nodo nodo : listaFrecuencia) {
            System.out.println("Caracter: '" + nodo.getClave() + "'\tFecuencia: " + nodo.getFrecuencia());
        }

        return listaFrecuencia;
    }

    public Map<String, String> generarMapa(List<Nodo> lista) {
        Map<String, String> nuevoMapa = new HashMap<String, String>();
        for (Nodo nodo : lista) {
            Character c = nodo.getClave().charAt(0);
            int ascii = c;
            String binario = Integer.toBinaryString(ascii);
            while (binario.length() < 8) {
                binario = '0' + binario;
            }
            nuevoMapa.put(c.toString(), binario);
        }
        return nuevoMapa;
    }

    public void GenerarArchivo(Map<String, String> claves, String ruta) throws FileNotFoundException, IOException {
        FileWriter escritor = null;
        FileReader reader = null;
        try {
            File archivoR = new File(ruta+".txt");
            reader = new FileReader(archivoR);
            BufferedReader buffReader = new BufferedReader(reader);
            
            File archivo = new File(ruta+"ASCII.txt");
            escritor = new FileWriter(archivo);
            
            String s = buffReader.readLine();
            int k = 0;
            int entero = 0;
            String representacion;

            while (s != null) {
                int len = s.length();
                for (int i = 0; i < len; i++) {
                    Character letra = s.charAt(i);
                    representacion = claves.get(Character.toString(letra));
                    escritor.write(representacion);
                    
                }
                s = buffReader.readLine();
                if (s != null) {
                    if (claves.containsKey("\n")) {
                        representacion = claves.get("\n");
                        escritor.write(representacion);
                    }
                }
            }
                asciiLen = Math.round(Math.ceil(archivo.length()/1024.0));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                escritor.close();
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(Lector.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

}

}
