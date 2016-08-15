package br.com.cardgameshare.importer.properties;

public enum PropertiesKeyEnum {

    JSON_FOLDER_NAME("json.folder.name"), JSON_FILE_NAME("json.file.name"), JSON_FILE_TYPE("json.file.type"),
    DATABASE_URL("database.url.connection"), DATABASE_USER("database.user"), DATABASE_PASSWORD("database.password");

    private String valor;

    PropertiesKeyEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}