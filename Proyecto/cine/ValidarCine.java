package cine;
import usuarios.cliente.Cliente;

import java.time.LocalDate;

public class ValidarCine {


    public boolean mesNacimiento(Cliente cliente){
        char primermes=cliente.getCURP().charAt(6);
        char segundmes=cliente.getCURP().charAt(7);
        String mes=""+primermes+segundmes;
        int mesnacimiento=Integer.parseInt(mes);

        if (mesnacimiento==LocalDate.now().getMonthValue()){

            return true;
        }
        return false;
    }
}


