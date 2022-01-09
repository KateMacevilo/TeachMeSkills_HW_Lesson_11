package com.teachMeSkills.lesson_11;
/**
 * Допустим есть файл с номерами документов.
 * Номером документа является строка, состоящая из букв и цифр(без служебных символов).
 * Пусть этот файл содержит каждый номер документа с новой строки и в строке никакой другой информации, только номер документа.
 * Валидный номер документа должен иметь длину 15 символов и начинаться с последовательности docnum
 * (далее любая последовательность букв/цифр) или сontract(далее любая последовательность букв/цифр).
 * <p>
 * Написать программу для чтения информации из входного файла - путь к входному файлу должне задаваться через консоль.
 * Программа должна проверять номера документов на валидность.
 * Валидные номера документов следует записать в один файл-отчет.
 * Невалидные номера документов следует записать в другой файл-отчет,
 * но после номеров документов следует добавить ифнформацию о том, почему этот документ невалиден.
 */

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите путь к файлу"); //C:\Users\Катя\Desktop\l_11_numDocs.txt
        String path = scanner.next();

        String data;
        String info;
        String pathOutValidFile = "C:\\Users\\Катя\\Desktop\\outValid.txt";
        String pathOutNotValidFile = "C:\\Users\\Катя\\Desktop\\outNotValid.txt";

        try (FileInputStream inFile = new FileInputStream(path);
             FileOutputStream outValidFile = new FileOutputStream(pathOutValidFile);
             FileOutputStream outNotValidFile = new FileOutputStream(pathOutNotValidFile);
             BufferedReader infileBuff = new BufferedReader(new InputStreamReader(inFile));
             BufferedWriter outFileBuff = new BufferedWriter(new OutputStreamWriter(outValidFile));
             BufferedWriter outFileBuff2 = new BufferedWriter(new OutputStreamWriter(outNotValidFile))) {

            while ((data = infileBuff.readLine()) != null) {
                info = NumDoc.checkNumDoc(data);
                if (info.isEmpty()) {
                    outFileBuff.write(data);
                    outFileBuff.newLine();
                    outFileBuff.flush();
                } else {
                    outFileBuff2.write(data + " - " + info);
                    outFileBuff2.newLine();
                    outFileBuff2.flush();
                }
                System.out.println(data);
            }

            System.out.println("Success");


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
