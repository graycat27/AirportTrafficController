package com.github.graycat27.atc.components;

/** 通話表（英数字を、単語で表現する無線向け対照表） */
public class PhoneticCode {

    /** 1文字を変換して返します */
    public static String getPhoneticCode(char c){
        if(c == 'A' || c == 'a' || c == 'Ａ' || c == 'ａ') return Alphabets.A;
        if(c == 'B' || c == 'b' || c == 'Ｂ' || c == 'ｂ') return Alphabets.B;
        if(c == 'C' || c == 'c' || c == 'Ｃ' || c == 'ｃ') return Alphabets.C;
        if(c == 'D' || c == 'd' || c == 'Ｄ' || c == 'ｄ') return Alphabets.D;
        if(c == 'E' || c == 'e' || c == 'Ｅ' || c == 'ｅ') return Alphabets.E;
        if(c == 'F' || c == 'f' || c == 'Ｆ' || c == 'ｆ') return Alphabets.F;
        if(c == 'G' || c == 'g' || c == 'Ｇ' || c == 'ｇ') return Alphabets.G;
        if(c == 'H' || c == 'h' || c == 'Ｈ' || c == 'ｈ') return Alphabets.H;
        if(c == 'I' || c == 'i' || c == 'Ｉ' || c == 'ｉ') return Alphabets.I;
        if(c == 'J' || c == 'j' || c == 'Ｊ' || c == 'ｊ') return Alphabets.J;
        if(c == 'K' || c == 'k' || c == 'Ｋ' || c == 'ｋ') return Alphabets.K;
        if(c == 'L' || c == 'l' || c == 'Ｌ' || c == 'ｌ') return Alphabets.L;
        if(c == 'M' || c == 'm' || c == 'Ｍ' || c == 'ｍ') return Alphabets.M;
        if(c == 'N' || c == 'n' || c == 'Ｎ' || c == 'ｎ') return Alphabets.N;
        if(c == 'O' || c == 'o' || c == 'Ｏ' || c == 'ｏ') return Alphabets.O;
        if(c == 'P' || c == 'p' || c == 'Ｐ' || c == 'ｐ') return Alphabets.P;
        if(c == 'Q' || c == 'q' || c == 'Ｑ' || c == 'ｑ') return Alphabets.Q;
        if(c == 'R' || c == 'r' || c == 'Ｒ' || c == 'ｒ') return Alphabets.R;
        if(c == 'S' || c == 's' || c == 'Ｓ' || c == 'ｓ') return Alphabets.S;
        if(c == 'T' || c == 't' || c == 'Ｔ' || c == 'ｔ') return Alphabets.T;
        if(c == 'U' || c == 'u' || c == 'Ｕ' || c == 'ｕ') return Alphabets.U;
        if(c == 'V' || c == 'v' || c == 'Ｖ' || c == 'ｖ') return Alphabets.V;
        if(c == 'W' || c == 'w' || c == 'Ｗ' || c == 'ｗ') return Alphabets.W;
        if(c == 'X' || c == 'x' || c == 'Ｘ' || c == 'ｘ') return Alphabets.X;
        if(c == 'Y' || c == 'y' || c == 'Ｙ' || c == 'ｙ') return Alphabets.Y;
        if(c == 'Z' || c == 'z' || c == 'Ｚ' || c == 'ｚ') return Alphabets.Z;


        if(c == '０' || c == '0') return Numbers.ZERO0;
        if(c == '１' || c == '1') return Numbers.ONE1;
        if(c == '２' || c == '2') return Numbers.TWO2;
        if(c == '３' || c == '3') return Numbers.THREE3;
        if(c == '４' || c == '4') return Numbers.FOUR4;
        if(c == '５' || c == '5') return Numbers.FIVE5;
        if(c == '６' || c == '6') return Numbers.SIX6;
        if(c == '７' || c == '7') return Numbers.SEVEN7;
        if(c == '８' || c == '8') return Numbers.EIGHT8;
        if(c == '９' || c == '9') return Numbers.NINE9;

        return String.valueOf(c);
    }

    /** 文章を変換して返します。文末にスペースは付きません */
    public static String getPhoneticCode(CharSequence seq){
        if(seq == null){
            return "";
        }
        int len = seq.length();
        StringBuilder res = new StringBuilder();
        for(int i=0; i<len; i++){
            String s = getPhoneticCode(seq.charAt(i));
            res.append(s);
            if(!s.equals(String.valueOf(seq.charAt(i)))
                && i < len-1){
                res.append(" ");
            }
        }
        return res.toString();
    }

    //TODO number(int/double) to phoneticCode

    private class Alphabets{
        private static final String A = "Alpha";
        private static final String B = "Bravo";
        private static final String C = "Charlie";
        private static final String D = "Delta";
        private static final String E = "Echo";
        private static final String F = "Foxtrot";
        private static final String G = "Golf";
        private static final String H = "Hotel";
        private static final String I = "India";
        private static final String J = "Juliet";
        private static final String K = "Kilo";
        private static final String L = "Lima";
        private static final String M = "Mike";
        private static final String N = "November";
        private static final String O = "Oscar";
        private static final String P = "Papa";
        private static final String Q = "Quebec";
        private static final String R = "Romeo";
        private static final String S = "Sierra";
        private static final String T = "Tango";
        private static final String U = "Uniform";
        private static final String V = "Victor";
        private static final String W = "Whiskey";
        private static final String X = "X-Ray";
        private static final String Y = "Yankee";
        private static final String Z = "Zulu";
    }

    private class Numbers{
        private static final String ZERO0 = "Ze-ro";
        private static final String ONE1 = "One";
        private static final String TWO2 = "Two";
        private static final String THREE3 = "Tree";
        private static final String FOUR4 = "Four";
        private static final String FIVE5 = "Fife";
        private static final String SIX6 = "Six";
        private static final String SEVEN7 = "Seven";
        private static final String EIGHT8 = "Eight";
        private static final String NINE9 = "Niner";

        private static final String HUNDRED00 = "Hundred";
        private static final String THOUSAND000 = "Tou-sand";

        private static final String DECIMAL = "Decimal";
    }
}
