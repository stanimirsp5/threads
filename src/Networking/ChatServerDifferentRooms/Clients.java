package Networking.ChatServerDifferentRooms;

import java.io.*;
public class Clients {
    PrintWriter arr[];
    Clients(){
        arr = new PrintWriter[0];
    }
    synchronized void addElement(PrintWriter out){
        PrintWriter temp[]= new PrintWriter[arr.length+1];
        System.arraycopy(arr, 0, temp, 0, arr.length);
        temp[arr.length]= out;
        arr=temp;
    }
    synchronized void removeElement(PrintWriter out){
        for(int i=0;i<arr.length;i++){
            if(arr[i]==out){
                arr[i]=arr[arr.length-1];
                PrintWriter temp[]= new PrintWriter[arr.length-1];
                System.arraycopy(arr, 0, temp, 0, arr.length-1);
                arr=temp;
                break;
            }
        }
    }
    synchronized PrintWriter elementAt(int i){
        return arr[i];
    }
    synchronized int size(){
        return arr.length;
    }
}