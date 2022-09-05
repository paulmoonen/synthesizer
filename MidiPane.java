package synthesizer;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.*;

/**
 * Class provides buttons to interact with the sound generating parts of the application
 * 
 * @author Paul Moonen
 */
public class MidiPane extends JPanel{

    private JButton playButton;                     //to start playing each loaded sound
    private JButton stopButton;                     //to stop playing each loaded sound
    private JButton onesoundbutton;                 //to play selected sound
    private JList<String> instrumentslist;          //to put instrument names on screen
    private JScrollPane scrollpane;                 //to make the above list scrollabla
    private PlayAllSoundsRunnable playallsounds;    //a sound generation Runnable 
    private PlayOneSoundRunnable playonesound;      //a sound generating Runnable
    private String[] loadedinstrumentnames;         //list of all loaded instruments
        
    public MidiPane(){
        
        this.playButton = new JButton("play all sounds");
        playButton.addActionListener(new ButtonListener());
        add(playButton);

        this.stopButton = new JButton("stop");
        stopButton.addActionListener((new ButtonListener()));
        add(stopButton);  
          
        this.onesoundbutton =  new JButton("play selected sound");
        onesoundbutton.addActionListener(new ButtonListener());
        
        this.playallsounds = new PlayAllSoundsRunnable();
        this.loadedinstrumentnames = playallsounds.getLoadedInstrumentNames();
        this.playonesound = new PlayOneSoundRunnable();           

        this.instrumentslist = new JList<String>(loadedinstrumentnames);
        this.scrollpane = new JScrollPane(instrumentslist);
        add(scrollpane);
        add(onesoundbutton);
        

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
                // sound names are listed in String[] loadedinstruments
                // which is used in JList<String> instrumentslist
                // their respective program numbers are used as array indices
                int selectedsound = instrumentslist.getSelectedIndex();
                playonesound.playSound(selectedsound, 60);//program #, central C
            }
        }
    }    
}
