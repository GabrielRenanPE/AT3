import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;

public class OrdenacaoRapida {

    
    private static ArrayList<Double> lerNumeros(String arquivo) {
        ArrayList<Double> numeros = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (!linha.isEmpty()) {
                    try {
                        numeros.add(Double.parseDouble(linha));
                    } catch (NumberFormatException e) {
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo: " + e.getMessage());
        }
        return numeros;
    }

    private static void escreverNumeros(double[] arr, String arquivo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (double num : arr) {
                bw.write(Double.toString(num));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever arquivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ArrayList<Double> numeros = lerNumeros("arq.txt");
        
        double[] arr = new double[numeros.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = numeros.get(i);
        }

        long inicio = System.currentTimeMillis();
        
        Arrays.sort(arr);

        long tempo = System.currentTimeMillis() - inicio;

        long memoriaKB = 0;
        for (MemoryPoolMXBean pool : ManagementFactory.getMemoryPoolMXBeans()) {
            if (pool.getName().contains("Heap")) {
                MemoryUsage peakUsage = pool.getPeakUsage();
                memoriaKB = peakUsage.getUsed() / 1024;
                break;
            }
        }

        escreverNumeros(arr, "arq-saida-java-OR.txt");

        System.out.println("Tempo de execução: " + tempo + " ms");
        System.out.println("Memória utilizada: " + memoriaKB + " KB");
        System.out.println("Informacoes do PC: Intel i5-13, RAM 16GB DDR5, RTX 4050, SSD M2");
        System.out.println("Arquivo de saída 'arq-saida-java-OR.txt' gerado com sucesso.");
    }
}
