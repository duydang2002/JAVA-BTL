package com.example.cuoiki;

import com.example.cuoiki.Customer.UserInformation;
import com.example.cuoiki.Drink.DrinkMap;
import com.example.cuoiki.Formatting.FormattedText;
import com.example.cuoiki.Formatting.CustomFont;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ChatPage extends Pane
{
    //Declaration:
    private ScrollPane ScrollMainPage;
    private Pane MainPage;
    private ImageView BlurredBackground;
    private FormattedText PageTitle, Quote;

    private UserInformation customer;
    private DrinkMap drink;
    private final Color TextColor=Color.rgb(71, 43, 43, 1.0);

    //Setup:
    public void setup(UserInformation customer, DrinkMap drink)
    {
        //SetCustomer:
        this.customer=customer;
        this.drink=drink;
    }

    //Build:
    private void build()
    {
        //Background:
        try {BlurredBackground=new ImageView(new Image(new FileInputStream("materials/image/BlurredBackground.png")));}
        catch(FileNotFoundException e) {}
        BlurredBackground.setFitWidth(264); BlurredBackground.setFitHeight(528);
        BlurredBackground.setSmooth(true);
        BlurredBackground.setCache(true);

        //PageTitle:
        PageTitle=new FormattedText("Chatting Up", -1.0, 0, false);
        PageTitle.setFont(CustomFont.createFont("Raleway - ExtraBold", "ttf", 28));
        PageTitle.setPrefWrapLength(170);
        PageTitle.setFill(TextColor);
        PageTitle.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(0 ,0 , 0, 0.2), 4, 0, 0, 4));
        PageTitle.setLayoutX(23); PageTitle.setLayoutY(44);

        //Quote:
        Quote=new FormattedText("Tell your stories and problems     here with us!", -1.0, 1, false);
        Quote.setPrefWrapLength(88);
        Quote.setFont(CustomFont.createFont("Raleway - SemiBoldItalic", "ttf", 12));
        Quote.setFill(TextColor);
        Quote.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(0 ,0 , 0, 0.15), 4, 0, 0, 4));
        Quote.setLayoutX(23); Quote.setLayoutY(84);

        //MainPage:
        MainPage=new Pane(PageTitle, Quote);
        ScrollMainPage=new ScrollPane(MainPage);
        ScrollMainPage.setStyle("-fx-background: transparent; -fx-background-color: transparent; -fx-unit-increment: 10; -fx-block-increment: 50;");
        ScrollMainPage.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        ScrollMainPage.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        ScrollMainPage.setPrefWidth(264); ScrollMainPage.setPrefHeight(480);
        ScrollMainPage.setLayoutX(0); ScrollMainPage.setLayoutY(0);

        //NavigationBar:
        NavigationBar NavBar=new NavigationBar(NavigationBar.PagePicker.CHAT, customer, drink);

        //Layout:
        this.getChildren().addAll(BlurredBackground, ScrollMainPage, NavBar);
        this.setLayoutX(0); this.setLayoutY(0);
    }

    //Constructor:
    public ChatPage(UserInformation customer, DrinkMap drink)
    {
        setup(customer, drink);
        build();
    }
}