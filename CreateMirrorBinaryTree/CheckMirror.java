package io.kongming;

/**
 * Created by zhugejunwei on 12/4/16.
 */
public class CheckMirror {
    public boolean checkMirror(String tree1, String tree2) {
        Deserialize deserialize = new Deserialize();
        TreeNode root1 = deserialize.deserialize(tree1);
        TreeNode root2 = deserialize.deserialize(tree2);

        Serialize serial = new Serialize();
        String origin = serial.serialize(root1);

        CreateMirror createMirror = new CreateMirror();
        String mirror = createMirror.createMirror(root2);
        System.out.println(origin);
        System.out.println(mirror);
        return origin.equals(mirror);
    }
}