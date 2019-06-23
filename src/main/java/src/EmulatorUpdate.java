package src;

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
     * Распаковка и замена файлов эмулятора
     */
    public static void updateEmulator(Configuration config) {
        log.info("Обновляем сервисный эмулятор");
        HelperUtils helper = new HelperUtils();
        try {
            System.out.println("Очищаем директорию " + config.getEmulatorPath());
            helper.cleanDir(config.getEmulatorPath());
            File archive = helper.searchFile(config.getArchivesPath(), regExp);
            if (archive == null) {
                throw new FileNotFoundException("Файл " + archive + " не найден");
            }
            helper.unzipArchive(archive.getAbsolutePath(), config.getEmulatorPath());
            log.info("Обновление заверешено");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
