package src;


import java.io.File;

/**
 * �������� ����� ����������
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // ������ ���� � ����������� ����������
        SettingsXmlInit settingsXmlInit = new SettingsXmlInit();
        System.out.println(settingsXmlInit.getPathIbank());

        // � ���������� ������� ���������� ibank ���� ���� version � �������� ��� ��������
        File version = UpdaterFileUtils.searchFile(settingsXmlInit.getPathIbank(), "version");
        System.out.println("�������� version");
        // ��������� ����� � ���������� ������ ����������
    }
}

