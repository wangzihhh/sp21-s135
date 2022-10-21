package capers;

import java.io.File;
import java.io.Serializable;
import static capers.Utils.*;

public class Test {
    public static void main(String[] args) {
        File testFile = new File("test");
        writeContents(testFile, "hello");
        System.out.println(readContentsAsString(testFile));
    }
}
