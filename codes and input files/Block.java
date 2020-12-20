public class Block {

    private boolean valid_bit;
    private String tag_bit;

    private int lastUsed; // this attribute is analogous to how many time units ago
                          // the block was accessed. For most recently accessed block,
                          // lastUsed = 1. For 2nd most recently accessed block, lastUsed = 2 and so on.
                          // We've used this attribute only for the implementation of set 
                          // associative cache (to implement "lru" principle)

    public Block(){
        tag_bit = "-1";
        valid_bit = false;
        lastUsed = -1;
    }

    public String getTag_bit() {
        return tag_bit;
    }

    public int getLastUsed() {
        return lastUsed;
    }

    public void updateLastUsed(){
        lastUsed += 1;
    }


    // "lastUsed" attribute is 'set'(to 1) only for 
    // most recently used block, otherwise it is updated(incremented by 1)
    public void setLastUsed() {
        this.lastUsed = 1;
    }

    public void setTag_bit(String tag_bit) {
        this.tag_bit = tag_bit;
    }

    public void setValid_bit() {
        this.valid_bit = true;
    }

    public boolean getValid_bit(){
        return valid_bit;
    }
}