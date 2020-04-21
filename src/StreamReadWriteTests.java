import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;

public class StreamReadWriteTests {
    @Test
    void insertDataTest(){
        String test = "Test";
        WriteAndReadDataSet.insertData(test);
    }
    @Test
    void readDataTest(){
        WriteAndReadDataSet.readData();
    }
}
