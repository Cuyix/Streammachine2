/*
Leider nicht die Lösung, die ich gerne gehabt hätte. Wenn ich nicht müsste, würde ich sowas nicht abgeben :(
Mir fehlt leider eine Lösung dafür, wie ich mit einer unbekannten Anzahl von Werten verschiedener Typen umgehe.
Insbesondere wenn ich davon ausgehe, dass die Werte nicht immer vor mir liegen und nicht immer von mir
ins File geschrieben werden.
 */

import java.io.*;

public class WriteAndReadDataSet {
    public static void main(String[] args) {
        // three example data sets
        String sensorName = "MyGoodOldSensor"; // does not change

        long timeStamps[] = new long[3];
        timeStamps[0] = System.currentTimeMillis();
        timeStamps[1] = timeStamps[0] + 1; // milli sec later
        timeStamps[2] = timeStamps[1] + 1000; // second later

        float[][] values = new float[3][];
        // 1st measure .. just one value
        float[] valueSet = new float[1];
        values[0] = valueSet;
        valueSet[0] = (float) 1.5; // example value 1.5 degrees

        // 2nd measure .. just three values
        valueSet = new float[3];
        values[1] = valueSet;
        valueSet[0] = (float) 0.7;
        valueSet[1] = (float) 1.2;
        valueSet[2] = (float) 2.1;

        // 3rd measure .. two values
        valueSet = new float[2];
        values[2] = valueSet;
        valueSet[0] = (float) 0.7;
        valueSet[1] = (float) 1.2;

        // write three data set into a file
        // TODO: your job. use DataOutputStream / FileOutputStream
        OutputStream os;
        DataOutputStream dos = null;
        try {
            String filename = "Data.txt";
            os = new FileOutputStream(filename);
            dos = new DataOutputStream(os);
        } catch (FileNotFoundException ex) {
            System.err.println("Failed to open file.");
            System.exit(0);
        }
        int numberOfBytesinString = -1;
        try {
            PrintStream ps = new PrintStream(dos);
            ps.print(sensorName);
            numberOfBytesinString = sensorName.getBytes().length;
            for (int i = 0; i < 3; i++) {
                dos.writeLong(timeStamps[i]);
                for (int k = 0; k < values[i].length; k++) {
                    dos.writeFloat(values[i][k]);
                }
            }
        } catch (IOException ex) {
            System.err.println("Failed to write data to file.");
            System.exit(0);
        }

        // read data from file and print to System.out
        // TODO: your job use DataInputStream / FileInputStream
        InputStream is;
        DataInputStream dis = null;
        try {
            String filename = "Data.txt";
            is = new FileInputStream(filename);
            dis = new DataInputStream(is);
        } catch (FileNotFoundException ex) {
            System.err.println("Failed to open file.");
            System.exit(0);
        }
        try {
            byte[] nameBytes = dis.readNBytes(numberOfBytesinString);
            String t = new String(nameBytes);
            System.out.println(t);
            long time = dis.readLong();
            System.out.println(time);
            float val = dis.readFloat();
            System.out.println(val);
            long time2 = dis.readLong();
            System.out.println(time2);
            for (int i = 0; i < 3; i++) {
                float val2 = dis.readFloat();
                System.out.println(val2);
            }
            long time3 = dis.readLong();
            System.out.println(time3);
            for (int i = 0; i < 2; i++) {
                float val3 = dis.readFloat();
                System.out.println(val3);
            }
        } catch (IOException ex) {
            System.err.println("Failed to read from file.");
            System.exit(0);
        }
    }
}
