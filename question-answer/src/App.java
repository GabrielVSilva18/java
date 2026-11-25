import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        String[] nomes = new String[4];
        String[] cidades = new String[4];
        String[] paises = new String[4];
        double[] viagens = new double[4];
        double[] restaurantes = new double[4];
        double totalrestaurantes = 0;
        double[] total = new double[4];
        int qtdeua = 0;
        int qtdportugal = 0;
        int qtdalemanha = 0;

        Scanner teclado = new Scanner(System.in);
        for (int pessoa = 0; pessoa < 4; pessoa++) {
            System.out.println("----------INFORMAÇÕES PESSOA " + (pessoa + 1) + "----------");
            System.out.println("Nome: ");
            nomes[pessoa] = teclado.nextLine();
            System.out.println("Cidade: ");
            cidades[pessoa] = teclado.nextLine();
            System.out.println("País visitado");
            paises[pessoa] = teclado.nextLine();
            while (!paises[pessoa].equalsIgnoreCase("brasil")
                && !paises[pessoa].equalsIgnoreCase("eua") 
                && !paises[pessoa].equalsIgnoreCase("portugal") 
                && !paises[pessoa].equalsIgnoreCase("alemanha")) {
                System.out.println("Insira somente: Brasil, EUA, Portugal ou Alemanha");
                System.out.println("País visitado: ");
                paises[pessoa] = teclado.nextLine();
            }

            if (paises[pessoa].equalsIgnoreCase("eua")) {
                qtdeua += 1;
            }
            else if (paises[pessoa].equalsIgnoreCase("portugal")) {
                qtdportugal += 1;
            }
            else if (paises[pessoa].equalsIgnoreCase("alemanha")) {
                qtdalemanha += 1;
            }

            System.out.println("Valor da viagem: ");
            viagens[pessoa] = teclado.nextDouble();
            teclado.nextLine();
            System.out.println("Gastos com restaurante: ");
            restaurantes[pessoa] = teclado.nextDouble();
            teclado.nextLine();
            totalrestaurantes += restaurantes[pessoa];
            total[pessoa] = viagens[pessoa] + restaurantes[pessoa];
        }

        System.out.println("********EXIBIÇÃO DE RELATÓRIOS********");
        for (int pessoa = 0; pessoa < 4; pessoa++) {
            System.out.println("======================================");
            System.out.println("----------RELATÓRIO PESSOA " + (pessoa + 1) + "----------");
            System.out.println("Nome: " + nomes[pessoa]);
            System.out.println("Cidade: " + cidades[pessoa]);
            System.out.println("País: " + paises[pessoa]);
            System.out.println("Valor da viagem: R$" + viagens[pessoa]);
            System.out.println("Gastos com restaurante: R$" + restaurantes[pessoa]);
            System.out.println("Valor total: R$" + total[pessoa]);
            if (total[pessoa] <= 15000) {
                System.out.println("Viagem com custo razoável");
            }
            else if (total[pessoa] <= 28000) {
                System.out.println("Viagem com custo alto");
            }
            else {
                System.out.println("Viagem com custo altíssimo!");
            }
        }
        System.out.println("======================================");
        System.out.println("Valor total gasto com restaurantes: " + (totalrestaurantes));
        System.out.println("Valor médio gasto com restaurantes: " + (totalrestaurantes / 4));
        System.out.println("Quantidade de pessoas nos EUA: " + qtdeua);
        System.out.println("Quantidade de pessoas em Portugal: " + qtdportugal);
        System.out.println("Quantidade de pessoas na Alemanha: " + qtdalemanha);
    }
}