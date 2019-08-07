import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Распаковка и обновление сервера интеграции
 */
public class RainbowUpdate {

    /**
     * Логгер
     */
    private static Logger log = Logger.getLogger(RainbowUpdate.class);

    /**
     * Вспомогательный класс для работе с файлами и архивами
     */
    private HelperUtils helper;

    /**
     * Выражение для поиска архива сервера интеграции
     */
    private static final String regExpRainbow = "ServiceEmulator.*.zip";

    /**
     * Выражение для поиска архива сервера интеграции
     */
    private static final String regExpAdmin = "ServiceEmulator.*.zip";

    public RainbowUpdate() {
        helper = new HelperUtils();
    }

    public void updateRinbow(Configuration config) {
        log.info("Обновляем сервер интеграции");
        try {
            // сначала распаковываем общий архив сервера интеграции затем распаковываем админку
            searchAndUnzip(config.getArchivesPath(), regExpRainbow, config.getRainbowPath());
            // после распаковываем админку и запускаем ее обновления
            searchAndUnzip(config.getRainbowPath(), regExpAdmin, config.getRainbowPath());

        } catch (Exception ex){
            log.error(ex);
        }
    }

    /**
     * Поиск и распаковка архива
     *
     * @param searchDir   директория в которой ведется поиск архива
     * @param regExp      регулярное выражение по которому ищется архив
     * @param destination путь куда производится распаковка
     */
    private void searchAndUnzip(String searchDir, String regExp, String destination) throws Exception {
        // Поиск архива
        File rainbowArchive = helper.searchFile(searchDir, regExp);
        if (rainbowArchive == null) {
            throw new FileNotFoundException("Файл архива сервера интеграции" + rainbowArchive + " не найден");
        }
        // Распаковка архива
        helper.unzipArchive(rainbowArchive.getPath(), destination);
    }
}
