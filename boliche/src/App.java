import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) throws Exception {
        String[] participantes = new String[6];
        int[] pontuacao = new int[6];

        for (int participante = 0; participante < participantes.length; participante++) {
            boolean erro = false;

            participantes[participante] = JOptionPane.showInputDialog("Nome do " + (participante + 1) + "° Participante: ");

            do {
                erro = false;

                try {
                    pontuacao[participante] = Integer.parseInt(JOptionPane.showInputDialog("Pontuação de " + participantes[participante]));
                } catch (Exception e) {
                    erro = true;
                    JOptionPane.showMessageDialog(null, "Erro, tente inserir um número!");
                }

                if (pontuacao[participante] > 300 || pontuacao[participante] < 0) {
                    erro = true;
                    JOptionPane.showMessageDialog(null, "A pontuação deve ser entre 0 a 300!");
                }
            } while (erro == true);
        }

        int media = calculaMedia(pontuacao);
        
        exibirRelatorio(participantes, pontuacao);

        System.out.println("=========================");
        System.out.println("    MAIOR PONTUAÇÃO!!    ");
        System.out.println("=========================");
        System.out.println("Nome: " + participantes[identificaMaior(pontuacao)]);
        System.out.println("Pontuação: " + pontuacao[identificaMaior(pontuacao)]);

        System.out.println("=========================");
        System.out.println("   CALCULANDO MÉDIA...   ");
        System.out.println("=========================");
        System.out.println("Média das pontuações: " + media);

        System.out.println("=========================");
        System.out.println("JOGADORES ACIMA DA MÉDIA!");
        for (int participante = 0; participante < participantes.length; participante++) {
            if (pontuacao[participante] > media) {
                System.out.println("-------------------------");
                System.out.println("Nome: " + participantes[participante]);
                System.out.println("Pontuação: " + pontuacao[participante]);
            }
        }
        
    }

    // MÉTODOS / FUNÇÕES ---------------------------------------------------------------------------------

    public static void exibirRelatorio(String[] participantes, int[] pontuacao) {
        System.out.println("=========================");
        System.out.println("  EXIBINDO RELATÓRIO...  ");
        for (int i = 0; i < participantes.length; i++) {
            System.out.println("-------------------------");
            System.out.println("Nome: " + participantes[i]);
            System.out.println("Pontuação: " + pontuacao[i]);
        }
    }

    public static int identificaMaior(int[] pontuacao) {
        int maior = 0;
        int posicaoMaior = 0;

        for (int participante = 0; participante < pontuacao.length; participante++) {
            if (pontuacao[participante] > maior) {
                maior = pontuacao[participante];
                posicaoMaior = participante;
            }
        }

        return posicaoMaior;
    }

    public static int calculaMedia(int[] pontuacao) {
        int soma = 0;

        for (int participante = 0; participante < pontuacao.length; participante++) {
            soma += pontuacao[participante];
        }

        return soma / pontuacao.length;
    }
}
