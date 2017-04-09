package tps.tp1.pack1Decisoes;

import java.util.Scanner;

/**
 * A classe P04Switch corresponde ao 4o exercicio do capitulo de Decisoes da serie de
 * exercicios. Um objecto desta classe determina qual o nome e a estacao do ano de
 * um determinado mes introduzido pelo utilizador.
 * <p>
 * @author Andre Fonseca
 */
public class P04Switch {

    protected static String[] ELEMENTS = { "Fire", "Earth", "Metal", "Water", "Wood" };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Month number? \n");
        int input = Integer.parseInt(scanner.nextLine());

        String month = "";
        String season = "";

        switch (input) {
            case 1:
                month = "January";
                season = "totally within Winter.";
                break;
            case 2:
                month = "February";
                season = "totally within Winter.";
                break;
            case 3:
                month = "March";
                season = "mainly within Winter.";
                break;
            case 4:
                month = "April";
                season = "totally within Spring.";
                break;
            case 5:
                month = "May";
                season = "totally within Spring.";
                break;
            case 6:
                month = "June";
                season = "mainly within Spring.";
                break;
            case 7:
                month = "Jully";
                season = "totally within Summer.";
                break;
            case 8:
                month = "August";
                season = "totally within Summer.";
                break;
            case 9:
                month = "September";
                season = "mainly within Summer.";
                break;
            case 10:
                month = "October";
                season = "totally within Autumn.";
                break;
            case 11:
                month = "November";
                season = "totally within Autumn.";
                break;
            case 12:
                month = "December";
                season = "mainly within Autumn.";
                break;
            default:
                System.out.println("Month number invalid.");
                return;
        }

        System.out.printf("The month number %d is %s and is %s", input, month, season);
    }


}
