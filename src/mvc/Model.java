package mvc;

import java.io.Serializable;

public class Model extends Publisher implements Serializable {
    protected Boolean unsavedChanges = false;
    protected String fileName = null;

    public void changed(){
        //some implementation here.

    }
    public String getFileName(){
        return fileName;
    }
    public Boolean getUnsavedChanges(){
        return unsavedChanges;
    }
}
