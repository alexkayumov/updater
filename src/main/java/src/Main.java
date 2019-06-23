package src;

import org.apache.log4j.Logger;

/**
 * Основной класс приложения
 */
public class Main {

    private Configuration config;

    /** Логгер */
    private static Logger log = Logger.getLogger(Main.class);

    private Main() {
        config = new Configuration();
    }

    public static void main(String[] args) {
        Main main = new Main();
        log.info("Стартуем приложение");
        switch (args[0]){
            case "1":
               EmulatorUpdate.updateEmulator(main.config);
        }
    }

}

