public class App {
    public static void main(String[] args) throws Exception {
        int[] vet1 = new int[5];
        int[] vet2 = new int[5];
        float[] vet3 = new float[5];
        int num1;
        int num2;
        float num3;
        for (int i = 0; i < vet1.length; i++){
            num1 = EntradaDados.entradaInt("Digite um número " + (i + 1) + " inteiro: ");
            num2 = EntradaDados.entradaInt("Digite um número " + (i + 1) + " inteiro: ");
            num3 = EntradaDados.entradaFloat("Digite um número " + (i + 1) + " decimal: ");
            vet1[i] = num1;
            vet2[i] = num2;
            vet3[i] = num3;
        }

        System.out.println("=======VALORES ATUAIS=======");
        System.out.print("Vetor 1: ");
        EntradaDados.exibeVetorInt(vet1);
        System.out.print("Vetor 2: ");
        EntradaDados.exibeVetorInt(vet2);
        System.out.print("Vetor 3: ");
        EntradaDados.exibeVetorFloat(vet3);

        // Soma do vet1 e vet2 e armazenar no vet3
        for (int i = 0; i < vet1.length; i++) {
            vet3[i] = vet1[i] + vet2[i];
        }

        System.out.println("======SOMA VETORES 1 E 2======");
        System.out.print("Soma: ");
        EntradaDados.exibeVetorFloat(vet3);
    }
}