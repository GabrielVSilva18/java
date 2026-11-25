import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        // Variáveis
        Scanner teclado = new Scanner(System.in);
        String[][] velha = new String[3][3];
        String jog1 = "";
        String jog2 = "";
        int linha = 0;
        int coluna = 0;
        int preenchidos = 0;
        boolean fim = false;
        boolean verifica = false;

        // Nome dos jogadores
        System.out.println("Digite o nome do jogador 1 (X):");
        jog1 = teclado.nextLine();
        System.out.println("Digite o nome do jogador 2 (O):");
        jog2 = teclado.nextLine();
        String vez = jog1;

        // 1° Exibição do Jogo da Velha
        System.out.println("=======================");
        System.out.println("     JOGO DA VELHA     ");
        System.out.println("=======================");
        System.out.printf("Bem vindo(a) %s e %s! \n", jog1, jog2);

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                velha[x][y] = " ";
                System.out.printf("[%s]", velha[x][y]);
            }
            System.out.println("");
        }

        // Input dos jogadores
        while (!fim) {
            System.out.printf("*****VEZ DE %s*****\n", vez.toUpperCase());
            verifica = false;
            while (!verifica) {
                System.out.println("Em qual linha deseja jogar? [1 a 3]");
                linha = teclado.nextInt() - 1;
                teclado.nextLine();
                System.out.println("Em qual coluna deseja jogar? [1 a 3]");
                coluna = teclado.nextInt() - 1;
                teclado.nextLine();
                if (velha[linha][coluna].equals(" ")) {
                    verifica = true;
                }
                else {
                    System.out.println("Posição inválida, tente novamente");
                }
            }
            
            // Verifica se é o 1° jogador (X) ou 2° jogador (O)
            if (vez.equals(jog1)) {
                velha[linha][coluna] = "X";
            }
            else {
                velha[linha][coluna] = "O";
            }

            // Exibição após input do jogador
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    System.out.printf("[%s]", velha[x][y]);
                }
                System.out.println("");
            }

            // Verifica vitória horizontal
            for (int x = 0; x < 3; x++) {
                if (velha[x][0].equals(velha[x][1]) && velha[x][1].equals(velha[x][2]) && !velha[x][0].equals(" ")) {
                    System.out.printf("%s venceu o jogo!", vez);
                    fim = true;
                }
            }
            
            // Verifica vitória vertical
            for (int y = 0; y < 3; y++) {
                if (velha[0][y].equals(velha[1][y]) && velha[1][y].equals(velha[2][y]) && !velha[0][y].equals(" ")) {
                    System.out.printf("%s venceu o jogo!", vez);
                    fim = true;
                }
            }

            // Verifica vitória diagonal
            if (velha[0][0].equals(velha[1][1]) && velha[1][1].equals(velha[2][2]) && !velha[0][0].equals(" ")) {
                System.out.printf("%s venceu o jogo!", vez);
                fim = true;
            }
            else if (velha[0][2].equals(velha[1][1]) && velha[1][1].equals(velha[2][0]) && !velha[0][2].equals(" ")) {
                System.out.printf("%s venceu o jogo!", vez);
                fim = true;
            }

            // Verifica empate
            preenchidos++;
            if (!fim && preenchidos == 9) {
                System.out.println("Deu velha!");
                fim = true;
            }

            // Altenar jogador
            if (!fim) {
                if (vez.equals(jog1)) {
                    vez = jog2;
                }
                else {
                    vez = jog1;
                }
            }
        }
        teclado.close();
    }
}
