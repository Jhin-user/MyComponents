/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RunAsWindowsUI;

import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.ImageIcon;

/**
 *
 * @author Jhin
 */
public class test {

    public test() {
        if (getClass().getResource("/Images/Viego.png") == null) {
            System.out.println("null");
        } else {
            System.out.println("dell");
        }
    }

    @SuppressWarnings("ResultOfObjectAllocationIgnored")
    public static void main(String[] args) {
        new test();
    }
}
