package org.example;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private String path = "saved_board";

    public FileSudokuBoardDao(String path) {
        this.path = path;
    }

    @Override
    public SudokuBoard read() {
        try (FileInputStream fileIn = new FileInputStream(path);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            return (SudokuBoard) objectIn.readObject();
        }  catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(SudokuBoard obj) {
        try (FileOutputStream fileOut = new FileOutputStream(path);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(obj);
            System.out.println("Object saved to " + path + " file.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception {
        System.out.println("Dao closed");
    }
}
