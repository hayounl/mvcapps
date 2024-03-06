package mvc;

import java.io.Serializable;

public class Model extends Publisher implements Serializable {
    protected Boolean unsavedChanges = false;
    protected String fileName = null;


}
