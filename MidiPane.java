package synthesizer;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.*;
import java.util.Map;

/**
 * Class provides buttons to interact with the sound generating parts of the application
 * 
 * @author Paul Moonen
 */
public class MidiPane extends JPanel{

    private JButton playButton;
    private JButton stopButton;
    private JButton onesoundbutton;
    private PlayAllSoundsRunnable playallsounds; //a sound generation Runnable 
    private PlayOneSoundRunnable playonesound; //a sound generating Runnable
    private Map loadedinstruments; //a list of all loaded instruments and their program numbers
    
    public MidiPane(){
        
        this.playButton = new JButton("play");
        playButton.addActionListener(new ButtonListener());
        add(playButton);

        this.stopButton = new JButton("stop");
        stopButton.addActionListener((new ButtonListener()));
        add(stopButton);  
        
        this.onesoundbutton =  new JButton("one sound");
        onesoundbutton.addActionListener(new ButtonListener());
        add(onesoundbutton);

        this.playallsounds = new PlayAllSoundsRunnable();  
        this.loadedinstruments = playallsounds.getInstrumentsMap();   
        
        this.playonesound = new PlayOneSoundRunnable();       

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
            if(e.getSource() == onesoundbutton){
                //program #, central C
                playonesound.playSound(10, 60);
            }
        }
    }    
}
