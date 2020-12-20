public class Set{
    private Block[] set = new Block[4]; // as we've a 4 way set i.e. each set has 4 blocks
    private int setSize = 0; // stores the number of inputs the set contains

    public Set(){
        int i;
        for(i = 0; i < 4; i++){
            set[i] = new Block(); // initializing the set
        }
    }

    // 1. updates the "lastUsed" attribute of each block.
    // 2. returns true if the set contains a block whose tag-bit matches the given tag
    public boolean getDataFromSet(String tag){
        int i;
        boolean flag = false;
        for(i = 0; i < setSize; i++){
            if(set[i].getTag_bit().equals(tag)){
                set[i].setLastUsed();
                flag = true;
            } else {
                set[i].updateLastUsed();
            }
        }

        return flag;
    }

    // 1. appends the block with given tag, to the set if the set is NOT full
    // 2. replaces the least recently used block with the block(with given tag),
    //    if the set is full
    public void addDataToSet(String tag){
        if(setSize < 4){ // if the set is NOT full

            // "setting" these attributes is analogous to appending a new block 
            set[setSize].setTag_bit(tag);
            set[setSize].setValid_bit(); 
            set[setSize].setLastUsed();
            setSize += 1;

        // if the set is full
        } else {
            int i, j = 0;
            
            // finding the "lru" block's index in the set 
            for(i = 0; i < 4; i++){
                if(set[i].getLastUsed() > set[j].getLastUsed()){
                    j = i;
                }
            }
            
            // replacing "lru" block with th egiven block
            set[j].setTag_bit(tag);
            set[j].setLastUsed();
        }
    }
}