/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.edu.ister.modelo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
/**
 *
 * @author Christian
 * @Instituto Superior "Tecnologico Rumiñahui"
 * @Programacion Orientada a Objetos
 */
public class Cliente {
    private Socket socketCliente;
    
    private InputStream inputStream;
    private OutputStream outputStream;
    
    private DataInputStream entradaDatos;
    private DataOutputStream salidaDatos;
    
    private boolean opcion = true;
    
    private Scanner scanner;
    private String escribir;
    
    public void conexion(int numeroPuerto, String ipMaquina){
        try {
            socketCliente = new Socket(ipMaquina, numeroPuerto);
            Thread hilo1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(opcion){
                        escucharDatos(socketCliente);
                        System.out.println("Cliente");
                    }
                }
            });
            hilo1.start();
            while(opcion){
                scanner = new Scanner(System.in);
                escribir = scanner.nextLine();
                if (!escribir.equals("Servidor: fin")) {
                    enviarDatos("Cliente: "+escribir);
                } else {
                    opcion = false;
                    cerrarTodo();
                }
            }
        } catch (Exception ex) {
            System.out.println("Error al abrir los socket Cliente"+ex.getMessage());
        }
    }
    
    public void escucharDatos(Socket socket){
        try {
            inputStream = socket.getInputStream();
            entradaDatos = new DataInputStream(inputStream);
            System.out.println(entradaDatos.readUTF());
        } catch (Exception ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviarDatos(String datos){
        try {
            outputStream = socketCliente.getOutputStream();
            salidaDatos = new DataOutputStream(outputStream);
            salidaDatos.writeUTF(datos);
            salidaDatos.flush();
        } catch (Exception ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cerrarTodo(){
        try {
            salidaDatos.close();
            entradaDatos.close();
            socketCliente.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}