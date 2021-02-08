package com.example.scorequery;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.WallpaperInfo;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public List<StudentScore> list=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        try {
            list=readExcel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Button queryScore=(Button)findViewById(R.id.button_query);
        queryScore.setOnClickListener(new View.OnClickListener() {
            int number_lenth;
            int name_length;
            @Override
            public void onClick(View v) {
                //finish();
                //number= String.valueOf(((EditText)findViewById(R.id.stu_number)).getText()).toString();
                //Log.d("number", String.valueOf(((EditText)findViewById(R.id.stu_number)).getText()));
                //判断考号和姓名是否为空，为空则弹出alert
                number_lenth= ((EditText)findViewById(R.id.stu_number)).length();
                name_length=((EditText)findViewById(R.id.stu_name)).length();
                if(number_lenth==0){
                    AlertDialog.Builder dialog1 = new AlertDialog.Builder(MainActivity.this);
                    dialog1.setTitle("错误：");
                    dialog1.setMessage("请输入考号");
                    dialog1.setCancelable(false);
                    dialog1.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    dialog1.show();
                }else if(name_length==0){
                    AlertDialog.Builder dialog2 = new AlertDialog.Builder(MainActivity.this);
                    dialog2.setTitle("错误：");
                    dialog2.setMessage("请输入姓名");
                    dialog2.setCancelable(false);
                    dialog2.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    dialog2.show();
                }else{
                    //判断考号和姓名是否和excel匹配，匹配则打出成绩
                    for(int i=0;i<list.size();i++){
                        //Log.d("名字",list.get(i).getNumber());
                        //String stu_number= String.valueOf(((EditText)findViewById(R.id.stu_number)).getText());
                        if((list.get(i).getNumber()).equals(((EditText)findViewById(R.id.stu_number)).getText().toString())){
                            if(list.get(i).getName().equals(((EditText)findViewById(R.id.stu_name)).getText().toString())){
                                //if(list.get(i).getChinese().equals("")){
                                //    ((TextView)findViewById(R.id.edit_chinese)).setText("该科目未考试");
                                //}
                                ((TextView)findViewById(R.id.edit_chinese)).setText(list.get(i).getChinese());
                                ((TextView)findViewById(R.id.edit_math)).setText(list.get(i).getMath());
                                ((TextView)findViewById(R.id.edit_english)).setText(list.get(i).getEnglish());
                                ((TextView)findViewById(R.id.edit_physics)).setText(list.get(i).getPhysics());
                                ((TextView)findViewById(R.id.edit_politics)).setText(list.get(i).getPolitics());
                                ((TextView)findViewById(R.id.edit_history)).setText(list.get(i).getHistory());
                                ((TextView)findViewById(R.id.edit_biology)).setText(list.get(i).getBiology());
                                ((TextView)findViewById(R.id.edit_geography)).setText(list.get(i).getGeography());
                                ((TextView)findViewById(R.id.edit_sum)).setText(list.get(i).getSum());
                                ((TextView)findViewById(R.id.edit_rank)).setText(list.get(i).getRank());
                                ((TextView)findViewById(R.id.edit_gradeRank)).setText(list.get(i).getGradeRank());
                                break;
                            }
                        }
                        if((i+1)==list.size()){
                            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                            dialog.setTitle("错误：");
                            dialog.setMessage("考号和姓名不匹配，请核对后重新输入考号和姓名！");
                            dialog.setCancelable(false);
                            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            dialog.show();
                        }
                    }
                    //Toast.makeText(MainActivity.this, "version1.0.0", Toast.LENGTH_SHORT);
                }
            }
        });

        //for(int i=0;i<list.size();i++){
        //    StudentScore studentScore = list.get(47);
        //    Log.d("最后一行的学生名字", studentScore.getName());
        //}


    }

    public List<StudentScore> readExcel() throws FileNotFoundException {
        List<StudentScore> list = new ArrayList<StudentScore>();
        InputStream is = null;
        Workbook workbook = null;

        //String uriStr = "android.resource://" + getApplicationContext().getPackageName() + "/"+R.raw.stu_score;
        //Uri uri=Uri.parse(uriStr);
        try {
            is = this.getResources().openRawResource(R.raw.stu_score);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            workbook = new HSSFWorkbook(is);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //得到一个工作表
        Sheet sheet = workbook.getSheetAt(0);
        //获得表头
        Row rowHead = sheet.getRow(0);
        //Log.d("roWHead", String.valueOf(rowHead.getPhysicalNumberOfCells()));
        //判断表头是否正确
        if (rowHead.getPhysicalNumberOfCells() != 13) {
            Log.d("表头","列数不对！");
        }

        //获得数据的总行数
        int totalRowNum = sheet.getPhysicalNumberOfRows();
        //Log.d("totalRowNum", String.valueOf(totalRowNum));
        //结果为49
        //int totalColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        //Log.d("totalColumn", String.valueOf(totalColumn));
        //结果为13
        //Log.d("第二行考号", sheet.getRow(1).getCell(1).getStringCellValue() );


        //获得所有数据
        for (int i = 1; i < totalRowNum; i++) {
            //获得第i行对象
            Row row = sheet.getRow(i);
            StudentScore studentScore=new StudentScore();
            if(row.getCell(0)!=null){
                row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
                studentScore.setNumber(row.getCell(0).getStringCellValue());
                //Log.d("第"+i+"行考号", studentScore.getNumber() );
            }
            if(row.getCell(1)!=null){
                row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                studentScore.setName(row.getCell(1).getStringCellValue());;
            }
            if(row.getCell(2)!=null){
                row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
                studentScore.setChinese(row.getCell(2).getStringCellValue());
            }
            if(row.getCell(3)!=null){
                row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
                studentScore.setMath(row.getCell(3).getStringCellValue());
            }
            if(row.getCell(4)!=null){
                row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                studentScore.setEnglish(row.getCell(4).getStringCellValue());
            }
            if(row.getCell(5)!=null){
                row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
                studentScore.setPhysics(row.getCell(5).getStringCellValue());
            }
            if(row.getCell(6)!=null){
                row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
                studentScore.setPolitics(row.getCell(6).getStringCellValue());
            }
            if(row.getCell(7)!=null){
                row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
                studentScore.setHistory(row.getCell(7).getStringCellValue());
            }
            if(row.getCell(8)!=null){
                row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
                studentScore.setBiology(row.getCell(8).getStringCellValue());
            }
            if(row.getCell(9)!=null){
                row.getCell(9).setCellType(Cell.CELL_TYPE_STRING);
                studentScore.setGeography(row.getCell(9).getStringCellValue());
            }
            if(row.getCell(10)!=null){
                row.getCell(10).setCellType(Cell.CELL_TYPE_STRING);
                studentScore.setSum(row.getCell(10).getStringCellValue());
            }
            if(row.getCell(11)!=null){
                row.getCell(11).setCellType(Cell.CELL_TYPE_STRING);
                studentScore.setRank(row.getCell(11).getStringCellValue());
            }
            if(row.getCell(12)!=null){
                row.getCell(12).setCellType(Cell.CELL_TYPE_STRING);
                studentScore.setGradeRank(row.getCell(12).getStringCellValue());
            }
            list.add(studentScore);
            //Log.d("第"+i+"行", "已完成" );
        }
        //Log.d("List","已完成");
        //Log.d("学生姓名", list.get(47).getPhysics());
        return list;

    }

}
