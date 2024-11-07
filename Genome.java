import java.io.File;        //import connection to file
import java.io.FileReader;  //import for reading file
public class Genome {
    
    public static void exception(File a){               //extra method just for fun
        if(a.length() % 3 != 0){                        //test if there are only 'triplets' in the file
            System.out.println("ERROR");
        }else{
            System.out.println(a.length());             //just to print the length, so you can calculate yourself if it works
        }
    }
    
    public static void main(String[] args) {
        String GenSeq = "ccctttatgbbbxxxtaaccctttccctttatgbbbssstaacccttt";

        // Convert string to an array of characters (necessary?)
        char[] GenSeqArr = GenSeq.toCharArray();
        String Gen = ""; // Variable that stores the current gene
        int startIndex = -1; // To track the starting index of "atg"
        int gencounter = 0;

        File ecoli = new File("ecoli.txt");               //in() path for file
        FileReader ecolireader = new FileReader(file);    //initialize FileReader and let it read the file
        long leng = ecoli.length();                        
        char[] car = new char[(int)leng];                 //length of Arry = length of file BUT cast long from filelength to int for arraylength
        ecolireader.read(car);                            //let FileReader read the complete file and save on char Array

        exception(file);                                 //extra method just for fun
        
        int i = 0;
        while (i < GenSeqArr.length - 2) {
            //loop through characters in GenSeq
            for (i = i; i < GenSeqArr.length - 2; i += 3) { // Stop before the last two characters because we think in triples
                // Find Start Index i: Check for the sequence "atg"
//                System.out.println(i);
                if (GenSeqArr[i] == 'a' && GenSeqArr[i + 1] == 't' && GenSeqArr[i + 2] == 'g') {
                    startIndex = i; // the index for the "a" in the atg starting sequence
                }
                // Check for the End sequences tga, tag, taa
                char c1 = GenSeqArr[i], c2 = GenSeqArr[i+1], c3 = GenSeqArr[i+2];
                // if ((GenSeqArr[i] == 't' && GenSeqArr[i + 1] == 'a' && GenSeqArr[i + 2] == 'a') ||
                //         (GenSeqArr[i] == 't' && GenSeqArr[i + 1] == 'a' && GenSeqArr[i + 2] == 'g') ||
                //         (GenSeqArr[i] == 't' && GenSeqArr[i + 1] == 'g' && GenSeqArr[i + 2] == 'a') )
                if ((c1 == 't' && c2 == 'a' && (c3 == 'a' || c3 == 'g')) || (c1 == 't' && c2 == 'g' && c3 == 'a')) // man koennte das auch noch kuerzen, aber so ist es noch relativ gut lesbar
                {
                    if (startIndex != -1) { // If we found "atg" before any End sequence: Ignore
                        /*use substring command to extract part of string:
                         *start by Start index, end with i + 3 (include stop Codon) */
                        Gen = GenSeq.substring(startIndex, i += 3); //i = Position vor End Sequenz ("break")
                        gencounter++;
                        System.out.println("Gen " + gencounter + ": " + Gen );
                    }
                    break; // Exit the loop when any End sequence was found
                }
            }
        }
        // Print the original sequence, the character array, and the collected characters
        System.out.println("Gesamte Sequenz: " + GenSeq);                     // Print the original sequence
        //System.out.println("GenSeqArr: " + String.valueOf(GenSeqArr)); // Print array as a string
//        System.out.println("Gen: " + Gen);                           // Print collected characters including "taa"
    }
}

