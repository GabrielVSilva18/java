import java.util.Scanner;

import javax.swing.JOptionPane;

public class App {

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

                case 4:
                    exibirRelatorio(produtos, precos, estoque);
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

    // Registra venda, recebe código do produto informado pelo usuario, solicita quantidade no estoque, atualiza o estoque e calcula valor total da venda
    private static void registrarVenda(String[][] produtos, double[] precos, int[] estoque) {
        boolean invalido = true;
        int posicao = 0;
        int qtd = 0;

        // Recebe código inserido e identifica produto
        String codigo = (JOptionPane.showInputDialog("Informe o código do produto:")).trim();
        for (int l = 0; l < produtos.length; l++) {
            if (produtos[l][0] != null && produtos[l][0].equals(codigo)) {
                System.out.println("Produto identificado!");
                for (int c = 0; c < produtos[l].length; c++) {
                    System.out.print(produtos[l][c]  + " ");
                }
                System.out.print(precos[l] + " ");
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
            System.out.println(qtd + "unidades de " +  produtos[posicao][1] + " foram vendidos!");
            System.out.println("Valor total: " + (precos[posicao] * qtd));
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
                System.out.print(precos[l] + " ");
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
