package synthesizer;

import javax.swing.JFrame;

/**
 * start up class of application that plays Java MIDI sounds
 * 
 * @author Paul Moonen
 */
public class TonePlayer extends JFrame{
    public static void main(String[] args){        
        
        JFrame frame = new TonePlayer();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Java MIDI sound player");
        frame.setContentPane(new MidiPane());
        frame.setVisible(true);    
    } 
}
