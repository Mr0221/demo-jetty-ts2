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
        dom=DocumentHelper.createDocument();//����xml�ļ�
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
        boolean isE2007 = false;    //�ж��Ƿ���excel2007��ʽ
        if(sourceFilePath.endsWith("xlsx")) {
            isE2007 = true;
        }
        try {
            final InputStream input = new FileInputStream(sourceFilePath);  //����������
            Workbook wb  = null;
            //�����ļ���ʽ(2003����2007)����ʼ��
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
        boolean isE2007 = false;    //�ж��Ƿ���excel2007��ʽ
        if(sourceFilePath.endsWith("xlsx")) {
            isE2007 = true;
        }
        try {
            final File file = new File(sourceFilePath);
            final InputStream input = new FileInputStream(file);  //����������
            this.fileName = file.getName();
            Workbook wb  = null;
            //�����ļ���ʽ(2003����2007)����ʼ��
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
  //����Elemt��ӵ�elementSheet;
    //����Ƿ���header Y ��ӵ�ArrayList�У�
    //N�������� �м��ֵ��ӵ�ArraList�е�Ԫ��
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
        final Sheet sheet = wb.getSheetAt(index);     //��õ�һ����
        final Iterator<Row> rows = sheet.rowIterator(); //��õ�һ�����ĵ�����
        while (rows.hasNext()) {
            final Row row = rows.next();  //���������
//            System.out.println("Row #" + row.getRowNum());  //����кŴ�0��ʼ
            final Iterator<Cell> cells = row.cellIterator();    //��õ�һ�еĵ�����
            while (cells.hasNext()) {
                final Cell cell = cells.next();
                String strtmp ="";
//                System.out.println("Cell #" + cell.getColumnIndex());
                switch (cell.getCellType()) {   //����cell�е��������������
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
