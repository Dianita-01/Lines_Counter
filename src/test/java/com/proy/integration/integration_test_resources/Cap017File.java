package integration.integration_test_resources;

public class Cap017File {
    public static void main(String[] args) {
        int day = 3;
        String dayName = "Domingo";

        switch (day) {
            case 1:
                dayName = "Lunes";
                break;
            case 2:
                dayName = "Martes";
        }
        System.out.println(dayName);
    }
}
