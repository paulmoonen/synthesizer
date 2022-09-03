package synthesizer;

import javax.sound.midi.MidiUnavailableException;

/*
 * class PlayAllSounds inherits all the MidiSynthesizer class data
 * it is a thread to enable to close window while playing
 * provides methods to tart and stop playing
 */
public class PlayAllSounds extends MidiSynthesizer implements Runnable{

    private Thread soundThread;
    private boolean proceed;
    
    public PlayAllSounds(){
        
        super();
        this.proceed = true;
        usedchannel.setMute(true);       
    
    }

    //begin thread
    public void startThread(){
        soundThread = new Thread(this);
        soundThread.start();
    }

    //start playing
    public void startPlaying(){
        usedchannel.setMute(false);
        //soundThread.notify();
    }

    //stop playing
    public void stopPlaying(){        
        usedchannel.setMute(true);  
        
    }

    public void run(){
        while(proceed){
            
            try{
                synthesizer.open();
            }catch(MidiUnavailableException e){
                e.printStackTrace();
            }
            instrumentsmap.forEach((program, instrumentname)->{
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
            });           
            synthesizer.close();      
        }  
    }
    
}
