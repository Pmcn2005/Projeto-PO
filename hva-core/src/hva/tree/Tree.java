package hva.tree;

import java.io.Serializable;

import hva.seasons.CurrentSeason;

public abstract class Tree implements Serializable {
    private String _treeId;
    private String _name;
    private int _age;
    private int _baseCleaningDifficulty;
    private CurrentSeason _currentSeason;
    private int _seasonCounter = 0;

    public Tree(String treeId, String name, int age, int baseCleaningDifficulty, CurrentSeason currentSeason) {
        _treeId = treeId;
        _name = name;
        _age = age;
        _baseCleaningDifficulty = baseCleaningDifficulty;
        _currentSeason = currentSeason;
    }

    public String getTreeId() {
        return _treeId;
    }

    public String getName() {
        return _name;
    }

    public int getAge() {
        return _age;
    }

    public int getBaseCleaningDifficulty() {
        return _baseCleaningDifficulty;
    }

    public CurrentSeason getCurrentSeason() {
        return _currentSeason;
    }

    public void updateTreeAge(){
        _seasonCounter++;
        if(_seasonCounter == 4){
            _age++;
            _seasonCounter = 0;
        }
    }

    public abstract String getBiologicalCycle();

    public abstract double getCleaningEffort();

    @Override
    public String toString(){
        return "Árvore|" + _treeId + "|" + _name + "|" + _age + "|" + _baseCleaningDifficulty; 
    }
}
