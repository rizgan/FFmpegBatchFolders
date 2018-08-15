package com.rizgan.FFmpegBatchFolders;

import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        String realFileName = "";
        String fullFilePath = "";
        int cores = Runtime.getRuntime().availableProcessors();
        String tmpDir = "F:\\OUT\\";
        String mp3Dir = "F:\\IN\\";

        Object[] arrayOfFilesPaths = Files.walk(Paths.get(mp3Dir)).filter(Files::isRegularFile).filter(p -> p.toString().endsWith(".MP3") || p.toString().endsWith(".mp3") || p.toString().endsWith(".mp3") || p.toString().endsWith(".mP3") || p.toString().endsWith(".Mp3") || p.toString().endsWith(".wav") || p.toString().endsWith(".Wav") || p.toString().endsWith(".WaV") || p.toString().endsWith(".WAV") || p.toString().endsWith(".wAV") || p.toString().endsWith(".waV") || p.toString().endsWith(".wAv")).toArray();

        for (int i = 0; i < arrayOfFilesPaths.length; i++) {
            realFileName = arrayOfFilesPaths[i].toString().substring(arrayOfFilesPaths[i].toString().lastIndexOf("\\") + 1);
            fullFilePath = arrayOfFilesPaths[i].toString().substring(0, arrayOfFilesPaths[i].toString().lastIndexOf("\\") + 1);

            String forThreadInput = arrayOfFilesPaths[i].toString();
            String forThreadOutput = tmpDir + realFileName;
            Thread t1 = new Thread(new Runnable() {
                public void run() {
                    try {
                        ConvertFile.convertMp3(forThreadInput, forThreadOutput);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

            if (Thread.activeCount() >= cores) {
                while (Thread.activeCount() >= cores) {
                    Thread.sleep(500);
                }
            } else {
                t1.start();
            }
        }
        System.out.println("Done!");
    }
}
