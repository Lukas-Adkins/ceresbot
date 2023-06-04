package goobot.model.starlight;

import java.util.Random;

public class TableRequest {
    public Integer
    ubiquitous = 0,
    abundant = 0,
    plentiful = 0,
    common = 0,
    average = 0,
    scarce = 0,
    rare = 0,
    veryRare = 0,
    extremelyRare = 0,
    nearUnique = 0;

    private static Random rng = new Random();

    public TableRequest(){}
    
    public TableRequest(Integer ubiquitous, Integer abundant, Integer plentiful, Integer common, Integer average, Integer scarce, Integer rare, Integer veryRare, Integer extremelyRare, Integer nearUnique) {
        this.ubiquitous = ubiquitous;
        this.abundant = abundant;
        this.plentiful = plentiful;
        this.common = common;
        this.average = average;
        this.scarce = scarce;
        this.rare = rare;
        this.veryRare = veryRare;
        this.extremelyRare = extremelyRare;
        this.nearUnique = nearUnique;
    }

    public static TableRequest requisition(Integer commerce, Integer items){
        Integer ubiquitous = 0, abundant = 0, plentiful = 0, common = 0,
        average = 0, scarce = 0, rare = 0, veryRare = 0, extremelyRare = 0, nearUnique = 0;
        for(int i = 0; i < items; i++){
            Integer d100 = rng.nextInt(100) + 1;
            if(d100 < commerce - 50)
                nearUnique++;
            else if(d100 < commerce - 40)
                extremelyRare++; 
            else if(d100 < commerce - 30)
                veryRare++;
            else if(d100 < commerce - 20)
                rare++;
            else if(d100 < commerce - 10)
                scarce++;
            else if(d100 < commerce)
                average++;
            else
                common++;
        }
        return new TableRequest(ubiquitous, abundant, plentiful, common, average, scarce, rare, veryRare, extremelyRare, nearUnique);
    }
}
