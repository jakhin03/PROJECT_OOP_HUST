package com.project.application;

import java.io.IOException;

import com.project.application.historygui.HistoryGUI;
import com.project.historydatabase.HistoryDatabase;

import com.project.searchengine.SearchEngine;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import com.project.historydatabase.dynasty.Dynasty;
import com.project.historydatabase.festival.Festival;
import com.project.historydatabase.figure.King;
import com.project.historydatabase.figure.Character;
import com.project.historydatabase.relic.Relic;
import com.project.historydatabase.event.Event;

public class MainController {
	ObservableList<Character> listObservablesCharacter = HistoryDatabase.<Character>getHistoryDatabase("characterUpdate.json", Character.class);
    ObservableList<King> listObservablesKing = HistoryDatabase.<King>getHistoryDatabase("king.json", King.class);
    ObservableList<Dynasty> listObservablesDynasty = HistoryDatabase.<Dynasty>getHistoryDatabase("dynasty.json", Dynasty.class);
    ObservableList<Festival> listObservablesFestival = HistoryDatabase.<Festival>getHistoryDatabase("festival.json", Festival.class);
    ObservableList<Relic> listObservablesRelic = HistoryDatabase.<Relic>getHistoryDatabase("relic.json", Relic.class);
    ObservableList<Event> listObservablesEvent = HistoryDatabase.<Event>getHistoryDatabase("event.json", Event.class);

    HistoryGUI historyGUI = new HistoryGUI();
    @FXML
    private MenuItem menuItemKing;

    @FXML
    private MenuItem menuItemCharacter;

    @FXML
    private MenuItem menuItemFestival;
    
    @FXML
    private MenuItem menuItemDynasty;
    
    @FXML
    private MenuItem menuItemRelic;
    
    @FXML
    private MenuItem menuItemEvent;

    @FXML
    private BorderPane borderPane;

    @FXML
    private MenuButton menuBtnSearchField;

    @FXML
    private TextField tfSearch;
    
    @FXML
    void initialize() {
    	kingSelected();
    }

    // Search field chosen
    @FXML
    void clickMenuItem(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource();
        String labelSelecItem = menuItem.getText();

        menuBtnSearchField.setText(menuItem.getText());
        
        switch (labelSelecItem) {
            case "Vua":
                kingSelected();
                break;
            case "Nhân Vật Lịch Sử":
                characterSelected();
                break;
            case "Sự kiện lịch sử":
                eventSelected();
                break;

            case "Di tích lịch sử":
                relicSelected();
                break;
            case "Lễ Hội Văn Hóa":
                festivalSelected();
                break;
            case "Triều Đại Lịch Sử":
                dynastySelected();
                break;
            default:
                break;
        }
    }
    
