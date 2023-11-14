import java.util.Random;
import java.util.Arrays;

// TRABALHO SISTEMAS OPERACIONAIS A1
// ALUNO: PEDRO ARAÚJO RODRIGUES
// RA: 22103327

public class JAVA_Trabalho {
    public static int[] generateRandomNumbers(int[] valueArray) {
        Random randomGenerator = new Random();
        for (int i = 0; i < valueArray.length; i++) {
            valueArray[i] = randomGenerator.nextInt(1001);
        }
        return valueArray;
    }

    public static void main(String[] args) {
        final int[] valueArray = new int[1000];
        generateRandomNumbers(valueArray);

        // Calcular media
        Thread thread1 = new Thread(() -> {
            float sum = 0;
            for (int value : valueArray) {
                sum += value;
            }
            float average = sum / 1000;
            System.out.printf("Média: %.2f\n", average);
        });

        // valores maximos e minimos
        Thread thread2 = new Thread(() -> {
            int minValue = valueArray[0];
            int maxValue = valueArray[0];
            for (int value : valueArray) {
                if (value < minValue) {
                    minValue = value;
                }
                if (value > maxValue) {
                    maxValue = value;
                }
            }
            System.out.printf("Menor valor: %d e o maior valor: %d\n", minValue, maxValue);
        });

        // Soma de numeros pares
        Thread thread3 = new Thread(() -> {
            int sum = 0;
            for (int value : valueArray) {
                if (value % 2 == 0) {
                    sum += value;
                }
            }
            System.out.println("Soma total dos pares: " + sum);
        });

        // Conteagem de numeros primos
        Thread thread4 = new Thread(() -> {
            int primoCount = 0;
            int primoCount1 = 0;
            for (int value : valueArray) {
                if (isPrime(value)) {
                    primoCount++;
                }
                if (value == 2) {
                    primoCount1++;
                }
            }
            System.out.println("Tiveram " + primoCount + " numeros primos");
        });

        // Organiza o array de forma ascendente
        Thread thread5 = new Thread(() -> {
            int[] sortedArray = Arrays.copyOf(valueArray, valueArray.length);
            Arrays.sort(sortedArray);
            System.out.println("O array ordenado de forma ascendente: "+Arrays.toString(sortedArray));
        });

        // Organiza o array e pega a mediana
        Thread thread6 = new Thread(() -> {
            int[] sortedArray = Arrays.copyOf(valueArray, valueArray.length);
            Arrays.sort(sortedArray);
            float median;
            if (valueArray.length % 2 == 0) {
                int middle1 = sortedArray[valueArray.length / 2 - 1];
                int middle2 = sortedArray[valueArray.length / 2];
                median = (middle1 + middle2) / 2.0f;
            } else {
                median = sortedArray[valueArray.length / 2];
            }
            System.out.printf("O valor da mediana é: %.2f\n", median);
        });

        // Inicia as threads
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();

        // Espera todas retornarem seus valores antes de continuar
        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
            thread6.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        if (number <= 3) {
            return true;
        }
        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= number; i += 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }
}
