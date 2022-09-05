package synthesizer;

import javax.swing.JFrame;

/**
 * start up class of an application that plays Java MIDI sounds
 * 
 * @author Paul Moonen
 */
public class StartupMainClass extends JFrame{
    public static void main(String[] args){        
        
        JFrame frame = new StartupMainClass();
        frame.setSize(200, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Java MIDI sound player");
        frame.setContentPane(new MidiPane());
        frame.setVisible(true);    
    } 
}
