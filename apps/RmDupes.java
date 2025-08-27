/** 
 * LC #26: Problem Statement
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
 * Explanation: Your method should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * --------------------------------------------------------------------------------------------------------------------------------------------
 * 
 * Constraints:
 * ------------
 * 1 <= nums.length <= 3 * 104
 * -100 <= nums[i] <= 100
 * nums is sorted in non-decreasing order.
 */


// package apps;

import java.util.Arrays;

public class RmDupes {
    /**
     * This method removes the duplicate integers from the input array
     * @param numbersArr The input array which has sorted integer values in a non decreasing order.
     * @return The length of the array which contains only unique elements i.e. after removal of the duplicate values.
     */
    public static int removeDuplicates(int[] numbersArr) {
        Integer[] nums = Arrays.stream(numbersArr)          // Convert int array to an IntStream
                               .boxed()                     // Convert each int to an Integer object
                               .toArray(Integer[]::new);    // Collect elements into a new Integer array

        int prevElemIndex = 0, duplicateCount = 0, currentElemIndex = 1;
        while(currentElemIndex < nums.length) {
            // System.out.println("prevElemIndex= " + prevElemIndex + " Current Index= " + currentElemIndex + " prevVal= " + nums[prevElemIndex] + " Current Val= " + nums[currentElemIndex]);

            if(nums[prevElemIndex] != null && nums[currentElemIndex] != null) {
                if(nums[prevElemIndex] == nums[currentElemIndex]) {
                    // if duplicate encountered, remove the duplicate index [left shift from index currentElemIndex+1]
                    shiftElements(nums, prevElemIndex);

                    // Increase duplicate count
                    duplicateCount++;
                    // System.out.println("Current Duplicate Count= " + duplicateCount + " and Current Array= " + Arrays.toString(nums) + "\n");
                } else {
                    prevElemIndex++; // increase prevElemIndex
                    currentElemIndex++; // Increase parse counter
                }
            } else { // If we're at the end of the array with null values
                break;
            }
        }

        System.out.println("Input Array = " + Arrays.toString(numbersArr));
        System.out.println("No Dupes Array = " + Arrays.toString(nums));
        System.out.println("No Dupes Array Length = " + (nums.length - duplicateCount));

        return nums.length - duplicateCount;
    }

    /**
     * This method shifts elements of the array 1 index towards left
     * @param nums The array which values need to be shifted 1 index left.
     */
    private static void shiftElements(Integer[] nums, int prevElemIndex) {
        for(int j = prevElemIndex; j < nums.length; j++) {
            if(nums[j] != null) {
                if(j == nums.length - 1) {
                    nums[j] = null;
                } else {
                    nums[j] = nums[j + 1];
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] numbersArr = {-8,-8,-5,-5,-5,-4,-4,-1,-1,-1,-1,0,0,1,1,1,2,2,3,3,4};
        int k = removeDuplicates(numbersArr);
        System.out.println("Array length after removing duplicates: " + k);
    }
}