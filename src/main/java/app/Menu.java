package app;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class Menu {
    private List<MenuItem> items;

    public void print(){
        for(int i =0; i < items.size(); i++){
            MenuItem item =  items.get(i);
            System.out.println(i + ". " + item.getLabel());
        }
    }

    public void executeOption(int option){
        if (option >= 0 & option< items.size()){
            items.get(option).getAction().run();
        }
    }
}