package com.example.lrb13.cipher;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Spinner;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;


public class MainActivity extends Activity {

    Spinner functionList;
    Spinner oddNumberList;
    TextView output;
    String nv;
    EditText in;
    EditText n;
    int size;
    String caesarIndex;
    int p2;
    int position;
    int i = 0;
    String input;
    String result = "";
    int length;
    int p;
    String strn;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        output = (TextView) findViewById(R.id.textView);
        n = (EditText) findViewById(R.id.nValue);
        in = (EditText) findViewById(R.id.inputCipher);

        functionList = (Spinner) findViewById(R.id.spinner);
        oddNumberList = (Spinner) findViewById(R.id.oddNumbers);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.split_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.odd_numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        functionList.setAdapter(adapter);
        oddNumberList.setAdapter(adapter2);
        functionList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                p = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // sometimes you need nothing here
            }


        });

        oddNumberList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int[] d = {1,3,5,7,9,11,15,17,19,21,23,25};
                p2 = d[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // sometimes you need nothing here
            }
        });



        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void RunOnClick(View v) {
        result = "";
        input = in.getText().toString();
        strn = n.getText().toString();
        length = input.length();
        output.setText("");
        if (p == 0) { //ngraph
            output.setText(nGraph());

        } else if (p == 1) {//run the alphabet
            output.setText(runTheAlphabet());
        }
        else if (p==2) { //index of coincidence
            output.setText(indexOfCoincidence());

        }
        else if (p==3)
        { //caesar
            output.setText(caesar());
           // caesar();
        }
        else if (p==4)
        { //frequency
            output.setText(frequency());
            //frequency();


        }
        else if (p==5)  //todo use this as a precedence for the rest of the code
        { //multiplicative
            output.setText(multiplicative());
        }

        else if (p==6)
        {
           output.setText(affine());
        }

        else if (p==7)
        {
            vigenere();
        }

        else if (p==8)
        {
            RSAKeyGeneration();
        }

        else if (p==9)
        {
            present();
        }
    }


    public String nGraph() {
        String result = "";
        size = Integer.parseInt(strn);;
        if(size<1){ //size is the result of text field i need to create (n)
            size=1;
        }//end if

        input =input.replaceAll(" ","");
        input=input.toUpperCase();
        input = input.replaceAll("[^A-Za-z]+","");
        if(size<=input.length()){
            ArrayList<String> array = new ArrayList<String>();
            for(int i=0; i<input.length()-size+1;i++){
                String letters = input.substring(i,i+size);
                array.add(i,letters);
            }//end for loop

            ArrayList<String> listed = new ArrayList<String>();

            for(int j=0;j<array.size();j++){
                String l=array.get(j);
                if(listed.contains(l)==false){
                    listed.add(l);
                    if(array.lastIndexOf(l)==j){
                        result = result + l + " = 1 at positions " + j + "\n";
                    }//end if

                    else{
                        ArrayList<Integer> position = new ArrayList<Integer>();
                        int count =0;
                        for(int k=0;k<array.size();k++){
                            if(array.get(k).equals(l)){
                                count++;
                                position.add(k);
                            }//end if
                        }//end for
                        result = result + l + " = " + count + " at positions " + position + "\n";
                    }//end else
                }//end if

            }
            //end for loop
        }//end if
        return result;
    }

    public String runTheAlphabet() {
        String result = "";
        input = input.toLowerCase();
        for (int j = 0; j <= 25; j++) {
            for (int i = 0; i < input.length(); i++) {
                int charInt = (int)input.charAt(i);
                if ((charInt >= 97 && charInt <= 122)) {
                    if ((charInt + j) > 122) {
                        int tempIndex = charInt + j - 123;
                        result = result + ((char)(97 + tempIndex));
                    }
                    else
                        result = result + ((char)(charInt + j));
                    }
                else {
                    result = result + (' ');
                }
            }
            result = result + "\n";
            }
        return result;
    }

    public String indexOfCoincidence() {
        result = "";
        //create array to store count in a=0,b=1...
        int array[]=new int[26];

        //take string and convert to lowercase and remove spaces
        input=input.replaceAll(" ","");
        input=input.toLowerCase();
        input=input.replaceAll("[^A-Za-z]+","");
        if(input.length()<=1){
            result = "NA";
        }//end if

        else{

            //look through each letter in string and update count in the correct spot
            for(int i=0;i<input.length();i++){
                char letter=input.charAt(i);
                int position= letter-'a';
                array[position]=array[position]+1;
            }//end for loop

            //loop through and get the numerator value
            int numerator=0;
            for(int i=0;i<array.length;i++){
                numerator=numerator+(array[i]*(array[i]-1));
            }//end for loop

            //create denominator value
            int denominator=input.length()*(input.length()-1);

            //convert integers to doubles and get Index
            double num=(double) numerator;
            double den=(double) denominator;
            double index = num/den;
            result = ("" + index);
        }//end else
        return result;
    }

    public String caesar() {
        String result = "";
        caesarIndex = strn;
        size = getIndex(caesarIndex);
        input = input.toLowerCase();
        for (int i = 0; i < input.length(); i++) {
            int charInt = (int)input.charAt(i);
            if ((charInt >= 97 && charInt <= 122)) {
                if ((charInt + size) > 122) {
                    int tempIndex = charInt + size - 123;
                    result = result + ((char)(97 + tempIndex));
                }
                else
                    result = result + ((char)(charInt + size));
            }
            else {
                result = result + " ";
            }
        }
        return result;
    }

    public String frequency() {
        String result = "";
        //remove spaces and special characters from string
        input=input.replaceAll(" ","");
        input=input.toLowerCase();
        input=input.replaceAll("[^A-Za-z]+","");

        int array[]=new int[26];
        //look through each letter in string and update count in the correct spot
        //index 0 in array is a and index 25 is z
        for(int i=0;i<input.length();i++){
            char letter=input.charAt(i);
            int position= letter-'a';
            array[position]=array[position]+1;
        }//end for loop

        result="";
        //loop through array and put the results into a single string
        for(int i=0;i<26;i++){

            String l=String.valueOf((char)(65+i));

            String I="";
            for(int j=0;j<array[i];j++){
                I=I.concat("I");
            }//end for
            result=result + l + " = " + array[i] + " = " + I + "\n";
        }//end for
        return result;
    }

    public String multiplicative() {
        String result = "";
        int value = p2;
        int count = 0;
        input=input.replaceAll(" ","");
        input=input.toLowerCase();
        input=input.replaceAll("[^A-Za-z]+","");

        for (int i = 0; i < input.length(); i++) {
            count++;
            int charInt = (int)input.charAt(i);
            if ((charInt >= 97 && charInt <= 122)) {
                charInt = charInt - 96;
                charInt = charInt * value;
                charInt = charInt % 26;
                if (charInt == 0)
                    charInt = 122;
                else
                    charInt += 96;
                result = result + ((char)charInt);
            }
            else {
                result = result + (' ');
            }
            if(count == 5){
                result = result + " ";
                count = 0;
            }
        }
        return result;
        //output.setText(result);
    }

    public String affine()
    {
        String result = "";
        input = input.toLowerCase();
        result = multiplicative2(input, p2);
        result = caesar2(result, length);
        return result;
    }

    public static String caesar2(String input, int index) {
        StringBuilder s2 = new StringBuilder();
        input = input.toLowerCase();

        for (int i = 0; i < input.length(); i++) {
            int charInt = (int)input.charAt(i);
            if ((charInt >= 97 && charInt <= 122)) {
                if ((charInt + index) > 122) {
                    int tempIndex = charInt + index - 123;
                    s2.append((char)(97 + tempIndex));
                }
                else
                    s2.append((char)(charInt + index));
            }
            else {
                s2.append(' ');
            }
        }
        return s2.toString();
    }

    public String multiplicative2(String input, int index) {
        StringBuilder result2 = new StringBuilder();
        input = input.toLowerCase();

        for (int i = 0; i < input.length(); i++) {
            int charInt = (int)input.charAt(i);
            if ((charInt >= 97 && charInt <= 122)) {
                charInt -= 96;
                charInt *= index;
                charInt %= 26;
                if (charInt == 0)
                    charInt = 122;
                else
                    charInt += 96;
                result2.append((char)charInt);
            }
            else {
                result2.append(' ');
                			}
        }
        return result2.toString();
    }

    public static int getIndex(String input) {
        if (isInteger(input)) {
            int index = Integer.parseInt(input);
            if (index < 0)
                return -1;
            else if (index >= 0 && index <= 25)
                return index;
            else
                return (index % 26);
        }
        else {
            if (input.length() == 1) {
                int charInt = (int)input.charAt(0);
                if (charInt >= 65 && charInt <= 90)
                    return (charInt - 65);
                else if (charInt >= 97 && charInt <= 122)
                    return (charInt - 97);
                else
                    return -1;
            }
            else
                return -1;
        }
    }

    private static boolean isInteger(String str) {
        return str.matches("^-?\\d+$");
    }

    public void vigenere()
    {
        ArrayList<Integer> index = getIndexArray();
        int arrayCounter = 0;
        int shiftIndex = 0;
        input = input.toLowerCase();

        for (int i = 0; i < input.length(); i++) {
            int charInt = (int)input.charAt(i);
            if (charInt >= 97 && charInt <= 122) {
                if (arrayCounter >= index.size()) {
                    shiftIndex = index.get(arrayCounter % index.size());
                }
                else
                    shiftIndex = index.get(arrayCounter);

                if ((charInt + shiftIndex) > 122) {
                    int tempIndex = charInt + shiftIndex - 123;
                    result = result + ((char)(97 + tempIndex));
                }
                else {
                    result = result + ((char)(charInt + shiftIndex));
                }
                arrayCounter++;
            }
            else {
                result = result + (' ');
            }
        }
        output.setText(result);
    }

    public ArrayList<Integer> getIndexArray() {
        String indexInput = n.getText().toString();
        indexInput = indexInput.toLowerCase();
        ArrayList<Integer> indexArray = new ArrayList<>();

        for (int i = 0; i < indexInput.length(); i++) {
            int charInt = (int)indexInput.charAt(i);
            if (charInt >= 97 && charInt <= 122) {
                indexArray.add(charInt - 97);
            }
        }
        return indexArray;
    }

    public void RSAKeyGeneration()
    {
        //define variables that are used in while loop below
        boolean prime1=false;
        boolean prime2=false;
        boolean noPrimeInt=false;
        boolean noPrimeInt2=false;
        long p1=0;
        long p2=0;

        //runs until two primes are generated between 10 mil and 20 mil
        while(prime1==false || prime2== false){
            Random rand = new Random();
            Random rand2 = new Random();

            //once we have prime number stop running this part
            if(prime1==false){
                //generate random number
                p1=rand.nextInt(10000000)+10000000;
                noPrimeInt=false;

                //loop through possible factors to check if prime
                for(int i=2;i<=Math.sqrt(p1)+1;i++){
                    if(p1%i==0){
                        noPrimeInt=true;
                    }//end if
                }//end for

                //change prime boolean if prime number
                if(noPrimeInt==false){
                    prime1=true;
                }//ends if

                else{
                    prime1=false;
                }//ends else
            }//end if

            //once we have prime number 2 stop running this part
            if(prime2==false){
                //generate random number
                p2=rand2.nextInt(10000000)+10000000;
                noPrimeInt2=false;

                //check to see if random number is prime
                for(int i=2;i<=Math.sqrt(p2)+1;i++){
                    if(p2%i==0){
                        noPrimeInt2=true;
                    }//end if
                }//end for

                //change prime boolean if prime number
                if(noPrimeInt2==false){
                    prime2=true;
                }//end if

                else{
                    prime2=false;
                }//end else
            }//end if
        }//end while


        //create modulus and phi
        //these numbers are based on the two prime numbers generated above
        Long mod=p1*p2;
        Long phi =(p1-1)*(p2-1);

        //define variables to be used in while loop below
        //had to convert some numbers we had before from long to BigInteger
        BigInteger phi2=BigInteger.valueOf(phi);
        long encryption=0;
        BigInteger encrypt=BigInteger.valueOf(encryption);
        BigInteger gcd=encrypt;
        BigInteger one=BigInteger.valueOf(Long.valueOf(1));
        Random rand3= new Random();
        boolean relprime=false;
        boolean noPrimeInt3=false;
        boolean prime3=false;

        //gets encryption exponent
        while(relprime==false){
            while(prime3==false){
                encryption=rand3.nextInt(1000000);
                encrypt=BigInteger.valueOf(encryption);
                noPrimeInt3=false;
                for(int i=2;i<=Math.sqrt(encryption);i++){
                    if(encryption%i==0){
                        noPrimeInt3=true;
                    }//end if
                }//end for loop
                if(noPrimeInt3==false){
                    prime3=true;
                }
                else{
                    prime3=false;
                }
            }//end nested while

            gcd=phi2.gcd(encrypt);
            if(gcd.equals(one)){
                relprime=true;
            }//end if

        }//end while


        //get decryption exponent
        long decryption=gcdExtended(phi,encryption);
        if(decryption<0){
            decryption=phi+decryption;
        }
        decryption=decryption%phi;

        String result="";
        result=result+"First large prime "+p1+ "\n";
        result=result+"Second large prime " +p2+"\n";
        result=result+"Modulus " +mod+"\n";
        result=result+"Phi is "+phi2+"\n";
        result=result+"Encryption exponent is " + encryption+"\n";
        result=result+"Decryption exponent is " + decryption+"\n";

        output.setText(result);
    }

    //function to get gcdExtended
    public static long gcdExtended(long a,long b){
        long x=0;
        long y=1;
        long lastx=1;
        long lasty=0;
        long temp;

        while(b!=0){
            long q=a/b;
            long r=a%b;

            a=b;
            b=r;

            temp=x;
            x=lastx-q*x;
            lastx=temp;

            temp=y;
            y=lasty-q*y;
            lasty=temp;
        }
        return lasty;
    }//end gcdExtended

    public void present()
    {
        String usk = input;
        String uP = n.getText().toString();

        if(!usk.matches("[01]+"))
        {
            output.setText("your first string is not a binary string");
        }

        else  if(usk.length()!=20)
        {
            output.setText("your first string is not of length 20");
        }

        else  if(!uP.matches("[01]+"))
        {
            output.setText("your second string is not a binary string");
        }


        else  if(uP.length()!=16)
        {
            output.setText("your second string is notof length 16");
        }

        else {

            //need input string called usk 20 chars only 0 and 1
            //need input string callked uP 16 chars only 0 and 1
            String ct = "";
            String key1 = keyGen(usk, 1);

            String rk1 = key1.substring(0, 16);
            result = result + "Round 1 Key: " + print(rk1) + "\n";
            //need to output rk1 using print function
            //example: print(rk1); + however you print to screen in android
            String key2 = keyGen(key1, 2);
            String rk2 = key2.substring(0, 16);
            result = result + "Round 2 Key: " + print(rk2) + "\n";
            //need to output rk2 using print function

            String key3 = keyGen(key2, 3);
            String rk3 = key3.substring(0, 16);
            result = result + "Round 3 Key: " + print(rk3) + "\n";
            //need to output rk3 using print function
            //each of these steps can be outputed but not necessary
            ct = XOR(uP, rk1);
            ct = SBOX(ct);
            ct = PRM(ct);

            ct = XOR(ct, rk2);
            ct = SBOX(ct);
            ct = PRM(ct);

            ct = XOR(ct, rk3);
            result = result + "Result: " + print(ct);

            output.setText(result);
            //need to output ct using print function
        }
    }

    //----------------------Key Generation function------------------------------//
    public String keyGen(String key, int r){
        String usk = key;
        char[] aK = usk.toCharArray();
        char[] sK = new char[20];
        char[] nK = new char[4];
        char[] aN = new char[20];
        char[] rc1 = {'0', '0' , '0' , '1'};
        char[] rc2 = {'0', '0' , '1' , '0'};
        char[] rc3 = {'0', '0' , '1' , '1'};
        char[] rcF = new char[4];
        int nF = 0;
        String snF = "";
        String sAN = "";

	/*

			S-Box
	Input:  0 1 2 3 4 5 6 7 8 9 A B C D E F
	Output: C 5 6 B 9 0 A D 3 E F 8 4 7 1 2

	How the array is set up:
	sIn = Input of S-Box
	sOut = Output of S-Box
	*/

        String[] sIn = {"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100","1101","1110","1111"};


        String[] sOut = {"1100", "0101", "0110", "1011", "1001", "0000", "1010", "1101", "0011", "1110", "1111", "1000", "0100", "0111", "0001", "0010" };



        //rotate left 17 bits
        System.arraycopy(aK, 17, sK, 0, 3);
        System.arraycopy(aK, 0, sK, 3, 17);




        //get nibble of key
        System.arraycopy(sK, 0, nK, 0, 4);
        String oN = new String(nK);
        //search sIn for nibble array position = the nibble as a int
        for(int i = 0; i < sIn.length; i++){
            if(oN.equals(sIn[i])){
                nF = i;
            }
        }
        //use nF to find Output nibble
        for(int i = 0; i < sIn.length; i++){
            if(i == nF){
                snF = sOut[i];
            }
        }

        //Output nibble string to char array
        char[] snFA = snF.toCharArray();
        //adjust key with nibble
        System.arraycopy(snFA, 0, aN, 0, 4);
        System.arraycopy(sK, 4, aN, 4, 16);

		/*
		if(r == 1){
                    snb1.setText(par(aN));
                }else if(r == 2){
                    snb2.setText(par(aN));
                }else if(r == 3){
                    snb3.setText(par(aN));
                }*/

        //make after nibble adjustment array = string
        sAN = String.valueOf(aN);
        //find bits in after nibble adjustment to be XORed
        char[] aNX = sAN.substring(12,16).toCharArray();
        //XOR r == # of round
        if(r == 1){
            for(int i = 0; i < aNX.length; i++){
                if(aNX[i] == rc1[i]){
                    rcF[i] =  '0';
                }else{
                    rcF[i] = '1';
                }
            }
            //change the actually array
            aN[12] = rcF[0];
            aN[13] = rcF[1];
            aN[14] = rcF[2];
            aN[15] = rcF[3];
        }else if(r == 2){
            for(int i = 0; i < aNX.length; i++){
                if(aNX[i] == rc2[i]){
                    rcF[i] =  '0';
                }else{
                    rcF[i] = '1';
                }
            }
            //change the actually array
            aN[12] = rcF[0];
            aN[13] = rcF[1];
            aN[14] = rcF[2];
            aN[15] = rcF[3];
        }else{
            for(int i = 0; i < aNX.length; i++){
                if(aNX[i] == rc3[i]){
                    rcF[i] =  '0';
                }else{
                    rcF[i] = '1';
                }
            }
            //change the actually array
            aN[12] = rcF[0];
            aN[13] = rcF[1];
            aN[14] = rcF[2];
            aN[15] = rcF[3];
        }
        return String.valueOf(aN);
    }

    //-------------------------XOR function-----------------------------------//
    public static String XOR(String p, String k){
        char[] pA = p.toCharArray();
        char[] kA = k.toCharArray();
        char[] x = new char[16];
        //XOR function
        for(int i = 0; i < pA.length; i++){
            if(pA[i] == kA[i]){
                x[i] = '0';
            }else{
                x[i] = '1';
            }
        }

        return String.valueOf(x);
    }
    //------------------S Box function encryption process--------------------//
    public static String SBOX(String p){
        String[] sIn = {"0000","0001","0010","0011","0100","0101","0110","0111","1000","1001","1010","1011","1100","1101","1110","1111"};


        String[] sOut = {"1100", "0101", "0110", "1011", "1001", "0000", "1010", "1101", "0011", "1110", "1111", "1000", "0100", "0111", "0001", "0010" };
        //take XOR pt and break it into nibbles
        String p0 = p.substring(0,4);
        String p1 = p.substring(4,8);
        String p2 = p.substring(8,12);
        String p3 = p.substring(12,16);

        String s0 = "";
        String s1 = "";
        String s2 = "";
        String s3 = "";
        String sF = "";
        //check sIn for position of nibbles and switch with sOut
        for(int i = 0; i < sIn.length; i++){
            if(p0.equals(sIn[i])){
                s0 = sOut[i];
            }
        }

        for(int i = 0; i < sIn.length; i++){
            if(p1.equals(sIn[i])){
                s1 = sOut[i];
            }
        }

        for(int i = 0; i < sIn.length; i++){
            if(p2.equals(sIn[i])){
                s2 = sOut[i];
            }
        }

        for(int i = 0; i < sIn.length; i++){
            if(p3.equals(sIn[i])){
                s3 = sOut[i];
            }
        }
        sF = s0 + s1 + s2 + s3;
        return sF;
    }
    //--------------------------Permutation function-----------------------//
    public static String PRM(String p){
        char[] sArray = p.toCharArray();


        List<Character> a = new ArrayList<Character>();
        List<Character> b = new ArrayList<Character>();
        for(int i = 0; i < sArray.length; i++){
            a.add(sArray[i]);
        }



        b.add(0, a.get(0));
        b.add(1, a.get(4));
        b.add(2, a.get(8));
        b.add(3, a.get(12));
        b.add(4, a.get(1));
        b.add(5, a.get(5));
        b.add(6, a.get(9));
        b.add(7, a.get(13));
        b.add(8, a.get(2));
        b.add(9, a.get(6));
        b.add(10, a.get(10));
        b.add(11, a.get(14));
        b.add(12, a.get(3));
        b.add(13, a.get(7));
        b.add(14, a.get(11));
        b.add(15, a.get(15));


        StringBuilder bs = new StringBuilder(b.size());
        for(Character ch: b){
            bs.append(ch);
        }

        return bs.toString();
    }
    //-------------------------------PRINT---------------------------------------//
    public String print(String p){
        char[] aP = p.toCharArray();
        String rString = "";
        for(int i = 0; i < aP.length; i++){
            if((i+1)%4 == 0 && i != 0){
                rString += Character.toString(aP[i]) + " ";
            }else{
                rString += Character.toString(aP[i]);
            }

        }
        return rString;
    }
    public String par(char[] aK){
        String rString = "";
        for(int i = 0; i < aK.length; i++){
            if((i+1)%4 == 0 && i != 0){
                rString += String.valueOf(aK[i] + " ");
            }else{
                rString += String.valueOf(aK[i]);
            }

        }
        return rString;
    }





    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.lrb13.cipher/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.lrb13.cipher/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
