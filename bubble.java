import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class bubble {

    public static void main(String[] args) {
        try {
            List<Double> numbers = readNumbers("arq.txt");
            double[] arr = new double[numbers.size()];
            for (int i = 0; i < numbers.size(); i++) {
                arr[i] = numbers.get(i);
            }

            Runtime runtime = Runtime.getRuntime();
            runtime.gc(); // Sugere a coleta de lixo para limpar memória
            long beforeMem = runtime.totalMemory() - runtime.freeMemory();

            long startTime = System.nanoTime();

            bubbleSort(arr);

            long endTime = System.nanoTime();
            double duration = (endTime - startTime) / 1e9;
            double tempo = Math.round(duration * 1000 * 100.0) / 100.0;

            long afterMem = runtime.totalMemory() - runtime.freeMemory();
            long memoria = afterMem - beforeMem;

            writeNumbers(arr, "arq-saida-java-bubble.txt");

            System.out.println("Tempo de execução: " + tempo + " ms;");
            System.out.println("Memória utilizada: " + memoria + " KB;");
            System.out.println("Arquivo de saída 'arq-saida-java-bubble.txt' gerado com sucesso;");
            System.out.println("Informacoes do PC: Intel i5-13, RAM 16GB DDR5, RTX 4050, SSD M2.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Double> readNumbers(String filename) throws IOException {
        List<Double> nums = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    try {
                        double num = Double.parseDouble(line);
                        nums.add(num);
                    } catch (NumberFormatException e) {
                        // Ignora linhas inválidas
                    }
                }
            }
        }
        return nums;
    }

    private static void bubbleSort(double[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    double temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private static void writeNumbers(double[] arr, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (double num : arr) {
                writer.write(Double.toString(num));
                writer.newLine();
            }
        }
    }
}