package com.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
/**
 * @author eddy.li
 *
 */
public class ExcelReader {
    private final  StringBuffer strBuf;
    private final Document dom;
    private String fileName;

    /**
     * @param strBuf
     */
    public ExcelReader() {
        dom=DocumentHelper.createDocument();//创建xml文件
        strBuf = new StringBuffer();
    }

    public void FileOut(final String fileOutPath){
        final File out = new File(fileOutPath);
        if(fileOutPath.endsWith("txt")){
            try {
                final FileWriter writer = new FileWriter(out);
                writer.write(strBuf.toString());
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        if(fileOutPath.endsWith("xml")){
            Writer w;
            try {
                w = new FileWriter(out);
                final OutputFormat outFm = new OutputFormat(" ", true);
                final XMLWriter writer = new XMLWriter(w,outFm);
                writer.write(dom);
                writer.flush();
                writer.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void read(final String sourceFilePath, final String desFilePath){
        if(desFilePath.endsWith("txt")){
            readToTxt(sourceFilePath, desFilePath);
        }
        if(desFilePath.endsWith("xml")){
            readToXml(sourceFilePath, desFilePath);
        }
    }

    public  void readToTxt(final String sourceFilePath, final String desFilePath){
        boolean isE2007 = false;    //判断是否是excel2007格式
        if(sourceFilePath.endsWith("xlsx")) {
            isE2007 = true;
        }
        try {
            final InputStream input = new FileInputStream(sourceFilePath);  //建立输入流
            Workbook wb  = null;
            //根据文件格式(2003或者2007)来初始化
            if(isE2007) {
                wb = new XSSFWorkbook(input);
            } else {
                wb = new HSSFWorkbook(input);
            }

            final int nSheets = wb.getNumberOfSheets();

            for(int i=0; i<nSheets; i++){
                readExcel(wb,i);
            }

            FileOut(desFilePath);
        } catch (final IOException ex) {
            ex.printStackTrace();
        }
    }


    public  void readToXml(final String sourceFilePath, final String desFilePath){
        boolean isE2007 = false;    //判断是否是excel2007格式
        if(sourceFilePath.endsWith("xlsx")) {
            isE2007 = true;
        }
        try {
            final File file = new File(sourceFilePath);
            final InputStream input = new FileInputStream(file);  //建立输入流
            this.fileName = file.getName();
            Workbook wb  = null;
            //根据文件格式(2003或者2007)来初始化
            if(isE2007) {
                wb = new XSSFWorkbook(input);
            } else {
                wb = new HSSFWorkbook(input);
            }
            final Element root = dom.addElement("System");
            root.addAttribute("postion", this.fileName);
            final int nSheets = wb.getNumberOfSheets();
            for(int i=0; i<nSheets; i++){
                final Element sheet = root.addElement("sheet");
                readExcel(sheet,wb,i);
            }
            FileOut(desFilePath);
        } catch (final IOException ex) {
            ex.printStackTrace();
        }
    }
  //构造Elemt添加到elementSheet;
    //检查是否是header Y 添加到ArrayList中，
    //N构造属性 中间的值添加到ArraList中的元素
    public  void readExcel(final Element elemSheet ,final Workbook wb, final int index){
        Element subRoot = null;
        Element elements = null;
        Element element = null;
        boolean dataFlag = false;
        boolean isHeader = false;
        final boolean isbegin_attr = false;
        int headerRow = -1;
        final int nAttr = 2;
        final Sheet sheet = wb.getSheetAt(index);
        final Iterator<Row> rows = sheet.rowIterator();
        final ArrayList<String> headerList = new ArrayList<String>();

        final String sheetName = sheet.getSheetName();
        elemSheet.addAttribute("id", sheetName);
        elemSheet.addAttribute("position", this.fileName+","+sheetName);

        while (rows.hasNext()) {
            final Row row = rows.next();
            final Iterator<Cell> cells = row.cellIterator();
            while (cells.hasNext()) {
                final Cell cell = cells.next();
                final int nColNum = cell.getColumnIndex()+1;
                String strtmp ="";
                switch (cell.getCellType()) {
                case HSSFCell.CELL_TYPE_NUMERIC:
                    strtmp=cell.getNumericCellValue()+"";
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    strtmp = cell.getStringCellValue()+"";
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    strtmp = cell.getBooleanCellValue()+"";
                    break;
                case HSSFCell.CELL_TYPE_FORMULA:
                    strtmp = cell.getCellFormula()+ "";
                    break;
                default:
                break;
                }
                if(strtmp.endsWith("*")){
                    strtmp=strtmp.replace("*", "");
                }
                if(strtmp.startsWith("##")){
                    subRoot = elemSheet.addElement(strtmp.replaceAll("##", "").trim());
                    subRoot.addAttribute("positon", this.fileName+","+sheetName+","+nColNum);
                    continue;
                }
//                //attr
//                if(strtmp.startsWith("##begin_attr")){
//                    elements = subRoot.addElement("elements");
//                    elements.addAttribute("id", "default");
//                    dataFlag = true;
//                    isHeader =  true;
//                    headerRow = row.getRowNum()+1;
//                    isbegin_attr=true;
//                    continue;
//                }

                if(strtmp.startsWith("#begin")){
                    elements = subRoot.addElement("elements");
                    elements.addAttribute("id", "default");
                    dataFlag = true;
                    isHeader =  true;
                    headerRow = row.getRowNum()+1;
                    continue;
                }

                if(strtmp.startsWith("#end")){
//                    System.out.println("#end!!!!");
                    headerList.clear();
                    dataFlag = false;
                    continue;
                }

                if(isHeader&&row.getRowNum()==headerRow){
                    headerList.add(strtmp);
                }
                if(dataFlag && headerList.size()!=0 && element!=null){

                    final int curCol = cell.getColumnIndex();

//                    if(isbegin_attr){
//                        subRoot.addAttribute(headerList.get(curCol), strtmp);
//                        nAttr--;
//                        if(nAttr<=0){
//                            isbegin_attr = false;
//                        }
//                        continue;
//                    }
                    if(curCol>=headerList.size()){
                        continue;
                    }
                    final Element childElement= element.addElement(headerList.get(curCol));
                    childElement.setText(strtmp);
                }
            }
            if(isHeader&&row.getRowNum()==headerRow){
                isHeader = false;
            }
            if(dataFlag && headerList.size()>0){
//                System.out.println("#end????");
                element = elements.addElement("element");
                element.addAttribute("position", this.fileName+","+sheetName+","+ (row.getRowNum()+2));
            }
        }
    }


    public  void readExcel(final Workbook wb, final int index){
        final Sheet sheet = wb.getSheetAt(index);     //获得第一个表单
        final Iterator<Row> rows = sheet.rowIterator(); //获得第一个表单的迭代器
        while (rows.hasNext()) {
            final Row row = rows.next();  //获得行数据
//            System.out.println("Row #" + row.getRowNum());  //获得行号从0开始
            final Iterator<Cell> cells = row.cellIterator();    //获得第一行的迭代器
            while (cells.hasNext()) {
                final Cell cell = cells.next();
                String strtmp ="";
//                System.out.println("Cell #" + cell.getColumnIndex());
                switch (cell.getCellType()) {   //根据cell中的类型来输出数据
                case HSSFCell.CELL_TYPE_NUMERIC:
                    strtmp=cell.getNumericCellValue()+" ";
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    strtmp = cell.getStringCellValue()+" ";
                    break;
                case HSSFCell.CELL_TYPE_BOOLEAN:
                    strtmp = cell.getBooleanCellValue()+" ";
                    break;
                case HSSFCell.CELL_TYPE_FORMULA:
                    strtmp = cell.getCellFormula()+ " ";
                    break;
                default:
                break;
                }
                strBuf.append(strtmp);
            }
            strBuf.append("\n");
        }
    }

    public static void main(final String[] args) {
//        new ExcelReader().readToXml("d:/system.xlsx", "D:\\xmlReader.xml");
//        new ExcelReader().readToTxt("d:/system.xlsx", "D:\\xmlReader.txt");
        new ExcelReader().read("./system.xlsx", "./xmlReader.xml");
        new ExcelReader().read("./system.xlsx", "./xmlReader.txt");

    }
}
