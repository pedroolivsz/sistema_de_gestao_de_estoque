package br.com.pedro.estoque.util;

import br.com.pedro.estoque.model.Produto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ArquivoHelper {
    public static final String CAMINHO_ARQUIVO = "estoque.json";
    public static final Gson gson = new Gson();

    public static void salvarArquivo(ArrayList<Produto> produtos) {
        try (FileWriter writer = new FileWriter(CAMINHO_ARQUIVO)) {
            gson.toJson(produtos, writer);
        } catch(IOException e) {
            System.out.println("Erro ao salvar os arquivos: " + e.getMessage());
        }
    }

    public static ArrayList<Produto> carregarProdutos() {
        try(FileReader reader = new FileReader(CAMINHO_ARQUIVO)) {
            Type tipoLista = new TypeToken<ArrayList<Produto>>() {}.getType();
            return gson.fromJson(reader, tipoLista);
        } catch(IOException e) {
            System.out.println("⚠️ Nenhum arquivo encontrado ou erro ao carregar. Começando com lista vazia.");
            return new ArrayList<>();
        }
    }
}
