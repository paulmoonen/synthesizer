package synthesizer;

import javax.sound.midi.MidiUnavailableException;

/**
 * Class to play one sound.
 * Class PlayOneSoundRunnable describes a runnable.
 * It has a method to play a specific sound at a specific pitch. 
 * That method then creates a Thread using this class runnable, and starts it up. 
 */
public class PlayOneSoundRunnable extends MidiSynthesizer implements Runnable{

    private Thread soundThread;     //a Thread made out of this Runnable
    private int program;            //the sound to play
    private int volume;             //the volume
    private int pitch;              //the pitch

    public PlayOneSoundRunnable(){
        super();                    //call to constructor of parent class MidiSynthesizer    
        this.volume = 100;          //a default volume setting            
    }

    /**
     * Method to play a sound with a selected program / instrument, and pitch.
     * Makes a new Thread out of this Runnable object, and starts it.
     * @param int program: instrument or timbre
     * @param int volume     * 
     */
    public void playSound(int program, int pitch){
        soundThread = null;         //cut any previous sound that may be playing on this Thread
        this.program = program;
        this.pitch = pitch;
        this.soundThread = new Thread(this);
        soundThread.start();
    }

    /**
     * Method run() is called indirectly, via start().
     * This method actually plays the selected sound.     
     */
    public void run(){
        try {
            synthesizer.open();
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
        
        try {
            usedchannel.programChange(program); //load the single instrument-sound-program of this Runnable
            usedchannel.noteOn(pitch, volume);  //start playing
            Thread.sleep(3000);
            usedchannel.noteOff(pitch);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synthesizer.close();
    }
    
}
