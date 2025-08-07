/* LC #26: Probelm Statement
 * -------------------------
 * Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element appears only once. 
 * The relative order of the elements should be kept the same. Then return the number of unique elements in nums.
 * 
 * Consider the number of unique elements of nums to be k, to get accepted, you need to do the following things:
 * 
 * Change the array nums such that the first k elements of nums contain the unique elements in the order they were present in nums initially. 
 * The remaining elements of nums are not important as well as the size of nums.
 * 
 * Return k.
 * --------------------------------------------------------------------------------------------------------------------------------------------
 * 
 * Example:
 * --------
 * Input: nums = [0,0,1,1,1,2,2,3,3,4]
 * Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * --------------------------------------------------------------------------------------------------------------------------------------------
 * 
 * Constraints:
 * ------------
 * 1 <= nums.length <= 3 * 104
 * -100 <= nums[i] <= 100
 * nums is sorted in non-decreasing order.
 */


package apps;

import java.util.Arrays;

public class RmDupes {
    public static int removeDuplicates(int[] nums) {
        int prevElemIndex = 0, duplicateCount = 0, i = 1, j;
        while(i<nums.length) {
            // System.out.println("prevElemIndex= " + prevElemIndex + " Current Index= " + i + " prevVal= " + nums[prevElemIndex] + " Current Val= " + nums[i]);

            if(nums[prevElemIndex] != -101 && nums[i] != -101) {
                if(nums[prevElemIndex] == nums[i]) {
                    // System.out.println("Current Duplicate Number That Will Be Removed= " + nums[i] + " At The Index= " + i);

                    // if duplicate encountered, remove the duplicate index [left shift from index i+1]
                    for(j=i; j<nums.length; j++) {
                        if(j == nums.length-1) {
                            nums[j] = -101; // Using -101 as a sentinel value as the constraint list says the values are between -100 and 100
                        } else {
                            nums[j] = nums[j+1];
                        }
                    }

                    // Increase duplicate count
                    duplicateCount++;

                    // System.out.println("Current Duplicate Count= " + duplicateCount + " and Current Array= " + Arrays.toString(nums) + "\n");
                } else {
                    prevElemIndex++; // increase prevElemIndex
                    i++; // Increase parse counter
                }
            } else { // If we're at the end of the array with null values
                break;
            }
        }

        System.out.println("No Dupes Array" + Arrays.toString(nums));

        return nums.length - duplicateCount;
    }

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int k = removeDuplicates(nums);
        System.out.println("Array length after removing duplicates: " + k);
    }
}