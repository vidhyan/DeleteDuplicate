package main.sample;

import java.util.*;

/**
 * Remove duplicate from input int array.
 */
public class DeDup {

    public static int[] randomIntegers = {1,2,34,34,25,1,45,3,26,85,4,34,86,25,43,2,1,10000,11,16,
            19,1,18,4,9,3,20,17,8,15,6,2,5,10,14,12,13,7,8,9,1,2,15,12,18,10,14,20,17,16,3,6,19,13,
            5,11,4,7,19,16,5,9,12,3,20,7,15,17,10,6,1,8,18,4,14,13,2,11};

    public static void main(String[] args){
        //input
        System.out.println("Input: " + Arrays.toString(randomIntegers));

        // retain order, removes duplicate, replace 0 in duplicate element, inefficient memory handling for large data set
        System.out.println("Output Using List Indicator: " + Arrays.toString(removeDupUseTempArray(randomIntegers)));

        //sort, removes duplicate. doesn't retain order. O(n log(n)) on large data set
        System.out.println("Sort and remove duplicate: " + Arrays.toString(removeDupUseSort(randomIntegers)));

        //use HashSet to remove duplicate. O(n)
        System.out.println("Using Set: " + Arrays.toString(removeDupUseSet(randomIntegers)));
    }

    /**
     * Replace duplicate primitive int with 0 and persist input order using O(n).
     *
     * @param input
     * @return int[]
     */
    private static int[] removeDupUseTempArray(int[] input){
        int[] output = input;
        List<Integer> temp = new ArrayList<Integer>();

        for(int i=0; i< output.length; i++){
            if(temp.contains(output[i])){
                output[i]=0;
            }else{
                temp.add(output[i]);
            }
        }
        return output;
    }

    /**
     * Dual Pivot Quicksort using Arrays sort O(n log(n)) on large data set and remove duplicates. Does not retain original order.
     *
     * @param input
     * @return int[]
     */
    private static int[] removeDupUseSort(int[] input){

        List<Integer> temp = new ArrayList<Integer>();

        Arrays.sort(input);
        int currentElement = input[0];
        boolean isDuplicate = false;

        temp.add(currentElement);
        for(int i=0; i< input.length; i++){
            if(currentElement == input[i] && !isDuplicate){
                isDuplicate = true;
                //input[i] = 0;
            }else if(currentElement != input[i]){
                currentElement = input[i];
                temp.add(currentElement);
                isDuplicate = false;
            }
        }

       int[] output = temp.stream().mapToInt(Integer::intValue).toArray();

        return output;
    }

    /**
     * Use Set to remove duplicate from primitive input array. O(n). Original order will not be retained.
     *
     * @param input
     * @return int[]
     */
    private static int[] removeDupUseSet(int[] input){
        Set<Integer> temp = new HashSet<Integer>();

        for(int i=0 ; i < input.length; i++){
            temp.add(input[i]);
        }

        int[] output = temp.stream().mapToInt(i->i).toArray();

        return output;
    }

}
