/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.pkg2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author JP
 */
public class Cliente2 {

    /**
     * @param args the command line arguments
     */
     

    public static void main(String[] args) {
        try {
            /**
             * Creamos un socket cliente 
             */
            System.out.println("Creando socket cliente");
            Socket clienteSocket = new Socket();
            System.out.println("Estableciendo la conexion");
            /**
             * Engadimos a direcion e o porto,para conectarse
             */
            InetSocketAddress direc = new InetSocketAddress("127.0.0.1", 6001);
            clienteSocket.connect(direc);
            /**
             * Creamos os fluxos de lectura e escritura para escribir os numeros e leer o resultado enviado polo servidor
             */
            InputStream leer = clienteSocket.getInputStream();
            OutputStream escribir = clienteSocket.getOutputStream();
            /**
             * Mediante JOptionPane introducimos os numeros 
             */
            int operando1 = Integer.parseInt(JOptionPane.showInputDialog("Introduce o primeiro numero"));

            int operando2 = Integer.parseInt(JOptionPane.showInputDialog("Introduce o segundo numero"));

            int operador = Integer.parseInt(JOptionPane.showInputDialog("Introduce o operador \n" + "1-suma,\n" + " 2-resta,\n" + " 3-division,\n" + " 4-multiplicacion  "));
            /**
             * Escribimos os numeros e eleximos o tipo de operacion que queremos realizar
             */
            escribir.write(operando1);
            escribir.write(operando2);
            escribir.write(operador);
            /**
             * Leemos o resultado e cerramos o socket Cliente
             */
            int resultado = leer.read();

            clienteSocket.close();

        } catch (IOException ex) {

        }
    }

    
}
