package src;

/**
 * Обьект с данными xml connection
 */
public class XmlConnectionData {

    private String connectorName;
    private String url;
    private String driver;
    private String schema;
    private String login;
    private String pass;

    public XmlConnectionData(String connectorName,
                             String url,
                             String driver,
                             String schema,
                             String login,
                             String pass) {
        this.connectorName = connectorName;
        this.url = url;
        this.driver = driver;
        this.schema = schema;
        this.login = login;
        this.pass = pass;
    }

    public String getConnectorName() {
        return connectorName;
    }

    public void setConnectorName(String connectorName) {
        this.connectorName = connectorName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "XmlConnectionData" +
                " [connectorName=" + connectorName
                + ", url=" + url
                + ", driver=" + driver
                + ", url=" + schema
                + ", url=" + login
                + ", pass=" + pass + "]";
    }
}
