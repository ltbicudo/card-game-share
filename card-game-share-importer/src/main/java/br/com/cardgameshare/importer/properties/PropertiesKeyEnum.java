package br.com.cardgameshare.importer.properties;

public enum PropertiesKeyEnum {

    JSON_FOLDER_NAME("json.folder.name"), JSON_FILE_NAME("json.file.name"), JSON_FILE_TYPE("json.file.type");

    private String valor;

    PropertiesKeyEnum(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}