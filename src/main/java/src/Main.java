package src;


import java.io.File;

/**
 * Основной класс приложения
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // Парсим файл с настройками приложения
        SettingsXmlInit settingsXmlInit = new SettingsXmlInit();
        System.out.println(settingsXmlInit.getPathIbank());

        // В директории сервера приложений ibank ищем файл version и получаем его значение
        File version = UpdaterFileUtils.searchFile(settingsXmlInit.getPathIbank(), "version");
        System.out.println("Получили version");
        // Выполняем поиск и распаковку архива обновления
    }
}

