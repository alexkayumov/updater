package src;

import org.apache.log4j.Logger;

/**
 * Основной класс приложения
 */
public class Main {

    /**
     * Логгер
     */
    private static Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        log.debug("Стартуем приложение");
        // Парсим файл с настройками приложения
        SettingsXmlInit settingsXmlInit = new SettingsXmlInit();

    }
}

