package synthesizer;

//import java.lang.Thread;
import javax.sound.midi.MidiUnavailableException;

/*
 * class PlayAllSounds inherits all the MidiSynthesizer class data
 * plays each sound that comes with the Java Midi synthesizer 
 * provides methods to tart and stop playing
 */
public class PlayAllSounds extends MidiSynthesizer implements Runnable{

    private Thread soundThread;
    private boolean proceed;
    
    public PlayAllSounds(){
        
        super();
        this.proceed = false;                       
    
    }    

    /*
     * start playing
     * a new Thread is made
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
        soundThread = new Thread(this);
        soundThread.start();
                
    }

    //stop playing
    public void stopPlaying(){        
        this.proceed = false;
        soundThread = null;
    }

    public void run(){       
            
        try{
            synthesizer.open();
        }catch(MidiUnavailableException e){
            e.printStackTrace();
        }
        instrumentsmap.forEach((program, instrumentname)->{

            while(proceed){ //stop button pokes in tight here
                try{                    
                    usedchannel.programChange(program);
                    usedchannel.noteOn(60, 100);
                    Thread.sleep(1000);
                    //a little silent gap
                    usedchannel.noteOff(60);
                    Thread.sleep(500);
                    
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });           
        synthesizer.close();                
    }    
}
