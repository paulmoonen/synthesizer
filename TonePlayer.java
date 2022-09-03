package synthesizer;

import javax.swing.JFrame;

/**
 * MIDI tonen via Java genereren
 * 
 */
public class TonePlayer extends JFrame{
    public static void main(String[] args){        
        
        JFrame frame = new TonePlayer();
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("play a Java MIDI note");
        frame.setContentPane(new MidiPane());
        frame.setVisible(true);    
    } 
}
