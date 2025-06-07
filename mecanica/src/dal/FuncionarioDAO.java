package dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import model.Funcionario;

public abstract class FuncionarioDAO {
    private static final String CAMINHO = "src/dados/funcionario";

    public static void salvar(List<Funcionario> funcionarios) throws IOException {
        File diretorio = new File(CAMINHO);
        diretorio.mkdirs();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(diretorio + "/funcionarios.ser"))) {
            oos.writeObject(funcionarios);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Funcionario> carregar() throws IOException, ClassNotFoundException {
        File arquivo = new File(CAMINHO + "/funcionarios.ser");
        if (!arquivo.exists()) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Funcionario>) ois.readObject();
        }
    }
}
