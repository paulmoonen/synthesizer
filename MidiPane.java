package synthesizer;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.event.*;

public class MidiPane extends JPanel{

    private JButton playButton;
    private JButton stopButton;
    private JList<String> displaylist;
    private String[] dummygegevens = {"aap", "noot", "mies"};
    private PlayAllSounds playallsounds;
    
    public MidiPane(){
        
        this.playButton = new JButton("unmute");
        playButton.addActionListener(new ButtonListener());
        add(playButton);

        this.stopButton = new JButton("mute");
        stopButton.addActionListener((new ButtonListener()));
        add(stopButton);

        this.displaylist = new JList<>(dummygegevens);
        add(displaylist);

        this.playallsounds = new PlayAllSounds();    
        playallsounds.startThread();

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
