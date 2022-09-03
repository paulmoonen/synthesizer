package synthesizer;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.event.*;

public class MidiPane extends JPanel{

    private JButton startButton;
    private JButton stopButton;
    private JList<String> displaylist;
    private String[] dummygegevens = {"aap", "noot", "mies"};
    private PlayAllSounds playallsounds;
    
    public MidiPane(){
        
        this.startButton = new JButton("play");
        startButton.addActionListener(new ButtonListener());
        add(startButton);

        this.stopButton = new JButton("stop");
        stopButton.addActionListener((new ButtonListener()));
        add(stopButton);

        this.displaylist = new JList<>(dummygegevens);
        add(displaylist);

        this.playallsounds = new PlayAllSounds();    
        playallsounds.startThread();

    }

    class ButtonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == startButton){                
                playallsounds.startPlaying();
            }
            if(e.getSource() == stopButton){                
                playallsounds.stopPlaying();                                   
            }
        }
    }    
}
