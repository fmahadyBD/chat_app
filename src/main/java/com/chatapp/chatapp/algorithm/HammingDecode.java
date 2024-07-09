package com.chatapp.chatapp.algorithm;

import org.springframework.stereotype.Component;

@Component
public class HammingDecode {

    public static int calculateParity(int[] indices, String s) {
        int count = 0;
        for (int index : indices) {
            if (s.charAt(index) == '1') {
                count++;
            }
        }
        // Even parity: return 0 if the count of 1's is even, otherwise 1
        return (count % 2 == 0) ? 0 : 1;
    }

    public static String hdecoding(String s) {
        int[] d0 = {0, 2, 4, 6, 8, 10};    // Positions checked by parity bit P1
        int[] d1 = {1, 2, 5, 6, 9, 10};    // Positions checked by parity bit P2
        int[] d2 = {3, 4, 5, 6, 11};       // Positions checked by parity bit P3
        int[] d3 = {7, 8, 9, 10, 11};      // Positions checked by parity bit P4

        // Calculate parity bits
        int x0 = calculateParity(d0, s);
        int x1 = calculateParity(d1, s);
        int x2 = calculateParity(d2, s);
        int x3 = calculateParity(d3, s);

        // Determine error bit position
        String exx = "" + x3 + x2 + x1 + x0; // Correct order for binary representation
        int errorBit = Integer.parseInt(exx, 2);

        if (errorBit == 0) {
            System.out.println("No error detected.");
        } else {
            System.out.println("Error in bit position: " + errorBit);
            StringBuilder sb = new StringBuilder(s);
            char lol = sb.charAt(errorBit - 1); // Correct method is charAt()
            if (lol == '1') {
                sb.setCharAt(errorBit - 1, '0'); // Use errorBit directly instead of errorBit-1
            } else {
                sb.setCharAt(errorBit - 1, '1'); // Use errorBit directly instead of errorBit-1
            }
            s = sb.toString();
            System.out.println("Corrected message: " + s);
        }

        return s;
    }

    public static String hToNormal(String s) {
        StringBuilder decodeText = new StringBuilder();

        // Loop through the string and skip indices 0, 1, 3, and 7
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 || i == 1 || i == 3 || i == 7) {
               
            } else {
                decodeText.append(s.charAt(i));
            }
        }

        // Print the decoded text
        System.out.println("Decoded text: " + binaryToString(decodeText.toString()));
        return binaryToString(decodeText.toString());
    }

    // Binary to text
    public static String binaryToString(String binary) {
        String[] binaryBlocks = binary.split(" ");
        StringBuilder outputString = new StringBuilder();
        for (String binaryBlock : binaryBlocks) {
            int charCode = Integer.parseInt(binaryBlock, 2);
            outputString.append((char) charCode);
        }
        return outputString.toString();
    }

    public String hdecode(String s) {
        System.out.println("The Encoded test: " + s);
        String fixedBit = hdecoding(s);
        System.out.println("Final decode: ");
        String ans = hToNormal(fixedBit);
        return ans;
    }
}