    void kingSelected() {
    	String[] nameColKing = { "Tên ", "Năm trị vì", "Miếu hiệu", "Thụy hiệu", "Niên hiệu" };
        String[] kingStr = { "ten", "namTriVi", "mieuHieu", "thuyHieu", "nienHieu" };
        TableView<King> tableKingView = new TableViewCustom<King>().makTableView(
                nameColKing, kingStr);
        tableKingView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() > 1) {
                King curSelect = tableKingView.getSelectionModel().getSelectedItem();
                historyGUI.showKingPopup(curSelect);
            }
        });
        SearchEngine<King> searchKing = new SearchEngine<King>();
        tableKingView.setItems(searchKing.searchList(listObservablesKing, tfSearch, King.class));
        borderPane.setCenter(tableKingView);
    }
    
    void characterSelected() {
    	String[] nameColCharacter = { "Tên Nhân Vật", "Quê quán", "Năm sinh", "Năm mất" };
        String[] CharacterStr = { "ten", "queQuan", "namSinh", "namMat" };
        TableView<Character> tableCharacterView = new TableViewCustom<Character>().makTableView(
                nameColCharacter, CharacterStr);
        tableCharacterView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() > 1) {
                Character curSelect = tableCharacterView.getSelectionModel().getSelectedItem();
                historyGUI.showCharacterPopup(curSelect, listObservablesDynasty, listObservablesKing);
            }
        });
        SearchEngine<Character> searchCharacter = new SearchEngine<Character>();
        tableCharacterView.setItems(searchCharacter.searchList(listObservablesCharacter, tfSearch, Character.class));
        borderPane.setCenter(tableCharacterView);
    }
    
    void dynastySelected() {
    	String[] nameColDynasty = { "Tên Triều đại", "Năm bất đầu", "Năm kết thúc", "Kinh đô" };
        String[] dynastyStr = { "name", "startYear", "endYear", "capital" };
        TableView<Dynasty> tableDynastyView = new TableViewCustom<Dynasty>().makTableView(
                nameColDynasty, dynastyStr);
        tableDynastyView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() > 1) {
                Dynasty curSelect = tableDynastyView.getSelectionModel().getSelectedItem();
                historyGUI.showDynastyPopup(curSelect, listObservablesKing);
            }
        });
        SearchEngine<Dynasty> searchDynasty = new SearchEngine<Dynasty>();
        tableDynastyView.setItems(searchDynasty.searchList(listObservablesDynasty, tfSearch, Dynasty.class));
        borderPane.setCenter(tableDynastyView);
    }
    
    void eventSelected() {
    	String[] nameColEvent = { "Tên sự kiện", "Thời gian diễn ra", "Địa điểm" };
        String[] eventFields = { "ten", "thoiGian", "diaDiem" };
        TableView<Event> tableEventView = new TableViewCustom<Event>().makTableView(
                nameColEvent, eventFields);
        tableEventView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() > 1) {
                Event curSelect = tableEventView.getSelectionModel().getSelectedItem();
                historyGUI.showEventPopup(curSelect);
            }
        });
        SearchEngine<Event> searchEvent = new SearchEngine<Event>();
        tableEventView.setItems(searchEvent.searchList(listObservablesEvent, tfSearch, Event.class));
        borderPane.setCenter(tableEventView);
    }
    
    void festivalSelected() {
    	String[] nameColFestival = { "Tên Lễ hội", "Thời gian", "Địa điểm" };
        String[] festivalStr = { "tenLeHoi", "thoigian", "diaDiem" };
        TableView<Festival> tableFestivalView = new TableViewCustom<Festival>().makTableView(
                nameColFestival, festivalStr);
        tableFestivalView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() > 1) {
                Festival curSelect = tableFestivalView.getSelectionModel().getSelectedItem();
                historyGUI.showFestivalPopup(curSelect, listObservablesCharacter, listObservablesDynasty, listObservablesKing);
            }
        });
        SearchEngine<Festival> searchFestival = new SearchEngine<Festival>();
        tableFestivalView
                .setItems(searchFestival.searchList(listObservablesFestival, tfSearch, Festival.class));
        borderPane.setCenter(tableFestivalView);
    }
    
    void relicSelected() {
    	String[] nameColRelic = { "Tên Di tích", "Địa điểm", "Thể loại", "Xếp hạng" };
        String[] RelicStr = { "name", "location", "type", "rank" };
        TableView<Relic> tableRelicView = new TableViewCustom<Relic>().makTableView(
                nameColRelic, RelicStr);
        tableRelicView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
            if (e.getClickCount() > 1) {
                Relic curSelect = tableRelicView.getSelectionModel().getSelectedItem();
                historyGUI.showRelicPopup(curSelect, listObservablesCharacter, listObservablesKing,listObservablesDynasty);
            }
        });
        SearchEngine<Relic> searchRelic = new SearchEngine<Relic>();
        tableRelicView.setItems(searchRelic.searchList(listObservablesRelic, tfSearch, Relic.class));
        borderPane.setCenter(tableRelicView);
    }

    @FXML
    void search(ActionEvent event) {
    }

    @FXML
    void pressEnter(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            search(null);
        } else {

        }
    }
}

class TableViewCustom<T> {
    public TableView<T> makTableView(String[] nameColT, String[] TStr) {
        TableView<T> tableTView = new TableView<T>();
        tableTView.getColumns().clear();
        for (int i = 0; i < TStr.length; i++) {
            TableColumn<T, String> ColT = new TableColumn<T, String>(nameColT[i]);
            ColT.prefWidthProperty().bind(tableTView.widthProperty().multiply((double) 1 / TStr.length));
            ColT.setCellValueFactory(new PropertyValueFactory<T, String>(TStr[i]));
            tableTView.getColumns().add(ColT);
        }
        return tableTView;
    }
}