package source.Menus;

import java.util.ArrayList;

import source.LMSFacade;

public abstract class ListMenu<T> extends Menu {
    protected ArrayList<T> list;
    protected int index;

    public ListMenu(LMSFacade facade, Menu pMenu, ArrayList<T> list){
        super(facade, pMenu);
        this.list = list;
        index = 0;
    }
    
    public ListMenu(LMSFacade facade, Menu pMenu, ArrayList<T> list, String failMsg){
        super(facade, pMenu);
        this.list = list;
        index = 0;
        if (list == null || list.size() == 0){
            back(failMsg);
        }
    }

    public T get(){
        return list.get(index);
    }

    protected abstract void updateHeader();

    @Override
    public void getSelection(){
        getSelection("");
    }

    @Override
    public void getSelection(String error) {
        updateHeader();
        super.getSelection(error);
    }
 
    protected void prev(){
        index--;
        if (index < 0) index = list.size()-1;

        getSelection();
    }

    protected void next(){
        index++;
        if (index >= list.size()) index = 0;

        getSelection();
    }

    protected void remove(){
        list.remove(index);
        index--;
        if (index < 0){
            index = 0;
        }
        
        getSelection();
    }

}
