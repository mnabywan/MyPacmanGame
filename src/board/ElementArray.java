package board;

import constants.Constants;


public class ElementArray {

    protected static Element[] [] elementArray;


    public ElementArray() {
        elementArray = new Element[Constants.ROWELEMENTS][Constants.COLUMNELEMENTS];
    }


    public static void addElement(Element element) {
        elementArray[element.getPosition().getRow()][element.getPosition().getColumn()] = element;
    }


    public static Element getElement(int row, int column) {
        return elementArray[row][column];
    }

}

