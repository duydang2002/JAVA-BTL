package com.example.cuoiki.Customer;

import com.example.cuoiki.Drink.DrinkConst;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

public class UserInformation
{
    //PersonalInformation:
    public boolean isLoggedIn;
    public String Password;
    public Image UserImage;
    public String EditedName, FullName, PhoneNumber, Email;
    public int UserLevel;
    public double UserLevelProgress;
    public boolean TurnNotifications, Previews, Vibrations;

    //Receipt:
    public static class ReceiptDraft
    {
        public Image DrinkImage;
        public String DrinkType, DrinkName;
        public int MCount, LCount;
        public double Price;

        public ReceiptDraft(Image drinkimage, String drinktype, String drinkname)
        {
            DrinkImage=drinkimage;
            DrinkType=drinktype; DrinkName=drinkname;
            MCount=0; LCount=0;
            Price=0;
        }
    }
    public HashMap<DrinkConst, ReceiptDraft> Receipt;
    public void addtoReceipt(DrinkConst drinkconst, ReceiptDraft draft)
    {
        if(draft.Price!=0)
        {
            if(Receipt.containsKey(drinkconst))
            {
                ReceiptDraft olddraft=Receipt.get(drinkconst);
                draft.MCount+=olddraft.MCount;
                draft.LCount+=olddraft.LCount;
                draft.Price+=olddraft.Price;
            }
            Receipt.put(drinkconst, draft);
        }
    }

    //Constructor:
    public UserInformation()
    {
        isLoggedIn=false;
        try {UserImage=new Image(new FileInputStream("materials/image/BlankAvatar.png"));}
        catch(FileNotFoundException e) {}
        Password="";
        EditedName="(none)"; FullName="(none)"; PhoneNumber="(none)"; Email="(none)";
        UserLevel=0; UserLevelProgress=0;
        TurnNotifications=false; Previews=false; Vibrations=false;
        Receipt=new HashMap<DrinkConst, ReceiptDraft>();
    }
}