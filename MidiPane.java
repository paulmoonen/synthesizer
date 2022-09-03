package synthesizer;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.*;

public class MidiPane extends JPanel{

    private JButton playButton;
    private JButton stopButton;
    private transient PlayAllSounds playallsounds; //transient because IDE preferes it like that ...
    
    public MidiPane(){
        
        this.playButton = new JButton("play");
        playButton.addActionListener(new ButtonListener());
        add(playButton);

        this.stopButton = new JButton("stop");
        stopButton.addActionListener((new ButtonListener()));
        add(stopButton);      

        this.playallsounds = new PlayAllSounds();          
    }

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
