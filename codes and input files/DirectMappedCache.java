import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;

public class DirectMappedCache{
    private Block[] cache = new Block[65536]; // as the index bit-width = 16
    private int hits, misses, accesses;

    public DirectMappedCache(){
        
        // initializing each block of the cache
        int i;
        for(i = 0; i < 65536; i++){
            cache[i] = new Block();
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
                
                // extracting the 14 bit binary tag-bits and converting them to 4 bits hexadecimal
                // for example "00011111111111" will be converted to "07ff"
                tag = new BigInteger(binAddress.substring(0,14), 2).toString(16);
                tag = String.format("%4s", tag).replace(" ", "0");

                // parsing the 16 bit index tag bits to an integer
                index = Integer.parseInt(binAddress.substring(14, 30), 2);
                
                // if the corresponding block has a valid bit value = 1 i.e. true
                if(cache[index].getValid_bit()){
                    if(cache[index].getTag_bit().equals(tag)){ // if the tag matches thenit is a cache hit
                        hits += 1;
                    } else {
                        misses += 1;

                        // analogous to adding the data if it is a miss due to tag mismatch
                        cache[index].setTag_bit(tag);
                    }

                // if the corresponding block has a valid bit value = 0 i.e. false
                // resulting in cache miss
                } else {
                    misses += 1;

                    // analogous to adding the data if it is a miss due to valid bit = 0
                    cache[index].setTag_bit(tag);
                    cache[index].setValid_bit();
                }
                
                // read next line of the file
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}