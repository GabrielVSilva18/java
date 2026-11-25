import javax.swing.JOptionPane;

public class EntradaDados {
    public static float entradaFloat(String mensagem) {
        boolean erro = false;
        int aux = 0;

        do {
              String r = JOptionPane.showInputDialog(null, mensagem, "Entrada de Dados", JOptionPane.QUESTION_MESSAGE);
            
            try {
                aux = Integer.parseInt(r);
                erro = false;
            } catch (Exception e) {
                erro = true;
                JOptionPane.showMessageDialog(null, "Valor incorreto! Informe novamente.");
            }
        } while (erro == true);
        return aux;
          
    }

    public static int entradaInt(String mensagem) {
        boolean erro = false;
        int aux = 0;

        do {
              String r = JOptionPane.showInputDialog(null, mensagem, "Entrada de Dados", JOptionPane.QUESTION_MESSAGE);
            
            try {
                aux = Integer.parseInt(r);
                erro = false;
            } catch (Exception e) {
                erro = true;
                JOptionPane.showMessageDialog(null, "Valor incorreto! Informe novamente.");
            }
        } while (erro == true);
        return aux;
    }

    public static void exibeVetorInt(int[] vetor) {
        System.out.print("[ ");
        for (int i = 0; i < vetor.length; i++) {
            System.out.print(vetor[i] + " ");
        }
        System.out.print("] \n");
    }

    public static void exibeVetorFloat(float[] vetor) {
        System.out.print("[ ");
        for (int i = 0; i < vetor.length; i++) {
            System.out.print(vetor[i] + " ");
        }
        System.out.print("] \n");
    }
}
