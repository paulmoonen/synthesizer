package synthesizer;

import javax.sound.midi.MidiUnavailableException;

/** 
 * Class PlayAllSounds inherits all the MidiSynthesizer class data.
 * It plays each sound that comes with the Java Midi synthesizer, 
 * and provides methods to tart and stop playing.
 * 
 * @author Paul Moonen
 */
public class PlayAllSounds extends MidiSynthesizer implements Runnable{

    private Thread soundThread;
    private boolean proceed; //boolean to stop the playing
    
    public PlayAllSounds(){        
        super();//call constructor of parent class MidiSynthesizer
        this.proceed = false;                           
    }    

    /**
     * Start playing.
     * A new Thread is made.
     */
    public void startPlaying(){
        this.proceed = true;
        //already a thread running?
        //avoid double sounds after extra click on start button
        if(soundThread != null){
            System.out.println("already playing, my dear...");    
            return;
        }
        //otherwise: let's do this!
        //make a Thread out of this Runnable and start it up
        soundThread = new Thread(this);
        soundThread.start();                
    }

    /**
     * Stop playing.
     * 
     */
    public void stopPlaying(){        
        this.proceed = false;//next sound in the list will not be played
        soundThread = null;
    }

    /**
     * Method is indirectly called, by start().
     * This is where the sounds are actually made.
     * 
     */
    @Override
    public void run(){       
            
        try{
            synthesizer.open();
        }catch(MidiUnavailableException e){
            e.printStackTrace();
        }
        instrumentsmap.forEach((program, instrumentname)->{

            while(proceed){ //stop button pokes in right here
                try{                    
                    usedchannel.programChange(program);//select a new sound ( - generating program )                    
                    usedchannel.noteOn(60, 100);//60 stands for central C pitch, 100 stands for volume
                    Thread.sleep(1000);//continue playing for ms miliseconds
                    usedchannel.noteOff(60);//silence ..
                    Thread.sleep(500);// .. for half a second
                    
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });           
        synthesizer.close();                
    }    
}
