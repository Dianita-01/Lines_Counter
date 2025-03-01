package integration;

import java.io.FileNotFoundException;

import com.proy.readers.HandleInput;

public class Cap003 {
    private final String[] PATH = {
        "src\\test\\java\\com\\proy\\integration\\integration_test_resources\\Cap003File.java"
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
        Cap003 cap03 = new Cap003();
        cap03.test();
    }
}
