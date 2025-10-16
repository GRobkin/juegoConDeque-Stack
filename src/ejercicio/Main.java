/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejercicio;

import java.util.*;

/**
 * Clase principal del programa que permite manipular frases ingresadas por el
 * usuario. Ofrece opciones como invertir el orden de palabras, invertir letras,
 * mostrar frases ordenadas alfabéticamente y mantener un historial limitado a 5
 * frases.
 *
 * Funcionalidades:
 * <ul>
 * <li>Invertir orden de palabras en una frase</li>
 * <li>Invertir letras de cada palabra</li>
 * <li>Ver frases originales en orden alfabético</li>
 * <li>Deshacer la última frase ingresada</li>
 * <li>Historial de hasta 5 frases invertidas</li>
 * </ul>
 *
 * @author 413xi
 */
public class Main {

    /**
     * Escáner global para entrada del usuario
     */
    public static Scanner scan = new Scanner(System.in);
    /**
     * Lista de frases originales ingresadas por el usuario
     */
    public static List<String> frasesOriginales = new ArrayList<>();
    /**
     * Historial de frases invertidas, con un límite de 5 frases
     */
    public static Deque<String> frasesHistorial = new ArrayDeque<>();

    /**
     * Método principal que muestra el menú y gestiona las acciones del usuario.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        boolean ejecutar = true;
        while (ejecutar) {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Invertir nueva frase");
            System.out.println("2. Ver frases originales en orden alfabético");
            System.out.println("3. Desfazer última inversão");
            System.out.println("4. Invertir letras de cada palabra");
            System.out.println("5. Salir");
            System.out.println("Escoja una opción: ");

            int opcion;
            opcion = scan.nextInt();
            scan.nextLine();

            switch (opcion) {
                case 1 ->
                    invertirFrase();
                case 2 ->
                    mostrarFrasesOriginales();
                case 3 ->
                    deshacerUltima();
                case 4 ->
                    invertirLetrasPalabras();
                case 5 -> {
                    System.out.println("Saliendo del programa");
                    ejecutar = false;
                }
                default ->
                    System.out.println("Opción inválida");
            }
        }
    }

    /**
     * Solicita una frase al usuario, invierte el orden de las palabras, guarda
     * la frase original y añade la frase invertida al historial.
     */
    private static void invertirFrase() {
        System.out.print("Escriba una frase: ");
        String fraseOriginal = scan.nextLine();
        frasesOriginales.add(fraseOriginal);
        añadirHistorial(fraseOriginal);
        String fraseInvertida = invertirOrdenPalabras(fraseOriginal);

        System.out.println("Frase original: " + fraseOriginal);
        System.out.println("Frase invertida: " + fraseInvertida);
    }

    /**
     * Invierte el orden de las palabras de una frase utilizando una pila.
     *
     * @param frase La frase original a invertir.
     * @return La frase con el orden de palabras invertido.
     */
    private static String invertirOrdenPalabras(String frase) {
        Stack<String> stackPalabra = new Stack<>();
        String[] palabras = frase.split(" ");
        StringBuilder fraseInvertida = new StringBuilder();

        for (String palabra : palabras) {
            stackPalabra.push(palabra);
        }

        while (!stackPalabra.isEmpty()) {
            fraseInvertida.append(stackPalabra.pop());
            if (!stackPalabra.isEmpty()) {
                fraseInvertida.append(" ");
            }
        }
        return fraseInvertida.toString();
    }

    /**
     * Muestra las frases invertidas almacenadas en el historial, ordenadas
     * alfabéticamente (máximo 5 frases).
     */
    private static void mostrarFrasesOriginales() {
        if (frasesHistorial.isEmpty()) {
            System.out.println("No hay frases en el historial.");
            return;
        }

        List<String> historialOrdenado = new ArrayList<>(frasesHistorial);
        historialOrdenado.sort(String::compareToIgnoreCase);

        System.out.println("\n--- Últimas 5 frases invertidas (orden alfabético) ---");
        for (String frase : historialOrdenado) {
            System.out.println(frase);
        }
    }

    /**
     * Elimina la última frase añadida al historial.
     */
    private static void deshacerUltima() {
        if (frasesHistorial.isEmpty()) {
            System.out.println("No hay frases para deshacer");
            return;
        }

        String fraseDeshecha = frasesHistorial.pop();
        System.out.println("Última frase eliminada: " + fraseDeshecha);
    }

    /**
     * Solicita una frase al usuario e invierte las letras de cada palabra
     * individualmente. Guarda la frase original y añade la invertida al
     * historial.
     */
    private static void invertirLetrasPalabras() {
        System.out.print("Ingrese la frase: ");
        String fraseOriginal = scan.nextLine();
        frasesOriginales.add(fraseOriginal);
        añadirHistorial(fraseOriginal);
        StringBuilder fraseInvertida = new StringBuilder();
        String[] palabras = fraseOriginal.split(" ");

        for (int i = 0; i < palabras.length; i++) {
            fraseInvertida.append(new StringBuilder(palabras[i]).reverse());
            if (i < palabras.length - 1) {
                fraseInvertida.append(" ");
            }
        }

        System.out.println("Frase original : " + fraseOriginal);
        System.out.println("Frase invertida: " + fraseInvertida);
    }

    /**
     * Añade una frase al historial. Si el historial ya contiene 5 frases,
     * elimina la más antigua (al final de la cola).
     *
     * @param frase La frase invertida a añadir al historial.
     */
    private static void añadirHistorial(String frase) {
        frasesHistorial.push(frase);
        if (frasesHistorial.size() > 5) {
            frasesHistorial.removeLast();
        }
    }
}
