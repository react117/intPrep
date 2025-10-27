import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

class TopFrequentElement {
    /**
     * This method traverse through the input array and finds the k most frequent elements.
     * It builds a frequency hashmap of elements and then uses a max-heap to arrange the top k frequent elements.
     * @param nums The input array which has integer values.
     * @param k The number of top frequent elements to return.
     * @return An array of k most frequent elements.
     */
    public static int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> elemFreq = new HashMap<>();
        int[] topFreqElem = new int[k];

        for(int i = 0; i < nums.length; i++) {
            elemFreq.put(nums[i], elemFreq.getOrDefault(nums[i], 0) + 1);
        }

        // System.out.println(elemFreq);

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> elemFreq.get(b) - elemFreq.get(a));
        maxHeap.addAll(elemFreq.keySet());

        if(!maxHeap.isEmpty()) {
            for(int i = 0; i < k; i++) {
                topFreqElem[i] = maxHeap.remove();
            }
        }

        System.out.println(Arrays.toString(topFreqElem));

        return topFreqElem;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,1,2,2,3,4,4,4,4};
        int k = 2;

        topKFrequent(nums, k);
    }
}