/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.edu.ister.modelo;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;
/**
 *
 * @author Christian
 * @Instituto Superior "Tecnologico Rumiñahui"
 * @Programacion Orientada a Objetos
 */
public class Servidor {
    
    private Socket miServicio;
    private ServerSocket socketservicio;
    
    private OutputStream outputStream;
    private InputStream inputStream;
    
    private DataOutputStream salidaDatos;
    private DataInputStream entradaDatos;
    
    private boolean opcion = true;
    private Scanner scanner;
    private String escribir;
    
    /**
     * Apertura de Socket
     */
    
    public void conexion (int numeroPuerto){
        try {
            socketservicio = new ServerSocket(numeroPuerto);
            System.out.println("El Servidor se esta escuchando "
                    + "por el puerto"+ numeroPuerto);
            miServicio = socketservicio.accept();
            Thread hilo = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(opcion){
                        System.out.println("Servidor: ");
                        recibirDatos();
                    }
                }
            });
            hilo.start();
            while(opcion){
                scanner = new Scanner(System.in);
                escribir = scanner.nextLine();
                if (!escribir.equals("Cliente: fin")) {
                    enviarDatos("Servidor:"+escribir);
                } else {
                    opcion = false;
                }
            }
            miServicio.close();
        } catch (Exception ex) {
            System.out.println("Error al abrir los sockets");
        }
    }
    
    public void enviarDatos(String datos){
        try {
            outputStream = miServicio.getOutputStream();
            salidaDatos = new DataOutputStream(outputStream);
            salidaDatos.writeUTF(datos);
            salidaDatos.flush();
        } catch (Exception ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void recibirDatos(){
        try {
            inputStream = miServicio.getInputStream();
            entradaDatos = new DataInputStream(inputStream);
            System.out.println(entradaDatos.readUTF());
        } catch (Exception ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarTodo(){
        try {
            salidaDatos.close();
            entradaDatos.close();
            socketservicio.close();
            miServicio.close();
        } catch (Exception ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
