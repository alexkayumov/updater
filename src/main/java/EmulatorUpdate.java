import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

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
        HelperUtils helper = new HelperUtils();
        try {
            String emulatorPath = config.getEmulatorPath();
            String emulatorArchivePath = config.getArchivesPath();
            if (emulatorPath == null || "".equals(emulatorPath)) {
                log.error("Не найден путь к сервисному эмулятору");
                return;
            }
            List<File> fileList = helper.searchFiles(emulatorArchivePath, regExp);
            log.info("Найдено файлов : " + fileList);
            if (fileList.isEmpty()) {
                throw new FileNotFoundException("В директории " + emulatorArchivePath + " не найдены архивы сервисного эмулятора");
            }
            Collections.reverse(fileList);
            File extractArchive = fileList.get(0);

            log.info("Начало обновления сервисного эмулятора");
            log.info("Очистка директории " + emulatorPath);

            helper.cleanDir(emulatorPath);
            log.info("Очистка директории завершена");

            helper.unzipArchive(extractArchive.getPath(), emulatorPath);
            log.info("Обновление сервисного эмулятора завершено");
        } catch (Exception ex) {
            log.error(ex);
        }
    }
}
