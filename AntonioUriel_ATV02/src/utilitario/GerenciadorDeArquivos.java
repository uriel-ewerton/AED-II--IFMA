
package utilitario;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author uriel
 */
public class GerenciadorDeArquivos {
    private BufferedReader reader;
    private String ultimaLinhaLida;
    
    public GerenciadorDeArquivos(String filePath) {
        try {
            reader = new BufferedReader(new FileReader(filePath));
            ultimaLinhaLida = null;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public String lerProximaLinha() {
        try {
            ultimaLinhaLida = reader.readLine();
            return ultimaLinhaLida;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public void fecharArquivo() {
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
    

