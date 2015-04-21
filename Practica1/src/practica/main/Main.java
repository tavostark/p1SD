/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica.main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import practica.entidad.Cuenta;
import practica.modelo.Deposito;
import practica.modelo.Retiro;
import practica.modelo.Saldo;
import practica.modelo.Transferencia;

/**
 *
 * @author marco
 */
public class Main extends Thread {

    public static final int puerto = 5000;

    private ServerSocket socketServidor;

    public Main() throws IOException {
        socketServidor = new ServerSocket(puerto);
    }

    public void run() {
        DataOutputStream salida;
        DataInputStream entrada;
        String message, dinero = "", resultado,origen="",destino="",monto="",cuenta="";

        while (true) {
            try {
                Socket socketClient = socketServidor.accept();

                entrada = new DataInputStream(socketClient.getInputStream());
                message = entrada.readUTF();


                if (message.charAt(0)== '1') {
                    
                    for(int i=2; i<message.length(); i++){
                        cuenta+=message.charAt(i);
                    }
                    
                    Saldo saldo = new Saldo(Long.parseLong(cuenta));
                    resultado = saldo.getSaldo();

                    salida = new DataOutputStream(socketClient.getOutputStream());
                    salida.writeUTF(resultado);

                    message = cuenta= "";

                } 
                else if (message.charAt(0) == '2') {
                    int z=0;
                    
                    for (int i = 2; i < message.length(); i++) {
                        if(z==0) {
                            if(message.charAt(i)!='@') {
                                cuenta+=message.charAt(i);
                            }
                            else {
                                z=1;
                            }
                        }
                        else if(z==1){
                            dinero += message.charAt(i);
                        }
                    }

                    Retiro retiro = new Retiro(dinero, Long.parseLong(cuenta));

                    resultado = retiro.getRetiro();

                    salida = new DataOutputStream(socketClient.getOutputStream());
                    salida.writeUTF(resultado);

                    message = dinero= cuenta= "";
                } 
                else if (message.charAt(0) == '3') {
                    int z=0;
                    
                    for (int i = 2; i < message.length(); i++) {
                       if(z==0) {
                            if(message.charAt(i)!='@') {
                                cuenta+=message.charAt(i);
                            }
                            else {
                                z=1;
                            }
                        }
                        else if(z==1){
                            dinero += message.charAt(i);
                        }
                    }

                    Deposito deposito = new Deposito(Long.parseLong(cuenta), Double.parseDouble(dinero));

                    resultado = deposito.setDeposito();

                    salida = new DataOutputStream(socketClient.getOutputStream());
                    salida.writeUTF(resultado);

                    message = dinero= "";
                }
                else if(message.charAt(0)== '4') {
                    
                    int z=0;
                    
                    for(int i=2; i < message.length(); i++) {
                        
                        if(z==0){
                            if(message.charAt(i)!='@'){
                                origen+=message.charAt(i);
                            }
                            else {
                                z=1;
                            }
                        }
                        else if(z==1) {
                            if(message.charAt(i)!='@'){
                                destino+=message.charAt(i);
                            }
                            else {
                                z=2;
                            }
                        }
                        else if(z==2) {
                            if(message.charAt(i)!='@'){
                                monto+=message.charAt(i);
                            }
                        }
                        
                    }
                    
                    Transferencia trans = new Transferencia(Double.parseDouble(monto), new Cuenta(Long.parseLong(origen)), new Cuenta(Long.parseLong(destino)));
                    
                    resultado= trans.setTransferencia();
                    salida = new DataOutputStream(socketClient.getOutputStream());
                    salida.writeUTF(resultado);

                    message = origen= destino= monto= "";
                    
                }

                resultado = "";

                socketClient.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        try {
            Thread t = new Main();
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
