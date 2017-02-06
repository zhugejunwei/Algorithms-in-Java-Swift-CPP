
public static int getLCM(int a, int b) {
    return a*b/getGCD(a, b);
}

public static int getGCD(int a, int b) {
    if (a == b) return a;
    if (a > b) return getGCD(a - b, b);
    else return getGCD(a, b - a);
}

