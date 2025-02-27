package integration.integration_test_resources;

public class Cap023File {
    // Método para sumar
    public double sumar(double num1, double num2) {
        return num1 + num2;
    }

    // Método para restar
    public double restar(double num1, double num2) {
        return num1 - num2;
    }

    // Método para ejecutar la operación
    public double ejecutarOperacion(int opcion, double num1, double num2) {
        if (opcion == 1) {
            return sumar(num1, num2);
        } else if (opcion == 2) {
            return restar(num1, num2);
        } else {
            System.out.println("Opción no válida.");
            return 0;
        }
    }
}