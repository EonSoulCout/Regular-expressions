package Practica3;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class main {	

	public static void main(String[] args) {
		Scanner read = new Scanner(System.in);
		int op;
		
		do{
			System.out.print("\n\tAplicación de Manipulación de Archivos\n");
			System.out.print("1.- Escribir texto en el archivo \"Info.txt\"\n");
			System.out.print("2.- Extraer información del archivo \"Info.txt\"\n");
			System.out.print("3.- Salir del programa\n");
			System.out.print("Opción: ");
			op=read.nextInt();

			switch(op){
			case 1:
				try {
					System.out.print("\nPor favor, ingresa el texto que deseas escribir en el archivo:\n");
					String text = read.nextLine();
					text = read.nextLine();
					FileWriter writer = new FileWriter("src/Informacion.txt", true);
					writer.write(text + "\n");
					writer.close();
					System.out.println("\nTexto escrito en el archivo correctamente.\n");
					
				} catch(IOException err) {
					System.out.println("\nError al escribir en el archivo: " + err.getMessage() + "\n");
				}
				break;

			case 2:

				do{

					System.out.print("Seleccione que tipo de información requiere:\n");
					System.out.print("1.- Enlaces web\n");
					System.out.print("2.- Numeros de tarjeta de crédito\n");
					System.out.print("3.- Cadenas en formato CamelCase\n");
					System.out.print("4.- Direcciones de correo electronico\n");
					System.out.print("5.- Salir de la opcion\n");
					System.out.print("\nOpcion: ");
					op = read.nextInt();

					switch(op){
					
					case 1:
						ExtractInformation(1);
						break;
					case 2:
						ExtractInformation(2);
						break;
					case 3:
						ExtractInformation(3);
						break;
					case 4:
						ExtractInformation(4);
						break;
					case 5:
						System.out.println("Volviendo al menu\n");
						break;
					default:
						System.out.println("Opcion no valida, seleccione una opción valida\n");
					}
					
				}while(op!=5);

				break;
			case 3:
				System.out.println("Fin del programa\n");
				break;
			default:
				System.out.println("Opcion no valida, seleccione una opcion valida.\n");
			}
		}while(op!=3);

	}


	public static void ExtractInformation(int type) {

		if(type==1) {
			try {

				String content = new String(Files.readAllBytes(Paths.get("src/Info.txt")));
				Pattern pattern = Pattern.compile("(http|https)://\\S+");
				Matcher matcher = pattern.matcher(content);

				System.out.println("\nEnlaces web encontrados:\n");
				while (matcher.find()) {
					System.out.println(matcher.group());
				}

			} catch (IOException err) {
				System.out.println("\nError al leer el archivo: " + err.getMessage() + "\n");
			}

		}else if(type==2) {

			try {

				String content = new String(Files.readAllBytes(Paths.get("src/Info.txt")));
				Pattern pattern = Pattern.compile("\\b\\d{4}[- ]?\\d{4}[- ]?\\d{4}[- ]?\\d{4}\\b");
				Matcher matcher = pattern.matcher(content);

				System.out.println("\nNúmeros de tarjeta de crédito encontrados:\n");
				while (matcher.find()) {
					System.out.println(matcher.group());
				}

			} catch (IOException err) {
				System.out.println("\nError al leer el archivo: " + err.getMessage() + "\n");
			}

		}else if(type==3) {

			try {

				String content = new String(Files.readAllBytes(Paths.get("src/Info.txt")));
				Pattern pattern = Pattern.compile("\\b[A-Z][a-zA-Z0-9]*\\b");
				Matcher matcher = pattern.matcher(content);

				System.out.println("Cadenas en formato CamelCase encontradas:\n");
				while (matcher.find()) {
					System.out.println(matcher.group());
				}

			} catch (IOException err) {
				System.out.println("Error al leer el archivo: " + err.getMessage() + "\n");
			}

		}else if(type==4) {

			try {

				String content = new String(Files.readAllBytes(Paths.get("src/Info.txt")));
				Pattern pattern = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\\b");
				Matcher matcher = pattern.matcher(content);

				System.out.println("Direcciones de correo electrónico encontradas:\n");
				while (matcher.find()) {
					System.out.println(matcher.group());
				}

			} catch (IOException err) {
				System.out.println("Error al leer el archivo: " + err.getMessage() + "\n");
			}

		}else {
			System.out.println("Error\n");
		}
	}
}
