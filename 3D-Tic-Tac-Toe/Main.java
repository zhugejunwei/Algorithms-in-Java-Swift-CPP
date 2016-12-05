package io.kongming;

import com.sun.tools.javac.comp.Check;

import java.util.LinkedList;
import java.util.concurrent.SynchronousQueue;

public class Main {

    public static void main(String[] args) {

        /** Problem 1 */

        // Part A
        /*
        String testCase1 = "3,20,9,null,null,1,5,2,4,null,null,15";
        Deserialize deserialize = new Deserialize();
        CreateMirror create = new CreateMirror();

        long startTime = System.currentTimeMillis();
        TreeNode root1 = deserialize.deserialize(testCase1);
        String mirror = create.createMirror(root1);
        System.out.println(mirror);
        long endTime = System.currentTimeMillis();
        long runningTime = endTime - startTime;
        System.out.println(runningTime);
        */


        // Part B
        /*
        CheckMirror check = new CheckMirror();
        String tree1 = "3,20,9,null,null,1,5,2,4,null,null,15";
        String tree2 = "3,9,20,5,1,null,null,null,null,4,2,null,15";
        if (check.checkMirror(tree1, tree2)) System.out.println("Yes, mirror images");
        else System.out.println("Not mirror image");
        */


        /** Problem 2 */

        /** The entry Tic Tac Toe game main() method */
        new GameMain();

    }
}
