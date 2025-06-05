package dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public abstract class ClienteDAO {
    private static final String CAMINHO = "src/dados/cliente";

    public static void salvar(List<Cliente> funcionarios) throws IOException {
        File diretorio = new File(CAMINHO);
        diretorio.mkdirs();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(diretorio + "/clientes.ser"))) {
            oos.writeObject(funcionarios);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Cliente> carregar() throws IOException, ClassNotFoundException {
        File arquivo = new File(CAMINHO + "/clientes.ser");
        if (!arquivo.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Cliente>) ois.readObject();
        }
    }
}
