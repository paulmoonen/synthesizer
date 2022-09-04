package synthesizer;

import java.util.HashMap;
import java.util.Map;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Patch;
import javax.sound.midi.Synthesizer;

/**
 * This class describes a standard Java MIDI synthesizer.
 * It provides methods to print listings of avalable, and of currently loaded instruments,
 * and a method to return a hashmap instrument names and their corresponding progran numbers 
 * (can be used in an instrument selection menu in user interface).
 * Sound generation methods will be implemented by various classes that extend this class and implement Runnable   
 * 
 * @author Paul Moonen
 */
public class MidiSynthesizer {

    protected Synthesizer synthesizer;
    protected MidiChannel[] midichannellist;
    protected MidiChannel usedchannel;
    protected Instrument[] availableinstruments;
    protected Instrument[] loadedinstruments;
    protected Map<Integer, String> instrumentsmap;

    public MidiSynthesizer(){
        try{        
            this.synthesizer = MidiSystem.getSynthesizer();
            this.synthesizer.open();
            
            // each channel may hold one 'instrument': a sound generation program.
            // each cnannel can play more than one note at the same time ( think chords )
            // this synthesizer has 16 channels ( uncomment println statement below to verify )
            this.midichannellist = synthesizer.getChannels();
            //System.out.println("Number of MIDI channels avalable: " + midichannellist.length);
            
            //select a channel to use
            this.usedchannel = midichannellist[0];

            //available instruments to this synthesizer
            this.availableinstruments = synthesizer.getAvailableInstruments(); 
            //printAvailableInstruments();           

            //currently / automatically loaded instruments in this synthesizer
            this.loadedinstruments = synthesizer.getLoadedInstruments();
            //printLoadedInstruments();
        
            /*
             * the instruments all seem to be organised in bank #0
             * with different preset numbers to each instrument
             * example: Instrument: Marimba bank #0 preset #12
             * banks are sets of instruments
             */        

            //create hashmap of all loaded instrument names and program numbers
            if(loadedinstruments.length == 0){
                System.out.println("no loaded instruments to play");
            }
            if(loadedinstruments.length != 0){
                
                instrumentsmap = new HashMap<>(); 

                //loop through all instruments
                for(Instrument instr : loadedinstruments){
                    //get name and patch
                    String instrumentname = instr.getName();
                    //a patch object contains bank number and instrument number
                    Patch patch = instr.getPatch();
                    int program = patch.getProgram();
                    //add name and number to the instrumentsmap
                    instrumentsmap.put(program, instrumentname);                      
                }
            }
        }
        catch(MidiUnavailableException e){
            e.printStackTrace();   
        }   
    }  
    
    /**
     * Print list of available instruments.
     */
    public void printAvailableInstruments(){
        System.out.println("List of available instruments: ");
        for(Instrument instr : availableinstruments){
            System.out.println(instr);
        }
        System.out.println("***** end of list of available instruments *****");
    }

    /**
     * Print list of currently loaded instruments.
     */
    public void printLoadedInstruments(){
        System.out.println("List of currently loaded instruments: ");
        for(Instrument instr : loadedinstruments){
            System.out.println(instr);
        }
        System.out.println("***** end of list of loaded instruments *****");
    }

    /**
     * Returns HashMap of loaded instrument names and their program numbers.
     * 
     * @return Map<Integer, String> 
     */
    public Map getInstrumentsMap(){
        return instrumentsmap;
    }
}
