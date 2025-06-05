package util;

import java.io.File;
import java.io.PrintStream;

public abstract class Log {
    public static void setError(){
        try {
            File dir = new File("logs");
            dir.mkdir();
            
            PrintStream logErro = new PrintStream("logs/erro.txt");
            System.setErr(logErro);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
