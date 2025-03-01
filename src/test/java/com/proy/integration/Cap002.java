package integration;

import java.io.FileNotFoundException;

import com.proy.readers.HandleInput;

public class Cap002 {
    private final String[] PATH = {
        "src\\test\\java\\com\\proy\\integration\\integration_test_resources\\Cap002Directory"
    };

    public void test() {
        HandleInput reader = new HandleInput();
        try {
            reader.getInput(this.PATH);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Cap002 cap02 = new Cap002();
        cap02.test();
    }
}
