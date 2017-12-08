/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ec.edu.ister.vista;

import ec.edu.ister.modelo.Cliente;

/**
 *
 * @author Christian
 * @Instituto Superior "Tecnologico Rumiñahui"
 * @Programacion Orientada a Objetos
 */
public class AplicacionCliente {
    public static void main(String[] args) {
        Cliente cli = new Cliente();
        cli.conexion(5555, "20.0.0.20");
    }
}
