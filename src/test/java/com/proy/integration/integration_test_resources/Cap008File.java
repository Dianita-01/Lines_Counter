package test.java.com.proy.integration.integration_test_resources;

// Archivo: Cap08File.java
// Líneas físicas: 8
// Líneas lógicas: 3

public class Cap008File extends Cap007File {
    @Override
    public void metodoAbstracto() {
        System.out.println("Implementación del método abstracto en la clase hija");
    }
    
    public void metodoHijo() {
        System.out.println("Método específico de la clase hija");
    }
}
