package com.project.historydatabase;

import java.util.ArrayList;
import java.util.List;

import com.project.historydatabase.dynasty.Dynasty;
import com.project.historydatabase.festival.Festival;
import com.project.historydatabase.figure.Figure;

public class HistoryDatabase {
    private List<Dynasty> dynasties;
    private List<Figure> figures;
    private List<Festival> festivals;

    public HistoryDatabase() {
        this.dynasties = new ArrayList<>();
        this.figures = new ArrayList<>();
        this.festivals = new ArrayList<>();
    }

    public void addDynasty(Dynasty dynasty) {
        dynasties.add(dynasty);
    }

    public void addFigure(Figure Figure) {
        figures.add(Figure);
    }

    public void addFestival(Festival festival) {
        festivals.add(festival);
    }

    public List<Dynasty> getDynasties() {
        return dynasties;
    }

    public void setDynasties(List<Dynasty> dynasties) {
        this.dynasties = dynasties;
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public void setFigures(List<Figure> Figures) {
        this.figures = Figures;
    }

    public List<Festival> getFestivals() {
        return festivals;
    }

    public void setFestivals(List<Festival> festivals) {
        this.festivals = festivals;
    }
}
