package src;

import org.apache.log4j.Logger;

import java.util.Scanner;

/**
 * Основной класс приложения
 */
public class Main {

    /**
     * Логгер
     */
    private static Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
       Configuration config = new Configuration();
        System.out.println(config.toString());
    }

}

