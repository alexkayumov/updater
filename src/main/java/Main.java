import org.apache.log4j.Logger;

/**
 * Основной класс приложения
 */
public class Main {

    /**
     * Обновление сервисного эмулятора
     */
    private static final String UPDATE_EMULATOR = "1";

    /**
     * Логгер
     */
    private static Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        Configuration config = new Configuration();
        switch (args[0]) {
            case UPDATE_EMULATOR:
                EmulatorUpdate.updateEmulator(config);
                break;
            default:
                log.error("Неизвестный режим обновления");
                break;
        }
    }
}

