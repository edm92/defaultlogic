package a;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * General utilities and global flags
 *
 * @author Evan Morrison edm92@uowmail.edu.au http://www.fnord.be
 *         Apache License, Version 2.0, Apache License Version 2.0, January 2004 http://www.apache.org/licenses/
 */
public class e {
    public static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    

    public static boolean HIDE_EMPTY_EFFECTS_IN_PRINT = false;
    
    // Global debugging display levels, in files these are generally propagated
    public static boolean __DEBUG = false;
    public static boolean __INFO = true;
    public static boolean __FATAL = true;
    public static boolean __HIGHDETAILS = false;

    // Do logging?
    public static boolean __LOGGER = false;

    // Debug Levels
    public static final int FATAL = 5;
    public static final int DEBUG = 3;
    public static final int INFO = 1;
    public static final int OFF = 0;

    // Default print level
    static final int __DEFAULTDISPLAY = INFO;
    
    // NLP Settings
    public static String RANDOM_RANGE[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
        "l", "m", "n","o","p","q","r","s","t","u","v","w","x","y","z",
        "A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
        "0","1","2","3","4","5","6","7","8","9"};
    public static final double MIN_MATCH_REQUIRED = 0.01; // Min sentence similarity match to bother with.
    public static enum WORD_MATCH_STRENGTH{
    	EXACT(10), 
    	STRONG(.75),
    	MEDIUM(.4), 
    	WEAK(.01),
    	STUPID(.000000001);    	
    	public double MATCH_NUMBER = 1;
    	WORD_MATCH_STRENGTH(double in){
    		MATCH_NUMBER = in;
    	}
    }
    // Similarity Measures		// In be.fnord.util.processModel.util.Distance
    public static double PENALTY_FOR_EXTRA_TRACE = 0.0; // Penalty when extra traces are found
    public static double PENALTY_FOR_EXTRA_LETTER = 0.1;
    public static boolean WHOLE_NUMBER = true;
    public static enum SIM_RESULT {
    	WHOLE_NUMBER(true),
    	RATIO(false);
    	public boolean TYPE;
    	SIM_RESULT(boolean _type){
    		TYPE = _type;
    	}
    }
    

    // Process Model loading
    public static final int NO_FLAGS = 0;
    public static final int DONT_SAVE_MESSAGES_AND_PARTICIPANTS = 1;        // Don't bother with message flows between pools or between actors

    // Trace processing
    public static final int SIMPLE_TRACES = 0;            // Simple trace processing, don't both with order constrained permutations
    public static final int FULL_TRACES = 1;            // Compute ALL traces.

    // Defaults
    public static int DEFAULT_TRACE_TYPE = SIMPLE_TRACES; // Change this to full on completion


    public static final int AGGRESSIVE_DEDUPING = 1;    // Don't consider edges, if you find that edges are being removed then change to simple
    public static final int SIMPLE_DEDUPING = 2;        // Consider edges, this edges up leaving lots of duplicates.
    // See GraphTransformer for implementation to see ifyou can make it better?
    public static final int NO_DEDUPING = 3;

    public static int DEFAULT_DEDUPING_LEVEL = AGGRESSIVE_DEDUPING; // See above

    // Logic special characters
    public static final String AND = "&";        // These are here because I keep forgetting that ^ crashes orbital
    public static final String OR = "|";
    public static final String NOT = "~";
    public static final String IMP = "->";
    public static final String IMPLIES = IMP;

    public static final String EMPTY_EFFECT = "eeee";
    public static final String EMPTY_FORMULA = EMPTY_EFFECT;

    // Special Characters
    public static String endl = System.getProperty("line.separator");
    public static String tab = "\t";

    public static int indent = 0;

    /**
     * Increase indent level when printing to screen.
     */
    public static void incIndent() {
        ++indent;
    }

    /**
     * Decrease indent level when printing to screen
     */
    public static void decIndent() {
        --indent;
        if (indent < 0) indent = 0;
    }

