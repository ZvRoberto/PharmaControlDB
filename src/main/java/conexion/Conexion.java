package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
            "jdbc:sqlserver://localhost:1433;"
            + "databaseName=PharmaControlDB;"
            + "encrypt=true;"
            + "trustServerCertificate=true;";

    private static final String USUARIO = "usr_farmacia";
    private static final String PASSWORD = "Farmacia1!";

    public static Connection conectar() throws SQLException {

        return DriverManager.getConnection(
                URL,
                USUARIO,
                PASSWORD
        );
    }

    public static void main(String[] args) {

        System.out.println("Probando conexion...");

        try (Connection conexion = conectar()) {

            if (conexion != null && !conexion.isClosed()) {

                System.out.println("==============================");
                System.out.println("CONEXION EXITOSA");
                System.out.println("Base de datos: PharmaControlDB");
                System.out.println("Usuario: usr_farmacia");
                System.out.println("==============================");
            }

        } catch (SQLException e) {

            System.out.println("==============================");
            System.out.println("ERROR DE CONEXION");
            System.out.println(e.getMessage());
            System.out.println("==============================");
        }
    }
}