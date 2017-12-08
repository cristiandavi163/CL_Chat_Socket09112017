/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.edu.ister.vista;

import ec.edu.ister.modelo.Servidor;

/**
 *
 * @author Christian
 * @Instituto Superior "Tecnologico Rumiñahui"
 * @Programacion Orientada a Objetos
 */
public class AplicacionServidor {
    public static void main(String[] args) {
        Servidor serv = new Servidor();
        serv.conexion(5555);
    }
}
