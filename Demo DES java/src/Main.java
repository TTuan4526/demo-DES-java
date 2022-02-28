import java.awt.*;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static long[] ConvertHexToBinary(String BanRo){
        long num =  Long.parseLong(BanRo,16);
        long[] x = new long[BanRo.length()*4];
        int index = 0;
        while(num > 0) {
            x[index++] = num %2;
            num = num / 2;

        }
        int j = x.length -1;
        int k = 0;
        while(k<j)
        {
            long temp = x[k];
            x[k] = x[j];
            x[j] = temp;
            k++;
            j--;
        }
        return x;
    }
    public static String[] ConvertBinaryToHex(int[] arrayBinary){
        String[] x = new String[16];
        int k=0,h=0;
        int[] Block = new int[4];
        for(int i = 1 ; i <= arrayBinary.length;i++){
                 Block[k++] = arrayBinary[i-1];
            if(i%4==0){
                k = 0;
                int Number = ConvertBinaryToDecimal(Block);
                x[h++] = Integer.toHexString(Number).toUpperCase();
            }


        }
        return x;
    }

    public static int[][] IP_1(int[][] array){
        int k = 4,h =1;
        int[][] b = new int[8][8];
        for(int i = 0 ; i < 8 ; i++){

                if(i%2 == 0){
                    for(int j = 0; j < 8 ; j++){
                        b[j][i] =  array[k+i][7-j];
                    }
                    k--;
                }
                else {
                    for(int j = 0; j < 8 ; j++){
                        b[j][i] =  array[i-h][7-j];
                    }
                    h++;
                }
        }
        return b;
    }
    public static int[][] IP(int a[][]){
        int k = 0;
        int[][] b = new int[a.length][a.length];
        for(int i = 0 ; i < a.length ; i++){
            if(i%2 != 0){
                for(int j = 0 ; j < a.length ; j++){
                    b[k][j] = a[a.length-1-j][i];
                }
                k++;
            }
        }
        for(int i = 0 ; i < a.length ; i++){
            if(i%2 == 0){
                for(int j = 0 ; j < a.length ; j++){
                    b[k][j] = a[a.length-1-j][i];
                }
                k++;
            }
        }
        return b;
    }
    public static int[] HoanViArray_K_PC_1(int[][] K_key){
        int[] tmp = new int[56];
        int k=0;
        for(int i = 0 ; i < 4 ; i++){

            for(int j = 7 ; j >= 0 ; j--){
                if(i == 3 && j == 3){
                    break;
                }
                tmp[k++] = K_key[j][i];
            }
        }
        for(int i = 6 ; i >= 3; i--){
            int j = 7;
            if(i == 3) {
                j = 3;

            }
            for(  ; j >=0 ; j--){


                tmp[k++] = K_key[j][i];
                if(i==3 && j==1){
                    return tmp;
                }
            }
        }
        return tmp;
    }
    public static  int[] PC_1(String K){

        // tach chuoi thanh 2ky tu mot, mỗi cặp sẽ được chuyển thành nhị phân và cho vào dòng của mảng 2 chiều để hoán vị với Pc-1
        String[] string_array_tmp = new String[8];
        int g = 0;
        for(int i = 0 ; i < K.length()-1;i+=2){
            string_array_tmp[g++] = K.substring(i,i+2);
        }

        int[][] array_K_primary = new int[8][8];
        for(int i = 0 ; i < 8 ; i++){
            for(int  j = 0 ; j < 8 ; j++){
                array_K_primary[i][j] =Integer.parseInt(String.valueOf(ConvertHexToBinary(string_array_tmp[i])[j]));
            }
        }
        int[][] PC1 = {
                {57,49,41,33,25,17,9},
                {1,58,50,42,34,26,18},
                {10,2,59,51,43,35,27},
                {19,11,3,60,52,44,36},
                {63,55,47,39,31,23,15},
                {7,62,54,46,38,30,22},
                {14,6,61,53,45,37,29},
                {21,13,5,28,20,12,4},
        };
        int[][] K_ = new int[8][7];
        int[] tmp = ConvertArray_2_to_1(array_K_primary,8,8);
        for(int i = 0 ; i < 8 ; i++)
            for(int j = 0 ; j < 7 ; j++)
                K_[i][j] = Integer.parseInt(String.valueOf(tmp[PC1[i][j]-1]));


        return ConvertArray_2_to_1(K_,7,8);
    }

    public static int[][] ConverArray_1_to_2(long array[]){
        int[][] arrayBanRo= new int[8][8];
        int k = 0;
        for(int i = 0 ; i < 8 ; i++){
            for(int j = 0; j < 8 ; j++){
                arrayBanRo[i][j] = Integer.parseInt(String.valueOf(array[k++]));
            }
        }
        return arrayBanRo;
    }
    public static int[] ConvertArray_2_to_1(int array[][] ,int Column,int Row){
        int[] tmp = new int[Row*Column];
        int k=0;
        for(int i = 0 ; i < Row ; i++)
        {
            for(int j = 0 ; j < Column ; j++)
                tmp[k++]=array[i][j];
        }
        return  tmp;
    }

    public static int[][] CreateArrayKey(String K_KEY){


        int[] K = PC_1(K_KEY); // Hoan vi khoa K theo phep PC-1

        //Tach Mang thanh 2 mang C va D.
        // Tim C1---C16
        // Tim D1---D16

        // Tao C0
        int[] C = new int[28];
        System.arraycopy(K, 0, C, 0, C.length);


        // Tao D0
        int[] D = new int[28];
        for (int i = 0; i < D.length; i++)
            D[i] = K[i + 28];
        int[][] PC2 = {
                {14,17,11,24,1,5},
                {3,28,15,6,21,10},
                {23,19,12,4,26,8},
                {16,7,27,20,13,2},
                {41,52,31,37,47,55},
                {30,40,51,45,33,48},
                {44,49,39,56,34,53},
                {46,42,50,36,29,32},
        };
        int[][] arrayKey = new int[16][48];
        for(int i = 0 ; i < 16 ; i++){
            int h=0,k=0;
                if(i == 0 || i == 1 || i == 8 || i == 15){
                    C = DichTrai_n_Bit(C,1);
                    D = DichTrai_n_Bit(D,1);
                    int[] tmp = new int[56];
                    for(int count = 0 ; count < tmp.length ; count++){

                            if(count < 28){
                                tmp[count] = C[count];
                            }
                            else
                                tmp[count] = D[count-28];
                        }
                    for(int j = 0 ; j < 48 ; j++){
                        if(k == 6 ){
                            h++;
                            k=0;
                        }
                            arrayKey[i][j] = tmp[PC2[h][k++]-1];
                    }
                }
                else{
                    C = DichTrai_n_Bit(C,2);
                    D = DichTrai_n_Bit(D,2);
                    int[] tmp = new int[56];
                    for(int count = 0 ; count < tmp.length ; count++){

                        if(count < 28){
                            tmp[count] = C[count];
                        }
                        else
                            tmp[count] = D[count-28];
                    }

                    for(int j = 0 ; j < 48 ; j++){
                        if(k == 6){
                            h++;
                            k=0;
                        }

                        arrayKey[i][j] = tmp[PC2[h][k++]-1];

                    }
                }


        }
            return arrayKey;
    }

    public static int[] DichTrai_n_Bit(int arrayBinary_[], int Count_bit){

        if(Count_bit == 1){
            int x = arrayBinary_[0];
            for(int i = 0 ; i < arrayBinary_.length -1 ; i++){
                    arrayBinary_[i] = arrayBinary_[i+1];
            }
            arrayBinary_[arrayBinary_.length - 1] = x & 0x01;
        }
        if(Count_bit == 2) {
            int x = arrayBinary_[0];
            for(int i = 0 ; i < arrayBinary_.length -1 ; i++){
                arrayBinary_[i] = arrayBinary_[i+1];
            }
            arrayBinary_[arrayBinary_.length - 1] = x & 0x01;

             x = arrayBinary_[0];
            for(int i = 0 ; i < arrayBinary_.length -1 ; i++){
                arrayBinary_[i] = arrayBinary_[i+1];
            }
            arrayBinary_[arrayBinary_.length - 1] = x & 0x01;

        }
    return  arrayBinary_;

    }

    public static int[] P(int[] arrayBinary){
        int[][] P_ ={
                {16,7,20,21},
                {29,12,28,17},
                {1,15,23,26},
                {5,18,31,10},
                {2,8,24,14},
                {32,27,3,9},
                {19,13,30,6},
                {22,11,4,25},
        };
        int[] f = new int[32];
        int k=0,h=0;
        for(int i = 0 ; i < arrayBinary.length; i++){
            if(k==4){
                k=0;
                h++;
            }
            f[i] = arrayBinary[P_[h][k++]-1];
        }
        return f;
    }

    public static int[] extensionFunction(int[] R){
        int[][] E = {
                {32,1,2,3,4,5},
                {4,5,6,7,8,9},
                {8,9,10,11,12,13},
                {12,13,14,15,16,17},
                {16,17,18,19,20,21},
                {20,21,22,23,24,25},
                {24,25,26,27,28,29},
                {28,29,30,31,32,1},
        };

        int[] ER = new int[48];
        int k = 0,h=0;
        for(int i = 0 ; i < 48 ; i++){
            if(k == 6) {
                k = 0;
                h++;
            }
            ER[i] = R[E[h][k++]-1];

        }

        return ER;
    }
    public static int[] F_Function( int[] R,int[][] arrayKey,int i) {
        int[][] S1 = {
                {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13},
        };
        int[][] S2 = {
                {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9},
        };
        int[][] S3 = {
                {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12},
        };
        int[][] S4 = {
                {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14},
        };
        int[][] S5 = {
                {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3},
        };
        int[][] S6 = {
                {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13},
        };
        int[][] S7 = {
                {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12},
        };
        int[][] S8 = {
                {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11},
        };


        int counter = 0;
        int[] C = new int[32];
        int[] ER = extensionFunction(R);
        int[] B1, B2, B3, B4, B5, B6, B7, B8;
        B1 = new int[6];
        B2 = new int[6];
        B3 = new int[6];
        B4 = new int[6];
        B5 = new int[6];
        B6 = new int[6];
        B7 = new int[6];
        B8 = new int[6];
        int[] ER_and_K = new int[48];
        for (int j = 0; j < 48; j++) {
            ER_and_K[j] = (ER[j] + arrayKey[i][j]) % 2;

        }
        int k = 0;
        int valueOf_Row_S_And_B = 0;
        int valueOf_column_S_And_B = 0;

        int[] row = new int[2];
        int[] column = new int[4];
        for (int a = 1; a <= 8; a++){
            for (int j = 0; j < 6; j++) {
                switch (a) {
                    case 1:
                        B1[j] = ER_and_K[k++];
                        break;
                    case 2:
                        B2[j] = ER_and_K[k++];
                        break;
                    case 3:
                        B3[j] = ER_and_K[k++];
                        break;
                    case 4:
                        B4[j] = ER_and_K[k++];
                        break;
                    case 5:
                        B5[j] = ER_and_K[k++];
                        break;
                    case 6:
                        B6[j] = ER_and_K[k++];
                        break;
                    case 7:
                        B7[j] = ER_and_K[k++];
                        break;
                    case 8:
                        B8[j] = ER_and_K[k++];
                        break;
                    default:
                        break;

                }
            }

            counter += 4;
            switch (a){
                case 1 -> {
                    row[0] = B1[0];
                    row[1] = B1[5];
                    valueOf_Row_S_And_B = ConvertBinaryToDecimal(row);
                    column[0] = B1[1];
                    column[1] = B1[2];
                    column[2] = B1[3];
                    column[3] = B1[4];
                    valueOf_column_S_And_B = ConvertBinaryToDecimal(column);

                    C = ConvertDecimalToBinary(S1[valueOf_Row_S_And_B][valueOf_column_S_And_B],counter,C);
                }
                case 2 -> {
                    row[0] = B2[0];
                    row[1] = B2[5];
                    valueOf_Row_S_And_B = ConvertBinaryToDecimal(row);
                    
                    
                    column[0] = B2[1];
                    column[1] = B2[2];
                    column[2] = B2[3];
                    column[3] = B2[4];
                    valueOf_column_S_And_B = ConvertBinaryToDecimal(column);
                    C = ConvertDecimalToBinary(S2[valueOf_Row_S_And_B][valueOf_column_S_And_B],counter,C);
                }
                case 3 -> {
                    row[0] = B3[0];
                    row[1] = B3[5];
                    valueOf_Row_S_And_B = ConvertBinaryToDecimal(row);
                    
                    
                    column[0] = B3[1];
                    column[1] = B3[2];
                    column[2] = B3[3];
                    column[3] = B3[4];
                    valueOf_column_S_And_B = ConvertBinaryToDecimal(column);
                    C = ConvertDecimalToBinary(S3[valueOf_Row_S_And_B][valueOf_column_S_And_B],counter,C);
                }
                case 4 -> {
                    row[0] = B4[0];
                    row[1] = B4[5];
                    valueOf_Row_S_And_B = ConvertBinaryToDecimal(row);
                    
                    
                    column[0] = B4[1];
                    column[1] = B4[2];
                    column[2] = B4[3];
                    column[3] = B4[4];
                    valueOf_column_S_And_B = ConvertBinaryToDecimal(column);
                    C = ConvertDecimalToBinary(S4[valueOf_Row_S_And_B][valueOf_column_S_And_B],counter,C);
                }
                case 5 -> {
                    row[0] = B5[0];
                    row[1] = B5[5];
                    valueOf_Row_S_And_B = ConvertBinaryToDecimal(row);
                    
                    
                    column[0] = B5[1];
                    column[1] = B5[2];
                    column[2] = B5[3];
                    column[3] = B5[4];
                    valueOf_column_S_And_B = ConvertBinaryToDecimal(column);
                    C = ConvertDecimalToBinary(S5[valueOf_Row_S_And_B][valueOf_column_S_And_B],counter,C);
                }
                case 6 -> {
                    row[0] = B6[0];
                    row[1] = B6[5];
                    valueOf_Row_S_And_B = ConvertBinaryToDecimal(row);
                    
                    
                    column[0] = B6[1];
                    column[1] = B6[2];
                    column[2] = B6[3];
                    column[3] = B6[4];
                    valueOf_column_S_And_B = ConvertBinaryToDecimal(column);
                    C = ConvertDecimalToBinary(S6[valueOf_Row_S_And_B][valueOf_column_S_And_B],counter,C);
                }
                case 7 -> {
                    row[0] = B7[0];
                    row[1] = B7[5];
                    valueOf_Row_S_And_B = ConvertBinaryToDecimal(row);
                    
                    
                    column[0] = B7[1];
                    column[1] = B7[2];
                    column[2] = B7[3];
                    column[3] = B7[4];
                    valueOf_column_S_And_B = ConvertBinaryToDecimal(column);
                    C = ConvertDecimalToBinary(S7[valueOf_Row_S_And_B][valueOf_column_S_And_B],counter,C);
                }
                case 8 -> {
                    row[0] = B8[0];
                    row[1] = B8[5];
                    valueOf_Row_S_And_B = ConvertBinaryToDecimal(row);
                    
                    
                    column[0] = B8[1];
                    column[1] = B8[2];
                    column[2] = B8[3];
                    column[3] = B8[4];
                    valueOf_column_S_And_B = ConvertBinaryToDecimal(column);
                    C = ConvertDecimalToBinary(S8[valueOf_Row_S_And_B][valueOf_column_S_And_B],counter,C);
                }
                default -> {
                }
            }
        }
        return C;
    }

    public static int[] ConvertDecimalToBinary(int NumberDecimal,int counter,int[] C){
        long[] tmp =  ConvertHexToBinary(Integer.toHexString(NumberDecimal));
        int i = 0;
        for(int r = counter - 4 ; r < counter ; r++){
            C[r] = Integer.parseInt(String.valueOf(tmp[i++]));
        }
        return C;
    }
    public static int ConvertBinaryToDecimal(int[] arrayBinary){
        int x = 0;
        for(int count = arrayBinary.length -1 ; count >= 0 ; count--){
            x += (int ) (arrayBinary[count] * Math.pow( 2 , Math.abs( count - arrayBinary.length + 1 ) ) );
        }
        return x;
    }

    public static int[] Encode(String BANRO,String K_KEY){
        String[] BanRo = new String[8];
        int g = 0;
        for(int i = 0 ; i < BANRO.length()-1;i+=2){
            BanRo[g++] = BANRO.substring(i,i+2);
        }

        System.out.println("Qua trinh ma hoa");
        int[][] arrayBanRo = new int[8][8];

        for(int i = 0 ; i < 8 ; i++){
            for(int  j = 0 ; j < 8 ; j++){

                arrayBanRo[i][j] =Integer.parseInt(String.valueOf(ConvertHexToBinary(BanRo[i])[j]));
            }

        }

        //long[] x = ConvertHexToBinary(BANRO); // Chuyen BANRO Thanh chuoi nhi phan

        //int[][] arrayBanRo = ConverArray_1_to_2(x); // Chuyen BANRO thanh mang 2 chieu

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(arrayBanRo[i][j] + " ");
            }
            System.out.println();
        }

        arrayBanRo = IP(arrayBanRo); // cho BanRo di qua hoan vi IP
        int[] arraytmp = ConvertArray_2_to_1(arrayBanRo, arrayBanRo.length, arrayBanRo.length);  //chuyen BanRo ve mang 1 chieu

        //Tach Mang thanh 2 mang L va R.
        int[] L = new int[arraytmp.length / 2];
        System.arraycopy(arraytmp, 0, L, 0, L.length);
        int[] R = new int[arraytmp.length / 2];
        for (int i = 0; i < L.length; i++) {
            R[i] = arraytmp[i + 32];
        }
        System.out.println("");
        for(int i =0 ; i < 32 ; i++){
            System.out.print(L[i]+" ");
        }
        System.out.println("");



        //Tao 16 khoa
        int[][] arrayKey = CreateArrayKey(K_KEY);
        for (int i = 0; i < 16; i++) {
            System.out.print("K " + (i + 1) + " = ");
            for (int j = 0; j < 48; j++) {
                System.out.print(arrayKey[i][j] + " ");
            }
            System.out.println();
        }

        //Ham f
        int[] tmp = new int[32];
        for(int i = 0 ; i < 16 ; i++){

            System.arraycopy(R, 0, tmp, 0, 32);

            int[]f = P(F_Function( R, arrayKey,i));

            for(int j = 0 ; j < 32 ; j++){
                if(f[j] == 0 && L[j] == 0)
                    R[j] = 0;
                else if(f[j] == 1 && L[j] == 1)
                    R[j] = 0;
                else R[j] = 1;
            }

            System.arraycopy(tmp, 0, L, 0, 32);
        }

        // Result là mảng R16 L16 sau 16 vòng lặp
        long[] Result = new long[64];
        for(int i = 0 ; i < 64;i++){
            if(i>31)
                Result[i] = L[i-32];
            else Result[i] = R[i];
        }
        System.out.println("");
        for(int i =0 ; i < 32 ; i++){
            System.out.print(L[i]+" ");
        }
        System.out.println("");
        // Chuyển mảng Result thành mảng 2 chi�?u để cho qua phép hoán vị IP-1
        int[][] l = ConverArray_1_to_2(Result);

        // Chuyển mảng Result v�? mảng 1 chi�?u sau khi cho qua hoán vị IP-1
        int[]bm = ConvertArray_2_to_1(IP_1(l),8,8);

        for(int i = 0 ; i < 64;i++){
            System.out.print(bm[i]+" ");
        }
        return bm;

    }
    public static String ResultEncode(String BANRO,String K_KEY){
        int[] bm = Encode(BANRO,K_KEY);
        System.out.println("\nBan Ma thu duoc la: ");
        // Chuyển bản mã thành chuỗi Hexa
        String[] BanMa = ConvertBinaryToHex(bm);
        for (String BanMa1 : BanMa) {
            System.out.print(BanMa1);
        }

        // Ghep Mang hex thanh Binary

        StringBuilder sb = new StringBuilder();
        for (String BanMa1 : BanMa) {
            sb.append(BanMa1);
        }
        String banMaY = sb.toString();
        return banMaY;
    }

    public static int[] Decode(String BANMA, String K_KEY){
        String[] BanMa = new String[8];
        int g = 0;
        for(int i = 0 ; i < BANMA.length()-1;i+=2){
            BanMa[g++] = BANMA.substring(i,i+2);
        }

        System.out.println("Qua trinh giai ma");

        int[][] banMa_Decode = new int[8][8];

        for(int i = 0 ; i < 8 ; i++){
            for(int  j = 0 ; j < 8 ; j++){
                banMa_Decode[i][j] = Integer.parseInt(String.valueOf(ConvertHexToBinary(BanMa[i])[j]));
            }

        }

        banMa_Decode = IP(banMa_Decode); // cho BanRo di qua hoan vi IP

        int[] arraytmp_BANMA = ConvertArray_2_to_1(banMa_Decode, banMa_Decode.length, banMa_Decode.length);  //chuyen BANMA ve mang 1 chieu

        //Tach Mang thanh 2 mang L va R.
        int[] L_Decode = new int[arraytmp_BANMA.length / 2];
        System.arraycopy(arraytmp_BANMA, 0, L_Decode, 0, L_Decode.length);
        int[] R_Decode = new int[arraytmp_BANMA.length / 2];
        for (int i = 0; i < R_Decode.length; i++) {
            R_Decode[i] = arraytmp_BANMA[i + 32];
        }


        int[][] arrayKey = CreateArrayKey(K_KEY); // tao array key
        for (int i = 0; i < 16; i++) {
            System.out.print("K " + (i + 1) + " = ");
            for (int j = 0; j < 48; j++) {
                System.out.print(arrayKey[i][j] + " ");
            }
            System.out.println();
        }

        //Hoan doi mang khoa K
        int[][] tmpKey_Decode = new int[16][48];
        for(int t = 0; t < 16 ;t++){
            System.arraycopy(arrayKey[15-t], 0, tmpKey_Decode[t], 0, 48);
        }

        //Ham f
        int[] tmp_Decode = new int[32];
        for(int i = 0 ; i < 16 ; i++){

            System.arraycopy(R_Decode, 0, tmp_Decode, 0, 32);

            int[]f = P(F_Function( R_Decode, tmpKey_Decode,i));

            for(int j = 0 ; j < 32 ; j++){
                if(f[j] == 0 && L_Decode[j] == 0)
                    R_Decode[j] = 0;
                else if(f[j] == 1 && L_Decode[j] == 1)
                    R_Decode[j] = 0;
                else R_Decode[j] = 1;
            }

            System.arraycopy(tmp_Decode, 0, L_Decode, 0, 32);
        }

        // Result là mảng R16 L16 sau 16 vòng lặp
        long[] Result1 = new long[64];
        for(int i = 0 ; i < 64;i++){
            if(i>31)
                Result1[i] = L_Decode[i-32];
            else Result1[i] = R_Decode[i];
        }

        // Chuyển mảng Result thành mảng 2 chieu để cho qua phép hoán vị IP-1
        int[][] l1 = ConverArray_1_to_2(Result1);

        // Chuyển mảng Result ve mảng 1 chieu sau khi cho qua hoán vị IP-1

        int[]bm1 = ConvertArray_2_to_1(IP_1(l1),8,8);

        for(int i = 0 ; i < 64;i++){
            System.out.print(bm1[i]+" ");
        }
        return bm1;
    }
    public static String ResultDecode(String BANRO,String K_KEY){
        int[] bm = Decode(BANRO,K_KEY);
        System.out.println("\nBan Ma thu duoc la: ");
        // Chuyển bản mã thành chuỗi Hexa
        String[] BanMa = ConvertBinaryToHex(bm);
        for (String BanMa1 : BanMa) {
            System.out.print(BanMa1);
        }

        // Ghep Mang hex thanh Binary

        StringBuilder sb = new StringBuilder();
        for (String BanMa1 : BanMa) {
            sb.append(BanMa1);
        }
        String banMaY = sb.toString();
        return banMaY;
    }
    public static void main(String[] args) {

        Frame DES = new Frame("DES");
        //Get SizeScreen
        DES.setLayout(null);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Set Size to Frame
        DES.setSize(700, 800);
        //Get Location
        int w = DES.getSize().width;
        int h = DES.getSize().height;
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/2;
        // Move the Frame to center Screen
        DES.setLocation(x, y);
        DES.setVisible(true);

        // Tao Label
        Font newfont= new Font("ThaiTuan",20,30);
        Label labDES = new Label("DES");
        labDES.setFont(newfont);
        labDES.setBounds(300,30,300,50);

//        DBConnection conn = new DBConnection();

        Label labBanRo=new Label("Bản Rõ: ");
        Label labKhoaK=new Label("Khóa K: ");
        Label labBanMa = new Label("Bản Mã:");
        Label labChuoiBinary = new Label("Chuỗi Nhị Phân");
        Label labArrayKhoaK = new Label("16 khóa K");

        labBanRo.setBounds(80, 100, 100, 30);
        labKhoaK.setBounds(80, 150, 100, 30);
        labBanMa.setBounds(80, 200,100,30);
        labChuoiBinary.setBounds(30,700,150,30);
        labArrayKhoaK.setBounds(80,300,100,30);
        TextField txtBanRo =new TextField();
        txtBanRo.setBounds(190, 100, 200, 30);

        TextField txtKhoaK =new TextField();
        txtKhoaK.setBounds(190, 150, 200, 30);

        TextField txtBanMa =new TextField();
        txtBanMa.setBounds(190, 200, 200, 30);


        Button btnEncode =new Button("Mã Hóa");
        btnEncode.setBounds(200, 250 ,70, 30);

        Button btnDecode=new Button("Giải Mã");
        btnDecode.setBounds(300, 250, 70, 30);

        TextArea txaKhoaK=new TextArea();
        txaKhoaK.setBounds(80, 350, 550, 300);

        TextField txtChuoiNhiPhanBanMa = new TextField();
        txtChuoiNhiPhanBanMa.setBounds(120,700,550,30);

        DES.add(txtBanMa);
        DES.add(txtKhoaK);
        DES.add(txtBanRo);
        DES.add(labBanMa);
        DES.add(labBanRo);
        DES.add(labKhoaK);
        DES.add(labDES);
        DES.add(labArrayKhoaK);
        DES.add(btnEncode);
        DES.add(btnDecode);
        DES.add(txaKhoaK);
        DES.add(txtChuoiNhiPhanBanMa);
        DES.add(labChuoiBinary);


        DES.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                DES.dispose();
            }
        });

        btnEncode.addActionListener((ActionEvent e) -> {
            String K_KEY = txtKhoaK.getText();
            String BANRO = txtBanRo.getText();
            //133457799BBCDFF1
            //85E813540F0AB405
            
            txtBanMa.setText(ResultEncode(BANRO,K_KEY));
            int[][] arrayKey = CreateArrayKey(K_KEY);
            txaKhoaK.setText("K1 = ");
            txtChuoiNhiPhanBanMa.setText("");
            for(int i = 0 ; i < 16 ; i++){
                for(int j = 0 ; j < 48; j++)
                    txaKhoaK.setText(txaKhoaK.getText() + arrayKey[i][j] +"");
                if(i==15) break;
                txaKhoaK.setText(txaKhoaK.getText() + "\n" + "K"+(i+2)+" = ");
            }
            
            // Complete Process Encode
            int[] stringBinary = Encode(BANRO,K_KEY);
            for(int i = 0 ; i < stringBinary.length;i++){
                txtChuoiNhiPhanBanMa.setText(txtChuoiNhiPhanBanMa.getText() + stringBinary[i]);
            }
            //1245786352412547
        });
        btnDecode.addActionListener((ActionEvent e) ->{
            String K_KEY = txtKhoaK.getText();
            String BANMA = txtBanMa.getText();
            //0123456789abcdef
            txtBanRo.setText(ResultDecode(BANMA,K_KEY));
            int[][] arrayKey = CreateArrayKey(K_KEY);
            txaKhoaK.setText("K1 = ");
            txtChuoiNhiPhanBanMa.setText("");
            for(int i = 0 ; i < 16 ; i++){
                for(int j = 0 ; j < 48; j++)
                    txaKhoaK.setText(txaKhoaK.getText() + arrayKey[15-i][j] +"");
                if(i==15) break;
                txaKhoaK.setText(txaKhoaK.getText() + "\n" + "K"+(i+2)+" = ");
            }
            int[] stringBinary = Decode(BANMA,K_KEY);
            for(int i = 0 ; i < stringBinary.length;i++){
                txtChuoiNhiPhanBanMa.setText(txtChuoiNhiPhanBanMa.getText() + stringBinary[i]);
            }

            //complete Process Decode
        });



    }

}