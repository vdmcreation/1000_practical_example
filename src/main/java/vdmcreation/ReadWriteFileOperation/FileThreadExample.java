package vdmcreation.ReadWriteFileOperation;

import java.io.*;

public class FileThreadExample {
    private static String data = "";

    public static void main(String[] args) throws InterruptedException {
        String path1 = "/{firstFilePath}/FirstFile.txt";
        String path2 = "/{secondFilePath}/SecondFile.txt";
        String path3 = "/{thirdFilePath}/ThirdFile.txt";
        Thread th1 = new Thread(new MyThreadClas(path1, "READ"));
        Thread th2 = new Thread(new MyThreadClas(path2, "READ"));
        Thread th3 = new Thread(new MyThreadClas(path3, "WRITE"));
        th1.start();
        th2.start();
        th2.join();
        th3.start();
    }

    public static class MyThreadClas implements Runnable {
        private String fileName;
        private String mode;

        public MyThreadClas(String fileName, String mode) {
            this.fileName = fileName;
            this.mode = mode;
        }

        @Override
        public void run() {
            try {
                if (mode.equals("READ")) {
                    readFile(fileName);
                } else {
                    writeFile(fileName);
                }
            } catch (Exception e) {
                System.out.println("Exception occured: " + e.getMessage());
            }

        }

        void readFile(String fileName) throws InterruptedException, IOException {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                synchronized (FileThreadExample.class) {
                    data += line + "\n";
                    System.out.println("Reading from : " + line);
                }
                // Sleep for 1 second
                Thread.sleep(1000);
            }
        }

        void writeFile(String fileName) throws IOException, InterruptedException {
            synchronized (FileThreadExample.class) {
                File yourFile = new File("score.txt");
                yourFile.createNewFile(); // if file already exists will do nothing
                new FileOutputStream(yourFile, false);
                FileWriter writer = new FileWriter(fileName);
                writer.write(data);  // Write the combined data
                writer.close();
                Thread.sleep(2000);
                data += "Writing to " + fileName + "\n";  // Append line to data
            }
        }
    }
}

