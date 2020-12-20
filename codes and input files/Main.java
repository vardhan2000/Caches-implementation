public class Main {
    
    public static void main(String[] args){
        // array of the names of files to be read 
        String[] files = {"gcc.trace", "gzip.trace", "mcf.trace", "swim.trace", "twolf.trace"};
        double cache1_hitRate, cache2_hitRate, cache1_missRate, cache2_missRate, cache1_hit_to_miss, cache2_hit_to_miss;
        
        // printing the output
        for(int i = 0; i < 5; i++){
            DirectMappedCache cache1 = new DirectMappedCache();
            cache1.compute_hits_and_misses(files[i]);
            System.out.println((i+1) + ". For " + files[i]);
            System.out.println("Number of accesses = " + cache1.getAccesses() + "\n");
            System.out.println("Direct mapped");
            System.out.println("Number of hits = " + cache1.getHits());
            System.out.println("Number of misses = " + cache1.getMisses());
            cache1_hitRate = (double)cache1.getHits() / cache1.getAccesses();
            System.out.println("Hit rate = " + cache1_hitRate);
            cache1_missRate = (double)cache1.getMisses() / cache1.getAccesses();
            System.out.println("Miss rate = " + cache1_missRate);
            cache1_hit_to_miss = (double)cache1.getHits() / cache1.getMisses();
            System.out.println("Hit to miss ratio = " + cache1_hit_to_miss + "\n");
            

            SetAssociativeCache cache2 = new SetAssociativeCache();
            cache2.compute_hits_and_misses(files[i]);
            System.out.println("Set associative");
            System.out.println("Number of hits = " + cache2.getHits());
            System.out.println("Number of misses = " + cache2.getMisses());
            cache2_hitRate = (double)cache2.getHits() / cache2.getAccesses();
            System.out.println("Hit rate = " + cache2_hitRate);
            cache2_missRate = (double)cache2.getMisses() / cache2.getAccesses();
            System.out.println("Miss rate = " + cache2_missRate);
            cache2_hit_to_miss = (double)cache2.getHits() / cache2.getMisses();
            System.out.println("Hit to miss ratio = " + cache2_hit_to_miss + "\n");
        }
    }
}