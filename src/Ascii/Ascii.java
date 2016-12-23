
package Ascii;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ascii {
    private Map<String, String> codigoPrefijo;
    private long originalLen;
    private long datLen;
    private long originalAsciiLen;
    
    public void compressAscii(String ruta) throws IOException {
        int ascii = 0;
        Lector lector = new Lector();
        List<Nodo> lista = lector.frecuenciaSimbolo(ruta+".txt");
        Map<String, String> mapa = lector.generarMapa(lista);
        codigoPrefijo = new HashMap<>();
        codigoPrefijo.putAll(mapa);
        
        System.out.println("\nHashMap con los caracteres de clave y los arrays de bits de su valor");
        for (String clave : mapa.keySet()) {
            ascii = clave.charAt(0);
            System.out.println("Clave: '" + clave + "'\tASCII : " + ascii + "\tArrayBiis: " + mapa.get(clave));
        }
        lector.GenerarArchivo(mapa, ruta);
        originalAsciiLen = lector.getAsciiLen();
    }

    public long getOriginalLen() {
        return originalLen;
    }

    public long getDatLen() {
        return datLen;
    }

    public Map<String, String> getCodigoPrefijo() {
        return codigoPrefijo;
    }

    public long getOriginalAsciiLen() {
        return originalAsciiLen;
    }
    
    
    
}