    public static String dent() {
        String result = "";
        for (int i = 0; i < indent; i++) {
            result += tab;
        }
        ;
        return result;
    }
    
    public static String err(String msg){
    	return println(msg, DEBUG);
    }

    /**
     * Simple toString function, input a message and all indenting is done before returning
     *
     * @param msg
     * @return
     */
    public static String write(String msg) {
    	if(HIDE_EMPTY_EFFECTS_IN_PRINT){
	    	msg = msg.replace(a.e.EMPTY_EFFECT + " &", "");
	    	msg = msg.replace(a.e.EMPTY_EFFECT + "&", "");
	    	msg = msg.replace("& " + a.e.EMPTY_EFFECT, "");
	    	msg = msg.replace("&" + a.e.EMPTY_EFFECT, "");
	    	msg = msg.replace("( " + a.e.EMPTY_EFFECT + " ) &", "");
	    	msg = msg.replace("( " + a.e.EMPTY_EFFECT + " )&", "");
	    	msg = msg.replace("&( " + a.e.EMPTY_EFFECT + " )", "");
	    	msg = msg.replace("& ( " + a.e.EMPTY_EFFECT + " )", "");
	    	msg = msg.replace("( " + a.e.EMPTY_EFFECT + " )", "");
	    	msg = msg.replace("(" + a.e.EMPTY_EFFECT + ") &", "");
	    	msg = msg.replace("(" + a.e.EMPTY_EFFECT + ")&", "");
	    	msg = msg.replace("&(" + a.e.EMPTY_EFFECT + ")", "");
	    	msg = msg.replace("& (" + a.e.EMPTY_EFFECT + ")", "");
	    	msg = msg.replace("(" + a.e.EMPTY_EFFECT + ")", "");
	    	msg = msg.replace("( " + a.e.EMPTY_EFFECT + ")", "");
	    	msg = msg.replace("(" + a.e.EMPTY_EFFECT + " )", "");
    	}
        return dent() + msg;
    }

    /**
     * As write, however a new line is added to the string
     *
     * @param msg
     * @return
     */
    public static String writeln(String msg) {
        return write(msg) + endl;
    }

    /**
     * Will both return a string and also display the string to the console at the default display level
     *
     * @param msg
     * @return
     */
    public static String print(String msg) {
        return print(msg, __DEFAULTDISPLAY);
    }

    /**
     * As print, however a new line is added to the string and the print statement.
     * Will also log message to system logger.
     *
     * @param msg
     * @return
     */
    public static String println(String msg) {
        return println(msg, __DEFAULTDISPLAY);
    }

    /**
     * As println, however can set a level of display
     * Will also log message to system logger.
     *
     * @param msg
     * @param displayLevel Either INFO, DEBUG, FATAL
     * @return
     */
    public static String println(String msg, int displayLevel) {
        return print(msg + endl);
    }

    /**
     * As println with display level; however no new line is added.
     * Will also log message to system logger.
     *
     * @param msg
     * @param displayLevel
     * @return
     */
    public static String print(String msg, int displayLevel) {
        String result = write(msg);
        log(result);
        switch (__DEFAULTDISPLAY) {
            case INFO:
                System.out.print(result);
                break;
            case DEBUG:
            case FATAL:
                System.err.print(result);
                break;
        }
        ;
        return result;
    }

    /**
     * Write to the system logger.
     *
     * @param msg
     */
    public static void log(String msg) {
        if (__LOGGER) {
            switch (__DEFAULTDISPLAY) {
                case INFO:
                    LOGGER.info(msg);
                    break;
                case DEBUG:
                    LOGGER.warning(msg);
                    break;
                case FATAL:
                    LOGGER.severe(msg);
                    break;
            }
        }
    }

    /**
     * Init Function, not assumed to be executed
     */
    static boolean __initDone = false;
    static boolean __measureInitDone = false;

    public e() {
        if (!__initDone) {
            LOGGER.setLevel(Level.SEVERE);
            __initDone = true;
        }
    }
    
   
}
