package com.rizgan.FFmpegBatchFolders;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;

import java.io.IOException;

public class ConvertFile {

    public static FFmpeg ffmpeg;
    static Runtime rt = Runtime.getRuntime();

    static {
        try {
            ffmpeg = new FFmpeg("C:\\ffmpeg\\bin\\ffmpeg.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FFprobe ffprobe;

    static {
        try {
            ffprobe = new FFprobe("C:\\ffmpeg\\bin\\ffprobe.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void convertMp3(String inputFilePath, Object outputFilePath) throws InterruptedException {
        FFmpegBuilder builder = new FFmpegBuilder()

            .setInput(inputFilePath)     // Filename, or a FFmpegProbeResult
            .addOutput(outputFilePath.toString())   // Filename for the destination
            .addExtraArgs("-af", "loudnorm=I=-23:TP=-1")
//            .addExtraArgs("-threads", "20")
            .done();

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

// Run a one-pass encode
        executor.createJob(builder).run();
        Thread.currentThread().join();

//        Process pr = rt.exec("cmd.exe /c copy " + "\"" + tmpDir + realFileName + "\"" + " " + "\"" + fullFilePath + "\"");
//        pr = rt.exec("cmd.exe /c del " + "\"" + tmpDir + realFileName + "\"" + " /f /s /q");
//cmd /c del "+tmpfolder+"IEDriver.dll /f /s /q"
//        System.out.println("Doing: " + arrayOfFilesPaths[i]);
        System.out.println("Done: " + outputFilePath);
    }

    public ConvertFile() throws IOException {
    }
}
