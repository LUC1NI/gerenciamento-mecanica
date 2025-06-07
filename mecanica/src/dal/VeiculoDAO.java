package dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import model.Veiculo;

public class VeiculoDAO {
    private static final String CAMINHO = "src/dados/veiculos";

    public static void salvar(List<Veiculo> veiculos) throws IOException {
        File diretorio = new File(CAMINHO);
        diretorio.mkdirs(); // 
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(diretorio + "/veiculos.ser"))) {
            oos.writeObject(veiculos);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Veiculo> carregar() throws IOException, ClassNotFoundException {
        File arquivo = new File(CAMINHO + "/veiculos.ser");
        if (!arquivo.exists()) return new ArrayList<>(); 
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Veiculo>) ois.readObject();
        }
    }
}
