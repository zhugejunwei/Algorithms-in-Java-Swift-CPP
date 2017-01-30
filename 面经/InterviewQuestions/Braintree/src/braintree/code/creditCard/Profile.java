package braintree.code.creditCard;

class Profile {
    private final String name;		//Client name
    private int balance;			//Client balance    
    private final int limit;		//Client account saving amount limit
    private final String cardNum;	//Client card number
    private boolean valid;			//Client card number is valid

    public Profile(String name, int limit, String cardNum) {
        this.name = name;
        this.limit = limit;
        this.cardNum = cardNum;
        balance = 0;
        if (luhn10(cardNum)) {
            valid = true;
        } else {
            valid = false;
        }
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }

    public boolean valid() {
        return valid;
    }

    public String getCardNum() {
        return cardNum;
    }

    //Testing purpose only
    public int getLimit() {
        return limit;
    }

    public void addBalance(int add) {
    	//Guard off saving money to more than limit and to invalid card
        if (balance + add <= limit && valid) {
            balance += add;
        }
    }

    public void deductBalance(int deduct) {
    	//Guard off getting money from invalid card
        if (valid) {
            balance -= deduct;
        }
    }

    //Module 10 card number checking
    private boolean luhn10(String card) {
    	//Check if card number only contains number and the length is smaller than 20
        if (card.matches("[0-9]{1,19}")) {
            char[] cardChar = card.toCharArray();
            int size = cardChar.length - 1;
            int sum = 0;
            //Iterating through number to calculate mod 10
            for (int index = size; index >= 0; index--) {
                if ((size - index) % 2 != 0) {
                    int twice = 2 * Integer.valueOf(String
                            .valueOf(cardChar[index]));
                    if (twice >= 10) {
                        char[] digitSum = String.valueOf(twice).toCharArray();
                        twice = 0;
                        for (char digit : digitSum) {
                            twice += Integer.valueOf(String.valueOf(digit));
                        }
                    }
                    sum += twice;
                } else {
                    sum += Integer.valueOf(String.valueOf(cardChar[index]));
                }
            }

            if (sum % 10 == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        Profile p = new Profile("Lisa", 3000, "5454545454545454");
        System.out.println(p.luhn10("5454545454545454"));
    }
}