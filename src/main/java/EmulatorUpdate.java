import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Распаковка и замена файлов эмулятора
 */
public class EmulatorUpdate {

    /** Выражение для поиска архива с эмулятором */
    private static final String regExp = "ServiceEmulator.*.zip";

    /** Логгер */
    private static Logger log = Logger.getLogger(EmulatorUpdate.class);

    /**
     * Распаковка и замена эмулятора
     */
    public static void updateEmulator(Configuration config) {
        log.info("Обновляем сервисный эмулятор");
        HelperUtils helper = new HelperUtils();

        try {
            String emulatorPath = config.getEmulatorPath();
            if (emulatorPath == null) {
                log.error("Не задан путь к сервисному эмулятору");
                return;
            }
            helper.cleanDir(config.getEmulatorPath());
            File archive = helper.searchFile(config.getArchivesPath(), regExp);
            if (archive == null) {
                throw new FileNotFoundException("Файл " + archive + " не найден");
            }
            helper.unzipArchive(archive.getAbsolutePath(), config.getEmulatorPath());
            log.info("Обновление заверешено");
        } catch (Exception ex) {
            log.error(ex);
        }
    }
}
