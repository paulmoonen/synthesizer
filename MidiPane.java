package synthesizer;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.*;

/**
 * Class provides buttons to interact with the sound generating parts of the application
 * 
 * @author Paul Moonen
 */
public class MidiPane extends JPanel{

    private JButton playButton;
    private JButton stopButton;
    private PlayAllSounds playallsounds; //a sound generation Runnable 
    
    public MidiPane(){
        
        this.playButton = new JButton("play");
        playButton.addActionListener(new ButtonListener());
        add(playButton);

        this.stopButton = new JButton("stop");
        stopButton.addActionListener((new ButtonListener()));
        add(stopButton);      

        this.playallsounds = new PlayAllSounds();          
    }
    /**
     * inner class, event listener for buttons
     */    
    class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == playButton){                
                playallsounds.startPlaying();
            }
            if(e.getSource() == stopButton){                
                playallsounds.stopPlaying();                                   
            }
        }
    }    
}
