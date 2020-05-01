package data.databaseControllers;

import java.io.IOException;
import java.util.ArrayList;

public interface IWrite<T> {
    public void write() throws IOException;
    public ArrayList<T> getData();
}
