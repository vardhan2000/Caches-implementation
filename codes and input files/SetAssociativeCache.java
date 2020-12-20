import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class SetAssociativeCache{
    private Set[] cache = new Set[16384];
    private int hits, misses, accesses;

    public SetAssociativeCache(){
        int i;
        for(i = 0; i < 16384; i++){
            cache[i] = new Set();
        }

        hits = 0;
        misses = 0;
        accesses = 0;
    }

    public int getHits() {
        return hits;
    }

    public int getMisses() {
        return misses;
    }

    public int getAccesses() {
        return accesses;
    }

    void compute_hits_and_misses(String fileName){
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(fileName));
            String line = reader.readLine();
            String address, tag, binAddress;
            int index;
            while(line != null){
                // updating the number of accesses
                accesses += 1;
                
                // extracting the address from each of the read line
                address = line.split("\\s")[1];
                
                // removing the redundant "0x" from the address string
                address = address.substring(2);
                
                // converting the hexadecimal address to 32 bit binary
                binAddress = new BigInteger(address, 16).toString(2);
                binAddress = String.format("%32s", binAddress).replaceAll(" ", "0");
                
                // extracting the 16 bit binary tag-bits and converting them to 4 bits hexadecimal
                tag = new BigInteger(binAddress.substring(0,16), 2).toString(16);
                tag = String.format("%4s", tag).replace(" ", "0");

                // parsing the 14 bit index tag bits to an integer
                index = Integer.parseInt(binAddress.substring(16, 30), 2);
                
                // if we were successful in fetching data
                if(cache[index].getDataFromSet(tag)){
                    hits += 1;
                } else {
                    misses += 1;
                    cache[index].addDataToSet(tag);
                }
                
                // read next line
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
