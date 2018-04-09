/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chathandler;

import java.util.Comparator;

/**
 *
 * @author ahmed
 * used to sort the list using Collections
 */
public class Compartor implements Comparator<String>{

    @Override
    public int compare(String o1, String o2) {
    return o1.hashCode() - o2.hashCode();  
    }

    
}
