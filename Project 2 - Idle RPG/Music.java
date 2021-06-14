import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Music {

    public Clip clip;
    long clipTimePosition = 0;

    void playBackgroundMusic(String musicLocation){
        try{
            File musicPath = new File(musicLocation);

            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);

                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volume.setValue(-10);

                // start playing audio
                clip.start();
                // keep looping forever
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                // JOptionPane.showMessageDialog(null, "Press OK to stop playing");
                // long clipTimePosition = 0;
                // if(Data.game_pause){
                //     clipTimePosition = clip.getMicrosecondPosition();
                //     clip.stop();
                //     System.out.println("Paused");
                // }
                // else {
                //     clip.setMicrosecondPosition(clipTimePosition);
                //     clip.start();
                //     System.out.println("Resumed");
                // }
            }else {
                System.out.println("Cannot find file");
            }
        }catch(Exception e){
            e.printStackTrace(); // shows the error message
        }
    }

    void pause(){
        clipTimePosition = clip.getMicrosecondPosition();
        clip.stop();
        // System.out.println("Paused");
    }

    void resume(){
        while (clipTimePosition >= 8.3e+7){
            clipTimePosition -= 8.3e+7;
        }
        clip.setMicrosecondPosition(clipTimePosition);

        FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(-10);

        clip.start();
        // System.out.println("Resumed");
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    void playSoundEffect(String musicLocation){
        try{
            File musicPath = new File(musicLocation);

            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                // louder
                // FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                // volume.setValue(6);
                // start playing audio
                clip.start();
                // keep looping forever
                // clip.loop(Clip.LOOP_CONTINUOUSLY);
                // JOptionPane.showMessageDialog(null, "Press OK to stop playing");
            }else {
                System.out.println("Cannot find file");
            }
        }catch(Exception e){
            e.printStackTrace(); // shows the error message
        }
    }

    void hitGroundEffect(String musicLocation){
        try{
            File musicPath = new File(musicLocation);

            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                // louder
                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volume.setValue(6);
                // start playing audio
                clip.start();
                // keep looping forever
                // clip.loop(Clip.LOOP_CONTINUOUSLY);
                // JOptionPane.showMessageDialog(null, "Press OK to stop playing");
            }else {
                System.out.println("Cannot find file");
            }
        }catch(Exception e){
            e.printStackTrace(); // shows the error message
        }
    }
}
