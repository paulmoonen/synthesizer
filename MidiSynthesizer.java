package synthesizer;

import java.util.HashMap;
import java.util.Map;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Patch;
import javax.sound.midi.Synthesizer;

public class MidiSynthesizer {

    protected Synthesizer synthesizer;
    protected MidiChannel[] midichannellist;
    protected MidiChannel usedchannel;
    protected Instrument[] availableinstruments;
    protected Instrument[] loadedinstruments;
    protected Map<Integer, String> instrumentsmap;

    public MidiSynthesizer(){
        try{        
            //method getSynthesizer() throws a MidiUnavailableException
            this.synthesizer = MidiSystem.getSynthesizer();
            this.synthesizer.open();

            /*
             * each channel may hold an 'instrument': a sound generation program
             * each cnannel can play more than one note at the same time ( think chords )
             * this synthesizer has 16 channels ( check the printed message in the console to be sure )
             */
            this.midichannellist = synthesizer.getChannels();
            this.usedchannel = midichannellist[0];
            //available instruments to this synthesizer
            this.availableinstruments = synthesizer.getAvailableInstruments();
        
            //currently loaded instruments in this synthesizer
            this.loadedinstruments = synthesizer.getLoadedInstruments();
        
            /*
             * the instruments all seem to be organised in bank #0
             * with different preset numbers to each instrument
             * example: Instrument: Marimba bank #0 preset #12
             * banks are sets of instruments
             */  
        

            //make a list of all loaded instrument names and program numbers
            if(loadedinstruments.length == 0){
                System.out.println("no loaded instruments to play");
            }
            if(loadedinstruments.length != 0){
                //make a Map of instrument names an program numbers
                instrumentsmap = new HashMap<>(); 

                //loop through all instruments
                for(Instrument instr : loadedinstruments){
                    //get name and patch
                    String instrumentname = instr.getName();
                    //patch object contains bank number and instrument number
                    Patch patch = instr.getPatch();
                    int program = patch.getProgram();
                    //add name and number to the instrumentsmap
                    instrumentsmap.put(program, instrumentname);                      
                }
            }
        }
        catch(MidiUnavailableException e){
            System.out.println(e);   
        }   
    }    
}
