package com.project.application;

import java.io.IOException;

import com.project.historydatabase.HistoryDatabase;

import application.popup.details.DynastyDetails;
import application.popup.details.FestivalDetails;
import application.popup.details.FigureDetails;
import application.popup.details.KingDetails;
import application.popup.details.RelicDetails;
import application.popup.details.SuKienDetails;
import application.readdata.ReadData;
import application.search.Search;
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
import com.project.historydatabase.figure.Figure;
import com.project.historydatabase.figure.King;
import com.project.historydatabase.figure.Character;
import com.project.historydatabase.relic.Relic;
import com.project.historydatabase.event.Event;

public class MainController {

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

    // Search field chosen
    @FXML
    void clickMenuItem(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource();
        String lableSelecItem = menuItem.getText();

        menuBtnSearchField.setText(menuItem.getText());

        HistoryDatabase.getHistoryDatabase();

        switch (lableSelecItem) {
            case "King":
                String[] nameColKing = { "Tên ", "Năm trị vì", "Miếu hiệu", "Thụy hiệu", "Niên hiệu" };
                String[] kingStr = { "ten", "namTriVi", "mieuHieu", "thuyHieu", "nienHieu" };
                TableView<King> tableKingView = new TableViewCustom<King>().makTableView(
                        nameColKing, kingStr);
                tableKingView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                    if (e.getClickCount() > 1) {
                        King curSelect = tableKingView.getSelectionModel().getSelectedItem();
                        new KingDetails(curSelect);
                    }
                });
                Search<King> searchKing = new Search<King>();
                tableKingView.setItems(searchKing.searchList(listObservablesKing, tfSearch, King.class));
                borderPane.setCenter(tableKingView);
                break;
            case "Nhân Vật Lịch Sử":
                String[] nameColFigure = { "Tên Nhân Vật", "Quê quán", "Năm sinh", "Năm mất" };
                String[] figureStr = { "ten", "queQuan", "namSinh", "namMat" };
                TableView<Figure> tableFigureView = new TableViewCustom<Figure>().makTableView(
                        nameColFigure, figureStr);
                tableFigureView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                    if (e.getClickCount() > 1) {
                        Figure curSelect = tableFigureView.getSelectionModel().getSelectedItem();
                        new FigureDetails(curSelect, listObservablesDynasty, listObservablesKing);
                    }
                });
                Search<Figure> searchFigure = new Search<Figure>();
                tableFigureView.setItems(searchFigure.searchList(listObservablesFigure, tfSearch, Figure.class));
                borderPane.setCenter(tableFigureView);
                break;
            case "Sự kiện lịch sử":
                String[] nameColSuKien = { "Tên sự kiện", "Thời gian diễn ra", "Địa điểm" };
                String[] SuKienStr = { "ten", "thoi_gian", "dia_diem" };
                TableView<SuKien> tableSuKienView = new TableViewCustom<SuKien>().makTableView(
                        nameColSuKien, SuKienStr);
                tableSuKienView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                    if (e.getClickCount() > 1) {
                        SuKien curSelect = tableSuKienView.getSelectionModel().getSelectedItem();
                        new SuKienDetails(curSelect);
                    }
                });
                Search<SuKien> searchSuKien = new Search<SuKien>();
                tableSuKienView.setItems(searchSuKien.searchList(listObservablesSuKien, tfSearch, SuKien.class));
                borderPane.setCenter(tableSuKienView);
                break;

            case "Di tích lịch sử":
                String[] nameColRelic = { "Tên Di tích", "Địa điểm", "Thể loại", "Xếp hạng" };
                String[] RelicStr = { "name", "location", "type", "rank" };
                TableView<Relic> tableRelicView = new TableViewCustom<Relic>().makTableView(
                        nameColRelic, RelicStr);
                tableRelicView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                    if (e.getClickCount() > 1) {
                        Relic curSelect = tableRelicView.getSelectionModel().getSelectedItem();
                        new RelicDetails(curSelect, listObservablesFigure, listObservablesKing,
                                listObservablesDynasty);
                    }
                });
                Search<Relic> searchRelic = new Search<Relic>();
                tableRelicView.setItems(searchRelic.searchList(listObservablesRelic, tfSearch, Relic.class));
                borderPane.setCenter(tableRelicView);
                break;
            case "Lễ Hội Văn Hóa":
                String[] nameColFestival = { "Tên Lễ hội", "Thời gian", "Địa điểm" };
                String[] festivalStr = { "tenLeHoi", "thoigian", "diaDiem" };
                TableView<Festival> tableFestivalView = new TableViewCustom<Festival>().makTableView(
                        nameColFestival, festivalStr);
                tableFestivalView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                    if (e.getClickCount() > 1) {
                        Festival curSelect = tableFestivalView.getSelectionModel().getSelectedItem();
                        new FestivalDetails(curSelect, listObservablesFigure, listObservablesDynasty,
                                listObservablesKing);
                    }
                });
                Search<Festival> searchFestival = new Search<Festival>();
                tableFestivalView
                        .setItems(searchFestival.searchList(listObservablesFestival, tfSearch, Festival.class));
                borderPane.setCenter(tableFestivalView);
                break;
            case "Triều Đại Lịch Sử":
                String[] nameColDynasty = { "Tên Triều đại", "Năm bất đầu", "Năm kết thúc", "Kinh đô" };
                String[] dynastyStr = { "name", "startYear", "endYear", "capital" };
                TableView<Dynasty> tableDynastyView = new TableViewCustom<Dynasty>().makTableView(
                        nameColDynasty, dynastyStr);
                tableDynastyView.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent e) -> {
                    if (e.getClickCount() > 1) {
                        Dynasty curSelect = tableDynastyView.getSelectionModel().getSelectedItem();
                        new DynastyDetails(curSelect, listObservablesKing);
                    }
                });
                Search<Dynasty> searchDynasty = new Search<Dynasty>();
                tableDynastyView.setItems(searchDynasty.searchList(listObservablesDynasty, tfSearch, Dynasty.class));
                borderPane.setCenter(tableDynastyView);
                break;
            default:
                break;
        }
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