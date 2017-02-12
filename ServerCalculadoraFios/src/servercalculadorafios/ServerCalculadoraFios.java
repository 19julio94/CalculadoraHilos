/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servercalculadorafios;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.parser.TokenType;

/**
 *
 * @author JP
 */
public class ServerCalculadoraFios extends Thread {

    public static int porto = 6000;
   
    
@Override
    public void run() {

        try {
            System.out.println("Creando un novo Servidor");
            //Creamos un socket Servidor
            ServerSocket serversocket = new ServerSocket();

            System.out.println("Realizando o bind");
            /**
             *Creamos a direccion IP e lle asginamos un porto e a variable porto incrementa en 1 
             * 
             */
            InetSocketAddress direccion = new InetSocketAddress("127.0.0.1", porto);
            serversocket.bind(direccion);
            porto++;
            
            System.out.println("Aceptando Conexions");

            Socket Socket = serversocket.accept();
            /**Se crea un novo socket e o servidor espera polas conexions entrantes
             * 
            */
            if (porto <=6002) {
                Thread cli = new ServerCalculadoraFios();
                cli.start();

                

            } 
            /**
             * Mediante un if preguntamos si o porto e menor que 6002,xa que empeza en 6000 incluindo este,
             * cada vez que se conecte un cliente comproba si o porto corresponde e lanza un fio
             */
            System.out.println("Conexion recibida");
            /**
             * Abrimos fluxos de lectura e escritura para leer os numeros e despois escribir o resultado en cada cliente
             */
            InputStream leer = Socket.getInputStream();
            OutputStream escribir = Socket.getOutputStream();
            /**
             * Leemos os numeros e o operador,e imprimos por consola  unha mensaxe conforme se reciben os numeros.
             */
            int operando1 = leer.read();
            int operando2 = leer.read();
            int operador = leer.read();

            System.out.println("Mensaxe recibida " + operando1);
            System.out.println("Mensaxe recibida " + operando2);

            int resultado;
            /**
             * Mediante un switch case,eleximos o tipo de operacion que queremos realizar
             */
            switch (operador) {
                case 1:

                    JOptionPane.showMessageDialog(null, resultado = operando1 + operando2);
                    escribir.write(resultado);
                    break;

                case 2:

                    JOptionPane.showMessageDialog(null, resultado = operando1 - operando2);
                    escribir.write(resultado);
                    break;

                case 3:

                    JOptionPane.showMessageDialog(null, resultado = operando1 / operando2);
                    escribir.write(resultado);
                    break;

                case 4:

                    JOptionPane.showMessageDialog(null, resultado = operando1 * operando2);
                    escribir.write(resultado);
            }
            /**
             * Cerramos os sockets 
             */
            System.out.println("Cerrando socket");

            Socket.close();
            System.out.println("Cerrando socket servidor");

            serversocket.close();

            System.out.println("¡¡FIN!!");

        } catch (IOException ex) {
            Logger.getLogger(ServerCalculadoraFios.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        new ServerCalculadoraFios().start();
    }
}
