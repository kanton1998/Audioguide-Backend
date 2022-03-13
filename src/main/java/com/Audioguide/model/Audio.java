package com.Audioguide.model;


import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * This class represents a Audiofile
 */
@Entity
public class Audio extends DatabaseEntity {

    /**
     * Filename
     */
    @NotNull
    @Size(min = 3, max = 128)
    private String fileName;

    /**
     * description of the audiofile
     */
    @NotNull
    @Size(min = 3, max = 2500)
    private String fileDescription;



    public Audio(){};

    public Audio(@Size(min = 3, max = 128) String fileName, @Size(min = 3, max = 2500) String fileDescription) {
        this.fileName = fileName;
        this.fileDescription = fileDescription;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

}
