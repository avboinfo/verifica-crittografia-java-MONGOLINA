import java.io.*;
import java.util.Scanner;


public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Menu:");
        System.out.println("1. Crypta il contenuto");
        System.out.println("2. Decrypta il contenuto");
        System.out.print("Scelta: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                cryptaContenuto();
                break;
            case 2:
                decryptaContenuto();
                break;
            default:
                System.out.println("Scelta non valida");
        }
        scanner.close();
    }

    private static void cryptaContenuto() {
        try {
            FileReader fileReader = new FileReader("static/data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String content = bufferedReader.readLine();
            String cesareCrypt = cesareCipher(content);
            String xorCrypt = xorCipher(cesareCrypt);
            
            FileWriter fileWriter = new FileWriter("static/data.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(xorCrypt);

            bufferedReader.close();
            bufferedWriter.close();
            System.out.println("Contenuto criptato e scritto su file data.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void decryptaContenuto() {
        try {
            FileReader fileReader = new FileReader("static/data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String content = bufferedReader.readLine();
            String xorDecrypt = xorCipher(content);
            
            String cesareDecrypt = cesareDecipher(xorDecrypt);
            
            FileWriter fileWriter = new FileWriter("static/data.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(cesareDecrypt);
            

            bufferedReader.close();
            bufferedWriter.close();
            System.out.println("Contenuto decriptato e scritto su file data.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String cesareCipher(String text) {
        int key = 13;
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                ch = (char)(((int)ch + key - 65) % 26 + 65);
            }
            result += ch;
        }
            
        return result;
    }

    private static String cesareDecipher(String text) {
        int key = 13;
        String result = "";

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                ch = (char)(((int)ch - key - 65 + 26) % 26 + 65);
            }
            result += ch;
        }
        
        return result;
    }

    private static String xorCipher(String text) {
        char key = 'K';
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            result.append((char)(text.charAt(i) ^ key));
        }
        System.out.println(result);
        return result.toString();
    }

}