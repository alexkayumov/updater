import net.lingala.zip4j.ZipFile;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * Распаковка и замена файлов эмулятора
 */
public class EmulatorUpdate {

    /** Выражение для поиска архива с эмулятором */
    private static final String regExp = "ServiceEmulator.*.zip";

    /** Выражение для поиска архива сервера интеграции */
    private static final String regExpRainbow = "Scheduled_Release_Branch_Build";

    /** Логгер */
    private Logger log = Logger.getLogger(EmulatorUpdate.class);

    /** Кастомная директория эмулятора */
    private String customEmulatorPath;

    /** Директория с установленным эмулятором */
    private String emulatorPath;

    /** Диреткория с архивами для обновления */
    private String archivePath;

    /** Вспомогательный обьект для работы с файлами */
    private HelperUtils helper;

    /**
     * Конструктор. Инициализация данных
     *
     * @param config данные файла config.prop
     */
    EmulatorUpdate(Configuration config) {
        customEmulatorPath = config.getCustomEmulatorPath();
        emulatorPath = config.getEmulatorPath();
        archivePath = config.getArchivesPath();
        helper = new HelperUtils();
    }

    /**
     * Распаковка и замена эмулятора
     */
    public void updateEmulator() {
        try {
            if (customEmulatorPath == null) {
                System.out.println("ssssssssssssssssss");
                log.debug("Указан путь к эмулятору " + customEmulatorPath);
                File emulator = helper.getArchive(customEmulatorPath, regExp);
                updateEmulator(emulator);
            }
            System.out.println("zzzz==========" + archivePath);
            File rainbowArchive = helper.getArchive(archivePath, regExpRainbow);
//            ZipFile zipFile = new ZipFile(file);
//            zipFile.extractAll(destination);
//            log.info("Распаковка архива " + file.getName() + " в директорию " + destination + " завершена");

            // Распаковываем общий архив сборки

            log.info("Обновление эмулятора из архива сервера интеграции");
        } catch (Exception ex) {
            log.error(ex);
        }
    }

    /**
     * Обновление сервисного эмулятора
     *
     * @param newEmulatorFile архив для обновления эмулятора
     */
    private void updateEmulator(File newEmulatorFile) {
        log.info("Начало обновления сервисного эмулятора");
        log.debug("Архив для обновления:" + newEmulatorFile.getName());
        helper.cleanDir(emulatorPath);
        helper.unzipArchive(newEmulatorFile.getPath(), emulatorPath);
        log.info("Обновление сервисного эмулятора завершено");
    }
}
