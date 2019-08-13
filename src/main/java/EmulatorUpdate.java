import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Распаковка и замена файлов эмулятора
 */
public class EmulatorUpdate implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {

        return 0;
    }

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
            System.out.println("test");
            System.out.println(helper.searchFiles(emulatorArchivePath, regExp).size());

            File archive = helper.searchFile(emulatorArchivePath, regExp);
            List<File> findArchiveList = helper.searchFiles(emulatorArchivePath, regExp);
            System.out.println(findArchiveList);
            if (archive == null) {
                throw new FileNotFoundException("Файл архива эмулятора не найден : " + emulatorArchivePath);
            }

            log.info("Начало обновления сервисного эмулятора");
            log.info("Очистка директории " + emulatorPath);

            helper.cleanDir(emulatorPath);

            log.info("Очистка директории завершено");

            //helper.unzipArchive(archive.getAbsolutePath(), emulatorPath);
            log.info("Обновление сервисного эмулятора завершено");
        } catch (Exception ex) {
            log.error(ex);
        }
    }

    public void checkVersion(List<File> listArchive) {
        List<String> listVersions = new ArrayList<>();

    }
}
