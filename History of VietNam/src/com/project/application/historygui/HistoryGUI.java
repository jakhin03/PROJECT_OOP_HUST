package com.project.application.historygui;

import java.util.*;

import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import com.project.historydatabase.dynasty.Dynasty;
import com.project.historydatabase.figure.King;
import com.project.historydatabase.figure.Character;
import com.project.historydatabase.festival.Festival;
import com.project.historydatabase.relic.Relic;
import com.project.historydatabase.event.Event;
import com.project.application.*;

public class HistoryGUI {
	public void showDynastyPopup(Dynasty curSelect, ObservableList<King> listKing){
		BorderPane borderPane = new BorderPane();
        Stage stage = new Stage();
        stage.setTitle("Các triều đại Việt Nam");
        
        Image imagebackground = new Image(
                "https://media.discordapp.net/attachments/755083836169257062/1071699179488944128/image.png?width=1190&height=670");
        BackgroundImage backgroundImage = new BackgroundImage(imagebackground, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        borderPane.setBackground(new Background(backgroundImage));
        
        Label name = new Label("Tên Triều đại: " + curSelect.getName());
        Label startYear = new Label("Năm bắt đầu: " + curSelect.getStartYear());
        Label endYear = new Label("Năm kết thúc: " + curSelect.getEndYear());
        Label capital = new Label("Thủ dô: " + curSelect.getCapital());
        Label founder = new Label("Người sáng lập: " + curSelect.getFounder().getTen());
        String strKing = "";
        LinkedList<King> newKing = new LinkedList<King>();
        for (int i = 0; i < curSelect.getKings().size(); i++) {

            strKing += curSelect.getKings().get(i).getTen() + ",";
            for (int j = 0; j < listKing.size(); j++) {
                if (curSelect.getKings().get(i).getTen().toLowerCase()
                        .indexOf(listKing.get(j).getTen().toLowerCase()) != -1) {
                    newKing.add(listKing.get(j));
                }
            }
        }
        Label kings = new Label(strKing);
        curSelect.setKings(newKing);
        kings.setWrapText(true);

        name.getStyleClass().add("text-color");
        startYear.getStyleClass().add("text-color");
        endYear.getStyleClass().add("text-color");
        capital.getStyleClass().add("text-color");
        founder.getStyleClass().add("text-color");

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(20, 20, 20, 20));
        hbox.setSpacing(25);


        VBox labelbox = new VBox();
        // labelbox.setSpacing(20);
        labelbox.getChildren().addAll(name, startYear, endYear, capital, founder);

        hbox.getChildren().addAll(labelbox);

        borderPane.setCenter(hbox);
        labelbox.setAlignment(Pos.CENTER);
        hbox.setAlignment(Pos.CENTER);

        HBox moreInforContainer = new HBox();
        for (int i = 0; i < curSelect.getKings().size(); i++) {
            final int index = i;
            Button moreInfoButton = new Button("" + curSelect.getKings().get(index).getTen());
            moreInfoButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                showKingPopup(curSelect.getKings().get(index));
            });
            moreInforContainer.getChildren().add(moreInfoButton);
        }

        borderPane.setBottom(moreInforContainer);
        BorderPane.setAlignment(moreInforContainer, Pos.CENTER);
        Scene scene = new Scene(borderPane, 800, 600);
        scene.getStylesheets().add(getClass().getResource("./popupStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
		
	public void showFestivalPopup(Festival curSelect, ObservableList<Character> listCharacter, ObservableList<Dynasty> listDynasty, ObservableList<King> listKing) {
		 BorderPane borderPane = new BorderPane();
	        Stage stage = new Stage();
	        stage.setTitle("Các lễ hội văn hóa ở Việt Nam");

	        Image imagebackground = new Image(
	                "https://media.discordapp.net/attachments/755083836169257062/1071699179488944128/image.png?width=1190&height=670");
	        BackgroundImage backgroundImage = new BackgroundImage(imagebackground, BackgroundRepeat.NO_REPEAT,
	                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
	        borderPane.setBackground(new Background(backgroundImage));

	        Image image = new Image(
	                "https://lib.agu.edu.vn/images/2020/2.png");
	        ImageView imageView = new ImageView(image);
	        imageView.setFitWidth(233);
	        imageView.setFitHeight(145);
	        imageView.getStyleClass().add("border-style");

	        Label tenLeHoi = new Label("Tên Lễ hội: " + curSelect.getTenLeHoi());
	        Label thoigian = new Label("Thời gian: " + curSelect.getThoigian());
	        Label diaDiem = new Label("Địa điểm: " + curSelect.getDiaDiem());
	        Label noiDung = new Label("Nội dung: " + curSelect.getNoiDung());
	        noiDung.setWrapText(true);
	        String strCharacter = "";
	        Character newCharacter = new Character();

	        strCharacter = curSelect.getCharacter().getTen();
	        for (int j = 0; j < listCharacter.size(); j++) {

	            if (curSelect.getCharacter().getTen().toLowerCase()
	                    .indexOf(listCharacter.get(j).getTen().toLowerCase()) != -1) {
	                newCharacter = listCharacter.get(j);
	            }
	        }

	        curSelect.setCharacter(newCharacter);
	        Label Character = new Label(
	                "Nhân vật liên quan: " + (curSelect.getCharacter().getTen() == null ? "Không có" : strCharacter));
	        Character.setWrapText(true);

	        tenLeHoi.getStyleClass().add("text-color");
	        thoigian.getStyleClass().add("text-color");
	        diaDiem.getStyleClass().add("text-color");
	        noiDung.getStyleClass().add("text-color");
	        Character.getStyleClass().add("text-color");

	        VBox vbox = new VBox();
	        HBox hbox = new HBox();
	        hbox.setPadding(new Insets(20, 20, 20, 20));
	        hbox.setSpacing(25);
	        HBox picturebox = new HBox();
	        // picturebox.setSpacing(20);
	        picturebox.getChildren().add(imageView);
	        picturebox.setStyle(
	                "-fx-border-color: white; -fx-border-width: 3px; -fx-effect : dropshadow(one-pass-box,white, 5, 5, 0, 0);");

	        VBox labelbox = new VBox();
	        // labelbox.setSpacing(20);
	        labelbox.getChildren().addAll(tenLeHoi, thoigian, diaDiem, Character);

	        hbox.getChildren().addAll(picturebox, labelbox);

	        VBox contentText = new VBox();
	        contentText.getChildren().addAll(noiDung);
	        contentText.setPadding(new Insets(10, 50, 10, 50));

	        vbox.getChildren().addAll(hbox, contentText);
	        borderPane.setCenter(vbox);
	        picturebox.setAlignment(Pos.CENTER_LEFT);
	        labelbox.setAlignment(Pos.BASELINE_LEFT);
	        hbox.setAlignment(Pos.CENTER);
	        contentText.setAlignment(Pos.CENTER);
	        vbox.setAlignment(Pos.CENTER);

	        if (curSelect.getCharacter().getTen() != null) {
	            Button moreInfoButton = new Button("More Info " + curSelect.getCharacter().getTen());
	            moreInfoButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
	                showCharacterPopup(curSelect.getCharacter(), listDynasty, listKing);
	            });
	            borderPane.setBottom(moreInfoButton);
	        }

	        Scene scene = new Scene(borderPane, 800, 600);
	        scene.getStylesheets().add(getClass().getResource("./popupStyle.css").toExternalForm());
	        stage.setScene(scene);
	        stage.show();
	}
	
	public void showCharacterPopup(Character curSelect, ObservableList<Dynasty> listDynasty, ObservableList<King> listKing) {
			BorderPane borderPane = new BorderPane();
	        Stage stage = new Stage();
	        stage.setTitle("Các nhân vật nổi tiếng Việt Nam");
	        Image imagebackground = new Image(
	                "https://media.discordapp.net/attachments/755083836169257062/1071699179488944128/image.png?width=1190&height=670");
	        BackgroundImage backgroundImage = new BackgroundImage(imagebackground, BackgroundRepeat.NO_REPEAT,
	                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
	        borderPane.setBackground(new Background(backgroundImage));

	        Image image = new Image(
	                "https://media.ohay.tv/v1/upload/content/2017-05/01/1-03e8c239c32be5d42c2bd5fa63243545-ohaytv.jpg");
	        ImageView imageView = new ImageView(image);
	        imageView.setFitWidth(233);
	        imageView.setFitHeight(145);
	        imageView.getStyleClass().add("border-style");

	        Label ten = new Label("Họ và Tên: " + curSelect.getTen());
	        Label queQuan = new Label("Quê Quán: " + curSelect.getQueQuan());
	        Label namSinh = new Label("Năm sinh: " + curSelect.getNamSinh());
	        Label namMat = new Label("Năm mất: " + curSelect.getNamMat());
	        String strTrieuDai = "";
	        ArrayList<Dynasty> dynasties = new ArrayList<Dynasty>();
	        for (int i = 0; i < curSelect.getTrieuDai().size(); i++) {

	            strTrieuDai += curSelect.getTrieuDai().get(i).getName() + ",";
	            for (int j = 0; j < listDynasty.size(); j++) {

	                if (curSelect.getTrieuDai().get(i).getName().toLowerCase()
	                        .indexOf(listDynasty.get(j).getName().toLowerCase()) != -1) {
	                    dynasties.add(listDynasty.get(j));
	                }
	            }

	        }
	        curSelect.setTrieuDai(dynasties);
	        Label trieuDai = new Label("Triều đại: " + strTrieuDai);
	        Label ghiChu = new Label("Ghi chú: " + curSelect.getGhiChu());
	        ghiChu.setWrapText(true);

	        ten.getStyleClass().add("text-color");
	        queQuan.getStyleClass().add("text-color");
	        namSinh.getStyleClass().add("text-color");
	        namMat.getStyleClass().add("text-color");
	        trieuDai.getStyleClass().add("text-color");
	        ghiChu.getStyleClass().add("text-color");

	        VBox vbox = new VBox();
	        HBox hbox = new HBox();
	        hbox.setPadding(new Insets(20, 20, 20, 20));
	        hbox.setSpacing(25);
	        HBox picturebox = new HBox();
	        // picturebox.setSpacing(20);
	        picturebox.getChildren().add(imageView);
	        picturebox.setStyle(
	                "-fx-border-color: white; -fx-border-width: 3px; -fx-effect : dropshadow(one-pass-box,white, 5, 5, 0, 0);");

	        VBox labelbox = new VBox();
	        // labelbox.setSpacing(20);
	        labelbox.getChildren().addAll(ten, queQuan, namSinh, namMat, trieuDai, ghiChu);

	        hbox.getChildren().addAll(picturebox, labelbox);

	        VBox contentText = new VBox();
	        contentText.getChildren().addAll(ghiChu);
	        contentText.setPadding(new Insets(10, 50, 10, 50));

	        vbox.getChildren().addAll(hbox, contentText);
	        borderPane.setCenter(vbox);
	        picturebox.setAlignment(Pos.CENTER_LEFT);
	        labelbox.setAlignment(Pos.BASELINE_LEFT);
	        hbox.setAlignment(Pos.CENTER);
	        contentText.setAlignment(Pos.CENTER);
	        vbox.setAlignment(Pos.CENTER);

	        HBox moreInforContainer = new HBox();

	        for (int i = 0; i < curSelect.getTrieuDai().size(); i++) {
	            final int index = i;
	            Button moreInfoButton = new Button("More Info " + curSelect.getTrieuDai().get(index).getName());
	            moreInfoButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
	                showDynastyPopup(curSelect.getTrieuDai().get(index), listKing);
	            });
	            moreInforContainer.getChildren().add(moreInfoButton);
	        }

	        borderPane.setBottom(moreInforContainer);
	        BorderPane.setAlignment(moreInforContainer, Pos.CENTER);
	        Scene scene = new Scene(borderPane, 800, 600);
	        scene.getStylesheets().add(getClass().getResource("./popupStyle.css").toExternalForm());
	        stage.setScene(scene);
	        stage.show();
	    
	}
	
	public  void showKingPopup(King curSelect) {
		 BorderPane borderPane = new BorderPane();
	        Stage stage = new Stage();
	        stage.setTitle("Các vị vua Việt Nam");
	        Image imagebackground = new Image(
	                "https://media.discordapp.net/attachments/755083836169257062/1071699179488944128/image.png?width=1190&height=670");
	        BackgroundImage backgroundImage = new BackgroundImage(imagebackground, BackgroundRepeat.NO_REPEAT,
	                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
	        borderPane.setBackground(new Background(backgroundImage));

	        Image image = new Image(
	                "https://baotanglichsu.vn/DataFiles/Uploaded/image/03-Thieu-Tri-emperor.jpg");
	        ImageView imageView = new ImageView(image);
	        // imageView.setStyle("-fx-border-color: red; -fx-border-width:
	        // medium;-fx-border-style: solid;");
	        imageView.getStyleClass().add("border-style");

	        Label ten = new Label("Tên Vua: " + curSelect.getTen());
	        Label namTriVi = new Label("Năm trị vì: " + curSelect.getNamTriVi());
	        Label theThu = new Label("Tên thứ: " + curSelect.getTheThu());
	        Label tenHuy = new Label("Tên húy: " + curSelect.getTenHuy());
	        Label nienHieu = new Label("Niên hiệu: " + curSelect.getNienHieu());
	        Label thuyHieu = new Label("Thụy hiệu: " + curSelect.getThuyHieu());
	        Label mieuHieu = new Label("Miếu hiệu: " + curSelect.getMieuHieu());
	        Label articleLink = new Label("Link bài báo tìm hiểu thêm: " + curSelect.getArticleLink());

	        ten.getStyleClass().add("text-color");
	        namTriVi.getStyleClass().add("text-color");
	        theThu.getStyleClass().add("text-color");
	        tenHuy.getStyleClass().add("text-color");
	        nienHieu.getStyleClass().add("text-color");
	        thuyHieu.getStyleClass().add("text-color");
	        mieuHieu.getStyleClass().add("text-color");
	        articleLink.getStyleClass().add("text-color");

	        VBox vbox = new VBox();
	        HBox hbox = new HBox();
	        hbox.setPadding(new Insets(20, 20, 20, 20));
	        hbox.setSpacing(25);
	        HBox picturebox = new HBox();
	        // picturebox.setSpacing(20);
	        picturebox.getChildren().add(imageView);
	        picturebox.setStyle(
	                "-fx-border-color: white; -fx-border-width: 3px; -fx-effect : dropshadow(one-pass-box,white, 5, 5, 0, 0);");

	        VBox labelbox = new VBox();
	        // labelbox.setSpacing(20);
	        labelbox.getChildren().addAll(ten, namTriVi, theThu, tenHuy, nienHieu, thuyHieu, mieuHieu);

	        hbox.getChildren().addAll(picturebox, labelbox);

	        VBox contentText = new VBox();
	        contentText.getChildren().addAll(articleLink);

	        vbox.getChildren().addAll(hbox, contentText);
	        borderPane.setCenter(vbox);
	        picturebox.setAlignment(Pos.CENTER_LEFT);
	        labelbox.setAlignment(Pos.BASELINE_LEFT);
	        hbox.setAlignment(Pos.CENTER);
	        contentText.setAlignment(Pos.CENTER);
	        vbox.setAlignment(Pos.CENTER);
	        Scene scene = new Scene(borderPane, 800, 600);
	        scene.getStylesheets().add(getClass().getResource("./popupStyle.css").toExternalForm());
	        stage.setScene(scene);
	        stage.show();
	}
	
	public void showRelicPopup(Relic curSelect, ObservableList<Character> characters, ObservableList<King> kings, ObservableList<Dynasty> dynasties) {
		   BorderPane borderPane = new BorderPane();
	        Stage stage = new Stage();
	        stage.setTitle("Di tích lịch sử Việt Nam");

	        Image imagebackground = new Image(
	                "https://media.discordapp.net/attachments/755083836169257062/1071699179488944128/image.png?width=1190&height=670");
	        BackgroundImage backgroundImage = new BackgroundImage(imagebackground, BackgroundRepeat.NO_REPEAT,
	                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
	        borderPane.setBackground(new Background(backgroundImage));

	        Image image = new Image(
	                "https://mcdn.coolmate.me/uploads/January2022/di-tic-lich-su-viet-nam.png");
	        ImageView imageView = new ImageView(image);
	        imageView.setFitWidth(233);
	        imageView.setFitHeight(145);
	        Label name = new Label("Tên di tích: " + curSelect.getName());
	        Label location = new Label("Địa điểm: " + curSelect.getLocation());
	        Label type = new Label("Kiểu di tích: " + curSelect.getType());
	        Label rank = new Label("Di tích cấp: " + curSelect.getRank());
	        Label desc = new Label("Thông tin thêm: " + curSelect.getDesc());
	        desc.setWrapText(true);
	        String strTrieuDai = "";
	        String strKings = "";
	        String strCharacters = "";
	        LinkedList<Dynasty> newDynasties = new LinkedList<Dynasty>();
	        LinkedList<King> newKings = new LinkedList<King>();
	        LinkedList<Character> newCharacters = new LinkedList<Character>();
	        for (int i = 0; i < curSelect.getDynasties().size(); i++) {

	            strTrieuDai += curSelect.getDynasties().get(i).getName() + ",";
	            for (int j = 0; j < dynasties.size(); j++) {

	                if (curSelect.getDynasties().get(i).getName().toLowerCase()
	                        .indexOf(dynasties.get(j).getName().toLowerCase()) != -1) {
	                    newDynasties.add(dynasties.get(j));
	                }
	            }
	        }
	        for (int i = 0; i < curSelect.getKings().size(); i++) {

	            strKings += curSelect.getKings().get(i).getTen() + ",";
	            for (int j = 0; j < kings.size(); j++) {

	                if (curSelect.getKings().get(i).getTen().toLowerCase()
	                        .indexOf(kings.get(j).getTen().toLowerCase()) != -1) {
	                    newKings.add(kings.get(j));

	                }
	            }
	        }
	        for (int i = 0; i < curSelect.getCharacters().size(); i++) {

	            strCharacters += curSelect.getCharacters().get(i).getTen() + ",";
	            for (int j = 0; j < characters.size(); j++) {

	                if (curSelect.getCharacters().get(i).getTen().toLowerCase()
	                        .indexOf(characters.get(j).getTen().toLowerCase()) != -1) {
	                    newCharacters.add(characters.get(j));

	                }
	            }
	        }
	        curSelect.setDynasties(newDynasties);
	        curSelect.setKings(newKings);
	        curSelect.setCharacters(newCharacters);
	        Label trieuDai = new Label(
	                "Triều đại: " + (strTrieuDai == "" ? "Chưa rõ" : strTrieuDai));
	        Label vua = new Label("Vua: " + (strKings == "" ? "Không có" : strKings));
	        Label nhanVat = new Label(
	                "Nhân vật Lịch sử: " + (strCharacters == "" ? "Không có" : strCharacters));

	        name.getStyleClass().add("text-color");
	        location.getStyleClass().add("text-color");
	        type.getStyleClass().add("text-color");
	        rank.getStyleClass().add("text-color");
	        desc.getStyleClass().add("text-color");
	        trieuDai.getStyleClass().add("text-color");
	        vua.getStyleClass().add("text-color");
	        nhanVat.getStyleClass().add("text-color");

	        VBox vbox = new VBox();
	        HBox hbox = new HBox();
	        hbox.setPadding(new Insets(20, 20, 20, 20));
	        hbox.setSpacing(25);
	        HBox picturebox = new HBox();
	        // picturebox.setSpacing(20);
	        picturebox.getChildren().add(imageView);
	        picturebox.setStyle(
	                "-fx-border-color: white; -fx-border-width: 3px; -fx-effect : dropshadow(one-pass-box,white, 5, 5, 0, 0);");

	        VBox labelbox = new VBox();
	        // labelbox.setSpacing(20);
	        labelbox.getChildren().addAll(name, location, type, rank, trieuDai, vua, nhanVat);

	        hbox.getChildren().addAll(picturebox, labelbox);

	        VBox contentText = new VBox();
	        contentText.getChildren().addAll(desc);
	        contentText.setPadding(new Insets(10, 50, 10, 50));

	        vbox.getChildren().addAll(hbox, contentText);
	        borderPane.setCenter(vbox);
	        picturebox.setAlignment(Pos.CENTER_LEFT);
	        labelbox.setAlignment(Pos.BASELINE_LEFT);
	        hbox.setAlignment(Pos.CENTER);
	        contentText.setAlignment(Pos.CENTER);
	        vbox.setAlignment(Pos.CENTER);
	        HBox moreInforContainer = new HBox();

	        for (int i = 0; i < curSelect.getDynasties().size(); i++) {
	            final int index = i;
	            Button moreInfoButton = new Button("More Info " + curSelect.getDynasties().get(index).getName());
	            moreInfoButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
	                showDynastyPopup(curSelect.getDynasties().get(index), kings);
	            });
	            moreInforContainer.getChildren().add(moreInfoButton);
	        }

	        for (int i = 0; i < curSelect.getKings().size(); i++) {
	            final int index = i;
	            Button moreInfoButton = new Button("More Info " + curSelect.getKings().get(index).getTen());
	            moreInfoButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
	                showKingPopup(curSelect.getKings().get(index));
	            });
	            moreInforContainer.getChildren().add(moreInfoButton);
	        }

	        for (int i = 0; i < curSelect.getCharacters().size(); i++) {
	            final int index = i;
	            Button moreInfoButton = new Button("More Info " + curSelect.getCharacters().get(index).getTen());
	            moreInfoButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
	                showCharacterPopup(curSelect.getCharacters().get(index), dynasties, kings);
	            });
	            moreInforContainer.getChildren().add(moreInfoButton);
	        }
	        borderPane.setBottom(moreInforContainer);
	        Scene scene = new Scene(borderPane, 800, 600);
	        scene.getStylesheets().add(getClass().getResource("./popupStyle.css").toExternalForm());
	        stage.setScene(scene);
	        stage.show();
	    
	}
	
	public void showEventPopup(Event curSelect) {
		BorderPane borderPane = new BorderPane();
        Stage stage = new Stage();
        stage.setTitle("Các sự kiện lịch sử");
        Image imagebackground = new Image(
                "https://media.discordapp.net/attachments/755083836169257062/1071699179488944128/image.png?width=1190&height=670");
        BackgroundImage backgroundImage = new BackgroundImage(imagebackground, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        borderPane.setBackground(new Background(backgroundImage));

        Image image = new Image(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRrDuM8-H23BUUNCV6C90QbUqbWV2iyQ7b_fQ&usqp=CAU");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(233);
        imageView.setFitHeight(145);
        // imageView.setStyle("-fx-border-color: red; -fx-border-width:
        // medium;-fx-border-style: solid;");
        imageView.getStyleClass().add("border-style");

        Label ten = new Label("Tên Sự Kiện: " + curSelect.getTen());
        Label thoiGian = new Label("Thời gian diễn ra: " + curSelect.getThoiGian());
        Label diaDiem = new Label("Địa điểm : " + curSelect.getDiaDiem());

        ten.getStyleClass().add("text-color");
        thoiGian.getStyleClass().add("text-color");
        diaDiem.getStyleClass().add("text-color");

        VBox vbox = new VBox();
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(20, 20, 20, 20));
        hbox.setSpacing(25);
        HBox picturebox = new HBox();
        // picturebox.setSpacing(20);
        picturebox.getChildren().add(imageView);
        picturebox.setStyle(
                "-fx-border-color: white; -fx-border-width: 3px; -fx-effect : dropshadow(one-pass-box,white, 5, 5, 0, 0);");

        VBox labelbox = new VBox();
        // labelbox.setSpacing(20);
        labelbox.getChildren().addAll(ten, thoiGian, diaDiem);

        hbox.getChildren().addAll(picturebox, labelbox);

        VBox contentText = new VBox();

        vbox.getChildren().addAll(hbox, contentText);
        borderPane.setCenter(vbox);
        picturebox.setAlignment(Pos.CENTER_LEFT);
        labelbox.setAlignment(Pos.BASELINE_LEFT);
        hbox.setAlignment(Pos.CENTER);
        contentText.setAlignment(Pos.CENTER);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(borderPane, 800, 600);
        scene.getStylesheets().add(getClass().getResource("./popupStyle.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
	}
}
