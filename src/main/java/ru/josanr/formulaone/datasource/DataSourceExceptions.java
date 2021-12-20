package ru.josanr.formulaone.datasource;

public class DataSourceExceptions extends RuntimeException{

    private DataSourceExceptions(String message) {
        super(message);
    }

    public static DataSourceExceptions becauseFileNotFound(){
        return new DataSourceExceptions("File notFound");
    }

    public static DataSourceExceptions becauseFileReadError(){
        return new DataSourceExceptions("File read Error");
    }
}
