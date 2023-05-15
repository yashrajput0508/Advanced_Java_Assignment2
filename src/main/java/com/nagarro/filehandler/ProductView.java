package com.nagarro.filehandler;

import com.nagarro.models.TShirtModel;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author yash02
 *
 * {@summary This is the productview class which print the list of tshirt which user wants to search}
 * 
 */
public class ProductView {
    
    protected void print(List<TShirtModel> tshirtLists)
    {
        if (tshirtLists.isEmpty())
        {
            System.out.println("TShirts Not Found");
        }
        else {
            System.out.println("Output:- ");
            System.out.println("       ID       |           NAME           | COLOUR |gender|SIZE| PRICE |RATING|AVAILABILITY|");

            for (TShirtModel value : tshirtLists
            ) {
                System.out.print(String.format("%16s",value.getId()));
                    System.out.print(String.format("%27s",value.getName()));
                    System.out.print( String.format("%8s",value.getColor()));
                    System.out.print(String.format("%5s",value.getGender()));
                    System.out.print(String.format("%6s",value.getSize())+" ");
                    System.out.print(String.format("%8s",value.getPrice())+" ");
                    System.out.print("  "+value.getRating()+"     ");
                    System.out.println("   "+value.getAvailable()+" ");
            }

            System.out.println();
        }
    }
}
