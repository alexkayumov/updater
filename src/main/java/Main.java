import org.apache.log4j.Logger;

/**
 * Основной класс приложения
 */
public class Main {

    /** Обновление сервисного эмулятора */
    private static final String UPDATE_EMULATOR = "1";

    /** Обновление сервера интеграции */
    private static final String UPDATE_INTEGRATION = "2";

    /** Логгер */
    private static Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Запуск приложения");
        Configuration config = new Configuration(args[0]);
        //switch (args[1]) {
            switch ("1") {
            case UPDATE_EMULATOR:
                new EmulatorUpdate(config).updateEmulator();
                break;
            case UPDATE_INTEGRATION:
                RainbowUpdate rainbowUpdate = new RainbowUpdate();
                rainbowUpdate.updateRinbow(config);
            default:
                log.error("Неизвестный режим обновления");
                break;
        }
    }
}

