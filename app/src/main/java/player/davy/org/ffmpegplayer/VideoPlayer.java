package player.davy.org.ffmpegplayer;

public class VideoPlayer {

    static {
        System.loadLibrary("ffmpeg-jni");
    }

    public static native String avcodecInfo();
    public static native int play(Object surface);
}