package source.Menus;

import java.util.ArrayList;

import source.LMSFacade;

public abstract class ListMenu extends Menu {
    protected ArrayList<?> list;
    protected int index;
    
    public ListMenu(LMSFacade facade, Menu pMenu, ArrayList<?> list, String failMsg){
        super(facade, pMenu);
        this.list = list;
        index = 0;
        if (list == null || list.size() == 0){
            back(failMsg);
        }
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

        updateHeader();
        getSelection();
    }

    protected void next(){
        index++;
        if (index >= list.size()) index = 0;

        updateHeader();
        getSelection();
    }

}
