package io.kongming;

public class Main {
    public static void main(String[] args) {
        SpreadSheet sheet = new SpreadSheet();
        Cell c1 = sheet.setValue(1, 1, "123");
        Cell c2 = sheet.setValue(2, 2, "321");
        Cell c3 = sheet.setValue(3, 3, c1, c2, '+');
        sheet.removeRow(3);
        
        System.out.println(sheet.getValue(3,3));
    }
}
