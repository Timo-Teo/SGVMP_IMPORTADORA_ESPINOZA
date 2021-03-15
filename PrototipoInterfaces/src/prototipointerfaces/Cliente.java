/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prototipointerfaces;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author santiago
 */
public class Cliente {
    String nombre;
    String apellido;
    String tipoDoc;
    String numDoc;
    String telf;
    String direccion;
    String correo;
    String estado;

    
    
    public boolean esNombreValido(String nombre){
        Pattern p = Pattern.compile("^[a-zA-Z\\s]{1,60}");
        Matcher m = p.matcher(nombre);
        return m.matches();
    }
    
    public boolean esApellidoValido(String apellido){
        Pattern p = Pattern.compile("^[a-zA-Z\\s]{1,60}");
        Matcher m = p.matcher(apellido);
        return m.matches();
    }
    
    public boolean esDireccionValido(String direccion){
        Pattern p = Pattern.compile("^[a-zA-Z\\s]{1,254}");// puede ser una letra o 254 digitos
        Matcher m = p.matcher(direccion);
        return m.matches();
    }
    
    public boolean esCorreoValido(String correo) {
        Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = p.matcher(correo);
        return m.matches();
    }
    public boolean esTelefonoValido(String telefono) {
        Pattern p = Pattern.compile("(^[09|08])[0-9]{9}");// valida el 09 o 08 mas 9 digitos de 0 al 9
        Matcher m = p.matcher(telefono);
        return m.matches();
    }
    
    public String validarCedula(String numCedula) {
        int ultimoDigito = Character.getNumericValue(numCedula.charAt(9));
        int sumaImpares = 0;
        int sumaPares = 0;
        for(int i=0; i<numCedula.length(); i+=2){
            int impar = Character.getNumericValue(numCedula.charAt(i));
            int imparDuplicado = impar * 2;
            int imparAUsar = 0;
            if (imparDuplicado >= 10) {
                imparAUsar = imparDuplicado - 9;
            }else{
                imparAUsar = imparDuplicado;
            }
            sumaImpares = sumaImpares + imparAUsar;
        }
        for(int j=1; j<numCedula.length()-1; j+=2){
            int par = Character.getNumericValue(numCedula.charAt(j));
            sumaPares = sumaPares + par;
        }
        int sumaTotal = sumaImpares + sumaPares;
        int verificador = (sumaTotal - (sumaTotal%10) + 10) - sumaTotal;
        int numeroAComparar;
        if (verificador == 10){
            numeroAComparar = 0;
        }else{
            numeroAComparar = verificador;
        }
        if (ultimoDigito == numeroAComparar) {
            return numCedula;
        } else {
            return numCedula = "xxxxxxxxxx";
        }
    }
    
    public boolean esDocumentoValido(){
        switch (this.tipoDoc){
            case "C.I.":
                String cedula = validarCedula(this.numDoc);
                if (cedula.equals("xxxxxxxxxx")){
                    return false;
                }
                break;
            case "RUC":
                String RUC = validarCedula(this.numDoc) + "001";
                if (RUC.equals("xxxxxxxxxx001")){
                    return false;
                }
                break;
            case "Pasaporte":
                String passport = validarCedula(this.numDoc);
                if (passport.equals("xxxxxxxxxx")){
                    return false;
                }
                break;
        }
        return true;
    }
    
}
