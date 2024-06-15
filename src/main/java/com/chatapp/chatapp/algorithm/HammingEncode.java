package com.chatapp.chatapp.algorithm;

import org.springframework.stereotype.Component;

@Component
public class HammingEncode {



    // Convert a string to binary representation
    public static String stringToBinary(String input) {
        StringBuilder binaryString = new StringBuilder();
        for (char c : input.toCharArray()) {
            String binaryChar = String.format("%8s", Integer.toBinaryString(c)).replaceAll(" ", "0");
            binaryString.append(binaryChar).append(" ");
        }
        return binaryString.toString().trim();
    }





    // Calculate the parity bit for given indices
    public static char calculateParity(int[] indices, String s) {
        int count = 0;
        for (int index : indices) {
            if (s.charAt(index-1) == '1') count++;
        }
        return (count % 2 == 0) ? '0' : '1';
    }




    // Encode a binary string using Hamming code
    public static String hammingEncode(String s) {
        int[] p1 = {2,5,7,9,11};
        int[] p2 = {3,6,7,10,11};
        int[] p3 = {5,6,7,12};
        int[] p4 = {9,10,11,12};

        StringBuilder encoded = new StringBuilder("000000000000");

        // Place data bits in their positions
        int dataIndex = 0;
        for (int i = 0; i < 12; i++) {
            if (i != 0 && i != 1 && i != 3 && i != 7) {
                encoded.setCharAt(i, s.charAt(dataIndex));
                dataIndex++;
            }
        }

        // Calculate and place parity bits
        encoded.setCharAt(0, calculateParity(p1, encoded.toString()));
        encoded.setCharAt(1, calculateParity(p2, encoded.toString()));
        encoded.setCharAt(3, calculateParity(p3, encoded.toString()));
        encoded.setCharAt(7, calculateParity(p4, encoded.toString()));

        return encoded.toString();
    }




    // start form here
    public String hamingEcode(String inputString ) {
       

        // Convert string to binary
        String binaryString = stringToBinary(inputString);
        // System.out.println("Binary representation: " + binaryString);

        // Encode each character using Hamming code
        StringBuilder encodedString = new StringBuilder();
        for (String binaryChar : binaryString.split(" ")) {
            encodedString.append(hammingEncode(binaryChar)).append(" ");
        }

        // Output the encoded string
        String ans=encodedString.toString().trim();
        // System.out.println("Hamming encoded binary representation: " + ans);
        return ans;
    }

}
