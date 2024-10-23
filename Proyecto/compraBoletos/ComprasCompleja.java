package compraBoletos;

import java.time.LocalDate;
import java.util.Scanner;

import compraBoletos.ComprasBoletos;
import compraBoletos.utils.MetodoPago;

public class ComprasCompleja {
    Scanner read = new Scanner(System.in);

    String id;
    LocalDate fechaCompra;
    ComprasBoletos boleto;
    int preciototal;
    MetodoPago metodopago;

    public Double completarCompras(Double precioBoletos){

        System.out.println("¿Desea agregar palomitas?");
        System.out.println("1. SI");
        System.out.println("2. NO");
        int res=read.nextInt();
        if (res==1) {
            System.out.println("Eliga un tamaño");
            System.out.println("1. $90 --- Pequeñas");
            System.out.println("2. $125 --- Medianas");
            System.out.println("3. $150 --- Grandes ");
            System.out.println("4. $185 ---Jumbo");
            res=read.nextInt();
            switch (res) {
                case 1:
                    precioBoletos=precioBoletos+90;
                    break;
                case 2:
                    precioBoletos=precioBoletos+125;
                    break;
                case 3:
                    precioBoletos=precioBoletos+150;
                    break;
                case 4:
                    precioBoletos=precioBoletos+185;
                    break;
                default:
                     System.out.println("Esa opción no existe.");
                     break;
            }
        }
        System.out.println("¿Desea agregar refrescos?");
        System.out.println("1. SI");
        System.out.println("2. NO");
        res=read.nextInt();
        if (res==1) {
            System.out.println("Eliga un tamaño");
            System.out.println("1. $75 --- Mediano");
            System.out.println("2. $80 --- Grandes ");
            System.out.println("3. $95 ---Jumbo");
            res=read.nextInt();
            switch (res) {
                case 1:
                    precioBoletos=precioBoletos+75;
                    break;
                case 2:
                    precioBoletos=precioBoletos+80;
                    break;
                case 3:
                    precioBoletos=precioBoletos+95;
                    break;
                default:
                    System.out.println("Esa opción no existe.");
                    break;
            }
        }
        return precioBoletos;
    }
}
