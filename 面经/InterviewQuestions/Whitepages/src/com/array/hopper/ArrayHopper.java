package com.array.hopper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ArrayHopper {
    private final ArrayList<Integer> array = new ArrayList<Integer>();;

    public boolean loadData(String file) {
        BufferedReader br = null;
        try {
            String currentLine = "";
            br = new BufferedReader(new FileReader(file));
            while ((currentLine = br.readLine()) != null) {
                array.add(Integer.valueOf(currentLine));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public ArrayList<Integer> findMinHops() {
        int n = array.size();
        ArrayList<Integer> hop = new ArrayList<Integer>();
        hop.add(0);
        for (int i = 0; i + array.get(i) <= n - 1;) {
            i = findMax(i, i + array.get(i));
            if (i == -1) {
                ArrayList<Integer> empty = new ArrayList<Integer>();
                return empty;
            }
            hop.add(i);
        }
        return hop;
    }

    public int findMax(int current, int limit) {
        int maxPos = current, maxVal = 0;
        for (int i = current; i <= limit; ++i) {
            if (array.get(i) > maxVal) {
                maxPos = i;
                maxVal = array.get(i);
            }
            maxVal--;
        }

        if (maxPos == current) {
            return -1;
        }
        return maxPos;
    }

    public static void main(String args[]) {
        if (args.length != 1) {
            System.out.println("Usage: java ArrayHopper <file location>");
        } else {
            ArrayHopper ah = new ArrayHopper();
            ArrayList<Integer> hop = new ArrayList<Integer>();
            if (!ah.loadData(args[0])) {
                System.out.println("File reading error.");
            } else {
                hop = ah.findMinHops();
                if (hop.isEmpty()) {
                    System.out.println("failure");
                } else {
                    for (int i = 0; i < hop.size(); ++i) {
                        System.out.print(hop.get(i) + ", ");
                    }
                    System.out.println("out");
                }
            }
        }
    }
}