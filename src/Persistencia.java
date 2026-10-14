//Ferramentas que permitem com que a gente trabalhe com arquivos

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class Persistencia {

    public static void salvar(Object dados, String caminho) {
        try (ObjectOutputStream saida = new ObjectOutputStream(new FileOutputStream(caminho))) {

            saida.writeObject(dados);
            System.out.println("Dados salvos!");

        } catch (IOException e) {
            System.out.println("Erro ao salvar os dados: " + e.getMessage());
        }
    }

    public static Object carregar(String caminho) {
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(caminho))) {

            return entrada.readObject();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar os dados: " + e.getMessage());
            return null;
        }
    }
}
