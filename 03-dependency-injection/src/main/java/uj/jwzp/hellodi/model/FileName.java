package uj.jwzp.hellodi.model;

import org.springframework.stereotype.Component;

@Component
public class FileName {
    private String fileName;

    public FileName(){}

    public FileName(String fileName){
        this.fileName=fileName;
    }

    public void setFileName(String fileName){
        this.fileName=fileName;
    }

    public String getFileName(){
        return fileName;
    }

    @Override
    public String toString(){
        return fileName;
    }
}
