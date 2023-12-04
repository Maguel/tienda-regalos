package org.tecnologias;

import org.tecnologias.admin.Admin;
import org.tecnologias.db.DataBase;
import org.tecnologias.empleado.Empleado;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        String opc;
        do {
            System.out.println("Inicie sesión\n1.- Administrador\n2.- Empleado\n3.- Salir");
            opc = sc.nextLine();
            switch (opc) {
                case "1":
                    System.out.println("Introduzca la contraseña");
                    if (DataBase.validarAdmin(sc.nextLine())) {
                        Admin.adminMain();
                    } else {
                        System.out.println("Contraseña invalida");
                    }
                    break;
                case "2":
                    System.out.println("Introduzca la contraseña");
                    if (DataBase.validarEmpleado(sc.nextLine())) {
                        Empleado.empleadoMain();
                    } else {
                        System.out.println("Contraseña invalida");
                    }
                    break;
                case "3":
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción invalida");
            }
        } while (!opc.equals("3"));
    }
}
