package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {
    
    // LOGGING
    private static final Logger BoardDaoLogger = Logger.getLogger(FileSudokuBoardDao.class.getName());
    private String path = "saved_board";

    public FileSudokuBoardDao(String path) {
        this.path = path;
    }

    @Override
    public SudokuBoard read() throws SudokuReadException {
        try (FileInputStream fileIn = new FileInputStream(path);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            return (SudokuBoard) objectIn.readObject();
        }  catch (IOException | ClassNotFoundException e) {
            throw new SudokuReadException("ReadError", e);
        }
    }

    @Override
    public void write(SudokuBoard obj) throws SudokuWriteException {
        try (FileOutputStream fileOut = new FileOutputStream(path);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(obj);
            BoardDaoLogger.info("Object saved to " + path + " file.");
        } catch (IOException e) {
            throw new SudokuWriteException("WriteError", e);
        }
    }

    public void write(SudokuBoard obj, SudokuBoard obj2)  throws SudokuWriteException {
        ArrayList<SudokuBoard> boards = new ArrayList<>();
        try (FileOutputStream fileOut = new FileOutputStream(path);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            boards.add(obj);
            boards.add(obj2);
            objectOut.writeObject(boards);
            BoardDaoLogger.info("Objects saved to " + path + " file.");
        } catch (IOException e) {
            throw new SudokuWriteException("WriteError", e);
        }
    }

    public ArrayList<SudokuBoard> readWithPrimal() throws SudokuReadException {
        try (FileInputStream fileIn = new FileInputStream(path);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            return (ArrayList<SudokuBoard>) objectIn.readObject();
        }  catch (IOException | ClassNotFoundException e) {
            throw new SudokuReadException("ReadError", e);
        }
    }

    @Override
    public void close() throws Exception {
        BoardDaoLogger.info("Dao closed");
    }
}
