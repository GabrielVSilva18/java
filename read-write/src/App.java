import java.io.FileWriter;
import  java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;

public class App {
    public static void main(String[] args) throws Exception {
        double[][] matriz = new double[100][4];

        gravarArquivo();

        lerArquivo();
        
        for (int linha = 0; linha < matriz.length; linha++) {
            for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
                matriz[linha][coluna] = 0;
            }
        }

        System.out.println("===== MATRIZ ANTES =====");
        for (int linha = 0; linha < matriz.length; linha++) {
            for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
                System.out.print(matriz[linha][coluna] + " ");
            }
            System.out.println();
        }

        separarDados(matriz);

        System.out.println("===== MATRIZ DEPOIS =====");
        for (int linha = 0; linha < matriz.length; linha++) {
            for (int coluna = 0; coluna < matriz[linha].length; coluna++) {
                System.out.print(matriz[linha][coluna] + " ");
            }
            System.out.println();
        }
    }

  private static void separarDados(double[][] matriz) {
        try {
            FileReader arquivo = new FileReader("info.txt");
            BufferedReader leitor = new BufferedReader(arquivo);
            String linha;
            int posicaoLinha = 0;

            while ((linha = leitor.readLine()) != null) {
                String[] dados = linha.split(";");
                matriz[posicaoLinha][0] = Double.parseDouble(dados[0]);
                matriz[posicaoLinha][1] = Double.parseDouble(dados[1]);
                matriz[posicaoLinha][2] = Double.parseDouble(dados[2]);
                matriz[posicaoLinha][3] = Double.parseDouble(dados[3]);
                posicaoLinha += 1;
            }

            leitor.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo");
        }
    }

  private static void lerArquivo() {
        try {
            FileReader arquivo = new FileReader("teste.txt");
            BufferedReader leitor = new BufferedReader(arquivo);
            String linha;
            int contagemLinha = 1;

            while ((linha = leitor.readLine()) != null) {
                System.out.println(contagemLinha + " | " + linha);
                contagemLinha += 1;
            }

            leitor.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo");
        }
    }

  public static void gravarArquivo() {
        try {
            FileWriter arquivo = new FileWriter("info.txt");
            BufferedWriter escrever = new BufferedWriter(arquivo);

            escrever.write("50000;42.5;5.79;246.075");

            escrever.close();
        } catch (IOException e) {
            System.out.println("Erro ao gravar arquivo");
        }
    }
}
