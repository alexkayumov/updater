package src;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Распаковка и замена файлов эмулятора
 */
public class EmulatorUpdate {

    /** Выражение для поиска архива с эмулятором */
    private static final String regExp = "ServiceEmulator.*.zip";

    /** Путь к архиву эмулятора из target*/
    private static final String emulatorTargetPath = "/home/kayumov/projects/rainbow/emulator/service-emulator/dist/target/";

    /**
     * Распаковка и замена файлов эмулятора
     */
    public static void updateEmulator() {
        System.out.println("Производим обновление сервисного эмулятора");
        Configuration conf = new Configuration();
        HelperUtils helper = new HelperUtils();
        try {
            System.out.println("Очищаем директорию " + conf.getEmulatorPath());
            helper.cleanDir(conf.getEmulatorPath());
            File archive = helper.searchFileArchive(emulatorTargetPath, regExp);
            if (archive == null) {
                throw new FileNotFoundException("Файл не найден");
            }
            System.out.println(archive.getName());
            helper.unzipArchive(archive.getAbsolutePath(), conf.getEmulatorPath());
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
