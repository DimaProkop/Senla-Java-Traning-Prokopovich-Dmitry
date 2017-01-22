package com.training.senla;

/**
 * Created by dmitry on 17.11.16.
 */
public class Props {
    private boolean blockStatus;
    private int countRecords;
    private String pathToMainFile;
    private String pathToInstanceFile;
    private String pathToFolderEntity;
    private String pathToFileEntity;
    private String urlToDB;
    private String usernameToDB;
    private String passwordToDB;
    private String pathToDriverJDBC;

    public String getPathToInstanceFile() {
        return pathToInstanceFile;
    }

    public void setPathToInstanceFile(String pathToInstanceFile) {
        this.pathToInstanceFile = pathToInstanceFile;
    }

    public boolean isBlockStatus() {
        return blockStatus;
    }

    public void setBlockStatus(boolean blockStatus) {
        this.blockStatus = blockStatus;
    }

    public int getCountRecords() {
        return countRecords;
    }

    public void setCountRecords(int countRecords) {
        this.countRecords = countRecords;
    }

    public String getPathToMainFile() {
        return pathToMainFile;
    }

    public void setPathToMainFile(String pathToMainFile) {
        this.pathToMainFile = pathToMainFile;
    }

    public String getPathToFolderEntity() {
        return pathToFolderEntity;
    }

    public void setPathToFolderEntity(String pathToFolderEntity) {
        this.pathToFolderEntity = pathToFolderEntity;
    }

    public String getPathToFileEntity() {
        return pathToFileEntity;
    }

    public void setPathToFileEntity(String pathToFileEntity) {
        this.pathToFileEntity = pathToFileEntity;
    }

    public String getUrlToDB() {
        return urlToDB;
    }

    public void setUrlToDB(String urlToDB) {
        this.urlToDB = urlToDB;
    }

    public String getUsernameToDB() {
        return usernameToDB;
    }

    public void setUsernameToDB(String usernameToDB) {
        this.usernameToDB = usernameToDB;
    }

    public String getPasswordToDB() {
        return passwordToDB;
    }

    public void setPasswordToDB(String passwordToDB) {
        this.passwordToDB = passwordToDB;
    }

    public String getPathToDriverJDBC() {
        return pathToDriverJDBC;
    }

    public void setPathToDriverJDBC(String pathToDriverJDBC) {
        this.pathToDriverJDBC = pathToDriverJDBC;
    }
}
