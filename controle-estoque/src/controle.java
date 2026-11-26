import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class controle {

    public static void main(String[] args) throws Exception {
        Scanner teclado = new Scanner(System.in);
        String[][] produtos = new String[50][3]; // [0] código, [1] nome, [2] categoria
        double[] precos = new double[50];
        int[] estoque = new int[50];
        boolean fim = false;

        do {
            System.out.println("===============================");
            System.out.println("      CONTROLE DE ESTOQUE      ");
            System.out.println("===============================");
            System.out.println("[1] Cadastrar produto\n" +
                                "[2] Registrar venda\n" +
                                "[3] Repor estoque\n" +
                                "[4] Exibir relatório\n" +
                                "[5] Consultar produto\n" +
                                "[6] Salvar dados\n" +
                                "[7] Carregar dados\n" +
                                "[8] Finalizar");
            System.out.println("===============================");
            System.out.print("Insira uma opção: ");

            try {
                int opcao = teclado.nextInt();
                switch (opcao) {
                case 1:
                    cadastrarProduto(produtos, precos, estoque);
                    break;

                case 2:
                    registrarVenda(produtos, precos, estoque);
                    break;

                case 3:
                    reporEstoque(produtos, precos, estoque);
                    break;

                case 4:
                    exibirRelatorio(produtos, precos, estoque);
                    break;

                case 5:
                    consultarProduto(produtos, precos, estoque);
                    break;

                case 6:
                    salvarDados(produtos, precos, estoque);
                    break;

                case 7:
                    carregarDados(produtos, precos, estoque);
                    break;

                case 8:
                    fim = true;
                    break;
            
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida, tente novamente!");
                    break;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Apenas números são aceitos! [1 a 8]");
                teclado.nextLine();
            }
        } while (!fim);

        teclado.close();
    }

    // MÉTODOS ---------------------------------------------------------------------------------------------------

    // Carrega os dados salvos no arquivo e armazena as informações na matriz e vetores
    private static void carregarDados(String[][] produtos, double[] precos, int[] estoque) {
        try {
            FileReader arquivo = new FileReader("estoque.txt");
            BufferedReader carregar = new BufferedReader(arquivo);
            String linhaArquivo;
            int posicao = 0;

            // Limpa todos os registros
            for (int l = 0; l < produtos.length; l++) {
                produtos[l][0] = null;
                produtos[l][1] = null;
                produtos[l][2] = null;

                precos[l] = 0;
                estoque[l] = 0;
            }

            // Lê cada registro e guarda na matriz e vetores
            while ((linhaArquivo = carregar.readLine()) != null) {
                if (posicao > 50) {
                    break;
                }
                String[] linha = linhaArquivo.split(";");
                produtos[posicao][0] = linha[0];
                produtos[posicao][1] = linha[1];
                produtos[posicao][2] = linha[2];
                precos[posicao] = Double.parseDouble(linha[3]);
                estoque[posicao] = Integer.parseInt(linha[4]);
                posicao += 1;
            }

            carregar.close();
            JOptionPane.showMessageDialog(null, "Os dados foram carregados!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados!");
        }
    }

    // Salva os dados de estoque em um arquivo de texto
    private static void salvarDados(String[][] produtos, double[] precos, int[] estoque) {
        try {
            FileWriter arquivo = new FileWriter("estoque.txt");
            BufferedWriter salvar = new BufferedWriter(arquivo);

            // Salva cada dado guardado na matriz e vetores
            for (int l = 0; l < produtos.length; l++) {
                if (produtos[l][0] != null && estoque[l] != 0) {
                    for (int c = 0; c < produtos[l].length; c++) {
                        salvar.write(produtos[l][c] + ";");
                    }
                    salvar.write(precos[l] + ";" + estoque[l] + "\n");
                }
            }

            salvar.close();
            JOptionPane.showMessageDialog(null, "Dados salvos com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar dados!");
        }

    }

    // Realiza consulta de produto, localizando pelo código inserido pelo usuário
    private static void consultarProduto(String[][] produtos, double[] precos, int[] estoque) {
        boolean invalido = true;
        
        String codigo = (JOptionPane.showInputDialog("Informe o código do produto:")).trim();
        for (int l = 0; l < produtos.length; l++) {
            if (produtos[l][0] != null && produtos[l][0].equals(codigo)) {
                System.out.println("Produto identificado!");
                System.out.println("Código / Nome / Categoria / Preço / Estoque");
                for (int c = 0; c < produtos[l].length; c++) {
                    System.out.print(produtos[l][c]  + " ");
                }
                System.out.print("R$" + precos[l] + " ");
                System.out.println(estoque[l]);
                invalido = false;
                break;
            }
        }
        if (invalido) {
            JOptionPane.showMessageDialog(null, "Produto não identificado!");
        }
    }

    // Localiza produto por código, solicita quantidade a adicionar e atualiza estoque
    private static void reporEstoque(String[][] produtos, double[] precos, int[] estoque) {
        int posicao = 0;
        int qtd = 0;
        boolean invalido = true;

        // Recebe codigo inserido e localiza produto
        String codigo = (JOptionPane.showInputDialog("Informe o código do produto:")).trim();
        for (int l = 0; l < produtos.length; l++) {
            if (produtos[l][0] != null && produtos[l][0].equals(codigo)) {
                System.out.println("Produto identificado!");
                System.out.println("Código / Nome / Categoria / Preço / Estoque");
                for (int c = 0; c < produtos[l].length; c++) {
                    System.out.print(produtos[l][c]  + " ");
                }
                System.out.print("R$" + precos[l] + " ");
                System.out.println(estoque[l]);
                posicao = l;
                invalido = false;
                break;
            }
        }
        if (invalido) {
            JOptionPane.showMessageDialog(null, "Produto não identificado!");
        } else {
            // Solicita quantidade que será adicionada
            do {
                qtd = Integer.parseInt(JOptionPane.showInputDialog("Insira quantidade:"));
            } while (qtd <= 0);

            estoque[posicao] += qtd;
        }

        JOptionPane.showMessageDialog(null, "Quantidade adicionada com sucesso!");
        System.out.println("===============================");
        System.out.println(qtd + " unidades de " + produtos[posicao][1] + " foram adicionados no estoque!");
        System.out.println("Estoque atual: " + estoque[posicao]);
    }

    // Registra venda, recebe código do produto informado pelo usuario, solicita quantidade no estoque, atualiza o estoque e calcula valor total da venda
    private static void registrarVenda(String[][] produtos, double[] precos, int[] estoque) {
        boolean invalido = true;
        int posicao = 0;
        int qtd = 0;

        // Recebe código inserido e localiza produto
        String codigo = (JOptionPane.showInputDialog("Informe o código do produto:")).trim();
        for (int l = 0; l < produtos.length; l++) {
            if (produtos[l][0] != null && produtos[l][0].equals(codigo)) {
                System.out.println("Produto identificado!");
                System.out.println("Código / Nome / Categoria / Preço / Estoque");
                for (int c = 0; c < produtos[l].length; c++) {
                    System.out.print(produtos[l][c]  + " ");
                }
                System.out.print("R$" + precos[l] + " ");
                System.out.println(estoque[l]);
                posicao = l;
                invalido = false;
                break;
            }
        }
        if (invalido) {
            JOptionPane.showMessageDialog(null, "Produto não identificado!");
        } else {
            // Pede quantidade desejada, confere se a quantidade inserida é aceitavel, realiza registro da venda, atualiza o estoque e exibe o registro
            do {
                qtd = Integer.parseInt(JOptionPane.showInputDialog("Insira quantidade: (máx " + estoque[posicao] + ")"));
            } while (qtd <= 0 || qtd > estoque[posicao]);

            estoque[posicao] -= qtd;

            JOptionPane.showMessageDialog(null, "Sua venda foi registrada!");
            System.out.println("===============================");
            System.out.println(qtd + " unidades de " +  produtos[posicao][1] + " foram vendidos!");
            System.out.println("Valor total: R$" + (precos[posicao] * qtd));
            System.out.println("Estoque atual: " + estoque[posicao]);
        }
    }

    // Exibe produtos cadastrados, quantidade de produtos cadastrados, valor total de estoque, maior e menor qtd em estoque e quantidade disponível
    private static void exibirRelatorio(String[][] produtos, double[] precos, int[] estoque) {
        int cont = 0;
        int somaEstoque = 0;
        double somaValorEstoque = 0;
        int maiorQtd = 0;
        int menorQtd = 0;
        // Define valor inicial de menorQtd, recebendo o primeiro valor disponível
        for (int l = 0; l < produtos.length; l++) {
            if (produtos[0] != null && estoque[l] != 0) {
                menorQtd = estoque[l];
                break;
            }

        }

        System.out.println("Código / Nome / Categoria / Preço / Estoque");
        // Verifica se há produto cadastrado e realiza contagem de produtos, soma da quantidade e valor total do estoque e identifica produto com maior e menor estoque
        for (int l = 0; l < produtos.length; l++) {
            if (produtos[l][0] != null && estoque[l] != 0) {
                cont += 1;
                somaEstoque += estoque[l];
                somaValorEstoque += precos[l] * estoque[l];

                if (maiorQtd < estoque[l]) {
                    maiorQtd = estoque[l];
                }

                if (menorQtd > estoque[l]) {
                    menorQtd = estoque[l];
                }
            }

            // Exibe os dados dos produtos
            for (int c = 0; c < produtos[l].length; c++) {
                if (produtos[l][0] != null && estoque[l] != 0) {
                    System.out.print(produtos[l][c] + " ");
                }
            }
            if (produtos[l][0] != null && estoque[l] != 0) {
                System.out.print("R$" + precos[l] + " ");
                System.out.print(estoque[l] + " \n");
            }
        }
        System.out.println("===============================");
        System.out.println("Produtos cadastrados: " + cont);
        System.out.println("-------------------------------");
        System.out.println("Valor total do estoque: R$" + somaValorEstoque);
        System.out.println("-------------------------------");
        System.out.println("Produto com maior estoque: " + maiorQtd);
        System.out.println("Produto com menor estoque: " + menorQtd);
        System.out.println("-------------------------------");
        System.out.println("Quantidade disponível: " + somaEstoque);
    }

    // Realiza o cadastro do produto de acordo com os dados inseridos pelo usuário
    private static void cadastrarProduto(String[][] produtos, double[] precos, int[] estoque) {
        // Busca primeira linha vazia para preenche-la, valores de estoque e preço devem ser maiores que zero
        for (int l = 0; l < produtos.length; l++) {
            if (produtos[0] != null && estoque[l] == 0) {
                try {
                    produtos[l][0] = String.valueOf(l + 1);
                    produtos[l][1] = JOptionPane.showInputDialog("Informe nome do produto:");
                    produtos[l][2] = JOptionPane.showInputDialog("Informe categoria do produto:");
                    do {
                        precos[l] = Double.parseDouble(JOptionPane.showInputDialog("Informe preço do produto:"));
                    } while (precos[l] == 0.0 || precos[l] < 0.0);
                    
                    do {
                        estoque[l] = Integer.parseInt(JOptionPane.showInputDialog("Informe quantidade:"));
                    } while (estoque[l] == 0 || estoque[l] < 0);
                    JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto, tente novamente!");
                }
                break;
            }
        }
    }
}
