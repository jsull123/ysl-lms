package source.Menus;

import java.util.ArrayList;

import source.LMSFacade;

public abstract class ListMenu<T> extends Menu {
    protected ArrayList<T> list;
    protected int index;
    /**
     * @param: LMSFacade facade = object to use Facade methods
     * @param: Menu pMEnu = objecy to use Menu methods and data members
     * @param: ArrayList<T> list = Generic list that holds lists of courses,
     * users, etc. in child classes
     */
    public ListMenu(LMSFacade facade, Menu pMenu, ArrayList<T> list){
        super(facade, pMenu);
        this.list = list;
        index = 0;
    }
    /**
     * Constructs a ListMenu object
     * @param: LMSFacade facade = object to use Facade methods
     * @param: Menu pMEnu = objecy to use Menu methods and data members
     * @param: ArrayList<T> list = Generic list that holds lists of courses,
     * users, etc. in child classes
     * @param: String failMsg = The message the user sees if there is an error
     * 
     */
    public ListMenu(LMSFacade facade, Menu pMenu, ArrayList<T> list, String failMsg){
        super(facade, pMenu);
        this.list = list;
        index = 0;
        if (list == null || list.size() == 0){
            back(failMsg);
        }
    }
    /**
     * Accesses a list of any type in a child class
     * @return: The list data member of the current class
     */
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
    /**
     * Selects the previous element in the list
     */
    protected void prev(){
        index--;
        if (index < 0) index = list.size()-1;

        getSelection();
    }
    /**
     * Selects the next element in the list
     */
    protected void next(){
        index++;
        if (index >= list.size()) index = 0;

        getSelection();
    }
    /**
     * Removes an element from the lsit
     */
    protected void remove(){
        list.remove(index);
        index--;
        if (index < 0){
            index = 0;
        }
        
        getSelection();
    }

}
