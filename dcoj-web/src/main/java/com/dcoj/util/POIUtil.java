package com.dcoj.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class POIUtil {

    private final static String xls = "xls";
    private final static String xlsx = "xlsx";

    /**
     * 读入excel文件，解析后返回
     * @param fileName
     * @param in
     * @throws IOException
     */
    public static List<String[]> readExcel(InputStream in,String fileName) throws IOException {
        //检查文件
        //checkFile(file);
        //获得Workbook工作薄对象
        Workbook workbook = getWorkBook(in, fileName);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<String[]> list = new ArrayList<>();
        if(workbook != null){
            for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
                //获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if(sheet == null){
                    continue;
                }
                //获得当前sheet的开始行
                int firstRowNum  = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                //获得当前行的开始列
                int firstCellNum = 0;
                //获得当前行的列数
                int lastCellNum = 0;
                //循环所有行
                for(int rowNum = firstRowNum; rowNum <= lastRowNum; rowNum++){
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    if(row == null){
                        continue;
                    }
                    //获取列数，以第一行标题为准
                    if(rowNum == 0){
                        //获得当前行的开始列
                        firstCellNum = row.getFirstCellNum();
                        //获得当前行的列数
                        lastCellNum = row.getLastCellNum();
                        continue;
                    }
                    String[] cells = new String[lastCellNum];
                    //循环当前列
                    for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum] = getCellValue(cell);
                    }
                    if(cells.length > 0){
                        int index = 0;
                        for (String c : cells) {
                            if(StringUtils.isEmpty(c)){
                                index++;
                            }
                        }
                        if(cells.length != index){
                            list.add(cells);
                        }
                    }
                }
            }
        }
        in.close();
        return list;
    }

    public static void checkFile(File file) throws IOException{
        //判断文件是否存在
        if(null == file){
            throw new FileNotFoundException("文件不存在！");
        }
        //获得文件名
        String fileName = file.getName();
        //判断文件是否是excel文件
        if(!fileName.endsWith(xls) && !fileName.endsWith(xlsx)){
            throw new IOException(fileName + "不是excel文件");
        }
    }

    public static Workbook getWorkBook(InputStream is, String fileName) {
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if(fileName.endsWith(xls)){
                //2003
                workbook = new HSSFWorkbook(is);
            }else if(fileName.endsWith(xlsx)){
                //2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return workbook;
    }

    public static String getCellValue(Cell cell){
        String cellValue = "";
        try {
            if (cell == null) {
                return cellValue;
            }
            //把数字当成String来读，避免出现1读成1.0的情况
//            if (cell.getCellTypeEnum() == CellType.STRING) {
////                cell.setCellType(CellType.STRING);
////            }
            //判断数据的类型
            if(cell.getCellTypeEnum() == CellType.NUMERIC) { //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
            } else if(cell.getCellTypeEnum() == CellType.STRING) {
                cellValue = String.valueOf(cell.getStringCellValue());
            } else if(cell.getCellTypeEnum() == CellType.BOOLEAN) {
                cellValue = String.valueOf(cell.getBooleanCellValue());
            } else if(cell.getCellTypeEnum() == CellType.FORMULA) {
                cellValue = String.valueOf(cell.getCellFormula());
            } else if(cell.getCellTypeEnum() == CellType.BLANK) {
                cellValue = "";
            } else if(cell.getCellTypeEnum() == CellType.ERROR) {
                cellValue = "非法字符";
            } else {
                cellValue = "未知类型";
            }
            return cellValue.trim();
        } catch (Exception e) {
            return cellValue.trim();
        }
    }
}
