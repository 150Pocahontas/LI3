package Geral;

import java.io.Serializable;

import static java.lang.System.nanoTime;

public class Crono implements Serializable {

    private static long inicio = 0L;
    private static long fim = 0L;

    /**
     *  Método que inicializa a contagem do tempo.
     */
    public static void start() {
        fim = 0L; inicio = nanoTime();
    }

    /**
     *  Método que termina a contagem do tempo.
     * @return double que é o tempo passado desde a chamada do método Crono.start().
     */
    public static double stop() {
        fim = nanoTime();
        long elapsedTime = fim - inicio;
        // segundos
        return elapsedTime / 1.0E09;
    }

    /**
     *  Método que para e retorna o tempo em string.
     * @return String que é o tempo passado.
     */
    public static String getTime() {
        return "" + stop();
    }

    /**
     *  Método que para e retorna uma mensagem com o tempo passado;
     * @return String com o tempo passado.
     */
    public static String getTImeString() {
        return "Elapsed Time: " +getTime() + " s";
    }
}