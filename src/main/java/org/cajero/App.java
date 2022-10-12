package org.cajero;

import java.text.SimpleDateFormat;
import java.util.*;

public class App 
{
    private final static String messageExit = "\nSaliendo del programa...";
    public static void main( String[] args )
    {
        Scanner scannerName = new Scanner(System.in);
        Scanner scannerPin = new Scanner(System.in);
        Scanner scannerOption = new Scanner(System.in);
        Scanner scannerPick = new Scanner(System.in);
        double balance = 1000;
        double preBalance;
        List<Map<String,Object>> records = new ArrayList<Map<String,Object>>();

        System.out.print("Inserta nombre: ");
        String inputName = scannerName.nextLine ();
        System.out.print("Inserta PIN: ");
        String inputPin = scannerPin.nextLine ();

        int attempts=1;

        while(attempts<3) {
            if (!"1235".equals(inputPin)) {
                attempts++;
                System.out.println("PIN invalido! \n");
                System.out.print("Inserta nuevamente PIN: ");
                inputPin = scannerPin.nextLine ();
            }
            if("1235".equals(inputPin)) {
                attempts=4;
                System.out.println("\nBienvenido ".concat(inputName).concat("!"));
                String inputOption;
                do{
                    System.out.println("\n1.-Consultar saldo");
                    System.out.println("2.-Retirar saldo");
                    System.out.println("3.-Histórico de Movimientos");
                    System.out.println("4.-Salir");
                    System.out.print("Elije el numero de opcion a realizar: ");
                    inputOption = scannerOption.nextLine ();
                    if(!inputOption.equals("1")&&!inputOption.equals("2")&&!inputOption.equals("3")&&!inputOption.equals("4")){
                        System.out.println("Opcion no valida!\n");
                    }else {
                        char inputOptionChar=inputOption.charAt(0);
                        switch(inputOptionChar) {
                            case '1':
                                System.out.println("\nSaldo actual: ".concat(String.valueOf(balance)));
                                inputOption = getInputOption(scannerOption);
                                inputOption = getInputOption(inputOption);
                                break;
                            case '2':
                                System.out.print("\nSaldo a retirar: ");
                                String inputPick = scannerPick.nextLine ();
                                preBalance = balance;
                                if(inputPick.matches("^[0-9]+(.[0-9]+)?$")) {
                                    balance=balance-Double.valueOf(inputPick);
                                }else{
                                    System.out.println("Respuesta invalida!");
                                    inputOption = getInputOption(scannerOption);
                                    inputOption = getInputOption(inputOption);
                                    continue;
                                }
                                if(balance<0) {
                                    balance=balance+Integer.parseInt(inputPick);
                                    System.out.println("No se tienen fondos suficientes!");
                                }else if(Double.valueOf(inputPick)>0){
                                    System.out.println("Se retiro con exito el saldo!");
                                    Map<String,Object> record = new HashMap<String, Object>();
                                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
                                    String date = formatter.format(new Date());
                                    record.put("date",date);
                                    record.put("drawout",Double.valueOf(inputPick));
                                    record.put("prebalance",String.valueOf(preBalance));
                                    records.add(record);
                                }
                                inputOption = getInputOption(scannerOption);
                                inputOption = getInputOption(inputOption);
                                break;
                            case '3':
                                System.out.println("\nHistorico de movimientos: \n");
                                System.out.print("Fecha: ");
                                System.out.print("                   Retiro: ");
                                System.out.print("  Saldo previo: \n");
                                for (Map<String,Object> map : records) {
                                    System.out.print(String.valueOf(map.get("date")).concat("     "));
                                    System.out.print(String.format("%-10s", map.get("drawout")));
                                    System.out.println(String.valueOf(map.get("prebalance")));
                                }
                                inputOption = getInputOption(scannerOption);
                                inputOption = getInputOption(inputOption);
                                break;
                            case '4':
                                System.out.println(messageExit);
                                break;
                        }
                    }
                }while(!inputOption.equals("1")&&!inputOption.equals("2")&&!inputOption.equals("3")&&!inputOption.equals("4"));
            }else if(attempts==3){
                System.out.println("PIN invalido!");
            }
        }
        if(attempts==3) {
            System.out.print("Se excedío el numero de intentos!!!");
        }
    }

    private static String getInputOption(String inputOption) {
        if(inputOption.equals("1")){
            inputOption ="0";
        }else {
            inputOption ="4";
            System.out.println(messageExit);
        }
        return inputOption;
    }
    private static String getInputOption(Scanner scannerOption) {
        String inputOption;
        do {
            System.out.println("\n1.-Menu");
            System.out.println("2.-Salir");
            System.out.print("Elije el numero de opcion a realizar: ");
            inputOption = scannerOption.nextLine();
            if (!inputOption.equals("1") && !inputOption.equals("2")) {
                System.out.println("Opcion no valida!\n");
            }
        }while(!inputOption.equals("1") && !inputOption.equals("2"));
        return inputOption;
    }
}