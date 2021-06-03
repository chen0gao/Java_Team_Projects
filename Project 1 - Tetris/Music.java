import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class Music {

    void playBackgroundMusic(String musicLocation){
        try{
            File musicPath = new File(musicLocation);

            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                // start playing audio
                clip.start();
                // keep looping forever
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                // JOptionPane.showMessageDialog(null, "Press OK to stop playing");
                long clipTimePosition = 0;
                if(Data.game_pause){
                    clipTimePosition = clip.getMicrosecondPosition();
                    clip.stop();
                    System.out.println("Paused");
                }
                else {
                    clip.setMicrosecondPosition(clipTimePosition);
                    clip.start();
                    System.out.println("Resumed");
                }
            }else {
                System.out.println("Cannot find file");
            }
        }catch(Exception e){
            e.printStackTrace(); // shows the error message
        }
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
